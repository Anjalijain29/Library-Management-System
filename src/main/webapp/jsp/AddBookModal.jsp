<div class="modal mt-5 form-popup d-none" id="myForm">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title text-white" id="exampleModalLabel">Add
							New Book</h4>

						<button onclick="closeForm()" type="button" class="btn-close close"
							data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
		
					<div class="modal-body">
						<form:form modelAttribute="req"
							action="${pageContext.request.contextPath}/addBook" method="post">
							<div class="mb-2">
								<div class="row">
									<div class="col col-lg-6">
										<i class="fas fa-book fa-1x"></i>
										<form:label path="title" for="book-name"
											class="col-form-label">
											<h6>
												Book Name<sup style="color: red;">*</sup>
											</h6>
										</form:label>
										<form:input path="title" type="text" class="form-control"
											id="book-name" />
										<c:if test="${not empty exists}">
											<span style="color: #D8000C;">This book already exists</span>
										</c:if>
										<form:errors cssStyle="color:#D8000C" path="title"></form:errors>
									</div>

									<div class="col col-lg-6">
										<i class="fas fa-user"></i>
										<form:label path="author" for="Author-text"
											class="col-form-label">
											<h6>
												Author<sup style="color: red;">*</sup>
											</h6>
										</form:label>
										<form:input path="author" type="text" class="form-control"
											id="Author-text" />
										<form:errors cssStyle="color:#D8000C" path="author"></form:errors>
									</div>
								</div>
							</div>

							<div class="mb-2">
								<div class="row">
									<div class="col col-lg-6">
										<i class="fas fa-upload"></i>
										<form:label path="publisher" for="publisher-text"
											class="col-form-label">
											<h6>
												Publisher<span class="caret"></span><sup style="color: red;">*</sup>
											</h6>
										</form:label>

										<form:select path="publisher" name="publisher"
											class="form-control " id="publisher-text">
											<option value="1">Pearson Longman</option>
											<option value="2">Cambridge University Press</option>
											<option value="3">Penguin Books</option>
											<option value="4">Westland Books</option>
											<option value="5">Amar Publishers</option>
											<option value="6">Jaico Books</option>
										</form:select>
										<form:errors cssStyle="color:#D8000C" path="publisher"></form:errors>
									</div>
									<div class="col col-lg-6">
										<i class="fas fa-tag"></i>
										<form:label path="category" for="category-text"
											class="col-form-label fst-normal">
											<h6>
												Category<sup style="color: red;">*</sup>
											</h6>
										</form:label>
										<form:input path="category" type="text" class="form-control"
											id="category-text" />
										<form:errors cssStyle="color:#D8000C" path="category"></form:errors>
									</div>
								</div>
							</div>

							<div class="mb-2">
								<div class="row">
									<div class="col col-lg-6">
										<i class="fas fa-plus-square"></i>
										<form:label path="count" for="count-text"
											class="col-form-label">
											<h6>
												Count<sup style="color: red;">*</sup>
											</h6>
										</form:label>
										<form:input path="count" type="number" class="form-control"
											id="count-text" />
										<form:errors cssStyle="color:#D8000C" path="count"></form:errors>
									</div>
									<div class="col col-lg-6">
										<i class="fas fa-calendar-alt"></i>
										<form:label path="printYear" for="print-year-text"
											class="col-form-label fst-normal">
											<h6>
												Print-Year<sup style="color: red;">*</sup>
											</h6>
										</form:label>
										<form:input type="number" path="printYear" class="form-control"
											id="print-year-text" />
											<c:if test="${not empty year}">
											<span style="color: #D8000C;">Enter a valid Print Year</span>
											</c:if>
										<form:errors cssStyle="color:#D8000C" path="printYear"></form:errors>
											
									</div>
								</div>
							</div>
							<div class="modal-footer justify-content-center">
								<button type="submit" class="btn add-btn">Add Book</button>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>