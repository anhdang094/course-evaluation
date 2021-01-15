<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>

<form class="form-horizontal" id="colorformexam" >
 <div class="row col-lg-12 ">
  <hr/>
   <div class="col-lg-4 col-lg-offset-2 text-center">
   <p><h4>ĐẠI HỌC QUỐC GIA TP.HCM</h4></p>
   <p><h4>TRƯỜNG ĐẠI HỌC BÁCH KHOA</h4></p>
   <p>*****************************</p>
   </div>
   <div class="col-lg-4 text-center">
   <h4>Môn Thi : ${subjects}</h4>
   <h4> Thời gian làm bài : ${times} (phút)</h4>
   <h4>Ngày thi :${dateexam}</h4>
   </div>
   <div class="row col-lg-12 ">
    <div class="row">
      <div class="col-lg-4 col-lg-offset-2 text-left">
      <p><h5>Họ Và Tên :.............................</h5></p>
      <p><h5>MSSV      :......................................</h5></p>
      </div>
      <div class="col-lg-4 col-lg-offset-2 text-left">
      <table border="1" width="150px">
         <tr>
             <th> <h4  align="center">Mã đề : ${code}</h4></th>
         </tr>
      </table>
      </div>
    </div>
    </div>
   <div class="row col-lg-12 ">
    <hr/>
    </div>
    
   </div>
   
   <c:set var="count" value="1" scope="page" />
  
    
    <c:forEach items="${question}" var="question">
     <div class="col-lg-10 col-lg-offset-2" >
         <h5>Câu <c:out value="${count}" /> :</h5>
         <p>${question.questionname}</p>
      </div>
		<div class="row">
			<c:if test="${not empty question.answera}">
				<div class='col-lg-4 col-lg-offset-2'>
					<input type="checkbox" value="" name="answera" /> A:
					${question.answera}
				</div>
			</c:if>
			<c:if test="${not empty question.answerb}">
				<div class='col-lg-4 col-lg-offset-1'>
					<input type="checkbox" value="" name="answerb" /> B:
					${question.answerb}
				</div>
			</c:if>
		</div>
		<br />
								<div class="row">
								<c:if test="${not empty question.answerc}">
									<div class='col-lg-4 col-lg-offset-2'>
										<input type="checkbox" value="" name="answerc" /> C:
										${question.answerc}
									</div>
									</c:if>
							   	<c:if test="${not empty question.answerd}">	
									<div class='col-lg-4 col-lg-offset-1'>
										<input type="checkbox" value="" name="answerd" /> D:
										${question.answerd}
									</div>
								</c:if>
								</div>
								<br />
								<div class="row">
								<c:if test="${not empty question.answere}">
									<div class='col-lg-4 col-lg-offset-2'>
										<input type="checkbox" value="" name="answerd" /> E:
										${question.answere}
									</div>
								</c:if>
									
								</div>
                              <br />
      
      <c:set var="count" value="${count + 1}" scope="page"/>
      </c:forEach>
          
          <br/>
   
  </form>
   
