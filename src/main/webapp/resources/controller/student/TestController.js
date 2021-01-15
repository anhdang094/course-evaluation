App.controller('TestController', ['$scope', '$interval', '$location', 'TestService', 'ExamService', function($scope, $interval, $location, TestService, ExamService) {
    var self = $scope;
    self.blockQuestionList =[];
    self.questionList = [];
    self.studentExam = {};
    self.examsInfo = {};
    self.examsId = Number($location.absUrl().substring($location.absUrl().lastIndexOf('/') + 1));
    self.classId = Number($location.absUrl().substring($location.absUrl().lastIndexOf('/class/') + 7, $location.absUrl().lastIndexOf('/exams/')));
        
    self.timer = 0;
    self.second = 59;
    var stop = null;
    
    self.titleOptions = {
    	contenteditable: false,
    	charCounterCount: false,
    	toolbarInline: true,
    	events: {
    		'froalaEditor.initialized': function() {
    			console.log('initialized');
    			$('div[contenteditable = true').attr('contenteditable',false);
    			$('a[href="https://froala.com/wysiwyg-editor"]').remove();
    		},
    		'froalaEditor.focus': function() {
    			console.log('focus');
    			$('div[contenteditable = true').attr('contenteditable',false);
    			$('a[href="https://froala.com/wysiwyg-editor"]').remove();
    		}
    	}
    };
    var time = 0;
    function start(time) {
    	if(self.examsInfo.exams.timetype === "exams") self.timer = time;
    	if(self.examsInfo.exams.timetype === "question") {
    		self.timer = 0;
    		self.second = time;
    	}
    	
    	stop = $interval(function() {
        	self.second = self.second - 1;
        	if (self.timer === 0 && self.second < 0) {
        		if(self.examsInfo.exams.timetype === "exams") self.onSubmitTest();
        		if(self.examsInfo.exams.timetype === "question") self.next();
        		stop = undefined;
        	}
        	
        	if (self.second < 0) {
        		self.second = 59;
        		self.timer  = self.timer - 1;
        	}
        }, 1000);
    }
    
    self.next = function() {
    	const index = _.findIndex(self.blockQuestionList, function(blockQuestion) {
    		return blockQuestion.isShow === true;
    	});
    	
    	if (index < self.blockQuestionList.length - 1) {
    		self.blockQuestionList[index].isShow = false;
        	self.blockQuestionList[index + 1].isShow = true;
        	
        	if(self.examsInfo.exams.timetype === "question") {
            	start(calculateBlockQuestionTime(index + 1));
        	}
    	} else {
    		if(self.examsInfo.exams.timetype === "question") self.onSubmitTest();
    		else toastr.warning('Câu cuối cùng', '', {timeOut: 1000});
    	}
    };
    
    function calculateBlockQuestionTime(index) {
    	var sum = 0;
        _.each(self.blockQuestionList[index].questionList, function(q) {
        	sum += q.time;
        });
        return sum
    }
    
    self.back = function() {
    	const index = _.findIndex(self.blockQuestionList, function(blockQuestion) {
    		return blockQuestion.isShow === true;
    	});
    	
    	if (index > 0) {
    		self.blockQuestionList[index].isShow = false;
        	self.blockQuestionList[index - 1].isShow = true;
    	} else {
    		toastr.warning('Câu đầu tiên', '', {timeOut: 1000});
    	}
    };

    self.clickQuestion = function(blockQuestionId) {
    	_.each(self.blockQuestionList,  function(blockQuestion) {
    		if(blockQuestion.id === blockQuestionId) blockQuestion.isShow = true;
    		else blockQuestion.isShow = false;
    	});
    };
    
    self.onSubmitTest = function() {
    	stop = undefined;
    	TestService.submitTest(self.studentExam, self.blockQuestionList)
	        .then(
	        function(result) {
	        	console.log(result);
	        	swal({
	        		title: result,
	        		type: "success",
	        		showCancelButton: false,
	        		confirmButtonColor: "#DD6B55",
	        		confirmButtonText: "OK",
	        		closeOnConfirm: false 
	        	}, function(isConfirm) {
	        		if (isConfirm) {
	        			window.location.href = '../../' + self.classId;
	        		}
	        	});
	        },
	        function(errResponse){
	            console.error('Error while fetching block question');
	        }
	    );
    };
    
    function fetchExams() {
    	TestService.fetchExams(self.examsId)
        .then(
        function(result) {
        	console.log(result);
        	self.examsInfo = result;
        	fetchExam();
        },
        function(errResponse){
            console.error('Error while fetching block question');
        }
    );
    }
    
    function fetchExam() {
    	TestService.fetchExam(self.classId, self.examsId)
	        .then(
	        function(result) {
	        	console.log(result);
	        	self.studentExam = result;
	        	fetchBlockQuestionByExamId1(result.examid);
	        },
	        function(errResponse){
	            console.error('Error while fetching block question');
	        }
	    );
    }
    
    function fetchBlockQuestionByExamId1(examId) {
    	ExamService.fetchBlockQuestionByExamId1(examId)
            .then(
            function(result) {
            	console.log(result);
            	self.blockQuestionList = _.map(_.shuffle(result), function(b, index) {
            		const blockQuestion = b.blockQuestion;
            		const questionList = _.map(b.questionList, function(q) {
            			const question = q.question;
            			question.answerList = _.shuffle(q.answerList);
            			
            			_.each(q.questionTargetList, function(qt){
            				const questionTarget = _.max(_.filter(self.examsInfo.examsTargetList, function(et) {
            					return et.targetid === qt.targetid && et.chapterid === qt.chapterid && et.subjectid === qt.subjectid;
            				}), function (et) {
            					if (question.level === 1 ) return et.level1time;
            					if (question.level === 2 ) return et.level2time;
            					if (question.level === 3 ) return et.level3time;
            					if (question.level === 4 ) return et.level4time;
            					if (question.level === 5 ) return et.level5time;
            				});
            				
            				console.log(questionTarget);
            				
            				if (question.level === 1 ) question.time = questionTarget.level1time;
            				if (question.level === 2 ) question.time = questionTarget.level2time;
            				if (question.level === 3 ) question.time = questionTarget.level3time;
            				if (question.level === 4 ) question.time = questionTarget.level4time;
            				if (question.level === 5 ) question.time = questionTarget.level5time;
            			});
            			
            			question.answerId = "";
            			return question
            		});
            		blockQuestion.questionList = _.shuffle(questionList);
            		blockQuestion.isShow = index === 0;
            		return blockQuestion;
            	});
            	
            	console.log(self.blockQuestionList);
            	self.questionList = _.flatten(_.map(self.blockQuestionList, function(b) {
            		return b.questionList;
            	}));
            	
            	if (self.examsInfo.exams.timetype === "exams") {
            		start(self.examsInfo.exams.time);
            	}
            	
            	if(self.examsInfo.exams.timetype === "question") {
                	start(calculateBlockQuestionTime(0));
            	}
            },
            function(errResponse){
                console.error('Error while fetching block question');
            }
        );
    };
 
    fetchExams();
}]);