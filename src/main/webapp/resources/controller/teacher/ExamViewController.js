App.controller('ExamViewController', ['$scope', '$location', 'ExamViewService',
                                   function($scope, $location, ExamViewService) {
    var self = $scope;
    self.examsId = Number($location.absUrl().substring($location.absUrl().lastIndexOf('/') + 1));
    self.examList = [];
    
    function fetchExamByExamsId() {
    	ExamViewService.fetchExamByExamsId(self.examsId)
        .then(
        function(result) {
        	console.log(result);
        	self.examList = result;
        },
        function(errResponse){
            console.error('Error while create Exam');
        });
    };
    
    fetchExamByExamsId();
}]);