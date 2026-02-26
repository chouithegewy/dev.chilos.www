document.addEventListener('DOMContentLoaded', (event) => {
  const hoverText = document.getElementById('hoverText');
  const sound = document.getElementById('mySound');

  hoverText.addEventListener('mouseover', function() {
    sound.currentTime = 0;
    sound.play();
  });

  hoverText.addEventListener('mouseout', function() {
    sound.pause();
    sound.currentTime = 0;
  });
});
