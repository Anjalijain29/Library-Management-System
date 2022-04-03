	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<c:choose>
				<c:when test="${lastPage !=0 }">
				
				
					<h3 class="text-center mt-3 mb-3 text-primary ">Issue Requests</h3>
					<div class="table-responsive-lg">
					<table class="table table-striped table-hover">
						<thead class="thd">
							<tr>
								<th scope="col">Request ID<a
									href="${pageContext.request.contextPath }/adminDB/${currentPage }?sortField=request_id&sortDirection=${revSortDirection}&pane=issueRequests"><i
										class="fa fa-sort"></i></a></th>
								<th scope="col">Member Name<a
									href="${pageContext.request.contextPath }/adminDB/${currentPage }?sortField=name&sortDirection=${revSortDirection}&pane=issueRequests"><i
										class="fa fa-sort"></i></a></th>
								<th scope="col">Book Title<a
									href="${pageContext.request.contextPath }/adminDB/${currentPage }?sortField=title&sortDirection=${revSortDirection}&pane=issueRequests"><i
										class="fa fa-sort"></i></a></th>

								<!--  <th scope="col">Username</th>-->
								<th scope="col">Request Date<a
									href="${pageContext.request.contextPath }/adminDB/${currentPage }?sortField=request_date&sortDirection=${revSortDirection}&pane=issueRequests"><i
										class="fa fa-sort"></i></a></th>
								<th scope="col">View Details</th>
							</tr>

						</thead>

						<tbody class="tbd">
							<c:forEach items="${issueRequests}" var="issueRequest">
								<tr>
									<td>${issueRequest.getRequestId()}</td>
									<td class="hover-text">${issueRequest.getName()}</td>
									<td class="hover-text">${issueRequest.getTitle()}</td>

									

									<td><fmt:formatDate type="date"
											value="${issueRequest.getRequestDate()}" /></td>
									<c:set var="id" value="#R${ issueRequest.getRequestId()}"></c:set>
									<td><button type="button" class="btn btn btn-primary"
											data-bs-toggle="modal" data-bs-target="${id }">View
											Details</button></td>
								</tr>
							</c:forEach>

						</tbody>



					</table>
					</div>
					<c:forEach var="issueRequest" items="${issueRequests }">
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
									<div class="col-12 ">
										<p>
											<b>Member ID : </b>${issueRequest.getMemberId()}</p>

									</div>
								</div>
								<div class="row mt-0">
									<div class="col-12 ">
										<p>
											<b>Member Name : </b>${issueRequest.getName()}</p>

									</div>
								</div>
								
								
								<div class="row mt-0">
									<div class="col-12 ">
										<p>
											<b>Member Pending Fine: </b>₹ ${issueRequest.getFine()}</p>

									</div>
								</div>
								
								<div class="row mt-0">
									<div class="col-12 d-block">
										<p>
											<b>Book ID : </b>${issueRequest.getBookId()}</p>

									</div>
								</div>
								<div class="row mt-0">
									<div class="col-12">
										<p>
											<b>Book Title : </b>${issueRequest.getTitle()}</p>

									</div>
								</div>

								<div class="row mt-0">
									<div class="col-12">

										<p>
											<b>Request Date : </b>
											<fmt:formatDate type="date"
												value="${issueRequest.getRequestDate()}" />
										</p>

									</div>
								</div>

							</div>


							<div class="modal-footer justify-content-center">

								<form:form
									action="${pageContext.request.contextPath}/acceptRequest"
									modelAttribute="accept" method="post">
									<form:hidden path="requestId"
										value="${issueRequest.getRequestId()}" />
									<form:hidden path="memberId"
										value="${issueRequest.getMemberId()}" />
									<form:hidden path="bookId"
										value="${ issueRequest.getBookId()}" />
									<td><button type="submit" class="btn btn btn-success">Accept</button></td>
								</form:form>
								<form:form
									action="${pageContext.request.contextPath}/rejectRequest"
									modelAttribute="reject" method="post">
									<form:hidden path="requestId"
										value="${issueRequest.getRequestId()}" />
									<form:hidden path="memberId"
										value="${issueRequest.getMemberId()}" />
									<form:hidden path="bookId"
										value="${ issueRequest.getBookId()}" />
									<td><button type="submit" class="btn btn btn-danger">Reject</button></td>
								</form:form>
							</div>
						</div>

					</div>
				</div>
			</c:forEach>


					<c:if test="${lastPage > 1 }">
						<nav class="pagination-outer" aria-label="Page navigation">
							<ul class="pagination">
								<c:if test="${currentPage != 1}">
									<li class="page-item"><a
										href="${pageContext.request.contextPath }/adminDB/${pageNo-1 }?sortField=${sortField}&sortDirection=${sortOrder}&pane=issueRequests"
										class="page-link" aria-label="Previous"> <span
											aria-hidden="true">« </span>
									</a></li>
								</c:if>
								<c:forEach begin="${beg}" end="${end}" var="pageNo">
									<c:choose>
										<c:when test="${pageNo != currentPage}">
											<li class="page-item"><a class="page-link"
												href="${pageContext.request.contextPath }/adminDB/${pageNo }?sortField=${sortField}&sortDirection=${sortOrder}&pane=issueRequests">${pageNo}</a></li>
										</c:when>
										<c:otherwise>
											<li class="page-item active"><a class="page-link"
												href="${pageContext.request.contextPath }/adminDB/${pageNo }?sortField=${sortField}&sortDirection=${sortOrder}&pane=issueRequests">${pageNo }</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:if test="${currentPage != lastPage}">
									<li class="page-item"><a
										href="${pageContext.request.contextPath }/adminDB/${pageNo+1 }?sortField=${sortField}&sortDirection=${sortOrder}&pane=issueRequests"
										class="page-link" aria-label="Next"> <span
											aria-hidden="true"> »</span>
									</a></li>
								</c:if>
							</ul>
						</nav>
					</c:if>
					
					</c:when>
					<c:otherwise>
							<h3 class="text-center mt-3 text-primary ">Issue Request</h3>
					<table class="table table-striped table-hover mb-0">
						<thead class="thd">
							<tr>
								<th scope="col">Request ID
									</th>
								<th scope="col">Member ID</th>
								<th scope="col">Book ID</th>
								<th scope="col">Request Date</th>
							
								<th scope="col">View Details</th>
							</tr>

						</thead>
					
						</table>
						<div class="col-lg-12 text-center m-0">
						   <h6 class="bg-light py-2">No Data Exists</h6>
						</div>
					</c:otherwise>
					</c:choose>