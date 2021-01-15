package thanhtuu.springmvc.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import thanhtuu.springmvc.Constains.Chart;
import thanhtuu.springmvc.Service.dashboard.ChartService;
import thanhtuu.springmvc.Service.dashboard.ReadResultExcelService;
import thanhtuu.springmvc.Temporary.Chart.ColumnForCreateChart;
import thanhtuu.springmvc.Temporary.Chart.DataForCreateChart;
import thanhtuu.springmvc.Temporary.Chart.ResultForChat;
import thanhtuu.springmvc.Temporary.Chart.Score;
import thanhtuu.springmvc.Temporary.ResultExcel.ResultExcelData;
import thanhtuu.springmvc.Todo.DataChart;
import thanhtuu.springmvc.Todo.ResultXLS;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anh.dang on 1/18/2017.
 */
@Controller
public class StatisticalController {

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/sendResult",  method = RequestMethod.POST)
    public ResponseEntity<List<ResultXLS>> getResultExcel(@RequestBody ResultExcelData data, Model model) {
        List<ResultXLS> resultXLSList = new ArrayList<ResultXLS>();
        ReadResultExcelService readResultExcelService = new ReadResultExcelService();
        resultXLSList = readResultExcelService.readListResultExcel(data);
        return new ResponseEntity<List<ResultXLS>>(resultXLSList, HttpStatus.OK);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/fetchAllExamCodeOfResultFile",  method = RequestMethod.POST)
    public ResponseEntity<List<String>> getResultExcel(@RequestBody List<ResultXLS> resultXLS, Model model) {
        ChartService chartService = new ChartService();
        List<String> examCodeList = new ArrayList<String>();
        examCodeList = chartService.fetchAllExamCodeOfResultFile(resultXLS);
        return new ResponseEntity<List<String>>(examCodeList, HttpStatus.OK);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/fetchDataForChart",  method = RequestMethod.POST)
    public ResponseEntity<List<DataChart>> getDataChart(@RequestBody ResultForChat data, Model model) {
        ChartService chartService = new ChartService();
        List<ResultXLS> resultXLSList = new ArrayList<ResultXLS>();
        resultXLSList = data.getResultXLS();
        String chart = data.getChart();
        List<DataChart> dataChartList = new ArrayList<>();
        Integer numberQuestion = data.getNumberQuestion();
        if (chart.equals(Chart.ColumnChart)) {
            dataChartList = chartService.fetchDataForColumnChart(resultXLSList, numberQuestion);
        }
        if (chart.equals(Chart.LineChart)) {
            dataChartList = chartService.fetchDataForLineChart(data.getExamCode(), resultXLSList);
        }
        if (chart.equals(Chart.PieChart)) {
            dataChartList = chartService.fetchDataForPieChart(resultXLSList);
        }
        return new ResponseEntity<List<DataChart>>(dataChartList, HttpStatus.OK);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/fetchChartList",  method = RequestMethod.GET)
    public ResponseEntity<List<String>> getChartList() {
        ChartService chartService = new ChartService();
        List<String> chartList = new ArrayList<>();
        chartList = chartService.getListChart();
        return new ResponseEntity<List<String>>(chartList, HttpStatus.OK);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/sendCreateChart",  method = RequestMethod.POST)
    public ResponseEntity<List<DataChart>> createChart(@RequestBody DataForCreateChart data, Model model) {
        List<DataChart> dataChartList = new ArrayList<>();
        ChartService chartService = new ChartService();
        dataChartList = chartService.getDataChartForCreateChart(data);
        return new ResponseEntity<List<DataChart>>(dataChartList, HttpStatus.OK);
    }
}
