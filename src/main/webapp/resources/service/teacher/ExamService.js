App.factory('ExamService', ['$http', '$q', function($http, $q){
	 
	var REST_SERVICE_URI = 'http://localhost:8080/EvaluationSource/api/';
 
    var factory = {
    	fetchBlockQuestionByExamId1: getBlockQuestionByExamId1,
    	fetchBlockQuestionByExamId: getBlockQuestionByExamId,
    	fetchQuestionByBlockQuestionIdList: getQuestionByBlockQuestionIdList,
    	fetchAnswerByQuestionIdList: getAnswerByQuestionIdList,
    	fetchBlockQuestionByKeyWord: getBlockQuestionByKeyWord,
    	updateExam: updateExam
    };
 
    return factory;
    
    function updateExam(blockQuestionAddList, blockQuestionRemoveList) {
    	var deferred = $q.defer();
        const data = {
        	blockQuestionAddList: blockQuestionAddList,
        	blockQuestionRemoveList: blockQuestionRemoveList
        };

        $http.post(REST_SERVICE_URI + 'updateExam', data).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
    
    function getBlockQuestionByKeyWord(subjectId, chapterId, keyWord) {
    	var deferred = $q.defer();
        
        $http.get(REST_SERVICE_URI + 'getBlockQuestionByKeyWord/' + subjectId + '/' +  chapterId + '/' + keyWord).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
    
    function getBlockQuestionByExamId(examId) {
        var deferred = $q.defer();
        
        $http.get(REST_SERVICE_URI + 'getBlockQuestionByExamId/' + examId).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
    
    function getBlockQuestionByExamId1(examId) {
        var deferred = $q.defer();
        
        $http.get(REST_SERVICE_URI + 'getBlockQuestionByExamId1/' + examId).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
    
   function getQuestionByBlockQuestionIdList(blockQuestionIdList) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI + 'getQuestionByBlockQuestionIdList', blockQuestionIdList).then(function (response) {
                deferred.resolve(response.data);
                console.log("getQuestionByBlockQuestionIdList" + blockQuestionInList );
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
       return deferred.promise;
    }
    
    function getAnswerByQuestionIdList(questionIdList) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI + 'getAnswerByQuestionIdList', questionIdList).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
}]);