App.controller('AdminController', ['$scope', 'AdminService', function($scope, AdminService) {
    var self = $scope;
    
    self.teacherList=[];
    self.subjectList=[];
    self.studentList=[];
    self.subjectName = "";
    self.selectedSubject = "";
    self.selectedTeacher = "";
    self.ListNotification = [];
    self.ListAddNotification =[];
    self.ListDelNotification = [];
    self.searchText = "";
    
    self.isShowStudent = false;
    self.isShowSubject = false;
    self.isShowTeacher = true;
    self.isShowNotification = true;
    self.isShowSlider = false;
    self.subjectTeach = [];
    self.subjectManage = [];
    
    self.Noti ={title : null,
    		    usercreate:null,
                body :null,
                link : null,            
    };
            
    
    self.title = "";
    self.usercreate = "";
    self.body = "";
    self.link = "";
    self.createat= "";
    self.modifiedat = "";
    self.Id = "";
    self.showNotify = false;
    self.showAdd = false;
    
    self.teacherTeach = [];
    self.teacherManage = [];
    
    self.showTeacher = function() {
    	self.isShowTeacher = true;
    	self.isShowStudent = false;
        self.isShowSubject = false;
    };
    
    self.showNotifyNoti=function(){
    	self.showNotify = true;
    	self.showAdd = false;
    }
    
    self.showAddNoti= function(){
    	self.showNotify = false;
    	self.showAdd = true;
    }
    
    
    self.showSubject = function() {
    	self.isShowTeacher = false;
    	self.isShowStudent = false;
        self.isShowSubject = true;
    };
    
    self.showStudent = function() {
    	self.isShowTeacher = false;
    	self.isShowStudent = true;
        self.isShowSubject = false;
    };
    
    
    self.showNotification = function(){
    	self.isShowNotification = true;
    	self.isShowSlider = false;
    }
    
    self.showEditSlider = function(){
    	self.isShowNotification = false;
    	self.isShowSlider = true;
    }
    
    self.fetchSubjectTeachOfTeacher = function(teacher) {
    	self.selectedTeacher = teacher;
    	AdminService.fetchSubjectTeachOfTeacher(teacher.id)
        .then(
        function(result) {
        	console.log(result);
        	self.subjectTeach = result;
        },
        function(errResponse){
            console.error('Error while create Subjects');
            toastr.error('', 'Error', {timeOut: 1000});
        });
    };
    
    self.fetchSubjectManageOfTeacher = function(teacher) {
    	self.selectedTeacher = teacher;
    	AdminService.fetchSubjectManageOfTeacher(teacher.id)
        .then(
        function(result) {
        	console.log(result);
        	self.subjectManage = result;
        },
        function(errResponse){
            console.error('Error while create Subjects');
            toastr.error('', 'Error', {timeOut: 1000});
        });
    };
    
    self.fetchTeacherTeachOfSubject = function(subject) {
    	self.selectedSubject = subject;
    	AdminService.fetchTeacherTeachOfSubject(subject.id)
        .then(
        function(result) {
        	console.log(result);
        	self.teacherTeach = result;
        },
        function(errResponse){
            console.error('Error while create Subjects');
            toastr.error('', 'Error', {timeOut: 1000});
        });
    };
    
    self.fetchTeacherManageOfSubject = function(subject) {
    	self.selectedSubject = subject;
    	AdminService.fetchTeacherManageOfSubject(subject.id)
        .then(
        function(result) {
        	console.log(result);
        	self.teacherManage = result;
        },
        function(errResponse){
            console.error('Error while create Subjects');
            toastr.error('', 'Error', {timeOut: 1000});
        });
    };
    
    self.unassignTeacher = function(teacherId, subjectId) {
    	AdminService.unassignTeacher(teacherId, subjectId)
        .then(
        function(result) {
        	if (self.selectedTeacher) self.fetchSubjectTeachOfTeacher(self.selectedTeacher);
        	if (self.selectedSubject) self.fetchTeacherTeachOfSubject(self.selectedSubject);
        	toastr.success('', 'Success', {timeOut: 1000});
        },
        function(errResponse){
            console.error('Error while create Subjects');
            toastr.error('', 'Error', {timeOut: 1000});
        });
    }
    
    self.unassignSuperTeacher = function(teacherId, subjectId) {
    	AdminService.unassignSuperTeacher(teacherId, subjectId)
        .then(
        function(result) {
        	console.log(result);
        	if (self.selectedTeacher)self.fetchSubjectManageOfTeacher(self.selectedTeacher);
        	if (self.selectedSubject) self.fetchTeacherManageOfSubject(self.selectedSubject);
        	toastr.success('', 'Success', {timeOut: 1000});
        },
        function(errResponse){
            console.error('Error while create Subjects');
            toastr.error('', 'Error', {timeOut: 1000});
        });
    }
    
    self.deleteSubject = function(subjectId) {
    	AdminService.deleteSubject(subjectId)
        .then(
        function(result) {
        	console.log(result);
        	fetchAllSubject();
        	toastr.success('', 'Success', {timeOut: 1000});
        },
        function(errResponse){
            console.error('Error while create Subjects');
            toastr.error('', 'Error', {timeOut: 1000});
        });
    }
    
    self.onClickAssignTeacherToSubject = function(subject) {
    	self.selectedSubject = subject;
    };
    
    self.onSubmitAssignTeacherToSubject = function() {
    	AdminService.superTeacherToSubject(self.selectedSubject.id, self.selectedTeacher.id)
        .then(
        function(result) {
        	console.log(result);
        	self.fetchTeacherManageOfSubject(self.selectedSubject);
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
        	fetchAllSubject();
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
        	self.fetchTeacherTeachOfSubject(self.selectedSubject);
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
            		self.subjectList = result;
                    console.log(self.subjectList);
            	}
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
    
    
    
    function fetchAllNotification(){
    	
    	AdminService.fetchAllNotifications().then(
    	 function (result){
    		 console.log("================fetch All NOTIFICATIONS===================");
    		 console.log(result);
    		 const listNotification = _.map(result,function(custom){
    			 const list = custom;
    			 list.editCreatedat = formatDay( custom.createdat);
    			 const strBody = custom.body;
    			 list.body = strBody;
    			 list.usercreat = custom.usercreate;
    			 const strLink = custom.link;
    			 list.link = strLink;
    			 list.title = custom.title;
    			 return list;
    		 });
    	 self.ListNotification = listNotification;
    		 
    		 
    	 },
    	 function(errResponse){
    		 console.error("Error Fetching Notification");
    	 }
    	
    	);
    	
    }
    function formatDay(utc){
      utc = new Date().toJSON().slice(0,10).replace(/-/g,'/');
    return utc;
    }
    
    function reset(){
   	 self.title = "";
   	 self.usercreate = "";
   	 self.body = " ";
   	 self.link= " ";
   	 self.Id ="";
    }
    
    self.delNotification = function(listNoti,ListNotification){
    	
    	console.log("======== Delete Notification =========");
    	console.log(listNoti);
    	var index = ListNotification.indexOf(listNoti);
    	console.log(index);
    	ListNotification.splice(index, index+1);
    	console.log("==========ID===========");
    	console.log(listNoti.id);
    	AdminService.delNotification(listNoti.id)
    		.then(
    		        function(result) {
    		        	console.log(result);
    		        	fetchAllNotification();
    		        	toastr.warning("Thông Báo Đã Xóa !");
    		        },
    		        function(errResponse){
    		            console.error('Error add notification');
    		            toastr.error('', 'Error', {timeOut: 1000});
    		        });
    	};
    	 
    
    	 self.modifyNotification2= function(Id, Title, Body, UserCreate, Link, CreateAt, ModifiedAt){
    		 
    	        Title = self.title;
    	    	Body = self.body;
    	    	UserCreate = self.usercreate;
    	    	Link = self.link;
    	    	CreateAt = self.createat;
    	    	ModifiedAt = self.modifiedat;
    	    	Id = self.Id;
    	        AdminService.modifyNotification(Id, Title, Body, UserCreate, Link, CreateAt, ModifiedAt)
    	        .then(
    	                function(result) {
    	                	console.log(result);
    	                	fetchAllNotification();
    	                	toastr.success("Thông Báo Đã Thay Đổi !");
    	                },
    	                function(errResponse){
    	                    console.error('Error add notification');
    	                    toastr.error('', 'Error', {timeOut: 1000});
    	                });
    	        
    	        reset();
    		 
    	 };
    
  
    self.modifyNotification= function(listNoti,ListNotification){
    	self.title =listNoti.title;
        self.usercreate = listNoti.usercreate;
        self.body = listNoti.body;
        self.link = listNoti.link;
        self.createat= listNoti.createat;
        self.modifiedat = listNoti.modifiedat;
        self.Id = listNoti.id;
        /*Title = self.title;
    	Body = self.body;
    	UserCreate = self.usercreate;
    	Link = self.link;
    	CreateAt = self.createat;
    	ModifiedAt = self.modifiedat;
        AdminService.modifyNotification(Id, Title, Body, UserCreate, Link, CreateAt, ModifiedAt)
        .then(
                function(result) {
                	console.log(result);
                	fetchAllNotification();
                	toastr.success("Thông Báo Đã Thay Đổi !");
                },
                function(errResponse){
                    console.error('Error add notification');
                    toastr.error('', 'Error', {timeOut: 1000});
                });
        
        reset();*/
    } ;
    
    self.addSubmitNotification = function(Title, Body, UserCreate, Link, CreateAt, ModifiedAt){
    	Title = self.title;
    	Body = self.body;
    	UserCreate = self.usercreate;
    	Link = self.link;
    	CreateAt = self.createat;
    	ModifiedAt = self.modifiedat;
    	const noti = self.Noti;
    	 noti.title = self.title;
    	 noti.usercreate = self.usercreate;
    	 noti.body = self.body;
    	 noti.link = self.link;
    	 self.ListNotification.push(noti);
    	 self.ListAddNotification.push(noti);
    	 
    	 
    	 AdminService.addNotiABC(Title, Body, UserCreate, Link, CreateAt, ModifiedAt)
    	  .then(
        function(result) {
        	console.log(result);
        	fetchAllNotification();
        	toastr.success("Thông Báo Đã Thêm!");
        },
        function(errResponse){
            console.error('Error add notification');
            toastr.error('', 'Error', {timeOut: 1000});
        });
    	 reset();
    	 console.log("=================================================");
    	 console.log(self.ListAddNotification);
    	 
    	
    };
    
    
    fetchAllTeachers();
    fetchAllStudents();
    fetchAllSubject();
    fetchAllNotification();
    
    
}]);