<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.12.1/css/all.css"
	crossorigin="anonymous">
<link href="${pageContext.request.contextPath}/css/catelogue.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/navbar.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/footer.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/page.css"
	rel="stylesheet" />
<title>Catalog</title>

</head>



<c:if test="${not empty countZero}">
	<script>
		alert("Cannot enable as count is 0.");
	</script>
</c:if>
<c:if test="${not empty borrowSuccess}">
	<script>
		alert("Book Borrowed Request Inititated");
	</script>
</c:if>

<c:if test="${not empty alreadyRequested}">
	<script>
		alert("You have already requested for this book. Wait for your request to get resolved");
	</script>
</c:if>

<c:if test="${not empty alreadyIssued}">
	<script>
		alert("You have already issued for this book.");
	</script>
</c:if>

<c:if test="${not empty isIssued}">
	<script>
		alert("You cannot disable this book. It is currently issued to members");
	</script>
</c:if>


<c:choose>
	<c:when test="${not empty invalidCount}">
		<script>
			alert("Count should be in between 1 and 999. Count not Updated");
		</script>
	</c:when>
	<c:when test="${not empty countUpdated}">
		<script>
			alert("Count updated successfully");
		</script>
	</c:when>
</c:choose>

<c:if test="${not empty bookToggled}">
	<c:choose>
		<c:when test='${fn:contains(bookToggled, "enabled") }'>
			<script>
				alert("Book Enabled successfully");
			</script>
		</c:when>
		<c:otherwise>
			<script>
				alert("Book Disabled successfully");
			</script>
		</c:otherwise>
	</c:choose>
</c:if>




