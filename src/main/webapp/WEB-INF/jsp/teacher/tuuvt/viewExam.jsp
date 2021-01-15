<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="container navbar-default" id="exportthis"
	ng-controller="ExportController" ng-init="start = 1">
	<div class="col-lg-2">
		<div class="sidebar-nav-fixed affix">
			<div class="well">
				<ul class="nav">
					<li class="panel-header"><strong>ĐỀ ĐƯỢC TẠO</strong></li>
					<li class="active" ng-repeat="listExam in ListExam"><a
						href="#exam{{$index}}">ĐỀ {{$index+1}}</a></li>

				</ul>
			</div>
		</div>
	</div>

	<div class="col-lg-10">
		<!-----------CREATE EXAM------- -->
		<div class="row">
			<div class="panel panel-default default">
				<div class="panel-heading">
					<strong>{{NameSubject.name}}</strong>
				</div>

				<div ng-repeat="listExam in ListExam" id="">

					<div ng-repeat="BlockQuestion in listExam.ListBlockQuestion"
						ng-init="outerIndex = $parent.start; $parent.start=$parent.start+BlockQuestion.ListQuestion.length;">

						<span ng-model="BlockQuestion.content">{{BlockQuestion.content}}</span>

						<div style="padding-left: 20px;"
							ng-repeat="Question in BlockQuestion.ListQuestion">

							<label>Câu {{outerIndex + $index}}:</label> <span
								ng-model="Question.content">{{Question.content}}</span>

							<div style="padding-left: 40px;"
								ng-repeat="Answer in Question.ListAnswer">

								<div class="checkbox disabled">

									<input type="checkbox" ng-model="Answer.issolution" disabled><span
										style="padding-left: 60px;" ng-model="Answer.content">{{Answer.content}}</span>
								</div>



							</div>

						</div>

					</div>


					<div class="panel panel-body">



						<!-- Save and ExportPDF -->


						<div class="panel panel-footer">
							<a target="_blank" href="view/{{listExam.examId}}/ExamPDF"
								class="btn btn-primary" role="button">Xuất Đề</a> <a
								target="_blank" href="view/{{listExam.examId}}/AnswerPDF"
								class="btn btn-primary" role="button">Xuất Đáp Án</a>


						</div>

					</div>
				</div>

			</div>


		</div>
	</div>
</div>