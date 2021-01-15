<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/includes/taglibs.jsp" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Thống kê</title>
</head>
<body>

<div ng-controller="DashboardController">
    <div class="container navbar-default" id="createStatistic">

        <div class="col-lg-2 col-md-2 col-sm-2">
            <div class="sidebar-nav-fixed affix">
                <div class="well">
                    <ul class="nav">
                        <li class="panel-header"><strong>NỘI DUNG</strong></li>
                        <li class="active"><a href="#">File kết quả</a></li>
                        <li ng-show="isReadResultSuccess"><a href="#statisticDefault">Thống kê</a></li>
                        <li ng-show="isReadResultSuccess"><a href="#createChart">Tạo Biểu đồ</a></li>
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
            <div class="row" id="statisticDefault" ng-show="isReadResultSuccess">
                <div class="panel panel-default default">
                    <div class="panel-heading"><strong>THỐNG KÊ</strong></div>
                    <div class="panel-body">

                        <div ng-show="isReadResultSuccess" class="panel-body" style="border: 1px solid #ccc">
                            <div class="table-responsive">
                                <button type="button"class="btn btn-default" ng-click="genImage(-3)">Lưu biểu đồ</button>
                                <div id="columnchart"></div>
                            </div>
                        </div>
                        <div ng-show="isReadResultSuccess" class="panel-body" style="border: 1px solid #ccc">
                            <div class="table-responsive">
                                <div class="row">
                                    <div class=" col-lg-4 col-lg-offset-1">
                                        <div class="form-group">
                                            <label for="examCode">Chọn mã đề : </label>
                                            <select id="examCode"
                                                    class="form-control"
                                                    ng-model="examCode"
                                                    ng-change="drawLinechart()"
                                            >
                                                <option ng-repeat="examcode in examcodeList" value="{{examcode}}">
                                                    {{examcode}}
                                                </option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <button type="button"class="btn btn-default" ng-click="genImage(-2)">Lưu biểu đồ</button>
                                <div id="linechart"></div>
                            </div>
                        </div>
                        <div ng-show="isReadResultSuccess" class="panel-body" style="border: 1px solid #ccc">
                            <div class="table-responsive">
                                <button type="button"class="btn btn-default" ng-click="genImage(-1)">Lưu biểu đồ</button>
                                <div id="piechart"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <!-------------- CREATE CHART ------------- -->
            <div class="row" id="createChart" ng-show="isReadResultSuccess">
                <div class="panel panel-default default">
                    <div class="panel-heading"><strong>TẠO BIỂU ĐỒ</strong></div>
                    <div class="panel-body">
                        <div class="col-lg-12">
                            <form id="create_chart" ng-submit="onSubmitCreateChart()">
                                <div class="row">
                                    <div class="col-lg-4 col-lg-offset-1">
                                        <div class="form-group input-group">
                                            <label>Tên biểu đồ :</label>
                                            <input style="border-radius: 10px" class="form-control" type="text"
                                                   name='chartName' placeholder="Nhập Tên Biểu Đồ" ng-model="chartName"
                                                   required/>
                                        </div>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-lg-offset-1">
                                        <div class="form-group">
                                            <label for="examCodeCreate">Chọn mã đề : </label>
                                            <select
                                                    id="examCodeCreate"
                                                    class="form-control"
                                                    style="border-radius:10px;"
                                                    ng-model="examCodeCreate"
                                                    ng-options="examcode.value as examcode.name for examcode in examcodeListAll"
                                                    required
                                            >
                                                <option></option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                </br>
                                <div class="row">
                                    <div class="col-lg-4 col-md-4 col-lg-offset-1">
                                        <div class="form-group">
                                            <label>Chọn biểu đồ :</label>
                                            <select
                                                    class="form-control"
                                                    style="border-radius:10px;"
                                                    ng-model="selectedChart"
                                                    ng-options="chart.value as chart.name for chart in chartList"
                                                    required
                                            >
                                                <option></option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-lg-offset-1">
                                        <div class="form-group">
                                            <label>Chọn loại thống kê :</label>
                                            <select
                                                    class="form-control"
                                                    style="border-radius:10px;"
                                                    ng-model="typeStatistic"
                                                    ng-change="refreshColumn()"
                                                    required
                                            >
                                                <option ng-repeat="typeStatisticElement in typeStatisticList"
                                                        value="{{typeStatisticElement.value}}">
                                                    {{typeStatisticElement.name}}
                                                </option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="row" ng-show="typeStatistic != 2">
                                    <div class="form-group" style="margin: 20px 0px 20px 0px">
                                        <div class="col-lg-2 col-lg-offset-1">
                                            <label style="padding-top: 3px">Câu : Từ câu </label>
                                        </div>
                                        <div class="col-lg-3">
                                            <select
                                                    class="form-control"
                                                    style="border-radius:10px;"
                                                    ng-model="startQuestion"
                                                    ng-options="questionStatistic for questionStatistic in questionStatisticList"
                                            >
                                                <option></option>
                                            </select>
                                        </div>
                                        <div class="col-lg-2">
                                            <label style="padding-top: 3px"> đến câu </label>
                                        </div>
                                        <div class="col-lg-3">
                                            <select
                                                    class="form-control"
                                                    style="border-radius:10px;"
                                                    ng-model="finishQuestion"
                                                    ng-options="questionStatistic for questionStatistic in questionStatisticList"
                                                    min="startQuestion"
                                            >
                                                <option></option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div style="background-color: #eee; margin: 30px 0px 0px 0px">
                                    <div ng-repeat="column in columnList">
                                        <div class="row">
                                            <div class="col-lg-5 col-lg-offset-1" style="margin-top: 15px">
                                                <div class="form-group input-group">
                                                    <label>Nhập tên cột thống kê cho biểu đồ :</label>
                                                    <input style="border-radius: 10px; width: 90%"
                                                           class="form-control"
                                                           type="text"
                                                           name='nameColumn' placeholder="Nhập Tên Cột Thống Kê Cho Biểu Đồ"
                                                           ng-model="column.nameColumn"
                                                           required/>
                                                </div>
                                            </div>
                                            <div class="col-lg-5 col-lg-offset-1" style="margin-top: 15px" ng-show="typeStatistic == 2">
                                                <div class="form-group input-group">
                                                    <label>Nhập tên cột trong file excel :</label>
                                                    <input style="border-radius: 10px; width: 90%"
                                                           class="form-control"
                                                           type="text"
                                                           name='columnRequire'
                                                           placeholder="Nhập Tên Cột Trong Excel "
                                                           ng-model="column.columnRequire"
                                                           ng-required="typeStatistic == 2"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-12 col-lg-offset-1" style="margin-top: 15px">
                                                <div class="col-lg-3 col-md-3 form-group input-group">
                                                    <label>Điều kiện :</label>
                                                </div>
                                                <div class="col-lg-10 col-md-10 form-group input-group">
                                                    <input style="border-radius: 10px; width: 100%" class="form-control"
                                                           type="text"
                                                           name='require' placeholder="Nhập Điều Kiện"
                                                           ng-model="column.require"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row" ng-show="typeStatistic != 2">
                                    <div class="col-lg-6 col-md-6">
                                        <button class="btn btn-success form-control"
                                                ng-click="addColumn(columnList)">
                                            Thêm cột
                                        </button>
                                    </div>
                                    <div class="col-lg-6 col-md-6">
                                        <button class="btn btn-danger form-control"
                                                ng-click="deleteColumn(columnList)">
                                            Xóa cột
                                        </button>
                                    </div>
                                </div>

                                <div class="row" style="margin: 40px auto 15px auto">
                                    <div class="col-lg-12 col-md-12">
                                        <button class="btn btn-info form-control" type="submit"
                                                value="Submit">
                                            Tạo biểu đồ
                                        </button>
                                    </div>
                                </div>
                            </form>
                            <div ng-repeat="chartCreated in chartCreatedList"
                                 class="panel-body" style="border: 1px solid #ccc">
                                <div class="table-responsive">
                                    <button style="margin-top: 10px" type="button"class="btn btn-default" ng-click="genImage($index)">Lưu biểu đồ</button>
                                    <div id="{{ 'chartInCreate-' + ($index) }}"></div>
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