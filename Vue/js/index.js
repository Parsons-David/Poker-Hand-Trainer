
var app = new Vue({
  el: '#app',
  data: {
    message: 'Hello Vue!',
    hand: [
      "img/b.gif",
      "img/b.gif",
      "img/b.gif",
      "img/b.gif",
      "img/b.gif"]
  },
  methods : {
    onTestClick : function () {
      console.log(this.$data.hand);
      var hand = this.$data.hand;
      for(i in hand){
        var card = hand[i];
        console.log(card);
        if(card == "img/b.gif"){
          this.$data.hand[i] = "img/2c.gif";
          return;
        }
      }
    }
  }
});

function onTestClick() {
  // var lbl = document.getElementById("lbl");
  // var img1 = document.getElementById("img1");
  // img1.src = "img/2c.gif";
}
function setUp(){
  // console.log("Loading");
  // var img;
  // for(i = 1; i < 6; i++){
  //   img = document.getElementById("img" + i);
  //   img.src = "img/b.gif";
  // }
}
