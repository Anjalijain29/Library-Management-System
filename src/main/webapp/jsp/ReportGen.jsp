
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="reportType container">
	<h2 class="text-primary text-center mt-5 mb-5">
		Generate Reports
		</h4>
		<form:form modelAttribute="dataTimeSpan"
			action="${pageContext.request.contextPath}/adminDB/1?pane=Report"
			method="post">
			<div class="container">
				<div class="row justify-content-center">
					<div class="col col-lg-5 col-md-12 col-sm-12">
						<h6 class="text-start">Report type:</h6>
						<select class="form-select" path="type" name="type"
							aria-label="Default select example">

							<c:set var="sortVal" value="${rType}"></c:set>

							<c:choose>

								<c:when test='${fn:contains(sortVal, "1") }'>


									<option selected="selected" value="1">Most requested
										Books</option>

								</c:when>
								<c:otherwise>

									<option value="1">Most requested Books</option>

								</c:otherwise>

							</c:choose>

							<c:choose>

								<c:when test='${fn:contains(sortVal, "2") }'>


									<option selected="selected" value="2">Member-wise
										Acceptance Rates of Book Requests [per User]</option>
									</option>

								</c:when>
								<c:otherwise>

									<option value="2">Member-wise Acceptance Rates of Book
										Requests [per User]</option>

								</c:otherwise>

							</c:choose>


							<c:choose>

								<c:when test='${fn:contains(sortVal, "3") }'>


									<option selected="selected" value="3">Percentage of
										Books returned within Due Date [per User]</option>

								</c:when>
								<c:otherwise>

									<option value="3">Percentage of Books returned within
										Due Date [per User]</option>

								</c:otherwise>

							</c:choose>
							
							<%-- <c:choose>

								<c:when test='${fn:contains(sortVal, "4") }'>


									<option selected="selected" value="4">Net Quantity of Books [per Book]</option>

								</c:when>
								<c:otherwise>

									<option value="4">Net Quantity of Books [per Book]</option>

								</c:otherwise>

							</c:choose> --%>

						</select>
					</div>
					<div class="col col-lg-3 col-md-12 col-sm-12">
						<div class="dates">
							<h6>From :</h6>
							<form:input id="startDate" class="form-control" type="date"
								path="startDate" name="startDate" value="${sDate }" />
							<c:if test="${not empty dateError}">
								<span Style="color: #D8000C"> Enter Valid date</span>
							</c:if>

						</div>
					</div>
					<div class="col col-lg-3 col-md-12 col-sm-12">
						<div class="dates">
							<h6>To:</h6>
							<form:input id="endDate" class="form-control" type="date"
								path="endDate" name="endDate" value="${eDate }" />
							<c:if test="${not empty dateError}">
								<span Style="color: #D8000C"> Enter Valid date</span>
							</c:if>
						</div>
					</div>
				</div>
			</div>
			<div class="download text-center p-3">
				<!-- Download as:
                <a href="#"><i class="fa fa-file-pdf"></i></a>
                <a href="#"><i class="fa fa-file-excel"></i></a> -->
				<button type="submit" class="btn btn-primary text-center generate">Generate</button>
			</div>



		</form:form>
</div>
<c:if test="${not empty lastPage }">
	<div class="report container">
		<c:set var="sortVal" value="${rType}"></c:set>
		<c:choose>

			<c:when test='${fn:contains(sortVal, "1") }'>
				<h4 class="text-center mt-3 text-primary ">Most requested Books</h4>
			</c:when>
			<c:when test='${fn:contains(sortVal, "2") }'>
				<h4 class="text-center mt-3 text-primary ">Member-wise
					Acceptance Rates of Book Requests [per User]</h4>
			</c:when>
			<c:when test='${fn:contains(sortVal, "3") }'>
				<h4 class="text-center mt-3 text-primary ">Percentage of Books
					returned within Due Date [per User]</h4>
			</c:when>
			<%-- <c:when test='${fn:contains(sortVal, "4") }'>
				<h4 class="text-center mt-3 text-primary ">Net Quantity of Books [per Book]</h4>
			</c:when> --%>
		</c:choose>


		<c:choose>
			<c:when test="${lastPage !=0 }">


				<a href="${pageContext.request.contextPath}/download/demo.pdf"><i
					class="fas fa-2x fa-file-pdf" style="color: red"></i></a>
				<a href="${pageContext.request.contextPath}/download/demo.xlsx"><i
					class="fas fa-2x fa-file-excel" style="color: green"></i></a>
				<table class="table table-striped table-hover">
					<thead class="thd">
						<tr>
							<th scope="col">${colNames[0]}</th>
							<th scope="col">${colNames[1]}</th>
							<th scope="col">${colNames[2]}</th>
							<th scope="col">${colNames[3]}</th>

						</tr>

					</thead>

					<tbody class="tbd">
						<c:forEach items="${popBooks}" var="popBook">
							<tr>
								<td>${popBook[0]}</td>
								<td>${popBook[1]}</td>
								<td>${popBook[2]}</td>
								<td>${popBook[3]}</td>
							</tr>
						</c:forEach>

					</tbody>



				</table>



				<c:if test="${lastPage > 1 }">
					<nav class="pagination-outer" aria-label="Page navigation">
						<ul class="pagination">
							<c:if test="${currentPage != 1}">
								<li class="page-item"><a
									href="${pageContext.request.contextPath }/adminDB/${pageNo-1 }?pane=Report&startDate=${sDate}&endDate=${eDate}&type=${rType}"
									class="page-link"> <span aria-hidden="true">  « </span>
								</a></li>
							</c:if>
							<c:forEach begin="${beg}" end="${end}" var="pageNo">
								<c:choose>
									<c:when test="${pageNo != currentPage}">
										<li class="page-item"><a class="page-link"
											href="${pageContext.request.contextPath }/adminDB/${pageNo }?pane=Report&startDate=${sDate}&endDate=${eDate}&type=${rType}">${pageNo}</a></li>
									</c:when>
									<c:otherwise>
										<li class="page-item active"><a class="page-link"
											href="${pageContext.request.contextPath }/adminDB/${pageNo }?pane=Report&startDate=${sDate}&endDate=${eDate}&type=${rType}">${pageNo }</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:if test="${currentPage != lastPage}">
								<li class="page-item"><a
									href="${pageContext.request.contextPath }/adminDB/${pageNo+1 }?pane=Report&startDate=${sDate}&endDate=${eDate}&type=${rType}"
									class="page-link"> <span aria-hidden="true"> » </span>
								</a></li>
							</c:if>
						</ul>
					</nav>
				</c:if>

			</c:when>
			<c:otherwise>
				<table class="table table-striped table-hover mb-0">
					<thead class="thd">
						<tr>
							<th scope="col">${colNames[0]}</th>
							<th scope="col">${colNames[1]}</th>
							<th scope="col">${colNames[2]}</th>
							<th scope="col">${colNames[3]}</th>

						</tr>

					</thead>

				</table>
				<div class="col-lg-12 text-center m-0">
					<h6 class="bg-light py-2">No Data Exists</h6>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</c:if>