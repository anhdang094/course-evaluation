App.factory('ExportService', ['$http', '$q', function($http, $q){
	 
	var REST_SERVICE_URI = 'http://localhost:8080/EvaluationSource/api/';
     
	var factory ={
			fetchAllExam : getAllExam,
			fetchSubjectOfExam : getSubjectOfExam,
	};
	 return factory;
	 
	 function getAllExam(examsid){
		 var deferred = $q.defer();
		 $http.get(REST_SERVICE_URI + 'getAllExam123/' + examsid).then(function(response){
					deferred.resolve(response.data); 
				 },
		             function(errResponse){
		                 console.error(errResponse);
		                 deferred.reject(errResponse);
		             }
				 );
				 return deferred.promise;
	 };
	 
	 function getSubjectOfExam(examsId){
		 var deferred = $q.defer();
		 $http.get(REST_SERVICE_URI + 'getSubjectOfExam123/' + examsId).then(function(response){
					deferred.resolve(response.data); 
				 },
		             function(errResponse){
		                 console.error(errResponse);
		                 deferred.reject(errResponse);
		             }
				 );
				 return deferred.promise;
	 };
	 
}]);