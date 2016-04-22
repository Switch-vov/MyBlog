// Empty JS for your own code to be here
function dropclick() {
	var clazz = document.getElementById("drop").getAttribute("class");
	if (document.getElementById("drop2") != null) {
		document.getElementById("drop2").setAttribute("class", "dropdown");
	}
	if(clazz == "dropdown") {
		document.getElementById("drop").setAttribute("class", "dropdown open");
	}
	if(clazz == "dropdown open") {
		document.getElementById("drop").setAttribute("class", "dropdown");
	}
}

function dropclick2() {
	/* window.alert("move"); */
	var clazz = document.getElementById("drop2").getAttribute("class");
	document.getElementById("drop").setAttribute("class", "dropdown");
	if(clazz == "dropdown") {
		document.getElementById("drop2").setAttribute("class", "dropdown open");
	}
	if(clazz == "dropdown open") {
		document.getElementById("drop2").setAttribute("class", "dropdown");
	}
}
