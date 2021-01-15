<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
 
<title>Welcome </title>
<style>
    table tr td, table tr th {
        border: 1px solid #aaa;
        padding: 2px;
    }
     
    table {
        border-collapse: collapse;
        width: 500px;
    }
</style>
<h1>Test</h1>
           <c:forEach items="${subjects}" var="s">
           <h1>${s.subjectsname}</h1>
           </c:forEach>

<%-- <div>
    <display:table name="${subjects}" class="simple wid100" id="subjects" requestURI="" pagesize="15">
       
        <display:column title="ExamId" property="examid" />
        <display:column title="LevelQ" property="levelq"/>
        <display:column title="ChapterSubjects" property="chaptersubjects"/>
        <display:column title="questionaname" property="questionname"/>
        <display:column title="Answera" property="answera"/>
       
        
    </display:table>
  
</div> --%>