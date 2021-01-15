App.controller('RegisterController', ['$scope', 'UserService', function($scope, UserService) {
    var self = $scope;
    self.isTeacher = false;
    self.isStudent = true;
    
    self.user = {
    	email: "",
    	password: "",
    	repassword: "",
    	role: 'student'
    };
    
    self.teacher = {
    	name: "",
    	faculty: "",
    	position: "",
    };
    
    self.student = {
    	name: "",
    	faculty: "",
    	classname: "",
    };
    
    self.createAccount = function() {	
    	if (self.user.password !== self.user.repassword) {
    		toastr.error("Mật khẩu không khớp", 'Error', {timeOut: 1000});
    		return;
    	}
    	
    	if (self.user.password.length < 6 || self.user.password.length > 20) {
    		toastr.error("Mật khẩu từ 6 đến 20 ký tự", 'Error', {timeOut: 1000});
    		return;
    	}
    	
    	const data = {
        		user: _.omit(self.user, 'repassword'),
        		teacher: self.teacher,
        		student: self.student,
        	};
    	
    	UserService.createAccount(data)
        .then(
        function(result) {
        	if (result.type === "error") {
        		toastr.error(result.message, 'Error', {timeOut: 1000});
        		return;
        	}
        	
        	if (result.type === "success") {
        		window.location.href = 'register/success';
        		return;
        	}
        	
        },
        function(errResponse){
            console.error('Error while create Subjects');
            toastr.error('', 'Error', {timeOut: 1000});
        });
    }
    
    self.onChangeRole = function() {
    	if (self.user.role === "student") {
    		self.isStudent = true;
    		self.isTeacher = false;
    		return;
    	}
    	
    	if (self.user.role === "teacher") {
    		self.isStudent = false;
    		self.isTeacher = true;
    		return;
    	}
    }
}]);