<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html"; charset="UTF-8"/>
<title>Hệ thống đánh giá chất lượng môn học<decorator:title/> </title>
 <link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
 <link href="<c:url value="/resources/css/stylefooter.css"/>" rel="stylesheet">
 <link href="<c:url value="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"/>" rel="stylesheet">

<link rel="stylesheet" href="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/KaTeX/0.5.1/katex.min.css"/>">


</head>

<body>
   
     <%@ include file="/includes/header.jsp"%>
     <div class="container page-content">
      <div id="content">
         <decorator:body />
      </div>
      </div>
    <%@ include file="/includes/footer.jsp"%>
   
<script src="<c:url value="/resources/js/search.js"/>"></script>
<script src="<c:url value="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
<script src="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/KaTeX/0.5.1/katex.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="http://arrow.scrolltotop.com/arrow79.js"/>"></script>


<script type="text/javascript" src="<c:url value="http://cdn.mathjax.org/mathjax/latest/MathJax.js"/>">
MathJax.Hub.Config({
 extensions: ["tex2jax.js","TeX/AMSmath.js","TeX/AMSsymbols.js"],
 jax: ["input/TeX", "output/HTML-CSS"],
 tex2jax: {
     inlineMath: [ ['$','$'], ["\\(","\\)"] ],
     displayMath: [ ['$$','$$'], ["\\[","\\]"] ],
 },
 "HTML-CSS": { availableFonts: ["TeX"] }
});
</script> 
</body>
</html>