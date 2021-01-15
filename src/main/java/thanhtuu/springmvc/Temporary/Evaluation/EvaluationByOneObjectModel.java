package thanhtuu.springmvc.Temporary.Evaluation;

import thanhtuu.springmvc.Temporary.Evaluation.Base.NumberPercent;

import java.util.List;

/**
 * Created by anh.dang on 4/7/2017.
 */
public class EvaluationByOneObjectModel {
    private List<Integer> listQuestion;
    private List<NumberPercent> numberPercentListByTrueFalse;
    private List<NumberPercent> numberPercentListByAnswer;

    public List<Integer> getListQuestion() {
        return listQuestion;
    }

    public void setListQuestion(List<Integer> listQuestion) {
        this.listQuestion = listQuestion;
    }

    public List<NumberPercent> getNumberPercentListByTrueFalse() {
        return numberPercentListByTrueFalse;
    }

    public void setNumberPercentListByTrueFalse(List<NumberPercent> numberPercentListByTrueFalse) {
        this.numberPercentListByTrueFalse = numberPercentListByTrueFalse;
    }

    public List<NumberPercent> getNumberPercentListByAnswer() {
        return numberPercentListByAnswer;
    }

    public void setNumberPercentListByAnswer(List<NumberPercent> numberPercentListByAnswer) {
        this.numberPercentListByAnswer = numberPercentListByAnswer;
    }
}
