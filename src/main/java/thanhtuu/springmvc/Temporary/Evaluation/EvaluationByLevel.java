package thanhtuu.springmvc.Temporary.Evaluation;

import thanhtuu.springmvc.Service.evaluation.EvaluationBaseModelService;
import thanhtuu.springmvc.Temporary.Evaluation.Level.QuestionEasyDiff;
import thanhtuu.springmvc.Temporary.Evaluation.Level.QuestionLevelChange;

import java.util.List;

/**
 * Created by anh.dang on 3/19/2017.
 */
public class EvaluationByLevel {
    private List<QuestionEasyDiff> questionEasyDiffListMin;
    private List<QuestionEasyDiff> questionEasyDiffListMax;
    private List<QuestionEasyDiff> questionEasyDiffListAll;
    private List<QuestionLevelChange> questionLevelChangeList;
    private EvaluationByOneObjectModel trueFalseOfLevel_1;
    private EvaluationByOneObjectModel trueFalseOfLevel_2;
    private EvaluationByOneObjectModel trueFalseOfLevel_3;
    private EvaluationByOneObjectModel trueFalseOfLevel_4;
    private EvaluationByOneObjectModel trueFalseOfLevel_5;

    public List<QuestionEasyDiff> getQuestionEasyDiffListMin() {
        return questionEasyDiffListMin;
    }

    public void setQuestionEasyDiffListMin(List<QuestionEasyDiff> questionEasyDiffListMin) {
        this.questionEasyDiffListMin = questionEasyDiffListMin;
    }

    public List<QuestionEasyDiff> getQuestionEasyDiffListMax() {
        return questionEasyDiffListMax;
    }

    public void setQuestionEasyDiffListMax(List<QuestionEasyDiff> questionEasyDiffListMax) {
        this.questionEasyDiffListMax = questionEasyDiffListMax;
    }

    public List<QuestionEasyDiff> getQuestionEasyDiffListAll() {
        return questionEasyDiffListAll;
    }

    public void setQuestionEasyDiffListAll(List<QuestionEasyDiff> questionEasyDiffListAll) {
        this.questionEasyDiffListAll = questionEasyDiffListAll;
    }

    public List<QuestionLevelChange> getQuestionLevelChangeList() {
        return questionLevelChangeList;
    }

    public void setQuestionLevelChangeList(List<QuestionLevelChange> questionLevelChangeList) {
        this.questionLevelChangeList = questionLevelChangeList;
    }

    public EvaluationByOneObjectModel getTrueFalseOfLevel_1() {
        return trueFalseOfLevel_1;
    }

    public void setTrueFalseOfLevel_1(EvaluationByOneObjectModel trueFalseOfLevel_1) {
        this.trueFalseOfLevel_1 = trueFalseOfLevel_1;
    }

    public EvaluationByOneObjectModel getTrueFalseOfLevel_2() {
        return trueFalseOfLevel_2;
    }

    public void setTrueFalseOfLevel_2(EvaluationByOneObjectModel trueFalseOfLevel_2) {
        this.trueFalseOfLevel_2 = trueFalseOfLevel_2;
    }

    public EvaluationByOneObjectModel getTrueFalseOfLevel_3() {
        return trueFalseOfLevel_3;
    }

    public void setTrueFalseOfLevel_3(EvaluationByOneObjectModel trueFalseOfLevel_3) {
        this.trueFalseOfLevel_3 = trueFalseOfLevel_3;
    }

    public EvaluationByOneObjectModel getTrueFalseOfLevel_4() {
        return trueFalseOfLevel_4;
    }

    public void setTrueFalseOfLevel_4(EvaluationByOneObjectModel trueFalseOfLevel_4) {
        this.trueFalseOfLevel_4 = trueFalseOfLevel_4;
    }

    public EvaluationByOneObjectModel getTrueFalseOfLevel_5() {
        return trueFalseOfLevel_5;
    }

    public void setTrueFalseOfLevel_5(EvaluationByOneObjectModel trueFalseOfLevel_5) {
        this.trueFalseOfLevel_5 = trueFalseOfLevel_5;
    }
}
