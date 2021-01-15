<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<div class="container">
	<!-- Responsive slider - START -->
	<div class="responsive-slider" data-spy="responsive-slider"
		data-autoplay="true">
		<div class="slides" data-group="slides">
			<ul>
				<li>
					<div class="slide-body" data-group="slide">
						<img src=<c:url value="resources/images/slider/ktmt.jpg"/>>
						<div class="caption header" data-animate="slideAppearRightToLeft"
							data-delay="500" data-length="300">
							<h2>Khoa Học & Kỹ Thuật Máy Tính</h2>
							<div class="caption sub" data-animate="slideAppearLeftToRight"
								data-delay="800" data-length="300">Đào tạo kỹ sư công nghệ
								hàng đầu !</div>
						</div>
						<br>
						<div class="caption img-html5"
							data-animate="slideAppearLeftToRight" data-delay="200">
							<img src=<c:url value="resources/images/slider/abet.jpg"/>>
						</div>
						<div class="caption img-css3"
							data-animate="slideAppearLeftToRight">
							<img src=<c:url value="resources/images/slider/abet.png"/>>
						</div>
					</div>
				</li>
				<li>
					<div class="slide-body" data-group="slide">
						<img src=<c:url value="resources/images/slider/slide-4.jpg"/>>
						<div class="caption header" data-animate="slideAppearRightToLeft"
							data-delay="500" data-length="300">
							<h2>Trường Đại Học Bách Khoa Tp HCM</h2>
							<div class="caption sub" data-animate="slideAppearLeftToRight"
								data-delay="800" data-length="300">Trung tâm nghiên cứu
								khoa học và chuyển giao công nghệ hàng đầu Việt Nam.</div>
						</div>
						<div class="caption img-bootstrap"
							data-animate="slideAppearDownToUp" data-delay="200">
							<img src=<c:url value="resources/images/slider/logo.png"/>>
						</div>
						<div class="caption img-twitter"
							data-animate="slideAppearUpToDown">
							<img src=<c:url value="resources/images/slider/logo2.png"/>>
						</div>
					</div>
				</li>
				<li>
					<div class="slide-body" data-group="slide">

						<img src=<c:url value="resources/images/slider/cs2.png"/>>
						<div class="caption header" data-animate="slideAppearRightToLeft"
							data-delay="500" data-length="300">
							<h2>Đại Học Bách Khoa TP HCM</h2>
							<div class="caption sub" data-animate="slideAppearLeftToRight"
								data-delay="800" data-length="300">Cung cấp nhân lực chất
								lượng cao phát triển đất nước !</div>
						</div>
						<div class="caption img-jquery" data-animate="slideAppearDownToUp"
							data-delay="200">
							<!--  <img src="../img/jquery.png">   -->
						</div>
					</div>
				</li>
			</ul>
		</div>
		<a class="slider-control left" href="#" data-jump="prev"><</a> <a
			class="slider-control right" href="#" data-jump="next">></a>
		<div class="pages">
			<a class="page" href="#" data-jump-to="1">1</a> <a class="page"
				href="#" data-jump-to="2">2</a> <a class="page" href="#"
				data-jump-to="3">3</a>
		</div>
	</div>

</div>

<!-- Responsive slider - END -->

<div class="container navbar-default" id="homepage"
	ng-controller="NotificationController">
	<div class="row carousel-holder">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<strong>THÔNG BÁO</strong>
				</div>
				<div class="panel-body">
					<div class="col-lg-10 col-lg-offset-1">
						<div class="input-group">
							<input class="form-control" type="text"
								placeholder="Nhập thông báo cần tìm... " ng-model="searchText"
								equired /> <span class="input-group-btn">
								<button class="btn btn-primary">Tìm Kiếm</button>
							</span>
						</div>
					</div>
					<hr>
					<br>
					<table
						class="table table-condensed table-fixedheader table-bordered table-striped">
						<thead id="modalHeader2" ng-show="(ListNotification|filter:searchText ).length != 0">
							<tr>
								<th class="text-center" style="width: 350px;">TIÊU ĐỀ</th>
								<th style="width: 500px;" class="text-center">NỘI DUNG</th>
								<th class="text-center">NGƯỜI TẠO</th>
								<th class="text-center">CHI TIẾT</th>
							</tr>
						</thead>
						<tbody>
							<tr
								ng-repeat=" listNoti in ListNotification |filter:searchText | orderBy:'-modifiedat'">
								<td class="text-left" style="color: blue;"><b>{{listNoti.title}}</b>
									<img ng-show="listNoti.checkDate" Alt="Mới"
									src="<c:url value="/resources/images/new/Icon_new.gif"/>" /></td>
								<td><p
										style="max-height: 80px; overflow-y: scroll; width: 500px;">{{listNoti.body}}</p></td>
								<td class="text-center">{{listNoti.usercreate}}</td>
								<td class="text-center"><a href="{{listNoti.link}}"
									target="_blank">Chi tiết xem ở đây !</a></td>
							</tr>
							
							
						</tbody>
						
						<div ng-show="(ListNotification|filter:searchText ).length === 0">
							<div class="alert alert-warning" role="alert">
								<strong>Chưa tìm thấy thông báo !</strong>
							</div>
						   </div>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
