package thanhtuu.springmvc.Service.dashboard;

import com.fasterxml.jackson.databind.JsonNode;
import thanhtuu.springmvc.Constains.Chart;
import thanhtuu.springmvc.Temporary.Chart.ColumnForCreateChart;
import thanhtuu.springmvc.Temporary.Chart.DataForCreateChart;
import thanhtuu.springmvc.Temporary.Chart.Score;
import thanhtuu.springmvc.Temporary.ResultExcel.ResultNumberQuestionExcel;
import thanhtuu.springmvc.Temporary.ResultExcel.ResultQuestionExcel;
import thanhtuu.springmvc.Todo.DataChart;
import thanhtuu.springmvc.Todo.ResultXLS;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by anh.dang on 2/9/2017.
 */
public class ChartService {

    public List<DataChart> fetchDataForColumnChart(List<ResultXLS> resultXLSList, Integer numberQuestion) {
        List<DataChart> dataChartList = new ArrayList<>();
        List<Score> ScoreArray = new ArrayList<>();

        for (int i = 0; i < resultXLSList.size(); i++) {
            float score_number = (float) Math.round((float) resultXLSList.get(i).getTGDIEM() / numberQuestion * 1000) / 100;
            boolean checkHaveScore = false;
            for (int j = 0; j < ScoreArray.size(); j++) {
                if (ScoreArray.get(j).getScore() == score_number) {
                    checkHaveScore = true;
                    int score_number_new = ScoreArray.get(j).getNumberOfScore() + 1;
                    ScoreArray.get(j).setNumberOfScore(score_number_new);
                }
            }
            if (!checkHaveScore) {
                Score score = new Score();
                score.setScore(score_number);
                score.setNumberOfScore(1);
                ScoreArray.add(score);
            }
        }
        Collections.sort(ScoreArray, new Comparator<Score>() {

            public int compare(Score o1, Score o2) {
                return o1.getScore().compareTo(o2.getScore());
            }
        });

        for (int i = 0; i < ScoreArray.size(); i++) {
            DataChart dataChart = new DataChart();
            List<Integer> valueDataList = new ArrayList<>();
            valueDataList.add(ScoreArray.get(i).getNumberOfScore());
            dataChart.setRowData(ScoreArray.get(i).getScore().toString());
            dataChart.setValueData(valueDataList);
            dataChartList.add(dataChart);
        }
        return dataChartList;
    }

    public List<DataChart> fetchDataForLineChart(String examCode, List<ResultXLS> resultXLSList) {
        List<DataChart> dataChartList = new ArrayList<>();
        int maxNumberQuestion = resultXLSList.get(0).getNumberOfQuestionResult();
        for (int i = 0; i < maxNumberQuestion; i++) {
            int numberCorrect = 0;
            int numberFalse = 0;
            int numberN = 0;
            for (int j = 0; j < resultXLSList.size(); j++) {
                for (int k = 0; k < maxNumberQuestion; k++) {
                    if (k == i) {
                        if (resultXLSList.get(j).getMA_DE().equals(examCode)) {
                            if (resultXLSList.get(j).getResultQuestionExcelList().get(k).resultAnwerExcel.answer != null) {
                                if (resultXLSList.get(j).getResultQuestionExcelList().get(k).resultAnwerExcel.checkAnswer == true) {
                                    numberCorrect++;
                                } else {
                                    numberFalse++;
                                }
                            } else {
                                numberN++;
                            }

                        }
                    }
                }
            }
            String charQuestion = resultXLSList.get(0).getResultQuestionExcelList().get(i).resultNumberQuestionExcel.QuestionChar;
            int numberQuestion = resultXLSList.get(0).getResultQuestionExcelList().get(i).resultNumberQuestionExcel.QuestionNumber;
            DataChart dataChart = new DataChart();
            dataChart.setRowData(charQuestion + numberQuestion);
            List<Integer> valueDataList = new ArrayList<>();
            valueDataList.add(numberCorrect);
            valueDataList.add(numberFalse);
            valueDataList.add(numberN);
            dataChart.setValueData(valueDataList);
            dataChartList.add(dataChart);
        }
        return dataChartList;
    }

