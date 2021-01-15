App.factory('ExamsService', ['$http', '$q', function($http, $q){
	 
	var REST_SERVICE_URI = 'http://localhost:8080/EvaluationSource/api/';
 
    var factory = {
    	createExams: createExams,
    	fetchExamsByExamsId: fetchExamsByExamsId,
    };
 
    return factory;
    
    function fetchExamsByExamsId(examsId) {
    	var deferred = $q.defer();
        
        $http.get(REST_SERVICE_URI + 'fetchExamsByExamsId/' + examsId).then(function (response) {
                deferred.resolve(response.data);
                console.log(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
    
    function createExams(subjectId, examName, examCount, questionCount, answerCount, timeType, startTime, endTime, time, times, targetList) {
        var deferred = $q.defer();
        
        var exams = {
        	subjectid: subjectId,
            examcount: examCount,
            questioncount: questionCount,
            answercount: answerCount,
            timetype: timeType,
            name: examName,
            starttime: startTime,
            endtime: endTime,
            time: time,
            times: times,
        };
        
        var data = {
        	exams: exams,
        	targetList: targetList,
        };
        
        console.log(data);
        
        $http.post(REST_SERVICE_URI + 'createExams', data).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
}]);