<body>
	<div>
		<nav class="navbar navbar-expand-lg navbar-dark" id="nav">
			<div class="container">
				<a class="navbar-brand" href="${pageContext.request.contextPath}">
					<i class="fa fa-book-open" style="margin-right: 5px;"></i> <b
					id="brand">Library Management System</b>
				</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarNav">
					<span class="navbar-toggler-icon text-success"></span>
				</button>
				<div class="collapse navbar-collapse justify-content-end"
					id="navbarNav">
					<ul id="nav-list"
						class="navbar-nav justify-content-center p-2 navItem">
						<li class="nav-item"><a class="nav-link item"
							href="${pageContext.request.contextPath}">Home</a></li>
						
						<c:set var="session" value="${session}"></c:set>
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
								<li class="nav-item"><a class="nav-link item"
									href="${pageContext.request.contextPath }/login">Login</a></li>
							</c:otherwise>
						</c:choose>

					</ul>
				</div>
			</div>
		</nav>
	</div>
	<div>
		<h2
			style="font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;"
			class="text-center mt-4 tag">Book Catalog</h2>
	</div>

	<div
		class="search-sort-container container d-flex align-items-center justify-content-center ali">
		<div>

			<form action="${pageContext.request.contextPath }/catalogue/0" class="d-flex p-3 align-content-center">
				<div  class="pgntn">
					<div class="tttb">
				
					<div class="search-box d-flex mr">
						<h5 class="mr">Search By</h5>
							
						<select class="mr" id="searchCriteria" name = "searchCriteria">
						<c:set var="val" value="${searchCriteria }"></c:set>
						<c:choose>
					
						<c:when test='${fn:contains(val, "title") }'>
				
						
						<option selected="selected" value="title">Book-Title</option>
						
						</c:when>
						<c:otherwise>
				
							<option value="title">Book-Title</option>
							
						</c:otherwise>
						
						</c:choose>
						
						
						<c:choose>
						<c:when test='${fn:contains(val, "author") }'>
						<option selected="selected" value="author">Author</option>
						</c:when>
						<c:otherwise>
							<option value="author">Author</option>
						</c:otherwise>
						
						</c:choose>
						
						<c:choose>
						<c:when test='${fn:contains(val, "category") }'>
						<option selected="selected" value="category">Category</option>
						</c:when>
						<c:otherwise>
							<option value="category">Category</option>
						</c:otherwise>
						
						</c:choose>
						
							<c:choose>
						<c:when test='${fn:contains(val, "print_year") }'>
						<option selected="selected" value="print_year">Print Year</option>
						</c:when>
						<c:otherwise>
							<option value="print_year">Print Year</option>
						</c:otherwise>
						
						</c:choose>
				
						</select>
						<input name="searchField" class="border-primary rounded"
							type="text" placeholder="search..." value="${searchField}"/>
							<button
					style="background: linear-gradient(to right, #4e4376, #2b5876); color: #fff;"
					class="p2 ml" type="submit">
					<i class="fa fa-search" aria-hidden="true"></i>
				</button>
					</div></div>
					<div class="space"></div>
					<div class="tttb">
							<div class="sort-box d-flex mr">
								<h5 class="mr">Sort By</h5>
								<select class="mr" name="sortField">
							
						<c:set var="sortVal" value="${sortField}"></c:set>
						<c:choose>
					
						<c:when test='${fn:contains(sortVal, "title") }'>
				
						
						<option selected="selected" value="title">Book-Title</option>
						
						</c:when>
						<c:otherwise>
				
							<option value="title">Book-Title</option>
							
						</c:otherwise>
						
						</c:choose>
						
						<c:choose>
						<c:when test='${fn:contains(sortVal, "author") }'>
						<option selected="selected" value="author">Author</option>
						</c:when>
						<c:otherwise>
							<option value="author">Author</option>
						</c:otherwise>
						
						</c:choose>
						
						<c:choose>
						<c:when test='${fn:contains(sortVal, "category") }'>
						<option selected="selected" value="category">Category</option>
						</c:when>
						<c:otherwise>
							<option value="category">Category</option>
						</c:otherwise>
						
						</c:choose>
						
							<c:choose>
						<c:when test='${fn:contains(sortVal, "printYear") }'>
						<option selected="selected" value="printYear">Print Year</option>
						</c:when>
						<c:otherwise>
							<option value="printYear">Print Year</option>
						</c:otherwise>
						
						</c:choose>
				
								</select> 
								<select name="sortOrder">
								<c:set var="sortOr" value="${sortOrder}"></c:set>
								<c:choose>
						<c:when test='${fn:contains(sortOr, "asc") }'>
						<option selected="selected" value="asc">Ascending</option>
						</c:when>
						<c:otherwise>
							<option value="asc">Ascending</option>
						</c:otherwise>
						
						</c:choose>
						
							<c:choose>
						<c:when test='${fn:contains(sortOr, "desc") }'>
						<option selected="selected" value="desc">Descending</option>
						</c:when>
						<c:otherwise>
								<option value="desc">Descending</option>
						</c:otherwise>
						
						</c:choose>
								
					
								</select>
								<button
							style="background: linear-gradient(to right, #4e4376, #2b5876); color: #fff;"
							class="p2 ml" type="submit">
							<i class="fas fa-random"></i>
						</button>
							</div>
					</div>
				</div>
				
			</form>
		</div>
	</div>


	<div class="container mt-5 mb-5">
	
		<c:choose>
		<c:when test="${lastPageNo !=0 }">

		<div class="container mb-2">
	
		

			<div class="card-deck g-3">
				<div class="row card-row justify-content-center">

					<!-- dynamically rendering Book cards    -->



					<c:forEach items="${books}" var="book">
						<div class="card col-xl-2 col-lg-2 col-md-3 col-sm-5 col-xs-4 mx-2">
							<div class="card-upper">
								<div class="card-img-div">
									<img
										src="${pageContext.request.contextPath }/assets/img/logo.png"
										class="img-fluid mx-auto mb-auto mt-auto">
								</div>

								<div class="card-body text-center">

									<p class="card-title" style="font-weight: bold;">${book.getTitle()}</p>
									<p class="card-text mb-0">${book.getAuthor()} </p>
							
									<p class="card-text pb-1">${book.getCategory()} </p>
								</div>
							</div>
							

							<c:set var="id" value="#B${book.getBookId()}"></c:set>
							<div class="card-view-btn-wrapper">
								<button type="button" class="view-btn btn btn-sm view"
									data-bs-toggle="modal" data-bs-target="${id}">View</button>

							</div>
						</div>
					</c:forEach>




					<!-- dynamically rendering Book cards    -->
				</div>
			</div>
		</div>



		<!-- dynamically rendering Modals   -->



		<c:forEach items="${books}" var="book">
			<c:set var="bid" value="B${book.getBookId()}"></c:set>

			<div class="modal fade right" id="${bid}" tabindex="-1" role="dialog"
				data-backdrop="true">
				<div class="modal-dialog ">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title text-white" id="exampleModalLabel">Book
								Details</h5>
							<button type="button" class="btn-close" style="background-color: white;" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<div class="row">
								<div class="col-4 d-flex align-items-center justify-content-center">
									<div class="position-relative">
									<p class="text-center">

										<i class="fas fa-book fa-4x " style="color: #1e6591;"></i>	
									</p>
									<h4 class="position-absolute top-90 bottom-20 start-10 "><span class="badge text-dark p-0 text-center">
									Count : ${book.getCount()}</span></h6>
									</div>
								
								</div>

								<div class="col-8">
						
									<div class="row mt-0">
									<div class="col-12">
										<ul class="p-0" style="list-style: none;">
										<li><b>Title : </b>${book.getTitle()}</li>
										</ul>
										</div>
										</div>
										<div class="row mt-0">
									<div class="col-12">
										<ul class="p-0" style="list-style: none;">
										<li><b>Author : </b>${book.getAuthor()}</li>
										</ul>
										</div>
										</div>
											<div class="row mt-0">
										<div class="col-12">
										<ul class="p-0" style="list-style: none;">
										<li><b>Category : </b>${book.getCategory()}</li>
										</ul>
										</div>
										<div class="col-12">
										<ul class="p-0" style="list-style: none;">
										<li><b>Publisher : </b>${book.getPublisher().getPubName()}</li>
										</ul>
										</div>
										</div>
										<div class="row mt-0">
										<div class="col-12">
										<ul class="p-0" style="list-style: none;">
										<li><b>Print Year : </b>${book.getPrintYear()}</li>
										</ul>
										</div>
										</div>
						
								
									
										
									
								</div>
							</div>
						</div>

						<div class="modal-footer justify-content-center">
							<c:choose>
								<c:when test='${fn:contains(session, "admin") }'>
									<button type="button"
										class="btn btn btn-warning waves-effect mx-3 update">Update
										Count</button>
	
												<form:form action="${pageContext.request.contextPath}/updateBookCount" method="post"
										modelAttribute="updateBook">
										<form:hidden path="bookId" value="${book.getBookId()}" />
										
										<form:input type="number" path="count" value="${book.getCount() }"
											class="count text-center" />
										<button type="submit"
											class="btn btn btn-success waves-effect mx-3 add">Update</button>
									</form:form>
										
							
									

									<form:form modelAttribute="enableDisable"
										action="${pageContext.request.contextPath }/enableDisable">
										<form:hidden path="bookId" value="${book.getBookId()}" />
										<c:choose>
											<c:when test='${fn:contains(book.getStatus(), "E") }'>
												<button type="submit"
													class="btn btn btn-danger mx-3 disable">Disable</button>
											</c:when>
											<c:otherwise>
												<button type="submit"
													class="btn btn btn-success mx-3 disable">Enable</button>
											</c:otherwise>
										</c:choose>
									</form:form>

								</c:when>

								<c:otherwise>
									<form:form modelAttribute="borrow" action="${pageContext.request.contextPath }/borrow">
										<form:hidden path="bookId" value="${book.getBookId()}" />
										<c:choose>
											<c:when test='${fn:contains(book.getStatus(), "E") }'>
												<button type="submit"
													class="btn btn btn-success waves-effect mx-3">Borrow</button>
											</c:when>
											<c:otherwise>
												<button type="button"
													class="btn waves-effect mx-3 disabled">Borrow</button>
											</c:otherwise>
										</c:choose>
									</form:form>
								</c:otherwise>
							</c:choose>
						</div>
					</div>

				</div>
			</div>
		</c:forEach>

