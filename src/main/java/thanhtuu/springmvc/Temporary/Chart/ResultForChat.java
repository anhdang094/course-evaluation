package thanhtuu.springmvc.Temporary.Chart;

import thanhtuu.springmvc.Todo.ResultXLS;

import java.util.List;

/**
 * Created by anh.dang on 2/9/2017.
 */
public class ResultForChat {
    private String chart;
    private String examCode;
    private List<ResultXLS> resultXLS;
    private Integer numberQuestion;

    public String getChart() {
        return chart;
    }

    public List<ResultXLS> getResultXLS() {
        return resultXLS;
    }

    public String getExamCode() {
        return examCode;
    }

    public void setChart(String chart) {
        this.chart = chart;
    }

    public void setResultXLS(List<ResultXLS> resultXLS) {
        this.resultXLS = resultXLS;
    }

    public void setExamCode(String examCode) {
        this.examCode = examCode;
    }

    public Integer getNumberQuestion() {
        return numberQuestion;
    }

    public void setNumberQuestion(Integer numberQuestion) {
        this.numberQuestion = numberQuestion;
    }
}
