/**
 * Created by anh.dang on 3/18/2017.
 */
/* Controllers */

'use strict';
App.controller('EvaluationController', ['$scope','$rootScope', 'EvaluationService', 'DashboardService', '$timeout', function($scope, $rootScope, EvaluationService, DashboardService, $timeout) {
    var self = $scope;
    var selfRoot = $rootScope;
    selfRoot.listResult =[];
    self.isReadResultSuccess = false;
    self.isSelectedSubject = false;
    self.isEvaluate = false;
    self.subjectList = [];
    self.chapterList = [];
    self.questionStatisticList = [];
    selfRoot.sendArray;
    selfRoot.numberQuestionFirstLine;

    self.chapterListEvaluation = [];

    self.chapter_evaluation_chapterList = [];
    self.chapter_evaluation_chapterListNotHave = [];

    self.isShowEvaluateChapter = false;

    self.isShowEvaluateOutcome = false;

    self.chapterEvaluationId = "";

    self.listQuestionEvaluationByChapter = [];
    self.numberPercentListByAnswerA = [];
    self.numberPercentListByAnswerB = [];
    self.numberPercentListByAnswerC = [];
    self.numberPercentListByAnswerD = [];
    self.numberPercentListByAnswerE = [];
    self.numberPercentListByAnswerNotAnswer = [];
    self.numberPercentListByAnswer = [];
    self.numberPercentListByTrueFalse = [];

    self.numberPercentListByAnswerCome = [];

    self.examcodeListAll = [];



    self.sendArray = function (numberQuestion, resultArray) {
        EvaluationService.sendResult(numberQuestion, resultArray)
            .then(
                function (result) {
                    selfRoot.listResult = result;
                    self.isReadResultSuccess = true;
                },
                function (errResponse) {
                    console.error('Lỗi khi đọc dữ liệu file');
                    toastr.error('', 'Error', {timeOut: 1000});
                });
    };

    function fetchAllSubjectOfTeacher(){
        EvaluationService.fetchAllSubjectTeachOfUser()
            .then(
                function(result) {
                    self.subjectList = result;
                },
                function(errResponse){
                    console.error('Error while fetching Subjects');
                }
            );
    };

    self.onChangeSelectedSubject = function() {
        self.isShowEvaluateChapter = false;
        EvaluationService.fetchAllChapterBySubjectId(self.subjectId)
            .then(
                function(result) {
                    self.chapterList = result.chapterList.map(function(chapter, index) {
                        chapter.targetList = [];
                        return chapter;
                    });
                },
                function(errResponse){
                    console.error('Error while fetching chapter');
                }
            );
        if (self.isSelectedSubject == false){
            self.fetchAllExamCodeOfResultFile();
        }
        self.isSelectedSubject = true;
    };


    self.onSubmitEvaluation = function () {
        self.onChangeSelectedSubject();
        self.isShowEvaluateChapter = false;
        self.isEvaluate = true;
        self.chapterEvaluationId = "";
        self.chapterListEvaluation = [];
        self.outcomeListEvaluation = [];
        self.dispersalListEvaluation =[];

        var targetList = _.flatten(_.pluck(self.chapterList, 'targetList'));

        var targetList = _.filter(targetList, function(target) {
            return ((typeof target.lv1  !== "undefined") || (typeof target.lv2  !== "undefined") ||
            (typeof target.lv3  !== "undefined") || (typeof target.lv4  !== "undefined") ||
            (typeof target.lv5  !== "undefined"));
        });

        var targetList = _.map(targetList, function(target) {
                var data = {
                    target: _.pick(target, 'chapterid', 'id', 'content', 'subjectid'),
                    lv1: target.lv1,
                    lv2: target.lv2,
                    lv3: target.lv3,
                    lv4: target.lv4,
                    lv5: target.lv5,
                };
            return data;
        });

        self.temp_chapterList = self.chapterList;
        for (var i=0; i<self.temp_chapterList.length; i++){
            delete self.temp_chapterList[i].targetList;
            delete self.temp_chapterList[i].isClick;
            delete self.temp_chapterList[i].isShow;
        }
       EvaluationService.fetchAllForEvaluation(targetList, selfRoot.numberQuestionFirstLine, selfRoot.listResult, self.temp_chapterList, self.examCode)
            .then(
            function(result) {
                console.log(result);
                $timeout(function () { self.getAllRenderDispersalList(self.dispersalLargestPercentListTemplates, "rectange-dispersal-larger-"); }, 2000);
                $timeout(function () { self.getAllRenderDispersalList(self.dispersalSmallestPercentListTemplates, "rectange-dispersal-smaller-"); }, 2000);
                $timeout(function () { self.getAllRenderLevelList(self.trueFalseOfLevel_1.numberPercentListByTrueFalse, 1, "rectange-level-" ); }, 2000);
                $timeout(function () { self.getAllRenderLevelList(self.trueFalseOfLevel_2.numberPercentListByTrueFalse, 2, "rectange-level-" ); }, 2000);
                $timeout(function () { self.getAllRenderLevelList(self.trueFalseOfLevel_3.numberPercentListByTrueFalse, 3, "rectange-level-" ); }, 2000);
                $timeout(function () { self.getAllRenderLevelList(self.trueFalseOfLevel_4.numberPercentListByTrueFalse, 4, "rectange-level-" ); }, 2000);
                $timeout(function () { self.getAllRenderLevelList(self.trueFalseOfLevel_5.numberPercentListByTrueFalse, 5, "rectange-level-" ); }, 2000);
                self.chapterListEvaluation = result.evaluationByChappter;
                self.chapter_evaluation_chapterList = self.chapterListEvaluation.chapterList;
                self.chapter_evaluation_chapterListNotHave = self.chapterListEvaluation.chapterListNotHave;
                self.chapterDifficult = self.chapterListEvaluation.chapterDifficult;
                self.chapterEasyMost = self.chapterListEvaluation.chapterEasyMost;
                //-----outcome
                self.outcomeListEvaluation = result.evaluationByOutcomes;
                self.outcome_evaluation_outcomeList = self.outcomeListEvaluation.outcomeList;
                self.outcomeDifficult = self.outcomeListEvaluation.outcomeDiffList;
                self.outcomeEasyMost = self.outcomeListEvaluation.outcomeEasyList;
                //-----dispersal
                self.dispersalListEvaluation = result.evaluationByDispersal;
                self.dispersalLargest = self.dispersalListEvaluation.disperssalLargestDispersal;
                self.dispersalLargestQuestionList = self.dispersalLargest.questionDisperSal;
                self.dispersalLargestPercentList = self.dispersalLargest.numberPercentList;
                self.dispersalSmallest = self.dispersalListEvaluation.disperssalSmallestDispersal;
                self.dispersalSmallestQuestionList = self.dispersalSmallest.questionDisperSal;
                self.dispersalSmallestPercentList = self.dispersalSmallest.numberPercentList;
                //----------
                self.dispersalLargestList = self.dispersalListEvaluation.dispersalForEvaluationByDispersalLargerList;
                self.dispersalLargestQuestionListTemplates = self.dispersalLargestList.questionDisperSal;
                self.dispersalLargestPercentListTemplates = self.dispersalLargestList.numberPercentList;

                self.dispersalSmallestList = self.dispersalListEvaluation.dispersalForEvaluationByDispersalSmallList;
                self.dispersalSmallestQuestionListTemplates = self.dispersalSmallestList.questionDisperSal;
                self.dispersalSmallestPercentListTemplates = self.dispersalSmallestList.numberPercentList;

                //---------level
                self.levelListEvaluation = result.evaluationByLevel;
                self.questionEasyDiffListMax = self.levelListEvaluation.questionEasyDiffListMax;
                self.questionEasyDiffListMin = self.levelListEvaluation.questionEasyDiffListMin;
                self.questionLevelChangeList = self.levelListEvaluation.questionLevelChangeList;

                self.trueFalseOfLevel_1 = self.levelListEvaluation.trueFalseOfLevel_1;
                self.trueFalseOfLevel_2 = self.levelListEvaluation.trueFalseOfLevel_2;
                self.trueFalseOfLevel_3 = self.levelListEvaluation.trueFalseOfLevel_3;
                self.trueFalseOfLevel_4 = self.levelListEvaluation.trueFalseOfLevel_4;
                self.trueFalseOfLevel_5 = self.levelListEvaluation.trueFalseOfLevel_5;

            },
            function(errResponse){
                console.error('Error while fetching targets');
                self.isEvaluate = false;
                toastr.error('', 'Vui lòng nhập câu trong đề theo level trong chương để tiến hành đánh giá', {timeOut:8000});
            }
        );
        self.chapterList = [];
    }


    self.getAllRenderLevelList = function (type, number, idString) {

            var id = idString + number;
            var c = document.getElementById(id);
            if (type[0].percent > 0){
                var ctx = c.getContext("2d");
                var xs = 800 * type[0].percent / 100;
                ctx.fillStyle = "rgb(0,255,0)";
                ctx.fillRect(0, 0, xs, 50);

            }
            if (type[1].percent > 0){
                var ctx = c.getContext("2d");
                var xs = 800 * type[1].percent / 100;
                ctx.fillStyle = "rgb(255,0,0)";
                ctx.fillRect(800 * type[0].percent / 100, 0,  xs, 50);
            }
            if (type[2].percent > 0){
                var ctx = c.getContext("2d");
                var xs = 800 * type[2].percent / 100;
                ctx.fillStyle = "rgb(255,0,255)";
                var sumTrueFalse = (800 * type[0].percent / 100) +
                    (800 * type[1].percent / 100);
                ctx.fillRect(sumTrueFalse, 0,  xs, 50);
            }
            ctx.stroke();

    }


    self.getAllRenderDispersalList = function (type, idString) {
        for (var i=0; i<(type.length); i+=6){
            var id = idString + i;
            var c = document.getElementById(id);
            if (type[i].percent > 0){
                var ctx = c.getContext("2d");
                var xs = 800 * type[i].percent / 100;
                ctx.fillStyle = "rgb(0,0,255)";
                ctx.fillRect(0, 0, xs, 50);

            }
            if (type[i+1].percent > 0){
                var ctx = c.getContext("2d");
                var xs = 800 * type[i+1].percent / 100;
                ctx.fillStyle = "rgb(0,255,255)";
                ctx.fillRect(800 * type[i].percent / 100, 0,  xs, 50);
            }
            if (type[i+2].percent > 0){
                var ctx = c.getContext("2d");
                var xs = 800 * type[i+2].percent / 100;
                ctx.fillStyle = "rgb(255,0,0)";
                var sumAB = (800 * type[i].percent / 100) +
                    (800 * type[i+1].percent / 100);
                ctx.fillRect(sumAB, 0,  xs, 50);
            }

            if (type[i+3].percent > 0){
                var ctx = c.getContext("2d");
                var xs = 800 * type[i+3].percent / 100;
                ctx.fillStyle = "rgb(0,255,0)";
                var sumABC = (800 * type[i].percent / 100) +
                    (800 * type[i+1].percent / 100) +
                    (800 * type[i+2].percent / 100);
                ctx.fillRect(sumABC, 0,  xs, 50);
            }

            if (type[i+4].percent > 0){
                var ctx = c.getContext("2d");
                var xs = 800 * type[i+4].percent / 100;
                ctx.fillStyle = "rgb(255,0,255)";
                var sumABCD = (800 * type[i].percent / 100) +
                    (800 * type[i+1].percent / 100) +
                    (800 * type[i+2].percent / 100) +
                    (800 * type[i+3].percent / 100);
                ctx.fillRect(sumABCD, 0,  xs, 50);
            }
            if (type[i+5].percent > 0){
                var ctx = c.getContext("2d");
                var xs = 800 * type[i+5].percent / 100;
                ctx.fillStyle = "rgb(123,123,0)";
                var sumABCDE = (800 * type[i].percent / 100) +
                    (800 * type[i+1].percent / 100) +
                    (800 * type[i+2].percent / 100) +
                    (800 * type[i+3].percent / 100) +
                    (800 * type[i+4].percent / 100);
                ctx.fillRect(sumABCDE, 0,  xs, 50);
            }
            ctx.stroke();
        }

    }

    self.onClickChapter = function(chapter) {
        chapter.isShow = !chapter.isShow;
        if (!chapter.isClick) {
            fetchAllTargetBySubjectIdChapterId(chapter);
            chapter.isClick = true;
        }
    }


    self.onChangeEvaluationByChapter = function(chapterEvaluationId) {
        $timeout(function () { self.getAllRender(); }, 2000);
        self.numberPercentListByAnswer = [];
        if (chapterEvaluationId != null){
            EvaluationService.fetchEvaluationByChapterSelect(chapterEvaluationId)
                .then(
                    function(result) {
                        self.listQuestionEvaluationByChapter = result.listQuestion;
                        self.numberPercentListByTrueFalse = result.numberPercentListByTrueFalse;

                        for (var i=0; i<result.numberPercentListByAnswer.length/6; i++) {
                            self.numberPercentListByAnswerA = result.numberPercentListByAnswer[6*i];
                            self.numberPercentListByAnswerB = result.numberPercentListByAnswer[1 + 6*i];
                            self.numberPercentListByAnswerC = result.numberPercentListByAnswer[2 + 6*i];
                            self.numberPercentListByAnswerD = result.numberPercentListByAnswer[3 + 6*i];
                            self.numberPercentListByAnswerE = result.numberPercentListByAnswer[4 + 6*i];
                            self.numberPercentListByAnswerNotAnswer = result.numberPercentListByAnswer[5 + 6*i];
                            var answerType = {
                                typeA : self.numberPercentListByAnswerA,
                                typeB : self.numberPercentListByAnswerB,
                                typeC : self.numberPercentListByAnswerC,
                                typeD : self.numberPercentListByAnswerD,
                                typeE : self.numberPercentListByAnswerE,
                                typeNotAnswer : self.numberPercentListByAnswerNotAnswer,
                                numberQuestion : self.listQuestionEvaluationByChapter[i]
                            };
                            self.numberPercentListByAnswer.push(answerType);
                        }
                    },
                    function(errResponse){
                        console.error('Error while evaluation by chapter');
                    }
                );
            self.isShowEvaluateChapter = true;
        }

    }

    self.onChangeEvaluationByOutcome = function(outcomeEvaluationId) {
        $timeout(function () { self.getAllRenderOutcome(); }, 2000);
        self.numberPercentListByAnswerCome = [];
        if (outcomeEvaluationId != null){
            EvaluationService.fetchEvaluationByOutcomeSelect(outcomeEvaluationId)
                .then(
                    function(result) {
                        console.log(result);
                        self.listQuestionEvaluationByOutcome = result.listQuestion;
                        self.numberPercentListByTrueFalseOutcome = result.numberPercentListByTrueFalse;

                        for (var i=0; i<result.numberPercentListByAnswer.length/6; i++) {
                            self.numberPercentListByAnswerAOutcome = result.numberPercentListByAnswer[6*i];
                            self.numberPercentListByAnswerBOutcome = result.numberPercentListByAnswer[1 + 6*i];
                            self.numberPercentListByAnswerCOutcome = result.numberPercentListByAnswer[2 + 6*i];
                            self.numberPercentListByAnswerDOutcome = result.numberPercentListByAnswer[3 + 6*i];
                            self.numberPercentListByAnswerEOutcome = result.numberPercentListByAnswer[4 + 6*i];
                            self.numberPercentListByAnswerNotAnswerOutcome = result.numberPercentListByAnswer[5 + 6*i];
                            var answerType = {
                                typeA : self.numberPercentListByAnswerAOutcome,
                                typeB : self.numberPercentListByAnswerBOutcome,
                                typeC : self.numberPercentListByAnswerCOutcome,
                                typeD : self.numberPercentListByAnswerDOutcome,
                                typeE : self.numberPercentListByAnswerEOutcome,
                                typeNotAnswer : self.numberPercentListByAnswerNotAnswerOutcome,
                                numberQuestion : self.listQuestionEvaluationByOutcome[i]
                            };
                            self.numberPercentListByAnswerCome.push(answerType);
                        }
                    },
                    function(errResponse){
                        console.error('Error while evaluation by chapter');
                    }
                );
            self.isShowEvaluateOutcome = true;
        }

    }

    self.getAllRenderOutcome = function () {
        for (var i=0; i<self.numberPercentListByAnswerCome.length; i++){
            var id = "rectange-outcome-" + i;
            var c = document.getElementById(id);
            if (self.numberPercentListByAnswerCome[i].typeA.percent > 0){
                var ctx = c.getContext("2d");
                var xs = 800 * self.numberPercentListByAnswerCome[i].typeA.percent / 100;
                ctx.fillStyle = "rgb(0,0,255)";
                ctx.fillRect(0, 0, xs, 50);

            }
            if (self.numberPercentListByAnswerCome[i].typeB.percent > 0){
                var ctx = c.getContext("2d");
                var xs = 800 * self.numberPercentListByAnswerCome[i].typeB.percent / 100;
                ctx.fillStyle = "rgb(0,255,255)";
                ctx.fillRect(800 * self.numberPercentListByAnswerCome[i].typeA.percent / 100, 0,  xs, 50);
            }
            if (self.numberPercentListByAnswerCome[i].typeC.percent > 0){
                var ctx = c.getContext("2d");
                var xs = 800 * self.numberPercentListByAnswerCome[i].typeC.percent / 100;
                ctx.fillStyle = "rgb(255,0,0)";
                var sumAB = (800 * self.numberPercentListByAnswerCome[i].typeA.percent / 100) +
                    (800 * self.numberPercentListByAnswerCome[i].typeB.percent / 100);
                ctx.fillRect(sumAB, 0,  xs, 50);
            }

            if (self.numberPercentListByAnswerCome[i].typeD.percent > 0){
                var ctx = c.getContext("2d");
                var xs = 800 * self.numberPercentListByAnswerCome[i].typeD.percent / 100;
                ctx.fillStyle = "rgb(0,255,0)";
                var sumABC = (800 * self.numberPercentListByAnswerCome[i].typeA.percent / 100) +
                    (800 * self.numberPercentListByAnswerCome[i].typeB.percent / 100) +
                    (800 * self.numberPercentListByAnswerCome[i].typeC.percent / 100);
                ctx.fillRect(sumABC, 0,  xs, 50);
            }

            if (self.numberPercentListByAnswerCome[i].typeE.percent > 0){
                var ctx = c.getContext("2d");
                var xs = 800 * self.numberPercentListByAnswerCome[i].typeE.percent / 100;
                ctx.fillStyle = "rgb(255,0,255)";
                var sumABCD = (800 * self.numberPercentListByAnswerCome[i].typeA.percent / 100) +
                    (800 * self.numberPercentListByAnswerCome[i].typeB.percent / 100) +
                    (800 * self.numberPercentListByAnswerCome[i].typeC.percent / 100) +
                    (800 * self.numberPercentListByAnswerCome[i].typeD.percent / 100);
                ctx.fillRect(sumABCD, 0,  xs, 50);
            }
            if (self.numberPercentListByAnswerCome[i].typeNotAnswer.percent > 0){
                var ctx = c.getContext("2d");
                var xs = 800 * self.numberPercentListByAnswerCome[i].typeNotAnswer.percent / 100;
                ctx.fillStyle = "rgb(123,123,0)";
                var sumABCDE = (800 * self.numberPercentListByAnswerCome[i].typeA.percent / 100) +
                    (800 * self.numberPercentListByAnswerCome[i].typeB.percent / 100) +
                    (800 * self.numberPercentListByAnswerCome[i].typeC.percent / 100) +
                    (800 * self.numberPercentListByAnswerCome[i].typeD.percent / 100) +
                    (800 * self.numberPercentListByAnswerCome[i].typeE.percent / 100);
                ctx.fillRect(sumABCDE, 0,  xs, 50);
            }
            ctx.stroke();
        }

    }

    self.getAllRender = function () {
        for (var i=0; i<self.numberPercentListByAnswer.length; i++){
            var id = "rectange-" + i;
            var c = document.getElementById(id);
            if (self.numberPercentListByAnswer[i].typeA.percent > 0){
                var ctx = c.getContext("2d");
                var xs = 800 * self.numberPercentListByAnswer[i].typeA.percent / 100;
                ctx.fillStyle = "rgb(0,0,255)";
                ctx.fillRect(0, 0, xs, 50);

            }
            if (self.numberPercentListByAnswer[i].typeB.percent > 0){
                var ctx = c.getContext("2d");
                var xs = 800 * self.numberPercentListByAnswer[i].typeB.percent / 100;
                ctx.fillStyle = "rgb(0,255,255)";
                ctx.fillRect(800 * self.numberPercentListByAnswer[i].typeA.percent / 100, 0,  xs, 50);
             }
            if (self.numberPercentListByAnswer[i].typeC.percent > 0){
                var ctx = c.getContext("2d");
                var xs = 800 * self.numberPercentListByAnswer[i].typeC.percent / 100;
                ctx.fillStyle = "rgb(255,0,0)";
                var sumAB = (800 * self.numberPercentListByAnswer[i].typeA.percent / 100) +
                    (800 * self.numberPercentListByAnswer[i].typeB.percent / 100);
                ctx.fillRect(sumAB, 0,  xs, 50);
            }

            if (self.numberPercentListByAnswer[i].typeD.percent > 0){
                var ctx = c.getContext("2d");
                var xs = 800 * self.numberPercentListByAnswer[i].typeD.percent / 100;
                ctx.fillStyle = "rgb(0,255,0)";
                var sumABC = (800 * self.numberPercentListByAnswer[i].typeA.percent / 100) +
                    (800 * self.numberPercentListByAnswer[i].typeB.percent / 100) +
                    (800 * self.numberPercentListByAnswer[i].typeC.percent / 100);
                ctx.fillRect(sumABC, 0,  xs, 50);
            }

            if (self.numberPercentListByAnswer[i].typeE.percent > 0){
                var ctx = c.getContext("2d");
                var xs = 800 * self.numberPercentListByAnswer[i].typeE.percent / 100;
                ctx.fillStyle = "rgb(255,0,255)";
                var sumABCD = (800 * self.numberPercentListByAnswer[i].typeA.percent / 100) +
                    (800 * self.numberPercentListByAnswer[i].typeB.percent / 100) +
                    (800 * self.numberPercentListByAnswer[i].typeC.percent / 100) +
                    (800 * self.numberPercentListByAnswer[i].typeD.percent / 100);
                ctx.fillRect(sumABCD, 0,  xs, 50);
            }
            if (self.numberPercentListByAnswer[i].typeNotAnswer.percent > 0){
                var ctx = c.getContext("2d");
                var xs = 800 * self.numberPercentListByAnswer[i].typeNotAnswer.percent / 100;
                ctx.fillStyle = "rgb(123,123,0)";
                var sumABCDE = (800 * self.numberPercentListByAnswer[i].typeA.percent / 100) +
                    (800 * self.numberPercentListByAnswer[i].typeB.percent / 100) +
                    (800 * self.numberPercentListByAnswer[i].typeC.percent / 100) +
                    (800 * self.numberPercentListByAnswer[i].typeD.percent / 100) +
                    (800 * self.numberPercentListByAnswer[i].typeE.percent / 100);
                ctx.fillRect(sumABCDE, 0,  xs, 50);
            }
            ctx.stroke();
        }
    }

    function fetchAllTargetBySubjectIdChapterId(chapter) {
        EvaluationService.fetchAllTargetBySubjectIdChapterId1(self.subjectId, chapter.id)
            .then(
                function(result) {
                    for (var i=0; i<result.length; i++){
                        chapter.targetList.push(result[i].target);
                    }
                },
                function(errResponse){
                    console.error('Error while fetching targets');
                }
            );
    }




    function to_csv(workbook) {
        var result = [];
        workbook.SheetNames.forEach(function(sheetName) {
            var csv = XLSX.utils.sheet_to_csv(workbook.Sheets[sheetName]);
            selfRoot.sendArray = XLSX.utils.sheet_to_json(workbook.Sheets[sheetName]);
            var firstLine = csv.split('\n')[0];

            var columnFirstLine = firstLine.split(',');

            var re = new RegExp("C[0-9]+");
            var numberQuestionFirstLine = 0;
            for (var i=0; i<columnFirstLine.length;i++){
                if(re.test(columnFirstLine[i])){
                    numberQuestionFirstLine = numberQuestionFirstLine + 1;
                }
            }
            selfRoot.numberQuestionFirstLine = numberQuestionFirstLine;
            self.sendArray(numberQuestionFirstLine, selfRoot.sendArray);
            for (var i=1; i<=numberQuestionFirstLine; i++){
                self.questionStatisticList.push(i);
            }

            if(csv.length > 0){
                result.push("SHEET: " + sheetName);
                result.push("");
                result.push(csv);
            }
        });
        return result.join("\n");
    }

    function process_wb(wb) {
        var output = "";
        output = to_csv(wb);
        if(out.innerText === undefined) out.textContent = output;
        else out.innerText = output;
    }

    function fixdata(data) {
        var o = "", l = 0, w = 10240;
        for(; l<data.byteLength/w; ++l) o+=String.fromCharCode.apply(null,new Uint8Array(data.slice(l*w,l*w+w)));
        o+=String.fromCharCode.apply(null, new Uint8Array(data.slice(l*w)));
        return o;
    }

    function handleFile(e) {
        var files = e.target.files;
        var f = files[0];
        {
            var reader = new FileReader();
            var name = f.name;
            reader.onload = function(e) {
                var data = e.target.result;
                var wb;
                var arr = fixdata(data);
                wb = XLSX.read(btoa(arr), {type: 'base64'});
                process_wb(wb);
            };
            reader.readAsArrayBuffer(f);
        }
    }

    self.fetchAllExamCodeOfResultFile = function(){
        self.examcodeListAll = [];
        DashboardService.fetchAllExamCodeOfResultFile(selfRoot.listResult)
            .then(
                function(result) {
                    if (result.length > 0) {
                        self.examcodeList = result;
                        self.examcodeListAll.push({
                            name: "Tất cả các mã đề",
                            value: "all"
                        });
                        for (var i=0; i<result.length; i++){
                            self.examcodeListAll.push({
                                value: result[i],
                                name: result[i]
                            })
                        }
                        self.examCode = result[0];
                    }
                },
                function(errResponse){
                    self.isReadResultSuccess = false;
                    console.error('Lỗi khi lấy mã đề');
                    toastr.error('', 'Lỗi khi lấy mã đề', {timeOut: 1000});
                }
            );
    };

    fetchAllSubjectOfTeacher();
    var xlf = document.getElementById('uploadfile');
    if(xlf.addEventListener) xlf.addEventListener('change', handleFile, false);

}]);