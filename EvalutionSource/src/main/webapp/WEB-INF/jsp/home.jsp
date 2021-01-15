<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<center>Xin chào! ${userlogin.username} đến với hệ thống đánh
	giá chất lượng môn học</center>
<div class="container navbar-default">
	<div class='row'>
		<div class="col-lg-3 content-categories-panel">
			<div class="panel panel-default">
				<div class="panel-heading">Vùng Làm Việc</div>
				<ul class="nav nav-stacked" id="accordion1">

					<li class="panel"><a data-toggle="collapse"
						data-parent="#accordion1" href="#firsthLink">Trang Chủ</a>
						<ul id="firsthLink" class="collapse">
							<li><a href="#firsthLink2">Trang Chủ</a></li>

						</ul></li>

					<li class="panel"><a data-toggle="collapse"
						data-parent="#accordion1" href="#secondthLink">Hồ Sơ Cá Nhân</a>
						<ul id="secondthLink" class="collapse">
							<li><a href="#secondthLink2">Hồ sơ của tôi</a></li>
							<li><a href="#secondthLink2">Kết nối </a></li>
							<li><a href="#secondthLink2">Cập nhật</a></li>
						</ul></li>

					<li class="panel"><a data-toggle="collapse"
						data-parent="#accordion1" href="#thirdthLink">Kế Hoạch Giảng
							Dạy</a>
						<ul id="thirdthLink" class="collapse">
							<li><a href="#thirdthLink2">Kế hoạch chi tiết</a></li>
						</ul>
					<li class="panel"><a data-toggle="collapse"
						data-parent="#accordion1" href="#fourthLink">Resource</a>
						<ul id="fourthLink" class="collapse">
							<li><a href="#fourthLink2">Resource</a></li>
						</ul>
					<li class="panel"><a data-toggle="collapse"
						data-parent="#accordion1" href="#fivethLink">Thông Báo </a>
						<ul id="fivethLink" class="collapse">
							<li><a href="#fivethLink2">Thông báo mới !</a></li>
						</ul>
					<li class="panel"><a data-toggle="collapse"
						data-parent="#accordion1" href="#sixthLink">Danh mục</a>
						<ul id="sixththLink" class="collapse">
							<li><a href="#sixthLink2">Danh mục chi tiết</a></li>
						</ul>
				</ul>
			</div>
		</div>
		<div class="col-lg-9">
			<div class="row carousel-holder">
				<div class="col-lg-12">
					<div class="panel panel-default" id="firstLink2">
						<div class="panel-heading">Trang Chủ</div>
						<p>Chào mừng!</p>
					</div>
					<div class="panel panel-default" id="secondthLink2">
						<div class="panel-heading" id="username">Hồ Sơ Cá Nhân</div>

						<a href="profile/${userlogin.userid}">Hồ Sơ Cá Nhân</a>

						<p>Kết Nối</p>
						<p>
							<a href="#">Cập nhật thông tin cá nhân</a>
						</p>
					</div>
					<div class="panel panel-default" id="thirdthLink2">
						<div class="panel-heading">Kế Hoạch Giảng Dạy</div>
						<p>Tuần 1</p>
						<p>Tuần 2</p>
						<p>Tuần 3</p>
						<p>Tuần 4</p>
						<p>Tuần 5</p>
					</div>

					<div class="panel panel-default" id="fourthLink2">
						<div class="panel-heading">Resource</div>
						<p>
							<a href="#">Resource</a>
						</p>
					</div>

					<div class="panel panel-default" id="fivethLink2">
						<div class="panel-heading">Thông Báo</div>
						<p>
							<a href="#">Thông báo mới !</a>
						</p>
					</div>

					<div class="panel panel-default" id="fivethLink2">
						<div class="panel-heading">Danh Mục</div>
						<p>
							<a href="#">Danh mục chi tiết</a>
						</p>
					</div>



				</div>
			</div>
		</div>



	</div>
</div>