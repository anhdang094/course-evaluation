/**
 * Created by anh.dang on 1/18/2017.
 */
App.factory('DashboardService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/EvaluationSource/api/';

    var factory = {
        sendResult: sendResult,
        fetchAllExamCodeOfResultFile: fetchAllExamCodeOfResultFile,
        fetchDataForChart: fetchDataForChart,
        fetchChartList: fetchChartList,
        sendCreateChart: sendCreateChart,
    };

    return factory;

    function sendResult(numberQuestion, resultArray) {
        var deferred = $q.defer();
        var data = {
            numberQuestion: numberQuestion,
            resultArray: resultArray,
        };
        $http({
            method: 'POST',
            url: REST_SERVICE_URI + 'sendResult',
            data: data
        }).then(function (response) {
                deferred.resolve(response.data);
            }, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function fetchDataForChart(chart, examCode, resultXLS, numberQuestionFirstLine) {
        var deferred = $q.defer();
        var data = {
            chart: chart,
            resultXLS: resultXLS,
            examCode: examCode,
            numberQuestion: numberQuestionFirstLine,
        };
        $http({
            method: 'POST',
            url: REST_SERVICE_URI + 'fetchDataForChart',
            data: data
        }).then(function (response) {
                deferred.resolve(response.data);
            }, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function fetchAllExamCodeOfResultFile(resultXLS) {
        var deferred = $q.defer();
        $http({
            method: 'POST',
            url: REST_SERVICE_URI + 'fetchAllExamCodeOfResultFile',
            data: resultXLS
        }).then(function (response) {
                deferred.resolve(response.data);
            }, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function fetchChartList(subjectId) {
        var deferred = $q.defer();
        $http({
            method: 'GET',
            url: REST_SERVICE_URI + 'fetchChartList',
        }).then(function (response) {
                deferred.resolve(response.data);
            }, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    function sendCreateChart(chartName, examCode, selectedChart, startQuestion,
                             finishQuestion, columnList, typeStatistic, resultXLS, resultArray) {
        var deferred = $q.defer();
        var data = {
            chartName: chartName,
            examCode: examCode,
            selectedChart: selectedChart,
            startQuestion: startQuestion,
            finishQuestion: finishQuestion,
            columnList: columnList,
            resultXLS: resultXLS,
            typeStatistic: typeStatistic,
            resultArray: resultArray,
        };
        console.log(data);
        $http({
            method: 'POST',
            url: REST_SERVICE_URI + 'sendCreateChart',
            data: data
        }).then(function (response) {
                deferred.resolve(response.data);
            }, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
}]);