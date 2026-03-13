const canvas = document.getElementById('drawingCanvas');
const ctx = canvas.getContext('2d');
let stompClient = null;
let isDrawing = false;
let sprayInterval = null;
let batchPoints = []; // Batch to reduce messages
const sprayRadius = 20; // Adjust for nozzle size
const sprayDensity = 200; // Points per spray tick
const color = '#121212'; // Example color

// Connect to WebSocket
function connect() {
    const socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        stompClient.subscribe('/topic/draw', function(message) {
            const action = JSON.parse(message.body);
            drawSpray(action.points, action.color); // Apply received draw
        });
        // Request initial state (private subscription for reply)
        stompClient.subscribe('/user/queue/init', function(message) {
            loadImageToCanvas(message.body);
        });
        stompClient.send("/app/init", {}, {});
    });
}

// Load base64 image from server
function loadImageToCanvas(base64) {
    const img = new Image();
    img.src = `data:image/png;base64,${base64}`;
    img.onload = () => ctx.drawImage(img, 0, 0);
}

// Mouse events
canvas.addEventListener('mousedown', () => {
    isDrawing = true;
    sprayInterval = setInterval(spray, 50); // Spray every 50ms
});
canvas.addEventListener('mouseup', () => {
    isDrawing = false;
    clearInterval(sprayInterval);
    sendBatch(); // Send any remaining
});
canvas.addEventListener('mousemove', (e) => {
    if (isDrawing) currentPos = { x: e.offsetX, y: e.offsetY };
});

// Spray function: Generate random points around cursor
function spray() {
    for (let i = 0; i < sprayDensity; i++) {
        const offsetX = (Math.random() * 2 - 1) * sprayRadius;
        const offsetY = (Math.random() * 2 - 1) * sprayRadius;
        const point = { x: currentPos.x + offsetX, y: currentPos.y + offsetY };
        batchPoints.push(point);
        ctx.fillStyle = color;
        ctx.fillRect(point.x, point.y, 1, 1); // Draw local point
    }
    if (batchPoints.length >= 100) sendBatch(); // Batch send every 100 points
}

// Send batch to server
function sendBatch() {
    if (batchPoints.length > 0) {
        stompClient.send("/app/draw", {}, JSON.stringify({ points: batchPoints, color }));
        batchPoints = [];
    }
}

// Draw received spray
function drawSpray(points, drawColor) {
    ctx.fillStyle = drawColor;
    points.forEach(p => ctx.fillRect(p.x, p.y, 1, 1));
}

connect(); // Start connection
