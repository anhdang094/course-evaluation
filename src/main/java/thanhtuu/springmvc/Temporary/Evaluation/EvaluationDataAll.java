package thanhtuu.springmvc.Temporary.Evaluation;

/**
 * Created by anh.dang on 3/22/2017.
 */
public class EvaluationDataAll {

    private EvaluationByChappter evaluationByChappter;
    private EvaluationByDispersal evaluationByDispersal;
    private EvaluationByLevel evaluationByLevel;
    private EvaluationByOutcomes evaluationByOutcomes;

    public EvaluationByChappter getEvaluationByChappter() {
        return evaluationByChappter;
    }

    public void setEvaluationByChappter(EvaluationByChappter evaluationByChappter) {
        this.evaluationByChappter = evaluationByChappter;
    }

    public EvaluationByDispersal getEvaluationByDispersal() {
        return evaluationByDispersal;
    }

    public void setEvaluationByDispersal(EvaluationByDispersal evaluationByDispersal) {
        this.evaluationByDispersal = evaluationByDispersal;
    }

    public EvaluationByLevel getEvaluationByLevel() {
        return evaluationByLevel;
    }

    public void setEvaluationByLevel(EvaluationByLevel evaluationByLevel) {
        this.evaluationByLevel = evaluationByLevel;
    }

    public EvaluationByOutcomes getEvaluationByOutcomes() {
        return evaluationByOutcomes;
    }

    public void setEvaluationByOutcomes(EvaluationByOutcomes evaluationByOutcomes) {
        this.evaluationByOutcomes = evaluationByOutcomes;
    }
}
