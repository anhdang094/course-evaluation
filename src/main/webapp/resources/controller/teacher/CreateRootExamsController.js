App.controller('CreateRootExamsController',['$scope', 'RootExamsService', 'SubjectsService', 'SubjectService',
function ($scope, RootExamsService, SubjectsService, SubjectService) {

    var self = $scope;
    self.subjectList =[];
    self.targetList=[];
    self.chapterList =[];
    self.examRootName = "";
    self.Rnumber= "";
    self.selectedSubject;
    self.RnumberExam = 1;
    self.timeRootExam = 60;
    self.RnoteExam = "Thí sinh không được sử dụng tài liệu.";
    self.answerCount = "";
    self.questionCount = 4;
    self.times = 0;
    self.time = 0;
    self.ExamInfo = [];

    self.onChangeSelectedSubject = function(){
        fetchAllChapterBySubjectId();
    };
    self.onClickChapter = function (chapter) {
        chapter.isShow = !chapter.isShow;
        if (!chapter.isClick) {
            fetchAllTargetBySubjectIdChapterId(chapter);
            chapter.isClick = true;
        }
    };
    

    self.onSubmitCreateRootExams = function(){
     var check = true;
     console.log(self.selectedSubject);
     if(!self.selectedSubject) {
         toastr.warning('Phải chọn môn học', 'Cảnh báo', {timeOut: 1000});
         return;
     }
     
   /*  if(!self.questionCount<=0){
         toastr.warning('Câu hỏi lớn hơn 0', 'Cảnh báo', {timeOut: 1000});
         return;
     }*/
        /*
         * _flatten(array a) convert array 
         * 
         * Select Pluck A into array B
         * _pluck (B, A)
         */
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
			return data;
		});
			
		console.log("TIEU CHI DANH GIA " + targetList);
		
        if (check) {
        
           RootExamsService.createRootExams(
            	self.selectedSubject.id,
                self.examRootName,
                self.questionCount,
                self.RnoteExam,
                self.timeRootExam,
                targetList)
                .then(
                    function(result) {
                    	console.log("=== TEST ROOT EXAM ===");
                    	window.location.href = './Rootexams/' + result+ '/edit';
                    	toastr.success('', 'Đã Tạo Thông Tin Đề Mẫu', {timeOut: 1000});
                    },
                    function(errResponse){
                        console.error('Error while fetching Subjects');
                        toastr.error('', 'Error', {timeOut: 1000});
                    }
                );   
           
       	/*
            * Insert ExamInfo
            */
               /* RootExamsService.pushExamInforByExam(self.RnoteExam, self.timeRootExam).then(
                	function(result){
                		toastr.success('', 'Đã Tạo Thông Tin Đề Mẫu', {timeOut: 1000});
                		 
                		console.log("=== TEST ROOT EXAM ===");
                	},
                	function(errResponse){
                		 console.error('Error Push ExamInfo');
                         toastr.error('', 'Error', {timeOut: 1000});
                	}
                );*/
                
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
    function fetchAllSubjectTeachOfUser(){
        SubjectsService.fetchAllSubjectTeachOfUser()
            .then(
                function(result) {
                    self.subjectList = result;
                    //reset();
                },
                function(errResponse){
                    console.error('Error while fetching Subjects');
                }
            );
    };

    function fetchAllChapterBySubjectId(){
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
    };


        function fetchAllTargetBySubjectIdChapterId(chapter) {
            SubjectService.fetchAllTargetBySubjectIdChapterId1(self.selectedSubject.id, chapter.id)
                .then(
                    function(result) {
                        console.log(result);
                        chapter.targetList = result.map(function (t, index) {
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
                    function (errResponse) {
                        console.error('Error while fetching Subjects');
                    }
                );
        };

    fetchAllSubjectTeachOfUser();

}]);