<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/issuerequest.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminDB.css">
    <!-- <link href="../css/modalForms.css" rel="stylesheet" /> -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous" />
    <script src="https://kit.fontawesome.com/3480789ff2.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <title>Issues</title>
    <link href="${pageContext.request.contextPath}/css/page.css" rel="stylesheet" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/srcbar.css">

</head> 
 


<!-- CONTENT -->

 <div class="content">
            <div class="mid justify-content-center">
                <div class="lbl">Issue Requests</div>
                <div class="sort">
                    <h4>Sort By</h4>
                    <form>
                        <select class="form-select mr" aria-label="Default select example">
                            <option selected value="1">Sort By</option>
                            <option value="2">Request Date</option>
                            <option value="3">Book-ID</option>
                            <option value="4">Member-Id</option>
                            <option value="5">Request-Id</option>
                          </select>
                        <select class="form-select" aria-label="Default select example">
                            <option selected value="1">Ascending</option>
                            <option value="2">Descending</option>
                          </select>
                        <button type="submit"><i class="fa fa-sort"></i></i></button>
                    </form>
                </div>
                <table class="req table table-striped table-hover">
                    <thead class="thd">
                        <tr>
                            <th scope="col">Request ID</th>
                            <th scope="col">User ID</th>
                            <th scope="col">Book ID</th>
                            <th scope="col">Request Date</th>
                            <th scope="col">View Details</th>
                        </tr>

                    </thead>

                    <tbody class="tbd">
						<c:forEach items="${list}" var="list">
							<c:set var="id" value="#R${list.getRequestId()}" ></c:set>
						
							    <tr>
									<td>${list.getRequestId()}</td>
									<td>${list.getMember().getUser().getUserId()}</td>
									<td>${list.getBook().getBookId()}</td>
									<td>${list.getRequestDate()}</td>
									<td><button type="button" class="btn btn btn-primary" data-bs-toggle="modal" data-bs-target="${id}">Details</button></td>
								</tr>
						
						
						
						
						
						
							<!-- MODAL_CONTENT -->
							<c:set var="rid" value="R${list.getRequestId()}" ></c:set>
								<div class="modal fade right" id="${rid}" tabindex="-1" role="dialog" data-backdrop="true">
									<div class="modal-dialog ">
										<div class="modal-content">
											<div class="modal-header header">
												<h5 class="modal-title " id="exampleModalLabel">Request ID : ${list.getRequestId()}</h5>
												<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
											</div>
											<div class="modal-body">
												<h5><b>Member ID : </b><p>${list.getMember().getUser().getUserId()}</p></h5>
												<h5><b>Member Name : </b><p>${list.getMember().getUser().getFirstName()} ${list.getMember().getUser().getLastName()}</p></h5>
												<h5><b>Book ID : </b><p>${list.getBook().getBookId()}</p></h5>
												<h5><b>Book Title : </b><p>${list.getBook().getTitle()}</p></h5>
												<h5><b>Request Date : </b><p>${list.getRequestDate()}</p></h5>
												<h5><b>Member Status : </b><p>${list.getMember().getStatus()}</p></h5>
												<h5><b>Member Warnings : </b><p>${list.getMember().getWarnings()}</p></h5>
												<!-- <div>
													<h5><b>Book Description : </b></h5>
													<p>Lorem ipsum dolor sit amet. Qui voluptas impedit in nihil modi sed tenetur soluta sit impedit quam qui autem repellat.
													Est porro facere qui unde quae et omnis voluptas et omnis laboriosam est dolorem mollitia et animi accusamus. Accusamus
													porro ab animi tenetur id repellat dignissimos. Et fugit consequuntur qui iusto ducimus non Quis consectetur.</p>
												</div> -->
												
											</div>
								
											<div class="modal-footer justify-content-center">
											
												<button type="button" class="btn btn-outline-success waves-effect mx-3">Accept</button>
												<button type="button" class="btn btn-outline-danger waves-effect mx-3">Reject</button>
											</div>
										</div>
								
									</div>
								</div>
							<!-- MODAL_CONTENT -->
						
						</c:forEach>

                        

                    </tbody>

                </table>



                <nav class="pagination-outer" aria-label="Page navigation">
                    <ul class="pagination">
                        <li class="page-item">
                            <a href="#" class="page-link" aria-label="Previous">
                                <span aria-hidden="true">« Previous</span>
                            </a>
                        </li>
                        <li class="page-item"><a class="page-link" href="#">1</a></li>
                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                        <li class="page-item active"><a class="page-link" href="#">3</a></li>
                        <li class="page-item"><a class="page-link" href="#">4</a></li>
                        <li class="page-item"><a class="page-link" href="#">5</a></li>
                        <li class="page-item">
                            <a href="#" class="page-link" aria-label="Next">
                                <span aria-hidden="true">Next »</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>


<!-- CONTENT -->


        <script src="${pageContext.request.contextPath}/js/adminDB.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/js/issuerequest.js"></script>

</body>

</html>