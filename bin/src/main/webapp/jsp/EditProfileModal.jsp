<div class="modal mt-5 form-popup d-none" id="myForm">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title text-white" id="exampleModalLabel">Update
							Profile</h4>

						<button type="button" class="btn-close"
							style="background-color: white;" onclick="closeForm()"></button>
					</div>
					<div class="modal-body"  style="padding-bottom: 16px !important;">

						<div class="card card-body edit-card">

							<form:form id="submitForm" action="${pageContext.request.contextPath}/updateProfile" method="post"
								modelAttribute="newReq">
								<div class="form-group required mb-2">
									<i class="fas fa-phone fa-1x mb-2"></i>
									<form:label path="phone" class="mb-2">Phone No</form:label>
									<form:input class="form-control text-lowercase" path=""
										value="${req.getPhone()}" disabled="true" />
									<form:hidden class="form-control text-lowercase" path="phone"
										value="${req.getPhone()}" />
								</div>
								<div class="form-group required mb-2">
									<i class="fas fa-user fa-1x mb-2"></i>
									<form:label path="firstName" class="mb-2">First name</form:label>
									<form:input class="form-control text-lowercase" required=""
										path="firstName" value="${req.getFirstName()}" />

									<form:errors cssStyle="color:#D8000C" path="firstName"></form:errors>
								</div>
								<div class="form-group required mb-2">
									<i class="fas fa-user fa-1x mb-2"></i>
									<form:label path="lastName" class="mb-2">Last Name</form:label>
									<form:input class="form-control text-lowercase "
										path="lastName" value="${req.getLastName()}" />

									<form:errors cssStyle="color:#D8000C" path="lastName"></form:errors>
								</div>

								<div class="form-group required mb-2">
									<i class="fas fa-lock fa-1x mb-2"></i>
									<form:label path="password" class="mb-2">Password</form:label>
									<form:password class="form-control text-lowercase"
										path="password" value="" />

									<form:errors cssStyle="color:#D8000C" path="password"></form:errors>
								</div>
								<div class="form-group required ">
									<i class="fas fa-lock fa-1x mb-2"></i>
									<form:label path="confirmPassword" class="mb-2">Confirm Password</form:label>
									<form:password class="form-control text-lowercase "
										path="confirmPassword" value="" />
									<c:if test="${not empty password }">
										<span style="color: #D8000C">Passwords not matching</span>
									</c:if>
								</div>
								<div class="modal-footer justify-content-center">
									<button class="btn" type="submit" style="color:black !important;">Update</button>
								</div>
							</form:form>
						</div>
					</div>

				</div>
			</div>
		</div>