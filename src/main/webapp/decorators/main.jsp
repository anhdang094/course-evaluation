<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Hệ thống ra đề, đánh giá và thực hành môn học </title>
 	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>" >
 	<link rel="stylesheet" href="<c:url value="/resources/css/admin-page.css"/>">
 	<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
 	<link rel="stylesheet" href="<c:url value="/resources/css/create-exams.css"/>">
 	<link rel="stylesheet" href="<c:url value="/resources/css/question.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/sweetalert.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>" >
    <link rel="stylesheet" href="<c:url value="/resources/css/responsiveStyle.css"/>" >
 	<link rel="stylesheet" href="<c:url value="/resources/bower_components/font-awesome/css/font-awesome.min.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/bower_components/froala-wysiwyg-editor/css/froala_editor.min.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/bower_components/froala-wysiwyg-editor/css/froala_style.min.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/bower_components/froala-wysiwyg-editor/css/plugins/char_counter.css"/>">
  	<link rel="stylesheet" href="<c:url value="/resources/bower_components/froala-wysiwyg-editor/css/plugins/code_view.css"/>">
  	<link rel="stylesheet" href="<c:url value="/resources/bower_components/froala-wysiwyg-editor/css/plugins/colors.css"/>">
  	<link rel="stylesheet" href="<c:url value="/resources/bower_components/froala-wysiwyg-editor/css/plugins/emoticons.css"/>">
  	<link rel="stylesheet" href="<c:url value="/resources/bower_components/froala-wysiwyg-editor/css/plugins/file.css"/>">
  	<link rel="stylesheet" href="<c:url value="/resources/bower_components/froala-wysiwyg-editor/css/plugins/fullscreen.css"/>">
  	<link rel="stylesheet" href="<c:url value="/resources/bower_components/froala-wysiwyg-editor/css/plugins/image_manager.css"/>">
  	<link rel="stylesheet" href="<c:url value="/resources/bower_components/froala-wysiwyg-editor/css/plugins/image.css"/>">
  	<link rel="stylesheet" href="<c:url value="/resources/bower_components/froala-wysiwyg-editor/css/plugins/line_breaker.css"/>">
  	<link rel="stylesheet" href="<c:url value="/resources/bower_components/froala-wysiwyg-editor/css/plugins/table.css"/>">
  	<link rel="stylesheet" href="<c:url value="/resources/bower_components/froala-wysiwyg-editor/css/plugins/video.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/responsive-slider.css"/>"/>
	<link rel="stylesheet" href="<c:url value="/resources/css/katex.min.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/css/toastr.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-datetimepicker.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/normalize.min.css"/>">
</head>
<body ng-app="evaluationCourse">
 <%@ include file="/includes/header.jsp"%>
	   <div class="container">
             <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                 <div id="content">
                 <decorator:body />
                 </div>
            </div>
        </div>
     <!-- JAVASCRIPT -->
     <script type="text/javascript" src="<c:url value="/resources/js/jquery.js"/>"></script>
     <script type="text/javascript" src="<c:url value="/resources/js/jquery.event.move.js"/>"></script>
     <script type="text/javascript" src="<c:url value="/resources/js/responsive-slider.js"/>"></script>
