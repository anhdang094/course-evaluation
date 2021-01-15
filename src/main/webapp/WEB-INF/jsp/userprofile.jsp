<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>

<div class="container navbar-default">
	<div class='row'>
		<div class="col-lg-3 content-categories-panel">
			<div class="panel panel-default">
				<div class="panel-heading">Vùng Làm Việc</div>
				<ul class="nav nav-stacked" id="accordion1">

					<li class="panel"><a data-toggle="collapse"
						data-parent="#accordion1" href="#firsthLink">Trang Chủ </a>

						<ul id="firsthLink" class="collapse">
							<li><a href="#firsthLink2">Trang chủ</a></li>

						</ul></li>

					<li class="panel"><a data-toggle="collapse"
						data-parent="#accordion1" href="#secondthLink">Kỹ Năng</a>
						<ul id="secondthLink" class="collapse">

							<li><a href="#secondthLink2">Kỹ Năng</a></li>

						</ul></li>

					<li class="panel"><a data-toggle="collapse"
						data-parent="#accordion1" href="#thirdthLink">Liên Lạc</a>

						<ul id="thirdthLink" class="collapse">
							<li><a href="#thirdthLink2">Liên Lạc Email</a></li>

						</ul></li>

					<li class="panel"><a data-toggle="collapse"
						data-parent="#accordion1" href="#thirdthLink">Kế Hoạch Làm
							Việc</a>

						<ul id="thirdthLink" class="collapse">
							<li><a href="#thirdthLink2">Tuần</a></li>
							<li><a href="#thirdthLink2">Tháng</a></li>
							<li><a href="#thirdthLink2">Năm</a></li>
						</ul></li>


				</ul>
			</div>
		</div>
		<div class="col-lg-9">
			<div class="row carousel-holder">
				<div class="col-lg-12">
					<div class="panel panel-default" id="firstLink2">
						<div class="panel-heading">Thông Tin Cá Nhân</div>
						<br />
						<p>
							<b>Họ Và Tên : </b>${userprofile.username}</p>
						<p>
							<b>Email : </b>${username.email}</p>
						<p>
							<b>Phone : </b>${userprofile.phone}</p>
						<p>
							<b>Khoa :  </b>${userprofile.faculty}</p>
						<p>
							<b>Chuyên Môn :  </b>${userprofile.technical}</p>	
					</div>
				</div>
			</div>
		</div>


	</div>
</div>


