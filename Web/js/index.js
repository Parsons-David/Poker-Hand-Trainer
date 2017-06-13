function onTestClick() {
  var lbl = document.getElementById("lbl");
  var img1 = document.getElementById("img1");
  img1.src = "img/2c.gif";
}
function setUp(){
  console.log("Loading");
  var img;
  for(i = 1; i < 6; i++){
    img = document.getElementById("img" + i);
    img.src = "img/b.gif";
  }
}
