function openForm() {
	document.getElementById("myForm").classList.remove("d-none")
	document.getElementById("myForm").classList.add("d-block");
	document.getElementById("sidebar").classList.add("blurred");
	document.getElementById("contentt").classList.add("blurred");
}

function closeForm() {
	document.getElementById("myForm").classList.remove("d-block")
	document.getElementById("myForm").classList.add("d-none");
	document.querySelector(".sidebar").classList.remove("blurred");
	document.getElementById("contentt").classList.remove("blurred");
}