<c:set var="i" value="${pageNumber}"></c:set>
	<c:if test="${lastPageNo > 1}">
		<nav class="pagination-outer" aria-label="Page navigation">
			<ul class="pagination">
			
			<c:if test="${pageNumber != 0}">
			<li class="page-item">
					<a href="${pageContext.request.contextPath}/catalogue/${pageNumber-1}?searchCriteria=${searchCriteria }&searchField=${searchField }&sortField=${sortField }&sortOrder=${sortOrder}" class="page-link" aria-label="Previous">
	                    <span aria-hidden="true"> « </span>
	                </a>
	                </li>
				</c:if>
		
				<c:forEach var="page" begin="${beg}" end="${end}">
				<!-- <c:out value="${lastPageNo}"></c:out>  -->
				<%-- <c:out value="${pageNumber }"></c:out> --%>
				<c:choose>

						<c:when test="${pageNumber==page-1}">

							<li class="page-item active"><a class="page-link"
						href="${pageContext.request.contextPath}/catalogue/${page-1}?searchCriteria=${searchCriteria }&searchField=${searchField }&sortField=${sortField }&sortOrder=${sortOrder}">${page}</a>
					</li>
	
						</c:when>
						
						<c:otherwise>
							<li class="page-item"><a class="page-link"
						href="${pageContext.request.contextPath}/catalogue/${page-1}?searchCriteria=${searchCriteria }&searchField=${searchField }&sortField=${sortField }&sortOrder=${sortOrder}">${page}</a>
					</li>
						</c:otherwise>
						
					</c:choose>
					
					
				</c:forEach>
		
				
				
				<c:if test="${pageNumber +1 < lastPageNo}">
					
				<li class="page-item">
                <a href="${pageContext.request.contextPath}/catalogue/${pageNumber+1}?searchCriteria=${searchCriteria }&searchField=${searchField }&sortField=${sortField }&sortOrder=${sortOrder}" class="page-link" aria-label="Next">
                    <span aria-hidden="true"> » </span>
                </a>
            </li>
            
				</c:if>
			
            
            
     

			</ul>
		</nav>
		</c:if>
		</c:when>
		
		<c:otherwise>
		<div class="container">
			<h4 class="text-center text-warning">Sorry! No such book exists</h4>
		</div>
		</c:otherwise>
		
		</c:choose>
	</div>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<script
		src="${pageContext.request.contextPath}/js/catelogue.js"></script>

</body>


</html>




