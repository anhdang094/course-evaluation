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
<div>
    <display:table name="${question2}" class="simple wid100" id="question" requestURI="" pagesize="15">
       
        <display:column title="ExamId" property="examid" />
        <display:column title="LevelQ" property="levelq"/>
        <display:column title="ChapterSubjects" property="chaptersubjects"/>
        <display:column title="questionaname" property="questionname"/>
        <display:column title="Answera" property="answera"/>
       
        
    </display:table>
  
</div>