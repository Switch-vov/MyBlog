// Empty JS for your own code to be here

var num = 0;
var num2 = 0;
function dropclick() {
	var clazz = $("drop").getAttribute("class");
	if ($("drop2") != null) {
		$("drop2").setAttribute("class", "dropdown");
	}
	if(clazz == "dropdown") {
		$("drop").setAttribute("class", "dropdown open");
	}
	if(clazz == "dropdown open") {
		$("drop").setAttribute("class", "dropdown");
	}
}

function dropclick2() {
	/* window.alert("move"); */
	var clazz = $("drop2").getAttribute("class");
	$("drop").setAttribute("class", "dropdown");
	if(clazz == "dropdown") {
		$("drop2").setAttribute("class", "dropdown open");
	}
	if(clazz == "dropdown open") {
		$("drop2").setAttribute("class", "dropdown");
	}
}

function $(obj){
	return document.getElementById(obj);
}