<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>Login</title>
  <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css'>
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Muli'>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginregister.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.12.1/css/all.css" crossorigin="anonymous">
</head>
<body>

			<c:set var="session" value="${session}" ></c:set>
                        <c:choose>
                        	<c:when test= '${fn:contains(session, "admin") }'>
                        	<%response.sendRedirect(request.getContextPath()+"adminDB/1?sortField=issue_id&sortDirection=asc&pane=issueHistory");%>
                        	</c:when>
                        	<c:when test= "${not empty session }">
                        	<%response.sendRedirect(request.getContextPath()+"/userDB/1");%>
                        	</c:when>
                        </c:choose>
            <nav class="navbar align-content-center  navbar-expand-lg navbar-dark " id="nav">
                <div class="container">
                    <a class="navbar-brand" href="${pageContext.request.contextPath}">
                        <i class="fas fa-book-open" style="margin-right: 5px;"></i>


                        <b id="brand" style="font-size: 28px;">Library Management System</b>
                    </a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                        <span class="navbar-toggler-icon"></span>
                    </button>

                    <div class="collapse navbar-collapse justify-content-end" id="navbarNav">

                        <ul id="nav-list" class="navbar-nav justify-content-center navItem">
                            <li class="nav-item">
                                <a class="nav-link item mx-1" href="${pageContext.request.contextPath}">Home</a>
                            </li>
                            <li class="nav-item ">
                                <a class="nav-link item mx-1" href="${pageContext.request.contextPath}/catalogue/0">Books</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>


<!-- partial:index.partial.html -->
<div id="form-bg" class="pt-5">
  <h1 class="text-center">Log In</h1>
  
<div class="container">
                <div class="row">
                    <div class="col-md-5 mx-auto">
                        <div class="card card-body">
                        <c:if test="${not empty profileUpdated}">
                        	<h6 class="text-center text-success">Profile Updated Successfully</h6>
                        </c:if>
                        <c:if test="${not empty registered}">
                        	<h6 class="text-center text-success">User Registered Successfully</h6>
                        </c:if>
                
                                                    
                            <form:form id="submitForm" action="userLogin" method="post" modelAttribute="req">
                                <div class="form-group required">
                                <i class="fas fa-phone fa-1x mb-2"></i>
                                    <form:label path="phone">Phone No<sup style="color: red;">*</sup></form:label>
                                    <form:input class="form-control text-lowercase" path="phone" />
                                    <c:if test="${not empty notRegistered}">
										<span style="color:#D8000C">Phone no. not registered.</span>
									</c:if>
                                    <form:errors cssStyle="color:#D8000C" path="phone"></form:errors>
                                </div>                    
                              
                                <div class="form-group required">
                                <i class="fas fa-lock fa-1x mb-2"></i>
                                     <form:label path="password">Password<sup style="color: red;">*</sup></form:label>
                                    <form:password class="form-control text-lowercase" path="password" />
	                                <c:if test="${not empty password}">
										<span style="color:#D8000C">Invalid Password</span>
									</c:if>
                                    <form:errors cssStyle="color:#D8000C" path="password"></form:errors>
                                </div> 
                                <div class="form-group pt-1">
                                    <button class="btn btn-primary btn-block" type="submit">Log In</button>
                                </div>
                            </form:form>
                            <p class="small-xl pt-3 text-center">
                                <span class="text-muted">Not a member?</span>
                                <a href="register">Sign up</a>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
</div>
<!-- partial -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
  
</body>
</html>
</html>

									
                                    
                                    


