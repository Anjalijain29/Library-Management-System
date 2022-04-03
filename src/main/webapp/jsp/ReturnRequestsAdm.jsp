<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:choose>
				<c:when test="${lastPage !=0 }">
					<h3 class="text-center mt-3 mb-3 text-primary ">Return Requests</h3>
					<table class="table table-striped table-hover">
						<thead class="thd">
							<tr>
								<th scope="col">Request ID<a
									href="${pageContext.request.contextPath }/adminDB/${currentPage }?sortField=request_id&sortDirection=${revSortDirection}&pane=returnRequests"><i
										class="fa fa-sort"></i></a></th>
								<th scope="col">Member Name<a
									href="${pageContext.request.contextPath }/adminDB/${currentPage }?sortField=name&sortDirection=${revSortDirection}&pane=returnRequests"><i
										class="fa fa-sort"></i></a></th>
								<th scope="col">Book Title<a
									href="${pageContext.request.contextPath }/adminDB/${currentPage }?sortField=title&sortDirection=${revSortDirection}&pane=returnRequests"><i
										class="fa fa-sort"></i></a></th>
								<th scope="col">Issue Date<a
									href="${pageContext.request.contextPath }/adminDB/${currentPage }?sortField=issue_date&sortDirection=${revSortDirection}&pane=returnRequests"><i
										class="fa fa-sort"></i></a></th>
								<th scope="col">Fine<a
									href="${pageContext.request.contextPath }/adminDB/${currentPage }?sortField=fine&sortDirection=${revSortDirection}&pane=returnRequests"><i
										class="fa fa-sort"></i></a></th>
								<th scope="col">View Details</th>

							</tr>
						</thead>
						<tbody class="tbd">
							<c:forEach items="${returnRequests}" var="returnRequest">
								<tr>
									<td>${returnRequest.getRequestId()}</td>
									<td class="hover-text">${returnRequest.getName()}</td>
									<td class="hover-text">${returnRequest.getTitle()}</td>

									<td><fmt:formatDate type="date"
											value="${returnRequest.getIssueDate()}" /></td>
									<td>₹ ${returnRequest.calculateFine()}</td>
									<c:set var="id" value="#Rt${returnRequest.getRequestId()}"></c:set>
									<td><button type="button" class="btn btn btn-primary"
											data-bs-toggle="modal" data-bs-target="${id}">View
											Details</button></td>
								</tr>
							</c:forEach>
						</tbody>
						<c:forEach items="${returnRequests}" var="returnRequest">
							<div class="modal fade right"
								id="Rt${ returnRequest.getRequestId()}" tabindex="-1"
								role="dialog" data-backdrop="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header header">
											<h5 class="modal-title text-white" id="exampleModalLabel">Request
												ID : ${returnRequest.getRequestId()}</h5>
											<button type="button" class="btn-close"
												style="background-color: white;" data-bs-dismiss="modal"
												aria-label="Close"></button>
										</div>

										<div class="modal-body">
											<div class="row mt-0">
												<div class="col-12 ">
													<p>
														<b>Member ID : </b>${returnRequest.getMemberId()}</p>
												</div>
											</div>
											<div class="row mt-0">
												<div class="col-12 ">
													<p>
														<b>Member Name : </b>${returnRequest.getName()}
													</p>

												</div>
											</div>
											<div class="row mt-0">
												<div class="col-12 d-block">
													<p>
														<b>Book ID : </b>${returnRequest.getBookId()}</p>

												</div>
											</div>
											<div class="row mt-0">
												<div class="col-12">
													<p>
														<b>Book Title : </b>${returnRequest.getTitle()}</p>

												</div>
											</div>
											<div class="row mt-0">
												<div class="col-12">
													<p>
														<b>Issue ID : </b>${returnRequest.getIssueId()}</p>

												</div>
											</div>
											<div class="row mt-0">
												<div class="col-12">
													<p>
														<b>Issue Date : </b>
														<fmt:formatDate type="date"
															value="${returnRequest.getIssueDate()}" />
													</p>
												</div>
											</div>

											<div class="row mt-0">
												<div class="col-12">
													<p>
														<b>Due Date : </b><fmt:formatDate type="date"
											value="${returnRequest.getReturnDate()}" /> 

													</p>
												</div>
											</div>
											
											
											<div class="row mt-0">
												<div class="col-12">
													<p>
														<b>Request Date : </b>
														<fmt:formatDate type="date"
															value="${returnRequest.getRequestDate()}" />
													</p>
												</div>
											</div>

											<div class="row mt-0">
												<div class="col-12">
													<p>
												<b>Fine Amount : </b>₹ ${returnRequest.calculateFine()}</p>
												</div>
											</div>


										</div>



										<div class="modal-footer justify-content-center">
											<form:form
												action="${pageContext.request.contextPath}/acceptReturn"
												modelAttribute="acceptRT" method="post">
												<!-- have to add in HistoryTable -->
												<form:hidden path="requestId"
													value="${returnRequest.getRequestId()}" />
												<form:hidden path="issueId"
													value="${returnRequest.getIssueId()}" />
												<form:hidden path="member"
													value="${returnRequest.getMemberId()}" />
												<form:hidden path="book"
													value="${ returnRequest.getBookId()}" />
												<button type="submit" class="btn btn btn-success">Accept</button>
											</form:form>
											<!-- <button type="button"
												class="btn btn-outline-warning waves-effect mx-3">
												Reset Fine</button> -->
												
												
												<!-- For future scope -->
												
												
											<%-- <form:form
												action="${pageContext.request.contextPath}/rejectReturn"
												modelAttribute="rejectRT" method="post">
												<form:hidden path="requestId"
													value="${returnRequest.getRequestId()}" />
												<form:hidden path="issueId"
													value="${returnRequest.getIssueId()}" />
												<form:hidden path="member"
													value="${returnRequest.getMember().getUser().getUserId()}" />
												<form:hidden path="book"
													value="${ returnRequest.getBook().getBookId()}" />
												<button type="submit" class="btn btn btn-danger">Reject</button>
											</form:form> --%>
											
											
											<!-- For future scope -->
										</div>


									</div>
								</div>
							</div>
						</c:forEach>
					</table>
					<c:if test="${lastPage > 1 }">
						<nav class="pagination-outer" aria-label="Page navigation">
							<ul class="pagination">
								<c:if test="${currentPage != 1}">
									<li class="page-item"><a
										href="${pageContext.request.contextPath }/adminDB/${pageNo-1 }?sortField=${sortField}&sortDirection=${sortOrder}&pane=returnRequests"
										class="page-link" aria-label="Previous"> <span
											aria-hidden="true">« </span>
									</a></li>
								</c:if>
								<c:forEach begin="${beg}" end="${end}" var="pageNo">
									<c:choose>
										<c:when test="${pageNo != currentPage}">
											<li class="page-item"><a class="page-link"
												href="${pageContext.request.contextPath }/adminDB/${pageNo }?sortField=${sortField}&sortDirection=${sortOrder}&pane=returnRequests">${pageNo}</a></li>
										</c:when>
										<c:otherwise>
											<li class="page-item active"><a class="page-link"
												href="${pageContext.request.contextPath }/adminDB/${pageNo }?sortField=${sortField}&sortDirection=${sortOrder}&pane=returnRequests">${pageNo }</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:if test="${currentPage != lastPage}">
									<li class="page-item"><a
										href="${pageContext.request.contextPath }/adminDB/${pageNo+1 }?sortField=${sortField}&sortDirection=${sortOrder}&pane=returnRequests"
										class="page-link" aria-label="Next"> <span
											aria-hidden="true"> »</span>
									</a></li>
								</c:if>
							</ul>
						</nav>
					</c:if>
					</c:when>
					
					<c:otherwise>
							<h3 class="text-center mt-3 text-primary ">Return Request</h3>
					<table class="table table-striped table-hover mb-0">
						<thead class="thd">
							<tr>
								<th scope="col">Request ID
									</th>
								<th scope="col">Member ID</th>
								<th scope="col">Book ID</th>
								<th scope="col">Issue Date</th>
								<th scope="col">Fine</th>
								<th scope="col">View Details</th>
							</tr>

						</thead>
						
						</table>
						<div class="col-lg-12 text-center m-0">
						   <h6 class="bg-light py-2">No Data Exists</h6>
						</div>
						
					</c:otherwise>
					</c:choose>