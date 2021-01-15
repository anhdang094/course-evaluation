<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>

<div id="classList" class="container navbar-default" ng-controller="ClassListController">
	<div class="row carousel-holder">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<strong>THÔNG BÁO</strong>
				</div>
				<div class="panel-body" style="padding-bottom:0;">
					<div class="table-responsive">
						<table class="table table-condensed table-hover table-striped">
							<thead>
							<tr>
								<th class="text-center text-align">Tên Lớp</th>
								<th class="text-center text-align">Thao Tác</th>
							</tr>
							</thead>
							<tbody>
							<tr ng-repeat="class in classList">
								<td class="text-center text-align">{{class.name}}</td>
								<td class="text-center text-align">
									<a title="view" class="btn btn-primary" href="class/{{class.id}}">
										<i class="fa fa-sign-in fa-lg" aria-hidden="true"></i>
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