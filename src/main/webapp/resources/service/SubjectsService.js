App.factory('SubjectsService', ['$http', '$q', function($http, $q){
	 
    var REST_SERVICE_URI = 'http://localhost:8080/EvalutionSource/api/';
 
    var factory = {
        fetchAllSubjectOfTeacher: fetchAllSubjectOfTeacher,
    };
 
    return factory;
    
    function fetchAllSubjectOfTeacher() {
    	var deferred = $q.defer();
        $http({
        	method: 'GET',
        	url: REST_SERVICE_URI + 'getAllSubjectByTeacherId',
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