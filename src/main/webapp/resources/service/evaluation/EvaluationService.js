/**
 * Created by anh.dang on 1/18/2017.
 */
App.factory('EvaluationService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/EvaluationSource/api/';

    var factory = {
        sendResult: sendResult,
        fetchAllSubjectTeachOfUser: fetchAllSubjectTeachOfUser,
        fetchAllChapterBySubjectId: fetchAllChapterBySubjectId,
        fetchAllTargetBySubjectIdChapterId1: fetchAllTargetBySubjectIdChapterId1,
        fetchAllForEvaluation: fetchAllForEvaluation,
        fetchEvaluationByChapterSelect: fetchEvaluationByChapterSelect,
        fetchEvaluationByOutcomeSelect: fetchEvaluationByOutcomeSelect,
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

    function fetchAllSubjectTeachOfUser() {
        var deferred = $q.defer();
        $http({
            method: 'GET',
            url: REST_SERVICE_URI + 'fetchAllSubjectTeachOfUser',
        }).then(function (response) {
                deferred.resolve(response.data);
            }, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function fetchAllChapterBySubjectId(subjectId) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + 'getAllChapterBySubjectId/' + subjectId).then(function (response) {
                deferred.resolve(response.data);
            }, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function fetchAllTargetBySubjectIdChapterId1(subjectId, chapterId) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + 'getAllTargetBySubjectIdChapterId1/' + subjectId + '/' + chapterId).then(function (response) {
                deferred.resolve(response.data);
            }, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function fetchAllForEvaluation(targetList, numberQuestion, resultArray, chapterList) {
        var deferred = $q.defer();
        var data = {
                resultArray: resultArray,
                numberQuestion: numberQuestion,
                targetList: targetList,
                chapterList: chapterList,
        };
        $http({
            method: 'POST',
            url: REST_SERVICE_URI + 'fetchAllForEvaluation',
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

    function fetchAllForEvaluation(targetList, numberQuestion, resultArray, chapterList, examCode) {
        var deferred = $q.defer();
        var data = {
            resultArray: resultArray,
            numberQuestion: numberQuestion,
            targetList: targetList,
            chapterList: chapterList,
            examCode: examCode,
        };
        $http({
            method: 'POST',
            url: REST_SERVICE_URI + 'fetchAllForEvaluation',
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

    function fetchEvaluationByChapterSelect(chapterId) {
        var deferred = $q.defer();
        var data = {
            chapterId: chapterId
        };
        $http({
            method: 'POST',
            url: REST_SERVICE_URI + 'fetchEvaluationByChapterSelect',
            params: data
        }).then(function (response) {
                deferred.resolve(response.data);
            }, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function fetchEvaluationByOutcomeSelect(outcomeId) {
        var deferred = $q.defer();
        var data = {
            outcomeId: outcomeId
        };
        $http({
            method: 'POST',
            url: REST_SERVICE_URI + 'fetchEvaluationByOutcomeSelect',
            params: data
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