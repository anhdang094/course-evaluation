App.controller('ClassController', ['$scope', '$location', 'ClassService', function($scope, $location, ClassService) {
    var self = $scope;
    self.classId = Number($location.absUrl().substring($location.absUrl().lastIndexOf('/') + 1));
    self.studentEmail = "";
    self.class = {};
    
    self.studentList = [];
    self.resultExamList = [];
    self.resultExamDetailList = [];
    
    var resultList = [];
    self.selectedStudent = {};
    
    self.fetchResultExam = function(examsId) {
    	ClassService.fetchExamsResultStudent(self.classId, examsId)
    	.then(
    	function(result) {
    		self.resultExamDetailList = result.map(function(r) {
    			r.createdAt = moment(r.createdat).format('MMMM Do YYYY, h:mm:ss a');
    			return r;
    		});
    	},
    	function(errResponse) {
    		console.log(errResponse);
    		toastr.error('Thêm Học Viên Thất Bại', 'Lỗi', {timeOut: 1000});
    	});
    }
    
    /************ show hide tab variable **************/
    self.isShowExams = true;
    self.isShowStudent = false;
    
    /************ Handler show hide tab **************/
    self.showStudent = function() {
        self.isShowExams = false;
        self.isShowStudent = true;
    };
    
    self.showExams = function() {
        self.isShowExams = true;
        self.isShowStudent = false;
    };
    
    self.assignStudentToClass = function() {
    	ClassService.assignStudentToClass(self.class.subjectid, self.classId, self.studentEmail)
    	.then(
    	function(result) {
    		console.log(result);
    		fetchStudentOfClass();
    		toastr.success('Thêm Học Viên Thành Công', 'Thành Công', {timeOut: 1000});
    		self.studentEmail = "";
    	},
    	function(errResponse) {
    		console.log(errResponse);
    		toastr.error('Thêm Học Viên Thất Bại', 'Lỗi', {timeOut: 1000});
    	});
    };
    
    /************ Remove Function: remove student from class **************/
    self.removeStudentFromClass = function(studentId) {
    	ClassService.removeStudentFromClass(studentId, self.classId)
	        .then(
	        function(result) {
	            console.log(result);
	            fetchStudentOfClass();
	            toastr.success('Xóa Sinh Viên Thành Công', 'Thành công', {timeOut: 1000});
	        },
	        function(errResponse){
	            console.error('Error while create chapter');
	            toastr.error('Xóa Sinh Viên Thất Bại', 'Lỗi', {timeOut: 1000});
	        }
	    );
    };
    
    /************ Fetch function **************/
    function fetchStudentOfClass() {
    	ClassService.fetchStudentOfClass(self.classId)
    	.then(
    	function(result) {
    		self.studentList = result;
    	},
    	function(errResponse) {
    		console.log(errResponse);
    	});
    };
    
    function fetchClassByClassId() {
    	ClassService.fetchClassByClassId(self.classId)
    	.then(
    	function(result) {
    		self.class = result;
    	},
    	function(errResponse) {
    		console.log(errResponse);
    	});
    };
    
    function fetchAllExamsByClassId(){
    	ClassService.fetchAllExamsByClassId(self.classId)
            .then(
            function(result) {
            	self.examsList = result.examsList.map(function(exams, index) {
            		exams.isApproved = exams.exams.status === "approved";
            		exams.isOpen = false;
            		exams.studentCount = result.studentCount[index];
            		if(exams.exams.endtime > new Date()) exams.isOpen = true;
            		return exams;
            	});
            },
            function(errResponse){
                console.error('Error while fetching Subjects');
            }
        );
    };
    
    self.resultDetail = function(student) {
    	self.selectedStudent = student; 
    	self.resultExamDetailList = _.map(_.filter(resultList, function(result) {
    		return result.studentid === student.id && result.classid === self.classId;
    	}), function (result) {
    		result.createdAt = moment(result.createdat).format('MMMM Do YYYY, h:mm:ss a');
			return result;
    	});
    }
    
    self.fetchExamsResult = function(examsId) {
    	ClassService.fetchExamsResult(self.classId,  examsId)
        .then(
        function(result) {
        	resultList = result;
        	self.resultExamList = [];  	
        	_.each(result, function(resultExam) {
        		self.resultList = resultExam;
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
    
    fetchStudentOfClass();
    fetchClassByClassId();
    fetchAllExamsByClassId();
}]);