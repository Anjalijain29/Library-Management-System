<nav class="navbar navbar-dark p-md-2" id="nav">
		<div id='title'>
			<a class="navbar-brand title" style="font-size: 30px;"
				href="${pageContext.request.contextPath }">
				<i class="fas fa-book-open" style="margin-right: 5px"></i> <b
				id="brand" style="font-size: 28px">Library Management System</b>
			</a>
		</div>
		<div id="db-name" style="color: #fff; font-size: 24px;">
		<c:set var="name" value="${req.getFirstName()}"></c:set>
			<b>Welcome ${name} <i class="fa fa-user"></i></b>
		</div>
		<div class="logout">
			<a href="${pageContext.request.contextPath }/logout">Logout<i
				style="margin-left: 24px;" class="fa fa-sign-out"></i></a>
		</div>
	</nav>