package thanhtuu.springmvc.Temporary.Evaluation;

import thanhtuu.springmvc.Temporary.Evaluation.Base.NumberPercent;
import thanhtuu.springmvc.Temporary.Evaluation.Dispersal.DispersalForEvaluationByDispersal;

import java.util.List;

/**
 * Created by anh.dang on 3/19/2017.
 */
public class EvaluationByDispersal {
    DispersalForEvaluationByDispersal disperssalLargestDispersal;
    DispersalForEvaluationByDispersal disperssalSmallestDispersal;
    DispersalForEvaluationByDispersal dispersalForEvaluationByDispersalLargerList;
    DispersalForEvaluationByDispersal dispersalForEvaluationByDispersalSmallList;

    public DispersalForEvaluationByDispersal getDisperssalLargestDispersal() {
        return disperssalLargestDispersal;
    }

    public void setDisperssalLargestDispersal(DispersalForEvaluationByDispersal disperssalLargestDispersal) {
        this.disperssalLargestDispersal = disperssalLargestDispersal;
    }

    public DispersalForEvaluationByDispersal getDisperssalSmallestDispersal() {
        return disperssalSmallestDispersal;
    }

    public void setDisperssalSmallestDispersal(DispersalForEvaluationByDispersal disperssalSmallestDispersal) {
        this.disperssalSmallestDispersal = disperssalSmallestDispersal;
    }

    public DispersalForEvaluationByDispersal getDispersalForEvaluationByDispersalLargerList() {
        return dispersalForEvaluationByDispersalLargerList;
    }

    public void setDispersalForEvaluationByDispersalLargerList(DispersalForEvaluationByDispersal dispersalForEvaluationByDispersalLargerList) {
        this.dispersalForEvaluationByDispersalLargerList = dispersalForEvaluationByDispersalLargerList;
    }

    public DispersalForEvaluationByDispersal getDispersalForEvaluationByDispersalSmallList() {
        return dispersalForEvaluationByDispersalSmallList;
    }

    public void setDispersalForEvaluationByDispersalSmallList(DispersalForEvaluationByDispersal dispersalForEvaluationByDispersalSmallList) {
        this.dispersalForEvaluationByDispersalSmallList = dispersalForEvaluationByDispersalSmallList;
    }
}
