<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="" class="container navbar-default"
	ng-controller="AdminController">
	<div
		class="col-xs-2 col-lg-2 col-md-2 col-sm-2 content-categories-panel">
		<div class="panel panel-default">
			<div class="panel-heading">NỘI DUNG</div>
			<ul class="nav nav-stacked" id="accordion1">
				<li class="panel active" id="panel1"><a
					data-parent="#accordion1" href="#" ng-click="showNotification()">Đăng
						Tin</a></li>

				<li class="panel" id="panel3"><a data-parent="#accordion1"
					href="#" ng-click="showEditSlider()">Cập Nhật Slider</a></li>
			</ul>
		</div>
	</div>

	<div class=" col-xs-10 col-lg-10 col-md-10 col-sm-10">
		<div ng-show="isShowNotification">
			<br>
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
			<table class="table table-condensed">
				<thead id="modalHeader2" ng-show="(ListNotification|filter:searchText ).length != 0">
					<tr>
						<th class="text-center">STT</th>
						<th class="text-center" style="width:200px;">TIÊU ĐỀ</th>
						<th class="text-center">NỘI DUNG THÔNG BÁO</th>
						<th class="text-center">LIÊN KẾT</th>
						<th class="text-center">NGƯỜI TẠO</th>
						<th class="text-center">NGÀY TẠO</th>
						<th class="text-center"></th>

					</tr>
				</thead>
				<tbody>

					<tr ng-repeat=" listNoti in ListNotification |filter:searchText">
						<td>{{$index + 1}}</td>
						<td>
						{{listNoti.title}}
						</td>
						<td>
						<p style="max-height: 80px; overflow-y: scroll; width: 300px;">
						{{listNoti.body}}
						</p>
						</td>
						
						<td>
						<p style="max-height: 80px; overflow-y: scroll; overflow-x: hidden; width: 150px;">
						{{listNoti.link}}
						</p>
						</td>
						<td>{{listNoti.usercreate}}</td>
						<td>{{listNoti.editCreatedat}}</td>
						<td> <div>
						<button class="btn btn-danger btn-sm" id="button1"
								name="singlebutton" ng-click="delNotification(listNoti,ListNotification)" style="width:80px; text-align:center">Xóa Tin</button>
						</div>
						<div>
							<button class="btn btn-primary btn-sm" 
								ng-click="modifyNotification(listNoti,ListNotification)" data-toggle="modal" data-target="#myModal2" style="width:80px; text-align:center">Cập Nhật</button>
						</div>
						</td>
					</tr>
                        <div ng-show="(ListNotification|filter:searchText ).length === 0">
							<div class="alert alert-warning" role="alert">
								<strong>Không tìm thấy thông báo !</strong>
							</div>
						   </div>
				</tbody>


			</table>
			<div class="row">
				<button type="button" class="btn btn-info" data-toggle="modal"
					data-target="#myModal">
					<span class="glyphicon glyphicon glyphicon-plus" aria-hidden="true" ></span>
					Thêm Thông Báo

				</button>
			</div>
			<div class="row">
				<div class="navbar-right">
					<ul class="pagination">
						<li class="active"><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
					</ul>
				</div>

			</div>

			<!-- Modal -->
			<div class="modal fade" id="myModal" role="dialog">

				<form ng-submit="addSubmitNotification()">
					<div class="modal-dialog">
						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header" id="modalHeader">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								
								<h4 class="modal-title text-center" > THÊM THÔNG BÁO !</h4>
								
							</div>
							<div class="modal-body">
								<div class="form-group">
									<label for="titleNotification">Tiêu Đề :</label> <input
										type="text" class="form-control" id="titleNotification"
										ng-model="title" required>
								</div>
								<div class="form-group">
									<label for="titleNotification">Người Tạo :</label> <input
										type="text" class="form-control" id="titleNotification"
										ng-model="usercreate" required>
								</div>

								<div class="form-group">
									<label for="pwd">Nội dung :</label>
									<textarea type="text" class="form-control" rows="5"
										ng-model="body" required></textarea>

								</div>

								<div class="form-group">
									<label for="pwd">Liên Kết :</label> <input type="text"
										class="form-control" id="link" ng-model="link"
										placeholder="www.sakai.hcmut.edu.vn">
								</div>
							</div>
							<div class="modal-footer">
							
							
								<input type="submit" value="Thêm" class="btn btn-primary"
									>
									<button type="button" class="btn btn-default"
									data-dismiss="modal">Đóng</button>
								
								
							
								
							</div>
						</div>
					</div>
				</form>

			</div>
			
			<div class="modal fade" id="myModal2" role="dialog">

				<form ng-submit="modifyNotification2()">
					<div class="modal-dialog">
						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header" id="modalHeader">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								
								<h4 class="modal-title text-center" > THAY ĐỔI THÔNG BÁO !</h4>
								
							</div>
							<div class="modal-body">
								<div class="form-group">
									<label for="titleNotification">Tiêu Đề :</label> <input
										type="text" class="form-control" id="titleNotification"
										ng-model="title" required>
								</div>
								<div class="form-group">
									<label for="titleNotification">Người Tạo :</label> <input
										type="text" class="form-control" id="titleNotification"
										ng-model="usercreate" required>
								</div>

								<div class="form-group">
									<label for="pwd">Nội dung :</label>
									<textarea type="text" class="form-control" rows="5"
										ng-model="body" required></textarea>

								</div>

								<div class="form-group">
									<label for="pwd">Liên Kết :</label> <input type="text"
										class="form-control" id="link" ng-model="link"
										placeholder="www.sakai.hcmut.edu.vn">
								</div>
							</div>
							<div class="modal-footer">
							
								<input type="submit" value="Thay đổi " class="btn btn-primary"
									>
									<button type="button" class="btn btn-default"
									data-dismiss="modal">Đóng</button>
								
							</div>
						</div>
					</div>
				</form>

			</div>
		</div>

		<!-- Show Slider -->
		<div ng-show="isShowSlider"></div>




	</div>

</div>