function function1(){
  alert('Hello!');
  var elem1 = document.getElementById("d1");
  var elem2 = document.getElementById("d2");
  var elem3 = document.getElementById("b1");
  var elem4 = document.getElementById("b2");
  elem1.className = "div2";
  elem2.className = "div1";
  elem1.innerHTML = "Not this";
  elem2.innerHTML = "Now enter this button";
  elem3.value = "=)";
  elem4.value = "And me =)";
}
function function2(){
  alert('Bye-bye!');
  var elem1 = document.getElementById("d1");
  var elem2 = document.getElementById("d2");
  var elem3 = document.getElementById("b1");
  var elem4 = document.getElementById("b2");
  elem1.className = "div1";
  elem2.className = "div2";
  elem1.innerHTML = "Enter this button first";
  elem2.innerHTML = "Not this";
  elem3.value = "Click Me =)";
  elem4.value = "=)";
}