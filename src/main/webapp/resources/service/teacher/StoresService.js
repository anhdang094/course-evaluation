App.factory('StoresService', ['$http', '$q', function($http, $q){
	 
var REST_SERVICE_URI = 'http://localhost:8080/EvaluationSource/api/';
 
    var factory = {
    		fetchAllExamOfSubject: getAllExamOfSubject,
    
    };
 
    return factory;
    /*
     * getAllExamOfSubjectOfTeacher
     */
    function getAllExamOfSubject(){
    	var deferred = $q.defer();
    	
      	//console.log(data);
    	$http.get(REST_SERVICE_URI + 'getAllExamOfSubjectOfTeacher').then(function (response){
    		deferred.resolve(response.data);
    		console.log(response.data);
    	},
    	function(errResponse){
    		console.error(errResponse);
    		deferred.reject(errResponse);
    	}
    	);
    	return deferred.promise;
    }
    
    
}]);