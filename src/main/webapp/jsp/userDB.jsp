<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>


<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/adminDB.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/profile.css">
<link href="${pageContext.request.contextPath}/css/modalForms.css"
	rel="stylesheet" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous" />
<link href="${pageContext.request.contextPath}/css/page.css"
	rel="stylesheet" />
<script src="https://kit.fontawesome.com/3480789ff2.js"
	crossorigin="anonymous"></script>
</head>


<c:if test="${not empty requestedToReturn }">
	<script>
		alert("Return request inintialted successfully");
	</script>
</c:if>
<c:if test="${not empty profileUpdated }">
	<script>
		alert("profile Updated Successfully");
	</script>
</c:if>

<body>
	
	<%@ include file="navbar.jsp" %> 

	<div class="main">
		
		<%@ include file="sidebar.jsp" %> 
		
		<div class="contentt" id="contentt">
			<c:choose>
				<c:when test="${not empty initial}">
					<%@ include file="MyBooks.jsp" %>  
				</c:when>





				<c:when test="${not empty rejected}">
					<%@ include file="RejectedRequests.jsp" %>  
				</c:when>







				<c:when test="${not empty MyHistory}">
					<%@ include file="IssuedBooks.jsp" %>  
				</c:when>






				<c:when test="${not empty ViewProfile}">
					<%@ include file="MyDetails.jsp" %>  

					<!--UPDATE  MODAL -->
				</c:when>

				<c:otherwise>
					<%@ include file="PendingRequests.jsp" %>  
				</c:otherwise>
			</c:choose>
			
		</div>


		<%@ include file="EditProfileModal.jsp" %>  

	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
	<script src="${pageContext.request.contextPath}/js/userDB.js"></script>
	<c:choose>
		<c:when test="${not empty hasErrors}">
			<script>
				document.getElementById("myForm").classList.remove("d-none");
				document.getElementById("myForm").classList.add("d-block")
				document.getElementById("sidebar").classList.add("blurred");
				document.getElementById("contentt").classList.add("blurred");
			</script>
		</c:when>
		<c:when test="${not empty password}">
			<script>
				document.getElementById("myForm").classList.remove("d-none");
				document.getElementById("myForm").classList.add("d-block");
				document.getElementById("sidebar").classList.add("blurred");
				document.getElementById("contentt").classList.add("blurred");
			</script>
		</c:when>

		<c:when test="${not empty saved}">
			<script>
				alert("Data Saved Successfully")
			</script>
		</c:when>
	</c:choose>
</body>
</html>
