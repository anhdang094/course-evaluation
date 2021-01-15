<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


 <div id="admin-page" class="container navbar-default" ng-controller="AdminController">
 	<div class="col-xs-2 content-categories-panel" id="show_admin">
		<div class="panel panel-default">
			<div class="panel-heading">Nội dung quản lí</div>
			<ul class="nav nav-stacked" id="accordion1">
				<li class="panel active" id="panel1">
					<a data-parent="#accordion1" href="#" ng-click="showTeacher()">Giảng Viên</a>
				</li>

				<li class="panel" id="panel2">
					<a data-parent="#accordion1" href="#" ng-click="showSubject()">Môn Học</a>
				</li>

				<li class="panel" id="panel3">
					<a data-parent="#accordion1" href="#" ng-click="showStudent()">Học Viên</a>
				</li>
			</ul>
		</div>
	</div>
	
	<div id="subjectTeach" class="modal fade" role="dialog">
	  		<div class="modal-dialog">
	    		<div class="modal-content">
	      			<div class="modal-header">
	        			<button type="button" class="close" data-dismiss="modal">&times;</button>
	        			<h4 class="modal-title">Môn Học Đang Dạy Của {{selectedTeacher.name}}</h4>
	      			</div>
		      			<div class="modal-body">
		        			<table class="table table-condensed table-hover table-striped">
								<thead>
							      <tr>
							       <th class="text-center text-align">Tên</th>
							       <th class="text-center text-align">Thao tác</th>
							     </tr>
							    </thead>
							    <tbody>
							      <tr ng-repeat="subject in subjectTeach">
							        <td class="text-center text-align">{{subject.name}}</td>
							        <td class="text-center text-align">
							        	<button title="remove" class="btn btn-danger"  ng-click="unassignTeacher(selectedTeacher.id, subject.id)">
											<i class="fa fa-times fa-lg" aria-hidden="true"></i>
										</button>
									</td>
							      </tr>
							    </tbody>
							</table>
		      			</div>
		      			<div class="modal-footer">
		        			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		      			</div>
	    		</div>
	  		</div>
	</div>
	
	<div id="subjectManage" class="modal fade" role="dialog">
	  		<div class="modal-dialog">
	    		<div class="modal-content">
	      			<div class="modal-header">
	        			<button type="button" class="close" data-dismiss="modal">&times;</button>
	        			<h4 class="modal-title">Môn Học Đang Quản Lý Của {{selectedTeacher.name}}</h4>
	      			</div>
		      			<div class="modal-body">
		        			<table class="table table-condensed table-hover table-striped">
								<thead>
							      <tr>
							       <th class="text-center text-align">Tên</th>
							       <th class="text-center text-align">Thao tác</th>
							     </tr>
							    </thead>
							    <tbody>
							      <tr ng-repeat="subject in subjectManage">
							        <td class="text-center text-align">{{subject.name}}</td>
							        <td class="text-center text-align">
							        	<button title="remove" class="btn btn-danger" ng-click="unassignSuperTeacher(selectedTeacher.id, subject.id)">
											<i class="fa fa-times fa-lg" aria-hidden="true"></i>
										</button>
									</td>
							      </tr>
							    </tbody>
							</table>
		      			</div>
		      			<div class="modal-footer">
		        			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		      			</div>
	    		</div>
	  		</div>
	</div>
	
	<div id="teacherTeach" class="modal fade" role="dialog">
	  		<div class="modal-dialog">
	    		<div class="modal-content">
	      			<div class="modal-header">
	        			<button type="button" class="close" data-dismiss="modal">&times;</button>
	        			<h4 class="modal-title">Giảng Viên Dạy Môn {{selectedSubject.name}}</h4>
	      			</div>
		      			<div class="modal-body">
		      				<div>
			      				<button style="width:100%" title="add" class="btn btn-primary" data-toggle="modal" data-target="#teach-modal">
									<i class="fa fa-user-plus fa-lg" aria-hidden="true"></i>
								</button>
		      				</div>
		        			<table class="table table-condensed table-hover table-striped">
								<thead>
							      <tr>
							       <th class="text-center text-align">Tên</th>
							       <th class="text-center text-align">Khoa</th>
							       <th class="text-center text-align">Thao tác</th>
							     </tr>
							    </thead>
							    <tbody>
							      <tr ng-repeat="teacher in teacherTeach">
							        <td class="text-center text-align">{{teacher.name}}</td>
							        <td class="text-center text-align">{{teacher.faculty}}</td>
							        <td class="text-center text-align">
							        	<button title="remove" class="btn btn-danger"  ng-click="unassignTeacher(teacher.id, selectedSubject.id)">
											<i class="fa fa-times fa-lg" aria-hidden="true"></i>
										</button>
									</td>
							      </tr>
							    </tbody>
							</table>
		      			</div>
		      			<div class="modal-footer">
		        			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		      			</div>
	    		</div>
	  		</div>
	</div>
	
		<div id="teacherManage" class="modal fade" role="dialog">
	  		<div class="modal-dialog">
	    		<div class="modal-content">
	      			<div class="modal-header">
	        			<button type="button" class="close" data-dismiss="modal">&times;</button>
	        			<h4 class="modal-title">Giảng Viên Quản Lý Môn {{selectedSubject.name}}</h4>
	      			</div>
		      			<div class="modal-body">
		      				<div>
			      				<button style="width:100%" title="add" class="btn btn-primary" data-toggle="modal" data-target="#assign-modal">
									<i class="fa fa-user-plus fa-lg" aria-hidden="true"></i>
								</button>
		      				</div>
		        			<table class="table table-condensed table-hover table-striped">
								<thead>
							      <tr>
							       <th class="text-center text-align">Tên</th>
							       <th class="text-center text-align">Khoa</th>
							       <th class="text-center text-align">Thao tác</th>
							     </tr>
							    </thead>
							    <tbody>
							      <tr ng-repeat="teacher in teacherManage">
							        <td class="text-center text-align">{{teacher.name}}</td>
							        <td class="text-center text-align">{{teacher.faculty}}</td>
							        <td class="text-center text-align">
							        	<button title="remove" class="btn btn-danger"  ng-click="unassignSuperTeacher(teacher.id, selectedSubject.id)">
											<i class="fa fa-times fa-lg" aria-hidden="true"></i>
										</button>
									</td>
							      </tr>
							    </tbody>
							</table>
		      			</div>
		      			<div class="modal-footer">
		        			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		      			</div>
	    		</div>
	  		</div>
	</div>
	
	<div id="createSubject" class="modal fade" role="dialog">
	  		<div class="modal-dialog">
	    		<div class="modal-content">
	      			<div class="modal-header">
	        			<button type="button" class="close" data-dismiss="modal">&times;</button>
	        			<h4 class="modal-title">Tạo Môn Học</h4>
	      			</div>
	      			<form class="form-horizontal" ng-submit="onSubmitCreateSubject()">
		      			<div class="modal-body">
		        			<div class="form-group">
		        				<label class="col-sm-4 control-label">Tên Môn Học</label>
		        				<div class="col-sm-6">
		        					<input name="subjectName" type="text" ng-model="subjectName" class="form-control" required/>
						        </div>
		        			</div>
		      			</div>
		      			<div class="modal-footer">
		      				<button type="submit" class="btn btn-default">Tạo</button>
		        			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		      			</div>
	      			</form>
	    		</div>
	  		</div>
	</div>
	
	<div id="assign-modal" class="modal fade" role="dialog">
	  		<div class="modal-dialog">
	    		<div class="modal-content">
	      			<div class="modal-header">
	        			<button type="button" class="close" data-dismiss="modal">&times;</button>
	        			<h4 class="modal-title">Assign Teacher To Subject</h4>
	      			</div>
	      			<form class="form-horizontal" ng-submit="onSubmitAssignTeacherToSubject()">
		      			<div class="modal-body">
		        			<div class="form-group">
		        				<label class="col-sm-4 control-label">Tên Môn Học</label>
		        				<div class="col-sm-6">
		        					<select
		        						class="form-control"
							 			ng-model="selectedSubject"
							 			ng-options="subject as subject.name for subject in subjectList"
							 		>
									</select>
						        </div>
		        			</div>
		        			
		        			<div class="form-group">
				        		<label class="col-sm-4 control-label">Giảng Viên</label>
				        		<div class="col-sm-6">
				        			<select
				        				class="form-control"
							 			ng-model="selectedTeacher"
							 			ng-options="teacher as teacher.name for teacher in teacherList"
							 		>
									</select>
								</div>
			        		</div>
		      			</div>
		      			<div class="modal-footer">
		      				<button type="submit" class="btn btn-default">Gán</button>
		        			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		      			</div>
	      			</form>
	    		</div>
	  		</div>
	</div>
	
	<div id="teach-modal" class="modal fade" role="dialog">
	  		<div class="modal-dialog">
	    		<div class="modal-content">
	      			<div class="modal-header">
	        			<button type="button" class="close" data-dismiss="modal">&times;</button>
	        			<h4 class="modal-title">Assign Teacher To Subject</h4>
	      			</div>
	      			<form class="form-horizontal" ng-submit="onSubmitTeacherToSubject()">
		      			<div class="modal-body">
		        			<div class="form-group">
		        				<label class="col-sm-4 control-label">Tên Môn Học</label>
		        				<div class="col-sm-6">
		        					<select
		        						class="form-control"
							 			ng-model="selectedSubject"
							 			ng-options="subject as subject.name for subject in subjectList"
							 		>
									</select>
						        </div>
		        			</div>
		        			
		        			<div class="form-group">
				        		<label class="col-sm-4 control-label">Giảng Viên</label>
				        		<div class="col-sm-6">
				        			<select
				        				class="form-control"
							 			ng-model="selectedTeacher"
							 			ng-options="teacher as teacher.name for teacher in teacherList"
							 		>
									</select>
								</div>
			        		</div>
		      			</div>
		      			<div class="modal-footer">
		      				<button type="submit" class="btn btn-default">Dạy</button>
		        			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		      			</div>
	      			</form>
	    		</div>
	  		</div>
	</div>
	
 	<div class="col-xs-10">
		<div class="row carousel-holder">
			<div class="col-lg-12" ng-show="isShowStudent" id="student22">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div style="float: left"><b>CÁC HỌC VIÊN</b></div>
						<div style="clear: both"></div>
					</div>
					<div class="panel-body" style="padding-bottom:0;">
						<div class="table-responsive">
							<table class="table table-hover table-striped">
								<thead>
							      <tr>
							      	<th class="text-center text-align">Tên</th>
					                <th class="text-center text-align">Khoa</th>
					                <th class="text-center text-align">Lớp</th>
							      </tr>
							    </thead>
							    <tbody>
							      <tr ng-repeat="student in studentList">
							      	<td class="text-center text-align">{{student.name}}</td>
					                <td class="text-center text-align">{{student.faculty}}</td>
					                <td class="text-center text-align">{{student.classname}}</td>
							      </tr>
							    </tbody>
							</table>
			            </div>  
					</div>
				</div>
			</div>
		
			<div class="col-lg-12" ng-show="isShowTeacher" id="teacher22">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div style="float: left"><b>GIẢNG VIÊN</b></div>
						<div style="clear: both"></div>
					</div>
					<div class="panel-body" style="padding-bottom:0;">
						<div class="table-responsive">
							<table class="table table-hover table-striped">
								<thead>
							      <tr>
							      	<th class="text-center text-align">Tên</th>
					                <th class="text-center text-align">Khoa</th>
					                <th class="text-center text-align">Vị trí</th>
					                <th class="text-center text-align">Dạy</th>
					                <th class="text-center text-align">Quản lý</th>
					                
							      </tr>
							    </thead>
							    <tbody>
							      <tr ng-repeat="teacher in teacherList">
							      	<td class="text-center text-align">{{teacher.name}}</td>
					                <td class="text-center text-align">{{teacher.faculty}}</td>
					                <td class="text-center text-align">{{teacher.position}}</td>
					                <td class="text-center text-align">
					                	<button title="view" class="btn btn-primary" data-toggle="modal" data-target="#subjectTeach" ng-click="fetchSubjectTeachOfTeacher(teacher)">
											<i class="fa fa-eye fa-lg" aria-hidden="true"></i>
										</button>
					                </td>
					                <td class="text-center text-align">
					                	<button title="view" class="btn btn-primary" data-toggle="modal" data-target="#subjectManage" ng-click="fetchSubjectManageOfTeacher(teacher)">
											<i class="fa fa-eye fa-lg" aria-hidden="true"></i>
										</button>
					                </td>
							      </tr>
							    </tbody>
							</table>
			            </div>
					</div>
				</div>
			</div>
			
			<div class="col-lg-12" ng-show="isShowSubject" id="subject22">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div style="float: left"><b>MÔN HỌC</b></div>
						<button style="float: right" data-toggle="modal" data-target="#createSubject">
							<i class="fa fa-plus-square fa-lg" aria-hidden="true"></i>
						</button>
						<div style="clear: both"></div>
					</div>
					<div class="panel-body" style="padding-bottom:0;">
						<div class="table-responsive">
							<table class="table table-hover table-striped">
								<thead>
							      <tr>
							      	<th class="text-center text-align">Tên</th>
					 				<th class="text-center text-align">Dạy</th>
					 				<th class="text-center text-align">Quản Lý</th>
					 				<th class="text-center text-align">Thao Tác</th>
							      </tr>
							    </thead>
							    <tbody>
							      <tr ng-repeat="subject in subjectList">
					 				<td class="text-center text-align">{{subject.name}}</td>
					 				<td class="text-center text-align">
					                	<button title="view" class="btn btn-primary" data-toggle="modal" data-target="#teacherTeach" ng-click="fetchTeacherTeachOfSubject(subject)">
											<i class="fa fa-eye fa-lg" aria-hidden="true"></i>
										</button>
					                </td>
					                <td class="text-center text-align">
					                	<button title="view" class="btn btn-primary" data-toggle="modal" data-target="#teacherManage" ng-click="fetchTeacherManageOfSubject(subject)">
											<i class="fa fa-eye fa-lg" aria-hidden="true"></i>
										</button>
					                </td>
					                <td class="text-center text-align">
							        	<button title="remove" class="btn btn-danger"  ng-click="deleteSubject(subject.id)">
											<i class="fa fa-times fa-lg" aria-hidden="true"></i>
										</button>
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
