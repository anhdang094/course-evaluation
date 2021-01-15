<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>

<div id="class" class="container navbar-default" ng-controller="ClassController">
	<div class="col-xs-2 content-categories-panel">
		<div class="panel panel-default">
			<div class="panel-heading">Lớp {{class.name}}</div>
			<ul class="nav nav-stacked" id="accordion1">
			<li class="panel" ng-click="showExams()">
						<a data-toggle="collapse" data-parent="#accordion1" href="#exam">Đề Thi</a>
					</li>
					<li class="panel" ng-click="showStudent()">
						<a data-toggle="collapse" data-parent="#accordion1" href="#student">Học Viên</a>
					</li>
				</ul>
		</div>
	</div>
	
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
							      <tr ng-repeat="result in resultExamList" data-toggle="modal" data-target="#examsResultDetail" ng-click="resultDetail(result.student)">
							        <td class="text-center text-align">{{result.student.email}}</td>
							      	<td class="text-center text-align">{{result.maxScore || 0}}</td>
							      	<td class="text-center text-align">{{result.minScore || 0}}</td>
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
        			<h4 class="modal-title">Kết Quả Của {{selectedStudent.email}}</h4>
      			</div>
	      		<div class="modal-body">
	        		<div class="table-responsive">
						<table class="table table-condensed table-hover table-striped">
							<thead>
						      <tr>
						       <th class="text-center text-align">Điểm</th>
						       <th class="text-center text-align">Ngày làm</th>
						     </tr>
						    </thead>
						    <tbody>
						      <tr ng-repeat="result in resultExamDetailList">
						        <td class="text-center text-align">{{result.score ? result.score : 0}}</td>
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
	
	<div id="assignStudentToClass" class="modal fade" role="dialog">
  		<div class="modal-dialog">
    		<div class="modal-content">
      			<div class="modal-header">
        			<button type="button" class="close" data-dismiss="modal">&times;</button>
        			<h4 class="modal-title">Thêm Học Sinh Vào Lớp Học</h4>
      			</div>
      			<form class="form-horizontal" ng-submit="assignStudentToClass()">
	      			<div class="modal-body">
	        			<div class="form-group">
	        				<label class="col-sm-4 control-label">Email Sinh Viên</label>
	        				<div class="col-sm-6">
					        	<input class="form-control" type="text" ng-model="studentEmail" required>
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
	
	<div class="col-xs-10">
		<div class="row carousel-holder">
			<div class="col-lg-12" ng-show="isShowStudent">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div style="float: left"><h4>Học Viên</h4></div>
						<button style="float: right" data-toggle="modal" data-target="#assignStudentToClass">
							<i class="fa fa-user-plus fa-2x" aria-hidden="true"></i>
						</button>
						<div style="clear: both"></div>
					</div>
					<div class="panel-body" style="padding-bottom:0;">
						<div class="table-responsive">
							<table class="table table-striped">
								<thead>
									<tr>
										<th class="text-center text-align">Học Viên</th>
										<th class="text-center text-align">Thao Tác</th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="student in studentList">
										<td class="text-center text-align">{{student.email}}</th>
										<td class="text-center text-align">
											<button class="btn btn-danger" ng-click="removeStudentFromClass(student.id)">
												<i class="fa fa-times fa-lg" aria-hidden="true"></i>
											</button>
										</th>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			
			<div class="col-lg-12" ng-show="isShowExams">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div style="float: left"><h4>Đề Thi</h4></div>
							<a style="float: right" data-toggle="modal" href="/EvalutionSource/create-exams">
								<i class="fa fa-plus-square fa-2x" aria-hidden="true"></i>
							</a>
							<div style="clear: both"></div>
						</div>
						<div class="panel-body" style="padding-bottom:0;">	
		              		<div class="table-responsive">
								<table class="table table-condensed table-hover table-striped">
									<thead>
								      <tr>
								        <th class="text-center text-align">Tên</th>
								        <th class="text-center text-align">Số Đề</th>
								        <th class="text-center text-align">Hoàn Thành</th>
								        <th class="text-center text-align">Tình Trạng</th>
								        <th class="text-center text-align">Trạng Thái</th>
								        <th class="text-center text-align">Kết Quả</th>
								        <th class="text-center text-align">Thao Tác</th>
								      </tr>
								    </thead>
								    <tbody>
								      <tr ng-repeat="exams in examsList">
								        <td class="text-center text-align">{{exams.exams.name}}</td>
								        <td class="text-center text-align">{{exams.exams.examcount}}</td>
								        <td class="text-center text-align">{{exams.studentCount}}</td>
								        <td class="text-center text-align">{{exams.isOpen ? "Mở" : "Đóng"}}</td>
								        <td class="text-center text-align">
								        	{{exams.isApproved ? "ĐÃ DUYỆT" : "CHƯA DUYỆT"}}
										</td>
										<td class="text-center text-align">
											<button class="btn btn-primary" data-toggle="modal" data-target="#examsResult" ng-click="fetchExamsResult(exams.exams.id)">
								        		<i class="fa fa-eye fa-lg" aria-hidden="true"></i>	
								        	</button>
										</td>
										<td class="text-center text-align">
								        	<a  title="view" class="btn btn-primary" href="../exams/{{exams.exams.id}}">
								        		<i class="fa fa-eye fa-lg" aria-hidden="true"></i>	
								        	</a>
										</td>
								      </tr>
								    </tbody>
								</table>
				            </div>    
						</div>
					</div>
				</div>
          </div>
	</div>
</div>