<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>

<div id="classList" class="container navbar-default" ng-controller="ClassListController">
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
							<label class="col-sm-2 control-label">Môn học</label>
							<div class="col-sm-10">
								<select
										class="form-control"
										ng-model="selectedSubject"
										ng-options="subject as subject.name for subject in subjectList"
										required
								>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Tên Lớp</label>
							<div class="col-sm-10">
								<input class="form-control" type="text" ng-model="className" required>
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

	<div class="row carousel-holder" id="classA">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<strong>LỚP HỌC</strong>
				</div>
				<div class="panel-body" style="padding-bottom:0;">
					<div class="table-responsive">
						<table class="table table-condensed table-hover table-striped">
							<thead>
							<tr>
								<th class="text-center text-align">Tên Lớp</th>
								<th class="text-center text-align">Môn Học</th>
								<th class="text-center text-align">Học Viên</th>
								<th class="text-center text-align">Bài Thi</th>
								<th class="text-center text-align">Tình Trạng</th>
								<th class="text-center text-align">Thao Tác</th>
							</tr>
							</thead>
							<tbody>
							<tr ng-repeat="class in classList">
								<td class="text-center text-align">{{class.name}}</td>
								<td class="text-center text-align">{{class.subject.name}}</td>
								<td class="text-center text-align">{{class.studentCount}}</td>
								<td class="text-center text-align">{{class.examsCount}}</td>
								<td class="text-center text-align">{{class.state}}</td>
								<td class="text-center text-align">
									<a title="view" class="btn btn-primary" href="class/{{class.id}}">
										<i class="fa fa-sign-in fa-lg" aria-hidden="true"></i>
									</a>
									<button ng-show="class.isopen" class="btn btn-danger" ng-click="closeClass(class.subject.id, class.id)">
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