App.controller('NotificationController', ['$scope', 'NotificationService', function($scope, NotificationService) {
    var self = $scope;
    self.ListNotification = [];
    self.title = "";
    self.usercreate = "";
    self.body = "";
    self.link = "";
    self.createat= "";
    self.modifiedat = "";
    self.Id = "";
    var date = new Date();
    
    self.isShow = false;
    
    function fetchAllNotification(){
    	
    	NotificationService.fetchAllNotificationsToHomePage().then(
    	 function (result){
    		 console.log("================fetch All NOTIFICATIONS===================");
    		 console.log(result);
    		 const listNotification = _.map(result,function(custom){
    			 const list = custom;
    			 list.editCreatedat = formatDay(custom.createdat);
    			 list.modifiedat = formatDay(custom.modifiedat);
    			 const strBody = custom.body;
    			 list.body = strBody;
    			 list.usercreat = custom.usercreate;
    			 const strLink = custom.link;
    			 list.link = strLink;
    			 list.title = custom.title;
    			 console.log("----------------------------");
    			 console.log(date);
    			 console.log(formatDay(new Date(date).getTime()));
    			 console.log(list.modifiedat);
    			 
    			 list.checkDate = angular.equals(list.modifiedat,formatDay(new Date(date).getTime()));
    			// console.log();
    			 return list;
    		 });
    	 self.ListNotification = listNotification;
    		// console.log(self.ListNotification);
    		 
    	 },
    	 function(errResponse){
    		 console.error("Error Fetching Notification");
    	 }
    	);
    	
    }
    function formatDay(utc){
    	   var values = "/Date("+utc+")/";
    	   var dt = new Date(parseInt(values.substring(6, values.length - 2)));
    	   var Month;
    	   if((dt.getMonth()+1) < 10){
    		   Month = "0"+(dt.getMonth()+1);
    	   }
    	   else{
    		   Month = (dt.getMonth()+1);
    	   }
    	   var result = (dt.getFullYear() + "/" + Month + "/" + dt.getDate()) ;
    	   return result;
    }
    
    
    
    function reset(){
   	 self.title = "";
   	 self.usercreate = "";
   	 self.body = " ";
   	 self.link= " ";
   	 self.Id ="";
    }
    fetchAllNotification();
    
}]);