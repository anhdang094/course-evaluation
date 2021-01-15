<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<div class="container navbar-default" id="createExam">
	<div class="col-lg-2">
		<div class="sidebar-nav-fixed affix">
			<div class="well">
				<ul class="nav">
					<li class="panel-header"><strong>NỘI DUNG</strong></li>
					<li class="active"><a href="#">Tạo Đề Online</a></li>
					<li><a href="#formExam">Tạo Đề Mẫu</a></li>
					<li><a href="#storeExam">Kho Đề</a></li>
				</ul>
			</div>
		</div>
	</div>

	<div class="col-lg-10">

		<!-----------CREATE EXAM------- -->
		<div class="row">
			<div class="panel panel-default default">
				<div class="panel-heading">
					<strong>TẠO ĐỀ THI ONLINE</strong>
				</div>

				<div class="panel-body">
					<div id="create-exams" class="col-lg-12"
						ng-controller="CreateExamsController">
						<div class="row carousel-holder">
							<div class="col-lg-12">
								<div class="panel panel-default">

									<form id="cinfo_exam" ng-submit="onSubmitCreateExams()">
										<div class="row">
											<div class="col-lg-3 col-lg-offset-1">
												<div class="form-group input-group">
													<label>Nhập Tên Đề Thi :</label> <input
														style="border-radius: 10px" class="form-control"
														type="text" name='NameExam' placeholder="Nhập Tên Đề Thi"
														ng-model="examName" required />
												</div>
											</div>
											<div class="col-lg-3">
												<div class="form-group input-group">
													<label>Môn Đã Chọn :</label> <select class="form-control"
														style="border-radius: 10px;" ng-model="selectedSubject"
														ng-options="subject as subject.name for subject in subjectList"
														ng-change="onChangeSelectedSubject()" required>
													</select>
												</div>
											</div>
										</div>

										<div class="row">
											<div class=" col-lg-3 col-lg-offset-1">
												<div class="form-group input-group">
													<label>Bắt Đầu: </label>
													<div class='input-group date' id='datetimepicker1'>
														<span class="input-group-addon"> <span
															class="glyphicon glyphicon-calendar"></span>
														</span> <input type='text' class="form-control" name='startTime'
															placeholder="Thời gian bắt đầu" min="{{now}}"
															ng-model="startTime" required />
													</div>
												</div>
											</div>
											<div class=" col-lg-3">
												<div class="form-group input-group">
													<label>Số Câu Hỏi: </label> <input class="form-control"
														style="border-radius: 10px;" type="number"
														placeholder="Số câu" ng-model="questionCount" readonly />
												</div>
											</div>
										</div>

										<div class="row">
											<div class=" col-lg-3 col-lg-offset-1">
												<div class="form-group input-group">
													<label>Kêt Thúc: </label>
													<div class='input-group date' id='datetimepicker2'>
														<span class="input-group-addon"> <span
															class="glyphicon glyphicon-calendar"></span>
														</span> <input type='text' class="form-control" name='endTime'
															placeholder="Thời gian kết thúc" min={{startTime}}
															ng-model="endTime" required />
													</div>
												</div>
											</div>
											<div class=" col-lg-3">
												<div class="form-group input-group">
													<label> Số Câu Trả Lời Tối Đa: </label> <input
														class="form-control" style="border-radius: 10px"
														type="number" placeholder="Số Câu Hỏi Nhóm"
														ng-model="answerCount" />
												</div>
											</div>
										</div>

										<div class="row">
											<div class=" col-lg-3 col-lg-offset-1">
												<div class="form-group input-group">
													<label>Tổng thời gian:(phút)</label> <input
														ng-disabled="timeType != 'exams'" class="form-control"
														style="border-radius: 10px;" type="number" name='time'
														placeholder="Thời gian" ng-model="time" />
												</div>
											</div>
											<div class=" col-lg-3">
												<div class="form-group input-group">
													<label>Số lượng đề : </label> <input class="form-control"
														style="border-radius: 10px" type="number" name='examCount'
														placeholder="Số lượng đề" ng-model="examCount" required />
												</div>
											</div>
										</div>

										<div class="row">
											<div class=" col-lg-3 col-lg-offset-1">
												<div class="form-group input-group">
													<label> Thời gian theo: </label>
													<div class="radio">
														<label class="radio-inline"><input type="radio"
															name="optradio" ng-model="timeType" value="exams">Bài
															thi</label> <label class="radio-inline"><input
															type="radio" name="optradio" ng-model="timeType"
															value="question" ng-change="onChangeTimeType()">Câu
															hỏi</label>
													</div>
												</div>
											</div>
											<div class=" col-lg-3">
												<div class="form-group input-group">
													<label> Số lần làm: </label> <input class="form-control"
														style="border-radius: 10px; width: 100%" type="number"
														name='times' placeholder="Số lần làm" ng-model="times" />
												</div>
											</div>
										</div>

										<div>
											<h4 class="text-center">Phân Bố Câu Hỏi</h4>

											<div class="list-group">
												<div class="list-group-item"
													ng-repeat="chapter in chapterList">
													<div style="cursor: pointer"
														ng-click="onClickChapter(chapter)">
														{{chapter.name}} <span
															ng-show="!!chapter.selectedQuestionCount && chapter.selectedQuestionCount > 0"
															class="badge" style="background-color: #0046d3">
															{{chapter.selectedQuestionCount}} </span> <span class="badge"
															style="float: right; background-color: #781">
															{{chapter.questionCount || 0}} <i
															ng-hide="chapter.isShow"
															class="fa fa-arrow-circle-down fa-lg" aria-hidden="true"></i>
															<i ng-show="chapter.isShow"
															class="fa fa-arrow-circle-right fa-lg" aria-hidden="true"></i>
														</span>

													</div>
													<table ng-show="chapter.isShow"
														class="table table-bordered">
														<thead style="background: #7A5; color: aliceblue;">
															<tr>
																<th>Tiêu chí
																</td>
																<th class="text-align question-count">Tổng</th>
																<th class="text-center text-align level1">Level 1</th>
																<th class="text-center text-align level2">Level 2</th>
																<th class="text-center text-align level3">Level 3</th>
																<th class="text-center text-align level4">Level 4</th>
																<th class="text-center text-align level5">Level 5</th>
															</tr>
														</thead>
														<tbody>
															<tr ng-repeat="target in chapter.targetList">
																<td>{{target.content}}</td>
																<td>
																	<p>Số câu: {{target.level1Count +
																		target.level2Count + target.level3Count +
																		target.level4Count + target.level5Count}}</p>
																	<p>Thời gian: {{target.totalTime || 0}} phút</p>
																</td>
																<td class="level1">
																	<div style="width: 100%" class="form-inline">
																		<input class="text-center" type="number"
																			style="border-radius: 10px;" min="0"
																			max="{{target.questionL1Count}}"
																			ng-model="target.level1Count"
																			ng-change="onChangeQuestionCount(chapter)" /> / <span>{{target.questionL1Count
																			|| 0}} câu</span>
																	</div>
																	<div ng-show="target.level1Count > 0"
																		style="width: 100%" class="form-inline">
																		<input class="text-center" type="number" min="0"
																			style="border-radius: 10px;"
																			ng-model="target.level1Time"
																			ng-change="onChangeTime(target)" placeholder="Giây" />
																		/ <span>1 câu</span>
																	</div>
																</td>
																<td class="level2">
																	<div style="width: 100%" class="form-inline">
																		<input class="text-center" type="number"
																			style="border-radius: 10px;" min="0"
																			max="{{target.questionL2Count}}"
																			ng-model="target.level2Count"
																			ng-change="onChangeQuestionCount(chapter)" /> / <span>{{target.questionL2Count
																			|| 0}} câu</span>
																	</div>
																	<div ng-show="target.level2Count > 0"
																		style="width: 100%" class="form-inline">
																		<input class="text-center" type="number" min="0"
																			style="border-radius: 10px;"
																			ng-model="target.level2Time"
																			ng-change="onChangeTime(target)" placeholder="Giây" />
																		/ <span>1 câu</span>
																	</div>
																</td>
																<td class="level3">
																	<div style="width: 100%" class="form-inline">
																		<input class="text-center" type="number"
																			style="border-radius: 10px;" min="0"
																			max="{{target.questionL3Count}}"
																			ng-model="target.level3Count"
																			ng-change="onChangeQuestionCount(chapter)" /> / <span>{{target.questionL3Count
																			|| 0}} câu</span>
																	</div>
																	<div ng-show="target.level3Count > 0"
																		style="width: 100%" class="form-inline">
																		<input class="text-center" type="number" min="0"
																			style="border-radius: 10px;"
																			ng-model="target.level3Time"
																			ng-change="onChangeTime(target)" placeholder="Giây" />
																		/ <span>1 câu</span>
																	</div>
																</td>
																<td class="level4">
																	<div style="width: 100%" class="form-inline">
																		<input class="text-center" type="number"
																			style="border-radius: 10px;" min="0"
																			max="{{target.questionL4Count}}"
																			ng-model="target.level4Count"
																			ng-change="onChangeQuestionCount(chapter)" /> / <span>{{target.questionL4Count
																			|| 0}} câu</span>
																	</div>
																	<div ng-show="target.level4Count > 0"
																		style="width: 100%" class="form-inline">
																		<input class="text-center" type="number" min="0"
																			style="border-radius: 10px;"
																			ng-model="target.level4Time"
																			ng-change="onChangeTime(target)" placeholder="Giây" />
																		/ <span>1 câu</span>
																	</div>
																</td>
																<td class="level5">
																	<div style="width: 100%" class="form-inline">
																		<input class="text-center" type="number"
																			style="border-radius: 10px;" min="0"
																			max="{{target.questionL5Count}}"
																			ng-model="target.level5Count"
																			ng-change="onChangeQuestionCount(chapter)" /> / <span>{{target.questionL5Count
																			|| 0}} câu</span>
																	</div>
																	<div ng-show="target.level5Count > 0"
																		style="width: 100%" class="form-inline">
																		<input class="text-center" type="number" min="0"
																			style="border-radius: 10px;"
																			ng-model="target.level5Time"
																			ng-change="onChangeTime(target)" placeholder="Giây" />
																		/ <span>1 câu</span>
																	</div>
																</td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>

										<hr>
										<div class="form-group input-group col-lg-12 text-center">
											<button style="width: 90%" type='submit' class='btn btn-info'
												value='Submit'>Tạo Đề</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- End ROW1 -->

		<!-------------- CREATE DESIGN FORM ------------- -->
		<div class="row" id="formExam">
			<div class="panel panel-default default">
				<div class="panel-heading">
					<strong>TẠO ĐỀ MẪU</strong>
				</div>
				<div class="panel-body">
					<div class="col-lg-12" ng-controller="CreateRootExamsController">
						<form id="root_exam" ng-submit="onSubmitCreateRootExams()">
							<div class="row">
								<div class="col-lg-4 col-lg-offset-1">
									<div class="form-group input-group">
										<label>Tiêu Đề :</label> <input style="border-radius: 10px"
											class="form-control" type="text" name='nameRootExam'
											placeholder="Nhập Tên Đề Thi" ng-model="examRootName"
											required />
									</div>
								</div>
								<div class="col-lg-4">
									<div class="form-group input-group">
										<label>Môn Học :</label> <select class="form-control"
											style="border-radius: 10px;" ng-model="selectedSubject"
											ng-options="subject as subject.name for subject in subjectList"
											ng-change="onChangeSelectedSubject()" required>

										</select>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-3 col-lg-offset-1 form-group">
									<label>Thời gian :</label> <input class="form-control"
										type="number" value="timeRootExam" ng-model="timeRootExam" />
								</div>
								<div class="col-lg-3 col-lg-offset-1 form-group">
									<label>Số Câu :</label> <input class="form-control"
										type="number" ng-model="questionCount" />
								</div>
							</div>
							<div class="row">
								<div class="col-lg-10 col-lg-offset-1">
									<label>Ghi Chú:</label>
									<textarea style="border-radius: 10px" class="form-control"
										rows="4" ng-model="RnoteExam">
             		                </textarea>
								</div>
							</div>
							<div>
								</hr>
								<div class="row">
									<div class="text-center col-lg-12">
										<strong>Phân Bố Câu Hỏi</strong>
									</div>
								</div>

								<div class="list-group">
									<div class="list-group-item" ng-repeat="chapter in chapterList">
										<div style="cursor: pointer"
											ng-click="onClickChapter(chapter)">
											{{chapter.name}} <span
												ng-show="!!chapter.selectedQuestionCount && chapter.selectedQuestionCount > 0"
												class="badge" style="background-color: #5bc0de">
												{{chapter.selectedQuestionCount}} </span> <span class="badge"
												style="float: right; background-color: #2e6da4">
												{{chapter.questionCount || 0}} <i ng-hide="chapter.isShow"
												class="fa fa-arrow-circle-down fa-lg" aria-hidden="true"></i>
												<i ng-show="chapter.isShow"
												class="fa fa-arrow-circle-right fa-lg" aria-hidden="true"></i>
											</span>

										</div>
										<table ng-show="chapter.isShow" class="table table-bordered">
											<thead style="background-color: #5bc0de;">
												<tr>
													<th>Tiêu chí
													</td>
													<th class="text-align question-count">Tổng</th>
													<th class="text-center text-align level1">Level 1</th>
													<th class="text-center text-align level2">Level 2</th>
													<th class="text-center text-align level3">Level 3</th>
													<th class="text-center text-align level4">Level 4</th>
													<th class="text-center text-align level5">Level 5</th>
												</tr>
											</thead>
											<tbody>
												<tr ng-repeat="target in chapter.targetList">
													<td>{{target.content}}</td>
													<td>
														<p>Số câu: {{target.level1Count + target.level2Count +
															target.level3Count + target.level4Count +
															target.level5Count}}</p>

													</td>
													<td class="level1">
														<div style="width: 100%" class="form-inline">
															<input class="text-center" type="number"
																style="border-radius: 10px;" min="0"
																max="{{target.questionL1Count}}"
																ng-model="target.level1Count"
																ng-change="onChangeQuestionCount(chapter)" /> / <span>{{target.questionL1Count
																|| 0}} câu</span>
														</div>

													</td>
													<td class="level2">
														<div style="width: 100%" class="form-inline">
															<input class="text-center" type="number"
																style="border-radius: 10px;" min="0"
																max="{{target.questionL2Count}}"
																ng-model="target.level2Count"
																ng-change="onChangeQuestionCount(chapter)" /> / <span>{{target.questionL2Count
																|| 0}} câu</span>
														</div>

													</td>
													<td class="level3">
														<div style="width: 100%" class="form-inline">
															<input class="text-center" type="number"
																style="border-radius: 10px;" min="0"
																max="{{target.questionL3Count}}"
																ng-model="target.level3Count"
																ng-change="onChangeQuestionCount(chapter)" /> / <span>{{target.questionL3Count
																|| 0}} câu</span>
														</div>

													</td>
													<td class="level4">
														<div style="width: 100%" class="form-inline">
															<input class="text-center" type="number"
																style="border-radius: 10px;" min="0"
																max="{{target.questionL4Count}}"
																ng-model="target.level4Count"
																ng-change="onChangeQuestionCount(chapter)" /> / <span>{{target.questionL4Count
																|| 0}} câu</span>
														</div>

													</td>
													<td class="level5">
														<div style="width: 100%" class="form-inline">
															<input class="text-center" type="number"
																style="border-radius: 10px;" min="0"
																max="{{target.questionL5Count}}"
																ng-model="target.level5Count"
																ng-change="onChangeQuestionCount(chapter)" /> / <span>{{target.questionL5Count
																|| 0}} câu</span>
														</div>

													</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>

							</br>
							<div class="row">
								<div class="form-group input-group col-lg-12 text-center">
									<button style="width: 90%" type='submit' class='btn btn-info'
										value='Submit'>Tạo Đề</button>
								</div>
							</div>
						</form>

					</div>
				</div>
			</div>
		</div>

		<!-------------- STORE EXAMs ------------- -->
		<div class="row" id="storeExam">
			<div class="panel panel-default default"
				ng-controller="StoresController">
				<div class="panel-heading">
					<strong>KHO ĐỀ</strong>
				</div>
				<div class="panel-body">
					<div class="col-lg-10 col-lg-offset-1">
						<div class="input-group">
							<input class="form-control" type="text"
								placeholder="Nhập đề thi cần tìm... " ng-model="searchText" required />

							<span class="input-group-btn">
								<button class="btn btn-primary">Tìm Kiếm</button>
							</span>
						</div>
					</div>
					<!--BODY -->

					</br>
					<div class="col-lg-12"
						ng-repeat="listAllSubject in ListAllSubject ">
						<hr>
						<div class="row">
							<div class="panel panel-default">
								<div class="panel-heading" ng-model="listAllSubject.name">
									<label>MÔN HỌC : </label><span>{{listAllSubject.name}}</span>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-4"
								ng-repeat="listExam in listAllSubject.ListExams | filter:searchText">
								<div class="panel panel-default">
									<div class="panel-heading text-left" ng-model="listExam.name">{{listExam.name}}</div>
									<div class="panel-body text-center"
										style="border-radius: 10px;">
										<img src=<c:url value="/resources/images/exam/ex_exam.png"/>
											alt="Exam" width="100px" height="100px" />
									</div>
									<div class="panel-footer text-center">
										<a href="Rootexams/{{listExam.id}}/view">Xem thêm</a>
									</div>
								</div>
				
							</div>
							
						</div>
                        
                       {{ListAllSubject.ListExams.length}}
						<div
							ng-show="(listAllSubject.ListExams|filter:searchText).length === 0">
							<div class="alert alert-warning" role="alert">
								<strong>Không tìm thấy đề thi !</strong>
							</div>
						</div>

						<br> <br>
				</div>
				
			  </div>



			</div>
		</div>
	</div>
	<!-- END Col-12 -->
</div>
</div>