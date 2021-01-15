App.factory('TestService', ['$http', '$q', function($http, $q){
 
	var REST_SERVICE_URI = 'http://localhost:8080/EvaluationSource/api/';
 
    var factory = {
    	fetchExams: getExams,
    	fetchExam: getExam,
    	submitTest: submitTest,
    };
 
    return factory;
    
    function getExams(examsId) {
    	var deferred = $q.defer();
    	$http.get(REST_SERVICE_URI + 'getExams/' +examsId).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    };
    
    function getExam(classId, examsId) {
    	var deferred = $q.defer();
    	$http.get(REST_SERVICE_URI + 'getExam/' + classId + "/" +examsId).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    };
    
    function submitTest(studentExam, questionBlockList) {
    	var deferred = $q.defer();
    	var data = _.flatten(questionBlockList.map(function(questionBlock) {
    		return questionBlock.questionList.map(function(question) {
    			return {
    				id: studentExam.id,
    				examsid: studentExam.examsid,
    				examid: studentExam.examid,
    				questionblockid: question.questionblockid,
    				questionid: question.id,
    				answerid: question.answerId
    			};
    		});
    	}));
    	
    	console.log(data);

    	$http.post(REST_SERVICE_URI + 'submitTest', data).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    };
    
}]);