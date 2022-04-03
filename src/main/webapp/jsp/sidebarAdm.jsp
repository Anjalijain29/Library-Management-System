<div class="sidebar" id="sidebar" style="height:130vh">
			<ul class="sidebar-nav">
				<li><a href="${pageContext.request.contextPath}"><i
						class="fa fa-home"></i>Home</a></li>
				<li><a href="${pageContext.request.contextPath}/catalogue/0"><i
						class="fa fa-book-open"></i>Catalog</a></li>
				<li>
				<c:choose>
				<c:when test="${not empty viewIssueRequests}">
				<a class="text-dark highlight"
					href="${pageContext.request.contextPath}/viewIssueRequests"
					class="text-white"><i class="fa fa-hand-point-right"></i>Issue
						Requests</a>
				
				</c:when>
				<c:otherwise>
				<a 
					href="${pageContext.request.contextPath}/viewIssueRequests"
					class="text-white"><i class="fa fa-hand-point-right"></i>Issue
						Requests</a>
				</c:otherwise>
				</c:choose>
				</li>
				<li>
				<c:choose>
			<c:when test="${not empty viewReturnRequests or empty initial }">
				<a class="text-dark highlight"
					href="${pageContext.request.contextPath}/viewReturnRequests"
					class="text-white"><i class="fa fa-hand-point-left"></i>Return
						Requests</a>
				</c:when>
				<c:otherwise>
				<a
					href="${pageContext.request.contextPath}/viewReturnRequests"
					class="text-white"><i class="fa fa-hand-point-left"></i>Return
						Requests</a>
				</c:otherwise>
				</c:choose>
		
				</li>
				<li>
				<c:choose>
				<c:when test="${not empty viewIssueHistory or empty initial }">
					<a class="text-dark highlight"
					href="${pageContext.request.contextPath}/viewIssueHistory"
					class="text-white"><i class="fas fa-history"></i>Issue
						History</a>
				</c:when>
				<c:otherwise>
					<a
					href="${pageContext.request.contextPath}/viewIssueHistory"
					class="text-white"><i class="fas fa-history"></i>Issue
						History</a>
				</c:otherwise>
				</c:choose>
		
				</li>
				<li>
				<a onclick="openForm()" class="text-white"><i
						class="fa fa-plus"></i>Add a Book</a>
				</li>
				<li>
				<c:choose>
				<c:when test="${not empty generateReport }">
						<a class="text-dark highlight" href="${pageContext.request.contextPath}/generateReport"
					class="text-white"><i class="fa fa-file-export"></i>Generate
						Report</a>
				</c:when>
				<c:otherwise>
						<a href="${pageContext.request.contextPath}/generateReport"
					class="text-white"><i class="fa fa-file-export"></i>Generate
						Report</a>
				</c:otherwise>
				</c:choose>
				
				</li>
			
				<div class="logout-side">
					<a href="${pageContext.request.contextPath }/logout"><i
						class="fa fa-sign-out"></i>Logout</a>
				</div>
			</ul>
		</div>