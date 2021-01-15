<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<div id="question" class="container navbar-default">
	<div class="col-lg-2">
		<div class="sidebar-nav-fixed affix">
			<div class="well">
				<ul class="nav">
					<li class="panel-header"><strong>NỘI DUNG</strong></li>
					<li class="active"><a href="#">Nhập Câu Hỏi</a></li>
					<li><a href="#pasterLatex">Dán Latex</a></li>

				</ul>
			</div>
		</div>
	</div>
	<div class="col-lg-10">

		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default"<%-- ng-show="showManual"--%>>
					<div class="panel-heading">
						<div>
							<strong>NHẬP CÂU HỎI</strong>
						</div>
					</div>

					<div class="panel-body">
						<div class="create_question" ng-controller="QuestionController">
							<div class="row carousel-holder">
								<div class=" col-lg-4 col-lg-offset-1">
									<div class="form-group">
										<label for="ExamID">Môn Học : </label> <select
											class="form-control" ng-model="subjectId"
											ng-options="subject.id as subject.name for subject in subjectList"
											ng-change="onChangeSelectedSubject()" required>
										</select>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-10 col-lg-offset-1">
									<div class="form-group input-group">
										<label for="questionblock">Câu hỏi nhóm:</label>
										<textarea ng-model="blockQuestion.content" rows="3" cols="200"
											placeholder="Nhập nội dung khối câu hỏi..."
											class="form-control">
                                             </textarea>

									</div>
								</div>
							</div>


							<div class="thumbnail" style="margin: 10px 20px"
								ng-repeat="question in blockQuestion.questionList">
								</br>
								<div class="row">
									<div class="col-lg-4 col-lg-offset-1">
										<div class="form-group">
											<label for="chaptersubjects">Chương:</label> <select
												class="form-control" ng-model="question.chapterid"
												ng-options="chapter.id as chapter.name for chapter in chapterList.chapterList"
												ng-change="onChangeSelectedChapter(question)" required>
											</select>
										</div>
									</div>
									<div class=" col-lg-4 col-lg-offset-2">
										<div class="form-group">
											<label>Mức Độ Khó:</label> <select class="form-control"
												name="level" ng-model="question.level">
												<option>1</option>
												<option>2</option>
												<option>3</option>
												<option>4</option>
												<option>5</option>
											</select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-4 col-lg-offset-1">
										<div class="form-group">
											<label for="target">Tiêu chí:</label> <select
												class="form-control" type="text"
												ng-model="question.selectedTarget"
												ng-options="target as target.content for target in question.targetList"
												placeholder="Chọn tiêu chí" required>
											</select>
										</div>
										<div class="form-group">
											<button class="btn btn-success form-control"
												ng-click="addTarget(question.selectedTarget, question.selectedTargetList)">
												Thêm tiêu chí</button>
										</div>
									</div>
									<div class="col-lg-4 col-lg-offset-2">
										<div class="form-group">
											<div ng-repeat="target in question.selectedTargetList"
												ng-click="removeTarget(target.id,  question.selectedTargetList)"
												class="tag-input alert alert-info" style="white-space: nowrap !important; overflow-x: scroll  !important; overflow-y: hidden  !important; height:100%">
												<strong>{{$index+1}}:
													{{target.content }}</strong>
											</div>
										</div>
									</div>
								</div>

								<div class="row">
									<div class="col-lg-10 col-lg-offset-1">
										<div class="form-group">
											<label for="target">Câu hỏi đơn:</label>
											<textarea ng-model="question.content" class="form-control"
												placeholder="Nhập nội dung câu hỏi" row="3" cols="100"></textarea>

										</div>
									</div>
								</div>

								<div class="row">
									<div class="col-lg-10 col-lg-offset-1"
										ng-repeat="answer in question.answerList">
										<div class="form-group col-xs-11 col-lg-11">
											<label for="target">Đáp án</label> <input
												ng-model="answer.content" class="form-control" />

										</div>
										<div class="form-group col-xs-1 col-lg-1">
											<label for="target" stykle="text-align:center">Đúng</label> <input
												style="box-shadow: none" type="checkbox"
												class="form-control" ng-model="answer.issolution" />
										</div>
									</div>
									<div class="col-lg-10 col-lg-offset-1">
										<div class="form-group col-lg-10">
											<button class="btn btn-success form-control"
												ng-click="addAnswer(question.answerList)">Thêm
												đáp án</button>
										</div>
									</div>
								</div>
							</div>
							<br>
							<div class="row">
								<div class="col-lg-4 col-lg-offset-1">
									<div class="form-group">
										<button class="btn btn-primary form-control"
											ng-click="saveBlockQuestion()">Lưu
											câu hỏi</button>
									</div>
								</div>
								<div class="col-lg-4 col-lg-offset-2">
									<div class="form-group">
										<button class="btn btn-default form-control"
											ng-click="addQuestion()">Thêm
											câu hỏi đơn</button>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
		<div class="row" id="pasterLatex">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<strong>DÁN LATEX</strong>
					</div>
					<div class="panel-body">

						<form>
							<div>
								<div class="row">
									<div class=" col-lg-4 col-lg-offset-1">
										<div class="form-group">
											<label>Môn Học : </label> <select class="form-control"
												ng-model="subjectId"
												ng-options="subject.id as subject.name for subject in subjectList"
												ng-change="onChangeSelectedSubject()" required>
											</select>
										</div>
									</div>
								</div>
							</div>

							<!-- <div class="form-group input-group col-lg-10 col-lg-offset-1">
                                <input type="file" onchange="angular.element(this).scope().getQuestionFromLatex(this)"/>
                            </div> -->

							<div class="form-group input-group col-lg-10 col-lg-offset-1">
								<textarea class="form-control" rows="10" cols="50" type="text"
									name='latex' placeholder="Copy code Latex vào đây"
									ng-model="latexContent"></textarea>
							</div>

							<div>
								<div class="row">
									<div class=" col-lg-4 col-lg-offset-1">
										<div class="form-group">
											<button class="btn btn-primary form-control"
												ng-click="getQuestionFromLatex()">Tạo Đề Latex</button>
										</div>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


