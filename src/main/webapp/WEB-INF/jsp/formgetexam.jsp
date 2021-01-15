<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/includes/taglibs.jsp"%>
 

 
 <div class="container navbar-default">
      <div class='row'>  
    <div class="col-lg-3 content-categories-panel">
        <div class="panel panel-default">
        <div class="panel-heading">Vùng Làm Việc</div>
        <ul class="nav nav-stacked" id="accordion1">
                    
            <li class="panel"> <a data-toggle="collapse" data-parent="#accordion1" href="#firsthLink">Trang Chủ </a>
               <ul id="firsthLink" class="collapse">
                  <li><a href="#firsthLink2">Trang chủ</a></li>                
                </ul> 
            </li> 

            <li class="panel"> <a data-toggle="collapse" data-parent="#accordion1" href="#secondthLink">Đề thi</a>
                <ul id="secondthLink" class="collapse">
                  <li><a href="#secondthLink2">Giữa Kì</a></li>
                  <li><a href="#secondthLink2">Cuối Kì</a></li>
                  <li><a href="#secondthLink2">Quiz hoặc Test</a></li> 
                </ul> 
            </li>
            
            <li class="panel"> <a data-toggle="collapse" data-parent="#accordion1" href="#thirdthLink">Tùy Chọn</a>

                <ul id="thirdthLink" class="collapse">
                  <li><a href="#thirdthLink2">Nhập Câu hỏi </a></li>
                  <li><a href="#thirdthLink2">Import File latex</a></li>
                  <li><a href="#thirdthLink2">Dán code Latex</a></li> 
                </ul> 
            </li>    
        </ul>
      </div>
    </div>
    
    <div class="col-lg-9">
    <div class="row carousel-holder">
           <div class="col-lg-12">
           <div class="panel panel-default" id="firstLink2">
            <div class="panel-heading">Tạo đề thi</div>
            <br/>
            <form action="" method="POST">
           <div class="row">
           <!--  <div class="col-lg-4 col-lg-offset-1">
              <div class="form-group input-group">
                 <label >Nhập Tên Đề Thi :</label> 
                 <input class="form-control" type="text" name='NameExam' placeholder="Nhập Tên Đề Thi" /> 
            </div>
            </div> -->
               <div class="col-lg-8 col-lg-offset-2">
               <div class="form-group input-group">
                 <label >Chọn Môn Trong Danh Sách :</label> 
                  <select class="form-control" id="subjects" name="subjects">
                   <c:forEach items = "${listsubject}" var="listsubjects">
                      <option value="${listsubjects.id}"> ${listsubjects.subjectsname}</option>
                   </c:forEach>  
                  </select> 
                  </div>        
               </div>
             </div>
  
               <div class="row">             
               </div>
               <div class="form-group input-group col-lg-12 text-center">  
                  <button type='submit' class='btn btn-info' value='Submit' >Bắt đầu</button>
               </div>
        </form>   
    </div>
            </div>
            </div>
           </div>
    </div>
    </div>
 