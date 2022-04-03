<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<link href="${pageContext.request.contextPath}/css/modalForms.css"
	rel="stylesheet" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous" />
<script src="https://kit.fontawesome.com/3480789ff2.js"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<link href="${pageContext.request.contextPath}/css/page.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/srcbar.css">

</head>

<c:if test="${not empty acceptedIssueRequest }">

	<script>
		alert("Issue request accepted successfully!!!");
	</script>

</c:if>
<c:if test="${not empty rejectedIssueRequest }">

	<script>
		alert("Issue request rejected successfully!!!");
	</script>

</c:if>

<c:if test="${not empty acceptedReturnRequest }">

	<script>
		alert("Return request accepted successfully!!!");
	</script>

</c:if>

<c:if test="${not empty rejectedReturnRequest }">

	<script>
		alert("Return request rejected successfully!!!");
	</script>

</c:if>

<body>
	
	<%@ include file="navbarAdm.jsp" %> 

	<div class="main">
		
		<%@ include file="sidebarAdm.jsp" %> 
		
		
		<%@ include file="AddBookModal.jsp" %> 
		
		<div class="contentt" id="contentt">
			<c:choose>



				<c:when test="${not empty viewIssueRequests}">
					<%@ include file="IssueRequestsAdm.jsp" %>  
					
				</c:when>


				<c:when test="${not empty generateReport }">
					<%@ include file="ReportGen.jsp" %> 
				</c:when>




				<c:when test="${not empty viewReturnRequests or empty initial }">
					<%@ include file="ReturnRequestsAdm.jsp" %> 
					<% System.out.println("11111111111111111111111111111111119"); %>
				</c:when>




				<c:when test="${not empty viewIssueHistory or empty initial }">
					<%@ include file="IssueHistoryAdm.jsp" %> 
					<% System.out.println("333333333333333333333333333333333333319"); %>
				</c:when>
			</c:choose>


			
		</div>



	</div>
	<script src="${pageContext.request.contextPath}/js/adminDB.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
	<script src="${pageContext.request.contextPath}/js/issuehistory.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>

	<c:choose>
		<c:when test="${not empty exists}">
			<script>
				document.getElementById("myForm").classList.remove("d-none");
				document.getElementById("myForm").classList.add("d-block")
				document.getElementById("sidebar").classList.add("blurred");
				document.getElementById("contentt").classList.add("blurred");
			</script>
		</c:when>
		<c:when test="${not empty error}">
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
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
