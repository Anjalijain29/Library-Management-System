if (window.innerWidth <= 1200) {
    document.getElementById("brand").innerText = "LMS"
}
window.addEventListener(("resize"), () => {
    if (window.innerWidth <=1200) {
        document.getElementById("brand").innerText = "LMS"
    } else {
        document.getElementById("brand").innerText = "Library Management System"

    }
})
$('.view').on('click', function(){
	$('.count').hide();
	$('.add').hide();
	$('.update').show();
	$('.disable').show();
	$('.update').on('click', function(){
	$('.update').hide();
	$('.disable').hide();
	$('.count').show();
	$('.add').show();
})
})







