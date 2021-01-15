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
<h1>User List</h1>
 <p><a href="login">Đăng nhập ngay !</a></p>
<div>
    <display:table name="${userList}" class="simple wid100" id="username" requestURI="" pagesize="15">
       
        <display:column title="UserName" property="username" />
        <display:column title="Password" property="passwordu"/>
        <display:column title="Detail" media="html" >
        <a href="profile/${username.userid}" title="Detail">Detail</a>&nbsp;
    </display:column>
    </display:table>
  
</div>