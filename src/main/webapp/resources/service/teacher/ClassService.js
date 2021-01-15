App.factory('ClassService', ['$http', '$q', function($http, $q){
 
	var REST_SERVICE_URI = 'http://localhost:8080/EvaluationSource/api/';
 
    var factory = {
    	assignStudentToClass: assignStudentToClass,
    	fetchStudentOfClass: getStudentOfClass,
    	fetchClassByClassId: getClassByClassId,
    	fetchAllExamsByClassId: fetchAllExamsByClassId,
    	removeStudentFromClass: removeStudentFromClass,
    	fetchExamsResult: fetchExamsResult,
    	fetchExamsResultStudent: fetchExamsResultStudent,
    };
 
    return factory; 
    
    function removeStudentFromClass(studentId, classId) {
    	var deferred = $q.defer();
    	const data = {
    			studentid: studentId,
    			classid: classId,
    	};
    	$http.post(REST_SERVICE_URI + 'removeStudentFromClass', data).then(function (response) {
                deferred.resolve(response.data);
                console.log(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
    
    function assignStudentToClass(subjectId, classId, studentEmail) {
        var deferred = $q.defer();
        var data = {
        	email: studentEmail,
        };
        
        $http.post(REST_SERVICE_URI + 'checkStudent', data).then(function (response) {
        		if (response.data.id) {
        			data = {
        				subjectid: subjectId,
                		classid: classId,
                		studentid: response.data.id,
                	};
        			console.log(data);
                	$http.post(REST_SERVICE_URI + 'assignStudentToClass', [data]).then(function (response) {
                		console.log("asd");
                		deferred.resolve(response.data);
                		}, function(errResponse){
                			console.error(errResponse);
        		            deferred.reject(errResponse);
        		        }
        		    );
        		}
	            deferred.resolve(response.data);
	    	}, function(errResponse){
	            console.error(errResponse);
	            deferred.reject(errResponse);
	    	}
        );
        return deferred.promise;
    }  
    
    function getStudentOfClass(classId) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + 'getStudentOfClass/' + classId).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }  
    
    function fetchAllExamsByClassId(classId) {
    	var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + 'getAllExamsByClassId/' + classId).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
    
    function fetchExamsResult(classId, examsId) {
    	var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + 'fetchExamsResultClass/' + classId + '/' + examsId).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
    
    function fetchExamsResultStudent(classId, examsId) {
    	var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + 'fetchExamsResult/' + classId + '/' + examsId).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
    
    function getClassByClassId(classId) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + 'getClassByClassId/' + classId).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
}]);