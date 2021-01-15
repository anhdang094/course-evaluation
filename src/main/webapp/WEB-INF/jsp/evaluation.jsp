<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/includes/taglibs.jsp" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Đánh giá</title>
</head>
<body>

<div ng-controller="EvaluationController">
    <div class="container-fluid navbar-default" id="createEvaluation">

        <div class="col-lg-2 col-md-2 col-sm-2">
            <div class="sidebar-nav-fixed affix">
                <div class="well">
                    <ul class="nav">
                        <li class="panel-header"><strong>NỘI DUNG</strong></li>
                        <li class="active"><a href="#">File kết quả</a></li>
                        <li ng-show="isReadResultSuccess"><a href="#inputdata">Nhập dữ liệu</a></li>
                        <li ng-show="isEvaluate"><a href="#evaluate">Đánh giá</a></li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="col-lg-10 col-md-10 col-sm-10">
            <div class="row">
                <div class="panel panel-default default">
                    <div class="panel-heading"><strong>FILE KẾT QUẢ</strong></div>
                    <div class="panel-body">
                        <div class="panel-body" style="padding-bottom:0">
                            <div class="table-responsive">
                                <table class="table table-condensed table-hover table-striped">
                                    <tr>
                                        <th class="text-center text-align upload">Tải lên file kết quả:</th>
                                        <th class="text-center text-align"><input type="file" name="xlfile"
                                                                                  id="uploadfile"/></th>
                                    </tr>

                                </table>
                            </div>
                        </div>
                        <div ng-show="isReadResultSuccess" class="panel-body" style="border: 1px solid #ccc">
                            <div class="table-responsive">
                                <div style="height: 350px;">
                                    <pre id="out"></pre>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div class="row" id="inputdata" ng-show="isReadResultSuccess">
                <div class="panel panel-default default">
                    <div class="panel-heading"><strong>NHẬP DỮ LIỆU</strong></div>
                    <div class="panel-body">
                        <div class="col-lg-12">
                            <form id="create_chart" ng-submit="onSubmitEvaluation()">
                                <div class="row carousel-holder">
                                    <div class=" col-lg-4 col-lg-offset-1">
                                        <div class="form-group">
                                            <label for="examID">Môn Học : </label>
                                            <select
                                                    id="examID"
                                                    class="form-control"
                                                    ng-model="subjectId"
                                                    ng-options="subject.id as subject.name for subject in subjectList"
                                                    ng-change="onChangeSelectedSubject()"
                                                    required
                                            >
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-lg-4 col-lg-offset-1" ng-show="isSelectedSubject">
                                        <div class="form-group">
                                            <label for="examCode">Chọn mã đề : </label>
                                            <select id="examCode"
                                                    class="form-control"
                                                    ng-model="examCode"
                                                    required
                                            >
                                                <option ng-repeat="examcode in examcodeListAll" value="{{examcode.value}}">
                                                    {{examcode.name}}
                                                </option>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-lg-12" ng-show="isSelectedSubject">
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <div style="float: left"><h4>Nhập câu hỏi theo độ khó các chương</h4></div>
                                            <button style="float: right" data-toggle="modal"
                                                    data-target="#createChapter">
                                                <i class="fa fa-plus-square fa-2x" aria-hidden="true"></i>
                                            </button>
                                            <div style="clear: both"></div>
                                        </div>
                                        <div class="panel-body" style="padding-bottom:0;">
                                            <div class="table-responsive">
                                                <div class="list-group">
                                                    <div class="list-group-item" ng-repeat="chapter in chapterList">
                                                        <div style="cursor:pointer" ng-click="onClickChapter(chapter)">
                                                            {{chapter.name}}
                                                            <span ng-show="!!chapter.selectedQuestionCount && chapter.selectedQuestionCount > 0"
                                                                  class="badge" style="background-color:#0046d3">
											{{chapter.selectedQuestionCount}}
										</span>
                                                            <span class="badge"
                                                                  style="float:right; background-color:#781">
											<i ng-hide="chapter.isShow" class="fa fa-arrow-circle-down fa-lg"
                                               aria-hidden="true"></i>
											<i ng-show="chapter.isShow" class="fa fa-arrow-circle-right fa-lg"
                                               aria-hidden="true"></i>
										</span>

                                                        </div>
                                                        <div ng-show="chapter.isShow">
                                                            <div ng-repeat="target in chapter.targetList">
                                                                <div class="row"
                                                                     style="background: #7A5; color: aliceblue; margin-top: 5px">
                                                                    {{target.content}}
                                                                </div>
                                                                <div class="row"
                                                                     style="background: #afd9ee; margin-top: 5px">
                                                                    <div class="col-lg-2">
                                                                        Level 1:
                                                                    </div>
                                                                    <div class="col-lg-10">
                                                                        <input type="text" name="lv1"
                                                                               ng-model="target.lv1"
                                                                               style="width: 100%">
                                                                    </div>
                                                                </div>
                                                                <div class="row"
                                                                     style="background: #afd9ee; margin-top: 5px">
                                                                    <div class="col-lg-2">
                                                                        Level 2:
                                                                    </div>
                                                                    <div class="col-lg-10">
                                                                        <input type="text" name="lv2"
                                                                               ng-model="target.lv2"
                                                                               style="width: 100%">
                                                                    </div>
                                                                </div>
                                                                <div class="row"
                                                                     style="background: #afd9ee; margin-top: 5px">
                                                                    <div class="col-lg-2">
                                                                        Level 3:
                                                                    </div>
                                                                    <div class="col-lg-10">
                                                                        <input type="text" name="lv3"
                                                                               ng-model="target.lv3"
                                                                               style="width: 100%">
                                                                    </div>
                                                                </div>
                                                                <div class="row"
                                                                     style="background: #afd9ee; margin-top: 5px">
                                                                    <div class="col-lg-2">
                                                                        Level 4:
                                                                    </div>
                                                                    <div class="col-lg-10">
                                                                        <input type="text" name="lv4"
                                                                               ng-model="target.lv4"
                                                                               style="width: 100%">
                                                                    </div>
                                                                </div>
                                                                <div class="row"
                                                                     style="background: #afd9ee; margin-top: 5px">
                                                                    <div class="col-lg-2">
                                                                        Level 5:
                                                                    </div>
                                                                    <div class="col-lg-10">
                                                                        <input type="text" name="lv5"
                                                                               ng-model="target.lv5"
                                                                               style="width: 100%">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row" style="margin: 40px auto 15px auto" ng-show="isSelectedSubject">
                                    <div class="col-lg-12 col-md-12">
                                        <button class="btn btn-success form-control" type="submit"
                                                value="Submit">
                                            Đánh giá
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>


            <div class="row" ng-show="isEvaluate" id="evaluate">
                <div class="panel panel-default default">
                    <div class="panel-heading"><strong>KẾT QUẢ ĐÁNH GIÁ</strong></div>
                    <div class="panel-body">
                        <div class="panel-body" style="padding-bottom:0">
                            <div class="table-responsive">

                                <div class="row">
                                    <div class="col-lg-12" style="background-color: #2b542c; color: white">
                                        MÃ ĐỀ: {{examCode}}
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-lg-12 header-evaluate" style="margin-top: 30px">
                                        ĐÁNH GIÁ THEO CÁC CHƯƠNG
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-lg-11 col-md-11 col-lg-offset-1 col-md-offset-1">
                                        <div class="row" style="margin-top: 10px">
                                            Các chương ra trong đề:
                                        </div>
                                        <div class="row">

                                            <div ng-repeat="chapter in chapter_evaluation_chapterList">
                                                <span> - </span>
                                                <span>{{chapter.chapter.name}}</span>
                                                <span> : </span>
                                                <span>{{chapter.numberOfChapter}}</span>
                                                <span> (câu hỏi)</span>
                                            </div>
                                        </div>

                                        <div class="row" style="margin-top: 10px">
                                            Các chương chưa ra:
                                        </div>
                                        <div class="row">

                                            <div ng-repeat="chapterNotHave in chapter_evaluation_chapterListNotHave">
                                                <span> - </span>
                                                <span>{{chapterNotHave.name}}</span>
                                            </div>
                                        </div>

                                        <div class="row" style="margin-top: 10px">
                                            Chương dễ nhất:
                                        </div>
                                        <div class="row">

                                            <div ng-repeat="chapterEasy in chapterEasyMost">
                                                <span> - </span>
                                                <span>{{chapterEasy.chapterElement.name}}</span>
                                                <span> ( {{chapterEasy.listTrueFalse[0]}} đúng, </span>
                                                <span> {{chapterEasy.listTrueFalse[1]}} sai, </span>
                                                <span> {{chapterEasy.listTrueFalse[2]}} không trả lời - </span>
                                                <span> đúng chiếm tỉ lệ: {{chapterEasy.percentTrue}} % ) </span>
                                            </div>


                                        </div>

                                        <div class="row" style="margin-top: 10px">
                                            Chương khó nhất:
                                        </div>
                                        <div class="row">

                                            <div ng-repeat="chapterDiff in chapterDifficult">
                                                <span> - </span>
                                                <span>{{chapterDiff.chapterElement.name}}</span>
                                                <span> ( {{chapterDiff.listTrueFalse[0]}} đúng, </span>
                                                <span> {{chapterDiff.listTrueFalse[1]}} sai, </span>
                                                <span> {{chapterDiff.listTrueFalse[2]}} không trả lời - </span>
                                                <span> đúng chiếm tỉ lệ: {{chapterDiff.percentTrue}} % ) </span>
                                            </div>
                                        </div>

                                        <div class="row" style="margin-top: 20px">
                                            <div class=" col-lg-6">
                                                <div class="form-group">
                                                    <label for="chapter">Chọn chương : </label>
                                                    <select id="chapter"
                                                            class="form-control"
                                                            ng-model="chapterEvaluation"
                                                            ng-change="onChangeEvaluationByChapter(chapterEvaluation)"
                                                    >
                                                        <option ng-repeat="chapter in chapter_evaluation_chapterList"
                                                                value="{{chapter.chapter.id}}">
                                                            {{chapter.chapter.name}}
                                                        </option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row" ng-show="isShowEvaluateChapter">
                                            <div class="row" style="margin-top: 10px">
                                                <span>Các câu hỏi thuộc chương đã chọn : </span>
                                                <span ng-repeat="questionEvaluationByChapter in listQuestionEvaluationByChapter">
                                                    Câu {{questionEvaluationByChapter}},
                                                </span>
                                            </div>
                                            <div class="row" style="margin-top: 10px">
                                                <span>Tỉ lệ đúng, sai, không trả lời của chương đã chọn : </span>
                                                <br>
                                                <span>
                                                    - Đúng: {{numberPercentListByTrueFalse[0].number}} câu - chiếm tỉ lệ: {{numberPercentListByTrueFalse[0].percent}}%
                                                    <br>
                                                    - Sai: {{numberPercentListByTrueFalse[1].number}} câu - chiếm tỉ lệ: {{numberPercentListByTrueFalse[1].percent}}%
                                                    <br>
                                                    - Không trả lời: {{numberPercentListByTrueFalse[2].number}} câu - chiếm tỉ lệ: {{numberPercentListByTrueFalse[2].percent}}%
                                                </span>
                                            </div>
                                            <div class="row" style="margin-top: 10px">
                                                <span>Trong đó : </span>
                                                <div class="row">
                                                    <div class="col-lg-2 col-md-2">
                                                        <div class="row">
                                                            <div class="col-lg-2">
                                                                <svg width="20" height="20">
                                                                    <rect width="20" height="20"
                                                                          style="fill:rgb(123,123,0);stroke-width:3;stroke:rgb(0,0,0)"/>
                                                                    Sorry, your browser does not support inline SVG.
                                                                </svg>
                                                            </div>
                                                            <div class="col-lg-9">
                                                                <span> : Không trả lời</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-2 col-md-2">
                                                        <div class="row">
                                                            <div class="col-lg-2">
                                                                <svg width="20" height="20">
                                                                    <rect width="20" height="20"
                                                                          style="fill:rgb(0,0,255);stroke-width:3;stroke:rgb(0,0,0)"/>
                                                                    Sorry, your browser does not support inline SVG.
                                                                </svg>
                                                            </div>
                                                            <div class="col-lg-9">
                                                                <span> : A</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-2 col-md-2">
                                                        <div class="row">
                                                            <div class="col-lg-2">
                                                                <svg width="20" height="20">
                                                                    <rect width="20" height="20"
                                                                          style="fill:rgb(0,255,255);stroke-width:3;stroke:rgb(0,0,0)"/>
                                                                    Sorry, your browser does not support inline SVG.
                                                                </svg>
                                                            </div>
                                                            <div class="col-lg-9">
                                                                <span> : B</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-2 col-md-2">
                                                        <div class="row">
                                                            <div class="col-lg-2">
                                                                <svg width="20" height="20">
                                                                    <rect width="20" height="20"
                                                                          style="fill:rgb(255,0,0);stroke-width:3;stroke:rgb(0,0,0)"/>
                                                                    Sorry, your browser does not support inline SVG.
                                                                </svg>
                                                            </div>
                                                            <div class="col-lg-9">
                                                                <span> : C</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-2 col-md-2">
                                                        <div class="row">
                                                            <div class="col-lg-2">
                                                                <svg width="20" height="20">
                                                                    <rect width="20" height="20"
                                                                          style="fill:rgb(0,255,0);stroke-width:3;stroke:rgb(0,0,0)"/>
                                                                    Sorry, your browser does not support inline SVG.
                                                                </svg>
                                                            </div>
                                                            <div class="col-lg-9">
                                                                <span> : D</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-2 col-md-2">
                                                        <div class="row">
                                                            <div class="col-lg-2">
                                                                <svg width="20" height="20">
                                                                    <rect width="20" height="20"
                                                                          style="fill:rgb(255,0,255);stroke-width:3;stroke:rgb(0,0,0)"/>
                                                                    Sorry, your browser does not support inline SVG.
                                                                </svg>
                                                            </div>
                                                            <div class="col-lg-9">
                                                                <span> : E</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row">

                                                    <div class="col-lg-12">
                                                        <div ng-repeat="numberPercentByAnswer in numberPercentListByAnswer">
                                                            <span> Câu {{numberPercentByAnswer.numberQuestion}}: </span>
                                                            <br>
                                                            <canvas id="{{ 'rectange-' + ($index) }}" width="800"
                                                                    height="50"/>
                                                        </div>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>

                                    </div>
                                </div>

                                <div class="row" style="margin-top: 50px">
                                    <div class="col-lg-12 header-evaluate">
                                        ĐÁNH GIÁ THEO OUTCOME (MỤC TIÊU - KẾT QUẢ)
                                    </div>
                                </div>
                                <div class="row" style="margin-top: 10px">
                                    <div class="col-lg-11 col-md-11 col-lg-offset-1 col-md-offset-1">
                                        <div class="row" style="margin-top: 10px">
                                            Các outcome ra trong đề:
                                        </div>
                                        <div class="row">
                                            <div ng-repeat="outcome in outcome_evaluation_outcomeList">
                                                <span> - </span>
                                                <span>{{outcome.target.content}}</span>
                                                <span> : </span>
                                                <span>{{outcome.numberOfQuestion}}</span>
                                                <span> (câu hỏi)</span>
                                            </div>
                                        </div>
                                        <div class="row" style="margin-top: 10px">
                                            Outcome dễ nhất:
                                        </div>
                                        <div class="row">

                                            <div ng-repeat="outcomeEasy in outcomeEasyMost">
                                                <span> - </span>
                                                <span>{{outcomeEasy.target.content}}</span>
                                                <span> ( {{outcomeEasy.listTrueFalse[0]}} đúng, </span>
                                                <span> {{outcomeEasy.listTrueFalse[1]}} sai, </span>
                                                <span> {{outcomeEasy.listTrueFalse[2]}} không trả lời - </span>
                                                <span> đúng chiếm tỉ lệ: {{outcomeEasy.percentTrue}} % ) </span>
                                            </div>


                                        </div>

                                        <div class="row" style="margin-top: 10px">
                                            Outcome khó nhất:
                                        </div>
                                        <div class="row">

                                            <div ng-repeat="outcomeDiff in outcomeDifficult">
                                                <span> - </span>
                                                <span>{{outcomeDiff.target.content}}</span>
                                                <span> ( {{outcomeDiff.listTrueFalse[0]}} đúng, </span>
                                                <span> {{outcomeDiff.listTrueFalse[1]}} sai, </span>
                                                <span> {{outcomeDiff.listTrueFalse[2]}} không trả lời - </span>
                                                <span> đúng chiếm tỉ lệ: {{outcomeDiff.percentTrue}} % ) </span>
                                            </div>
                                        </div>

                                        <div class="row" style="margin-top: 20px">
                                            <div class=" col-lg-6">
                                                <div class="form-group">
                                                    <label for="outcomeCode">Chọn outcome : </label>
                                                    <select id="outcomeCode"
                                                            class="form-control"
                                                            ng-model="outcomeEvaluation"
                                                            ng-change="onChangeEvaluationByOutcome(outcomeEvaluation)"
                                                    >
                                                        <option ng-repeat="outcome in outcome_evaluation_outcomeList"
                                                                value="{{outcome.target.id}}">
                                                            {{outcome.target.content}}
                                                        </option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>


                                        <div class="row" ng-show="isShowEvaluateOutcome">
                                            <div class="row" style="margin-top: 10px">
                                                <span>Các câu hỏi thuộc outcome đã chọn : </span>
                                                <span ng-repeat="questionEvaluationByOutcome in listQuestionEvaluationByOutcome">
                                                    Câu {{questionEvaluationByOutcome}},
                                                </span>
                                            </div>
                                            <div class="row" style="margin-top: 10px">
                                                <span>Tỉ lệ đúng, sai, không trả lời của outcome đã chọn : </span>
                                                <br>
                                                <span>
                                                    - Đúng: {{numberPercentListByTrueFalseOutcome[0].number}} câu - chiếm tỉ lệ: {{numberPercentListByTrueFalseOutcome[0].percent}}%
                                                    <br>
                                                    - Sai: {{numberPercentListByTrueFalseOutcome[1].number}} câu - chiếm tỉ lệ: {{numberPercentListByTrueFalseOutcome[1].percent}}%
                                                    <br>
                                                    - Không trả lời: {{numberPercentListByTrueFalseOutcome[2].number}} câu - chiếm tỉ lệ: {{numberPercentListByTrueFalseOutcome[2].percent}}%
                                                </span>
                                            </div>
                                            <div class="row" style="margin-top: 10px">
                                                <span>Trong đó : </span>
                                                <div class="row">
                                                    <div class="col-lg-2 col-md-2">
                                                        <div class="row">
                                                            <div class="col-lg-2">
                                                                <svg width="20" height="20">
                                                                    <rect width="20" height="20"
                                                                          style="fill:rgb(123,123,0);stroke-width:3;stroke:rgb(0,0,0)"/>
                                                                    Sorry, your browser does not support inline SVG.
                                                                </svg>
                                                            </div>
                                                            <div class="col-lg-9">
                                                                <span> : Không trả lời</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-2 col-md-2">
                                                        <div class="row">
                                                            <div class="col-lg-2">
                                                                <svg width="20" height="20">
                                                                    <rect width="20" height="20"
                                                                          style="fill:rgb(0,0,255);stroke-width:3;stroke:rgb(0,0,0)"/>
                                                                    Sorry, your browser does not support inline SVG.
                                                                </svg>
                                                            </div>
                                                            <div class="col-lg-9">
                                                                <span> : A</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-2 col-md-2">
                                                        <div class="row">
                                                            <div class="col-lg-2">
                                                                <svg width="20" height="20">
                                                                    <rect width="20" height="20"
                                                                          style="fill:rgb(0,255,255);stroke-width:3;stroke:rgb(0,0,0)"/>
                                                                    Sorry, your browser does not support inline SVG.
                                                                </svg>
                                                            </div>
                                                            <div class="col-lg-9">
                                                                <span> : B</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-2 col-md-2">
                                                        <div class="row">
                                                            <div class="col-lg-2">
                                                                <svg width="20" height="20">
                                                                    <rect width="20" height="20"
                                                                          style="fill:rgb(255,0,0);stroke-width:3;stroke:rgb(0,0,0)"/>
                                                                    Sorry, your browser does not support inline SVG.
                                                                </svg>
                                                            </div>
                                                            <div class="col-lg-9">
                                                                <span> : C</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-2 col-md-2">
                                                        <div class="row">
                                                            <div class="col-lg-2">
                                                                <svg width="20" height="20">
                                                                    <rect width="20" height="20"
                                                                          style="fill:rgb(0,255,0);stroke-width:3;stroke:rgb(0,0,0)"/>
                                                                    Sorry, your browser does not support inline SVG.
                                                                </svg>
                                                            </div>
                                                            <div class="col-lg-9">
                                                                <span> : D</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-2 col-md-2">
                                                        <div class="row">
                                                            <div class="col-lg-2">
                                                                <svg width="20" height="20">
                                                                    <rect width="20" height="20"
                                                                          style="fill:rgb(255,0,255);stroke-width:3;stroke:rgb(0,0,0)"/>
                                                                    Sorry, your browser does not support inline SVG.
                                                                </svg>
                                                            </div>
                                                            <div class="col-lg-9">
                                                                <span> : E</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row">

                                                    <div class="col-lg-12">
                                                        <div ng-repeat="numberPercentByAnswerOutcome in numberPercentListByAnswerCome">
                                                            <span> Câu {{numberPercentByAnswerOutcome.numberQuestion}}: </span>
                                                            <br>
                                                            <canvas id="{{ 'rectange-outcome-' + ($index) }}"
                                                                    width="800"
                                                                    height="50"/>
                                                        </div>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>


                                    </div>
                                </div>

                                <div class="row" style="margin-top: 50px">
                                    <div class="col-lg-12 header-evaluate">
                                        ĐÁNH GIÁ MỨC ĐỘ TẬP TRUNG CÁC ĐÁP ÁN
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-lg-11 col-md-11 col-lg-offset-1 col-md-offset-1">

                                        <div class="row" style="margin-top: 10px">
                                            Câu có các độ chênh lệch sự lựa chọn các đáp án lớn nhất:
                                        </div>
                                        <div class="row">

                                            <div ng-repeat="dispersalLargestQuestion in dispersalLargestQuestionList">
                                                <span> - </span>
                                                <span>Câu {{dispersalLargestQuestion}}: </span>
                                                <br>
                                                <span> + Lựa chọn phương án A chiếm: </span>
                                                <span> {{dispersalLargestPercentList[5 * $index + 0].percent}} %, </span>
                                                <br>
                                                <span> + Lựa chọn phương án B chiếm: </span>
                                                <span> {{dispersalLargestPercentList[5 * $index + 1].percent}} %, </span>
                                                <br>
                                                <span> + Lựa chọn phương án C chiếm: </span>
                                                <span> {{dispersalLargestPercentList[5 * $index + 2].percent}} %, </span>
                                                <br>
                                                <span> + Lựa chọn phương án D chiếm: </span>
                                                <span> {{dispersalLargestPercentList[5 * $index + 3].percent}} %,</span>
                                                <br>
                                                <span> + Lựa chọn phương án E chiếm: </span>
                                                <span> {{dispersalLargestPercentList[5 * $index + 4].percent}} %,</span>
                                                <br>
                                                <span> + Không lựa chọn chiếm: </span>
                                                <span> {{dispersalLargestPercentList[5 * $index + 5].percent}} % </span>
                                            </div>

                                        </div>

                                        <div class="row" style="margin-top: 10px">
                                            Câu có mức chênh lệch sự lựa chọn các đáp án thấp nhất:
                                        </div>
                                        <div class="row">

                                            <div ng-repeat="dispersalSmallestQuestion in dispersalSmallestQuestionList">
                                                <span> - </span>
                                                <span>Câu {{dispersalSmallestQuestion}}: </span>
                                                <br>
                                                <span> + Lựa chọn phương án A chiếm: </span>
                                                <span> {{dispersalSmallestPercentList[5 * $index + 0].percent}} %, </span>
                                                <br>
                                                <span> + Lựa chọn phương án B chiếm: </span>
                                                <span> {{dispersalSmallestPercentList[5 * $index + 1].percent}} %, </span>
                                                <br>
                                                <span> + Lựa chọn phương án C chiếm: </span>
                                                <span> {{dispersalSmallestPercentList[5 * $index + 2].percent}} %, </span>
                                                <br>
                                                <span> + Lựa chọn phương án D chiếm: </span>
                                                <span> {{dispersalSmallestPercentList[5 * $index + 3].percent}} %,</span>
                                                <br>
                                                <span> + Lựa chọn phương án E chiếm: </span>
                                                <span> {{dispersalSmallestPercentList[5 * $index + 4].percent}} %,</span>
                                                <br>
                                                <span> + Không lựa chọn chiếm: </span>
                                                <span> {{dispersalSmallestPercentList[5 * $index + 5].percent}} %</span>
                                            </div>
                                        </div>

                                        <div class="row" style="margin-top: 10px">
                                            <span>Trong đó : </span>
                                            <div class="row">
                                                <div class="col-lg-2 col-md-2">
                                                    <div class="row">
                                                        <div class="col-lg-2">
                                                            <svg width="20" height="20">
                                                                <rect width="20" height="20"
                                                                      style="fill:rgb(123,123,0);stroke-width:3;stroke:rgb(0,0,0)"/>
                                                                Sorry, your browser does not support inline SVG.
                                                            </svg>
                                                        </div>
                                                        <div class="col-lg-9">
                                                            <span> : Không trả lời</span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-2 col-md-2">
                                                    <div class="row">
                                                        <div class="col-lg-2">
                                                            <svg width="20" height="20">
                                                                <rect width="20" height="20"
                                                                      style="fill:rgb(0,0,255);stroke-width:3;stroke:rgb(0,0,0)"/>
                                                                Sorry, your browser does not support inline SVG.
                                                            </svg>
                                                        </div>
                                                        <div class="col-lg-9">
                                                            <span> : A</span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-2 col-md-2">
                                                    <div class="row">
                                                        <div class="col-lg-2">
                                                            <svg width="20" height="20">
                                                                <rect width="20" height="20"
                                                                      style="fill:rgb(0,255,255);stroke-width:3;stroke:rgb(0,0,0)"/>
                                                                Sorry, your browser does not support inline SVG.
                                                            </svg>
                                                        </div>
                                                        <div class="col-lg-9">
                                                            <span> : B</span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-2 col-md-2">
                                                    <div class="row">
                                                        <div class="col-lg-2">
                                                            <svg width="20" height="20">
                                                                <rect width="20" height="20"
                                                                      style="fill:rgb(255,0,0);stroke-width:3;stroke:rgb(0,0,0)"/>
                                                                Sorry, your browser does not support inline SVG.
                                                            </svg>
                                                        </div>
                                                        <div class="col-lg-9">
                                                            <span> : C</span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-2 col-md-2">
                                                    <div class="row">
                                                        <div class="col-lg-2">
                                                            <svg width="20" height="20">
                                                                <rect width="20" height="20"
                                                                      style="fill:rgb(0,255,0);stroke-width:3;stroke:rgb(0,0,0)"/>
                                                                Sorry, your browser does not support inline SVG.
                                                            </svg>
                                                        </div>
                                                        <div class="col-lg-9">
                                                            <span> : D</span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-2 col-md-2">
                                                    <div class="row">
                                                        <div class="col-lg-2">
                                                            <svg width="20" height="20">
                                                                <rect width="20" height="20"
                                                                      style="fill:rgb(255,0,255);stroke-width:3;stroke:rgb(0,0,0)"/>
                                                                Sorry, your browser does not support inline SVG.
                                                            </svg>
                                                        </div>
                                                        <div class="col-lg-9">
                                                            <span> : E</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>

                                        <div class="row" style="margin-top: 10px">
                                            Các câu có mức chênh lệch sự lựa chọn cao:
                                        </div>
                                        <div class="row">

                                            <div ng-repeat="dispersalLargestQuestionTemplates in dispersalLargestQuestionListTemplates"
                                                 style="margin-top: 10px">
                                                <span> - </span>
                                                <span>Câu {{dispersalLargestQuestionTemplates}}: </span>

                                                <span> ( A: </span>
                                                <span> {{dispersalLargestPercentListTemplates[6 * $index + 0].percent}} %, </span>

                                                <span> B: </span>
                                                <span> {{dispersalLargestPercentListTemplates[6 * $index + 1].percent}} %, </span>

                                                <span> C: </span>
                                                <span> {{dispersalLargestPercentListTemplates[6 * $index + 2].percent}} %, </span>

                                                <span> D: </span>
                                                <span> {{dispersalLargestPercentListTemplates[6 * $index + 3].percent}} %,</span>

                                                <span> E: </span>
                                                <span> {{dispersalLargestPercentListTemplates[6 * $index + 4].percent}} %,</span>

                                                <span> Không lựa chọn: </span>
                                                <span> {{dispersalLargestPercentListTemplates[6 * $index + 5].percent}} %)</span>
                                                <br>
                                                <div class="row" style="margin-top: 10px">
                                                    <canvas id="{{ 'rectange-dispersal-larger-' + (6 * $index ) }}"
                                                            width="800"
                                                            height="50"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row" style="margin-top: 10px">
                                            Các câu có mức chênh lệch sự lựa chọn thấp:
                                        </div>
                                        <div class="row">

                                            <div ng-repeat="dispersalSmallestQuestionTemplates in dispersalSmallestQuestionListTemplates"
                                                 style="margin-top: 10px">
                                                <span> - </span>
                                                <span>Câu {{dispersalSmallestQuestionTemplates}}: </span>

                                                <span> ( A: </span>
                                                <span> {{dispersalSmallestPercentListTemplates[6 * $index + 0].percent}} %, </span>

                                                <span> B: </span>
                                                <span> {{dispersalSmallestPercentListTemplates[6 * $index + 1].percent}} %, </span>

                                                <span> C: </span>
                                                <span> {{dispersalSmallestPercentListTemplates[6 * $index + 2].percent}} %, </span>

                                                <span> D: </span>
                                                <span> {{dispersalSmallestPercentListTemplates[6 * $index + 3].percent}} %,</span>

                                                <span> E: </span>
                                                <span> {{dispersalSmallestPercentListTemplates[6 * $index + 4].percent}} %,</span>

                                                <span> Không lựa chọn: </span>
                                                <span> {{dispersalSmallestPercentListTemplates[6 * $index + 5].percent}} %)</span>
                                                <br>
                                                <div class="row" style="margin-top: 10px">
                                                    <canvas id="{{ 'rectange-dispersal-smaller-' + (6 * $index ) }}"
                                                            width="800"
                                                            height="50"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row" style="margin-top: 50px">
                                    <div class="col-lg-12 header-evaluate">
                                        ĐÁNH GIÁ THEO MỨC ĐỘ KHÓ DỄ
                                    </div>
                                </div>

                                <div class="row" style="margin-top: 10px">
                                    <div class="col-lg-11 col-md-11 col-lg-offset-1 col-md-offset-1">
                                        <div class="row" style="margin-top: 10px">
                                            Các câu sai nhiều:
                                        </div>
                                        <div class="row">
                                            <div ng-repeat="questionMin in questionEasyDiffListMin">
                                                <span> - Câu {{questionMin.numberQuestion}}( level: {{questionMin.level}} - đúng chiếm tỉ lệ: {{questionMin.trueValue}} %)</span>
                                            </div>
                                        </div>
                                        <div class="row" style="margin-top: 10px">
                                            Các câu đúng nhiều:
                                        </div>
                                        <div class="row">
                                            <div ng-repeat="questionMax in questionEasyDiffListMax">
                                                <span> - Câu {{questionMax.numberQuestion}} ( level: {{questionMax.level}} - đúng chiếm tỉ lệ: {{questionMax.trueValue}} %)</span>
                                            </div>
                                        </div>


                                        <div class="row" style="margin-top: 10px">
                                            Nhận xét theo level nhập vào và theo thực tế kết quả:
                                        </div>
                                        <div class="row">
                                            <div ng-repeat="questionLevelChange in questionLevelChangeList">
                                                <span> - Câu</span>
                                                <span ng-repeat="numberQuestionOne in questionLevelChange.numberQuestion">
                                                    {{numberQuestionOne}}
                                                </span>
                                                <span>: </span>
                                                <span> level nhập vào chưa phù hợp với kết quả thực tế.</span>
                                                <br>
                                                <div style="margin-left: 20px">
                                                    <span>Cụ thể: </span>
                                                    <span> level nhập vào: </span>
                                                    <span style="font-weight: bold">{{questionLevelChange.levelBase}} - </span>
                                                    <span> tỉ lệ đúng thực tế: {{questionLevelChange.percentOfQuestion}} %</span>
                                                    <br>
                                                    <span> => phù hợp với level {{questionLevelChange.levelTarget}} hơn - vì tỉ lệ đúng trung bình của level {{questionLevelChange.levelTarget}} là: {{questionLevelChange.averageLevelTarget}} %</span>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row" style="margin-top: 10px">
                                            Thống kê tỉ lệ đúng, sai, không trả lời theo từng level:
                                        </div>


                                        <div class="row" style="margin-top: 10px">
                                            <span>Trong đó : </span>
                                            <div class="row">
                                                <div class="col-lg-2 col-md-2">
                                                    <div class="row">
                                                        <div class="col-lg-2">
                                                            <svg width="20" height="20">
                                                                <rect width="20" height="20"
                                                                      style="fill:rgb(0,255,0);stroke-width:3;stroke:rgb(0,0,0)"/>
                                                                Sorry, your browser does not support inline SVG.
                                                            </svg>
                                                        </div>
                                                        <div class="col-lg-9">
                                                            <span> : Trả lời đúng</span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-2 col-md-2">
                                                    <div class="row">
                                                        <div class="col-lg-2">
                                                            <svg width="20" height="20">
                                                                <rect width="20" height="20"
                                                                      style="fill:rgb(255,0,0);stroke-width:3;stroke:rgb(0,0,0)"/>
                                                                Sorry, your browser does not support inline SVG.
                                                            </svg>
                                                        </div>
                                                        <div class="col-lg-9">
                                                            <span> : Trả lời sai</span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-2 col-md-2">
                                                    <div class="row">
                                                        <div class="col-lg-2">
                                                            <svg width="20" height="20">
                                                                <rect width="20" height="20"
                                                                      style="fill:rgb(255,0,255);stroke-width:3;stroke:rgb(0,0,0)"/>
                                                                Sorry, your browser does not support inline SVG.
                                                            </svg>
                                                        </div>
                                                        <div class="col-lg-9">
                                                            <span> : Không trả lời</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>



                                        <div class="row">
                                            <span> Level 1: ( gồm các câu: </span>
                                            <span ng-repeat="numberlv1 in trueFalseOfLevel_1.listQuestion">
                                                {{numberlv1}}
                                                <span ng-if="$index !== (trueFalseOfLevel_1.listQuestion.length - 1)">,</span>
                                            </span>
                                            <span>)</span>
                                            <div class="row" style="margin-top: 10px">
                                                <canvas id="{{ 'rectange-level-1'}}"
                                                        width="800"
                                                        height="50"/>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <span> Level 2: ( gồm các câu: </span>
                                            <span ng-repeat="numberlv2 in trueFalseOfLevel_2.listQuestion">
                                                {{numberlv2}}
                                                <span ng-if="$index !== (trueFalseOfLevel_2.listQuestion.length - 1)">,</span>
                                            </span>
                                            <span>)</span>
                                            <div class="row" style="margin-top: 10px">
                                                <canvas id="{{ 'rectange-level-2'}}"
                                                        width="800"
                                                        height="50"/>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <span> Level 3: ( gồm các câu: </span>
                                            <span ng-repeat="numberlv3 in trueFalseOfLevel_3.listQuestion">
                                                {{numberlv3}}
                                                <span ng-if="$index !== (trueFalseOfLevel_3.listQuestion.length - 1)">,</span>
                                            </span>
                                            <span>)</span>
                                            <div class="row" style="margin-top: 10px">
                                                <canvas id="{{ 'rectange-level-3'}}"
                                                        width="800"
                                                        height="50"/>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <span> Level 4: ( gồm các câu: </span>
                                            <span ng-repeat="numberlv4 in trueFalseOfLevel_4.listQuestion">
                                                {{numberlv4}}
                                                <span ng-if="$index !== (trueFalseOfLevel_4.listQuestion.length - 1)">,</span>
                                            </span>
                                            <span>)</span>
                                            <div class="row" style="margin-top: 10px">
                                                <canvas id="{{ 'rectange-level-4' }}"
                                                        width="800"
                                                        height="50"/>
                                            </div>
                                        </div>

                                        <div class="row" style="margin-bottom: 30px">
                                            <span> Level 5: ( gồm các câu: </span>
                                            <span ng-repeat="numberlv5 in trueFalseOfLevel_5.listQuestion">
                                                {{numberlv5}}
                                                <span ng-if="$index !== (trueFalseOfLevel_5.listQuestion.length - 1)">,</span>
                                            </span>
                                            <span>)</span>
                                            <div class="row" style="margin-top: 10px">
                                                <canvas id="{{ 'rectange-level-5'}}"
                                                        width="800"
                                                        height="50"/>
                                            </div>
                                        </div>

                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>


        </div>
    </div>
</div>
</body>
</html>