<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:choose>
	<c:when test="${totalPagesIssueHistory !=0 }">

		<h3 class="text-primary text-center mt-3 mb-3">Issued Books</h3>


		<!--  TABLE -->
		<table class="table table-striped table-hover">
			<thead class="thd">
				<tr>
					<th scope="col">Issue ID<a
						href="${pageContext.request.contextPath}/userDB/${currentPage}?sortField=issue_id&sortDirection=${revSortDirection}&pane=history"><i
							class="fa fa-sort"></i></a></th>
					<th scope="col">Book Title<a
						href="${pageContext.request.contextPath}/userDB/${currentPage}?sortField=bookTitle&sortDirection=${revSortDirection}&pane=history"><i
							class="fa fa-sort"></i></a></th>
					<th scope="col">Issue Date<a
						href="${pageContext.request.contextPath}/userDB/${currentPage}?sortField=issue_date&sortDirection=${revSortDirection}&pane=history"><i
							class="fa fa-sort"></i></a></th>
					<th scope="col">Return Date<a
						href="${pageContext.request.contextPath}/userDB/${currentPage}?sortField=return_date&sortDirection=${revSortDirection}&pane=history"><i
							class="fa fa-sort"></i></a></th>
					<th scope="col">Fine<a
						href="${pageContext.request.contextPath}/userDB/${currentPage}?sortField=fine&sortDirection=${revSortDirection}&pane=history"><i
							class="fa fa-sort"></i></a></th>
				</tr>
			</thead>

			<tbody>

				<c:forEach var="History" items="${myHistory}">
					<tr>
						<td>${History.getIssueId() }</td>
						<td>${History.getBookTitle() }</td>
						<td><fmt:formatDate type="date"
								value="${History.getIssueDate() }" /></td>
						<td><fmt:formatDate type="date"
								value="${History.getReturnDate() }" /></td>

						<td> ₹ ${History.getFine() }</td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!--  TABLE -->

		<!--  PAGINATION -->
		<c:if test="${totalPagesIssueHistory>=2 }">
			<nav class="pagination-outer" aria-label="Page navigation">
				<ul class="pagination">
					<c:if test="${currentPage != 1}">
						<li class="page-item"><a
							href="${pageContext.request.contextPath}/userDB/${pageNo-1}?sortField=${sortField}&sortDirection=${sortOrder}&pane=history"
							class="page-link" aria-label="Previous"> <span
								aria-hidden="true">« </span>
						</a></li>
					</c:if>
					<c:forEach begin="${beg}" end="${end}" var="pageNo">
						<c:choose>
							<c:when test="${pageNo != currentPage}">
								<li class="page-item"><a class="page-link"
									href="${pageContext.request.contextPath}/userDB/${pageNo}?sortField=${sortField}&sortDirection=${sortOrder}&pane=history">${pageNo}</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item active"><a class="page-link"
									href="${pageContext.request.contextPath}/userDB/${pageNo}?sortField=${sortField}&sortDirection=${sortOrder}&pane=history">${pageNo}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${currentPage != totalPagesIssueHistory}">
						<li class="page-item"><a
							href="${pageContext.request.contextPath}/userDB/${pageNo+1}?sortField=${sortField}&sortDirection=${sortOrder}&pane=history"
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
		<h3 class="text-primary text-center mt-3 mb-3">Issued Books</h3>
		<table class="table table-striped table-hover mb-0">
			<thead class="thd">
				<tr>
					<th scope="col">Issue Id</th>
					<th scope="col">Book Id</th>
					<th scope="col">Issue Date</th>
					<th scope="col">Return Date</th>

					<th scope="col">Fine</th>
				</tr>
			</thead>
		</table>

		<div class="col-lg-12 text-center m-0">
			<h6 class="bg-light py-2">No Data Exists</h6>
		</div>

	</c:otherwise>
</c:choose>


