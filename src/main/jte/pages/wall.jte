@template.layout.main(
    title = "Wall",
    content = @`
<div class="p-4 mb-4">

  <h3 class="text-pink-50">The wall</h3>
  <br />
  <canvas id="drawingCanvas" class="w-full h-full" width="500px" height="500px"></canvas>

  <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.6.1/sockjs.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
  <script src="/js/draw.js"></script>

</div>
`
)
