App.controller('CreateExamsController', ['$scope', 'ExamsService', 'SubjectsService', 'SubjectService',
                                   function($scope, ExamsService, SubjectsService, SubjectService) {
    var self = $scope;
    self.subjectList = [];
    self.chapterList = [];
    self.targetList = [];

    self.selquestionCountectedSubject;
    self.questionCount = 0;
    self.examName = "";
    self.startTime = "";
    self.endTime = "";
    self.examCount = 1;
    self.answerCount = 3;
    self.time = 0;
    self.times = 1;
    self.timeType = "exams";
    self.blockQuestionCount = 0;
    self.now = new Date();
    
    self.onChangeTimeType = function() {
    	if(self.timeType === "question") {
    		resetTime();
    	}
    }
    
    self.onChangeTime = function(target) {
    	var totalTime = (target.level1Time || 0) * (target.level1Count || 0) + (target.level2Time || 0) * (target.level2Count || 0) +  
    	(target.level3Time || 0) * (target.level3Count || 0) + (target.level4Time || 0) * (target.level4Count || 0) +
    	(target.level5Time || 0) * (target.level5Count || 0);
    	target.totalTime = Math.ceil(totalTime / 60);
    	
    	resetTime();
    };
    
    function resetTime() {
    	var sum = 0;
    	var targetList = _.filter(_.flatten(_.pluck(self.chapterList, 'targetList')), function(target) {
    		var questionCount = _.isNumber(target.level1Count) ? target.level1Count : 0;
    		questionCount += _.isNumber(target.level2Count) ? target.level2Count : 0;
    		questionCount += _.isNumber(target.level3Count) ? target.level3Count : 0;
    		questionCount += _.isNumber(target.level4Count) ? target.level4Count : 0;
    		questionCount += _.isNumber(target.level5Count) ? target.level5Count : 0;
    		return questionCount > 0;
    	});
    	
    	_.each(targetList, function(t) {
			sum += (t.level1Time || 0) * (t.level1Count || 0) + (t.level2Time || 0) * (t.level2Count || 0) +  
			(t.level3Time || 0) * (t.level3Count || 0) + (t.level4Time || 0) * (t.level4Count || 0) +
			(t.level5Time || 0) * (t.level5Count || 0);
		});
    	self.time = Math.ceil(sum / 60);
    }
    
    self.onSubmitCreateExams = function() {	
    	console.log(!self.selectedSubject)
    	if (!self.selectedSubject) {
    		toastr.warning('Phải chọn môn học', 'Cảnh báo', {timeOut: 1000});
    		return;
    	}
    	
    	if (!self.examCount || self.examCount <= 0) {
    		toastr.warning('Số lượng đề phải lớn hơn 0', 'Cảnh báo', {timeOut: 1000});
    		return;
    	}
    	
    	if (!self.time || self.time <= 0) {
    		toastr.warning('Tổng thời gian phải lớn hơn 0', 'Cảnh báo', {timeOut: 1000});
    		return;
    	}
    	
    	if (!self.times || self.times <= 0) {
    		toastr.warning('Số lần làm phải lớn hơn 0', 'Cảnh báo', {timeOut: 1000});
    		return;
    	}
    	
    	if (!self.questionCount || self.questionCount <= 0) {
    		toastr.warning('Số lượng câu hỏi phải lớn hơn 0', 'Cảnh báo', {timeOut: 1000});
    		return;
    	}
    	
    	if (!self.answerCount || self.answerCount <= 0) {
    		toastr.warning('Số câu trả lời tối đa phải lớn hơn 0', 'Cảnh báo', {timeOut: 1000});
    		return;
    	}
    	
    		var check = true;
    		var targetList = _.flatten(_.pluck(self.chapterList, 'targetList'));
    		var targetList = _.filter(targetList, function(target) {
    			return target.level1Count + target.level2Count + target.level3Count + target.level4Count + target.level5Count > 0   
    		});
    		var targetList = _.map(targetList, function(target) {
    			const data = {
    				target: _.pick(target, 'chapterid', 'id', 'content', 'subjectid'),
    				questionL1Count: target.level1Count || 0,
    				questionL2Count: target.level2Count || 0,
    				questionL3Count: target.level3Count || 0,
    				questionL4Count: target.level4Count || 0,
    				questionL5Count: target.level5Count || 0,
    				
    				questionL1Time: target.level1Time || 0,
    				questionL2Time: target.level2Time || 0,
    				questionL3Time: target.level3Time || 0,
    				questionL4Time: target.level4Time || 0,
    				questionL5Time: target.level5Time || 0,
    			};
    			
    			if (self.timeType === "question" && check && (
    					(data.questionL1Count > 0 && data.questionL1Time <= 0) ||
    					(data.questionL2Count > 0 && data.questionL2Time <= 0) ||
    					(data.questionL3Count > 0 && data.questionL3Time <= 0) ||
    					(data.questionL4Count > 0 && data.questionL4Time <= 0) ||
    					(data.questionL5Count > 0 && data.questionL5Time <= 0)
    			)) {
    				toastr.warning('Thời gian cho mỗi câu phải lớn hơn 0', 'Cảnh báo', {timeOut: 1000});
    				check = false;
    			}
    			
    			return data;
    		});
    		
    		if (check) {
    			ExamsService.createExams(self.selectedSubject.id, self.examName, self.examCount, self.questionCount, self.answerCount, self.timeType, self.startTime, self.endTime, self.time, self.times, targetList)
		        .then(
		        function(result) {
		        	window.location.href = './exams/' + result;
		        	toastr.success('', 'Success', {timeOut: 1000});
		        },
		        function(errResponse){
		            console.error('Error while fetching Subjects');
		            toastr.error('', 'Error', {timeOut: 1000});
		        }
		        );
    		}
    };
    
    self.onChangeQuestionCount = function(chapter) {
    	var sum = 0;
    	var chapterSum = 0;
    	
    	_.each(chapter.targetList, function(target) {
    		chapterSum += target.level1Count + target.level2Count + target.level3Count + target.level4Count + target.level5Count; 
    	});
    	chapter.selectedQuestionCount = chapterSum;
    	
    	var targetList = _.filter(_.flatten(_.pluck(self.chapterList, 'targetList')), function(target) {
    		var questionCount = _.isNumber(target.level1Count) ? target.level1Count : 0;
    		questionCount += _.isNumber(target.level2Count) ? target.level2Count : 0;
    		questionCount += _.isNumber(target.level3Count) ? target.level3Count : 0;
    		questionCount += _.isNumber(target.level4Count) ? target.level4Count : 0;
    		questionCount += _.isNumber(target.level5Count) ? target.level5Count : 0;
    		return questionCount > 0;
    	});
    	
    	_.each(targetList, function(target) {
			sum += target.level1Count + target.level2Count + target.level3Count + target.level4Count + target.level5Count; 
		});
    	
    	self.questionCount = _.isNumber(sum) ? sum : 0;
    };
    
    self.onChangeSelectedSubject = function() {
    	fetchAllChapterBySubjectId();
    };
    
    self.onClickChapter = function(chapter) {
    	chapter.isShow = !chapter.isShow;
    	if (!chapter.isClick) {
    		fetchAllTargetBySubjectIdChapterId(chapter);
    		chapter.isClick = true;
    	}
    }
    
    function fetchAllSubjectTeachOfUser(){
    	SubjectsService.fetchAllSubjectTeachOfUser()
            .then(
            function(result) {
            	self.subjectList = result;
            	reset();
            },
            function(errResponse){
                console.error('Error while fetching Subjects');
            }
        );
    };
    
    function fetchAllChapterBySubjectId() {
    	SubjectService.fetchAllChapterBySubjectId(self.selectedSubject.id)
	        .then(
	        function(result) {
	        		console.log(result);
	        		self.chapterList = result.chapterList.map(function(chapter, index) {
	        			chapter.questionCount = result.questionL1CountList[index] + 
	        			result.questionL2CountList[index] + result.questionL3CountList[index] +
	        			result.questionL4CountList[index] + result.questionL5CountList[index];
	        			chapter.targetList = [];
	        			return chapter;
	        		});
	        },
	        function(errResponse){
	            console.error('Error while fetching Subjects');
	        }
        );
    }
    
    function fetchAllTargetBySubjectIdChapterId(chapter) {
    	SubjectService.fetchAllTargetBySubjectIdChapterId1(self.selectedSubject.id, chapter.id)
	    	.then(
	        function(result) {
	        	 console.log(result);
	        	 chapter.targetList = result.map(function(t, index) {
	        		const target = t.target;
	        		target.questionL1Count = t.questionL1Count;
	        		target.questionL2Count = t.questionL2Count;
	        		target.questionL3Count = t.questionL3Count;
	        		target.questionL4Count = t.questionL4Count;
	        		target.questionL5Count = t.questionL5Count;
            		
	        		target.level1Count = 0;
	        		target.level2Count = 0;
	        		target.level3Count = 0;
	        		target.level4Count = 0;
	        		target.level5Count = 0;
        			return target;
        		});
	        },
	        function(errResponse){
	            console.error('Error while fetching Subjects');
	        }
    	);
    }
    
    function reset() {
    	self.questionCount = 0;
        self.examName = "";
        self.examDate = "";
        self.time = 0;
        self.times = 1;
    }
    
    fetchAllSubjectTeachOfUser();
}]);