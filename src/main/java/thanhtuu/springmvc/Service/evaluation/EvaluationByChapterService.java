package thanhtuu.springmvc.Service.evaluation;

import thanhtuu.springmvc.Domain.Chapter;
import thanhtuu.springmvc.Temporary.Evaluation.Chapter.ChapterEasyDiff;
import thanhtuu.springmvc.Temporary.Evaluation.EvaluationByOneObjectModel;
import thanhtuu.springmvc.Temporary.Evaluation.Chapter.ChapterForEvaluationByChapter;
import thanhtuu.springmvc.Temporary.Evaluation.EvaluationByChappter;
import thanhtuu.springmvc.Temporary.ResultExcel.ResultQuestionExcel;
import thanhtuu.springmvc.Todo.Evaluation.ChapterEvaluation;
import thanhtuu.springmvc.Todo.Evaluation.Evaluation;
import thanhtuu.springmvc.Todo.Evaluation.LevelEvaluation;
import thanhtuu.springmvc.Todo.Evaluation.TargetEvaluation;
import thanhtuu.springmvc.Todo.ResultXLS;

import java.util.*;

/**
 * Created by anh.dang on 4/7/2017.
 */
public class EvaluationByChapterService {
    public EvaluationByChappter fetchAllForEvaluationForChapter(List<ChapterEvaluation> chapterEvaluationList, Evaluation data){

        EvaluationByChappter evaluationByChappter = new EvaluationByChappter();

        List<ChapterForEvaluationByChapter> chapterIntegerMap = new ArrayList<>();

        List<ResultXLS> resultXLSList = data.getResultArray();

        List<Chapter> chapterList = data.getChapterList();
        List<Chapter> chapterListNotHave = new ArrayList<>();

        for (Chapter chapter: chapterList){
            boolean checkChapterHave = false;
            for (ChapterEvaluation chapterEvaluation : chapterEvaluationList){
                if (chapterEvaluation.getChapter().getId().equals(chapter.getId())){
                    checkChapterHave = true;
                    break;
                }
            }
            if (checkChapterHave == false){
                chapterListNotHave.add(chapter);
            }
        }

        evaluationByChappter.setChapterListNotHave(chapterListNotHave);

        for (ChapterEvaluation chapterEvaluation: chapterEvaluationList){
            List<TargetEvaluation> targetEvaluationList = chapterEvaluation.getTargetEvaluationList();
            int sumQuestionOfChapter = 0;
            for (TargetEvaluation targetEvaluation: targetEvaluationList){
                int sumLevel1 = targetEvaluation.getLevelEvaluationList().getLv1_Question_List().size();
                int sumLevel2 = targetEvaluation.getLevelEvaluationList().getLv2_Question_List().size();
                int sumLevel3 = targetEvaluation.getLevelEvaluationList().getLv3_Question_List().size();
                int sumLevel4 = targetEvaluation.getLevelEvaluationList().getLv4_Question_List().size();
                int sumLevel5 = targetEvaluation.getLevelEvaluationList().getLv5_Question_List().size();
                int sumLevel = sumLevel1 + sumLevel2 + sumLevel3 + sumLevel4 + sumLevel5;
                sumQuestionOfChapter = sumQuestionOfChapter + sumLevel;
            }
            ChapterForEvaluationByChapter chapterForEvaluationByChapter = new ChapterForEvaluationByChapter();
            chapterForEvaluationByChapter.setChapter(chapterEvaluation.getChapter());
            chapterForEvaluationByChapter.setNumberOfChapter(sumQuestionOfChapter);
            chapterIntegerMap.add(chapterForEvaluationByChapter);
        }

        evaluationByChappter.setChapterList(chapterIntegerMap);

        Map<Chapter, List<Integer>> questionInChapter = new HashMap<>();

        for (ChapterEvaluation chapterEvaluation: chapterEvaluationList){
            List<Integer> questionList = new ArrayList<>();
            List<TargetEvaluation> targetEvaluationList = chapterEvaluation.getTargetEvaluationList();
            for (TargetEvaluation targetEvaluation : targetEvaluationList){
                LevelEvaluation levelEvaluation = targetEvaluation.getLevelEvaluationList();
                List<Integer> Lv1_Question_List = levelEvaluation.getLv1_Question_List();
                for (int i=0; i< Lv1_Question_List.size(); i++){
                    questionList.add(Lv1_Question_List.get(i));
                }
                List<Integer> Lv2_Question_List = levelEvaluation.getLv2_Question_List();
                for (int i=0; i< Lv2_Question_List.size(); i++){
                    questionList.add(Lv2_Question_List.get(i));
                }
                List<Integer> Lv3_Question_List = levelEvaluation.getLv3_Question_List();
                for (int i=0; i< Lv3_Question_List.size(); i++){
                    questionList.add(Lv3_Question_List.get(i));
                }
                List<Integer> Lv4_Question_List = levelEvaluation.getLv4_Question_List();
                for (int i=0; i< Lv4_Question_List.size(); i++){
                    questionList.add(Lv4_Question_List.get(i));
                }
                List<Integer> Lv5_Question_List = levelEvaluation.getLv5_Question_List();
                for (int i=0; i< Lv5_Question_List.size(); i++){
                    questionList.add(Lv5_Question_List.get(i));
                }

            }
            int numberTrue = 0;
            int numberFalse = 0;
            int numberNotChoose = 0;
            List<Integer> resultIntegerList = new ArrayList<>();

            for (int j=0; j<questionList.size(); j++){
                for (ResultXLS resultXLS: resultXLSList){
                    List<ResultQuestionExcel> resultQuestionExcelList = resultXLS.getResultQuestionExcelList();
                    for (ResultQuestionExcel resultQuestionExcel: resultQuestionExcelList){
                        int numberQuestion = resultQuestionExcel.resultNumberQuestionExcel.QuestionNumber;
                        if (questionList.get(j) == numberQuestion){
                            if (resultQuestionExcel.resultAnwerExcel.answer != null){
                                if (resultQuestionExcel.resultAnwerExcel.checkAnswer == true){
                                    numberTrue = numberTrue + 1;
                                }
                                else if (resultQuestionExcel.resultAnwerExcel.checkAnswer == false){
                                    numberFalse = numberFalse + 1;
                                }
                            }
                            else {
                                numberNotChoose = numberNotChoose + 1;
                            }
                        }

                    }
                }
            }
            resultIntegerList.add(numberTrue);
            resultIntegerList.add(numberFalse);
            resultIntegerList.add(numberNotChoose);
            questionInChapter.put(chapterEvaluation.getChapter(), resultIntegerList);
        }


        List<Float> getMinMaxList = new ArrayList<>();
        for ( Chapter key : questionInChapter.keySet() ) {
            int sum = questionInChapter.get(key).get(0) + questionInChapter.get(key).get(1) + questionInChapter.get(key).get(2);
            float truePercent = (float)((questionInChapter.get(key).get(0))/(float)sum) * 100;
            getMinMaxList.add(truePercent);
        }
        float maxTrue = Collections.max(getMinMaxList);
        float minTrue = Collections.min(getMinMaxList);

        List<ChapterEasyDiff> chapterEasyList = new ArrayList<>();
        List<ChapterEasyDiff> chapterDiffList = new ArrayList<>();

        for ( Chapter key : questionInChapter.keySet() ) {
            int sum = questionInChapter.get(key).get(0) + questionInChapter.get(key).get(1) + questionInChapter.get(key).get(2);
            float truePercent = (float)((questionInChapter.get(key).get(0))/(float)sum) * 100;
            if (truePercent == maxTrue){
                ChapterEasyDiff chapterEasyDiff = new ChapterEasyDiff();
                chapterEasyDiff.setChapterElement(key);
                chapterEasyDiff.setListTrueFalse(questionInChapter.get(key));
                chapterEasyDiff.setPercentTrue(truePercent);
                chapterEasyList.add(chapterEasyDiff);
            }
            if (truePercent == minTrue){
                ChapterEasyDiff chapterEasyDiff = new ChapterEasyDiff();
                chapterEasyDiff.setChapterElement(key);
                chapterEasyDiff.setListTrueFalse(questionInChapter.get(key));
                chapterEasyDiff.setPercentTrue(truePercent);
                chapterDiffList.add(chapterEasyDiff);
            }
        }



        evaluationByChappter.setChapterEasyMost(chapterEasyList);
        evaluationByChappter.setChapterDifficult(chapterDiffList);


        return evaluationByChappter;
    }

