package thanhtuu.springmvc.Service.evaluation;

import thanhtuu.springmvc.Constains.Answer;
import thanhtuu.springmvc.Temporary.Evaluation.Base.NumberPercent;
import thanhtuu.springmvc.Temporary.Evaluation.EvaluationByOneObjectModel;
import thanhtuu.springmvc.Temporary.ResultExcel.ResultQuestionExcel;
import thanhtuu.springmvc.Todo.ResultXLS;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anh.dang on 4/9/2017.
 */
public class EvaluationBaseModelService {

    public void fetchAllTrueFalseNotChooseOfQuestion(List<Integer> questionList, EvaluationByOneObjectModel evaluationByOneObjectModel, List<ResultXLS> resultXLSList){
        int numberTrue = 0;
        int numberFalse = 0;
        int numberNotChoose = 0;


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
                            numberNotChoose ++;
                        }
                    }

                }
            }
        }
        List<NumberPercent> numberPercentTrueFalseList = new ArrayList<>();
        NumberPercent numberPercentTrue = new NumberPercent();
        numberPercentTrue.setNumber(numberTrue);
        numberPercentTrue.setPercent((float) numberTrue/(numberTrue + numberFalse + numberNotChoose) * 100);
        numberPercentTrueFalseList.add(numberPercentTrue);

        NumberPercent numberPercentFalse = new NumberPercent();
        numberPercentFalse.setNumber(numberFalse);
        numberPercentFalse.setPercent((float) numberFalse/(numberTrue + numberFalse + numberNotChoose) * 100);
        numberPercentTrueFalseList.add(numberPercentFalse);

        NumberPercent numberPercentNotChoose = new NumberPercent();
        numberPercentNotChoose.setNumber(numberNotChoose);
        numberPercentNotChoose.setPercent((float) numberNotChoose/(numberTrue + numberFalse + numberNotChoose) * 100);
        numberPercentTrueFalseList.add(numberPercentNotChoose);

        evaluationByOneObjectModel.setNumberPercentListByTrueFalse(numberPercentTrueFalseList);
        evaluationByOneObjectModel.setListQuestion(questionList);
    }

    public void fetchAllAnswerByQuestion(List<Integer> questionList, EvaluationByOneObjectModel evaluationByOneObjectModel, List<ResultXLS> resultXLSList){
        List<NumberPercent> numberPercentList = new ArrayList<>();
        for (int j=0; j<questionList.size(); j++){
            int numberA = 0;
            int numberB = 0;
            int numberC = 0;
            int numberD = 0;
            int numberE = 0;
            int numberNotAnswer = 0;
            for (ResultXLS resultXLS: resultXLSList){
                List<ResultQuestionExcel> resultQuestionExcelList = resultXLS.getResultQuestionExcelList();
                for (ResultQuestionExcel resultQuestionExcel: resultQuestionExcelList){
                    int numberQuestion = resultQuestionExcel.resultNumberQuestionExcel.QuestionNumber;
                    if (questionList.get(j) == numberQuestion){
                        if (resultQuestionExcel.resultAnwerExcel.answer != null){
                            if (resultQuestionExcel.resultAnwerExcel.answer.equals(Answer.A)){
                                numberA = numberA + 1;
                            }
                            else if (resultQuestionExcel.resultAnwerExcel.answer.equals(Answer.B)){
                                numberB = numberB + 1;
                            }
                            else if (resultQuestionExcel.resultAnwerExcel.answer.equals(Answer.C)){
                                numberC = numberC + 1;
                            }
                            else if (resultQuestionExcel.resultAnwerExcel.answer.equals(Answer.D)){
                                numberD = numberD + 1;
                            }
                            else if (resultQuestionExcel.resultAnwerExcel.answer.equals(Answer.E)){
                                numberE = numberE + 1;
                            }
                        }
                        else {
                            numberNotAnswer ++;
                        }
                    }

                }
            }

            NumberPercent numberPercentA = new NumberPercent();
            numberPercentA.setNumber(questionList.get(j));
            numberPercentA.setPercent((float)numberA/(numberA + numberB + numberC + numberD + numberE + numberNotAnswer) * 100);
            numberPercentList.add(numberPercentA);
            NumberPercent numberPercentB = new NumberPercent();
            numberPercentB.setNumber(questionList.get(j));
            numberPercentB.setPercent((float)numberB/(numberA + numberB + numberC + numberD + numberE + numberNotAnswer) * 100);
            numberPercentList.add(numberPercentB);
            NumberPercent numberPercentC = new NumberPercent();
            numberPercentC.setNumber(questionList.get(j));
            numberPercentC.setPercent((float)numberC/(numberA + numberB + numberC + numberD + numberE + numberNotAnswer) * 100);
            numberPercentList.add(numberPercentC);
            NumberPercent numberPercentD = new NumberPercent();
            numberPercentD.setNumber(questionList.get(j));
            numberPercentD.setPercent((float)numberD/(numberA + numberB + numberC + numberD + numberE + numberNotAnswer) * 100);
            numberPercentList.add(numberPercentD);
            NumberPercent numberPercentE = new NumberPercent();
            numberPercentE.setNumber(questionList.get(j));
            numberPercentE.setPercent((float)numberE/(numberA + numberB + numberC + numberD + numberE + numberNotAnswer) * 100);
            numberPercentList.add(numberPercentE);
            NumberPercent numberPercentNotAnswer = new NumberPercent();
            numberPercentNotAnswer.setNumber(questionList.get(j));
            numberPercentNotAnswer.setPercent((float)numberNotAnswer/(numberA + numberB + numberC + numberD + numberE + numberNotAnswer) * 100);
            numberPercentList.add(numberPercentNotAnswer);
        }
        evaluationByOneObjectModel.setNumberPercentListByAnswer(numberPercentList);
    }
}

