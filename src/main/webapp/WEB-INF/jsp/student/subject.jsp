<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>

<div id="class" class="container navbar-default" ng-controller="SubjectController">
    <div class="col-xs-2 content-categories-panel">
        <div class="panel panel-default">
            <div class="panel-heading">{{subject.name}}</div>
            <ul class="nav nav-stacked" id="accordion1">
                <li class="panel" ng-click="showExams()">
                    <a data-toggle="collapse" data-parent="#accordion1" href="#">Đề Thi</a>
                </li>
            </ul>
        </div>
    </div>

    <div id="showExam" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Bài thi đã làm</h4>
                </div>
                <div class="modal-body">
                    <div class="table-responsive">
                        <table class="table table-condensed table-hover table-striped">
                            <thead>
                            <tr>
                                <th class="text-center text-align">Điểm</th>
                                <th class="text-center text-align">Ngày làm</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr ng-repeat="result in resultExamDetailList">
                                <td class="text-center text-align">{{result.score ? result.score : 0}}</td>
                                <td class="text-center text-align">{{result.createdAt}}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
                </div>
            </div>
        </div>
    </div>

    <div class="col-xs-10">
        <div class="row carousel-holder">

            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div style="float: left"><h4>Đề Thi</h4></div>
                        <div style="clear: both"></div>
                    </div>
                    <div class="panel-body" style="padding-bottom:0;">
                        <div class="table-responsive">
                            <table class="table table-condensed table-hover table-striped">
                                <thead>
                                <tr>
                                    <th class="text-center text-align">Tên</th>
                                    <th class="text-center text-align">Số lần đã làm</th>
                                    <th class="text-center text-align">Số lần tối đa</th>
                                    <th class="text-center text-align">Tình Trạng</th>
                                    <th class="text-center text-align">Thao Tác</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr ng-repeat="exams in examsList">
                                    <td class="text-center text-align">{{exams.exams.name}}</td>
                                    <td class="text-center text-align">{{exams.times}}</td>
                                    <td class="text-center text-align">{{exams.exams.times}}</td>
                                    <td class="text-center text-align">{{exams.isOpen ? "MỞ" : "ĐÓNG"}}</td>
                                    <td class="text-center text-align">
                                        <button title="view result" class="btn btn-primary" data-toggle="modal" data-target="#showExam" ng-click="fetchExamsResult(exams.exams.id)">
                                            <i class="fa fa-eye fa-lg" aria-hidden="true"></i>
                                        </button>
                                        <a title="test" ng-show="exams.times < exams.exams.times" class="btn btn-primary"  ng-show="exams.isOpen" href="../class/{{classId}}/exams/{{exams.exams.id}}" >
                                            <i class="fa fa-pencil fa-lg" aria-hidden="true"></i>
                                        </a>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>