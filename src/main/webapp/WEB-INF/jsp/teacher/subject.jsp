<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>

<div id="subject" class="container navbar-default"
	ng-controller="SubjectController">
	<div id="examsResult" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Kết Quả</h4>
				</div>
				<div class="modal-body">
					<div class="table-responsive">
						<table class="table table-condensed table-hover table-striped">
							<thead>
								<tr>
									<th class="text-center text-align">Học Viên</th>
									<th class="text-center text-align">Điểm Cao Nhất</th>
									<th class="text-center text-align">Điểm Thấp Nhất</th>
									<th class="text-center text-align">Số Lần Làm</th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="result in resultExamList" data-toggle="modal"
									data-target="#examsResultDetail"
									ng-click="resultDetail(result.student)">
									<td class="text-center text-align">{{result.student.name}}</td>
									<td class="text-center text-align">{{result.maxScore ||
										0}}</td>
									<td class="text-center text-align">{{result.minScore ||
										0}}</td>
									<td class="text-center text-align">{{result.times}}</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
				</div>
			</div>
		</div>
	</div>

	<div id="examsResultDetail" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Kết Quả Của {{selectedStudent.name}}</h4>
				</div>
				<div class="modal-body">
					<div class="table-responsive">
						<table class="table table-condensed table-hover table-striped">
							<thead>
								<tr>
									<th class="text-center text-align">Lớp</th>
									<th class="text-center text-align">Điểm</th>
									<th class="text-center text-align">Ngày làm</th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="result in resultExamDetailList">
									<td class="text-center text-align">{{result.classs.name}}</td>
									<td class="text-center text-align">{{result.score ?
										result.score : 0}}</td>
									<td class="text-center text-align">{{result.createdAt}}</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
				</div>
			</div>
		</div>
	</div>

	<div id="createChapter" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Tạo Chương</h4>
				</div>
				<form class="form-horizontal" ng-submit="createChapter()">
					<div class="modal-body">
						<div class="form-group">
							<label class="col-sm-4 control-label">Tên Chương</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" ng-model="chapterName"
									required />
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-default">Tạo</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div id="assignStudentToSubject" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Thêm Sinh Viên Vào Môn Học</h4>
				</div>
				<form class="form-horizontal" ng-submit="assignStudentToSubject()">
					<div class="modal-body">
						<div class="form-group">
							<label class="col-sm-4 control-label">Email Sinh Viên</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" ng-model="studentEmail"
									required />
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-default">Thêm</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div id="fileStudentToSubject" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Thêm Sinh Viên Vào Môn Học</h4>
				</div>
				<form class="form-horizontal" ng-submit="assignStudentToSubject()">
					<div class="modal-body">
						<div class="form-group">
							<label class="col-sm-4 control-label">File</label>
							<div class="col-sm-6">
								<input type="file"
									onchange="angular.element(this).scope().fileStudentToSubject(this)" />
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-default">Thêm</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div id="createClass" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Tạo Lớp</h4>
				</div>
				<form class="form-horizontal" ng-submit="createClass()">
					<div class="modal-body">
						<div class="form-group">
							<label class="col-sm-2 control-label">Môn</label>
							<div class="col-sm-10">
								<input class="form-control" type="text" ng-model="subject.name"
									disabled>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Tên Lớp</label>
							<div class="col-sm-10">
								<input class="form-control" type="text" ng-model="className"
									required>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-default">Tạo</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div id="assign" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Cho Kiểm Tra</h4>
				</div>
				<form class="form-horizontal" ng-submit="assign()">
					<div class="modal-body">
						<div class="form-group">
							<label class="col-sm-2 control-label">Bài Thi</label>
							<div class="col-sm-10">
								<input class="form-control" type="text"
									ng-model="selectedExams.name" disabled>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Tên Lớp</label>
							<div class="col-sm-10">
								<select class="form-control" ng-model="selectedClass"
									ng-options="class as class.name for class in classList"
									required>
								</select>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-default">Gán</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div id="createTarget" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Tạo Tiêu Chí</h4>
				</div>
				<form class="form-horizontal" ng-submit="createTarget()">
					<div class="modal-body">
						<div class="form-group">
							<label class="col-sm-2 control-label">Môn</label>
							<div class="col-sm-10">
								<input class="form-control" type="text" ng-model="subject.name"
									disabled>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Chương</label>
							<div class="col-sm-10">
								<select class="form-control" ng-model="selectedChapter"
									ng-options="chapter as chapter.name for chapter in chapterList"
									required></select>

							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Tiêu chí</label>
							<div class="col-sm-10">
								<input class="form-control" type="text" ng-model="targetName"
									required>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-default">Tạo</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div>
		<div class="col-xs-12 col-lg-2 content-categories-panel"
			id="subject_menu">
			<div class="panel panel-default">
				<div class="panel-heading">{{subject.name}}</div>
				<ul class="nav nav-stacked" id="accordion1">
					<li class="panel" ng-click="showChapter()"><a
						data-toggle="collapse" data-parent="#accordion1" href="#">Các
							Chương</a></li>
					<li class="panel" ng-click="showExams()"><a
						data-toggle="collapse" data-parent="#accordion1" href="#">Đề
							Thi</a></li>
					<li class="panel" ng-click="showStudent()"><a
						data-toggle="collapse" data-parent="#accordion1" href="#">Học
							Viên</a></li>

					<li class="panel" ng-click="showClass()"><a
						data-toggle="collapse" data-parent="#accordion1" href="#">Lớp
							Học</a></li>
					<li class="panel" ng-click="showTarget()"><a
						data-toggle="collapse" data-parent="#accordion1" href="#">Tiêu
							Chí</a></li>
				</ul>
			</div>
		</div>
		<div class="col-xs-12 col-lg-10">
			<div class="row carousel-holder">
				<div class="col-lg-12" ng-show="isShowChapter" id="chapter_view">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div style="float: left">
								<b>CHƯƠNG</b>
							</div>
							<button ng-show="isSuperTeacherOfSubject" style="float: right"
								data-toggle="modal" data-target="#createChapter">
								<i class="fa fa-plus-square fa-lg" aria-hidden="true"></i>
							</button>
							<div style="clear: both"></div>
						</div>

						<div class="panel-body">
							<div class="col-lg-10 col-lg-offset-1">
								<div class="input-group">
									<input class="form-control" type="text"
										placeholder="Nhập chương cần tìm... " ng-model="searchText2"
										required /> <span class="input-group-btn">
										<button class="btn btn-primary">Tìm Kiếm</button>
									</span>
								</div>
							</div>
						</div>
						<div class="panel-body" style="padding-bottom: 0;">
							<div class="table-responsive">
								<table class="table table-hover table-striped">
									<thead ng-show="(chapterList|filter:searchText2 ).length != 0">
										<tr>
											<th>Tên</th>
											<th class="text-center text-align">Cấp 1</th>
											<th class="text-center text-align">Cấp 2</th>
											<th class="text-center text-align">Cấp 3</th>
											<th class="text-center text-align">Cấp 4</th>
											<th class="text-center text-align">Cấp 5</th>
											<th class="text-center text-align">Tổng Câu Hỏi</th>
											<th class="text-center text-align">Thao Tác</th>
										</tr>
									</thead>
									<tbody>
										<tr ng-repeat="chapter in chapterList | filter:searchText2">
											<td>{{chapter.name}}</td>
											<td class="text-center text-align">{{chapter.questionL1Count}}</td>
											<td class="text-center text-align">{{chapter.questionL2Count}}</td>
											<td class="text-center text-align">{{chapter.questionL3Count}}</td>
											<td class="text-center text-align">{{chapter.questionL4Count}}</td>
											<td class="text-center text-align">{{chapter.questionL5Count}}</td>
											<td class="text-center text-align">{{chapter.questionCount}}</td>
											<th class="text-center text-align">
												<button class="btn btn-danger"
													ng-click="removeChapterFromSubject(chapter.id)">
													<i class="fa fa-times fa-lg" aria-hidden="true"></i>
												</button>
											</th>
										</tr>
									</tbody>

									<div
										ng-show="(chapterList|filter:searchText2 ).length === 0">
										<div class="alert alert-warning" role="alert">
											<strong>Không tìm thấy chương !</strong>
										</div>
									</div>
								</table>
							</div>
						</div>
					</div>
				</div>

				<div class="col-lg-12" ng-show="isShowExams" id="exam_view">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div style="float: left">
								<b>KHO ĐỀ THI MÔN HỌC</b>
							</div>
							<a style="float: right" data-toggle="modal"
								href="/EvalutionSource/create-exams"> <i
								class="fa fa-plus-square fa-lg" aria-hidden="true"></i>
							</a>
							<div style="clear: both"></div>
						</div>

						<div class="panel-body">
							<div class="col-lg-10 col-lg-offset-1">
								<div class="input-group">
									<input class="form-control" type="text"
										placeholder="Nhập Đề thi cần tìm... " ng-model="searchText3"
										required /> <span class="input-group-btn">
										<button class="btn btn-primary">Tìm Kiếm</button>
									</span>
								</div>
							</div>
						</div>
						<div class="panel-body" style="padding-bottom: 0;">
							<div class="table-responsive">
								<table class="table table-condensed table-hover table-striped">
									<thead ng-show="(examsList|filter:searchText3).length != 0">
										<tr>
											<th class="text-center text-align">Tên</th>
											<th class="text-center text-align">Số Đề</th>
											<th class="text-center text-align">Hoàn Thành</th>
											<th class="text-center text-align">Tình Trạng</th>
											<th class="text-center text-align">Trạng Thái</th>
											<th class="text-center text-align">Kết Quả</th>
											<th class="text-center text-align">Thao tác</th>
										</tr>
									</thead>
									<tbody>
										<tr ng-repeat="exams in examsList | filter:searchText3">
											<td class="text-center text-align">{{exams.exams.name}}</td>
											<td class="text-center text-align">{{exams.exams.examcount}}</td>
											<td class="text-center text-align">{{exams.studentCount}}</td>
											<td class="text-center text-align">{{exams.isOpen ?
												"MỞ" : "ĐÓNG"}}</td>
											<td class="text-center text-align">{{exams.isApproved ?
												"ĐÃ DUYỆT" : "CHƯA DUYỆT"}}</td>
											<td class="text-center text-align">
												<button class="btn btn-primary" data-toggle="modal"
													data-target="#examsResult"
													ng-click="fetchExamsResult(exams.exams.id)">
													<i class="fa fa-eye fa-lg" aria-hidden="true"></i>
												</button>
											</td>
											<td class="text-center text-align"><a
												class="btn btn-primary" href="../exams/{{exams.exams.id}}">
													<i class="fa fa-eye fa-lg" aria-hidden="true"></i>
											</a>
												<button
													ng-hide="exams.isApproved || !isSuperTeacherOfSubject"
													class="btn btn-danger"
													ng-click="approveExams(exams.exams.id)"
													style="padding: 3px 12px">Duyệt</button>
												<button class="btn btn-primary"
													ng-click="clickAnssign(exams)" data-toggle="modal"
													data-target="#assign" style="padding: 3px 12px">Gán</button>
											</td>
										</tr>
									</tbody>
									
									<div
										ng-show="(examsList|filter:searchText3).length === 0">
										<div class="alert alert-warning" role="alert">
											<strong>Không tìm thấy đề thi !</strong>
										</div>
									</div>
								</table>
							</div>
						</div>
					</div>
				</div>

				<div class="col-lg-12" ng-show="isShowStudent" id="student_view">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div style="float: left">
								<b>HỌC VIÊN</b>
							</div>
							<button ng-show="isSuperTeacherOfSubject" style="float: right"
								data-toggle="modal" data-target="#fileStudentToSubject">
								<i class="fa fa-cloud-upload fa-lg" aria-hidden="true"></i>
							</button>
							<button ng-show="isSuperTeacherOfSubject" style="float: right"
								data-toggle="modal" data-target="#assignStudentToSubject">
								<i class="fa fa-user-plus fa-2x" aria-hidden="true"></i>
							</button>
							<div style="clear: both"></div>
						</div>

						<div class="panel-body">
							<div class="col-lg-10 col-lg-offset-1">
								<div class="input-group">
									<input class="form-control" type="text"
										placeholder="Nhập học viên cần tìm cần tìm... "
										ng-model="searchText4" required /> <span
										class="input-group-btn">
										<button class="btn btn-primary">Tìm Kiếm</button>
									</span>
								</div>
							</div>
						</div>

						<div class="panel-body" style="padding-bottom: 0;">
							<div class="table-responsive">
								<table class="table table-condensed table-hover table-striped">
									<thead ng-show="(studentList|filter:searchText4).length != 0">
										<tr>
											<th class="text-center text-align">Tên</th>
											<th class="text-center text-align">Lớp</th>
											<th class="text-center text-align">Thao Tác</th>
										</tr>
									</thead>
									<tbody>
										<tr ng-repeat="student in studentList | filter:searchText4">
											<td class="text-center text-align">{{student.name}}</td>
											<td class="text-center text-align">{{student.classname}}</td>
											<td class="text-center text-align">
												<button class="btn btn-danger"
													ng-click="removeStudentFromSubject(student.id)">
													<i class="fa fa-times fa-lg" aria-hidden="true"></i>
												</button>
											</th>
										</tr>
									</tbody>
									<div
										ng-show="(studentList|filter:searchText4).length === 0">
										<div class="alert alert-warning" role="alert">
											<strong>Không tìm thấy học viên !</strong>
										</div>
									</div>
								</table>
							</div>
						</div>
					</div>
				</div>

				<div class="col-lg-12" ng-show="isShowClass" id="class_show">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div style="float: left">
								<b>DANH SÁCH LỚP HỌC</b>
							</div>
							<button style="float: right" data-toggle="modal"
								data-target="#createClass">
								<i class="fa fa-plus-square fa-lg" aria-hidden="true"></i>
							</button>
							<div style="clear: both"></div>
						</div>

						<div class="panel-body">
							<div class="col-lg-10 col-lg-offset-1">
								<div class="input-group">
									<input class="form-control" type="text"
										placeholder="Nhập lớp học cần tìm... " ng-model="searchText5"
										required /> <span class="input-group-btn">
										<button class="btn btn-primary">Tìm Kiếm</button>
									</span>
								</div>
							</div>
						</div>

						<div class="panel-body" style="padding-bottom: 0;">
							<div class="table-responsive">
								<table class="table table-condensed table-hover table-striped">
									<thead ng-show="(classList|filter:searchText5).length != 0">
										<tr>
											<th class="text-center text-align">Tên Lớp</th>
											<th class="text-center text-align">Học Viên</th>
											<th class="text-center text-align">Bài Thi</th>
											<th class="text-center text-align">Tình Trạng</th>
											<th class="text-center text-align">Thao Tác</th>
										</tr>
									</thead>
									<tbody>
										<tr ng-repeat="class in classList |filter:searchText5">
											<td class="text-center text-align">{{class.name}}</td>
											<td class="text-center text-align">{{class.studentCount}}</td>
											<td class="text-center text-align">{{class.examsCount}}</td>
											<td class="text-center text-align">{{class.state}}</td>
											<td class="text-center text-align"><a title="view"
												class="btn btn-primary" href="../class/{{class.id}}"> <i
													class="fa fa-sign-in fa-lg" aria-hidden="true"></i>
											</a>
												<button ng-show="class.isopen" class="btn btn-danger"
													ng-click="closeClass(class.id)">
													<i class="fa fa-times fa-lg" aria-hidden="true"></i>
												</button></td>
										</tr>
									</tbody>
									
										<div
										ng-show="(classList|filter:searchText5).length === 0">
										<div class="alert alert-warning" role="alert">
											<strong>Không tìm thấy lớp học !</strong>
										</div>
									</div>
								</table>
							</div>
						</div>
					</div>
				</div>

				<div class="col-lg-12" ng-show="isShowTarget">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div style="float: left">
								<b>TIÊU CHÍ</b>
							</div>
							<button ng-show="isSuperTeacherOfSubject" style="float: right"
								data-toggle="modal" data-target="#createTarget">
								<i class="fa fa-plus-square fa-lg" aria-hidden="true"></i>
							</button>
							<div style="clear: both"></div>
						</div>

						<div class="panel-body">
							<div class="col-lg-10 col-lg-offset-1">
								<div class="input-group">
									<input class="form-control" type="text"
										placeholder="Nhập tiêu chí cần tìm... " ng-model="searchText"
										required /> <span class="input-group-btn">
										<button class="btn btn-primary">Tìm Kiếm</button>
									</span>
								</div>
							</div>
						</div>
						<div class="panel-body" style="padding-bottom: 0;">
							<div class="table-responsive">
								<table class="table table-condensed table-hover table-striped">
									<thead ng-show="(targetList|filter:searchText).length != 0">
										<tr>
											<th>Nội Dung</th>
											<th>Chương</th>
											<th>Thao Tác</th>
										</tr>
									</thead>
									<tbody>
										<tr ng-repeat="target in targetList |filter:searchText">
											<td>{{target.content}}</td>
											<th>{{target.chapter.name}}</th>
											<th>
												<button class="btn btn-danger"
													ng-click="removeTargetFromSubject(target.id)">
													<i class="fa fa-times fa-lg" aria-hidden="true"></i>
												</button>
											</th>
										</tr>
									</tbody>
									
									<div
										ng-show="(targetList|filter:searchText).length === 0">
										<div class="alert alert-warning" role="alert">
											<strong>Không tìm thấy tiêu chí !</strong>
										</div>
									</div>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>