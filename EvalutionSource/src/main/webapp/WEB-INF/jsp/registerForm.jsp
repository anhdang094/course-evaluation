<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
 
<title>ĐĂNG KÝ TÀI KHOẢN MỚI !</title>
 
   <div class="container">
    <div class="row">
      <div class="col-lg-4 col-lg-offset-4">
      
        <form id="username" action="" method="POST">
        <div class="form-group text-center">
         <h3> ĐĂNG KÝ TÀI KHOẢN MỚI !</h3>
        </div>
          <div class="form-group input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
            <input class="form-control" type="text" name='username' placeholder="Tên đăng nhập"/>          
          </div>
          
          <div class="form-group input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
            <input class="form-control" type="password" name='passwordu' placeholder="Mật khẩu"/>     
          </div>
          
          <div class="form-group input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
            <input class="form-control" type="text" name='fullname' placeholder="Tên đầy đủ"/>     
          </div>
           
          <div class="form-group input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
            <input class="form-control" type="email" name='email' placeholder="email"/>     
          </div>
          
          <div class="form-group input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
            <input class="form-control" type="text" name='faculty' placeholder="Khoa"/>     
          </div>
          
           <div class="form-group input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span>
            <input class="form-control" type="text" name='phone' placeholder="phone"/>     
          </div>
          
          <div class="checkbox">
            <label>
              <input type="checkbox"> Tôi đồng ý với chính sách các điều khoản của hệ thống?
            </label>
          </div>
          <div class="form-group text-center">
            
            <button type='submit' class='btn btn-info' >ĐĂNG KÝ</button>
          </div>
        </form>       
      </div>  
    </div>    
  </div>

  


