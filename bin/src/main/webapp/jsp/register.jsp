<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Register</title>
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css'>
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Muli'>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.12.1/css/all.css" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginregister.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar.css">
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


	<div id="form-bg" class="pt-2">
		<h1 class="text-center">Register</h1>

		<div class="container">
			<div class="row">
				<div class="col-md-5 mx-auto">
					<div class="card card-body">

						<form:form id="submitForm" action="addUser" method="post"
							modelAttribute="req">
							<div class="form-group required">
							<i class="fas fa-user fa-1x mb-2"></i>
								<form:label path="firstName">First name<sup style="color: red;">*</sup></form:label>
								<form:input class="form-control text-lowercase"
									required="" path="firstName" value="" />
								<form:errors cssStyle="color:#D8000C" path="firstName"></form:errors>
							</div>
							<div class="form-group required">
							<i class="fas fa-user fa-1x mb-2"></i>
								<form:label path="lastName">Last Name<sup style="color: red;">*</sup></form:label>
								<form:input class="form-control text-lowercase"
									path="lastName" value="" />
								<form:errors cssStyle="color:#D8000C" path="lastName"></form:errors>
							</div>
							<div class="form-group required">
							<i class="fas fa-phone fa-1x mb-2"></i>
								<form:label path="phone">Phone<sup style="color: red;">*</sup></form:label>
								<form:input class="form-control text-lowercase"
									path="phone" value="" />
								<form:errors cssStyle="color:#D8000C" path="phone"></form:errors>
								<c:if test="${not empty phone}">
								<span style="color:#D8000C">Phone no already registered.</span>
								</c:if>
							</div>
							<div class="form-group required">
							<i class="fas fa-lock fa-1x mb-2"></i>
								<form:label path="password">Password<sup style="color: red;">*</sup></form:label>
								<form:password class="form-control text-lowercase" path="password" value="" />
								<form:errors cssStyle="color:#D8000C" path="password"></form:errors>
							</div>
							<div class="form-group required">
							<i class="fas fa-lock fa-1x mb-2"></i>
								<form:label path="confirmPassword">Confirm Password<sup style="color: red;">*</sup></form:label>
								<form:password class="form-control text-lowercase"
									path="confirmPassword" value="" />
								<c:if test="${not empty password }">
								<span style="color:#D8000C">Passwords not matching</span>
								</c:if>
							</div>

							<div class="form-group pt-1">
								<button class="btn btn-primary btn-block" type="submit">Register</button>
							</div>
						</form:form>
						<p class="small-xl pt-3 text-center">
							<span class="text-muted">Already a member?</span> <a href="login">Sign
								in</a>
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







