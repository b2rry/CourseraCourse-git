let redValue = 0;
let greenValue = 0;
let blueValue = 0;
let redValueInput = null;
let greenValueInput = null;
let blueValueInput = null;

let loadedPictureObj = null;
let loadedPicture = null;
let resImage = null;
let crashFlag = false;
var picHeight;
var picWidth;

function loadPicture(){
  redValue = 0;
  greenValue = 0;
  blueValue = 0;
  var imgAtCanvas = document.getElementById("can1");
  loadedPictureObj = document.getElementById("inputImage");
  loadedPicture = new SimpleImage(loadedPictureObj);
  loadedPicture.drawTo(imgAtCanvas);
  picWidth = loadedPicture.getWidth();
  picHeight = loadedPicture.getHeight();
  picWidth = Number(picWidth);
  document.getElementById("width").innerHTML = picWidth;
  document.getElementById("height").innerHTML = picHeight;
}

function pictureCheck(){
  if(loadedPicture == null || ! loadedPicture.complete()){
    alert("Background picture dont exist yet, try to repit loading");
    crashFlag = true;
  }
}

function setRedValue(){
  redValueInput =  document.getElementById("rangeRed");
  redValue = redValueInput.value;
  document.getElementById("redValueId").innerHTML = redValue;
}

function setGreenValue(){
  greenValueInput =  document.getElementById("rangeGreen");
  greenValue = greenValueInput.value;
  document.getElementById("greenValueId").innerHTML = greenValue;
}

function setBlueValue(){
  blueValueInput =  document.getElementById("rangeBlue");
  blueValue = blueValueInput.value;
  document.getElementById("blueValueId").innerHTML = blueValue;
}

function convertImage(){
  crashFlag = false;
  pictureCheck();
  if(crashFlag == true){
    return;
  }
  
  var resImage = new SimpleImage(loadedPicture.getWidth(),loadedPicture.getHeight());
  for(var px of loadedPicture.values()){
    
    let newRed = px.getRed() + redValue;
    let newGreen = px.getGreen() + greenValue;
    let newBlue = px.getBlue() + blueValue;
    
    if(newRed > 255){ newRed = 255}
    if(newGreen > 255){ newGreen = 255}
    if(newBlue > 255){ newBlue = 255}
    if(newRed < 0){ newRed = 0}
    if(newGreen < 0){ newGreen = 0}
    if(newBlue < 0){ newBlue = 0}
    
    var newPix = resImage.getPixel(px.getX(),px.getY());
    
    newPix.setRed(newRed);
    newPix.setGreen(newGreen);
    newPix.setBlue(newBlue);
    
    resImage.setPixel(px.getX(),px.getY(),newPix);
  }
  // let test = resImage.getPixel(10,10);
  // let a = test.getRed();
  // let b = test.hetGreen();
  // document.getElementById("a").innerHTML = a;
  // document.getElementById("b").innerHTML = b;
  
  var imgAtCanvas = document.getElementById("can1");
  resImage.drawTo(imgAtCanvas);
}