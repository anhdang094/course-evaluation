App.factory('NotificationService', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI = 'http://localhost:8080/EvaluationSource/api/';
 
    var factory = {
    	fetchAllNotificationsToHomePage : fetchAllNotificationsToHomePage,
    };
 
    return factory;
    
    function fetchAllNotificationsToHomePage(){
    	  var deferred = $q.defer();
          $http({
          	method: 'GET',
          	url: REST_SERVICE_URI + 'getAllNotificationToHomePage',
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
    








