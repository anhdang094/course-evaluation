App.factory('UserService', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI = 'http://localhost:8080/EvaluationSource/api/';
 
    var factory = {
    	createAccount: createAccount
    };
 
    return factory;
    
    function createAccount(account) {
    	var deferred = $q.defer();
    	
    	if (account.user.role === "teacher") {
    		account = _.omit(account, 'student');
    	}
    	
    	if (account.user.role === "student") {
    		account = _.omit(account, 'teacher');
    	}
    	
        $http({
        	method: 'POST',
        	url: REST_SERVICE_URI + 'createAccount',
        	data: account
        }).then(function (response) {
                deferred.resolve(response.data);
        	}, function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
        	}
        );
        return deferred.promise;
    }
    
}]);