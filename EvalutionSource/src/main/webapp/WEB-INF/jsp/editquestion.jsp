<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>

<div class="container navbar-default">
	<div class='row'>
		<div class="col-lg-3 content-categories-panel">
			<div class="panel panel-default">
				<div class="panel-heading">Vùng Làm Việc</div>
				<ul class="nav nav-stacked" id="accordion1">

					<li class="panel"><a data-toggle="collapse"
						data-parent="#accordion1" href="#firsthLink">Trang Chủ </a>

						<ul id="firsthLink" class="collapse">
							<li><a href="#firsthLink2">Trang chủ</a></li>

						</ul></li>

					<li class="panel"><a data-toggle="collapse"
						data-parent="#accordion1" href="#secondthLink">Đề thi</a>
						<ul id="secondthLink" class="collapse">

							<li><a href="#secondthLink2">Giữa Kì</a></li>
							<li><a href="#secondthLink2">Cuối Kì</a></li>
							<li><a href="#secondthLink2">Quiz hoặc Test</a></li>
						</ul></li>

					<li class="panel"><a data-toggle="collapse"
						data-parent="#accordion1" href="#thirdthLink">Tùy Chọn</a>

						<ul id="thirdthLink" class="collapse">
							<li><a href="#thirdthLink2">Nhập Câu hỏi </a></li>
							<li><a href="#thirdthLink2">Import File latex</a></li>
							<li><a href="#thirdthLink2">Dán code Latex</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
		<div class="col-lg-9">
			<div class="row carousel-holder">
				<div class="col-lg-12">
					<div class="panel panel-default" id="firstLink2">
						<div class="panel-heading">Chỉnh Sửa Câu Hỏi</div>

						<form class="form-horizontal" action="" method="POST">
							<div class="form-group">
								<label class="col-lg-3 control-label">Mã Đề:</label>
								<div class="col-lg-8">
									<input class="form-control" type="text" name="examid"
										value="${editQuestion.examid}"/>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-lg-3 control-label">Level:</label>
								<div class="col-lg-8">
									<select class="form-control" name="levelq" value="${editQuestion.levelq}">
										<option>1</option>
										<option>2</option>
										<option>3</option>
										<option>4</option>
										<option>5</option>
									</select>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-lg-3 control-label">Môn Học:</label>
								<div class="col-lg-8">
									<input class="form-control" type="text" name="subjects" value="${editQuestion.subjects}"/>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-lg-3 control-label">Chương:</label>
								<div class="col-lg-8">
									<input class="form-control" type="number" name="chaptersubjects"
										value="${editQuestion.chaptersubjects}"/>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-lg-3 control-label">Tên Câu Hỏi:</label>
								<div class="col-lg-8">
									<input class="form-control" type="text" name="questionname"
										value="${editQuestion.questionname}"/>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-lg-3 control-label">Thuộc Khối Câu:</label>
								<div class="col-lg-8">
									<input class="form-control" type="text" name="qparentId"
										value="${editQuestion.qparentId}"/>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-3 control-label">Đáp án A:</label>
								<div class="col-md-8">
									<input class="form-control" type="text" name="answera" value="${editQuestion.answera}"/>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-3 control-label">Đáp án B:</label>
								<div class="col-md-8">
									<input class="form-control" type="text" name="answerb" value="${editQuestion.answerb}"/>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-3 control-label">Đáp án C:</label>
								<div class="col-md-8">
									<input class="form-control" type="text" name="answerc" value="${editQuestion.answerc}"/>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-3 control-label">Đáp án D:</label>
								<div class="col-md-8">
									<input class="form-control" type="text" name="answerd" value="${editQuestion.answerd}"/>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-3 control-label">Đáp án E:</label>
								<div class="col-md-8">
									<input class="form-control" type="text" name="answere" value="${editQuestion.answere}"/>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-3 control-label"></label>
								<div class="col-md-8">
									<input type="submit" class="btn btn-primary"
										value="Save Changes"> <span></span> <input
										type="reset" class="btn btn-default" value="Cancel">
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>