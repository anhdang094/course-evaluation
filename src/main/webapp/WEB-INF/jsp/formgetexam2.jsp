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
            <h4 class="text-center">Tạo Đề Ngẫu Nhiên</h4>
                 <hr>
            <br/>
            <form action="" method="POST">
           <div class="row">
            <div class="col-lg-4 col-lg-offset-1">
              <div class="form-group input-group">
                 <label >Nhập Tên Đề Thi :</label> 
                 <input class="form-control" type="text" name='NameExam' placeholder="Nhập Tên Đề Thi" /> 
            </div>
            </div> 
               <div class="col-lg-4 col-lg-offset-2">
               <div class="form-group input-group">
                 <label >Chọn Môn Trong Danh Sách :</label> 
                  <select class="form-control" id="subjects" name="subjects">
                   <c:forEach items = "${listsubject}" var="listsubjects">
                      <option value="${listsubjects.subjectsname}"> ${listsubjects.subjectsname}</option>
                      
                   </c:forEach>  
                  </select> 
                  </div>        
               </div>
             </div>
             <div class="row">
                <div class=" col-lg-4 col-lg-offset-1">
                <div class="form-group input-group">
                <label >Số Câu Hỏi: </label>
                  <input class="form-control" type="number" name='NumberQ' id="NumberQ" placeholder="Số câu" value="0" readonly/>             
                </div>
               </div>
               
                <div class=" col-lg-4 col-lg-offset-2">
                <div class="form-group input-group">        
                  <label >Thời gian:(phút)</label>
                  <input class="form-control" type="number" name='times' placeholder="Thời gian" />      
               </div>
               </div>
             </div>  
             <div class="row">
                <div class=" col-lg-4 col-lg-offset-1">
                <div class="form-group input-group">               
                 <label>Mã đề : </label>
                  <input class="form-control" type="text" name='code' placeholder="Mã Đề"/>
               </div>
               </div>
               
                <div class=" col-lg-4 col-lg-offset-2">
                <div class="form-group input-group">               
                 <label>Ngày: </label>
                  <input class="form-control" type="date" name='dateexam' placeholder="Ngày tạo đề"/>
               </div>
               </div>  
               </div> 
              <%--  <div class="col-lg-12 ">       
                <input type="text" name='numberchapter' id="numberchapter" value="${numberchapter}"/>
               </div> --%>
               <div class="row">
                <div class=" col-lg-12 col-lg-offset-1">
                <div class="form-group input-group">
               <label>Chọn Số Câu Hỏi Theo Mức: </label>
                    </div> 
               </div>
               </div>
               <div class="row">     
               <div class="col-lg-2 col-lg-offset-1">       
                <div class="form-group input-group"> 
                 <label>Mức 1 </label>              
                  <input class="form-control" type="number" name='level1' id="level1" placeholder="Mức độ 1" max= "100" min= "0" step= "1" value= "0" onblur="number()"/>
               </div>
               </div> 
                <div class="col-lg-2">
                <div class="form-group input-group">   
                  <label>Mức 2 </label>              
                  <input class="form-control" type="number" name='level2' id="level2" placeholder="Mức độ 2" max= "100" min= "0" step= "1" value= "0" onblur="number()"/>
               </div>
               </div> 
                <div class="col-lg-2">
                <div class="form-group input-group"> 
                  <label>Mức 3 </label>                            
                  <input class="form-control" type="number" name='level3' id="level3" placeholder="Mức độ 3" max= "100" min= "0" step= "1" value= "0" onblur="number()"/>
               </div>
               </div> 
                <div class="col-lg-2">
                <div class="form-group input-group">  
                  <label >Mức 4 </label>                                                         
                  <input class="form-control" type="number" name='level4' id="level4" placeholder="Mức độ 4" max= "100" min= "0" step= "1" value= "0" onblur="number()"/>
               </div>
               </div> 
                <div class="col-lg-2">
                <div class="form-group input-group">               
                  <label>Mức 5 </label>                                                         
                  <input class="form-control" type="number" name='level5' id="level5" placeholder="Mức độ 5" max= "100" min= "0" step= "1" value= "0" onblur="number()"/>
               </div>
               </div>
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
  
<script> 
function number(){
	if(document.getElementById("level1").value==""){
		document.getElementById("level1").value = 0;
	}
	if(document.getElementById("level2").value==""){
		document.getElementById("level2").value = 0;
	}
	if(document.getElementById("level3").value==""){
		document.getElementById("level3").value = 0;
	}
	
	if(document.getElementById("level4").value==""){
		document.getElementById("level4").value = 0;
	}
	if(document.getElementById("level5").value==""){
		document.getElementById("level5").value = 0;
	}
	
         var number1 = document.getElementById("level1").value;
         var number2 = document.getElementById("level2").value;
         var number3 = document.getElementById("level3").value;
         var number4 = document.getElementById("level4").value;
         var number5 = document.getElementById("level5").value;
         var sumnumber = eval(number1) + eval(number2) + eval(number3) + eval(number4) + eval(number5);
         document.getElementById("NumberQ").value = sumnumber; 
}
</script>
    
 