package thanhtuu.springmvc.Todo.Evaluation;

import com.fasterxml.jackson.databind.JsonNode;
import thanhtuu.springmvc.Domain.Chapter;
import thanhtuu.springmvc.Temporary.ChapterList;
import thanhtuu.springmvc.Temporary.Evaluation.ElementTargetList;
import thanhtuu.springmvc.Temporary.ResultExcel.ResultExcelData;
import thanhtuu.springmvc.Todo.ResultXLS;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anh.dang on 3/19/2017.
 */
public class Evaluation {
    private List<ResultXLS> resultArray;
    private List<ElementTargetList> targetList;
    private Integer numberQuestion;
    List<Chapter> chapterList = new ArrayList<>();
    private String examCode;

    public List<ResultXLS> getResultArray() {
        return resultArray;
    }

    public void setResultArray(List<ResultXLS> resultArray) {
        this.resultArray = resultArray;
    }

    public List<ElementTargetList> getTargetList() {
        return targetList;
    }

    public void setTargetList(List<ElementTargetList> targetList) {
        this.targetList = targetList;
    }

    public Integer getNumberQuestion() {
        return numberQuestion;
    }

    public void setNumberQuestion(Integer numberQuestion) {
        this.numberQuestion = numberQuestion;
    }

    public List<Chapter> getChapterList() {
        return chapterList;
    }

    public void setChapterList(List<Chapter> chapterList) {
        this.chapterList = chapterList;
    }

    public String getExamCode() {
        return examCode;
    }

    public void setExamCode(String examCode) {
        this.examCode = examCode;
    }
}
