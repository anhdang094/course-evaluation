App.factory('AdminService', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI = 'http://localhost:8080/EvalutionSource/api/';
 
    var factory = {
        fetchAllTeachers: fetchAllTeachers,
        fetchAllSubject: fetchAllSubject,
        fetchAllStudents: fetchAllStudents,
        createSubject: createSubject,
        assignTeacherToSubject: assignTeacherToSubject,
        teacherToSubject: teacherToSubject,
    };
 
    return factory;
    
    function fetchAllTeachers() {
        var deferred = $q.defer();
        $http({
        	method: 'GET',
        	url: REST_SERVICE_URI + 'getAllTeacher',
        }).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
    
    function fetchAllSubject() {
        var deferred = $q.defer();
        $http({
        	method: 'GET',
        	url: REST_SERVICE_URI + 'getAllSubject',
        }).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
    
    function fetchAllStudents() {
    	var deferred = $q.defer();
        $http({
        	method: 'GET',
        	url: REST_SERVICE_URI + 'getAllStudent',
        }).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
    
    function createSubject(subjectName) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI + 'createSubject', {name: subjectName}).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
    
    function assignTeacherToSubject(subjectId, teacherId) {
        var deferred = $q.defer();
        var subject = {
        	id: subjectId,
        	teacherid: teacherId,
        };
        console.log(subject);
        $http.post(REST_SERVICE_URI + 'assignTeacherToSubject', subject).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
    
    function teacherToSubject(subjectId, teacherId) {
        var deferred = $q.defer();
        var teacher_subject = {
        	id: subjectId,
        	teacherId: teacherId,
        };
        $http.post(REST_SERVICE_URI + 'teacherToSubject', teacher_subject).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
}]);