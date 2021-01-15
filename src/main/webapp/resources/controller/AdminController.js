App.controller('AdminController', ['$scope', 'AdminService', function($scope, AdminService) {
    var self = $scope;
    
    self.teacherList=[];
    self.subjectList=[];
    self.studentList=[];
    self.subjectName = "";
    self.selectedSubject = "";
    self.selectedTeacher = "";
    
    self.onClickAssignTeacherToSubject = function(subject) {
    	self.selectedSubject = subject;
    };
    
    self.onSubmitAssignTeacherToSubject = function() {
    	AdminService.assignTeacherToSubject(self.selectedSubject.id, self.selectedTeacher.id)
        .then(
        function(result) {
        	console.log(result);
        	toastr.success('', 'Success', {timeOut: 1000});
        },
        function(errResponse){
            console.error('Error while create Subjects');
            toastr.error('', 'Error', {timeOut: 1000});
        });
    };
    
    self.onSubmitCreateSubject = function() {
    	AdminService.createSubject(self.subjectName.trim())
        .then(
        function(result) {
        	console.log(result);
        	toastr.success('', 'Success', {timeOut: 1000});
        },
        function(errResponse){
            console.error('Error while create Subjects');
            toastr.error('', 'Error', {timeOut: 1000});
        });
    };
    
    self.onSubmitTeacherToSubject = function() {
    	AdminService.teacherToSubject(self.selectedSubject.id, self.selectedTeacher.id)
        .then(
        function(result) {
        	console.log(result);
        	toastr.success('', 'Success', {timeOut: 1000});
        },
        function(errResponse){
            console.error('Error while assign teacher to subject');
            toastr.error('', 'Error', {timeOut: 1000});
        });
    };
    
    function fetchAllTeachers(){
    	AdminService.fetchAllTeachers()
            .then(
            function(result) {
            	if(result.length) self.teacherList = result;
                console.log(result);
            	fetchAllSubject();
                $('#teacher-table').DataTable();
            },
            function(errResponse){
                console.error('Error while fetching Teachers');
            }
        );
    }
    
    function fetchAllSubject(){
    	AdminService.fetchAllSubject()
            .then(
            function(result) {
            	if(result.length) {
            		self.subjectList = result.map(function(subject) {
                		subject.teacher = _.find(self.teacherList, function(teacher) {
                			return teacher.id === subject.teacherid;
                		});
                		return subject;
                	});
                    console.log(self.subjectList);
            	}
                $('#subject-table').DataTable();
            },
            function(errResponse){
                console.error('Error while fetching Subjects');
            }
        );
    }
    
    function fetchAllStudents() {
    	AdminService.fetchAllStudents()
        .then(
	        function(result) {
	        	if (result.length) self.studentList = result;
	            console.log(result);
	            $('#student-table').DataTable();
	        },
	        function(errResponse){
	            console.error('Error while fetching Students');
	        }
	    );
    }
    
    fetchAllTeachers();
    fetchAllStudents();
}]);