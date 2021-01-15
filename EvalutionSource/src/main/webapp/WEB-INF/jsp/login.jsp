<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<title>Login</title>

<div class="container-fluid">
	<div id="page-login" class="row">
		<div class="col-lg-4 col-lg-offset-4">
			<div class="text-right" style="display: block">
				<a href="signupform" class="txt-default">Tạo một tài khoản?</a>
			</div>
			<div class="box">
				<div class="box-content">
					<form id="loginForm" class="loginbox" name="loginForm"
						action="j_spring_security_check" method="post">

						<div class="text-center">
							<h3 class="page-header">ĐĂNG NHẬP</h3>
						</div>
						<div class="form-group">
							<label class="control-label">Email </label> <input type="text"
								class="form-control" id="j_username"
								placeholder="nhập tên email ..." name="j_username" />
						</div>
						<div class="form-group">
							<label class="control-label">Mật khẩu </label> <input
								type="password" class="form-control" id="j_password"
								placeholder="nhập mật khẩu ..." name="j_password" />
						</div>
						<div class="text-center">
							<c:if test="${not empty param.authfailed}">
								<div class="error txtcenter" style="margin-bottom: 8px">
									Email hoặc Mật khẩu không phù hợp !</div>
								<div style="display: none;">
									Lỗi:
									<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
								</div>
							</c:if>
							<button class="btn btn-primary">ĐĂNG NHẬP</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>