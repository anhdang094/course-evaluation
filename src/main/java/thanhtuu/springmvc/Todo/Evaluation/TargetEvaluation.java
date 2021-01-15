package thanhtuu.springmvc.Todo.Evaluation;

import thanhtuu.springmvc.Domain.Target;

import java.util.List;

/**
 * Created by anh.dang on 3/25/2017.
 */
public class TargetEvaluation {

    private Target target;

    private LevelEvaluation levelEvaluationList;

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public LevelEvaluation getLevelEvaluationList() {
        return levelEvaluationList;
    }

    public void setLevelEvaluationList(LevelEvaluation levelEvaluationList) {
        this.levelEvaluationList = levelEvaluationList;
    }
}
