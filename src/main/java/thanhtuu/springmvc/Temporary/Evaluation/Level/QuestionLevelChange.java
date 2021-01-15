package thanhtuu.springmvc.Temporary.Evaluation.Level;

import java.util.List;

/**
 * Created by anh.dang on 04/22/2017.
 */
public class QuestionLevelChange {
    private List<Integer> numberQuestion;
    private Integer levelBase;
    private Integer levelTarget;
    private Float averageLevelTarget;
    private Float percentOfQuestion;

    public Float getPercentOfQuestion() {
        return percentOfQuestion;
    }

    public void setPercentOfQuestion(Float percentOfQuestion) {
        this.percentOfQuestion = percentOfQuestion;
    }

    public List<Integer> getNumberQuestion() {
        return numberQuestion;
    }

    public void setNumberQuestion(List<Integer> numberQuestion) {
        this.numberQuestion = numberQuestion;
    }

    public Integer getLevelBase() {
        return levelBase;
    }

    public void setLevelBase(Integer levelBase) {
        this.levelBase = levelBase;
    }

    public Integer getLevelTarget() {
        return levelTarget;
    }

    public void setLevelTarget(Integer levelTarget) {
        this.levelTarget = levelTarget;
    }

    public Float getAverageLevelTarget() {
        return averageLevelTarget;
    }

    public void setAverageLevelTarget(Float averageLevelTarget) {
        this.averageLevelTarget = averageLevelTarget;
    }
}
