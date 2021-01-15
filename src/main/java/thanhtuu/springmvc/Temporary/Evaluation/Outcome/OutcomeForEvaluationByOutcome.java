package thanhtuu.springmvc.Temporary.Evaluation.Outcome;

import thanhtuu.springmvc.Domain.Target;

/**
 * Created by anh.dang on 4/9/2017.
 */
public class OutcomeForEvaluationByOutcome {
    private Target target;
    private Integer numberOfQuestion;

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public Integer getNumberOfQuestion() {
        return numberOfQuestion;
    }

    public void setNumberOfQuestion(Integer numberOfQuestion) {
        this.numberOfQuestion = numberOfQuestion;
    }
}
