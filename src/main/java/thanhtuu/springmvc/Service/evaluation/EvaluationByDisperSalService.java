package thanhtuu.springmvc.Service.evaluation;

import thanhtuu.springmvc.Temporary.Evaluation.Base.NumberPercent;
import thanhtuu.springmvc.Temporary.Evaluation.Chapter.ChapterForEvaluationByChapter;
import thanhtuu.springmvc.Temporary.Evaluation.Dispersal.DispersalForEvaluationByDispersal;
import thanhtuu.springmvc.Temporary.Evaluation.EvaluationByChappter;
import thanhtuu.springmvc.Temporary.Evaluation.EvaluationByDispersal;
import thanhtuu.springmvc.Temporary.Evaluation.EvaluationByOneObjectModel;
import thanhtuu.springmvc.Temporary.ResultExcel.ResultQuestionExcel;
import thanhtuu.springmvc.Todo.Evaluation.ChapterEvaluation;
import thanhtuu.springmvc.Todo.Evaluation.Evaluation;
import thanhtuu.springmvc.Todo.Evaluation.LevelEvaluation;
import thanhtuu.springmvc.Todo.Evaluation.TargetEvaluation;
import thanhtuu.springmvc.Todo.ResultXLS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by anh.dang on 4/15/2017.
 */
