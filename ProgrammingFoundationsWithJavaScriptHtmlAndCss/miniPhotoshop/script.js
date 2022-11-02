var loadedBgImage;
var loadedFgImage;
var resImage;
var crashFlag = false;
var elem1;

function uploadBg(){
  var imgAtCanvas = document.getElementById("can1");
  var inputFile = document.getElementById("fInput1");
 loadedBgImage = new SimpleImage(inputFile);
  loadedBgImage.drawTo(imgAtCanvas);
}

function uploadFg(){
  var imgAtCanvas = document.getElementById("can2");
  var inputFile = document.getElementById("fInput2");
 loadedFgImage = new SimpleImage(inputFile);
  loadedFgImage.drawTo(imgAtCanvas);
}

function picturesCheck(){
  if(loadedBgImage == null || ! loadedBgImage.complete()){
    alert("Background picture dont exist yet, try to repit loading");
    crashFlag = true;
  }
  if(loadedFgImage == null || ! loadedFgImage.complete()){
    alert("Foreground picture dont exist yet, try to repit loading");
    crashFlag = true;
  }
  elem1 = document.getElementById("hh2");
  if (elem1 == null) elem1 = document.getElementById("hh3");
  if((loadedBgImage.getWidth() != loadedFgImage.getWidth()) || (loadedBgImage.getHeight() != loadedFgImage.getHeight())){
    alert("Dimensions of pictures in pixels dont match");
    crashFlag = true;
    elem1.id = "hh3";
  }
}

function createNewImage(){
  crashFlag = false;
  picturesCheck();
  if(crashFlag == true){
    return;
  }
  elem1.id = "hh2";
  
  resImage = new SimpleImage(loadedFgImage.getWidth(),loadedFgImage.getHeight());
  for(var px of loadedFgImage.values()){
    var bgPix = loadedBgImage.getPixel(px.getX(),px.getY());
    if((px.getRed()+px.getBlue()) > px.getGreen()){
      resImage.setPixel(px.getX(),px.getY(),px);
    }else{
      resImage.setPixel(px.getX(),px.getY(),bgPix);
    }
  }
  var imgAtCanvas = document.getElementById("can3");
  resImage.drawTo(imgAtCanvas);
}