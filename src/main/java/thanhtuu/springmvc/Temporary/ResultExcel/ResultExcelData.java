package thanhtuu.springmvc.Temporary.ResultExcel;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

/**
 * Created by anh.dang on 2/11/2017.
 */
public class ResultExcelData {
    private Integer numberQuestion;
    private List<JsonNode> resultArray;

    public Integer getNumberQuestion() {
        return numberQuestion;
    }

    public List<JsonNode> getResultArray() {
        return resultArray;
    }

    public void setNumberQuestion(Integer numberQuestion) {
        this.numberQuestion = numberQuestion;
    }

    public void setResultArray(List<JsonNode> resultArray) {
        this.resultArray = resultArray;
    }
}
