package thanhtuu.springmvc.Temporary.Chart;

import com.fasterxml.jackson.databind.JsonNode;
import thanhtuu.springmvc.Todo.ResultXLS;

import java.util.List;

/**
 * Created by anh.dang on 2/22/2017.
 */
public class DataForCreateChart {
    private String chartName;
    private String examCode;
    private String selectedChart;
    private Integer startQuestion;
    private Integer finishQuestion;
    private List<ColumnForCreateChart> columnList;
    private List<ResultXLS> resultXLS;
    private String typeStatistic;
    private List<JsonNode> resultArray;

    public String getChartName() {
        return chartName;
    }

    public void setChartName(String chartName) {
        this.chartName = chartName;
    }

    public String getExamCode() {
        return examCode;
    }

    public void setExamCode(String examCode) {
        this.examCode = examCode;
    }

    public String getSelectedChart() {
        return selectedChart;
    }

    public void setSelectedChart(String selectedChart) {
        this.selectedChart = selectedChart;
    }

    public Integer getStartQuestion() {
        return startQuestion;
    }

    public void setStartQuestion(Integer startQuestion) {
        this.startQuestion = startQuestion;
    }

    public Integer getFinishQuestion() {
        return finishQuestion;
    }

    public void setFinishQuestion(Integer finishQuestion) {
        this.finishQuestion = finishQuestion;
    }

    public List<ColumnForCreateChart> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<ColumnForCreateChart> columnList) {
        this.columnList = columnList;
    }

    public List<ResultXLS> getResultXLS() {
        return resultXLS;
    }

    public void setResultXLS(List<ResultXLS> resultXLS) {
        this.resultXLS = resultXLS;
    }

    public String getTypeStatistic() {
        return typeStatistic;
    }

    public void setTypeStatistic(String typeStatistic) {
        this.typeStatistic = typeStatistic;
    }

    public List<JsonNode> getResultArray() {
        return resultArray;
    }

    public void setResultArray(List<JsonNode> resultArray) {
        this.resultArray = resultArray;
    }
}
