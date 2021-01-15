package thanhtuu.springmvc.Temporary.Evaluation.Outcome;

import thanhtuu.springmvc.Domain.Target;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anh.dang on 4/9/2017.
 */
public class OutcomeEasyDiff {
    private Target target;
    private List<Integer> listTrueFalse = new ArrayList<>();
    private Float percentTrue;

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public List<Integer> getListTrueFalse() {
        return listTrueFalse;
    }

    public void setListTrueFalse(List<Integer> listTrueFalse) {
        this.listTrueFalse = listTrueFalse;
    }

    public Float getPercentTrue() {
        return percentTrue;
    }

    public void setPercentTrue(Float percentTrue) {
        this.percentTrue = percentTrue;
    }
}
