App.factory('QuestionService', ['$http', '$q', function($http, $q){
 
	var REST_SERVICE_URI = 'http://localhost:8080/EvaluationSource/api/';
 
    var factory = {
    	saveBlockQuestionList: saveBlockQuestionList,
    	getQuestionFromLatex: getQuestionFromLatex,
    };
 
    return factory;
    
    function getQuestionFromLatex(subjectId, latextContent) {
    	var deferred = $q.defer();
    	const data = {
    		subjectId: subjectId,
    		latextContent: latextContent,
    	};
    	$http.post(REST_SERVICE_URI + 'getQuestionFromLatex', data).then(function (response) {
	            deferred.resolve(response.data);
	    	}, function(errResponse){
	            console.error(errResponse);
	            deferred.reject(errResponse);
	    	}
	    );
	    return deferred.promise;
    }
    
    function saveBlockQuestionList(blockQuestionList) {
    	var deferred = $q.defer();
    	var data = blockQuestionList.map(function(blockQuestion) {
    		var questionList = blockQuestion.questionList.map(function(question) {
    			var targetList = question.targetList.map(function(target) {
    				return {
    					targetid: target.id,
    					subjectid: target.subjectid,
    					chapterid: target.chapterid,
    				};
    			});
    			var answerList = question.answerList.map(function(answer) {
    				if(answer.content) {
    					return answer;
    				}
        		});
    			if (question.content) {
    				const q = _.pick(question, 'chapterid', 'content', 'level');
    				q.subjectid = blockQuestion.subjectId;
    				return {
        				question: q,
        				answerList: answerList,
        				questionTargetList: targetList,
        			};
    			}
    		});
    		return {
    			blockQuestion: {
    				content: blockQuestion.content,
    				subjectid: blockQuestion.subjectId
    			},
    			questionList: questionList,
    		};
    	});
    	
    	console.log("=====================DATA==================");
    	console.log(data);
        $http.post(REST_SERVICE_URI + 'saveBlockQuestionList', data).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
}]);