    public List<DataChart> fetchDataForPieChart(List<ResultXLS> resultXLSList) {
        List<DataChart> dataChartList = new ArrayList<>();
        int maxNumberQuestion = resultXLSList.get(0).getNumberOfQuestionResult();
        int numberCorrect = 0;
        int numberFalse = 0;
        int numberN = 0;
        for (int i = 0; i < resultXLSList.size(); i++) {
            for (int j = 0; j < maxNumberQuestion; j++) {
                if (resultXLSList.get(i).getResultQuestionExcelList().get(j).resultAnwerExcel.checkAnswer != null) {
                    if (resultXLSList.get(i).getResultQuestionExcelList().get(j).resultAnwerExcel.checkAnswer == true) {
                        numberCorrect++;
                    } else {
                        numberFalse++;
                    }
                } else {
                    numberN++;
                }
            }
        }
        DataChart dataChartCorrect = new DataChart();
        dataChartCorrect.setRowData("Số lượng trả lời đúng");
        List<Integer> valueDataListCorrect = new ArrayList<>();
        valueDataListCorrect.add(numberCorrect);
        dataChartCorrect.setValueData(valueDataListCorrect);
        dataChartList.add(dataChartCorrect);

        DataChart dataChartFalse = new DataChart();
        dataChartFalse.setRowData("Số lượng trả lời sai");
        List<Integer> valueDataListFalse = new ArrayList<>();
        valueDataListFalse.add(numberFalse);
        dataChartFalse.setValueData(valueDataListFalse);
        dataChartList.add(dataChartFalse);

        DataChart dataChartNaN = new DataChart();
        dataChartNaN.setRowData("Số lượng không trả lời");
        List<Integer> valueDataListNull = new ArrayList<>();
        valueDataListNull.add(numberN);
        dataChartNaN.setValueData(valueDataListNull);
        dataChartList.add(dataChartNaN);
        return dataChartList;
    }

    public List<String> fetchAllExamCodeOfResultFile(List<ResultXLS> resultXLSList) {
        List<String> examCodeList = new ArrayList<>();
        for (int i = 0; i < resultXLSList.size(); i++) {
            boolean checkHaveExamCode = false;
            for (int j = 0; j < examCodeList.size(); j++) {
                if (resultXLSList.get(i).getMA_DE().equals(examCodeList.get(j))) {
                    checkHaveExamCode = true;
                    break;
                }
            }
            if (!checkHaveExamCode) {
                examCodeList.add(resultXLSList.get(i).getMA_DE());
            }
        }
        return examCodeList;
    }

    public List<String> getListChart() {
        List<String> chartList = new ArrayList<>();
        chartList.add(Chart.ColumnChart);
        chartList.add(Chart.PieChart);
        chartList.add(Chart.LineChart);
        chartList.add(Chart.TableChart);
        chartList.add(Chart.BarChart);
        chartList.add(Chart.ScatterChart);
        return chartList;
    }

    public List<DataChart> getDataChartForCreateChart(DataForCreateChart data) {
        List<DataChart> dataChartList = new ArrayList<>();
        int typeStatictis = Integer.parseInt(data.getTypeStatistic());

        if (typeStatictis == 1) {
                dataChartList = getDataForStatisicByObject(data);

        } else if (typeStatictis == 2) {
            dataChartList = getDataForStatisicByElementOfObject(data);

        } else {
            dataChartList = getDataForStatisicByQuestionNumber(data);
        }

        return dataChartList;
    }

