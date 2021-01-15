<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>

<div id="testView" class="container navbar-default" ng-controller="TestController">
	<div class="col-xs-2 content-categories-panel">
		<div class="panel panel-default">
			<div class="panel-heading">Thông tin</div>
		</div>
	</div>
	
	<div class="col-xs-10">
		<div class="row carousel-holder">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div style="float: left"><h4>Kỳ Thi {{examsInfo.exams.name}} </h4></div>
						<div style="clear: both"></div>
					</div>
					<div class="panel-body" style="padding-bottom:0;">
						<h4 style="text-align:center" ng-show='examsInfo.exams.timetype == "question"'>Thời gian còn lại của câu</h4>
						<h4 style="text-align:center" ng-show='examsInfo.exams.timetype == "exams"'>Thời gian còn lại của bài thi</h4>
						<div style="height: 35px;position: relative; border: 1px solid; border-radius: 10px;">
							<div style="display:flex; position:absolute; width:100%; height:100%; justify-content: center;">
								<h4 style="">{{timer}} : {{second}}</h4>
							</div>
						</div>
						<div>
							<ul style="padding:0px; display: flex; flex-direction: row; justify-content:space-between; align-items: center">
								<li ng-show='examsInfo.exams.timetype === "exams"' ng-click="back()" style="list-style-type: none; cursor: pointer">
									<div>
										<i class="fa fa-caret-left fa-3x" aria-hidden="true"></i>
									</div>
								</li>
								<li ng-repeat="question in questionList"  style="list-style-type: none; cursor: pointer">
									<div ng-click="clickQuestion(question.questionblockid)">
										<i class="fa fa-circle-thin" aria-hidden="true" ng-hide="!!question.answerId" ></i>
										<i class="fa fa-dot-circle-o" aria-hidden="true" ng-show="!!question.answerId" ></i>										
									</div>
								</li>
								<li  ng-click="next()" style="list-style-type: none; cursor: pointer">
									<div>
										<i class="fa fa-caret-right fa-3x" aria-hidden="true"></i>
									</div>
								</li>
							</ul>
						</div>
						<form ng-submit="onSubmitTest()">
							<div ng-repeat="blockQuestion in blockQuestionList">
				                <div class="thumbnail"  id="fix_size" ng-show="blockQuestion.isShow">                
					                <div style="padding: 30px 15px 10px" ng-show="!!blockQuestion.content">
					                	<div froala="titleOptions" ng-model="blockQuestion.content"></div>
					                </div>
					                <div style="border: 1px solid #f47142; padding: 15px 30px; border-radius: 10px; margin: 15px 10px;" ng-repeat="question in blockQuestion.questionList">
					                	<div style="padding: 15px 0px 10px">
					                		<div froala="titleOptions" ng-model="question.content"></div>
					                	</div>
					                	<div>
					                		<div style="padding: 5px 0px; display: flex" ng-repeat="answer in question.answerList"> 
					                			<input style="margin-right:10px" type="radio" ng-model="question.answerId" name="{{answer.questionid}}" value="{{answer.id}}" >
					                			<div froala="titleOptions" ng-model="answer.content"></div>
					                		</div>
					                	</div>
					                </div>
				            	</div>
				            </div>
				            <div class="form-group">
				            	<input class="form-control" type="submit" value="Nộp bài">
				            </div>  
						</form>	  
					</div>
				</div>
			</div>
          </div>
	</div>
</div>