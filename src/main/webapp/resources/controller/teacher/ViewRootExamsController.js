/**
 * Created by thanhtuuVox on 19-Feb-17.
 */
App.controller(
				'ViewRootExamsController',
				[		'$scope',
						'$location',
						'SubjectService',
						'RootExamsService',
						'ViewRootExamsService',
						function($scope, $location, SubjectService,
								RootExamsService, ViewRootExamsService) {
							var self = $scope;
							self.rootExams = {};
							self.rootExamId = $location.absUrl().substring(
									$location.absUrl()
											.lastIndexOf('Rootexams/') + 10,
									$location.absUrl().lastIndexOf('/'));
							console.log(self.rootExamId);

							self.ListRootQuestionBlock = [];
							self.ListRootQuestion = [];
							self.ListRootAnswer = [];
							
							self.blockRootQuestionRemove = [];
							self.ListQuestionRemove = [];
							self.ListAnswerRemove = [];
							
							self.blockRootQuestionAdd = [];
							self.ListQuestionAdd = [];
							self.ListAnswerAdd = [];
							self.ListTemp =[];
							
							self.blockQuestionListSearch = [];
							self.blockRootQuestionSearch = [];
							self.blockRootAnswerSearch = [];
							self.updateExamCount = 1;
							self.searchText = "";
							self.selectedChapter = {};
							self.chapterList = [];
							self.CodeExam = "";
							self.contentAnswer = "";
							self.isSolution = false;
							var timeOut;
							
							self.isDisabled = false;
							 						
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
							function fetchRootExamByExamId() {
								ViewRootExamsService.fetchRootExamByExamId(
										self.rootExamId).then(function(result) {
									console.log("IDExam" );
									console.log(result);
									self.rootExams = result;
									fetchRootExamByExamID2();
									seacrhQuestion();
									fetchAllChapterBySubjectId();
									
									
								}, function(errResponse) {
									console.log("Error fetch RootExam");
								});
							};

							/*
							 * Save Exam
							 */
							
							self.updateRootExam = function() {
						    	console.log("===================BLOCK ADD AND REMOVE========================");
						    	console.log(self.blockRootQuestionAdd);
				    			console.log(self.blockRootQuestionRemove);
				    			
				    			swal({
				    				  title: "NHẬP SỐ ĐỀ!",
				    				  text: "Nhập số đề",
				    				  type: "input",
				    				  showCancelButton: true,
				    				  closeOnConfirm: false,
				    				  inputPlaceholder: "Nhập số đề được tạo !",
				    				  showLoaderOnConfirm: true
				    				}, function (inputValue) {
				    				  if (inputValue === false) return false;
				    				  
				    				  if (inputValue === "") {
				    				    swal.showInputError("Phải nhập một số bất kì!");
				    				    return false;
				    				  }
				    				  if( Boolean(/^\d+$/.test(inputValue))===false) {
				    					  swal.showInputError("Phải nhập một con số !");
				    					  return false;
				    				  }
				    				  
				    					const examQuestionRootBlockRemove = self.blockRootQuestionRemove.map(function(blockQuestion) {
						    				return {
						    					examid: 0,
						    					examsid: self.rootExams.id,
						    					questionblockid: blockQuestion.id,
						    				}
						    			});
						    			
				    					const examQuestionRemoveList = self.ListQuestionRemove.map(function(question){
				    					    return {
				    					    	    id : null,
								    				examsid : self.rootExams.id,
								    				examid : null,
								    				questionblockid :question.questionblockid,
								    				questionid : question.id,
								    				solution : null,
								    				solutiontext : null,
								    				}
				    						
				    					});
				    					
				    					const examAnswerRemoveList = self.ListAnswerRemove.map(function(answer){
				    						return {
				    							id : null,
				    							questionid :answer.questionid,
				    							answerid : answer.id,
				    							checksolution : answer.issolution,
				    							questionblockid : answer.questionblockid,
				    							examid : null,
				    						}
				    					});
				    					
						    			const examQuestionRootBlockAdd = self.blockRootQuestionAdd.map(function(blockQuestion) {
						    				return {
						    					examid: 0,
						    					examsid: self.rootExams.id,
						    					questionblockid: blockQuestion.id,
						    				}
						    			});
						    			
						    			const examQuestionAddList = self.ListQuestionAdd.map(function(question){
						    				return {
						    				id : null,
						    				examsid : self.rootExams.id,
						    				examid : null,
						    				questionblockid :question.questionblockid,
						    				questionid : question.id,
						    				solution : null,
						    				solutiontext : null,
						    				}
						    			});
						    			
						    			const examAnswerAddList = self.ListAnswerAdd.map(function(answer){
						    				return {
						    					id : null,
						    					content : answer.content,
				    							questionid :answer.questionid,
				    							issolution : answer.issolution,
				    							questionblockid : answer.questionblockid,
				    						}
						    			});
						    		//	const codeExam = self.CodeExam;
						    			/*console.log("==================================dasssssssssssssssssssss");
						    			console.log(examQuestionRemoveList);
						    			console.log(examQuestionAddList);
						    			console.log(examAnswerRemoveList);
						    			console.log(examAnswerAddList);
						    			console.log(examQuestionRootBlockAdd);
						    			console.log(examQuestionRootBlockRemove);
						  */           
						    			/*setTimeout(function () {
				    					    swal("Ajax request finished!");
				    					  }, 5000);*/
						    			ViewRootExamsService.updateRootExam(
						    					examQuestionRootBlockAdd,
						    					examQuestionRootBlockRemove,						    					
						    		    		examQuestionRemoveList,
						    		    		examQuestionAddList,
						    		    		examAnswerRemoveList,
						    		    		examAnswerAddList,						    		    		
						    					inputValue,
						    					self.rootExamId,
						    					self.CodeExam)
						    	        .then(function(result){
						    	        	
						    	        	 self.isDisabled = true;
						    	        	 swal("Hoàn Tất ", "Số đề đã tạo : " + inputValue, "success",{timeOut: 1000});
						    	        	toastr.success('Cập Nhật Thành Công', 'Thành công', {timeOut: 1000});
						    	        	console.log(result);
						    	        	
						    	        	window.location.href = 'view';
						    	        },
						    	        function(errResponse){
						    	        	toastr.error('Cập Nhật Thất Bại', 'Thất Bại', {timeOut: 1000});
						    	            console.error('Error while fetching block question');
						    	        }
						    	        );
				    				});
				    		
						    }
						
						    function fetchAllChapterBySubjectId(){
						    	SubjectService.fetchAllChapterBySubjectId(self.rootExams.subjectid)
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
							
							
							function fetchRootExamByExamID2() {
								ViewRootExamsService.fetchRootExamByExam(self.rootExams.id)
										.then(
												function(result) {
													console.log("======================== RootExam ==============================");
													console.log(result);
													self.ListRootQuestionBlock = _.map(result, function(b, index) {
																		const rootQuestionBlock = b.rootQuestionBlock;
																		const questionList = _.map(b.questionList,
																						function(q) {
																							const question = q.question;
																							question.ListRootAnswer = q.answerList;
																							return question
																						});
																		rootQuestionBlock.ListRootQuestion = questionList;
																		console.log(rootQuestionBlock);
																		console.log("TEST FETCH BLOCK QUESTION : "+ rootQuestionBlock);

																		return rootQuestionBlock;

																	});
												},
												function(errRespone) {
													console.log("Error Fetch List Block Root Exam");
												});
							};

							/*
							 * Search Question
							 */
							self.onChangeSearchQuestion = function() {
								if (!!timeOut)
									clearTimeout(timeOut);
								timeOut = setTimeout(seacrhQuestion, 1000);
							};
							
							function seacrhQuestion() {
								ViewRootExamsService.fetchBlockQuestionByKeyWord(
												      self.rootExams.subjectid,
												      self.selectedChapter.id || 0
												     )
										.then(function(result) {
											        console.log("ROOT QUESTION SEARCH");
													console.log(result);
													self.blockQuestionListSearch =
													_.map(result, function(b, index) {
														const rootQuestionBlock = b.rootQuestionBlock;
														const questionList = _.map(b.questionList,
																		function(q) {
																			const question = q.question;
																			question.ListRootAnswer = q.answerList;
																			return question
																		});
														rootQuestionBlock.ListRootQuestion = questionList;
														console.log(rootQuestionBlock);
														//console.log("TEST FETCH BLOCK QUESTION : "+ rootQuestionBlock);

														return rootQuestionBlock;
													
																
                                  console.log(self.blockQuestionListSearch);
												},
												function(errResponse) {
													console.error('Error while fetching block question');
												});
							})

							self.onChangeSelectedChapter = function(data) {
								seacrhQuestion();
							}
							
							self.addAnswer = function(addAnswer,ListRootQuestion){
							  console.log("=================== List Root Question ==============");
							  console.log( ListRootQuestion);
							  
							  const listAnswerOfQuestion = ListRootQuestion.map(function(e){
								  const listRootAnswer = e.ListRootAnswer;
								  const questionId = e.id;
								  const questionblockId = e.questionblockid;
								  const answer = {content :self.contentAnswer ,
									      issolution :self.isSolution ,
									      questionid : questionId,
									      questionblockid : questionblockId,};
								  addAnswer.push(answer);
								  
								  //defined exam_answer
								  self.ListAnswerAdd.push(answer);
								  
								  return listRootAnswer;
							  });
							 
							  
							// listAnswerOfQuestion.push(addAnswer);
							  
							  console.log("===================listAnswerOfQuestion ==============");
							  console.log( listAnswerOfQuestion);
							  
						    };
                            
						    self.removeAnswer = function(answer,ListRootAnswer){
						    	console.log(ListRootAnswer);
						    	var index = ListRootAnswer.indexOf(answer);
						    	console.log(index);
						    	ListRootAnswer.splice(index, index+1);
						    	console.log("=========Answer Remove===============");
						    	if(answer.id!=null){
						    	self.ListAnswerRemove.push(answer);
						    	toastr.warning("Đán án đã được xóa !");
						    	}
							    console.log(self.ListAnswerRemove);
						    	
						    }
						    
						    self.removeQuestion = function(question,ListRootQuestion){
						    	//console.log("---------List Question-------------");
						    	console.log(ListRootQuestion);
						    	//console.log("=========Question===============");
						    	console.log(question);
						    	var index = ListRootQuestion.indexOf(question);
						    	console.log(index);
						    	ListRootQuestion.splice(index, index+1);
						    	console.log("=========Question Remove===============");
						    	self.ListQuestionRemove.push(question);
						    	toastr.warning("Câu hỏi đã được xóa !");
							    console.log(self.ListQuestionRemove);
						    }
						    
						    
						    self.removeBlockQuestion = function(blockQuestion, ListRootQuestionBlock){
						    	console.log(ListRootQuestionBlock);
						    	console.log(blockQuestion);
						    	var index = ListRootQuestionBlock.indexOf(blockQuestion);
						    	console.log("---------------List Question Block------------------------");
						    	console.log(index);
						    	self.blockRootQuestionRemove.push(blockQuestion);
						    	ListRootQuestionBlock.splice(index, index+1);
						    }
						    
						/*
						 * Add Question to RootExam
						 */    
						    self.addBlockQuestion = function(blockquestion, blockQuestionListSearch){
						    	console.log("--------------blockquestion-------------------");
						    	console.log(blockquestion);
						    	console.log("--------------blockQuestionListSearch-------------------");
						    	console.log(blockQuestionListSearch);
						    	//console.log(blockquestion.id);
						    	console.log(self.ListRootQuestionBlock);
						      	
						    	//console.log(self.ListRootQuestionBlock.find(blockquestion.id));
						        var checkblock =  _.find(self.ListRootQuestionBlock,function(result){
						        	return result.id == blockquestion.id;
						        })
						     
						        if(Boolean(checkblock)){
						        	//Add All Question
						        	var idBlock = blockquestion.id;
				                    var indexBlock = self.ListRootQuestionBlock.indexOf(checkblock);
				                    var indexBlockSearch = blockQuestionListSearch.indexOf(blockquestion);
				                       console.log("===================================");
						        		console.log(indexBlockSearch);
						        		
						        		console.log(blockQuestionListSearch[indexBlockSearch].ListRootQuestion.length);
						        	for(var k = 0; k < blockQuestionListSearch[indexBlockSearch].ListRootQuestion.length; k++){
						        		
						        		console.log(blockQuestionListSearch[indexBlockSearch].ListRootQuestion[k].id);
						        		if(!_.find(self.ListRootQuestionBlock[indexBlock].ListRootQuestion, function(result){
						        			return blockQuestionListSearch[indexBlockSearch].ListRootQuestion[k].id
						        			     == result.id;
						        		})){
						        			self.ListRootQuestionBlock.push(blockQuestionListSearch[indexBlockSearch].ListRootQuestion[k]);
						        			toastr.success("Block Đã Được Thêm !");
						        		}						        		
						        	}	
						        	
						        }
						        //Add Block Question
						        else{
						            blockquestion.isNew = true;
						            //self.blockQuestionAdd.push(blockQuestion);
						           // console.log("TEST ADD BLOCK");
						            //console.log(blockquestion);
						            //console.log("--------ListRootQuestionBlock--------");
						            //console.log(self.ListRootQuestionBlock);
						           
						            self.ListRootQuestionBlock.push(blockquestion);
						           // console.log("-----AFTER---ListRootQuestionBlock--------");
						           // console.log(self.ListRootQuestionBlock);
						            
						            self.blockRootQuestionAdd.push(blockquestion);
						            toastr.success("Block Đã Được Thêm Mới!");
						        }
						    	
						    }
						
						    /*
						     * Add Single Question
						     */
						    self.addQuestion = function(question, blockQuestion, blockQuestionListSearch){
						    	
						    	var boolquestion = false;
						    	var boolblock = false;
						    	 for(var e = 0 ; e <self.ListRootQuestionBlock.length ; e++ ){
						    		 if(self.ListRootQuestionBlock[e].id == blockQuestion.id){
						    			 boolblock = true;
						    		 }
						    		 for(var r = 0; r <self.ListRootQuestionBlock[e].ListRootQuestion.length; r++ ){
						    		 if(self.ListRootQuestionBlock[e].ListRootQuestion[r].id == question.id){
						    			 boolquestion = true;
						    		 }
						    	   }
						    	 }
						    		
						    	if(boolquestion){
						    		toastr.warning("Câu Hỏi Đã Tồn Tại !");
						    		return;
						    	}
						    	
						    	console.log(boolquestion);
						    	/*
						    	 * Case 1 : Block contains Question exist.
						    	 */
						    	if(boolblock && !boolquestion){
						    		
						    	/*	@TODO
						    	 */
						    		console.log("++++++++++++++++++++++++++++++++++");
						    		//console.log(self.List)
						    		console.log(blockQuestion);
						    	    const findBlock = _.find(self.ListRootQuestionBlock, function(e){
						    	    	return (e.id == blockQuestion.id);
						    	    });
						    	    console.log(findBlock);
						    		findBlock.ListRootQuestion.push(question);
						    		
						    		//self.ListRootQuestionBlock[index].ListRootQuestion.push(question);
						    		console.log(self.ListRootQuestionBlock);
						    		ListQuestionAdd.push(question);
						    		toastr.success("Câu Hỏi Đã Được Thêm Vào Block!");
						    	}
						    	
						    	/*
						    	 * ADD Question single
						    	 */
						    	
						    	if(!boolblock && !boolquestion){
						    		console.log(self.ListRootQuestionBlock);
						    		
						    		//self.ListRootQuestion.push(question);
						    		
						    		console.log("--------------------------------------");
						    		//console.log(listquestion);
						    		const blockadd = {
						    				id : blockQuestion.id,
						    				subjectid : blockQuestion.subjectid,
						    				content : blockQuestion.content,
						    				teacherid : blockQuestion.teacherid,
						    				keyword : blockQuestion.keyword,
						    				questioncount : blockQuestion.questioncount,
						    				ListRootQuestion : [],
						    		};
						    		blockadd.ListRootQuestion.push(question);
						    		
						    		self.ListRootQuestionBlock.push(blockadd);
						    		//Add Question
						    		self.ListQuestionAdd.push(question);
						    		console.log("ListRootQuestionBlock : " );
						    		console.log(self.ListRootQuestionBlock);
						    		
						    		
							    	 
						    		//console.log(self.ListRootQuestionBlock);
						    		toastr.success("Câu Hỏi Đã Được Thêm Mới!");
						    	}
						    	
						   
						    }
						    
						    };
							
							
							fetchRootExamByExamId();// Search ID RootExam
							//fetchRootExamByExamID2();// Fetch RootExam
							//fetchAllChapterBySubjectId();
							//seacrhQuestion(); //Search Question		
							//fetchExamsByExamsId();
					
						} ]);
