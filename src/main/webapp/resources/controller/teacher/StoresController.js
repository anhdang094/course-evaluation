App.controller('StoresController', ['$scope','StoresService', function($scope, StoresService){
	var self = $scope;
	self.ListAllSubject=[];
	
	self.ListExams = [];
	self.searchText = "";
	
	function fetchAllExamOfSubjectOfTeacher(){
		StoresService.fetchAllExamOfSubject().then(function(result){
			console.log(result);
			console.log("-----------------------");
			self.ListAllSubject = _.map(result, function(b){
				const listSubject = b.subject;
					   const listexams = b.exams;
					   listSubject.ListExams = listexams;
					   return listSubject;
				});
				console.log("===============================");
				//console.log(self.ListAllSubject);
		},
		function(errRespone) {
			console.log("Error Fetch ALL subject");
		});
	}

	fetchAllExamOfSubjectOfTeacher();//FetchAllExamOfSubjecOfTeacher
}]);