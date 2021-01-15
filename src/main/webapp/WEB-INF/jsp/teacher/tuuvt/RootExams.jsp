<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>

<div id="exam" class="container navbar-default"	ng-controller="ViewRootExamsController">
	<div class="col-xs-12">
		<div class="row carousel-holder">
			<div class="col-lg-6">
				<div class="panel panel-default">
					<div class="panel-heading" style="padding-bottom: 20px">
						<p style="text-align: center;">
							<b>Đề Thi {{rootExams.name}}</b>
						</p>
						<div>
							<a href="#" class="btn btn-danger" role="button"
								ng-click="updateRootExam();" ng-disabled="isDisabled">Lưu Đề</a>
							
							<span>Mã Đề : </span>
							<input type="text" ng-model="CodeExam"  placeholder="Nhập mã đề" 
							 style="width:150px; height:30px; border-radius: 25px;" required >
						</div>
					</div>
					

						<div ng-repeat="rBlockQuestion in ListRootQuestionBlock"
							style="border: 1px solid #a1a1a1; padding: 10px 20px; background:; border-radius: 15px;">
						
							<div class="thumbnail" id="fix_size">
								<div class="thumbnail">
									<button class="btn btn-danger"
										ng-click="removeBlockQuestion(rBlockQuestion,ListRootQuestionBlock)">
										<i class="fa fa-trash fa-lg" aria-hidden="true"></i>
									</button>
									<img ng-show="rBlockQuestion.isNew"
										style="float: right; float: right;" Alt="Mới"
										src=<c:url value="/resources/images/new/Icon_new.gif"/> />
                                </div>
								<input type="text" class="form-control"
									ng-model="rBlockQuestion.content" disabled />
								<hr>
								<div ng-repeat="rootQuestion in rBlockQuestion.ListRootQuestion">
									<div style="padding: 5px 0px 0px">
										<div class="input-group">
											<input type="text" class="form-control"
												ng-model="rootQuestion.content" disabled /> <span
												class="input-group-addon"
												style="color: rgb(255, 255, 255); background-color: rgb(217, 83, 79); border-color: rgb(212, 63, 58);">
												<i class="fa fa-times" aria-hidden="true"
												ng-click="removeQuestion(rootQuestion,rBlockQuestion.ListRootQuestion)"></i>
											</span>
										</div>
										<div ng-repeat="rootAnswer in rootQuestion.ListRootAnswer">
											<!-- <div class="btn-group" data-toggle="buttons"
												style="padding: 5px 0px 0px"> -->

												<div class="input-group">
												<span class="input-group-addon">
												    <input type="checkbox" ng-model="rootAnswer.issolution" ng-change="updateSolutionAnswer(rootAnswer,rootQuestion.ListRootAnswer)"/>
											    </span>
													<input type="text" class="form-control"
														value={{rootAnswer.content}} ng-model="rootAnswer.content" />
														
													<span class="input-group-addon"
														style="color: rgb(255, 255, 255); background-color: rgb(217, 83, 79); border-color: rgb(212, 63, 58);">

														<i class="fa fa-times" aria-hidden="true"
														ng-click="removeAnswer(rootAnswer,rootQuestion.ListRootAnswer)" ng-model="contentAnswer" ng-model="isSolution"></i>
													</span>
												</div>

										</div>
										<div class="text-right">
											<button type="button" class="btn btn-sm btn-primary">
												<i class="fa fa-plus-square fa-2x" aria-hidden="true"
													ng-click="addAnswer(rootQuestion.ListRootAnswer, rBlockQuestion.ListRootQuestion)" ></i>
											</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					
				</div>
			</div>
			<div class="col-lg-6">
				<div class="panel panel-default">
					<div class="panel-heading"
						style="display: flex; justify-content: space-between;">
						<div class="form-group" style="flex-grow: 1">
							<label for="keyWord">Từ Khoá</label> <input type="search"
								class="form-control" id="keyWord" ng-model="searchText" placeholder="Nhập câu hỏi cần tìm..."
								/>
						</div>
						<div class="form-group" style="flex-grow: 1">
							<label for="keyWord">Chương</label> <select class="form-control"
								ng-model="selectedChapter"
								ng-options="chapter as chapter.name for chapter in chapterList"
								ng-change="onChangeSelectedChapter()" required>
							</select>
						</div>
					</div>
					<div class="panel-body" ng-drop="true" style="padding-bottom: 0">
						<div ng-repeat="blockQuestion in blockQuestionListSearch |filter:searchText ">
							<div class="thumbnail" ng-drag="true"
								ng-drag-data="blockQuestion" data-allow-transform="true"
								id="fix_size">
								<div class="thumbnail">
									<button class="btn btn-sm btn-primary"
										ng-click="addBlockQuestion(blockQuestion,blockQuestionListSearch)">
										Thêm</button>
									{{blockQuestion.content}}
								</div>

								<!-- <div ng-show="!!blockQuestion.content">
				                	<div ng-model="blockQuestion.content"></div>
				                </div> -->
								<div ng-repeat="question in blockQuestion.ListRootQuestion |filter:searchText ">
									<div style="padding: 5px 0px 0px">
										<div class="input-group">
											<span class="input-group-addon"
												style="color: rgb(255, 255, 255); background-color: rgb(57, 179, 215); border-color: rgb(38, 154, 188);">
												<i class="glyphicon glyphicon-plus"
												ng-click="addQuestion(question,blockQuestion,blockQuestionListSearch)"></i>
											</span> <input class="form-control" ng-model="question.content"
												disable /> <span class="input-group-addon"> <i
												ng-click="showDetails = ! showDetails"
												class="fa fa-arrow-circle-down fa-lg" aria-hidden="true"></i>
											</span>
										</div>
										<div ng-repeat="answer in question.ListRootAnswer"
											ng-show="showDetails">
											<div style="padding: 0px 35px 0px";>ĐA {{$index +1}} :
												{{answer.content}}</div>
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