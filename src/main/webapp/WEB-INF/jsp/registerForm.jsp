<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>

 <style type="text/css">
.error {
color: red;
}
</style>

<title>ĐĂNG KÝ TÀI KHOẢN MỚI !</title>
 
   <div class="container" id="css_reg" ng-controller="RegisterController">
    <div class="row">
      <div class="col-lg-8 col-lg-offset-3 col-md-8 col-md-offset-3 col-xs-8 col-xs-offset-3 col-sm-8 col-sm-offset-3">

        <form id="usens" ng-submit="createAccount()" >
        <div class="form-group">
        </div>

        <div class="row">
        <div class="col-lg-6 col-lg-offset-2 text-center">
          <h3> ĐĂNG KÝ TÀI KHOẢN</h3>
          </div>
        </div>

       <div class="row">
        <div class="col-lg-6 col-lg-offset-2">
          <div class="form-group input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
            <select
            	class="form-control" name='user.role'
            	ng-model="user.role"
            	ng-change="onChangeRole()"
            	placeholder="Loại tài khoản"
            	required>
               <option value ="student">Sinh Viên</option>
               <option value ="teacher">Giảng Viên</option>
            </select>
          </div>
       	</div>
       </div>


       <div class="row">
        <div class="col-lg-6 col-lg-offset-2">
          <div class="form-group input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
            <input class="form-control" required type="email" name='email' placeholder="Email" ng-model="user.email"/>
          </div>
        </div>
       </div>

       <div class="row">
        <div class="col-lg-6 col-lg-offset-2">
          <div class="form-group input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
            <input class="form-control" required type="password" name='password' placeholder="Mật khẩu" ng-model="user.password"/>
          </div>
        </div>
       </div>

       <div class="row">
        <div class="col-lg-6 col-lg-offset-2">
          <div class="form-group input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
            <input class="form-control" required type="password" name='repassword' placeholder="Nhập lại mật khẩu" ng-model="user.repassword"/>
          </div>
        </div>
       </div>

       <hr>

       <div class="row" ng-show="isTeacher">
        <div class="col-lg-6 col-lg-offset-2">
          <div class="form-group input-group">
            <label class="input-group-addon"><i class="glyphicon glyphicon-info-sign"></i></label>
            <input class="form-control" name='teacherName' ng-required="isTeacher" placeholder="Tên" ng-model="teacher.name"/>
          </div>
          <div class="form-group input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-info-sign"></i></span>
            <input class="form-control" name='teacherFaculty' ng-required="isTeacher" placeholder="Khoa" ng-model="teacher.faculty"/>
          </div>
          <div class="form-group input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-info-sign"></i></span>
            <input class="form-control" name='teacherPosition' ng-required="isTeacher" placeholder="Vị trí" ng-model="teacher.position"/>
          </div>
        </div>
       </div>

       <div class="row" ng-show="isStudent">
        <div class="col-lg-6 col-lg-offset-2">
          <div class="form-group input-group">
            <label class="input-group-addon"><i class="glyphicon glyphicon-info-sign"></i></label>
            <input class="form-control" name='studentName' ng-required="isStudent" placeholder="Tên" ng-model="student.name"/>
          </div>
          <div class="form-group input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-info-sign"></i></span>
            <input class="form-control" name='studentFaculty' ng-required="isStudent" placeholder="Khoa" ng-model="student.faculty"/>
          </div>
          <div class="form-group input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-info-sign"></i></span>
            <input class="form-control" name='studentClass' ng-required="isStudent" placeholder="Lớp" ng-model="student.classname"/>
          </div>
        </div>
       </div>

       <div class="row">
        <div class="col-lg-6 col-lg-offset-2 text-center">
         <div class="checkbox">
           <label>
             <input type="checkbox" required> Tôi đồng ý với <b><a href="ruleSystem">chính sách điều khoản của hệ thống</a></b>?
           </label>
         </div>
        </div>
       </div>

       <div class="row">
        <div class="col-lg-6 col-lg-offset-2">
         <div class="form-group text-center">
           <button type='submit' class='btn btn-info'>ĐĂNG KÝ</button>
         </div>
        </div>
        </div>

       <div class="row">
         <div class="col-lg-6 col-lg-offset-2 text-center" style="margin-bottom: 30px">
          <span>Bạn đã có tài khoản ? </span><a style="color:red;" href="<c:url value="/login"/>">Đăng nhập ngay !</a>
         </div>
       </div>

      </form>
      </div>
    </div>    
  </div>
  


