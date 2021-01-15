App.controller('QuestionController', ['$scope', '$location', 'QuestionService', 'SubjectsService', 'SubjectService', function($scope, $location, QuestionService, SubjectsService, SubjectService) {
    var self = $scope;
   
    
    self.subjectList = [];
    self.chapterList = [];
    self.targetList = [];
    self.subjectId;
    self.selectedTarget = {};
    self.latexContent = "";
    self.blockQuestion = {
    	content: null,
    	questionList: [],
    };
    
    self.showLatex = false;
    self.showManual = true;
    
    self.clickShowLatex = function() {
    	self.showLatex = true;
        self.showManual = false;
    }
    
    self.clickShowManual = function() {
    	self.showLatex = false;
        self.showManual = true;
    }
    
    self.getQuestionFromLatex = function() {
    	if(self.latexContent && self.subjectId) {
    		QuestionService.getQuestionFromLatex(self.subjectId, self.latexContent)
            .then(
            function(result) {
            	console.log(result);
            	toastr.success('Lưu câu hỏi thành công', 'Thành công', {timeOut: 1000});
            },
            function(errResponse){
                console.error('Error while create Subjects');
                toastr.error('', 'Thất bại', {timeOut: 1000});
            });
    	}
    };
    
    self.removeTarget = function(targetId, selectedTargetList) {
    	questionTargetList = _.filter(selectedTargetList, function(target) {
    		return target.id !== targetId;
    	});
    };
    
    self.saveBlockQuestion = function() {
    	self.blockQuestion.questionList = _.filter(self.blockQuestion.questionList, function(question){
    		return !!question.content;
    	});
    	console.log("==================Question Content========================");
    	console.log(self.blockQuestion.questionList);
    	
    	if (self.blockQuestion.questionList.length === 0 ) {
    		toastr.warning('Không tồn tại câu hỏi', 'Chú ý', {timeOut: 1000});
    		return;
    	}

    	self.blockQuestion.questionList = self.blockQuestion.questionList.map(function(question) {
    		question.answerList = _.filter(question.answerList, function(answer) {
    			return !!answer.content;
    		})
    		return question;
    	});
    	
    	_.each(self.blockQuestion.questionList, function(question) {
    		if (question.answerList.length === 0) {
    			toastr.warning('Câu hỏi phải có đáp án', 'Chú ý', {timeOut: 1000});
    			return;
    		}
    		const haveSolution = _.some(question.answerList , function(a) {
    			return !!a.issolution; 
    		})
    		
    		if (!haveSolution) {
    			toastr.warning('Phải có ít nhất một đáp án đúng cho câu hỏi', 'Chú ý', {timeOut: 1000});
    			return;
    		}
    	});
    	
    	self.blockQuestion.subjectId = self.subjectId;
    	
    	console.log(self.blockQuestion);
    	
    	QuestionService.saveBlockQuestionList([self.blockQuestion])
        .then(
        function(result) {
        	console.log(result);
        	toastr.success('Lưu câu hỏi thành công', 'Thành công', {timeOut: 1000});
        },
        function(errResponse){
            console.error('Error while create Subjects');
            toastr.error('', 'Thất bại', {timeOut: 1000});
        });
    };
    
    self.addTarget = function(selectedTarget, selectedTargetList) {
    	if(!_.isEmpty(selectedTarget) && _.has(selectedTarget, "content") && !_.find(selectedTargetList, function(target){return target.id == selectedTarget.id;})) {
    		selectedTargetList.push(selectedTarget);
    	}
    };
    
    self.addAnswer = function(answerList){
    	answerList.push({
    		content: null,
    		issolution: false,
    	});
    	
    	answerList.push({
    		content: null,
    		issolution: false,
    	});
    };
    
    self.addQuestion = function(){
    	self.blockQuestion.questionList.push({
    		level: 0,
    		chapterid: 0,
    		content: null,
    		targetList: [],
    		answerList: [],
    		selectedTarget: {},
    		selectedTargetList:[],
    	});
    	self.addAnswer(_.last(self.blockQuestion.questionList).answerList);
    };;
    
    self.onChangeSelectedSubject = function() {
    	SubjectService.fetchAllChapterBySubjectId(self.subjectId)
	        .then(
	        function(result) {
	        	self.chapterList = result;
	        },
	        function(errResponse){
	            console.error('Error while fetching Subjects');
	        }
        );
    };
    
    self.onChangeSelectedChapter = function(question) {
    	SubjectService.fetchAllTargetBySubjectIdChapterId(self.subjectId, question.chapterid)
	    	.then(
	        function(result) {
	        	question.targetList = result;
	        },
	        function(errResponse){
	            console.error('Error while fetching Subjects');
	        }
    	);
    }
    
    function fetchAllSubjectOfTeacher(){
    	SubjectsService.fetchAllSubjectTeachOfUser()
            .then(
            function(result) {
            	self.subjectList = result;
            },
            function(errResponse){
                console.error('Error while fetching Subjects');
            }
        );
    };
    
    fetchAllSubjectOfTeacher(); 
    self.addQuestion();
}]);