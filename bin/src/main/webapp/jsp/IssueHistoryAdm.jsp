<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:choose>
	<c:when test="${lastPage !=0 }">
		<h3 class="text-center mt-3 mb-3 text-primary ">History</h3>
		<div class="table-responsive-lg">
			<table class="table table-striped table-hover">
				<thead class="thd">
					<tr>
						<th scope="col">Issue ID<a
							href="${pageContext.request.contextPath }/adminDB/${currentPage }?sortField=issue_id&sortDirection=${revSortDirection}&pane=issueHistory"><i
								class="fa fa-sort"></i></a></th>
						<th scope="col">Member Name<a
							href="${pageContext.request.contextPath }/adminDB/${currentPage }?sortField=name&sortDirection=${revSortDirection}&pane=issueHistory"><i
								class="fa fa-sort"></i></a></th>
						<th scope="col">Book Title<a
							href="${pageContext.request.contextPath }/adminDB/${currentPage }?sortField=title&sortDirection=${revSortDirection}&pane=issueHistory"><i
								class="fa fa-sort"></i></a></th>
						<!--  <th scope="col">Request Date<a
									href="${pageContext.request.contextPath }/adminDB/${currentPage }?sortField=requestDate&sortDirection=${revSortDirection}&pane=issueHistory"><i
										class="fa fa-sort"></i></a></th> -->
						<th scope="col">Issue Date<a
							href="${pageContext.request.contextPath }/adminDB/${currentPage }?sortField=issue_date&sortDirection=${revSortDirection}&pane=issueHistory"><i
								class="fa fa-sort"></i></a></th>
						<th scope="col">Return/Due Date<a
							href="${pageContext.request.contextPath }/adminDB/${currentPage }?sortField=return_date&sortDirection=${revSortDirection}&pane=issueHistory"><i
								class="fa fa-sort"></i></a></th>
						<th scope="col">Status<a
							href="${pageContext.request.contextPath }/adminDB/${currentPage }?sortField=status&sortDirection=${revSortDirection}&pane=issueHistory"><i
								class="fa fa-sort"></i></a></th>
						<th scope="col">Fine<a
							href="${pageContext.request.contextPath }/adminDB/${currentPage }?sortField=fine&sortDirection=${revSortDirection}&pane=issueHistory"><i
								class="fa fa-sort"></i></a></th>
						<th scope="col">View</th>
					</tr>

				</thead>

				<tbody class="tbd">
					<c:forEach items="${issueHistory}" var="issueHistory">
						<tr>
							<c:set var="id" value="#ih${issueHistory.getRequestId()}"></c:set>
							<td>${issueHistory.getIssueId()}</td>
							<td class="hover-text">${issueHistory.getName()}</td>

							<td class="hover-text">${issueHistory.getTitle()}</td>

							<!-- <td><fmt:formatDate type="date"
											value="${issueHistory.getRequestDate()}" /></td> -->

							<c:set var="rjq" value="${issueHistory.getStatus()}"></c:set>

							<td><c:if test='${not fn:contains(rjq, "R") }'>
									<fmt:formatDate type="date"
										value="${issueHistory.getIssueDate()}" />
								</c:if> <c:if test='${fn:contains(rjq, "R") }'>
											NA
											</c:if></td>
							<td><c:if test='${not fn:contains(rjq, "R") }'>
									<fmt:formatDate type="date"
										value="${issueHistory.getReturnDate()}" />
								</c:if> <c:if test='${fn:contains(rjq, "R") }'>
											NA
											</c:if></td>


							<c:if test='${fn:contains(rjq, "A") }'>
								<c:set var="text" value="text-success"></c:set>
								<c:set var="stats" value="Book Issued"></c:set>
							</c:if>
							<c:if test='${fn:contains(rjq, "C") }'>
								<c:set var="text" value="text-success"></c:set>
								<c:set var="stats" value="Book Returned"></c:set>
							</c:if>
							<c:if test='${fn:contains(rjq, "R") }'>
								<c:set var="text" value="text-danger"></c:set>
								<c:set var="stats" value="Issue Rejected"></c:set>
							</c:if>
							<c:if test='${fn:contains(rjq, "X") }'>
								<c:set var="text" value="text-danger"></c:set>
								<c:set var="stats" value="Return Rejected"></c:set>
							</c:if>

							<td class="${text}">${stats}</td>
							<c:if test='${fn:contains(rjq, "A") }'>
								<td>₹ ${issueHistory.calculateFine()}</td>
							</c:if>
							<c:if test='${fn:contains(rjq, "C") }'>
								<td>₹ ${issueHistory.getFine()}</td>
							</c:if>
							<c:if test='${fn:contains(rjq, "R") }'>
								<td>NA</td>
							</c:if>

							<td><button type="button" class="btn btn btn-primary"
									data-bs-toggle="modal" data-bs-target="${id}">Details</button></td>
						</tr>
					</c:forEach>
				</tbody>
				<c:forEach items="${issueHistory}" var="issueHistory">
					<c:set var="rid" value="ih${issueHistory.getRequestId()}"></c:set>
					<c:set var="rjq" value="${issueHistory.getStatus()}"></c:set>
					<div class="modal fade right" id="${rid}" tabindex="-1"
						role="dialog" data-backdrop="true">
						<div class="modal-dialog ">
							<div class="modal-content">
								<div class="modal-header header">
									<h5 class="modal-title text-white" id="exampleModalLabel">Request
										ID : ${issueHistory.getRequestId()}</h5>
									<button type="button" class="btn-close"
										style="background-color: white;" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body">
									<div class="row mt-0">
										<div class="col-12 ">
											<p>
												<b>Member ID : </b>${issueHistory.getMemberId()}</p>

										</div>
									</div>
									<div class="row mt-0">
										<div class="col-12 ">
											<p>
												<b>Member Name : </b>${issueHistory.getName()}
											</p>

										</div>
									</div>
									<div class="row mt-0">
										<%-- <div class="col-12">
													<c:set var="memberStatus"
														value="${issueHistory.getStatus()}"></c:set>
													<c:if test='${fn:contains(memberStatus, "A") }'>
														<c:set var="stats" value="Active"></c:set>
													</c:if>
													<c:if test='${fn:contains(memberStatus, "I") }'>
														<c:set var="stats" value="In Active"></c:set>
													</c:if>
													<p>
														<b>Member Status : </b>${stats}</p>

												</div> --%>
									</div>
									<div class="row mt-0">
										<div class="col-12 d-block">
											<p>
												<b>Book ID : </b>${issueHistory.getBookId()}</p>

										</div>
									</div>
									<div class="row mt-0">
										<div class="col-12">
											<p>
												<b>Book Title : </b>${issueHistory.getTitle()}</p>

										</div>
									</div>
									<div class="row mt-0">
										<div class="col-12">
											<p>
												<b>Request Date : </b>
												<fmt:formatDate type="date"
													value="${issueHistory.getRequestDate()}" />
											</p>

										</div>
									</div>


									<c:if test='${fn:contains(rjq, "A") || fn:contains(rjq, "C") }'>
										<div class="row mt-0">
											<div class="col-12">
												<p>
													<b>Issue Date : </b>
													<fmt:formatDate type="date"
														value="${issueHistory.getIssueDate()}" />
												</p>

											</div>
										</div>


										<div class="row mt-0">
											<div class="col-12">
												<p>
													<b> <c:if test='${fn:contains(rjq, "A")}'>
																		Due Date : 
																		</c:if> <c:if test='${fn:contains(rjq, "C") }'>
																		Return Date : 
																		</c:if>
													</b>
													<fmt:formatDate type="date"
														value="${issueHistory.getReturnDate()}" />
												</p>

											</div>
										</div>
									</c:if>


									<c:if test='${not fn:contains(rjq, "R") }'>
										<div class="row mt-0">
											<div class="col-12">
												<p>
													<c:if test='${fn:contains(rjq, "A") }'>
														<b>Pending Fine : </b> ₹ ${issueHistory.calculateFine()}
																				  </c:if>
													<c:if test='${fn:contains(rjq, "C") }'>
														<b>Paid Fine : </b> ₹ ${issueHistory.getFine()}
																				  </c:if>

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
		</div>
		<c:if test="${lastPage > 1 }">
			<nav class="pagination-outer" aria-label="Page navigation">
				<ul class="pagination">

					<c:if test="${currentPage != 1}">
						<li class="page-item"><a
							href="${pageContext.request.contextPath }/adminDB/${pageNo-1 }?sortField=${sortField}&sortDirection=${sortOrder}&pane=issueHistory"
							class="page-link" aria-label="Previous"> <span
								aria-hidden="true">« </span>
						</a></li>
					</c:if>
					<c:forEach begin="${beg}" end="${end}" var="pageNo">
						<c:choose>
							<c:when test="${pageNo != currentPage}">
								<li class="page-item"><a class="page-link"
									href="${pageContext.request.contextPath }/adminDB/${pageNo }?sortField=${sortField}&sortDirection=${sortOrder}&pane=issueHistory">${pageNo}</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item active"><a class="page-link"
									href="${pageContext.request.contextPath }/adminDB/${pageNo }?sortField=${sortField}&sortDirection=${sortOrder}&pane=issueHistory">${pageNo }</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${currentPage != lastPage}">
						<li class="page-item"><a
							href="${pageContext.request.contextPath }/adminDB/${pageNo+1 }?sortField=${sortField}&sortDirection=${sortOrder}&pane=issueHistory"
							class="page-link" aria-label="Next"> <span aria-hidden="true">
									»</span>
						</a></li>
					</c:if>
				</ul>
			</nav>
		</c:if>
	</c:when>
	<c:otherwise>
		<h3 class="text-center mt-3 text-primary ">Issue History</h3>
		<table class="table table-striped table-hover m-0">
			<thead class="thd">
				<tr>
					<th scope="col">Issue ID</th>
					<th scope="col">User ID</th>
					<th scope="col">Book ID</th>
					<!--<th scope="col">Request Date</th> -->
					<th scope="col">Issue Date</th>
					<th scope="col">Return Date</th>
					<th scope="col">Status</th>
					<th scope="col">Fine</th>
					<th scope="col">View/Edit</th>
				</tr>

			</thead>

		</table>
		<div class="col-lg-12 text-center m-0">
			<h6 class="bg-light py-2">No Data Exists</h6>
		</div>

	</c:otherwise>
</c:choose>