package thanhtuu.springmvc.Temporary.Evaluation;

import thanhtuu.springmvc.Constains.Answer;

import java.util.List;
import java.util.Map;

/**
 * Created by anh.dang on 3/22/2017.
 */
public class EvaluationByOneChapter {

    private List<Integer> questionListOfChapter;
    private Map<Integer, Integer> percentTrueOfAllQuestionInChapter;
    private List<Map<Answer, Map<Integer, Integer>>> answerABCDOfQuestion;

    public List<Integer> getQuestionListOfChapter() {
        return questionListOfChapter;
    }

    public void setQuestionListOfChapter(List<Integer> questionListOfChapter) {
        this.questionListOfChapter = questionListOfChapter;
    }

    public Map<Integer, Integer> getPercentTrueOfAllQuestionInChapter() {
        return percentTrueOfAllQuestionInChapter;
    }

    public void setPercentTrueOfAllQuestionInChapter(Map<Integer, Integer> percentTrueOfAllQuestionInChapter) {
        this.percentTrueOfAllQuestionInChapter = percentTrueOfAllQuestionInChapter;
    }

    public List<Map<Answer, Map<Integer, Integer>>> getAnswerABCDOfQuestion() {
        return answerABCDOfQuestion;
    }

    public void setAnswerABCDOfQuestion(List<Map<Answer, Map<Integer, Integer>>> answerABCDOfQuestion) {
        this.answerABCDOfQuestion = answerABCDOfQuestion;
    }
}
