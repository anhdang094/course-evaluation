App.controller('SubjectController', ['$scope', '$routeParams', 'SubjectService', function($scope, $routeParams, SubjectService) {
    var self = $scope;
    self.chapterList = [];
    self.subjectId = 4;
    self.chapterName = "";
    self.isSuperTeacherOfSubject = false;
    console.log("qwe");
    self.createChapter = function(){
    	SubjectService.createChapter(self.subjectId, self.chapterName)
            .then(
            function(result) {
                console.log(result);
                fetchAllChapterBySubjectId();
            },
            function(errResponse){
                console.error('Error while create chapter');
            }
        );
    };
    
    function fetchAllChapterBySubjectId(){
    	SubjectService.fetchAllChapterBySubjectId(self.subjectId)
            .then(
            function(result) {
            	if(result.length) self.chapterList = result;
                console.log(result);
            },
            function(errResponse){
                console.error('Error while fetching Subjects');
            }
        );
    };
    
    function isSuperTeacherOfSubject(){
    	SubjectService.isSuperTeacherOfSubject(self.subjectId)
            .then(
            function(result) {
            	self.isSuperTeacherOfSubject = result;
            	console.log(result);
            },
            function(errResponse){
                console.error('Error while fetching Subjects');
            }
        );
    };

    fetchAllChapterBySubjectId();
    isSuperTeacherOfSubject();
}]);