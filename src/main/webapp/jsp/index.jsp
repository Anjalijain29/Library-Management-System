<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>


<head>
    <title>Home</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/3480789ff2.js" crossorigin="anonymous"></script>
    <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/css/navbar.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/css/footer.css" rel="stylesheet" />
</head>

<body>

    <div class="container-fluid-wrapper">
        <div class="container-fluid first">

            <!-- NAVBAR -->

            <nav class="navbar align-content-center  navbar-expand-lg navbar-dark " id="nav">
                <div class="container">
                    <a class="navbar-brand" href="${pageContext.request.contextPath }">
                        <i class="fas fa-book-open" style="margin-right: 5px;"></i>
                        <b id="brand" style="font-size: 28px;">Library Management System</b>
                    </a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                        <span class="navbar-toggler-icon"></span>
                    </button>

                    <div class="collapse navbar-collapse justify-content-end" id="navbarNav">

                        <ul id="nav-list" class="navbar-nav justify-content-center navItem">
                            <!-- <li class="nav-item">
                                <a class="nav-link item mx-1" href="#">Home</a>
                            </li>-->
                            <li class="nav-item ">
                                <a class="nav-link item mx-1" href="#banner">About</a>
                            </li>
                            <li class="nav-item ">
                                <a class="nav-link item mx-1" href="#features">Features</a>
                            </li>
                            <li class="nav-item ">
                                <a class="nav-link item mx-1" href="${pageContext.request.contextPath}/catalogue/0">Books</a>
                            </li>
                            
                        <c:set var="session" value='${session}'></c:set>
                        
                        
                        <c:choose>
							<c:when test='${fn:contains(session, "admin") }'>
								<c:set var="url" value="${pageContext.request.contextPath }/adminDB/1?sortField=book_id&sortDirection=asc&pane=issueHistory"></c:set>
							</c:when>
							<c:otherwise>
								<c:set var="url" value="${pageContext.request.contextPath }/userDB/1"></c:set>
							</c:otherwise>
						</c:choose>
                        
                        <c:choose>
							<c:when test='${not empty session}'>
								<li class="nav-item"><a class="nav-link item" href="${url }">
									Dashboard </a></li>
								
								<li class="nav-item"><a class="nav-link item"
									href="${pageContext.request.contextPath }/logout">Logout</a></li>
							</c:when>
							<c:otherwise>
								 <li class="nav-item ">
                                <a class="nav-link item mx-1" href="${pageContext.request.contextPath}/register">Register</a>
                            </li>
                            <li class="nav-item ">
                                <a class="nav-link item mx-1" href="${pageContext.request.contextPath}/login">Login</a>
                            </li>
							</c:otherwise>
						</c:choose>
                            
                            
                           
                        </ul>
                    </div>
                </div>
            </nav>

            <!-- NAVBAR -->

            <div class="welcome-div-wrapper">
                <div class="container welcome-div">
                    <div class="row">
                        <div class="col-lg-10" id="nav-body-inner">
                            <h1 class="welcome-tag">Welcome To The Library Management System</h1>
                            <c:if test='${empty session}'>
                            	<button type="button" onclick="window.location='${pageContext.request.contextPath}/register'" class="btn btn-dark btn-lg Download-button"><i class="fa fa-sign-in px-2" aria-hidden="true"></i> Register</button>
                            	<button type="button" onclick="window.location='${pageContext.request.contextPath}/login'" class="btn btn-outline-light btn-lg Download-button"><i class="fa fa-sign-in px-2" aria-hidden="true"></i>Login</button>
                        	</c:if>
                        </div>
                        <div class="col-lg-2">
                            <!-- <img class="title-img" src="assets/img/library2.jpg" alt="iphone-mockup"> -->
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
    
    <div class="banner-area banner-2" id="banner">
        <div class="d-table w-100">
            <div class="d-table-cell">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-8 m-auto text-center col-sm-12
                                        col-md-12">
                            <div class="banner-content content-padding">
                                <h3 class="subtitle">A Unique Smart Library</h3>
                                <h1 class="banner-title">Read what you like, explore the vast knowledge </h1>
                                <p class="banner-tag">A place where you find a numerous category to choose from and feel the power the books contain and explore new realities with ease.
                                </p>

                                <a href="${pageContext.request.contextPath}/catalogue/0" class="btn btn-white
                                                btn-circled explore-btn">Explore Books</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- SERVICES -->

    <!-- Features -->


    <div class="flex features">
        <section id="features" class="gb-3">
            <h2 class="d-flex justify-content-center pt-5 feature-tag">Features</h2>
            <div class="row d-flex justify-content-center mt-0 pb-5 feature-div">
                <div class="col-lg-3 col-md-5 col-sm-10 feature-box ">
                    <a href="${pageContext.request.contextPath}/register">
                        <i class="icon fa fa-user fa-2x"></i>
                        <h3>Be a Memeber</h3>
                        <p>Follow two simple steps to join as a member</p>
                    </a>
                </div>


                <div class="col-lg-3 col-md-5 col-sm-10 feature-box">
                    <a href="${pageContext.request.contextPath}/catalogue/0">
                        <i class="icon fa fa-search fa-2x"></i>
                        <h3>Search A Book</h3>
                        <p>Find the book you are looking for</p>
                    </a>
                </div>

                <div class="col-lg-3 col-md-5 col-sm-10 feature-box">
                    <a href="${url}">
                        <i class="icon fa fa-book fa-2x"></i>
                        <h3>Supereasy Tracking</h3>
                        <p>Keep track of all your issues and returns</p>
                    </a>
                </div>

                <div class="col-lg-3 col-md-5 col-sm-10 feature-box">
                    <a href="${url}">
                        <i class="icon fa fa-certificate fa-2x"></i>
                        <h3>Convenient Issue</h3>
                        <p>Issue books in no time with ease</p>
                    </a>
                </div>

                <div class="col-lg-3 col-md-5 col-sm-10 feature-box">
                    <a href="${url}">
                        <i class="icon fa fa-bar-chart fa-2x"></i>
                        <h3>Check Statistics</h3>
                        <p>Check your history & details</p>
                    </a>
                </div>

                <div class="col-lg-3 col-md-5 col-sm-10 feature-box">
                    <a href="${url}">
                        <i class="icon fa fa-bullseye fa-2x"></i>
                        <h3>Easy Return</h3>
                        <p>Return a book with one click</p>
                    </a>
                </div>


            </div>
        </section>

    </div>

    <!-- FEATURES -->


    <!-- overlay button -->
    <button onclick="topFunction()" id="myBtn" title="Go to top"><i class="fa fa-arrow-up arrow" aria-hidden="true"></i></button>
    <!-- overlay button -->

    <div class="ftr">

    </div>

</body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/index.js"></script>
<script src="${pageContext.request.contextPath}/js/navbar.js"></script>
<script src="${pageContext.request.contextPath}/js/footer.js"></script>

</html>