package dev.chilos.www;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import dev.chilos.www.Models.DrawAction;

@Controller
public class DrawController {

    private BufferedImage canvasImage = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB); // Example size
    private Graphics2D graphics = canvasImage.createGraphics();

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/draw")
    @SendTo("/topic/draw")
    public DrawAction handleDraw(DrawAction action) {
        applyActionToImage(action);
        return action;
    }

    private void applyActionToImage(DrawAction action) {
        graphics.setColor(action.toAwtColor());
        for (Point p : action.getPoints()) {
            // graphics.fillRect(p.x, p.y, 1, 1);
            graphics.fillOval(p.x - 2, p.y - 2, 4, 4);
        }
    }

    @MessageMapping("/init")
    public void sendInitialState(SimpMessageHeaderAccessor headerAccessor) throws IOException {
        // Get session ID or use @SendToUser for private reply
        String sessionId = headerAccessor.getSessionId();
        // Encode image
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(canvasImage, "png", baos);
        String base64Image = Base64.getEncoder().encodeToString(baos.toByteArray());
        messagingTemplate.convertAndSendToUser(sessionId, "/queue/init", base64Image); // Private queue
    }
}
