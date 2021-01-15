App.factory('SubjectService', ['$http', '$q', function($http, $q){
	 
	var REST_SERVICE_URI = 'http://localhost:8080/EvaluationSource/api/';
 
    var factory = {
    	fetchAllTargetBySubjectIdChapterId1: fetchAllTargetBySubjectIdChapterId1,
    	fetchAllTargetBySubjectIdChapterId: fetchAllTargetBySubjectIdChapterId,
    	fetchExamsResult: fetchExamsResult,
    	fetchAllChapterBySubjectId: fetchAllChapterBySubjectId,
    	fetchAllTargetBySubjectId: fetchAllTargetBySubjectId,
    	fetchAllExamsBySubjectId: fetchAllExamsBySubjectId,
    	fetchAllClassBySubjectId: fetchAllClassBySubjectId,
    	fetchAllStudentBySubjectId: fetchAllStudentBySubjectId,
    	fetchSubjectBySubjectId: fetchSubjectBySubjectId,
    	isSuperTeacherOfSubject: isSuperTeacherOfSubject,
    	assignStudentToSubject: assignStudentToSubject,
    	createChapter: createChapter,
    	createTarget: createTarget,
    	assign: assign,
    	fileStudentToSubject: fileStudentToSubject,
    	approveExams: approveExams,
    	removeStudentFromSubject: removeStudentFromSubject,
    	removeTargetFromSubject: removeTargetFromSubject,
    	removeChapterFromSubject: removeChapterFromSubject,
    	closeClass: closeClass,
    };
 
    return factory;
    
    function fetchExamsResult(subjectId, examsId) {
    	var deferred = $q.defer();
    	$http.get(REST_SERVICE_URI + 'fetchExamsResult/' + subjectId + '/' + examsId).then(function (response) {
                deferred.resolve(response.data);
                console.log(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
    
    function closeClass(subjectId, classId) {
    	var deferred = $q.defer();
    	const data = {
    			subjectid: subjectId,
    			id: classId,
    	};
    	$http.post(REST_SERVICE_URI + 'closeClass', data).then(function (response) {
                deferred.resolve(response.data);
                console.log(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
    
    function removeTargetFromSubject(subjectId, targetId) {
    	var deferred = $q.defer();
    	const data = {
    			subjectid: subjectId,
    			id: targetId,
    	};
    	console.log(" TARGET");
    	console.log(data);
    	$http.post(REST_SERVICE_URI + 'removeTargetFromSubject', data).then(function (response) {
                deferred.resolve(response.data);
                console.log(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
    
    function removeChapterFromSubject(subjectId, chapterId) {
    	var deferred = $q.defer();
    	const data = {
    			subjectid: subjectId,
    			id: chapterId,
    	};
    	$http.post(REST_SERVICE_URI + 'removeChapterFromSubject', data).then(function (response) {
                deferred.resolve(response.data);
                console.log(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
    
    function removeStudentFromSubject(studentId, subjectId) {
    	var deferred = $q.defer();
    	const data = {
    			studentid: studentId,
    			subjectid: subjectId,
    	};
    	$http.post(REST_SERVICE_URI + 'removeStudentFromSubject', data).then(function (response) {
                deferred.resolve(response.data);
                console.log(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
    
    function approveExams(examsId) {
    	var deferred = $q.defer();
    	$http.post(REST_SERVICE_URI + 'approveExams', examsId).then(function (response) {
                deferred.resolve(response.data);
                console.log(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
    
    function assign(examsId, classId) {
    	var deferred = $q.defer();
    	var data = {
    		classid: classId,
    		examsid: examsId,
    	};
    	$http.post(REST_SERVICE_URI + 'assign', data).then(function (response) {
                deferred.resolve(response.data);
                console.log(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
    
    /************ Insert Function: add student to subject **************/
    function assignStudentToSubject(subjectId, studentEmail) {
    	var deferred = $q.defer();
    	var data = {
    		email: studentEmail,
    	};
    	$http.post(REST_SERVICE_URI + 'checkStudent', data).then(function (response) {
                deferred.resolve(response.data);
                console.log(response.data);
                if (response.data.id) {
                	data = {
                		studentid: response.data.id,
                		subjectid: subjectId,
                	};
                	$http.post(REST_SERVICE_URI + 'assignStudentToSubject', data).then(function (response) {
		                    deferred.resolve(response.data);
		            	}, function(errResponse){
		                    console.error(errResponse);
		                    deferred.reject(errResponse);
		            	}
		            );
                }
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    };
    
    /************ Insert Function: create chapter of subject **************/
    function createChapter(subjectId, chapterName) {
    	var deferred = $q.defer();
    	var chapter = {
    		name: chapterName,
    		subjectid: subjectId,
    	};
    	console.log(chapter);
    	$http.post(REST_SERVICE_URI + 'createChapter', chapter).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    };
    
    /************ Insert Function: create target of subject **************/
    function createTarget(subjectId, chapterId, targetName) {
    	var deferred = $q.defer();
    	const data = {
    		content: targetName,
    		subjectid: subjectId,
    		chapterid: chapterId,
    	};
    	$http.post(REST_SERVICE_URI + 'createTarget', data).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    };
    
    /************ Check Function: current teacher is super teacher of subject **************/
    function isSuperTeacherOfSubject(subjectId) {
    	var deferred = $q.defer();
    	$http.get(REST_SERVICE_URI + 'isSuperTeacherOfSubject/' + subjectId).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
    
    
    /************ Fetch function **************/
    
    function fetchAllChapterBySubjectId(subjectId) {
    	var deferred = $q.defer();
    	$http.get(REST_SERVICE_URI + 'getAllChapterBySubjectId/' + subjectId).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
    
    function fetchAllTargetBySubjectIdChapterId(subjectId, chapterId) {
    	var deferred = $q.defer();
    	$http.get(REST_SERVICE_URI + 'getAllTargetBySubjectIdChapterId/' + subjectId + '/' + chapterId).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
    
    function fetchAllTargetBySubjectIdChapterId1(subjectId, chapterId) {
    	var deferred = $q.defer();
    	$http.get(REST_SERVICE_URI + 'getAllTargetBySubjectIdChapterId1/' + subjectId + '/' + chapterId).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
    
    function fetchAllTargetBySubjectId(subjectId) {
    	var deferred = $q.defer();
    	$http.get(REST_SERVICE_URI + 'getAllTargetBySubjectId/' + subjectId).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
    
    function fetchAllExamsBySubjectId(subjectId) {
    	var deferred = $q.defer();
    	$http.get(REST_SERVICE_URI + 'getAllExamsBySubjectId/' + subjectId).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
    
    function fetchAllStudentBySubjectId(subjectId) {
    	var deferred = $q.defer();
    	$http.get(REST_SERVICE_URI + 'getAllStudentBySubjectId/' + subjectId).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
    
    function fetchAllClassBySubjectId(subjectId) {
    	var deferred = $q.defer();
    	$http.get(REST_SERVICE_URI + 'getAllClassBySubjectId/' + subjectId).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
    
    function fetchSubjectBySubjectId(subjectId) {
    	var deferred = $q.defer();
    	$http.get(REST_SERVICE_URI + 'getSubjectBySubjectId/' + subjectId).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
    
    function fileStudentToSubject(subjectId, csvContent) {
    	var deferred = $q.defer();
    	const data = {
    		subjectId: subjectId,
    		content: csvContent,
    	}
    	$http.post(REST_SERVICE_URI + 'fileStudentToSubject', data).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
}]);