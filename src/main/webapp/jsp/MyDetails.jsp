<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="wrapper">
	<div class="profile_card">
		<div class="profile_wrap">
			<div class="profile_img">
				<i class="fa fa-id-badge"></i>
				<p class="name">${user.getFirstName()} ${user.getLastName()}</p>
			</div>

			<div class="profile_icons">
				<div class="profile_item">
					<div class="icon">
						<i class="fa fa-phone"></i>
					</div>
					<div class="title">
						Mobile <br> No
					</div>
					<div class="num">${req.getPhone()}</div>
				</div>
				<div class="profile_item">
					<div class="icon">
						<i class="fas fa-users"></i>
					</div>
					<div class="title">User ID</div>
					<div class="num">${user.getUserId()}</div>
				</div>
				<div class="profile_item">
					<div class="icon">
						<i class="fas fa-bell"></i>
					</div>
					<div class="title">Membership Status</div>
					<c:set var="status" value="${user.getMember().getStatus()}"></c:set>
					<c:if test='${fn:contains(status, "A") }'>
						<c:set var="statss" value="Active"></c:set>
					</c:if>
					<c:if test='${fn:contains(status, "I") }'>
						<c:set var="statss" value="Inactive"></c:set>
					</c:if>
					<div class="num">${statss}</div>
				</div>
			</div>

			<!--  <div class="profile_btn text-white">
                    Show My Stats
                </div>-->

			<div class="edit_btn text-white" onclick="openForm()">Edit
				profile</div>
		</div>
	</div>
	<div class="profile_slider active">
		<ul>
			<li>
				<div class="slider_item">
					<div class="slider_left">

						<div class="item">
							<div class="item_name">Total books borrowed</div>
						</div>
					</div>
					<div class="slider_right">
						<div class="btn btn_following">${userBBooks}</div>
					</div>
				</div>
			</li>
			<li>
				<div class="slider_item">
					<div class="slider_left">

						<div class="item">
							<div class="item_name">No of current books</div>

						</div>
					</div>
					<div class="slider_right">
						<div class="btn btn_follow">${userCBooks}</div>
					</div>
				</div>
			</li>
			<li>
				<div class="slider_item">
					<div class="slider_left">

						<div class="item">
							<div class="item_name">Total pending requests</div>
						</div>
					</div>
					<div class="slider_right">
						<div class="btn btn_following">${userPBooks}</div>
					</div>
				</div>
			</li>
			<li>
				<div class="slider_item">
					<div class="slider_left">

						<div class="item">
							<div class="item_name">Total pending fine</div>
						</div>
					</div>
					<div class="slider_right">
						<div class="btn btn_following">${userPFine}</div>
					</div>
				</div>
			</li>
			<li>
				<div class="slider_item">
					<div class="slider_left">

						<div class="item">
							<div class="item_name">Total fine paid</div>

						</div>
					</div>
					<div class="slider_right">
						<div class="btn btn_follow">${userFine}</div>
					</div>
				</div>
			</li>

			<li>
				<div class="slider_item">
					<div class="slider_left">

						<div class="item">
							<div class="item_name">Total Rejected Requests</div>

						</div>
					</div>
					<div class="slider_right">
						<div class="btn btn_following">${userRBooks}</div>
					</div>
				</div>
			</li>
		</ul>
	</div>
</div>