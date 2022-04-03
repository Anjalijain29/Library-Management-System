<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<c:choose>
	<c:when test="${totalPagesMyBooks !=0 }">



		<h3 class="text-primary text-center mt-3 mb-3">My Books</h3>
		<table class="table table-striped table-hover">
			<thead class="thd">


				<tr>
				<th scope="col">Issue Id<a
						href="${pageContext.request.contextPath}/userDB/${currentPage}?sortField=issue_id&sortDirection=${revSortDirection}&pane=active"><i
							class="fa fa-sort"></i></a></th>
					<th scope="col">Book Title<a
						href="${pageContext.request.contextPath}/userDB/${currentPage}?sortField=bookTitle&sortDirection=${revSortDirection}&pane=active"><i
							class="fa fa-sort"></i></a></th>
					<th scope="col">Issue Date<a
						href="${pageContext.request.contextPath}/userDB/${currentPage}?sortField=issue_date&sortDirection=${revSortDirection}&pane=active"><i
							class="fa fa-sort"></i></a></th>
					<th scope="col">Due Date<a
						href="${pageContext.request.contextPath}/userDB/${currentPage}?sortField=return_date&sortDirection=${revSortDirection}&pane=active"><i
							class="fa fa-sort"></i></a></th>
					<th scope="col">Fine<a
						href="${pageContext.request.contextPath}/userDB/${currentPage}?sortField=fine&sortDirection=${revSortDirection}&pane=active"><i
							class="fa fa-sort"></i></a></th>
					<th scope="col">Action</th>
				</tr>
			</thead>

			<tbody>

				<c:forEach var="history" items="${issueHistory}">
					<tr>
							<td>${history.getIssueId() }</td>
						<td>${history.getBookTitle() }</td>
						<td><fmt:formatDate type="date"
								value="${history.getIssueDate() }" /></td>
						<td><fmt:formatDate type="date"
								value="${history.getReturnDate() }" /></td>
						<td>₹ ${history.calculateFine()}</td>
						<td><form:form
								action="${pageContext.request.contextPath}/requestReturn"
								modelAttribute="return" method="post">
								<form:hidden path="requestId" value="${history.getRequestId()}" />
								<form:hidden path="issueId" value="${history.getIssueId()}" />
								<form:hidden path="member" value="${history.getMemberId()}" />
								<form:hidden path="book" value="${history.getBookId()}" />
								<button type="submit" class="btn btn btn-success">Request
									Return</button>
							</form:form></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>




		<!--  PAGINATION -->
		<c:if test="${totalPagesMyBooks>=2 }">
			<nav class="pagination-outer" aria-label="Page navigation">
				<ul class="pagination">
					<c:if test="${currentPage != 1}">
						<li class="page-item"><a
							href="${pageContext.request.contextPath}/userDB/${pageNo-1}?sortField=${sortField}&sortDirection=${sortDirection}&pane=active"
							class="page-link" aria-label="Previous"> <span
								aria-hidden="true">« </span>
						</a></li>
					</c:if>
					<c:forEach begin="${beg}" end="${end}" var="pageNo">
						<c:choose>
							<c:when test="${pageNo != currentPage}">
								<li class="page-item"><a class="page-link"
									href="${pageContext.request.contextPath}/userDB/${pageNo}?sortField=${sortField}&sortDirection=${sortDirection}&pane=active">${pageNo}</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item active"><a class="page-link"
									href="${pageContext.request.contextPath}/userDB/${pageNo}?sortField=${sortField}&sortDirection=${sortDirection}&pane=active">${pageNo}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${currentPage != totalPagesMyBooks}">
						<li class="page-item"><a
							href="${pageContext.request.contextPath}/userDB/${pageNo+1}?sortField=${sortField}&sortDirection=${sortDirection}&pane=active"
							class="page-link" aria-label="Next"> <span aria-hidden="true">
									»</span>
						</a></li>
					</c:if>
				</ul>
			</nav>
		</c:if>
		<!--  PAGINATION -->


	</c:when>
	<c:otherwise>
		<h3 class="text-center mt-3 mb-3 text-primary">My Books</h3>
		<table class="table table-striped table-hover mb-0">
			<thead class="thd">
				<tr>
				<th scope="col">Issue Id</th>
					<th scope="col">Book Title</th>
					<th scope="col">Issue Date</th>
					<th scope="col">Due Date</th>
					<th scope="col">Fine</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
		</table>

		<div class="col-lg-12 text-center m-0">
			<h6 class="bg-light py-2">No Data Exists</h6>
		</div>

	</c:otherwise>
</c:choose>