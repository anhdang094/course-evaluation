App.factory('ClassListService', ['$http', '$q', function($http, $q){
 
	var REST_SERVICE_URI = 'http://localhost:8080/EvaluationSource/api/';
 
    var factory = {
    	fetchClassOfUser: getClassOfUser,
    	createClass: createClass,
    	assignStudentToClass: assignStudentToClass
    };
 
    return factory;
    
    function getClassOfUser() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + 'getClassOfUser').then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
    
    function createClass(subjectId, name) {
        var deferred = $q.defer();
        var data = {
        	name: name,
        	subjectid: subjectId,
        };
        $http.post(REST_SERVICE_URI + 'createClass', data).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
    
    function assignStudentToClass(studentId, classId) {
        var deferred = $q.defer();
        var data = {
        	studentid: studentId,
        	classid: classId,
        };
        $http.post(REST_SERVICE_URI + 'assignStudentToClass', data).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
}]);