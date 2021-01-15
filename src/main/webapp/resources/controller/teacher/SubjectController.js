App.controller('SubjectController', ['$scope', '$location', 'SubjectService', 'ClassListService',
                                     function($scope, $location, SubjectService, ClassListService) {
    var self = $scope;
    var resultList = [];
    /************ Info of Subject **************/
    self.subjectId = Number($location.absUrl().substring($location.absUrl().lastIndexOf('/') + 1));
    self.chapterName = "";
    self.studentEmail = "";
    self.isSuperTeacherOfSubject = false;
    self.subject = {};
    self.className = "";
    self.targetName= "";
    self.selectedExams = {};
    self.selectedClass = {};
    self.selectedStudent = {};
    self.selectedChapter = {};
    
    /************ Data List **************/
    self.chapterList = [];
    self.targetList = [];
    self.classList = [];
    self.studentList = [];
    self.examsList = [];
    self.resultExamList = [];
    
    /************ show hide tab variable **************/
    self.isShowChapter = true;
    self.isShowExams = false;
    self.isShowStudent = false;
    self.isShowClass = false;
    self.isShowTarget = false;
    
    /************ Handler show hide tab **************/
    self.showChapter = function() {
    	self.isShowChapter = true;
        self.isShowExams = false;
        self.isShowStudent = false;
        self.isShowClass = false;
        self.isShowTarget = false;
    };
    
    self.showStudent = function() {
    	self.isShowChapter = false;
        self.isShowExams = false;
        self.isShowStudent = true;
        self.isShowClass = false;
        self.isShowTarget = false;
    };
    
    self.showExams = function() {
    	self.isShowChapter = false;
        self.isShowExams = true;
        self.isShowStudent = false;
        self.isShowClass = false;
        self.isShowTarget = false;
    };
    
    self.showClass = function() {
    	self.isShowChapter = false;
        self.isShowExams = false;
        self.isShowStudent = false;
        self.isShowClass = true;
        self.isShowTarget = false;
    };
    
    self.showTarget = function() {
    	self.isShowChapter = false;
        self.isShowExams = false;
        self.isShowStudent = false;
        self.isShowClass = false;
        self.isShowTarget = true;
    };
    
    self.clickAnssign = function(exams) {
    	self.selectedExams = exams.exams;
    };
    
    self.assign = function() {
    	SubjectService.assign(self.selectedExams.id, self.selectedClass.id)
	        .then(
	        function(result) {
	            toastr.success('', 'Thành công', {timeOut: 1000});
	        },
	        function(errResponse){
	            console.error('Error while assign');
	            toastr.error('', 'Lỗi', {timeOut: 1000});
	        }
	    );
    }
    
    /************ Remove Function: remove student from subject **************/
    self.removeStudentFromSubject = function(studentId) {
    	swal({
    		title: "Xác Nhận Hủy Quyền Học",
    		type: "warning",
    		showCancelButton: true,
    		confirmButtonColor: "#DD6B55",
    		confirmButtonText: "OK",
    		closeOnConfirm: true 
    	}, function(isConfirm) {
    		if(isConfirm) {
    			SubjectService.removeStudentFromSubject(studentId, self.subjectId)
    	        .then(
    	        function(result) {
    	            fetchAllStudentBySubjectId();
    	            toastr.success('Xóa Sinh Viên Thành Công', 'Thành công', {timeOut: 1000});
    	        },
    	        function(errResponse){
    	            console.error('Error while create chapter');
    	            toastr.error('Xóa Sinh Viên Thất Bại', 'Lỗi', {timeOut: 1000});
    	        }
    	        );
    		}
    	});
    };
    
    /************ Update Function: approve exams **************/
    self.approveExams = function(examsId) {
    	swal({
    		title: "Xác Nhận Duyệt Đề Thi",
    		type: "warning",
    		showCancelButton: true,
    		confirmButtonColor: "#DD6B55",
    		confirmButtonText: "OK",
    		closeOnConfirm: true 
    	}, function(isConfirm) {
    		if(isConfirm) {
    			SubjectService.approveExams(examsId)
    	        .then(
    	        function(result) {
    	            fetchAllExamsBySubjectId();
    	            toastr.success('Duyệt Đề Thi Thành Công', 'Thành công', {timeOut: 1000});
    	        },
    	        function(errResponse){
    	            console.error('Error while create chapter');
    	            toastr.error('Duyệt đề thi Thất Bại', 'Lỗi', {timeOut: 1000});
    	        }
    	        );
    		}
    	});
    };
    
    /************ Create Function: create target **************/
    self.createTarget = function() {
    	SubjectService.createTarget(self.subjectId, self.selectedChapter.id, self.targetName)
	        .then(
	        function(result) {
	            fetchAllTargetBySubjectId();
	            toastr.success('Tạo Tiêu Chí Thành Công', 'Thành công', {timeOut: 1000});
	        },
	        function(errResponse){
	            console.error('Error while create chapter');
	            toastr.error('Tạo Tiêu Chí Thất Bại', 'Lỗi', {timeOut: 1000});
	        }
	    );
    };
    
    /************ Create Function: create class **************/
    self.createClass = function() {
    	ClassListService.createClass(self.subjectId, self.className)
	        .then(
	        function(result) {
	            fetchAllClassBySubjectId();
	            toastr.success('Tạo Lớp Thành Công', 'Thành công', {timeOut: 1000});
	        },
	        function(errResponse){
	            console.error('Error while create chapter');
	            toastr.error('Tạo Lớp Thất Bại', 'Lỗi', {timeOut: 1000});
	        }
	    );
    };
    
    /************ Insert Function: add student to subject **************/
    self.assignStudentToSubject = function() {
    	SubjectService.assignStudentToSubject(self.subjectId, self.studentEmail)
	        .then(
	        function(result) {
	            fetchAllStudentBySubjectId();
	            toastr.success('Thêm Học Viên Thành Công', 'Thành công', {timeOut: 1000});
	        },
	        function(errResponse){
	            console.error('Error while create chapter');
	            toastr.error('Thêm Học Viên Thất Bại', 'Lỗi', {timeOut: 1000});
	        }
	    );
    };
    
    self.fileStudentToSubject = function(fileUpload) {
    	const files = fileUpload.files;
        if (files.length > 0 && files[0]) {
          const reader = new FileReader();
          reader.onload = function() {
        	SubjectService.fileStudentToSubject(self.subjectId, reader.result)
	  	        .then(
	  	        function(result) {
	  	            fetchAllStudentBySubjectId();
	  	            toastr.success('Thêm Học Viên Thành Công', 'Thành công', {timeOut: 1000});
	  	        },
	  	        function(errResponse){
	  	            console.error('Error while add student');
	  	            toastr.error('Thêm Học Viên Thất Bại', 'Lỗi', {timeOut: 1000});
	  	        }
	  	    );
          };
          reader.readAsText(files[0]);
        }
    }
    
    /************ Check Function: current teacher is super teacher of subject **************/
    function isSuperTeacherOfSubject(){
    	SubjectService.isSuperTeacherOfSubject(self.subjectId)
            .then(
            function(result) {
            	self.isSuperTeacherOfSubject = result;
            },
            function(errResponse){
                console.error('Error while check super teacher');
            }
        );
    };
    
    /************ Insert Function: create chapter of subject **************/
    self.createChapter = function(){
    	SubjectService.createChapter(self.subjectId, self.chapterName)
            .then(
            function(result) {
                fetchAllChapterBySubjectId();
                toastr.success('Tạo Chương Thành Công', 'Thành công', {timeOut: 1000});
            },
            function(errResponse){
            	toastr.success('Tạo Chương Thất Bại', 'Thành công', {timeOut: 1000});
                console.error('Error while create chapter');
            }
        );
    };
    
    /************ Fetch function **************/
    function fetchAllTargetBySubjectId(){
    	SubjectService.fetchAllTargetBySubjectId(self.subjectId)
            .then(
            function(result) {
            	self.targetList = result.map(function(target) {
            		target.chapter = _.find(self.chapterList, function(chapter) {
            			return chapter.id === target.chapterid;
            		});
            		return target;
            	});
            },
            function(errResponse){
                console.error('Error while fetching target');
            }
        );
    };
    
    function fetchAllChapterBySubjectId(){
    	SubjectService.fetchAllChapterBySubjectId(self.subjectId)
            .then(
            function(result) {
            	self.chapterList = result;
            	self.chapterList = result.chapterList.map(function(chapter, index) {
            		chapter.questionL1Count = result.questionL1CountList[index];
            		chapter.questionL2Count = result.questionL2CountList[index];
            		chapter.questionL3Count = result.questionL3CountList[index];
            		chapter.questionL4Count = result.questionL4CountList[index];
            		chapter.questionL5Count = result.questionL5CountList[index];
            		chapter.questionCount = chapter.questionL1Count + chapter.questionL2Count +
            								chapter.questionL3Count + chapter.questionL4Count + chapter.questionL5Count;
            		return chapter;
            	});
            	fetchAllTargetBySubjectId();
            },
            function(errResponse){
                console.error('Error while fetching chapter');
            }
        );
    };
    
    function fetchAllExamsBySubjectId(){
    	SubjectService.fetchAllExamsBySubjectId(self.subjectId)
            .then(
            function(result) {
            	self.examsList = result.examsList.map(function(exams, index) {
            		exams.isApproved = exams.exams.status === "approved";
            		exams.isOpen = false;
            		exams.studentCount = result.studentCount[index];
            		if(exams.exams.endtime > new Date() && exams.exams.starttime < new Date()) exams.isOpen = true;
            		return exams;
            	});
            },
            function(errResponse){
                console.error('Error while fetching exams');
            }
        );
    };
    
    function fetchAllStudentBySubjectId(){
    	SubjectService.fetchAllStudentBySubjectId(self.subjectId)
            .then(
            function(result) {
            	self.studentList = result;
            },
            function(errResponse){
                console.error('Error while fetching students');
            }
        );
    };
    
    function fetchAllClassBySubjectId(){
    	SubjectService.fetchAllClassBySubjectId(self.subjectId)
            .then(
            function(result) {
            	self.classList = result.classesList.map(function(classes, index) {
            		classes.studentCount = result.studentCountList[index];
            		classes.state = classes.isopen ? "MỞ" : "ĐÓNG";
            		classes.examsCount = result.examsCountList[index];
            		return classes;
            	});
            },
            function(errResponse){
                console.error('Error while fetching classes');
            }
        );
    };
    
    function fetchSubjectBySubjectId(){
    	SubjectService.fetchSubjectBySubjectId(self.subjectId)
            .then(
            function(result) {
            	self.subject = result;
            },
            function(errResponse){
                console.error('Error while fetching subject');
            }
        );
    };
    
    self.removeTargetFromSubject = function(targetId) {
    	swal({
    		title: "Xác Nhận Xóa Tiêu Chí",
    		text: "Tiêu chí sẽ không thể phục hồi lại!",
    		type: "warning",
    		showCancelButton: true,
    		confirmButtonColor: "#DD6B55",
    		confirmButtonText: "OK",
    		closeOnConfirm: true 
    	}, function(isConfirm) {
    		if(isConfirm){
    			SubjectService.removeTargetFromSubject(self.subjectId, targetId)
                .then(
                function(result) {
                    fetchAllTargetBySubjectId();
                    toastr.success('Xóa Tiêu Chí Thành Công', 'Thành công', {timeOut: 1000});
                },
                function(errResponse){
                    console.error('Error while remove target');
                    toastr.error('Xóa Tiêu Chí Thất Bại', 'Thất Bại', {timeOut: 1000});
                }
                );
    		}
    	});
    }
    
    self.removeChapterFromSubject = function(chapterId) {
    	swal({
    		title: "Xác Nhận Xóa Chương",
    		text: "Chương sẽ không thể phục hồi lại!",
    		type: "warning",
    		showCancelButton: true,
    		confirmButtonColor: "#DD6B55",
    		confirmButtonText: "OK",
    		closeOnConfirm: true 
    	}, function(isConfirm) {
    		if(isConfirm){
    			SubjectService.removeChapterFromSubject(self.subjectId, chapterId)
                .then(
                function(result) {
                	fetchAllChapterBySubjectId();
                    toastr.success('Xóa Chương Thành Công', 'Thành công', {timeOut: 1000});
                },
                function(errResponse){
                	console.error('Error while remove chapter');
                    toastr.error('Xóa Chương Thất Bại', 'Thất Bại', {timeOut: 1000});
                }
                );
    		}
    	});
    }
    
    self.closeClass = function(classId) {
    	swal({
    		title: "Xác Nhận Đóng Lớp",
    		text: "Lớp sẽ không thể mở lại!",
    		type: "warning",
    		showCancelButton: true,
    		confirmButtonColor: "#DD6B55",
    		confirmButtonText: "OK",
    		closeOnConfirm: true 
    	}, function(isConfirm) {
    		if(isConfirm){
    			SubjectService.closeClass(self.subjectId, classId)
                .then(
                function(result) {
                	fetchAllClassBySubjectId();
                    toastr.success('Đóng Lớp Thành Công', 'Thành công', {timeOut: 1000});
                },
                function(errResponse){
                	console.error('Error while close class');
                    toastr.error('Đóng Lớp Thất Bại', 'Thất Bại', {timeOut: 1000});
                }
                );
    		}
    	});
    }
    
    self.fetchExamsResult = function(examsId) {
    	SubjectService.fetchExamsResult(self.subjectId, examsId)
        .then(
        function(result) {
        	self.resultExamList = [];  	
        	resultList = result;
        	_.each(result, function(resultExam) {
        		const isExist = _.find(self.resultExamList, function(e) {
        			return e.student.id === resultExam.studentid;
        		});
        
        		if (!isExist) {
        			var resultExamList = _.filter(result, function(e) {
            			return e.studentid === resultExam.studentid;
            		});
        			
        			var maxResultExam = _.max(resultExamList, function(resultExam) {
                		return resultExam.score;
                	});
        			
        			var minResultExam = _.min(resultExamList, function(resultExam) {
                		return resultExam.score;
                	});
        			
        			var student = _.find(self.studentList, function(e){
        				return e.id === resultExam.studentid;
        			});
        			
        			
        			self.resultExamList.push({
        				student: student,
        				resultExamList: resultExamList,
        				maxScore: maxResultExam.score,
        				minScore: minResultExam.score,
        				times: resultExamList.length
        			});
        		}
        	});
        },
        function(errResponse){
        	console.error('Error while fetch result exams');
        }
        );
    }
    
    self.resultDetail = function(student) {
    	self.selectedStudent = student; 
    	self.resultExamDetailList = _.map(_.filter(resultList, function(result) {
    		return result.studentid === student.id;
    	}), function(result) {
    		result.createdAt = moment(result.createdat).format('MMMM Do YYYY, h:mm:ss a');
			result.classs = _.find(self.classList, function(classs){
				return classs.id === result.classid;
			});
			return result;
    	});
    }

    fetchSubjectBySubjectId();
    fetchAllChapterBySubjectId();
    fetchAllClassBySubjectId();
    fetchAllStudentBySubjectId();
    fetchAllExamsBySubjectId();
    
    isSuperTeacherOfSubject();
}]);