<%-- 	 <script type="text/javascript" src="<c:url value="/resources/js/responsive-slider.min.js"/>"></script> 
 --%>	 
 
   <%--  <script type="text/javascript" src="<c:url value="/resources/js/homeslider.js"/>"></script> --%>
   
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.dataTables.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/bower_components/froala-wysiwyg-editor/js/froala_editor.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/bower_components/froala-wysiwyg-editor/js/plugins/align.min.js"/>"></script>
  	<script type="text/javascript" src="<c:url value="/resources/bower_components/froala-wysiwyg-editor/js/plugins/char_counter.min.js"/>"></script>
  	<script type="text/javascript" src="<c:url value="/resources/bower_components/froala-wysiwyg-editor/js/plugins/code_beautifier.min.js"/>"></script>
  	<script type="text/javascript" src="<c:url value="/resources/bower_components/froala-wysiwyg-editor/js/plugins/code_view.min.js"/>"></script>
  	<script type="text/javascript" src="<c:url value="/resources/bower_components/froala-wysiwyg-editor/js/plugins/colors.min.js"/>"></script>
  	<script type="text/javascript" src="<c:url value="/resources/bower_components/froala-wysiwyg-editor/js/plugins/emoticons.min.js"/>"></script>
  	<script type="text/javascript" src="<c:url value="/resources/bower_components/froala-wysiwyg-editor/js/plugins/entities.min.js"/>"></script>
  	<script type="text/javascript" src="<c:url value="/resources/bower_components/froala-wysiwyg-editor/js/plugins/file.min.js"/>"></script>
  	<script type="text/javascript" src="<c:url value="/resources/bower_components/froala-wysiwyg-editor/js/plugins/font_family.min.js"/>"></script>
  	<script type="text/javascript" src="<c:url value="/resources/bower_components/froala-wysiwyg-editor/js/plugins/font_size.min.js"/>"></script>
  	<script type="text/javascript" src="<c:url value="/resources/bower_components/froala-wysiwyg-editor/js/plugins/fullscreen.min.js"/>"></script>
  	<script type="text/javascript" src="<c:url value="/resources/bower_components/froala-wysiwyg-editor/js/plugins/image.min.js"/>"></script>
  	<script type="text/javascript" src="<c:url value="/resources/bower_components/froala-wysiwyg-editor/js/plugins/image_manager.min.js"/>"></script>
  	<script type="text/javascript" src="<c:url value="/resources/bower_components/froala-wysiwyg-editor/js/plugins/inline_style.min.js"/>"></script>
  	<script type="text/javascript" src="<c:url value="/resources/bower_components/froala-wysiwyg-editor/js/plugins/line_breaker.min.js"/>"></script>
  	<script type="text/javascript" src="<c:url value="/resources/bower_components/froala-wysiwyg-editor/js/plugins/link.min.js"/>"></script>
  	<script type="text/javascript" src="<c:url value="/resources/bower_components/froala-wysiwyg-editor/js/plugins/lists.min.js"/>"></script>
  	<script type="text/javascript" src="<c:url value="/resources/bower_components/froala-wysiwyg-editor/js/plugins/paragraph_format.min.js"/>"></script>
  	<script type="text/javascript" src="<c:url value="/resources/bower_components/froala-wysiwyg-editor/js/plugins/paragraph_style.min.js"/>"></script>
  	<script type="text/javascript" src="<c:url value="/resources/bower_components/froala-wysiwyg-editor/js/plugins/quote.min.js"/>"></script>
  	<script type="text/javascript" src="<c:url value="/resources/bower_components/froala-wysiwyg-editor/js/plugins/save.min.js"/>"></script>
  	<script type="text/javascript" src="<c:url value="/resources/bower_components/froala-wysiwyg-editor/js/plugins/table.min.js"/>"></script>
  	<script type="text/javascript" src="<c:url value="/resources/bower_components/froala-wysiwyg-editor/js/plugins/video.min.js"/>"></script>
    <script src="<c:url value="/resources/bower_components/angular/angular.min.js"/>"></script>
    <script src="<c:url value="/resources/js/jsapi.js"/>"></script>
 	<script src="<c:url value="/resources/bower_components/js-xlsx/dist/xlsx.core.min.js"/>"></script>
    <script src="<c:url value="/resources/bower_components/js-xlsx/shim.js"/>"></script>
    <script src="<c:url value="/resources/bower_components/js-xlsx/jszip.js"/>"></script>
    <script src="<c:url value="/resources/bower_components/js-xlsx/xlsx.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/angular-froala.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/underscore-min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/sweetalert.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/moment.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/ngDraggable.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/header.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/app.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/service/admin/AdminService.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/controller/admin/AdminController.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/service/teacher/SubjectsService.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/controller/teacher/SubjectsController.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/service/teacher/SubjectService.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/controller/teacher/SubjectController.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/service/teacher/ExamsService.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/service/teacher/RootExamsService.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/controller/teacher/CreateExamsController.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/controller/teacher/CreateRootExamsController.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/service/teacher/ExamViewService.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/controller/teacher/ExamViewController.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/service/teacher/ExamService.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/controller/teacher/ExamController.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/service/teacher/ClassListService.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/service/teacher/ClassService.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/controller/teacher/ClassController.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/controller/teacher/ClassListController.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/service/student/TestService.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/controller/student/TestController.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/service/teacher/QuestionService.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/controller/teacher/CreateQuestionController.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/service/profile/UserService.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/controller/profile/RegisterController.js"/>"></script>
	
	<script type="text/javascript" src="<c:url value="/resources/controller/teacher/ExportController.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/service/teacher/ExportService.js"/>"></script>
	
	<script type="text/javascript" src="<c:url value="/resources/controller/teacher/StoresController.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/service/teacher/StoresService.js"/>"></script>
	
	<script type="text/javascript" src="<c:url value="/resources/controller/default/NotificationController.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/service/default/NotificationService.js"/>"></script>
	
	
    <script type="text/javascript" src="<c:url value="/resources/controller/teacher/ViewRootExamsController.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/service/teacher/ViewRootExamsService.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/controller/dashboard/DashboardController.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/service/dashboard/DashboardService.js"/>"></script>
     <script type="text/javascript" src="<c:url value="/resources/controller/evaluation/EvaluationController.js"/>"></script>
	 <script type="text/javascript" src="<c:url value="/resources/service/evaluation/EvaluationService.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/toastr.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/katex.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/arrow79.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/bootstrap-datetimepicker.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/moment.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/create_exam.js"/>"></script>
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