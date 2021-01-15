<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>

<style>
  td, tr, thead{
  width : 30px;
  height : 50px;
  }
</style>

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
					<div class="panel panel-default" id="firstLink2">
						<div class="panel-heading">Tạo đề thi</div>
						<br />
						<h4 class="text-center">Tạo Đề Tùy Chọn</h4>
						<hr>

						<form action='<c:url value="createexam/option"></c:url>' method="POST">
						
		<div class="row">
            <div class="col-lg-4 col-lg-offset-1">
              <div class="form-group input-group">
                 <label >Nhập Tên Đề Thi :</label> 
                 <input class="form-control" type="text" name='NameExam' placeholder="Nhập Tên Đề Thi" /> 
            </div>
            </div> 
               <div class="col-lg-4 col-lg-offset-2">
               <div class="form-group input-group">
                 <label >Môn Đã Chọn :</label> 
                   <input class="form-control" type="text" name='NameSubjects' value="${subjects.subjectsname}" readonly="readonly" /> 
                  </div>        
               </div>
             </div>
           
             <div class="row">
                <div class=" col-lg-4 col-lg-offset-1">
                <div class="form-group input-group">
                <label >Số Câu Hỏi: </label>
                  <input class="form-control" type="number" name='NumberQ' id="NumberQ" placeholder="Số câu" value="${maxquestion}" readonly/>             
                </div>
               </div>
               
                <div class=" col-lg-4 col-lg-offset-2">
                <div class="form-group input-group">        
                  <label >Thời gian:(phút)</label>
                  <input class="form-control" type="number" name='times' placeholder="Thời gian"/>      
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
				  <hr>
             		<div class="col-lg-12 ">       
                 <input type="text" name='numberchapter' id="numberchapter" value="${numberchapter}" hidden/>
                 </div>
							<div class="row">
								
								<div class="col-lg-4">
									<table class="table">
										<thead>
											<tr>
												<th class="text-center text-align">Chương</th>
											</tr>
										<thead>
										<tbody>
											<c:set var="count" value="0" scope="page" />
											<c:forEach items="${chapter}" var="chapter">
												<c:set var="string1" value="${chapter.chaptername}" />
												<c:set var="string2" value="${fn:substring(string1, 0, 34)}" />
												<tr>

													<td>${string2}...</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>

								</div>

								<div class="col-lg-2">
									<table class="table">
										<thead>
											<tr>
												<th class="text-center text-align">Tổng Câu</th>
											</tr>
										<thead>
										<tbody>
											<c:set var="count" value="0" scope="page" />
											<c:forEach items="${numberquestion}" var="numberquestion">
												<tr>												
													<td class="text-center">
													
													${numberquestion}																								
													
												    </td>
												
												</tr>
											</c:forEach>
										</tbody>
									</table>

								</div>

								<div class="col-lg-1">
									<table class="table">
										<thead>
											<tr>
												<th class="text-center text-align">L1</th>
											</tr>
										<thead>
										<tbody>
											<c:set var="count" value="0" scope="page" />
											<c:forEach items="${level1}" var="level1">
												<tr>
													<td class="text-center">
														<div class="input-group">
														<c:set var="level1" scope="session" value="${level1}"/>
														<c:choose>
																    <c:when test="${level1 >'0'}">
																       <input class="text-center" type="number"
																name="countlevel1${count}" id="countlevel1${count}" max="${level1}" min="0"
																step="1" value="${level1}" onblur="countnumber()"/>
																        <br/>
																    </c:when>    
																    <c:otherwise>
																      <input class="form-control" type="hidden"
																name="countlevel1${count}" id="countlevel1${count}" max="${level1}" min="0"
																step="1" value="0" readonly/>
																        <br />
																       </c:otherwise>
														</c:choose>
															
														</div>
													</td>
													
												</tr>
												<c:set var="count" value="${count + 1}" scope="page" />
											</c:forEach>									  
										</tbody>
									</table>

								</div>

								<div class="col-lg-1">
									<table class="table">
										<thead>
											<tr>
												<th class="text-center text-align">L2</th>
											</tr>
										<thead>
										<tbody>
											<c:set var="count" value="0" scope="page" />
											<c:forEach items="${level2}" var="level2">
												<tr>
													<td class="text-center" width="50px" height="50px">
														<div class="input-group">
														<c:set var="level2" scope="session" value="${level2}"/>
														<c:choose>
																    <c:when test="${level2 >'0'}">
																       <input class="text-center" type="number"
																name="countlevel2${count}" id="countlevel2${count}" onblur="countnumber()" max="${level2}" min="0"
																step="1" value="${level2}"/>
																        <br />
																    </c:when>    
																    <c:otherwise>
																      <input class="form-control" type="hidden"
																name="countlevel2${count}" id="countlevel2${count}" max="${level2}" min="0"
																step="1" value="0" readonly/>
																        <br />
																       </c:otherwise>
														</c:choose>														
														
														</div>
													</td>
												</tr>
												<c:set var="count" value="${count + 1}" scope="page" />
											</c:forEach>
										</tbody>
									</table>

								</div>

								<div class="col-lg-1">
									<table class="table">
										<thead>
											<tr>
												<th class="text-center text-align">L3</th>
											</tr>
										<thead>
										<tbody>
											<c:set var="count" value="0" scope="page" />
											<c:forEach items="${level3}" var="level3">
												<tr>
													<td class="text-center">
														<div class="input-group">
														<c:set var="level3" scope="session" value="${level3}"/>														
														<c:choose>
																    <c:when test="${level3 >'0'}">
																       <input class="text-center" type="number"
																name="countlevel3${count}" id="countlevel3${count}" max="${level3}" min="0"
																step="1" value="${level3}" />
																        <br />
																    </c:when>    
																    <c:otherwise>
																      <input class="form-control" type="hidden"
																name="countlevel3${count}" id="countlevel3${count}" onblur="countnumber()" max="${level3}" min="0"
																step="1" value="0" readonly/>
																        <br />
																       </c:otherwise>
														</c:choose>																													
														</div>
													</td>
												</tr>
												<c:set var="count" value="${count + 1}" scope="page" />
											</c:forEach>
										</tbody>
									</table>

								</div>
								<div class="col-lg-1">
									<table class="table">
										<thead>
											<tr>
												<th class="text-center text-align">L4</th>
											</tr>
										<thead>
										<tbody>
											<c:set var="count" value="0" scope="page" />
											<c:forEach items="${level4}" var="level4">
												<tr>
													<td class="text-center">
													<div class="input-group">
														<c:set var="level4" scope="session" value="${level4}"/>														
														<c:choose>
																    <c:when test="${level4 >'0'}">
																       <input class="text-center" type="number"
																name="countlevel4${count}" id="countlevel4${count}" onblur="countnumber()" max="${level4}" min="0"
																step="1" value="${level4}" />
																        <br />
																    </c:when>    
																    <c:otherwise>
																      <input class="form-control" type="hidden"
																name="countlevel4${count}" id="countlevel4${count}" max="${level4}" min="0"
																step="1" value="0" readonly/>
																        <br />
																       </c:otherwise>
														</c:choose>	
														
														</div>
													</td>
												</tr>
												<c:set var="count" value="${count + 1}" scope="page" />
											</c:forEach>
										</tbody>
									</table>

								</div>

								<div class="col-lg-1">
									<table class="table">
										<thead>
											<tr>
												<th class="text-center text-align">L5</th>
											</tr>
										<thead>
										<tbody>
											<c:set var="count2" value="0" scope="page" />
											<c:forEach items="${level5}" var="level5">
												<tr>
													<td class="text-center">
													<div class="input-group">
														<c:set var="level5" scope="session" value="${level5}"/>														
														<c:choose>
																    <c:when test="${level5 >'0'}">
																       <input class="text-center" type="number"
																name="countlevel5${count2}" id="countlevel5${count2}" onblur="countnumber()" max="${level5}" min="0"
																step="1" value="${level5}" />
																        <br />
																    </c:when>    
																    <c:otherwise>
																      <input class="form-control" type="hidden"
																name="countlevel5${count2}" id="countlevel5${count2}" max="${level5}" min="0"
																step="1" value="0" readonly/>
																        <br />
																       </c:otherwise>
														</c:choose>	
														</div>
													</td>
												</tr>
												<c:set var="count2" value="${count2 + 1}" scope="page" />
											</c:forEach>
										</tbody>
									</table>

								</div>
							</div>

							<div class="form-group input-group col-lg-12 text-center">
								<button type='submit' class='btn btn-info' value='Submit'>Tạo
									Đề</button>
							</div>

						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
    function countnumber(){
       var numberchapter = document.getElementById("numberchapter").value;
        var sum = 0;
        //window.alert(numberchapter);
        
        for(j=0; j < numberchapter; j++){
        	for( i = 1; i <= 5; i++){
        		 var countlevel = "countlevel"+i + j;
        		var number = document.getElementById(countlevel).value;
       		    sum = eval(number) + eval(sum); 
        	//	window.alert(sum);
        	}
        }
       // window.alert(sum);
        	document.getElementById("NumberQ").value = sum;
     }
</script>
