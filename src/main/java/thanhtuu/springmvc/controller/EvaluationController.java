package thanhtuu.springmvc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import thanhtuu.springmvc.Service.evaluation.EvaluationByChapterService;
import thanhtuu.springmvc.Service.evaluation.EvaluationByOutcomeService;
import thanhtuu.springmvc.Service.evaluation.ReadTargetListService;
import thanhtuu.springmvc.Temporary.Evaluation.EvaluationByOneObjectModel;
import thanhtuu.springmvc.Temporary.Evaluation.EvaluationDataAll;
import thanhtuu.springmvc.Todo.Evaluation.ChapterEvaluation;
import thanhtuu.springmvc.Todo.Evaluation.Evaluation;
import thanhtuu.springmvc.Todo.ResultXLS;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anh.dang on 3/18/2017.
 */
@Controller
public class EvaluationController {
    public static Evaluation evaluation;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/fetchAllForEvaluation",  method = RequestMethod.POST)
    public EvaluationDataAll fetchAllForEvaluation(@RequestBody Evaluation data, Model model) {
        List<ResultXLS> resultArray = data.getResultArray();
        List<ResultXLS> resultXLSList = new ArrayList<>();
        String examCode = data.getExamCode();
        if (! examCode.equals("all")) {
            for (ResultXLS resultXLS : resultArray) {
                if (resultXLS.getMA_DE().equals(examCode)){
                    resultXLSList.add(resultXLS);
                }
            }
            data.setResultArray(resultXLSList);
        }
        evaluation = data;
        ReadTargetListService readTargetListService = new ReadTargetListService();
        EvaluationDataAll evaluationDataAll = readTargetListService.fetchAllForEvaluation(data);
        return evaluationDataAll;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/fetchEvaluationByChapterSelect",  method = RequestMethod.POST)
    public EvaluationByOneObjectModel fetchEvaluationByChapterSelect(@RequestParam("chapterId") Long chapterId) {
        EvaluationByOneObjectModel evaluationByOneObjectModel = new EvaluationByOneObjectModel();
        ReadTargetListService readTargetListService = new ReadTargetListService();
        List<ChapterEvaluation> evaluationList = readTargetListService.fetchAllChapterForEvaluation(evaluation);
        EvaluationByChapterService evaluationByChapterService = new EvaluationByChapterService();
        evaluationByOneObjectModel = evaluationByChapterService.getEvaluationByOneChapter(evaluationList, chapterId, evaluation);
        return evaluationByOneObjectModel;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/fetchEvaluationByOutcomeSelect",  method = RequestMethod.POST)
    public EvaluationByOneObjectModel fetchEvaluationByOutcomeSelect(@RequestParam("outcomeId") Long outcomeId) {
        EvaluationByOneObjectModel evaluationByOneObjectModel = new EvaluationByOneObjectModel();
        ReadTargetListService readTargetListService = new ReadTargetListService();
        List<ChapterEvaluation> evaluationList = readTargetListService.fetchAllChapterForEvaluation(evaluation);
        EvaluationByOutcomeService evaluationByOutcomeService = new EvaluationByOutcomeService();
        evaluationByOneObjectModel = evaluationByOutcomeService.getEvaluationByOneOutcome(evaluationList, outcomeId, evaluation);
        return evaluationByOneObjectModel;
    }
}