    public EvaluationByOneObjectModel getEvaluationByOneChapter(List<ChapterEvaluation> evaluationList, Long chapterId, Evaluation evaluation){
        EvaluationByOneObjectModel evaluationByOneObjectModel = new EvaluationByOneObjectModel();
        List<ResultXLS> resultXLSList = evaluation.getResultArray();
        List<Integer> questionList = new ArrayList<>();
        for (ChapterEvaluation chapterEvaluation: evaluationList){
            if (chapterEvaluation.getChapter().getId() == chapterId){
                List<TargetEvaluation> targetEvaluationList = chapterEvaluation.getTargetEvaluationList();
                for (TargetEvaluation targetEvaluation : targetEvaluationList) {
                    LevelEvaluation levelEvaluation = targetEvaluation.getLevelEvaluationList();
                    List<Integer> Lv1_Question_List = levelEvaluation.getLv1_Question_List();
                    for (int i = 0; i < Lv1_Question_List.size(); i++) {
                        questionList.add(Lv1_Question_List.get(i));
                    }
                    List<Integer> Lv2_Question_List = levelEvaluation.getLv2_Question_List();
                    for (int i = 0; i < Lv2_Question_List.size(); i++) {
                        questionList.add(Lv2_Question_List.get(i));
                    }
                    List<Integer> Lv3_Question_List = levelEvaluation.getLv3_Question_List();
                    for (int i = 0; i < Lv3_Question_List.size(); i++) {
                        questionList.add(Lv3_Question_List.get(i));
                    }
                    List<Integer> Lv4_Question_List = levelEvaluation.getLv4_Question_List();
                    for (int i = 0; i < Lv4_Question_List.size(); i++) {
                        questionList.add(Lv4_Question_List.get(i));
                    }
                    List<Integer> Lv5_Question_List = levelEvaluation.getLv5_Question_List();
                    for (int i = 0; i < Lv5_Question_List.size(); i++) {
                        questionList.add(Lv5_Question_List.get(i));
                    }

                }
            }
        }

        EvaluationBaseModelService evaluationBaseModelService = new EvaluationBaseModelService();
        evaluationBaseModelService.fetchAllTrueFalseNotChooseOfQuestion(questionList, evaluationByOneObjectModel, resultXLSList);
        evaluationBaseModelService.fetchAllAnswerByQuestion(questionList, evaluationByOneObjectModel, resultXLSList);

        return evaluationByOneObjectModel;
    }
}
