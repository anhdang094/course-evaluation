package thanhtuu.springmvc.Service.evaluation;

import thanhtuu.springmvc.Domain.Target;
import thanhtuu.springmvc.Temporary.Evaluation.EvaluationByOneObjectModel;
import thanhtuu.springmvc.Temporary.Evaluation.EvaluationByOutcomes;
import thanhtuu.springmvc.Temporary.Evaluation.Outcome.OutcomeEasyDiff;
import thanhtuu.springmvc.Temporary.Evaluation.Outcome.OutcomeForEvaluationByOutcome;
import thanhtuu.springmvc.Temporary.ResultExcel.ResultQuestionExcel;
import thanhtuu.springmvc.Todo.Evaluation.ChapterEvaluation;
import thanhtuu.springmvc.Todo.Evaluation.Evaluation;
import thanhtuu.springmvc.Todo.Evaluation.LevelEvaluation;
import thanhtuu.springmvc.Todo.Evaluation.TargetEvaluation;
import thanhtuu.springmvc.Todo.ResultXLS;

import java.util.*;

/**
 * Created by anh.dang on 4/9/2017.
 */
public class EvaluationByOutcomeService {

    public EvaluationByOutcomes fetchAllForEvaluationForOutcome(List<ChapterEvaluation> chapterEvaluationList, Evaluation data) {
        EvaluationByOutcomes evaluationByOutcomes = new EvaluationByOutcomes();
        List<OutcomeForEvaluationByOutcome> outcomeList = new ArrayList<>();
        List<ResultXLS> resultXLSList = data.getResultArray();
        for (ChapterEvaluation chapterEvaluation : chapterEvaluationList) {
            List<TargetEvaluation> targetEvaluationList = chapterEvaluation.getTargetEvaluationList();
            for (TargetEvaluation targetEvaluation : targetEvaluationList) {
                Target target = targetEvaluation.getTarget();
                OutcomeForEvaluationByOutcome outcomeForEvaluationByOutcome = new OutcomeForEvaluationByOutcome();
                outcomeForEvaluationByOutcome.setTarget(target);
                outcomeList.add(outcomeForEvaluationByOutcome);
            }
        }
        for (OutcomeForEvaluationByOutcome outcomeForEvaluationByOutcome : outcomeList) {
            int numberOfQuestion = 0;
            for (ChapterEvaluation chapterEvaluation : chapterEvaluationList) {
                List<TargetEvaluation> targetEvaluationList = chapterEvaluation.getTargetEvaluationList();
                for (TargetEvaluation targetEvaluation : targetEvaluationList) {
                    Target target = targetEvaluation.getTarget();
                    if (target.getId().equals(outcomeForEvaluationByOutcome.getTarget().getId())) {
                        LevelEvaluation levelEvaluationList = targetEvaluation.getLevelEvaluationList();
                        List<Integer> Lv1_Question_List = levelEvaluationList.getLv1_Question_List();
                        List<Integer> Lv2_Question_List = levelEvaluationList.getLv2_Question_List();
                        List<Integer> Lv3_Question_List = levelEvaluationList.getLv3_Question_List();
                        List<Integer> Lv4_Question_List = levelEvaluationList.getLv4_Question_List();
                        List<Integer> Lv5_Question_List = levelEvaluationList.getLv5_Question_List();
                        numberOfQuestion = Lv1_Question_List.size() + Lv2_Question_List.size() + Lv3_Question_List.size()
                                + Lv4_Question_List.size() + Lv5_Question_List.size();
                    }
                }
            }
            outcomeForEvaluationByOutcome.setNumberOfQuestion(numberOfQuestion);
        }
        evaluationByOutcomes.setOutcomeList(outcomeList);

        Map<Target, List<Integer>> questionInOutcome = new HashMap<>();
        for (OutcomeForEvaluationByOutcome outcomeForEvaluationByOutcome : outcomeList) {
            List<Integer> questionList = new ArrayList<>();
            for (ChapterEvaluation chapterEvaluation : chapterEvaluationList) {
                List<TargetEvaluation> targetEvaluationList = chapterEvaluation.getTargetEvaluationList();
                for (TargetEvaluation targetEvaluation : targetEvaluationList) {
                    Target target = targetEvaluation.getTarget();
                    if (target.getId().equals(outcomeForEvaluationByOutcome.getTarget().getId())) {
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
            int numberTrue = 0;
            int numberFalse = 0;
            int numberNotChoose = 0;
            List<Integer> resultIntegerList = new ArrayList<>();

            for (int j = 0; j < questionList.size(); j++) {
                for (ResultXLS resultXLS : resultXLSList) {
                    List<ResultQuestionExcel> resultQuestionExcelList = resultXLS.getResultQuestionExcelList();
                    for (ResultQuestionExcel resultQuestionExcel : resultQuestionExcelList) {
                        int numberQuestion = resultQuestionExcel.resultNumberQuestionExcel.QuestionNumber;
                        if (questionList.get(j) == numberQuestion) {
                            if (resultQuestionExcel.resultAnwerExcel.answer != null) {
                                if (resultQuestionExcel.resultAnwerExcel.checkAnswer == true) {
                                    numberTrue = numberTrue + 1;
                                } else if (resultQuestionExcel.resultAnwerExcel.checkAnswer == false) {
                                    numberFalse = numberFalse + 1;
                                }
                            } else {
                                numberNotChoose = numberNotChoose + 1;
                            }
                        }

                    }
                }
            }
            resultIntegerList.add(numberTrue);
            resultIntegerList.add(numberFalse);
            resultIntegerList.add(numberNotChoose);
            questionInOutcome.put(outcomeForEvaluationByOutcome.getTarget(), resultIntegerList);

        }

        List<Float> getMinMaxList = new ArrayList<>();
        for (Target key : questionInOutcome.keySet()) {
            int sum = questionInOutcome.get(key).get(0) + questionInOutcome.get(key).get(1) + questionInOutcome.get(key).get(2);
            float truePercent = (float) ((questionInOutcome.get(key).get(0)) / (float) sum) * 100;
            getMinMaxList.add(truePercent);
        }
        float maxTrue = Collections.max(getMinMaxList);
        float minTrue = Collections.min(getMinMaxList);

        List<OutcomeEasyDiff> outcomeEasyList = new ArrayList<>();
        List<OutcomeEasyDiff> outcomeDiffList = new ArrayList<>();

        for (Target key : questionInOutcome.keySet()) {
            int sum = questionInOutcome.get(key).get(0) + questionInOutcome.get(key).get(1) + questionInOutcome.get(key).get(2);
            float truePercent = (float) ((questionInOutcome.get(key).get(0)) / (float) sum) * 100;
            if (truePercent == maxTrue) {
                OutcomeEasyDiff outcomeEasy = new OutcomeEasyDiff();
                outcomeEasy.setTarget(key);
                outcomeEasy.setListTrueFalse(questionInOutcome.get(key));
                outcomeEasy.setPercentTrue(truePercent);
                outcomeEasyList.add(outcomeEasy);
            }
            if (truePercent == minTrue) {
                OutcomeEasyDiff outcomeDiff = new OutcomeEasyDiff();
                outcomeDiff.setTarget(key);
                outcomeDiff.setListTrueFalse(questionInOutcome.get(key));
                outcomeDiff.setPercentTrue(truePercent);
                outcomeDiffList.add(outcomeDiff);
            }
        }


        evaluationByOutcomes.setOutcomeEasyList(outcomeEasyList);
        evaluationByOutcomes.setOutcomeDiffList(outcomeDiffList);

        return evaluationByOutcomes;
    }

    public EvaluationByOneObjectModel getEvaluationByOneOutcome(List<ChapterEvaluation> evaluationList, Long chapterId, Evaluation evaluation) {
        EvaluationByOneObjectModel evaluationByOneObjectModel = new EvaluationByOneObjectModel();
        List<ResultXLS> resultXLSList = evaluation.getResultArray();
        List<Integer> questionList = new ArrayList<>();
        for (ChapterEvaluation chapterEvaluation : evaluationList) {
            List<TargetEvaluation> targetEvaluationList = chapterEvaluation.getTargetEvaluationList();
            for (TargetEvaluation targetEvaluation : targetEvaluationList) {
                if (targetEvaluation.getTarget().getId().equals(chapterId)){
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
