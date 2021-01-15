package thanhtuu.springmvc.Temporary.Evaluation;

import thanhtuu.springmvc.Temporary.Evaluation.Outcome.OutcomeEasyDiff;
import thanhtuu.springmvc.Temporary.Evaluation.Outcome.OutcomeForEvaluationByOutcome;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anh.dang on 3/19/2017.
 */
public class EvaluationByOutcomes {
    private List<OutcomeForEvaluationByOutcome> outcomeList = new ArrayList<>();
    private List<OutcomeEasyDiff> outcomeEasyList = new ArrayList<>();
    private List<OutcomeEasyDiff> outcomeDiffList = new ArrayList<>();

    public List<OutcomeForEvaluationByOutcome> getOutcomeList() {
        return outcomeList;
    }

    public void setOutcomeList(List<OutcomeForEvaluationByOutcome> outcomeList) {
        this.outcomeList = outcomeList;
    }

    public List<OutcomeEasyDiff> getOutcomeEasyList() {
        return outcomeEasyList;
    }

    public void setOutcomeEasyList(List<OutcomeEasyDiff> outcomeEasyList) {
        this.outcomeEasyList = outcomeEasyList;
    }

    public List<OutcomeEasyDiff> getOutcomeDiffList() {
        return outcomeDiffList;
    }

    public void setOutcomeDiffList(List<OutcomeEasyDiff> outcomeDiffList) {
        this.outcomeDiffList = outcomeDiffList;
    }
}
