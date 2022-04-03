<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<c:choose>
	<c:when test="${totalPagesPending !=0 }">

		<h3 class="text-primary text-center mt-3 mb-3">Pending Requests</h3>
		<table class="table table-striped table-hover">
			<thead class="thd">
				<tr>
					<th scope="col">Request ID<a
						href="${pageContext.request.contextPath}/userDB/${currentPage}?sortField=request_id&sortDirection=${revSortDirection}&pane=pending"><i
							class="fa fa-sort"></i></a></th>
					<!-- <th scope="col">Book Title<a href="${pageContext.request.contextPath}/userDB/${currentPageIssueHistory}?sortField=title&sortDirection=${revSortDirection}&pane=pending" ><i class="fa fa-sort"></i></a></th> -->
					<th scope="col">Book Name<a
						href="${pageContext.request.contextPath}/userDB/${currentPage}?sortField=bookTitle&sortDirection=${revSortDirection}&pane=pending"><i
							class="fa fa-sort"></i></a></th>
					<th scope="col">Request Date<a
						href="${pageContext.request.contextPath}/userDB/${currentPage}?sortField=request_date&sortDirection=${revSortDirection}&pane=pending"><i
							class="fa fa-sort"></i></a></th>
					<th scope="col">Status<a
						href="${pageContext.request.contextPath}/userDB/${currentPage}?sortField=type&sortDirection=${revSortDirection}&pane=pending"><i
							class="fa fa-sort"></i></a></th>
					<th scope="col">View</th>
				</tr>

			</thead>

			<tbody>

				<c:forEach var="request" items="${issueRequests}">
					<tr>
						<c:set var="id" value="#R${request.getRequestId()}"></c:set>
						<td>${request.getRequestId() }</td>
						<td class="hover-text">${request.getBookTitle() }</td>

						<td><fmt:formatDate type="date"
								value="${request.getRequestDate() }" /></td>
						<td><c:set var="rrq" value="${request.getStatus()}"></c:set>
							<c:if test='${fn:contains(rrq, "R") }'>
								<c:set var="statss" value="Return Pending"></c:set>
							</c:if> <c:if test='${fn:contains(rrq, "I") }'>
								<c:set var="statss" value="Issue Pending"></c:set>
							</c:if>
							<p class="text-warning">${statss}</p></td>
						<td><button type="button" class="btn btn btn-primary"
								data-bs-toggle="modal" data-bs-target="${id}">Details</button></td>
					</tr>
				</c:forEach>
			</tbody>
			<c:forEach var="issueRequest" items="${issueRequests}">
				<c:set var="rid" value="R${issueRequest.getRequestId()}"></c:set>
				<div class="modal fade right" id="${rid }" tabindex="-1"
					role="dialog" data-backdrop="true">
					<div class="modal-dialog ">
						<div class="modal-content">
							<div class="modal-header header">
								<h5 class="modal-title text-white" id="exampleModalLabel">Request
									ID : ${issueRequest.getRequestId()}</h5>
								<button type="button" class="btn-close"
									style="background-color: white;" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>

							<div class="modal-body">

								<div class="row mt-0">
									<div class="col-12">
										<p>
											<b>Book Title : </b>${issueRequest.getBookTitle()}</p>


									</div>
								</div>

								<div class="row mt-0">
									<div class="col-12">
										<c:set var="rrq" value="${issueRequest.getStatus()}"></c:set>
										<c:if test='${fn:contains(rrq, "R") }'>
											<c:set var="statss" value="Return Pending"></c:set>
										</c:if>
										<c:if test='${fn:contains(rrq, "I") }'>
											<c:set var="statss" value="Issue Pending"></c:set>
										</c:if>
										<p>
											<b>Status : </b>${statss}
										</p>

									</div>
								</div>
								<c:set var="rrq" value="${issueRequest.getStatus()}"></c:set>
								<c:if test='${fn:contains(rrq, "I")}'>
									<div class="row mt-0">
										<div class="col-12">

											<p>
												<b>Issue Request Date : </b>
												<fmt:formatDate type="date"
													value="${issueRequest.getRequestDate()}" />
											</p>

										</div>
									</div>
								</c:if>

								<c:if test='${fn:contains(rrq, "R")}'>



									<div class="row mt-0">
										<div class="col-12">

											<p>
												<b>Return Request Date : </b>
												<fmt:formatDate type="date"
													value="${issueRequest.getRequestDate()}" />
											</p>

										</div>
									</div>


									<div class="row mt-0">
										<div class="col-12">
											<p>
												<b>Issue Date : </b>
												<fmt:formatDate type="date"
													value="${issueRequest.getIssueDate()}" />
											</p>

										</div>
									</div>


									<div class="row mt-0">
										<div class="col-12">
											<p>
												<b> Due Date : </b>
												<fmt:formatDate type="date"
													value="${issueRequest.getReturnDate()}" />
											</p>

										</div>
									</div>
									<div class="row mt-0">
										<div class="col-12">
											<p>
												<b> Fine : </b> ₹ ${issueRequest.getFine()}
											</p>

										</div>
									</div>
								</c:if>



							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</table>





		<!--  PAGINATION -->
		<c:if test="${totalPagesPending>=2 }">
			<nav class="pagination-outer" aria-label="Page navigation">
				<ul class="pagination">
					<c:if test="${currentPage != 1}">
						<li class="page-item"><a
							href="${pageContext.request.contextPath}/userDB/${pageNo-1}?sortField=${sortField}&sortDirection=${sortDirection}&pane=pending"
							class="page-link" aria-label="Previous"> <span
								aria-hidden="true">« </span>
						</a></li>
					</c:if>
					<c:forEach begin="${beg}" end="${end}" var="pageNo">
						<c:choose>
							<c:when test="${pageNo != currentPage}">
								<li class="page-item"><a class="page-link"
									href="${pageContext.request.contextPath}/userDB/${pageNo}?sortField=${sortField}&sortDirection=${sortDirection}&pane=pending">${pageNo}</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item active"><a class="page-link"
									href="${pageContext.request.contextPath}/userDB/${pageNo}?sortField=${sortField}&sortDirection=${sortDirection}&pane=pending">${pageNo}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${currentPage != totalPagesPending}">
						<li class="page-item"><a
							href="${pageContext.request.contextPath}/userDB/${pageNo+1}?sortField=${sortField}&sortDirection=${sortDirection}&pane=pending"
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

		<h3 class="text-center mt-3 mb-3 text-primary">Pending Requests</h3>
		<table class="table table-striped table-hover mb-0">
			<thead class="thd">
				<tr>
					<th scope="col">Issue Id</th>
					<th scope="col">Book Title</th>
					<th scope="col">Issue Date</th>
					<th scope="col">Return Date</th>

					<th scope="col">Fine</th>
					<th scope="col">View</th>
				</tr>
			</thead>
		</table>

		<div class="col-lg-12 text-center m-0">
			<h6 class="bg-light py-2">No Data Exists</h6>
		</div>

	</c:otherwise>
</c:choose>
