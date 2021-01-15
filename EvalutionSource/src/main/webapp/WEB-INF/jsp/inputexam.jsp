<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="container navbar-default">
      <div class='row'>
      
        <div class="col-lg-3 content-categories-panel">
        <div class="panel panel-default">
        <div class="panel-heading">Vùng Làm Việc</div>
        <ul class="nav nav-stacked" id="accordion1">
                    
            <li class="panel"> <a data-toggle="collapse" data-parent="#accordion1" href="<c:url value="/home"/>">Trang Chủ </a>
            </li> 

            <li class="panel"> <a data-toggle="collapse" data-parent="#accordion1" href="#secondthLink2">Nhập câu hỏi</a>
             
            </li>
            
             <li class="panel"> <a data-toggle="collapse" data-parent="#accordion1" href="#thirdthLink2">Dán LaTex</a>
             
            </li>
            
             <li class="panel"> <a data-toggle="collapse" data-parent="#accordion1" href="#fivethLink2">Import File</a>
             
            </li>
          
         
          
        </ul>
      </div>
    </div>
    
  <div class="col-lg-9">
           <div class="row carousel-holder">
           <div class="col-lg-12">
           <div class="panel panel-default" id="secondthLink2">
            <div class="panel-heading">Nhập Câu Hỏi</div>
            
            <form  action="" method="POST">
            <div class="form-group text-center">
              <h3>Nội Dung Câu Hỏi</h3>
            </div>
            
            <div class=" col-lg-3 col-lg-offset-2">
            <div class="form-group input-group">
            <label >Mã Đề : </label>
            <input class="form-control" type="text" name='examid' placeholder="Mã Đề Kiểm Tra"/>
            </div>          
          </div>
            
          <div class=" col-lg-3 col-lg-offset-1">
           <div class="form-group">
             <label >Mức Độ Khó :</label>
            <select class="form-control"  name="levelq">
                     <option>1</option>
                     <option>2</option>
                     <option>3</option>
                     <option>4</option>
                     <option>5</option>
           </select>                   
          </div>
          </div>
          
          <div class=" col-lg-3 col-lg-offset-2">
            <div class="form-group input-group">
          
             <label for="ExamID">Tên Môn Học : </label>
            <input class="form-control" type="text" name='subjects' placeholder="Tên Môn Học"/>          
          </div>
          </div>
          
          <div class=" col-lg-3 col-lg-offset-1">
           <div class="form-group">
            <label for="chaptersubjects">Chương :</label>         
            <input class="form-control" type="number" name='chaptersubjects' placeholder="Chương"/>     
          </div>
          </div>
          
          
          <div class="form-group input-group col-lg-8 col-lg-offset-2">
            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
            <input class="form-control" type="text" name='questionname' placeholder="Tên câu hỏi"/>     
          </div>
                 
          <div class="form-group input-group col-lg-8 col-lg-offset-2">
            <span class="input-group-addon"><i class="glyphicon glyphicon-text-color"></i></span>
            <input class="form-control" type="text" name='answera' placeholder="Đáp Án A"/>     
          </div>
           
          <div class="form-group input-group col-lg-8 col-lg-offset-2">
            <span class="input-group-addon"><i class="glyphicon glyphicon-text-color"></i></span>
            <input class="form-control" type="text" name='answerb' placeholder="Đáp Án B"/>     
          </div>
          
          <div class="form-group input-group col-lg-8 col-lg-offset-2">
            <span class="input-group-addon"><i class="glyphicon glyphicon-text-color"></i></span>
            <input class="form-control" type="text" name='answerc' placeholder="Đáp Án C"/>     
          </div>
          
          <div class="form-group input-group col-lg-8 col-lg-offset-2">
            <span class="input-group-addon"><i class="glyphicon glyphicon-text-color"></i></span>
            <input class="form-control" type="text" name='answerd' placeholder="Đáp Án D"/>     
          </div>
          
           <div class="form-group input-group col-lg-8 col-lg-offset-2">
            <span class="input-group-addon"><i class="glyphicon glyphicon-text-color"></i></span>
            <input class="form-control" type="text" name='answere' placeholder="Đáp Án E"/>     
          </div>
          
          <div class="form-group text-center">  
          <button type='submit' class='btn btn-info' name ="postquestion" value='Submit' >Submit</button>
          </div>
        </form>       
      </div>
        
      
     
       <div class="panel panel-default" id="thirdthLink2">
        <form id="question" action="" method="POST">
            <div class="panel-heading">Dán Latex</div>
            
            <div class=" col-lg-3 col-lg-offset-2">
            <div class="form-group input-group">
            <label >Mã Đề : </label>
            <input class="form-control" type="text" name='examid' placeholder="Mã Đề Kiểm Tra"/>
            </div>          
          </div>
            
          <div class=" col-lg-3 col-lg-offset-1">
           <div class="form-group">
             <label >Mức Độ Khó :</label>
            <select class="form-control"  name="levelq">
                     <option>1</option>
                     <option>2</option>
                     <option>3</option>
                     <option>4</option>
                     <option>5</option>
           </select>                   
          </div>
          </div>
             <div class=" col-lg-3 col-lg-offset-2">
            <div class="form-group input-group">
          
             <label for="ExamID">Tên Môn Học : </label>
            <input class="form-control" type="text" name='subjects' placeholder="Tên Môn Học"/>          
          </div>
          </div>
          
          <div class=" col-lg-3 col-lg-offset-1">
           <div class="form-group">
            <label for="chaptersubjects">Chương :</label>         
            <input class="form-control" type="number" name='chaptersubjects' placeholder="Chương"/>     
          </div>
          </div>
              <div class="form-group input-group col-lg-10 col-lg-offset-1">         
            <textarea class="form-control" rows="10" cols="50"  type="text" name='latex' placeholder="Copy code Latex vào đây"></textarea>     
              </div>
              
              
 				<div class="form-group text-center">  
          <button type='submit' class='btn btn-info' name="postlatex" value='Submit' >Submit</button>
          </div>
         </form>       
       </div> 
      
      
      <div class="panel panel-default" id="thirdthLink2">
        <form id="question" action="" method="POST">
            <div class="panel-heading">Dán Word</div>
            
            <div class=" col-lg-8 col-lg-offset-2">
            <div class="form-group input-group">
            <label >Nhập dấu hiệu nhận biết trước câu hỏi  : </label>
            <input class="form-control" type="text" name='stringexam' placeholder="Ví dụ : Câu 1."/>
            </div>          
          </div>
            
          <div class=" col-lg-8 col-lg-offset-2">
            <div class="form-group input-group">
            <label >Nhập dấu hiệu nhận biết trước câu trả lời A  : </label>
            <input class="form-control" type="text" name='stringsolutionA' placeholder="Ví dụ : A."/>
            </div>          
          </div>
          <div class=" col-lg-8 col-lg-offset-2">
            <div class="form-group input-group">
            <label >Nhập dấu hiệu nhận biết trước câu trả lời B : </label>
            <input class="form-control" type="text" name='stringsolutionB' placeholder="Ví dụ : B."/>
            </div>          
          </div>
          <div class=" col-lg-8 col-lg-offset-2">
            <div class="form-group input-group">
            <label >Nhập dấu hiệu nhận biết trước câu trả lời C : </label>
            <input class="form-control" type="text" name='stringsolutionC' placeholder="Ví dụ : C."/>
            </div>          
          </div>
          <div class=" col-lg-8 col-lg-offset-2">
            <div class="form-group input-group">
            <label >Nhập dấu hiệu nhận biết trước câu trả lời D : </label>
            <input class="form-control" type="text" name='stringsolutionD' placeholder="Ví dụ : D."/>
            </div>          
          </div>
          <div class=" col-lg-8 col-lg-offset-2">
            <div class="form-group input-group">
            <label >Nhập dấu hiệu nhận biết trước câu trả lời E : </label>
            <input class="form-control" type="text" name='stringsolutionE' placeholder="Ví dụ : E."/>
            </div>          
          </div>
          
          
             <div class=" col-lg-3 col-lg-offset-2">
            <div class="form-group input-group">
          
             <label for="ExamID">Tên Môn Học : </label>
            <input class="form-control" type="text" name='subjects' placeholder="Tên Môn Học"/>          
          </div>
          </div>
         
              <div class="form-group input-group col-lg-10 col-lg-offset-1">         
            <textarea class="form-control" rows="10" cols="50"  type="text" name='word' placeholder="Copy Word vào đây"></textarea>     
              </div>
              
              
 				<div class="form-group text-center">  
          <button type='submit' class='btn btn-info' name="postword" value='Submit' >Submit</button>
          </div>
         </form>       
       </div> 
     
       <div class="panel panel-default" id="fivethLink2">
                <div class="panel-heading">Import File</div>
           <form method="POST" enctype=“multipart/form-data”>     
                <div class="form-group input-group">
                 <span class="input-group-addon"><i class="glyphicon glyphicon-folder-open"></i></span>
                 <input class="form-control" type="file" name='importfile' placeholder="Import File"/>     
                </div>
                <div class="form-group text-center">
                 <button type='submit' class='btn btn-info' name="importfile" value='Submit' >Submit</button>
                </div>
           </form>     
       </div>
              
         </div>
         </div>
         </div>
         
      </div>
      </div>   
      
 