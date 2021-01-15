<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>

<div id="subject" class="container navbar-default" ng-controller="SubjectController">
	<div class="row">
		<div class="col-xs-3 content-categories-panel">
			<div class="panel panel-default">
				<div class="panel-heading">Vùng Làm Việc</div>
				<ul class="nav nav-stacked" id="accordion1">
					<li class="panel">
						<a data-toggle="collapse" data-parent="#accordion1" href="#firsthLink">Trang Chủ </a>
						<ul id="firsthLink" class="collapse">
							<li><a href="#firsthLink2">Trang chủ</a></li>
						</ul>
					</li>
	
					<li class="panel">
						<a data-toggle="collapse" data-parent="#accordion1" href="#secondthLink">Đề thi</a>
						<ul id="secondthLink" class="collapse">
							<li><a href="#secondthLink2">Giữa Kì</a></li>
							<li><a href="#secondthLink2">Cuối Kì</a></li>
							<li><a href="#secondthLink2">Quiz hoặc Test</a></li>
						</ul>
					</li>
	
					<li class="panel">
						<a data-toggle="collapse" data-parent="#accordion1" href="#thirdthLink">Tùy Chọn</a>
						<ul id="thirdthLink" class="collapse">
							<li><a href="#thirdthLink2">Nhập Câu hỏi </a></li>
							<li><a href="#thirdthLink2">Import File latex</a></li>
							<li><a href="#thirdthLink2">Dán code Latex</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	
	<div class="col-xs-9">
		<div class="row carousel-holder">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">{{subjectName}}</div>
					<div ng-show="isSuperTeacherOfSubject">
						<form ng-submit="createChapter()" class="form-inline">
							<h4>Create Subject</h4>
						    <div class="form-inline">
							 	<input type="text" class="form-control" ng-model="chapterName"/>
							 	<input type="submit" placeholder="Subject Name" class="btn btn-default" value="Create" />
							</div>
				 		</form>
			 		</div>
					<div class="panel-body" style="padding-bottom:0;">
						<h4>Đây Là Các Chương Đã Tạo</h4>		
	              		<div class="col-sm-4 col-lg-4 col-md-4" ng-repeat="chapter in chapterList">
	               			<!-- <span><img src=<c:url value="/resources/images/new/new.gif"/> alt="new chapter" /></span> -->              
	                  		<div class="thumbnail" >  
	                   			<a href="subject/{{chapter.subjectId}}/chapter/{{chapter.id}}"> 
	                   				<img src=<c:url value="/resources/images/logo_chapter/grade.jpg"/> alt="chapter" />
	                    		</a>
	                    		<div class="caption">
	                    			<div class="col-lg-12">
					                    <h5 class="text-center">{{chapter.name.substring(0,20)}}...</h5> 
	                    			</div>                 
	                    		</div>
			                    <div class="button-div">
			                    	<a href="subject/{{chapter.subjectId}}/chapter/{{chapter.id}}"> 
			                    		<button class="btn btn-info btn-block buttonseedetail">Chi Tiết</button>
			                      	</a>
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