    public List<DataChart> getDataForStatisicByObject(DataForCreateChart data){
        List<DataChart> dataChartList = new ArrayList<>();
        String examCode = data.getExamCode();
        int startQuestion = data.getStartQuestion();
        int finishQuestion = data.getFinishQuestion();
        List<ColumnForCreateChart> columnList = data.getColumnList();
        List<ResultXLS> resultXLSList = data.getResultXLS();
        int numberOfQuestion = resultXLSList.get(0).getNumberOfQuestionResult();

        if (examCode.equals("all")) {
            for (int i = 0; i < columnList.size(); i++) {
                List<Integer> valueDataList = new ArrayList<>();
                DataChart dataChart = new DataChart();
                dataChart.setRowData(columnList.get(i).nameColumn);
                int number = 0;

                for (int j = startQuestion; j <= finishQuestion; j++) {
                    for (int m=0; m<resultXLSList.size(); m++ ) {
                        CheckRequireService checkRequireService = new CheckRequireService();
                        List<JsonNode> dataOfExcel = data.getResultArray();
                        if (checkRequireService.checkObjectResultByRequire(numberOfQuestion, j, dataOfExcel.get(m),columnList.get(i).require)){
                            number ++;
                        }
                    }
                }
                valueDataList.add(number);

                dataChart.setValueData(valueDataList);
                dataChart.setTypeStatistic(1);
                dataChartList.add(dataChart);
            }
        }
        else {
            for (int i = 0; i < columnList.size(); i++) {
                List<Integer> valueDataList = new ArrayList<>();
                DataChart dataChart = new DataChart();
                dataChart.setRowData(columnList.get(i).nameColumn);
                int number = 0;

                for (int j = startQuestion; j <= finishQuestion; j++) {
                    for (int m=0; m<resultXLSList.size(); m++ ) {
                        CheckRequireService checkRequireService = new CheckRequireService();
                        List<JsonNode> dataOfExcel = data.getResultArray();
                        if (checkRequireService.checkObjectResultByRequire(numberOfQuestion, j, dataOfExcel.get(m),columnList.get(i).require)&&
                        (resultXLSList.get(m).getMA_DE().equals(examCode))){
                            number ++;
                        }
                    }
                }
                valueDataList.add(number);

                dataChart.setValueData(valueDataList);
                dataChart.setTypeStatistic(1);
                dataChartList.add(dataChart);
            }

        }
        return dataChartList;
    }

    public List<DataChart> getDataForStatisicByElementOfObject(DataForCreateChart data){

        List<DataChart> dataChartList = new ArrayList<>();
        String examCode = data.getExamCode();

        List<ColumnForCreateChart> columnList = data.getColumnList();
        List<ResultXLS> resultXLSList = data.getResultXLS();

        for (int m = 0; m<columnList.size(); m++){
            HashMap<String, Integer> ColumnArray = new HashMap<>();
            String columnRequired = columnList.get(m).columnRequire;
            for (int i = 0; i < resultXLSList.size(); i++) {
                if (examCode.equals("all")) {
                    if (getValueOfResultByName(i, columnRequired, data) != null) {
                        String value = getValueOfResultByName(i, columnRequired, data);
                        if (value != null){
                            if (!ColumnArray.containsKey(value)) {
                                ColumnArray.put(value, 1);
                            } else {
                                int valueOfKeyHashMap = ColumnArray.get(value);
                                valueOfKeyHashMap++;
                                ColumnArray.put(value, valueOfKeyHashMap);
                            }
                        }
                    }
                }
                else {
                    if (resultXLSList.get(i).getMA_DE().equals(examCode)){
                        if (getValueOfResultByName(i, columnRequired, data) != null) {
                            String value = getValueOfResultByName(i, columnRequired, data);
                            if (value != null) {
                                if (!ColumnArray.containsKey(value)) {
                                    ColumnArray.put(value, 1);
                                } else {
                                    int valueOfKeyHashMap = ColumnArray.get(value);
                                    valueOfKeyHashMap++;
                                    ColumnArray.put(value, valueOfKeyHashMap);
                                }
                            }
                        }
                    }
                    else continue;
                }
            }
            List<Map.Entry<String, Integer>> ColumnArraySort = new LinkedList<Map.Entry<String, Integer>>(ColumnArray.entrySet());
            List<Map.Entry<String, Integer>> ColumnArrayAfterSort = new LinkedList<>();
            try {
                ColumnArrayAfterSort = ColumnArraySort.stream()
                        .sorted((e1, e2) -> Float.compare(Float.parseFloat(e1.getKey()), Float.parseFloat(e2.getKey())))
                        .collect(Collectors.toList());
            } catch (NumberFormatException e) {
                ColumnArrayAfterSort = ColumnArraySort;
            }

            for (int j = 0; j < ColumnArrayAfterSort.size(); j++) {
                DataChart dataChart = new DataChart();
                List<Integer> valueDataList = new ArrayList<>();
                dataChart.setRowData(ColumnArrayAfterSort.get(j).getKey().toString());
                valueDataList.add(ColumnArrayAfterSort.get(j).getValue());
                dataChart.setValueData(valueDataList);
                dataChart.setTypeStatistic(2);
                dataChartList.add(dataChart);
            }

        }
        return dataChartList;
    }

