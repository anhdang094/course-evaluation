App.factory('ViewRootExamsService', ['$http', '$q', function($http, $q){
	 
	var REST_SERVICE_URI = 'http://localhost:8080/EvaluationSource/api/';
     
	var factory ={
			fetchRootExamByExamId : getRootExamByExamId,
			//fetchQuestionBlockOfRootExamId : getQuestionBlockOfRootExamId,
			//fetchListQuestionOfBlockByRootExamId : getQuestionOfRootExam,
			fetchRootExamByExam : getRootExamByExam,
			fetchBlockQuestionByKeyWord : getBlockQuestionByKeyWord,
			updateRootExam : updateRootExam,
			//updateExamCount : updateExamCount,
			//updateAnswerByQuestion : updateAnswerByQuestion,
	};
	 return factory;
     
	// function updateAnswerByQuestion(QuestionId, QuestionBlockId, )
	 
/*	 function updateExamCount(idRootExam, examCount){
		 var deferred = $q.defer();
		 const data = {
				 id : idRootExam,
				 examCount : examCount,
		 }
		 $http.post(
		REST_SERVICE_URI + 'updateRootExamCount',data).then(function(response){
			deferred.resolve(response.data);
			
		},
		function(errResponse){
			console.error(errResponse);
			deferred.reject(errResponse);
		});	 
		 return deferred.promise;
		 
	 }*/
	 
	 function getRootExamByExam(rootExamId){
		 var deferred = $q.defer();
		 $http.get(
		  REST_SERVICE_URI + 'getRootExamByExam/' + rootExamId		 
		 ).then(function(response){
			deferred.resolve(response.data); 
		 },
             function(errResponse){
                 console.error(errResponse);
                 deferred.reject(errResponse);
             }
		 );
		 return deferred.promise;
	 }
	 
	 
	 function getRootExamByExamId(rootExamId){
		 var deferred = $q.defer();
		 $http.get(
		   REST_SERVICE_URI + 'getRootExamByExamId/' + rootExamId		 
		 ).then(function(response){
			 deferred.resolve(response.data);
		 },
		 function(errResponse){
			 console.error(errResponse);
			 deferred.reject(errResponse);
		 }
		 );
		 return deferred.promise;
	 }
	/*
	 * Get List Block Question of RootExam
	 */
	/* function getQuestionBlockOfRootExamId(ExamId){
		 var deferred = $q.defer();
		 $http.get(
				 REST_SERVICE_URI + 'getListBockRootExamId/' + ExamId
				 ).then(function(response){
					deferred.resolve(response.data);
				 },function(errResponse){
					 console.error(errResponse);
					 deferred.reject(errResponse);
				 }
				 );
		 return deferred.promise;
	 }*/
	 
	 /*
	  * Get All Question into Exam
	  */
	 
	/* function getQuestionOfRootExam(QuestionBlockId){
		 var deferred = $q.defer();
		 $http.get(
		       REST_SERVICE_URI + 'getListQuestionRootExamId' , QuestionBlockId		 
		 ).then(function(respone){
			 deferred.resolve(respone.data);
		 },
		    function(errResponse){
			 console.error(errResponse);
			 deferred.reject(errResponse);
		 }
		 );
		 return deferred.promise;
	 }*/
	 
	 function getBlockQuestionByKeyWord(subjectid, Chapterid){
		 var deferred = $q.defer();
		 $http.get(
		      REST_SERVICE_URI + 'getRootBlockQuestionByKeyWord/'+subjectid+'/'+Chapterid
		      
		 ).then(function(response){
			 deferred.resolve(response.data);
		 },
		   function(errResponse){
			 console.error(errResponse);
			 deferred.reject(errResponse);
		 }
		 );
		 return deferred.promise;
	 }


    function updateRootExam(
    		blockQuestionAddList,
    		blockQuestionRemoveList,
    		examQuestionRemoveList,
    		examQuestionAddList,
    		examAnswerRemoveList,
    		examAnswerAddList,
    		countExam,
    		examsId,
    		codeExam){
        var deferred = $q.defer();
        
        const data = {
            blockQuestionAddList: blockQuestionAddList,
            blockQuestionRemoveList: blockQuestionRemoveList,
            examQuestionRemoveList : examQuestionRemoveList,
            examQuestionAddList    : examQuestionAddList,
            examAnswerRemoveList : examAnswerRemoveList,
            examAnswerAddList : examAnswerAddList,
            countExam : countExam,
            IdExams : examsId,
            codeExam: codeExam,
            
        };
        console.log("============================================");
        console.log(data);
        $http.post(
            REST_SERVICE_URI + "updateRootExam",data

        ).then(function(response){
                deferred.resolve(response.data);
                console.log(data);
            },
            function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

}]);