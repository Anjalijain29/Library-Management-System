<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



<c:choose>
				<c:when test="${totalPagesRejected !=0 }">

<h3 class="text-primary text-center mt-3 mb-3">Rejected Requests</h3>
					<table class="table table-striped table-hover">
						<thead class="thd">
							
							
							<tr>
								<th scope="col">Request ID<a href="${pageContext.request.contextPath}/userDB/${currentPage}?sortField=request_id&sortDirection=${revSortDirection}&pane=rejected" ><i class="fa fa-sort"></i></a></th>
								<!--  <th scope="col">Book Title<a href="${pageContext.request.contextPath}/userDB/${currentPageIssueHistory}?sortField=title&sortDirection=${revSortDirection}&pane=rejected" ><i class="fa fa-sort"></i></a></th>-->
								<th scope="col">Book Name<a href="${pageContext.request.contextPath}/userDB/${currentPage}?sortField=bookTitle&sortDirection=${revSortDirection}&pane=rejected" ><i class="fa fa-sort"></i></a></th>
								<th scope="col">Request Date<a href="${pageContext.request.contextPath}/userDB/${currentPage}?sortField=request_date&sortDirection=${revSortDirection}&pane=rejected" ><i class="fa fa-sort"></i></a></th>
								<th scope="col">Status<a href="${pageContext.request.contextPath}/userDB/${currentPage}?sortField=status&sortDirection=${revSortDirection}&pane=rejected" ><i class="fa fa-sort"></i></a></th>
							</tr>
							
							
						</thead>

						<tbody>

							<c:forEach var="rejReq" items="${rejectRequestss}">
								<tr>
									<td>${rejReq.getRequestId() }</td>
									<td>${rejReq.getBookTitle() }</td>
									<td><fmt:formatDate type="date"
											value="${rejReq.getRequestDate() }" /></td>
									<!--  Have to cahnge to return date after adding into the table -->
									<td><c:set var="rjq" value="${rejReq.getStatus() }"></c:set>
										<c:if test='${fn:contains(rjq, "X") }'>
											<c:set var="stats" value="Return Rejected"></c:set>
										</c:if> <c:if test='${fn:contains(rjq, "R") }'>
											<c:set var="stats" value="Issue Rejected"></c:set>
										</c:if>
										<p class="text-danger">${stats}</p></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
					
					
					<!--  PAGINATION -->
					<c:if test="${totalPagesRejected>=2 }">
					<nav class="pagination-outer" aria-label="Page navigation">
					<ul class="pagination">
						<c:if test="${currentPage != 1}">
							<li class="page-item"><a
								href="${pageContext.request.contextPath}/userDB/${pageNo-1}?sortField=${sortField}&sortDirection=${sortDirection}&pane=rejected"
								class="page-link" aria-label="Previous"> <span
									aria-hidden="true">« </span>
							</a></li>
						</c:if>
						<c:forEach begin="${beg}" end="${end}" var="pageNo">
							<c:choose>
								<c:when test="${pageNo != currentPage}">
									<li class="page-item"><a class="page-link"
										href="${pageContext.request.contextPath}/userDB/${pageNo}?sortField=${sortField}&sortDirection=${sortDirection}&pane=rejected">${pageNo}</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item active"><a class="page-link"
										href="${pageContext.request.contextPath}/userDB/${pageNo}?sortField=${sortField}&sortDirection=${sortDirection}&pane=rejected">${pageNo}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${currentPage != totalPagesRejected  }">
							<li class="page-item"><a
								href="${pageContext.request.contextPath}/userDB/${pageNo+1}?sortField=${sortField}&sortDirection=${sortDirection}&pane=rejected"
								class="page-link" aria-label="Next"> <span
									aria-hidden="true"> »</span>
							</a></li>
						</c:if>
					</ul>
				</nav></c:if>
				<!--  PAGINATION -->
				
					</c:when>
					<c:otherwise>
					
<h3 class="text-center mt-3 mb-3 text-primary">Rejected Requests</h3>
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