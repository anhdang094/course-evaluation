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
						<div class="panel-heading">Các Môn Học </div>
			<div class="panel-body" style="padding-bottom:0;">	
				<c:forEach items="${subjects}" var="sub">
								
              <div class="col-sm-4 col-lg-4 col-md-4 ">
               <span><img src=<c:url value="/resources/images/new/new.gif"/> alt="new chapter" /></span> 
                  <div class="thumbnail"  id="fix_size">                
                    <a href="getsubjects/subjects${sub.id}">
                    <img src=<c:url value="/resources/images/subjects_icon/books-icon.png"/>
                    alt="subjects" />
                    </a>
                    <div class="caption">
                      <h5 class="text-center">${sub.subjectsname}</h5>                  
                    </div>
                    <div class="button-div">
                     <a href="getsubjects/subjects${sub.id}"> <button class="btn btn-info btn-block buttonseedetail" >Chi Tiết</button></a>
                      
                    </div>
                  </div>
                </div>               
		    </c:forEach>
			</div>			
			           </div>
			           </div>
          </div>
		</div>
	</div>
	</div>