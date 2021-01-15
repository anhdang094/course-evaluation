App.controller('ExportController',['$scope','$location','ExportService' ,function($scope,$location, ExportService){
	var self = $scope;
	self.ExamsId = $location.absUrl().substring(
			$location.absUrl()
					.lastIndexOf('Rootexams/') + 10,
			$location.absUrl().lastIndexOf('/'));
	
	self.NameSubject = [];
	self.ListExam=[];
	self.ListBlockQuestion=[];
	self.ListQuestion = [];
	self.ListAnswer = [];
	
	function getSubjectOfExam(){
		ExportService.fetchSubjectOfExam(self.ExamsId).then(
		function(result){
			console.log(">>>>>>>>>>>>>>>>>>>SUBJECT>>>>>>>>>>>");
			console.log(result);
			self.NameSubject = result;
			
		},
		
        function(errResponse){
        	
            console.error('Error Subject not Found !');
        }
		);
		
	}
	function getAllListExam(){
		ExportService.fetchAllExam(self.ExamsId).then(
		function(result){
			//Xu ly du lieu tra ve
			console.log(self.ExamsId);
			
			console.log(result);
			
			self.ListExam = _.map(result, function(b, index) {
				
				const listExam = b;
				const listBlockQuestion = _.map(b.blockRootQuestion, function(q){
					
					const rootQuestionBlock = q.rootQuestionBlock;
					
					const questionList = _.map(q.questionList, function(r){
						const question = r.question;
						question.ListAnswer = r.answerList;
						
						  return question;
					});
					rootQuestionBlock.ListQuestion = questionList;
					
					//console.log(rootQuestionBlock);
					//ExamInfo.ListBlockQuestion = rootQuestionBlock;
					return rootQuestionBlock;
				})
				listExam.ListBlockQuestion = listBlockQuestion;
				return listExam;
			});
			
			console.log("===============ListExam=======================");
			console.log(self.ListExam);
			
		},
        function(errResponse){
        	toastr.error('Cập Nhật Thất Bại', 'Thất Bại', {timeOut: 1000});
            console.error('Error ');
        }
		)
	}
	
	
	getAllListExam();//getAllExam
	getSubjectOfExam();
   
}]);