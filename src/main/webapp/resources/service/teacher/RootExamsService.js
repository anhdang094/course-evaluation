App.factory('RootExamsService', ['$http', '$q', function($http, $q){
	 
var REST_SERVICE_URI = 'http://localhost:8080/EvaluationSource/api/';
 
    var factory = {
    	createRootExams: createRootExams,
    	fetchExamsByExamsId: fetchExamsByExamsId,
    };
 
    return factory;
    
  /*  function pushExamInforByExam(noteExam,times){
    	var deferred = $q.defer();
    	var data = {
    			noteExam:noteExam,
    			times:times,
    	}
    	console.log("---------------------PUSHEX------------------------");
    	console.log(data);
    	$http.post(REST_SERVICE_URI + 'pushExamInfoByExam', data).then(function (response){
    		deferred.resolve(response.data);
    		console.log(response.data);
    	},
    	function(errResponse){
    		console.error(errResponse);
    		deferred.reject(errResponse);
    	}
    	);
    	return deferred.promise;
    }*/
    
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
    
    function createRootExams(subjectId, examRootName, questionCount,noteExams, times, targetList) {
        var deferred = $q.defer();
        
        var Rootexams = {
        	subjectid: subjectId,
            questioncount: questionCount,
            name: examRootName,
        };
        
        var data = {
        		exams: Rootexams,
        		noteExams : noteExams,
        		times : times,
        	    targetList: targetList,
        };
        console.log(data);
        $http.post(REST_SERVICE_URI + 'createRootExams', data).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
}]);