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
					<div class="panel panel-default">		
			<div>
				<div class="panel-heading">${subjects.subjectsname}</div>
				<span class="text-center"><h4> ${chapter.chaptername} </h4></span>
			</div>
			<form class="form-horizontal">

				<c:set var="count" value="1" scope="page" />
				<div class="panel-body" style="padding-bottom:0;">
					<c:forEach items="${question}" var="ques">
					
								<div class='row col-lg-11 col-lg-offset-1'>
								
								
								
								<c:if test="${not empty ques.questionblockId}">
								
								<p>ID Nhóm câu : ${ques.questionblockId}</p>
								
								</c:if>		
														 
								 Câu <c:out value="${count}" />:
									<p>${ques.questionname}</p>
								</div>
								<div class="row">
									<c:if test="${not empty ques.answera}">
										<div class='col-lg-10 col-lg-offset-2'>
									 A:	${ques.answera}
										</div>								
									</c:if>
								</div>
								<div class="row">	
									<c:if test="${not empty ques.answerb}">
										<div class='col-lg-10 col-lg-offset-2'>
									 B: ${ques.answerb}
										</div>
									</c:if>
								</div>
                               
								<div class="row">
								<c:if test="${not empty ques.answerc}">
									<div class='col-lg-10 col-lg-offset-2'>
									C: ${ques.answerc}
									</div>
									</c:if>
							    </div>
							    <div class="row">
							   	<c:if test="${not empty ques.answerd}">	
									<div class='col-lg-10 col-lg-offset-2'>
									D: ${ques.answerd}
									</div>
								</c:if>
								</div>
								
								<div class="row">
								<c:if test="${not empty ques.answere}">
									<div class='col-lg-10 col-lg-offset-2'>
							       E: ${ques.answere}
									</div>
								</c:if>
									
								</div>
                              <br />
								<div class="row">
									<div class="col-lg-4 col-lg-offset-2">
										<a href="chapter${ques.chapterId}/question${ques.id}/edit"
											class="btn btn-info" role="button">	
											Edit
											</a>
											 <a
											href="chapter${ques.chapterId}/question${ques.id}/delete"
											class="btn btn-danger" role="button">
											Delete
											</a>
									</div>
								</div>
                                                    
                              <c:set var="count" value="${count + 1}" scope="page"/>
                              <hr>	
					</c:forEach>
                 </div>
			
			</form>
</div>	
		</div>
	</div>
</div>
</div>
</div>