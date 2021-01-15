App.controller('ClassListController', ['$scope', 'ClassListService', 'SubjectsService', 'SubjectService', function($scope, ClassListService, SubjectsService, SubjectService) {
    var self = $scope;
    self.classList = [];
    self.subjectList = [];
    self.selectedSubject = "";
    self.className = "";

    self.createClass = function() {
    	ClassListService.createClass(self.selectedSubject.id, self.className)
    	.then(
    	function(result) {
    		console.log(result);
    		self.className = "";
    		fetchClassOfUser();
    	},
    	function(errResponse) {
    		console.log(errResponse);
    	});
    };
    
    function fetchClassOfUser() {
    	ClassListService.fetchClassOfUser()
    	.then(
    	function(result) {
    		self.classList = result.classesList.map(function(classes, index) {
        		classes.studentCount = result.studentCountList[index];
        		classes.state = classes.isopen ? "MỞ" : "ĐÓNG";
        		classes.examsCount = result.examsCountList[index];
        		classes.subject = _.find(result.subjectList, function(subject) {
        			return subject.id === classes.subjectid;
        		});
        		return classes;
        	});;
    	},
    	function(errResponse) {
    		console.log(errResponse);
    	});
    };
    
    function fetchSubjectOfTeacher(){
    	SubjectsService.fetchAllSubjectTeachOfUser()
            .then(
            function(result) {
            	self.subjectList = result;
            },
            function(errResponse){
            	console.log(errResponse);
            }
        );
    };
    
    self.closeClass = function(subjectId, classId) {
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
    			SubjectService.closeClass(subjectId, classId)
                .then(
                function(result) {
                	fetchClassOfUser();
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
    
    fetchClassOfUser();
    fetchSubjectOfTeacher();
}]);