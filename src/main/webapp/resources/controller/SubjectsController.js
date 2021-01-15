App.controller('SubjectsController', ['$scope', 'SubjectsService', function($scope, SubjectsService) {
    var self = $scope;
    self.subjectList = [];
    
    function fetchAllSubjectOfTeacher(){
    	SubjectsService.fetchAllSubjectOfTeacher()
            .then(
            function(result) {
            	if(result.length) self.subjectList = result
                console.log(self.subjectList);
            },
            function(errResponse){
                console.error('Error while fetching Subjects');
            }
        );
    }
    fetchAllSubjectOfTeacher();
}]);