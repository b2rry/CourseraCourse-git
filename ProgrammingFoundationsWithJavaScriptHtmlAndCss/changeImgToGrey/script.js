var loadImg;
var resImg;
function upload(){
  var imgAtCanvas = document.getElementById("can1");
  var inputFile = document.getElementById("fInput");
 loadImg = new SimpleImage(inputFile);
  loadImg.drawTo(imgAtCanvas);
}
function changeColor(){
  resImg = loadImg;
  for(var px of resImg.values()){
    var avr = ((px.getRed()+px.getGreen()+px.getBlue())/3);
    px.setRed(avr);
    px.setGreen(avr);
    px.setBlue(avr);
  }
  var imgAtCanvas = document.getElementById("can2");
  resImg.drawTo(imgAtCanvas);
}