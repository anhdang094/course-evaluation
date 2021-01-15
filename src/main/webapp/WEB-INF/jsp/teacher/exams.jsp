<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>

<div id="examView" class="container navbar-default" ng-controller="ExamViewController">	
	<div class="col-xs-1 content-categories-panel">
	</div>
	
	<div class="col-xs-10">
		<div class="row carousel-holder">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div style="float: left"><h4>Các Đề</h4></div>
						<div style="clear: both"></div>
					</div>
					<div class="panel-body" style="padding-bottom:0;">
							<div class="table-responsive">
								<table class="table table-condensed table-hover table-striped">
									<thead>
										<tr>
									        <th class="text-center text-align">STT</th>
									        <th class="text-center text-align">Thao Tác</th>
									    </tr>
									</thead>
									<tbody>
										<tr ng-repeat="exam in examList">
									        <td class="text-center text-align">{{exam.id}}</td>
									        <td class="text-center text-align">
									        	<a title="view" class="btn btn-primary" href="{{exam.examsid}}/exam/{{exam.id}}/view">
									        		<i class="fa fa-eye fa-lg" aria-hidden="true"></i>
									        	</a>
									        	<a title="edit" class="btn btn-primary" href="{{exam.examsid}}/exam/{{exam.id}}/edit">
									        		<i class="fa fa-pencil fa-lg" aria-hidden="true"></i>
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