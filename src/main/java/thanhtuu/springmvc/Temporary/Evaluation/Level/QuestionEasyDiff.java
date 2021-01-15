package thanhtuu.springmvc.Temporary.Evaluation.Level;

import java.util.List;

/**
 * Created by anh.dang on 04/22/2017.
 */
public class QuestionEasyDiff {
    private Integer Level;
    private Integer numberQuestion;
    private Float trueValue;

    public Integer getLevel() {
        return Level;
    }

    public void setLevel(Integer level) {
        Level = level;
    }

    public Integer getNumberQuestion() {
        return numberQuestion;
    }

    public void setNumberQuestion(Integer numberQuestion) {
        this.numberQuestion = numberQuestion;
    }

    public Float getTrueValue() {
        return trueValue;
    }

    public void setTrueValue(Float trueValue) {
        this.trueValue = trueValue;
    }
}