    public List<DataChart> getDataForStatisicByQuestionNumber(DataForCreateChart data){
        List<DataChart> dataChartList = new ArrayList<>();
        String examCode = data.getExamCode();
        int startQuestion = data.getStartQuestion();
        int finishQuestion = data.getFinishQuestion();
        List<ColumnForCreateChart> columnList = data.getColumnList();
        List<ResultXLS> resultXLSList = data.getResultXLS();
        int numberOfQuestion = resultXLSList.get(0).getNumberOfQuestionResult();
        for (int i = startQuestion; i <= finishQuestion; i++) {
            DataChart dataChart = new DataChart();
            dataChart.setRowData("C" + i);
            List<Integer> valueDataList = new ArrayList<>();
            for (int j = 0; j < columnList.size(); j++) {
                if (examCode.equals("all")){
                    int number = 0;
                    for (int m=0; m<resultXLSList.size(); m++ ) {
                        CheckRequireService checkRequireService = new CheckRequireService();
                        List<JsonNode> dataOfExcel = data.getResultArray();
                        if (checkRequireService.checkObjectResultByRequire(numberOfQuestion, i, dataOfExcel.get(m),columnList.get(j).require)){
                            number ++;
                        }
                    }
                    valueDataList.add(number);
                }
                else {
                    int number = 0;
                    for (int m=0; m<resultXLSList.size(); m++ ) {
                        CheckRequireService checkRequireService = new CheckRequireService();
                        List<JsonNode> dataOfExcel = data.getResultArray();
                        if ((checkRequireService.checkObjectResultByRequire(numberOfQuestion, i, dataOfExcel.get(m),columnList.get(j).require)) &&
                                (resultXLSList.get(m).getMA_DE().equals(examCode))){
                            number ++;
                        }
                    }
                    valueDataList.add(number);
                }
            }
            dataChart.setValueData(valueDataList);
            dataChart.setTypeStatistic(3);
            dataChartList.add(dataChart);
        }
        return dataChartList;
    }

    public String getValueOfResultByName(int number, String fieldName, DataForCreateChart data){
        List<JsonNode> dataOfExcel = data.getResultArray();
        List<ColumnForCreateChart> columnList = data.getColumnList();
        String value = null;
        List<ResultXLS> resultXLSList = data.getResultXLS();
        int numberOfQuestion = resultXLSList.get(0).getNumberOfQuestionResult();
        if (dataOfExcel.get(number).get(fieldName) != null){
            CheckRequireService checkRequireService = new CheckRequireService();
            if (columnList.get(0).require != null){
                if (checkRequireService.checkObjectResultByRequire(numberOfQuestion,0, dataOfExcel.get(number),columnList.get(0).require)){
                    value = dataOfExcel.get(number).get(fieldName).textValue();
                }
            }
            else {
                value = dataOfExcel.get(number).get(fieldName).textValue();
            }
        }
        return value;
    }
}
