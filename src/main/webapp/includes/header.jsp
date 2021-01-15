<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<%@page session="true"%>
<nav class="navbar navbar-fixed-top" id="customer_header"
	role="navigation">

	<div class="row">
		<div
			class="col-lg-1 col-lg-1 col-md-1 col-sm-1 col-xs-1 
			            col-lg-offset-2 col-md-offset-2 col-sm-offset-2 col-xs-offset-2"
			id="imagelogo">
			<img onclick="window.location.href='home'"
				src=<c:url value="/resources/images/logo/logo_bk.png"/> alt="LogoBK"
				 />
		</div>

		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#example-navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>

			<a class="navbar-brand" href="<c:url value="/home"/>">HỆ THỐNG RA
				ĐỀ, ĐÁNH GIÁ VÀ THỰC HÀNH MÔN HỌC</a>
		</div>

	</div>

	<div class="row">
		<div
			class="col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2 col-xs-8 col-sm-offset-2"
			id="header_menu">
			<div class="collapse navbar-collapse" id="example-navbar-collapse">
			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<ul class="nav navbar-nav">
					
						<c:if test="${pageContext.request.isUserInRole('admin')}">
							<li><a href="<c:url value="/admin"/>" class="menuLink">TRANG
									CHỦ </a></li>
							<li><a href="<c:url value="/notification"/>" class="menuLink">ĐĂNG TIN </a></li>		
						</c:if>

						<!-- Checked Teacher user -->
						<c:if test="${pageContext.request.isUserInRole('teacher')}">
							<li><a href="<c:url value="/home"/>" class="menuLink">TRANG
									CHỦ </a></li>
							<li><a href="<c:url value="/subject"/>" class="menuLink">MÔN
									HỌC </a></li>
							<li><a href="<c:url value="/class"/>" class="menuLink">LỚP
									HỌC </a></li>
							<%-- <li class="dropdown">
								<a class="dropdown-toggle" data-toggle="dropdown" name="dropdown-question">
									THÊM CÂU HỎI
									<span class="caret"></span></a>
								<ul class="dropdown-menu" id="question_dropList">
									<li><a href="<c:url value="/create-question"/>">
										<span class="glyphicon glyphicon-plus"></span> Thêm mới </a></li>
									<li><a href="<c:url value="/edit-question"/>"><span
										class="glyphicon glyphicon-pencil"></span> Chỉnh sửa</a></li>
								</ul> --%>
							<li>	
								<a href="<c:url value="/create-question"/>"
								class="menuLink">TẠO CÂU HỎI </a>
							</li>
							<li><a href="<c:url value="/create-exams"/>"
								class="menuLink">TẠO ĐỀ </a></li>
							<li><a href="<c:url value="/statistical"/>" class="menuLink">THỐNG
									KÊ </a></li>
							<li><a href="<c:url value="/evaluation"/>" class="menuLink">ĐÁNH
									GIÁ </a></li>
						</c:if>

						<!-- Checked Teacher user -->
						<c:if test="${pageContext.request.isUserInRole('student')}">
							<li><a href="<c:url value="/home"/>" class="menuLink">TRANG
									CHỦ </a></li>
							<li><a href="<c:url value="/subject"/>" class="menuLink">MÔN
									HỌC </a></li>
							<li><a href="<c:url value="/class"/>" class="menuLink">LỚP
									HỌC </a></li>
							<li><a href="<c:url value="/score"/>" class="menuLink">ĐIỂM</a></li>
							<li><a href="<c:url value="/discussion"/>" class="menuLink">THẢO
									LUẬN</a></li>
						</c:if>
						
				</ul>
				<ul class="nav navbar-nav navbar-right" id="icon_user"
					title="THÔNG TIN TÀI KHOẢN">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button"> <img
							src="<c:url value="/resources/images/logo/logoff.jpg"/>"
							alt="logoff" /> <span class="caret"></span>
					</a>
						<ul class="dropdown-menu" id="user_dropList">
							<li><a href="/profile"><span
									class="glyphicon glyphicon-user"></span> Tài Khoản </a></li>
							<li><a href="/setting"><span
									class="glyphicon glyphicon-cog"></span> Cài Đặt</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="<c:url value="/logout" />"><span
									class="glyphicon glyphicon-log-out"></span> Đăng Xuất</a></li>
						</ul>
						</li>
				</ul>
		
				
				</c:if>
				
				
			</div>


		</div>


	</div>
	
			
				

</nav>

