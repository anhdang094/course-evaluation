App.factory('ExamViewService', ['$http', '$q', function($http, $q){
	 
	var REST_SERVICE_URI = 'http://localhost:8080/EvaluationSource/api/';
 
    var factory = {
    		fetchExamByExamsId: getExamByExamsId,
    };
 
    return factory;
    
    function getExamByExamsId(examsId) {
        var deferred = $q.defer();
        
        $http.get(REST_SERVICE_URI + 'getExamByExamsId/' + examsId).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
}]);