<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>

<div id="exam" class="container navbar-default" ng-controller="ExamController">
	<div class="col-xs-12">
		<div class="row carousel-holder">
			<div class="col-lg-6">
				<div class="panel panel-default">
					<div class="panel-heading" style="padding-bottom:20px">
						<p style="text-align: center;"><b>Đề Thi {{exams.name}}</b></p>
					  	<div>
							<button class="btn btn-danger" style="width:100%" ng-click="updateExam()">Cập Nhật</button>
						</div>
					</div>
					<div ng-drop="true" class="panel-body" ng-drop-success="ondropsucess($data,$event)" style="padding-bottom:0;">	
						<div ng-drag="true" ng-drag-data="blockQuestion" data-allow-transform="true" ng-repeat="blockQuestion in blockQuestionList">
			                <div class="thumbnail"  id="fix_size">                
				                <div class="thumbnail">
					                <button class="btn btn-danger" ng-click="removeQuestion(blockQuestion)">
										<i class="fa fa-trash fa-lg" aria-hidden="true"></i>
									</button>
									<label ng-show="blockQuestion.isNew" style="float:right; float: right; border-radius: 50px; padding: 3px 10px; background: yellow;">
										Mới
									</label>
				                </div>
				                
				                <div style="padding: 15px 15px 1px" ng-show="!!blockQuestion.content">
					                <div froala="titleOptions" contenteditable="false" ng-model="blockQuestion.content"></div>
				                </div>
				                <div style="border: 1px solid #f47142; padding: 15px 30px; border-radius: 10px; margin: 15px 10px;" ng-repeat="question in blockQuestion.questionList">
				                	<div style="padding: 10px 0px 5px">
				                		<div froala="titleOptions" contenteditable="false" ng-model="question.content"></div>
				                	</div>
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
			
			<div class="col-lg-6">
				<div class="panel panel-default">
					<div class="panel-heading" style ="display: flex; justify-content: space-between;">
						<div class="form-group"  style="flex-grow:1">
						    <label for="keyWord">Từ Khoá</label>
						    <input type="text" class="form-control" id="keyWord" ng-model="searchText" ng-change="onChangeSearchQuestion()">
					  	</div>
					  	<div class="form-group" style="flex-grow:1">
						    <label for="keyWord">Chương</label>
						    <select
				 				class="form-control"
								ng-model="selectedChapter"
								ng-options="chapter as chapter.name for chapter in chapterList"
								ng-change="onChangeSelectedChapter()"
								required
							>
							</select>
					  	</div>
					</div>
					<div class="panel-body" ng-drop="true" style="padding-bottom:0">	
						<div ng-repeat="blockQuestion in blockQuestionListSearch">
			                <div class="thumbnail" ng-drag="true" ng-drag-data="blockQuestion" data-allow-transform="true" id="fix_size">                
				                <div class="thumbnail" style="padding: 5px 15px">
					                <button class="btn btn-primary" ng-click="addQuestion(blockQuestion)">
										<i class="fa fa-plus fa-lg" aria-hidden="true"></i>
									</button>
				                </div>
				                <div style="padding: 15px 15px 1px" ng-show="!!blockQuestion.content">
				                	<div froala="titleOptions" contenteditable="false" ng-model="blockQuestion.content"></div>
				                </div>
				                <div style="border: 1px solid #f47142; padding: 15px 30px; border-radius: 10px; margin: 15px 10px;" ng-repeat="question in blockQuestion.questionList">
				                	<div style="padding: 5px 0px 0px">
				                		<div froala="titleOptions" contenteditable="false" ng-model="question.content"></div>
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