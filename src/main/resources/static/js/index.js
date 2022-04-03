var mybutton = document.getElementById("myBtn");
if (window.innerWidth <= 550) {
    document.getElementById("brand").innerText = "LMS"
}
window.addEventListener(("resize"), () => {
    if (window.innerWidth <= 550) {
        document.getElementById("brand").innerText = "LMS"
    } else {
        document.getElementById("brand").innerText = "Library Management System"

    }
})

if (window.innerWidth <= 472) {
    console.log(window.innerWidth);
    // document.getElementById("brand").innerText = "LMS"
}

// When the user scrolls down 20px from the top of the document, show the button
window.onscroll = function() { scrollFunction() };

function scrollFunction() {
    if (document.body.scrollTop > 30 || document.documentElement.scrollTop > 30) {
        mybutton.style.display = "block";
    } else {
        mybutton.style.display = "none";
    }
}

// When the user clicks on the button, scroll to the top of the document
function topFunction() {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}