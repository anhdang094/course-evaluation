<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<%@page session="true"%>
<nav class="navbar navbar-default" id="header-nav">
  <div class="container-fluid col-lg-12">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/EvalutionSource/username/loginform"></a>
    </div>
  <div class="row">
   <div class="col-lg-1 col-lg-offset-2"><img src=<c:url value="/resources/images/logo/logo_bk.png"/> alt="Logo BK" height="45px" width="45px"/></div>
   <div class="navbar-brand col-lg-5"><p>HỆ THỐNG ĐÁNH GIÁ CHẤT LƯỢNG MÔN HỌC</p></div>
     <div class="navbar-right col-lg-4">
       <c:if test="${pageContext.request.userPrincipal.name != null}">
          <p>Tài khoản: ${pageContext.request.userPrincipal.name}
            <a class="btn btn-danger" role="button" href="<c:url value="/logout" />" >Thoát</a></p>
       </c:if> 
       </div>
    </div>
     <div class="row">
      <div class="col-lg-8 col-lg-offset-2"id="header_menu">
       <ul class="nav nav-tabs">
        <li><a href="<c:url value="/home"/>">TRANG CHỦ </a></li>
       
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="input" aria-haspopup="true" aria-expanded="false">ĐỀ THI<span class="caret"></span></a>
          <ul class="dropdown-menu multi-level" role="menu">
                     
            <li class='dropdown-submenu'>
              <a tabindex="-1" href="<c:url value="/inputexam"/>">Tạo Câu Hỏi</a>
              <ul class='dropdown-menu'>
                  <li><a href="<c:url value="/inputexam"/>">Giữa Kì</a></li>
                   <li class="divider"></li>
                  <li><a href="<c:url value="/inputexam"/>">Cuối Kì</a></li>
                   <li class="divider"></li>
                  <li><a href="<c:url value="/inputexam"/>">Quizs</a></li>
                   <li class="divider"></li>
                  <li><a href="<c:url value="/inputexam"/>">Tut</a></li>
                   <li class="divider"></li>
                  <li><a href="<c:url value="/inputexam"/>">Khác</a></li>
              </ul>
            </li>
          
          <li class='dropdown-submenu'>
              <a href="<c:url value="/formgetexam"/>">Tạo Đề</a>  
            </li>
          
        <li class='dropdown-submenu'>
             <a tabindex="-1" href="<c:url value="/getexam"/>">Lấy Đề</a>
             <ul class='dropdown-menu'>
                  <li><a href="<c:url value="/getexam"/>">Giữa Kì </a></li>
                   <li class="divider"></li>
                  <li><a href="<c:url value="/getexam"/>">Cuối Kì</a></li>
                   <li class="divider"></li>
                  <li><a href="<c:url value="/getexam"/>">Quiz</a></li>
                   <li class="divider"></li>
                  <li><a href="<c:url value="/getexam"/>">Tut</a></li>
                   <li class="divider"></li>
                  <li><a href="<c:url value="/getexam"/>">Khác</a></li>
              </ul>
        </li>
        </ul>
        </li>
        
        <li><a href="#">ĐÁNH GIÁ</a></li>
        <li><a href="#">BIỂU ĐỒ</a></li>
         <li><a href="#">HƯỚNG DẪN</a></li>
        <li class="dropdown">
         <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="input" aria-haspopup="true" aria-expanded="false">MỞ RỘNG<span class="caret"></span></a>
          <ul class="dropdown-menu multi-level" role="menu">
         
            <li class="divider"></li>
            <!-- Collectibles & arts -->
            <li class='dropdown-submenu'>
              <a tabindex="-1" href="#">---------</a>
              <ul class='dropdown-menu'>
                  <li><a href="#">------------</a></li>
                  <li><a href="#">------------</a></li>
                  <li><a href="#">------------</a></li>
                  <li><a href="#">------------</a></li>
              </ul>
            </li>
            <li class="divider"></li>
            <!-- Collectibles & arts -->
            <li class='dropdown-submenu'>
              <a tabindex="-1" href="#">------------</a>
              <ul class='dropdown-menu'>
                  <li><a href="#">-----------</a></li>
                  <li><a href="#">-----------</a></li>
                  <li><a href="#">-----------</a></li>
                  <li><a href="#">-----------</a></li>  
              </ul>
            </li>
          </ul>
        </li>
      </ul>
      </div>
      
      </div>
   <!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
  

  
</nav>