public class EvaluationByDisperSalService {
    public EvaluationByDispersal fetchAllForEvaluationDispersal(List<ChapterEvaluation> chapterEvaluationList, Evaluation data) {
        EvaluationByDispersal evaluationByDispersal = new EvaluationByDispersal();

        List<ResultXLS> resultXLSList = data.getResultArray();
        List<Integer> questionList = new ArrayList<>();

        for (ChapterEvaluation chapterEvaluation : chapterEvaluationList) {
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
        EvaluationByOneObjectModel evaluationByOneObjectModel = new EvaluationByOneObjectModel();
        EvaluationBaseModelService evaluationBaseModelService = new EvaluationBaseModelService();
        List<NumberPercent> numberPercentListByAnswer = new ArrayList<>();
        for (Integer questionNumber : questionList) {
            List<Integer> questionListOne = new ArrayList<>();
            questionListOne.add(questionNumber);
            evaluationBaseModelService.fetchAllAnswerByQuestion(questionListOne, evaluationByOneObjectModel, resultXLSList);
            List<NumberPercent> numberPercentListByAnswerOne = evaluationByOneObjectModel.getNumberPercentListByAnswer();
            for (NumberPercent numberPercent : numberPercentListByAnswerOne) {
                numberPercentListByAnswer.add(numberPercent);
            }
        }

        List<NumberPercent> numberPercentList = new ArrayList<>();
        List<Float> getMinMaxSumPercent = new ArrayList<>();

        for (int i = 0; i < numberPercentListByAnswer.size(); i += 6) {
            float percentA = numberPercentListByAnswer.get(i).getPercent();
            float percentB = numberPercentListByAnswer.get(i + 1).getPercent();
            float percentC = numberPercentListByAnswer.get(i + 2).getPercent();
            float percentD = numberPercentListByAnswer.get(i + 3).getPercent();
            float percentE = numberPercentListByAnswer.get(i + 4).getPercent();
            float percentNotAnswer = numberPercentListByAnswer.get(i + 5).getPercent();
            List<Float> floatList = new ArrayList<>();
            floatList.add(percentA);
            floatList.add(percentB);
            floatList.add(percentC);
            floatList.add(percentD);
            floatList.add(percentE);
            floatList.add(percentNotAnswer);
            float maxPercent = Collections.max(floatList);
            float sumDispersal = (maxPercent - percentA) + (maxPercent - percentB) + (maxPercent - percentC) +
                    (maxPercent - percentD) + (maxPercent - percentE) + (maxPercent - percentNotAnswer);
            NumberPercent numberPercentTemp = new NumberPercent();
            numberPercentTemp.setNumber(numberPercentListByAnswer.get(i).getNumber());
            numberPercentTemp.setPercent(sumDispersal);
            numberPercentList.add(numberPercentTemp);
            getMinMaxSumPercent.add(sumDispersal);
        }

        float maxSumPercent = Collections.max(getMinMaxSumPercent);
        float minSumPercent = Collections.min(getMinMaxSumPercent);


        List<Float> getMinMaxSumPercentListTemplatesForMax = new ArrayList<>();
        List<Float> getMinMaxSumPercentListTemplatesForMin = new ArrayList<>();

        for (Float minMaxSumPercent : getMinMaxSumPercent) {
            getMinMaxSumPercentListTemplatesForMax.add(minMaxSumPercent);
            getMinMaxSumPercentListTemplatesForMin.add(minMaxSumPercent);
        }

        List<Float> getMaxSumPercentListTemplates = new ArrayList<>();
        int numberListMax = 0;


        List<Float> getMinSumPercentListTemplates = new ArrayList<>();
        int numberListMin = 0;

        if (getMinMaxSumPercent.size() >= 8) {
            while (numberListMax < 4) {
                float maxSumpercentTemplates = Collections.max(getMinMaxSumPercentListTemplatesForMax);
                getMaxSumPercentListTemplates.add(maxSumpercentTemplates);
                getMinMaxSumPercentListTemplatesForMax.remove(maxSumpercentTemplates);
                numberListMax++;
            }
            while (numberListMin < 4) {
                float minSumpercentTemplates = Collections.min(getMinMaxSumPercentListTemplatesForMin);
                getMinSumPercentListTemplates.add(minSumpercentTemplates);
                getMinMaxSumPercentListTemplatesForMin.remove(minSumpercentTemplates);
                numberListMin++;
            }
        } else {
            int numberAverage = getMinMaxSumPercent.size() / 2;
            while (numberListMax < numberAverage) {
                float maxSumpercentTemplates = Collections.max(getMinMaxSumPercentListTemplatesForMax);
                getMaxSumPercentListTemplates.add(maxSumpercentTemplates);
                getMinMaxSumPercentListTemplatesForMax.remove(maxSumpercentTemplates);
                numberListMax++;
            }
            while (numberListMin < numberAverage) {
                float minSumpercentTemplates = Collections.min(getMinMaxSumPercentListTemplatesForMin);
                getMinSumPercentListTemplates.add(minSumpercentTemplates);
                getMinMaxSumPercentListTemplatesForMin.remove(minSumpercentTemplates);
                numberListMin++;
            }
        }


        List<Integer> numberQuestionResultMinList = new ArrayList<>();
        List<NumberPercent> numberPercentResultMinList = new ArrayList<>();
        List<Integer> numberQuestionResultMaxList = new ArrayList<>();
        List<NumberPercent> numberPercentResultMaxList = new ArrayList<>();


        List<Integer> numberQuestionResultMinListTemplates = new ArrayList<>();
        List<NumberPercent> numberPercentResultMinListTemplates = new ArrayList<>();
        List<Integer> numberQuestionResultMaxListTemplates = new ArrayList<>();
        List<NumberPercent> numberPercentResultMaxListTemplates = new ArrayList<>();


        for (NumberPercent numberPercent : numberPercentList) {
            if (numberPercent.getPercent().equals(maxSumPercent)) {
                numberQuestionResultMaxList.add(numberPercent.getNumber());
            }
            if (numberPercent.getPercent().equals(minSumPercent)) {
                numberQuestionResultMinList.add(numberPercent.getNumber());
            }
        }


        for (NumberPercent numberPercent : numberPercentList) {
            for (Float getMinSumPercentTemplates : getMinSumPercentListTemplates) {
                if (numberPercent.getPercent().equals(getMinSumPercentTemplates)) {
                    numberQuestionResultMinListTemplates.add(numberPercent.getNumber());
                    break;
                }
            }
        }

        for (NumberPercent numberPercent : numberPercentList) {
            for (Float getMaxSumPercentTemplates : getMaxSumPercentListTemplates) {
                if (numberPercent.getPercent().equals(getMaxSumPercentTemplates)) {
                    numberQuestionResultMaxListTemplates.add(numberPercent.getNumber());
                    break;
                }
            }
        }


        for (int i = 0; i < numberPercentListByAnswer.size(); i += 6) {
            float percentA = numberPercentListByAnswer.get(i).getPercent();
            float percentB = numberPercentListByAnswer.get(i + 1).getPercent();
            float percentC = numberPercentListByAnswer.get(i + 2).getPercent();
            float percentD = numberPercentListByAnswer.get(i + 3).getPercent();
            float percentE = numberPercentListByAnswer.get(i + 4).getPercent();
            float percentNotAnswer = numberPercentListByAnswer.get(i + 5).getPercent();
            List<Float> floatList = new ArrayList<>();
            floatList.add(percentA);
            floatList.add(percentB);
            floatList.add(percentC);
            floatList.add(percentD);
            floatList.add(percentE);
            floatList.add(percentNotAnswer);
            float maxPercent = Collections.max(floatList);
            float sumDispersal = (maxPercent - percentA) + (maxPercent - percentB) + (maxPercent - percentC) +
                    (maxPercent - percentD) + (maxPercent - percentE) + (maxPercent - percentNotAnswer);
            if (sumDispersal == maxSumPercent) {

                NumberPercent numberPercentA = new NumberPercent();
                numberPercentA.setNumber(numberPercentListByAnswer.get(i).getNumber());
                numberPercentA.setPercent(percentA);
                numberPercentResultMaxList.add(numberPercentA);

                NumberPercent numberPercentB = new NumberPercent();
                numberPercentB.setNumber(numberPercentListByAnswer.get(i + 1).getNumber());
                numberPercentB.setPercent(percentB);
                numberPercentResultMaxList.add(numberPercentB);

                NumberPercent numberPercentC = new NumberPercent();
                numberPercentC.setNumber(numberPercentListByAnswer.get(i + 2).getNumber());
                numberPercentC.setPercent(percentC);
                numberPercentResultMaxList.add(numberPercentC);

                NumberPercent numberPercentD = new NumberPercent();
                numberPercentD.setNumber(numberPercentListByAnswer.get(i + 3).getNumber());
                numberPercentD.setPercent(percentD);
                numberPercentResultMaxList.add(numberPercentD);

                NumberPercent numberPercentE = new NumberPercent();
                numberPercentE.setNumber(numberPercentListByAnswer.get(i + 4).getNumber());
                numberPercentE.setPercent(percentE);
                numberPercentResultMaxList.add(numberPercentE);

                NumberPercent numberPercentNotAnswer = new NumberPercent();
                numberPercentNotAnswer.setNumber(numberPercentListByAnswer.get(i + 5).getNumber());
                numberPercentNotAnswer.setPercent(percentNotAnswer);
                numberPercentResultMaxList.add(numberPercentNotAnswer);
            }
            if (sumDispersal == minSumPercent) {
                NumberPercent numberPercentA = new NumberPercent();
                numberPercentA.setNumber(numberPercentListByAnswer.get(i).getNumber());
                numberPercentA.setPercent(percentA);
                numberPercentResultMinList.add(numberPercentA);

                NumberPercent numberPercentB = new NumberPercent();
                numberPercentB.setNumber(numberPercentListByAnswer.get(i + 1).getNumber());
                numberPercentB.setPercent(percentB);
                numberPercentResultMinList.add(numberPercentB);

                NumberPercent numberPercentC = new NumberPercent();
                numberPercentC.setNumber(numberPercentListByAnswer.get(i + 2).getNumber());
                numberPercentC.setPercent(percentC);
                numberPercentResultMinList.add(numberPercentC);

                NumberPercent numberPercentD = new NumberPercent();
                numberPercentD.setNumber(numberPercentListByAnswer.get(i + 3).getNumber());
                numberPercentD.setPercent(percentD);
                numberPercentResultMinList.add(numberPercentD);

                NumberPercent numberPercentE = new NumberPercent();
                numberPercentE.setNumber(numberPercentListByAnswer.get(i + 4).getNumber());
                numberPercentE.setPercent(percentE);
                numberPercentResultMinList.add(numberPercentE);

                NumberPercent numberPercentNotAnswer = new NumberPercent();
                numberPercentNotAnswer.setNumber(numberPercentListByAnswer.get(i + 5).getNumber());
                numberPercentNotAnswer.setPercent(percentNotAnswer);
                numberPercentResultMinList.add(numberPercentNotAnswer);
            }
            for (Float getMaxSumPercentTemplates : getMaxSumPercentListTemplates) {
                if (getMaxSumPercentTemplates.equals(sumDispersal)) {


                    NumberPercent numberPercentA = new NumberPercent();
                    numberPercentA.setNumber(numberPercentListByAnswer.get(i).getNumber());
                    numberPercentA.setPercent(percentA);
                    numberPercentResultMaxListTemplates.add(numberPercentA);

                    NumberPercent numberPercentB = new NumberPercent();
                    numberPercentB.setNumber(numberPercentListByAnswer.get(i + 1).getNumber());
                    numberPercentB.setPercent(percentB);
                    numberPercentResultMaxListTemplates.add(numberPercentB);

                    NumberPercent numberPercentC = new NumberPercent();
                    numberPercentC.setNumber(numberPercentListByAnswer.get(i + 2).getNumber());
                    numberPercentC.setPercent(percentC);
                    numberPercentResultMaxListTemplates.add(numberPercentC);

                    NumberPercent numberPercentD = new NumberPercent();
                    numberPercentD.setNumber(numberPercentListByAnswer.get(i + 3).getNumber());
                    numberPercentD.setPercent(percentD);
                    numberPercentResultMaxListTemplates.add(numberPercentD);

                    NumberPercent numberPercentE = new NumberPercent();
                    numberPercentE.setNumber(numberPercentListByAnswer.get(i + 4).getNumber());
                    numberPercentE.setPercent(percentE);
                    numberPercentResultMaxListTemplates.add(numberPercentE);

                    NumberPercent numberPercentNotAnswer = new NumberPercent();
                    numberPercentNotAnswer.setNumber(numberPercentListByAnswer.get(i + 5).getNumber());
                    numberPercentNotAnswer.setPercent(percentNotAnswer);
                    numberPercentResultMaxListTemplates.add(numberPercentNotAnswer);
                    break;
                }
            }
            for (Float getMinSumPercentTemplates : getMinSumPercentListTemplates) {

                if (getMinSumPercentTemplates.equals(sumDispersal)) {


                    NumberPercent numberPercentA = new NumberPercent();
                    numberPercentA.setNumber(numberPercentListByAnswer.get(i).getNumber());
                    numberPercentA.setPercent(percentA);
                    numberPercentResultMinListTemplates.add(numberPercentA);

                    NumberPercent numberPercentB = new NumberPercent();
                    numberPercentB.setNumber(numberPercentListByAnswer.get(i + 1).getNumber());
                    numberPercentB.setPercent(percentB);
                    numberPercentResultMinListTemplates.add(numberPercentB);

                    NumberPercent numberPercentC = new NumberPercent();
                    numberPercentC.setNumber(numberPercentListByAnswer.get(i + 2).getNumber());
                    numberPercentC.setPercent(percentC);
                    numberPercentResultMinListTemplates.add(numberPercentC);

                    NumberPercent numberPercentD = new NumberPercent();
                    numberPercentD.setNumber(numberPercentListByAnswer.get(i + 3).getNumber());
                    numberPercentD.setPercent(percentD);
                    numberPercentResultMinListTemplates.add(numberPercentD);

                    NumberPercent numberPercentE = new NumberPercent();
                    numberPercentE.setNumber(numberPercentListByAnswer.get(i + 4).getNumber());
                    numberPercentE.setPercent(percentE);
                    numberPercentResultMinListTemplates.add(numberPercentE);

                    NumberPercent numberPercentNotAnswer = new NumberPercent();
                    numberPercentNotAnswer.setNumber(numberPercentListByAnswer.get(i + 5).getNumber());
                    numberPercentNotAnswer.setPercent(percentNotAnswer);
                    numberPercentResultMinListTemplates.add(numberPercentNotAnswer);
                    break;
                }
            }
        }

        DispersalForEvaluationByDispersal dispersalForEvaluationByDispersalMin = new DispersalForEvaluationByDispersal();
        dispersalForEvaluationByDispersalMin.setQuestionDisperSal(numberQuestionResultMinList);
        dispersalForEvaluationByDispersalMin.setNumberPercentList(numberPercentResultMinList);
        evaluationByDispersal.setDisperssalSmallestDispersal(dispersalForEvaluationByDispersalMin);

        DispersalForEvaluationByDispersal dispersalForEvaluationByDispersalMax = new DispersalForEvaluationByDispersal();
        dispersalForEvaluationByDispersalMax.setQuestionDisperSal(numberQuestionResultMaxList);
        dispersalForEvaluationByDispersalMax.setNumberPercentList(numberPercentResultMaxList);
        evaluationByDispersal.setDisperssalLargestDispersal(dispersalForEvaluationByDispersalMax);

        DispersalForEvaluationByDispersal dispersalForEvaluationByDispersalMaxTemplates = new DispersalForEvaluationByDispersal();
        dispersalForEvaluationByDispersalMaxTemplates.setQuestionDisperSal(numberQuestionResultMaxListTemplates);
        dispersalForEvaluationByDispersalMaxTemplates.setNumberPercentList(numberPercentResultMaxListTemplates);
        evaluationByDispersal.setDispersalForEvaluationByDispersalLargerList(dispersalForEvaluationByDispersalMaxTemplates);

        DispersalForEvaluationByDispersal dispersalForEvaluationByDispersalMinTemplates = new DispersalForEvaluationByDispersal();
        dispersalForEvaluationByDispersalMinTemplates.setQuestionDisperSal(numberQuestionResultMinListTemplates);
        dispersalForEvaluationByDispersalMinTemplates.setNumberPercentList(numberPercentResultMinListTemplates);
        evaluationByDispersal.setDispersalForEvaluationByDispersalSmallList(dispersalForEvaluationByDispersalMinTemplates);

        return evaluationByDispersal;
    }
}
