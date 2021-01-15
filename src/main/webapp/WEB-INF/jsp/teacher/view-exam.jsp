<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>

<div id="exam" class="container navbar-default" ng-controller="ExamController">
	<div class="col-xs-1"></div>
	<div class="col-xs-10">
		<div class="row carousel-holder">
			<div class="col-xs-12">
				<div class="panel panel-default">
					<div class="panel-heading" style="padding-bottom:20px">
						<p style="text-align: center;"><b>Đề Thi {{exams.name}}</b></p>
					</div>
					<div>	
						<div ng-repeat="blockQuestion in blockQuestionList">
			                <div class="thumbnail"  id="fix_size">                
				                <div style="padding: 30px 15px 5px" ng-show="!!blockQuestion.content">
					                <div froala="titleOptions" contenteditable="false" ng-model="blockQuestion.content"></div>
				                </div>
				                <div style="border: 1px solid #f47142; padding: 15px 30px; border-radius: 10px; margin: 15px 10px;" ng-repeat="question in blockQuestion.questionList">
				                	<div style="padding: 15px 0px 10px">
				                		<div froala="titleOptions" contenteditable="false" ng-model="question.content"></div>
				                	</div>
				                	<div>
				                		<div style="padding: 5px 0px; display: flex;" ng-repeat="answer in question.answerList"> 
				                			<input style="margin-right:10px" type="radio" disabled>
				                			<div froala="titleOptions" contenteditable="false" ng-model="answer.content"></div>
				                		</div>
				                	</div>
				                </div>
			            	</div>
			            </div>       
					</div>
				</div>
			</div>
          </div>
	</div>
</div>