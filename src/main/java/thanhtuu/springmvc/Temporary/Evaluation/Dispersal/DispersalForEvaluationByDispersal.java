package thanhtuu.springmvc.Temporary.Evaluation.Dispersal;

import thanhtuu.springmvc.Temporary.Evaluation.Base.NumberPercent;

import java.util.List;

/**
 * Created by anh.dang on 4/15/2017.
 */
public class DispersalForEvaluationByDispersal {
    List<Integer> questionDisperSal;
    List<NumberPercent> numberPercentList;

    public List<Integer> getQuestionDisperSal() {
        return questionDisperSal;
    }

    public void setQuestionDisperSal(List<Integer> questionDisperSal) {
        this.questionDisperSal = questionDisperSal;
    }

    public List<NumberPercent> getNumberPercentList() {
        return numberPercentList;
    }

    public void setNumberPercentList(List<NumberPercent> numberPercentList) {
        this.numberPercentList = numberPercentList;
    }
}
