App.controller('SubjectsController', ['$scope', 'SubjectsService', function($scope, SubjectsService) {
    var self = $scope;
    self.subjectList = [];
    self.subjectManageList = [];
    
    function fetchAllSubjectManageOfUser(){
    	SubjectsService.fetchAllSubjectManageOfUser()
            .then(
            function(result) {
            	self.subjectManageList = result;
            	_.each(self.subjectManageList, function(subject) {
            		subject.isManage = false;
            		var sub = _.findWhere(self.subjectList, {id: subject.id});
            		if(sub) {
            			sub.isManage = true;
            		}
            		else {
            			self.subjectList.push(subject);
            		}
            	});
            },
            function(errResponse){
                console.error('Error while fetching Subjects');
            }
        );
    }
    
    function fetchAllSubjectTeachOfUser(){
    	SubjectsService.fetchAllSubjectTeachOfUser()
            .then(
            function(result) {
            	self.subjectList = _.map(result, function(subject) {
            		subject.isTeach = true;
            		return subject;
            	});
            	fetchAllSubjectManageOfUser();
                
            },
            function(errResponse){
                console.error('Error while fetching Subjects');
            }
        );
    }
    
    fetchAllSubjectTeachOfUser();
}]);