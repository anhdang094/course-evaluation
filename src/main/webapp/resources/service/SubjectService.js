App.factory('SubjectService', ['$http', '$q', function($http, $q){
	 
    var REST_SERVICE_URI = 'http://localhost:8080/EvalutionSource/api/';
 
    var factory = {
    	fetchAllChapterBySubjectId: fetchAllChapterBySubjectId,
    	createChapter: createChapter,
    	isSuperTeacherOfSubject: isSuperTeacherOfSubject,
    };
 
    return factory;
    
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
}]);