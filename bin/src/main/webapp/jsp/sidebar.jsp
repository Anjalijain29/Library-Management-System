<div class="sidebar" id="sidebar" style="height:150vh;">
			<ul class="sidebar-nav">
				<li>
				<c:choose>
				<c:when test="${not empty ViewProfile}">
					<a class="text-dark highlight" href="${pageContext.request.contextPath}/userDB/1"
					class="text-white"><i class="fa fa-user"></i>Profile</a>
					</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath}/userDB/1"
					class="text-white"><i class="fa fa-user"></i>Profile</a>
				</c:otherwise>
				
				</c:choose>
				</li>
				<li><a href="${pageContext.request.contextPath }" class="nav-link active"><i
						class="fa fa-home"></i>Home</a></li>
				<li><a href="${pageContext.request.contextPath}/catalogue/0"><i
						class="fa fa-book-open"></i>Catalog</a></li>
				<!--   <li>
                    <a class="text white" data-bs-toggle="modal" data-bs-target="#exampleModal"><i class="fa fa-folder-plus"></i>Request New Book</a>
                </li>-->
				<li>
				<c:choose>
					<c:when test="${not empty initial}">
				<a class="text-dark highlight" href="${pageContext.request.contextPath}/ActiveRequest"><i class="fas fa-swatchbook"></i>My Books</a>
				</c:when>
				<c:otherwise>
				<a href="${pageContext.request.contextPath}/ActiveRequest"><i class="fas fa-swatchbook"></i>My Books</a>
				</c:otherwise>
				</c:choose>
				
				</li>
				
				<li>
				<c:choose>
			<c:when test="${empty rejected and empty initial and empty MyHistory and empty ViewProfile}">
					<a class="text-dark highlight" href="${pageContext.request.contextPath}/PendingRequest"><i class="fa fa-hand-point-right"></i>Pending Requests</a>
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath}/PendingRequest"><i class="fa fa-hand-point-right"></i>Pending Requests</a>
				</c:otherwise>
				</c:choose>
				
				</li>
						
				<li>
					<c:choose>
			<c:when test="${not empty rejected}">
				<a class="text-dark highlight" href="${pageContext.request.contextPath}/RejectedRequest"><i class="fas fa-times-circle"></i>Rejected Requests</a>
				</c:when>
				<c:otherwise>
				<a href="${pageContext.request.contextPath}/RejectedRequest"><i class="fas fa-times-circle"></i>Rejected Requests</a>
				</c:otherwise>
				</c:choose>
				
				</li>
						
				<li>
					<c:choose>
					<c:when test="${not empty MyHistory}">
					<a class="text-dark highlight" href="${pageContext.request.contextPath}/IssueHistory"><i class="fas fa-history"></i>Borrow History</a>
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath}/IssueHistory"><i class="fas fa-history"></i>Borrow History</a>
				</c:otherwise>
				</c:choose>
				</li>

				<div class="logout-side">
					<a href="${pageContext.request.contextPath }/logout"><i
						class="fa fa-sign-out"></i>Logout</a>
				</div>
			</ul>
		</div>