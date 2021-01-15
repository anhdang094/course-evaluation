App.controller('ExamController', ['$scope','$location', 'ExamService' , 'SubjectService', 'ExamsService', 
                                  function($scope, $location, ExamService, SubjectService, ExamsService) {
    var self = $scope;
    self.examId = Number($location.absUrl().substring($location.absUrl().lastIndexOf('exam/') + 5, $location.absUrl().lastIndexOf('/')));
    self.examsId = Number($location.absUrl().substring($location.absUrl().lastIndexOf('exams/') + 6, $location.absUrl().lastIndexOf('exam/') - 1));
    
    self.titleOptions = {
    	contenteditable: false,
    	charCounterCount: false,
    	toolbarInline: true,
    	events: {
    		'froalaEditor.initialized': function() {
    			$('div[contenteditable = true').attr('contenteditable',false);
    			$('a[href="https://froala.com/wysiwyg-editor"]').remove();
    			
    		},
    		'froalaEditor.focus': function() {
    			$('div[contenteditable = true').attr('contenteditable',false);
    			$('a[href="https://froala.com/wysiwyg-editor"]').remove();
    		}
    	}
    };
    
    self.exams = {};
    self.blockQuestionList = [];
    self.blockQuestionListSearch = [];
    
    self.searchText = "";
    self.selectedChapter = {};
    self.chapterList = [];
    
    self.blockQuestionAdd = [];
    self.blockQuestionRemove = [];
    
    self.ondropsucess = function(data, evt) { 
    	self.addQuestion(data);
    }
    
    var timeOut;
    
    self.updateExam = function() {
    	if (self.blockQuestionRemove.length <= 0 &&  self.blockQuestionAdd.length <= 0) {
    		window.location.href = './view';
    		return
    	}
    	
    	swal({
    		title: "Xác Nhận Cập Nhật Đề Thi " + self.exams.name ,
    		type: "warning",
    		showCancelButton: true,
    		confirmButtonColor: "#DD6B55",
    		confirmButtonText: "OK",
    		closeOnConfirm: true 
    	}, function(isConfirm) {
    		if (isConfirm) {
    			const examQuestionBlockRemove = self.blockQuestionRemove.map(function(blockQuestion) {
    				return {
    					examid: self.examId,
    					examsid: self.examsId,
    					questionblockid: blockQuestion.id,
    				}
    			});
    			
    			const examQuestionBlockAdd = self.blockQuestionAdd.map(function(blockQuestion) {
    				return {
    					examid: self.examId,
    					examsid: self.examsId,
    					questionblockid: blockQuestion.id,
    				}
    			});
    			
    			console.log(examQuestionBlockAdd);
    			console.log(examQuestionBlockRemove);
    			
    			ExamService.updateExam(examQuestionBlockAdd, examQuestionBlockRemove)
    	        .then(
    	        function(result) {
    	        	toastr.success('Cập Nhật Thành Công', 'Thành công', {timeOut: 1000});
    	        	console.log(result);
    	        	window.location.href = './view';
    	        },
    	        function(errResponse){
    	        	toastr.error('Cập Nhật Thất Bại', 'Thất Bại', {timeOut: 1000});
    	            console.error('Error while fetching block question');
    	        }
    	        );
    		}
    	});
    }
    
    self.removeQuestion = function(blockQuestion){
    	index = self.blockQuestionList.indexOf(blockQuestion);
        self.blockQuestionList.splice(index,index+1);
        blockQuestion.isNew = false;
    	self.blockQuestionRemove.push(blockQuestion);
    }
    
    self.onChangeSelectedChapter = function(data) {
    	seacrhQuestion();
    }
    
    self.addQuestion = function(blockQuestion) {
    	var index = self.blockQuestionList.indexOf(blockQuestion);
        if (!blockQuestion || index > -1) return;
        
        const isExist = !!_.find(self.blockQuestionList, function(question){
       	 return  question.id === blockQuestion.id;
        });
        
        if (isExist) {
     	   toastr.warning('Câu hỏi đã tồn tại trong đề thi', 'Thông Báo', {timeOut: 1000});
     	   return;
        }
        
        index = self.blockQuestionListSearch.indexOf(blockQuestion);
        self.blockQuestionListSearch.splice(index,index+1);
        
        blockQuestion.isNew = true;
        self.blockQuestionAdd.push(blockQuestion);
        self.blockQuestionList.push(blockQuestion);
    }
    
    self.onChangeSearchQuestion = function() {
    	if(!!timeOut) clearTimeout(timeOut);
    	timeOut = setTimeout(seacrhQuestion, 800);
    };
    
    function seacrhQuestion() {
    	ExamService.fetchBlockQuestionByKeyWord(self.exams.subjectid, self.selectedChapter.id || 0, self.searchText || "")
        .then(
        function(result) {
        	self.blockQuestionListSearch = result.map(function(b) {
        		const blockQuestion = b.blockQuestion;
        		blockQuestion.questionList = b.questionList.map(function(q){
        			return q.question;
        		});
        		return blockQuestion;
        	});
        	console.log(result);
        },
        function(errResponse){
            console.error('Error while fetching block question');
        }
        );
    }
    
    function fetchBlockQuestionByExamId1() {
    	ExamService.fetchBlockQuestionByExamId1(self.examId)
            .then(
            function(result) {
            	console.log(result);
            	self.blockQuestionList = _.map(result, function(b, index) {
            		const blockQuestion = b.blockQuestion;
            		//console.log("++++++++++++++++" + blockQuestion);
            		const questionList = _.map(b.questionList, function(q) {
            			//console.log("000000000000000000000000" + b.questionList);
            			const question = q.question;
            			question.answerList = q.answerList;
            			//console.log("1111111111111111" + q.answerList);
            			return question
            		});
            		blockQuestion.questionList = questionList;
            		console.log("TEST FETCH BLOCK QUESTION : "+blockQuestion);
            		return blockQuestion;

                	
            	});     	
            },
            function(errResponse){
                console.error('Error while fetching block question');
            }
        );
    };
    
    function fetchAllChapterBySubjectId(){
    	SubjectService.fetchAllChapterBySubjectId(self.exams.subjectid)
            .then(
            function(result) {
            	self.chapterList = result.chapterList;
            	self.chapterList.push({name: "Tất cả", id: 0})
            },
            function(errResponse){
                console.error('Error while fetching chapter');
            }
        );
    };
    
    function fetchExamsByExamsId(){
    	ExamsService.fetchExamsByExamsId(self.examsId)
            .then(
            function(result) {
            	self.exams = result;
            	fetchAllChapterBySubjectId();
            	seacrhQuestion();
            },
            function(errResponse){
                console.error('Error while fetching exams');
            }
        );
    };
    
    fetchBlockQuestionByExamId1();
    fetchExamsByExamsId();
}]);