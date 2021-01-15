<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>

<style>
  td, tr, thead{
  width : 30px;
  height : 50px;
  }
</style>

<div class="container navbar-default" >
 <div class="crex_form">
            <ul class="tab-group">
                  <li class="tab active"><a href="#create-exams">Tạo Đề Thi</a></li>
                  <li class="tab"><a href="#design-exams">Thiết Kế Mẫu Đề</a></li>
           </ul>
   <div class="tab-content">    
		<div id="create-exams" class="col-lg-12" ng-controller="CreateExamsController" >
			<div class="row carousel-holder">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div style="float: left"><h4>Tạo Đề Thi </h4></div>
							<div style="clear: both"></div>
						</div>
						<hr>
						<form id="cinfo_exam" ng-submit="onSubmitCreateExams()">
							<div class="row">
            					<div class="col-lg-3 col-lg-offset-1">
              						<div class="form-group input-group">
                 						<label >Nhập Tên Đề Thi :</label> 
                 						<input style ="border-radius: 10px" class="form-control" type="text" name='NameExam' placeholder="Nhập Tên Đề Thi" ng-model="examName" required/> 
            						</div>
            					</div> 
               					<div class="col-lg-3">
               						<div class="form-group input-group">
                 						<label >Môn Đã Chọn :</label> 
				 						<select
				 							class="form-control"
				 							style = "border-radius: 10px;"
									 		ng-model="selectedSubject"
											ng-options="subject as subject.name for subject in subjectList"
											ng-change="onChangeSelectedSubject()"
											required
								 		>
										</select>
                  					</div>        
               					</div>
             				</div>
           
             				<div class="row">
                				<div class=" col-lg-3 col-lg-offset-1">
                					<div class="form-group input-group">       
                 						   <label>Bắt Đầu: </label>
                                             <div class='input-group date' id='datetimepicker1'>
                                                <span class="input-group-addon">
                                                   <span class="glyphicon glyphicon-calendar"></span>
                                                </span>
                                                <input type='text' class="form-control" name='startTime' placeholder="Thời gian bắt đầu" min="{{now}}" ng-model="startTime" required />
                                             </div>
               						</div>
               					</div>
               					<div class=" col-lg-3" >
               						<div class="form-group input-group">
                						<label >Số Câu Hỏi: </label>
                  						<input class="form-control" style = "border-radius: 10px;" type="number" placeholder="Số câu" ng-model="questionCount" readonly/>             
                					</div>
               					</div>
             				</div>  
             				
             				<div class="row">
                				<div class=" col-lg-3 col-lg-offset-1" >
                					<div class="form-group input-group">               
                 						<label>Kêt Thúc: </label>
                  						<div class='input-group date' id='datetimepicker2'>
                                                <span class="input-group-addon">
                                                   <span class="glyphicon glyphicon-calendar"></span>
                                                </span>
                                                <input type='text' class="form-control" name='endTime' placeholder="Thời gian kết thúc" min={{startTime}} ng-model="endTime" required />
                                             </div>
               						</div>
               					</div>
                				<div class=" col-lg-3">
                					<div class="form-group input-group">               
                 						<label> Số Câu Trả Lời Tối Đa: </label>
                  						<input class="form-control" style = "border-radius: 10px" type="number" placeholder="Số Câu Hỏi Nhóm" ng-model="answerCount"/>
               						</div>
               					</div>  
               				</div> 
               				
               				<div class="row">
                				<div class=" col-lg-3 col-lg-offset-1">
                					<div class="form-group input-group">        
                  						<label >Tổng thời gian:(phút)</label>
                  						<input ng-disabled = "timeType != 'exams'" class="form-control" style = "border-radius: 10px;" type="number" name='time' placeholder="Thời gian" ng-model="time"/>      
               						</div>
               					</div>  
               					<div class=" col-lg-3">
               						<div class="form-group input-group">               
                 						<label>Số lượng đề : </label>
                  						<input class="form-control" style = "border-radius: 10px" type="number" name='examCount' placeholder="Số lượng đề" ng-model="examCount" required/>
               						</div>
               					</div>  
               				</div>
               				
               				<div class="row">
                				<div class=" col-lg-3 col-lg-offset-1">
                					<div class="form-group input-group">  
                						<label> Thời gian theo: </label>             
                  						<div class="radio">
                  						  <label class="radio-inline"><input type="radio" name="optradio" ng-model="timeType" value="exams">Bài thi</label>
										  <label class="radio-inline"><input type="radio" name="optradio" ng-model="timeType" value="question" ng-change="onChangeTimeType()">Câu hỏi</label>
										</div>
               						</div>
               					</div>
               					<div class=" col-lg-3">
               						<div class="form-group input-group">               
                 						<label> Số lần làm: </label>
                  						<input class="form-control" style = "border-radius: 10px; width:100%" type="number" name='times'  placeholder="Số lần làm" ng-model="times"/>
               						</div>
               					</div> 
               				</div>
							
							<div>
								<h4 class="text-center">Phân Bố Câu Hỏi</h4>
								
								<div class="list-group">
									<div class="list-group-item" ng-repeat="chapter in chapterList">
										<div  style="cursor:pointer" ng-click="onClickChapter(chapter)">
										{{chapter.name}}
										<span ng-show="!!chapter.selectedQuestionCount && chapter.selectedQuestionCount > 0" class="badge" style="background-color:#0046d3">
											{{chapter.selectedQuestionCount}}
										</span>
										<span class="badge" style="float:right; background-color:#781">
											{{chapter.questionCount || 0}}
											<i ng-hide="chapter.isShow" class="fa fa-arrow-circle-down fa-lg" aria-hidden="true"></i>
											<i ng-show="chapter.isShow" class="fa fa-arrow-circle-right fa-lg" aria-hidden="true"></i>
										</span>
										
										</div>
										<table ng-show="chapter.isShow" class="table table-bordered">
											<thead style="background: #7A5; color: aliceblue;">
												<tr>
													<th>Tiêu chí</td>
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
														<p>Số câu: {{target.level1Count + target.level2Count + target.level3Count + target.level4Count + target.level5Count}}</p>
														<p>Thời gian: {{target.totalTime || 0}} phút</p>
													</td>
													<td class="level1">
														<div style="width: 100%" class="form-inline">
															<input 
																class="text-center" type="number"
																style = "border-radius: 10px;"
																min="0" max="{{target.questionL1Count}}" 
																ng-model="target.level1Count"
																ng-change="onChangeQuestionCount(chapter)"
															/> / 
															<span>{{target.questionL1Count || 0}} câu</span>
														</div>
														<div ng-show="target.level1Count > 0" style="width: 100%" class="form-inline">
															<input 
																class="text-center" type="number" min="0" 
																style = "border-radius: 10px;"
																ng-model="target.level1Time"
																ng-change="onChangeTime(target)"
																placeholder="Giây"
															/>
															/ 
															<span>1 câu</span>
														</div>
													</td>
													<td class="level2">
														<div style="width: 100%" class="form-inline">
															<input 
																class="text-center" type="number"
																style = "border-radius: 10px;"
																min="0" max="{{target.questionL2Count}}" 
																ng-model="target.level2Count"
																ng-change="onChangeQuestionCount(chapter)"
															/> / 
															<span>{{target.questionL2Count || 0}} câu</span>
														</div>
														<div ng-show="target.level2Count > 0" style="width: 100%" class="form-inline">
															<input 
																class="text-center" type="number" min="0" 
																style = "border-radius: 10px;"
																ng-model="target.level2Time"
																ng-change="onChangeTime(target)"
																placeholder="Giây"
															/>
															/ 
															<span>1 câu</span>
														</div>
													</td>
													<td class="level3">
														<div style="width: 100%" class="form-inline">
															<input 
																class="text-center" type="number"
																style = "border-radius: 10px;"
																min="0" max="{{target.questionL3Count}}" 
																ng-model="target.level3Count"
																ng-change="onChangeQuestionCount(chapter)"
															/> / 
															<span>{{target.questionL3Count || 0}} câu</span>
														</div>
														<div ng-show="target.level3Count > 0" style="width: 100%" class="form-inline">
															<input 
																class="text-center" type="number" min="0" 
																style = "border-radius: 10px;"
																ng-model="target.level3Time"
																ng-change="onChangeTime(target)"
																placeholder="Giây"
															/>
															/ 
															<span>1 câu</span>
														</div>
													</td>
													<td class="level4">
														<div style="width: 100%" class="form-inline">
															<input 
																class="text-center" type="number"
																style = "border-radius: 10px;"
																min="0" max="{{target.questionL4Count}}" 
																ng-model="target.level4Count"
																ng-change="onChangeQuestionCount(chapter)"
															/> / 
															<span>{{target.questionL4Count || 0}} câu</span>
														</div>
														<div ng-show="target.level4Count > 0" style="width: 100%" class="form-inline">
															<input 
																class="text-center" type="number" min="0" 
																style = "border-radius: 10px;"
																ng-model="target.level4Time"
																ng-change="onChangeTime(target)"
																placeholder="Giây"
															/>
															/ 
															<span>1 câu</span>
														</div>
													</td>
													<td class="level5">
														<div style="width: 100%" class="form-inline">
															<input 
																class="text-center" type="number"
																style = "border-radius: 10px;"
																min="0" max="{{target.questionL5Count}}" 
																ng-model="target.level5Count"
																ng-change="onChangeQuestionCount(chapter)"
															/> / 
															<span>{{target.questionL5Count || 0}} câu</span>
														</div>
														<div ng-show="target.level5Count > 0" style="width: 100%" class="form-inline">
															<input 
																class="text-center" type="number" min="0" 
																style = "border-radius: 10px;"
																ng-model="target.level5Time"
																ng-change="onChangeTime(target)"
																placeholder="Giây"
															/>
															/ 
															<span>1 câu</span>
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
								<button style="width:90%" type='submit' class='btn btn-info' value='Submit'>Tạo
									Đề</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	    <div id="design-exams">
	      <div class="row carousel-holder">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div style="float: left"><h4>Thiết Kế Mẫu Đề </h4></div>
							<div style="clear: both"></div>
						</div>
						</div>
						</div>
						</div>
	    </div>
	 </div><!-- tab-content -->
  </div><!-- form -->
</div>
