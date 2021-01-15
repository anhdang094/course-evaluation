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
			<div>
				<h3>Các Câu Hỏi</h3>
			</div>
			<form class="form-horizontal">

				<fieldset>
					<c:forEach items="${question2}" var="question">
						<form:form class="form-horizontal">

							<fieldset>
								<div class='row col-lg-12 col-lg-offset-1'>
									<legend>Câu ${question.questionid}:</legend>

									<p>${question.questionname}</p>
								</div>
								<div class="row">
									<c:if test="${not empty question.answera}">
										<div class='col-lg-4 col-lg-offset-2'>
											<input type="checkbox" value="" name="answera" /> A:
											${question.answera}
										</div>
									</c:if>
									<c:if test="${not empty question.answerb}">
										<div class='col-lg-4 col-lg-offset-2'>
											<input type="checkbox" value="" name="answerb" /> B:
											${question.answerb}
										</div>
									</c:if>
								</div>
                                <br />
								<div class="row">
								<c:if test="${not empty question.answerc}">
									<div class='col-lg-4 col-lg-offset-2'>
										<input type="checkbox" value="" name="answerc" /> C:
										${question.answerc}
									</div>
									</c:if>
							   	<c:if test="${not empty question.answerd}">	
									<div class='col-lg-4 col-lg-offset-2'>
										<input type="checkbox" value="" name="answerd" /> D:
										${question.answerd}
									</div>
								</c:if>
								</div>
								<br />
								<div class="row">
								<c:if test="${not empty question.answere}">
									<div class='col-lg-4 col-lg-offset-2'>
										<input type="checkbox" value="" name="answerd" /> E:
										${question.answere}
									</div>
								</c:if>
									
								</div>
                              <br />
								<div class="row">
									<div class="col-lg-4 col-lg-offset-2">
										<a href="question/${question.questionid}/edit"
											class="btn btn-info" role="button">Edit</a> <a
											href="question/${question.questionid}/delete"
											class="btn btn-danger" role="button">Delete</a>
									</div>
								</div>
                              <br />
                              <br />
							</fieldset>
						</form:form>
					</c:forEach>

				</fieldset>
			</form>

		</div>
	</div>
</div>