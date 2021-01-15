/**
 * Created by anh.dang on 1/20/2017.
 */
/* Controllers */

'use strict';
google.load("visualization", "1", {packages:["corechart", "table"]});
google.setOnLoadCallback(function () {
});
App.controller('DashboardController', ['$scope','$rootScope', 'DashboardService', '$timeout' , function($scope, $rootScope, DashboardService, $timeout) {
    var self = $scope;
    var selfRoot = $rootScope;
    selfRoot.listResult =[];
    self.isReadResultSuccess = false;
    self.isCreateChart = false;
    self.examcodeList = [];
    self.examcodeListAll = [];
    self.examCodeCreate;
    self.examCode;
    self.chartList = [];

    self.columnList = [];
    self.chartName;
    self.selectedChart;
    self.questionStatisticList = [];
    self.chartList = [];

    self.chartCreatedList = [];

    selfRoot.coutID = 0;

    self.typeStatisticList = [];

    selfRoot.sendArray;
    selfRoot.numberQuestionFirstLine;

    self.charColumnUrl = "";
    self.charLineUrl = "";
    self.charPieUrl = "";

    self.listSaveChar =[];

    self.isShowNext = false;

    self.addTypeStatistic = function(){
        self.typeStatisticList.push({
            value: 1,
            name: "Thống kê mỗi đối tượng là 1 cột"
        });
        self.typeStatisticList.push({
            value: 2,
            name: "Thống kê đối tượng mà các thành phần đối tượng là 1 cột"
        });
        self.typeStatisticList.push({
            value: 3,
            name: "Thống kê đối tượng theo câu hỏi"
        });

    }

    self.addColumnDefault = function(){
        self.columnList.push({
            nameColumn: null,
            columnRequire: null,
            require: null
        });
    };
    self.addColumn = function(columnList){
        self.columnList.push({
            nameColumn: null,
            columnRequire: null,
            require: null
        });
    };

    self.deleteColumn = function(columnList){
        self.columnList.pop();
    };

    self.sendArray = function (numberQuestion, resultArray) {
        DashboardService.sendResult(numberQuestion, resultArray)
            .then(
                function (result) {
                    selfRoot.listResult = result;
                    self.fetchAllExamCodeOfResultFile();
                    self.isReadResultSuccess = true;
                },
                function (errResponse) {
                    console.error('Lỗi khi đọc dữ liệu file');
                    toastr.error('', 'Error', {timeOut: 1000});
                });
    };

    function to_csv(workbook) {
        var result = [];
        workbook.SheetNames.forEach(function(sheetName) {
            var csv = XLSX.utils.sheet_to_csv(workbook.Sheets[sheetName]);
            selfRoot.sendArray = XLSX.utils.sheet_to_json(workbook.Sheets[sheetName]);
            var firstLine = csv.split('\n')[0];

            var columnFirstLine = firstLine.split(',');

            var re = new RegExp("C[0-9]+");
            var numberQuestionFirstLine = 0;
            for (var i=0; i<columnFirstLine.length;i++){
                if(re.test(columnFirstLine[i])){
                    numberQuestionFirstLine = numberQuestionFirstLine + 1;
                }
            }
            selfRoot.numberQuestionFirstLine = numberQuestionFirstLine;
            self.sendArray(numberQuestionFirstLine, selfRoot.sendArray);
            for (var i=1; i<=numberQuestionFirstLine; i++){
                self.questionStatisticList.push(i);
            }
            self.startQuestion = self.questionStatisticList[0];
            self.finishQuestion = self.questionStatisticList[self.questionStatisticList.length - 1];

            if(csv.length > 0){
                result.push("SHEET: " + sheetName);
                result.push("");
                result.push(csv);
            }
        });
        return result.join("\n");
    }

    function process_wb(wb) {
        var output = "";
        output = to_csv(wb);
        if(out.innerText === undefined) out.textContent = output;
        else out.innerText = output;
    }

    function fixdata(data) {
        var o = "", l = 0, w = 10240;
        for(; l<data.byteLength/w; ++l) o+=String.fromCharCode.apply(null,new Uint8Array(data.slice(l*w,l*w+w)));
        o+=String.fromCharCode.apply(null, new Uint8Array(data.slice(l*w)));
        return o;
    }

    function handleFile(e) {
        var files = e.target.files;
        var f = files[0];
        {
            var reader = new FileReader();
            var name = f.name;
            reader.onload = function(e) {
                var data = e.target.result;
                var wb;
                var arr = fixdata(data);
                wb = XLSX.read(btoa(arr), {type: 'base64'});
                process_wb(wb);
            };
            reader.readAsArrayBuffer(f);
        }
    }

    self.drawColumnchart = function(){
        self.columnChart = "ColumnChart";

        DashboardService.fetchDataForChart(self.columnChart, null, selfRoot.listResult, selfRoot.numberQuestionFirstLine)
            .then(
                function (result) {
                    var data = new google.visualization.DataTable();
                    data.addColumn('string', 'Điểm số');
                    data.addColumn('number', 'Số thí sinh');
                    for (var i=0; i<result.length; i++){
                        data.addRows([
                            [result[i].rowData,result[i].valueData[0]]
                        ]);
                    }
                    var options = {
                        title: 'Thống kê điểm số',
                        'height':500
                    };
                    var chart = new google.visualization.ColumnChart(document.getElementById('columnchart'));
                    google.visualization.events.addListener(chart, 'ready', function () {
                    self.charColumnUrl = chart.getImageURI();
                    });
                    chart.draw(data, options);
                },
                function (errResponse) {
                    console.error('Error while fetch data for chart');
                    self.isReadResultSuccess = false;
                    toastr.error('', 'Lỗi khi lấy dữ liệu cho biểu đồ', {timeOut: 1000});
                });
    };

    self.drawLinechart = function() {
        self.lineChart = "LineChart";
        DashboardService.fetchDataForChart(self.lineChart, self.examCode, selfRoot.listResult)
            .then(
                function (result) {
                    var data = new google.visualization.DataTable();
                    data.addColumn('string', 'Câu');
                    data.addColumn('number', 'Số lượng trả lời đúng');
                    data.addColumn('number', 'Số lượng trả lời sai');
                    data.addColumn('number', 'Số lượng không trả lời');

                    for (var i=0; i<result.length; i++){
                        var listData = [];
                        listData.push(result[i].rowData)
                        for (var j=0; j<result[i].valueData.length; j++){
                            listData.push(result[i].valueData[j]);
                        }
                        data.addRows([
                            listData
                        ]);
                    }
                    var options = {
                        title: 'Thống kê số lượng trả lời đúng sai theo câu hỏi',
                        'height':500
                    };
                    var chart = new google.visualization.LineChart(document.getElementById('linechart'));
                    google.visualization.events.addListener(chart, 'ready', function () {
                        self.charLineUrl = chart.getImageURI();
                    });
                    chart.draw(data, options);
                },
                function (errResponse) {
                    console.error('Error while fetch data for chart');
                    self.isReadResultSuccess = false;
                    toastr.error('', 'Lỗi khi lấy dữ liệu cho biểu đồ', {timeOut: 1000});
                });
    };

    self.drawPiechart = function() {
        self.pieChart = "PieChart";

        DashboardService.fetchDataForChart(self.pieChart, null, selfRoot.listResult)
            .then(
                function (result) {
                    var data = new google.visualization.DataTable();
                    data.addColumn('string', 'Đối tượng');
                    data.addColumn('number', 'Tỉ lệ');

                    for (var i=0; i<result.length; i++){
                        data.addRows([
                            [result[i].rowData,result[i].valueData[0]]
                        ]);
                    }
                    var options = {
                        title: 'Thống kê tỉ lệ đúng sai',
                        'height':700
                    };
                    var chart = new google.visualization.PieChart(document.getElementById('piechart'));
                    google.visualization.events.addListener(chart, 'ready', function () {
                        self.charPieUrl = chart.getImageURI();
                    });
                    chart.draw(data, options);
                },
                function (errResponse) {
                    console.error('Error while fetch data for chart');
                    self.isReadResultSuccess = false;
                    toastr.error('', 'Lỗi khi lấy dữ liệu cho biểu đồ', {timeOut: 1000});
                });
    };

    self.fetchAllExamCodeOfResultFile = function(){
        DashboardService.fetchAllExamCodeOfResultFile(selfRoot.listResult)
            .then(
                function(result) {
                    if (result.length > 0) {
                        self.examcodeList = result;

                        self.examcodeListAll.push({
                            name: "Tất cả các mã đề",
                            value: "all"
                        });
                        for (var i=0; i<result.length; i++){
                            self.examcodeListAll.push({
                                value: result[i],
                                name: result[i]
                            })
                        }
                        self.examCode = result[0];
                        self.drawLinechart(selfRoot.listResult);
                        self.drawPiechart();
                        self.drawColumnchart();
                    }
                    else {
                        self.isReadResultSuccess = false;
                        console.error('Lỗi khi lấy mã đề');
                        toastr.error('', 'Lỗi khi lấy dữ liệu cho biểu đồ', {timeOut: 3000});
                    }
                    if (self.isReadResultSuccess == true) {
                        toastr.success('', 'Thành Công', {timeOut: 3000, positionClass : "toast-top-right"});
                    }
                },
                function(errResponse){
                    self.isReadResultSuccess = false;
                    console.error('Lỗi khi lấy mã đề');
                    toastr.error('', 'Lỗi khi lấy dữ liệu cho biểu đồ', {timeOut: 1000});
                }
            );
    };

    self.fetchChartList = function() {
        DashboardService.fetchChartList()
            .then(
                function (result) {
                    for (var i=0; i<result.length; i++){
                        if (result[i] === "ColumnChart"){
                            var chart = {
                                value : result[i],
                                name: "Biểu đồ cột"
                            };
                        }
                        if (result[i] === "LineChart"){
                            var chart = {
                                value : result[i],
                                name: "Biểu đồ đường"
                            };
                        }
                        if (result[i] === "PieChart"){
                            var chart = {
                                value : result[i],
                                name: "Biểu đồ tròn"
                            };
                        }
                        if (result[i] === "TableChart"){
                            var chart = {
                                value : result[i],
                                name: "Biểu đồ bảng"
                            };
                        }
                        if (result[i] === "BarChart"){
                            var chart = {
                                value : result[i],
                                name: "Biểu đồ thanh ngang"
                            };
                        }
                        if (result[i] === "ScatterChart"){
                            var chart = {
                                value : result[i],
                                name: "Biểu đồ điểm"
                            };
                        }
                        self.chartList.push(chart);
                    }
                },
                function (errResponse) {
                    console.error('Lỗi khi get list chart');
                });
    };

    self.onSubmitCreateChart = function() {
        if (self.typeStatistic != 2) {
            if (self.finishQuestion >= self.startQuestion) {
                DashboardService.sendCreateChart(self.chartName, self.examCodeCreate,
                    self.selectedChart, self.startQuestion, self.finishQuestion, self.columnList,
                    self.typeStatistic, selfRoot.listResult, selfRoot.sendArray)
                    .then(
                        function (result) {
                           /* console.log(result);
                            self.chartCreatedList.push(selfRoot.coutID);
                            self.drawCreateChart(result, self.chartName, self.selectedChart, self.columnList);*/
                            self.showNext(result);
                        },
                        function (errResponse) {
                            console.error('Lỗi khi tạo biểu đồ');
                            toastr.error('', 'Lỗi khi tạo biểu đồ', {timeOut: 3000});
                        });
            }
            else {
                console.error('Câu cuối phải lớn hơn hoặc bằng câu đầu');
                toastr.error('', 'Câu cuối phải lớn hơn hoặc bằng câu đầu', {timeOut: 3000});
            }
        }
        else {
            DashboardService.sendCreateChart(self.chartName, self.examCodeCreate,
                self.selectedChart, 0, 0, self.columnList,
                self.typeStatistic, selfRoot.listResult, selfRoot.sendArray)
                .then(
                    function (result) {
                        console.log(result);
                        if (result.length > 0){
                            /*self.chartCreatedList.push(selfRoot.coutID);
                            self.drawCreateChart(result, self.chartName, self.selectedChart, self.columnList);*/
                            self.showNext(result);
                        }
                        else {
                            console.error('Lỗi khi tạo biểu đồ');
                            toastr.error('', 'Biểu đồ không có đối tượng', {timeOut: 5000});
                        }

                    },
                    function (errResponse) {
                        console.error('Lỗi khi tạo biểu đồ');
                        toastr.error('', 'Lỗi khi tạo biểu đồ', {timeOut: 5000});
                    });
        }
    };

    self.showNext = function(result) {
        $timeout(function () { self.drawCreateChart(result, self.chartName, self.selectedChart, self.columnList); }, 2000);
        self.chartCreatedList.push(selfRoot.coutID);
    }


    self.drawCreateChart = function(result, chartName, typeChart, columnList){

        if (self.isReadResultSuccess == true){
            self.isCreateChart = true;
        }
        var data = new google.visualization.DataTable();
        if (result[0].typeStatistic == 1){

            data.addColumn('string', 'Đối tượng');
            data.addColumn('number', 'Giá trị');
            for (var i=0; i<result.length; i++){
                var listData = [];
                listData.push(result[i].rowData);
                for (var j=0; j<result[i].valueData.length; j++){
                    listData.push(result[i].valueData[j]);
                }
                data.addRows([
                    listData
                ]);
            }
        }
        else if (result[0].typeStatistic == 2) {

            data.addColumn('string', columnList[0].nameColumn);
            data.addColumn('number', 'Số lượng');
            for (var i=0; i<result.length; i++){
                var listData = [];
                listData.push(result[i].rowData);
                listData.push(result[i].valueData[0]);
                data.addRows([
                    listData
                ]);
            }


        }
        else {
            data.addColumn('string', 'Câu');
            for (var i=0; i< columnList.length; i++){
                data.addColumn('number', columnList[i].nameColumn);
            }
            for (var i=0; i<result.length; i++){
                var listData = [];
                listData.push(result[i].rowData)
                for (var j=0; j<result[i].valueData.length; j++){
                    listData.push(result[i].valueData[j]);
                }
                data.addRows([
                    listData
                ]);
            }
        }
        if ((typeChart === "ColumnChart")&&(self.typeStatistic != 1)) {
            var options = {
                title: chartName,
                'height':500,
                'width': 1200
            };
        }
        else {
            var options = {
                title: chartName,
                'height':500,
                'width': 750
            };
        }
        var id = 'chartInCreate-' + (selfRoot.coutID);
        var chart;
        if (typeChart === "ColumnChart") {
            chart = new google.visualization.ColumnChart(document.getElementById(id));
        }
        if (typeChart === "LineChart") {
            chart = new google.visualization.LineChart(document.getElementById(id));
        }
        if (typeChart === "PieChart") {
            chart = new google.visualization.PieChart(document.getElementById(id));
        }
        if (typeChart === "BarChart") {
            chart = new google.visualization.BarChart(document.getElementById(id));
        }
        if (typeChart === "TableChart") {
            chart = new google.visualization.Table(document.getElementById(id));
        }
        if (typeChart === "ScatterChart") {
            chart = new google.visualization.ScatterChart(document.getElementById(id));
        }
        console.log(selfRoot.coutID);
        google.visualization.events.addListener(chart, 'ready', function () {
            self.listSaveChar.push(
                {
                    number: selfRoot.coutID,
                    url: chart.getImageURI()
                }
            );
            selfRoot.coutID = selfRoot.coutID + 1;
        });

        chart.draw(data, options);
        self.columnList = [];
        self.addColumnDefault();
        self.selectedChart = "";
        self.startQuestion = self.questionStatisticList[0];
        self.finishQuestion = self.questionStatisticList[self.questionStatisticList.length - 1];
        self.chartName ="";

    };

    self.refreshColumn = function(){
        self.columnList = [];
        self.addColumnDefault();
        self.startQuestion = self.questionStatisticList[0];
        self.finishQuestion = self.questionStatisticList[self.questionStatisticList.length - 1];
    }

    self.genImage = function (numberType) {
        console.log(numberType);
        var type;
        if (numberType === -3){
            type = self.charColumnUrl;
        }
        if (numberType === -2){
            type = self.charLineUrl;
        }
        if (numberType === -1){
            type = self.charPieUrl;
        }
       else {
            for (var i=0; i<self.listSaveChar.length; i++){
                console.log(self.listSaveChar[i]);
                if (numberType === self.listSaveChar[i].number){
                    type = self.listSaveChar[i].url;
                }
            }
        }
        var win = window.open(type, '_blank');
        win.focus();
        var a = $("<a>").attr("href", type).attr("download", "img.png").appendTo("body");
        a[0].click();
        a.remove();
    }

    var xlf = document.getElementById('uploadfile');
    if(xlf.addEventListener) xlf.addEventListener('change', handleFile, false);
    self.addColumnDefault();
    self.fetchChartList();
    self.addTypeStatistic();

}]);