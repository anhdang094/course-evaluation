App.factory('SubjectsService', ['$http', '$q', function($http, $q){
	 
	var REST_SERVICE_URI = 'http://localhost:8080/EvaluationSource/api/';
 
    var factory = {
    	fetchAllSubjectTeachOfUser: fetchAllSubjectTeachOfUser,
    	fetchAllSubjectManageOfUser: fetchAllSubjectManageOfUser,
    };
 
    return factory;
    
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
    
    function fetchAllSubjectManageOfUser() {
    	var deferred = $q.defer();
        $http({
        	method: 'GET',
        	url: REST_SERVICE_URI + 'fetchAllSubjectManageOfUser',
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