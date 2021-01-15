App.factory('AdminService', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI = 'http://localhost:8080/EvaluationSource/api/';
 
    var factory = {
    	deleteSubject: deleteSubject,
    	fetchTeacherManageOfSubject: fetchTeacherManageOfSubject,
    	fetchTeacherTeachOfSubject: fetchTeacherTeachOfSubject,
    	unassignTeacher: unassignTeacher,
    	unassignSuperTeacher: unassignSuperTeacher,
    	fetchSubjectManageOfTeacher: fetchSubjectManageOfTeacher,
    	fetchSubjectTeachOfTeacher: fetchSubjectTeachOfTeacher,
        fetchAllTeachers: fetchAllTeachers,
        fetchAllSubject: fetchAllSubject,
        fetchAllStudents: fetchAllStudents,
        createSubject: createSubject,
        superTeacherToSubject: superTeacherToSubject,
        teacherToSubject: teacherToSubject,
        fetchAllNotifications :getAllNotifications,
        addNotiABC : addNotiABC,
        delNotification : delNotification,
        modifyNotification : modifyNotification,
    };
 
    return factory;
    
    function modifyNotification(Id, Title, Body, UserCreate, Link, CreateAt, ModifiedAt){
    	var deferred = $q.defer();
    	const data = {
    			id : Id,
    			title:Title,
    			body:Body,
    			usercreate:UserCreate,
    			link:Link,
    			createdat:CreateAt,
    			modifiedat:ModifiedAt,
    	};
    	$http({
        	method: 'POST',
        	url: REST_SERVICE_URI + 'modifyNotification',
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
    
    function addNotiABC(Title, Body, UserCreate, Link, CreateAt, ModifiedAt){
    	var deferred = $q.defer();
    	const data = {
    			title:Title,
    			body:Body,
    			usercreate:UserCreate,
    			link:Link,
    			createdat:CreateAt,
    			modifiedat:ModifiedAt,
    	};
    	$http({
        	method: 'POST',
        	url: REST_SERVICE_URI + 'addnotificationABC',
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
    
    function deleteSubject(subjectId) {
    	var deferred = $q.defer();
    	var data = {
    		id: subjectId,
    	};
        $http({
        	method: 'POST',
        	url: REST_SERVICE_URI + 'deleteSubject',
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
    
    function delNotification(Id){
    	console.log(Id);
    	var deferred = $q.defer();
    	var data = Id;
    	
    	console.log(Id);
        $http({
        	method: 'POST',
        	url: REST_SERVICE_URI + 'delNotification',
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
    
    function getAllNotifications(){
    	  var deferred = $q.defer();
          $http({
          	method: 'GET',
          	url: REST_SERVICE_URI + 'getAllNotification',
          }).then(function (response) {
                  deferred.resolve(response.data);
          	}, function(errResponse){
                  console.error(errResponse);
                  deferred.reject(errResponse);
          	}
          );
          return deferred.promise;
    }
    
    function unassignTeacher(teacherId, subjectId) {
    	var deferred = $q.defer();
    	var data = {
    		subjectid: subjectId,
    		teacherid: teacherId,
    	};
        $http({
        	method: 'POST',
        	url: REST_SERVICE_URI + 'unassignTeacher',
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
    
    function unassignSuperTeacher(teacherId, subjectId) {
    	var deferred = $q.defer();
    	var data = {
    		subjectid: subjectId,
    		teacherid: teacherId,
    	};
        $http({
        	method: 'POST',
        	url: REST_SERVICE_URI + 'unassignSuperTeacher',
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
    
    function fetchTeacherTeachOfSubject(subjectId) {
    	var deferred = $q.defer();
        $http({
        	method: 'GET',
        	url: REST_SERVICE_URI + 'fetchTeacherTeachOfSubject/' + subjectId,
        }).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
    
    function fetchTeacherManageOfSubject(subjectId) {
    	var deferred = $q.defer();
        $http({
        	method: 'GET',
        	url: REST_SERVICE_URI + 'fetchTeacherManageOfSubject/' + subjectId,
        }).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
    
    function fetchSubjectManageOfTeacher(teacherId) {
    	var deferred = $q.defer();
        $http({
        	method: 'GET',
        	url: REST_SERVICE_URI + 'fetchSubjectManageOfTeacher/' + teacherId,
        }).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
    
    function fetchSubjectTeachOfTeacher(teacherId) {
    	var deferred = $q.defer();
        $http({
        	method: 'GET',
        	url: REST_SERVICE_URI + 'fetchSubjectTeachOfTeacher/' + teacherId,
        }).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
    
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
        var data = {
        		name: subjectName
        };
        $http.post(REST_SERVICE_URI + 'createSubject', data).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
    
    function superTeacherToSubject(subjectId, teacherId) {
        var deferred = $q.defer();
        var data = {
        	subjectid: subjectId,
        	teacherid: teacherId,
        };
        $http.post(REST_SERVICE_URI + 'assignSuperTeacher', data).then(function (response) {
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
        var data = {
        	subjectid: subjectId,
        	teacherid: teacherId,
        };
        console.log(data);
        $http.post(REST_SERVICE_URI + 'assignTeacher', data).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
}]);