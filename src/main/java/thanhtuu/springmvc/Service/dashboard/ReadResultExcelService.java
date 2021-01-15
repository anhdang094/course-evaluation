package thanhtuu.springmvc.Service.dashboard;

import com.fasterxml.jackson.databind.JsonNode;
import thanhtuu.springmvc.Constains.Answer;
import thanhtuu.springmvc.Constains.CheckAnswer;
import thanhtuu.springmvc.Constains.ColumnName;
import thanhtuu.springmvc.Temporary.ResultExcel.ResultAnwerExcel;
import thanhtuu.springmvc.Temporary.ResultExcel.ResultExcelData;
import thanhtuu.springmvc.Temporary.ResultExcel.ResultNumberQuestionExcel;
import thanhtuu.springmvc.Temporary.ResultExcel.ResultQuestionExcel;
import thanhtuu.springmvc.Todo.ResultXLS;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

/**
 * Created by anh.dang on 2/7/2017.
 */
public class ReadResultExcelService {

    public List<ResultXLS> readListResultExcel (ResultExcelData data){
        List<ResultXLS> resultXLSList = new ArrayList<ResultXLS>();
        List<JsonNode> dataResult = data.getResultArray();
        for (int i=0; i< dataResult.size(); i++) {
            resultXLSList.add(readResultExcel(data.getNumberQuestion(), dataResult.get(i)));
        }
        return resultXLSList;
    }

    public ResultXLS readResultExcel(Integer numberOfQuestion, JsonNode data){
        ResultXLS resultXLS = new ResultXLS();
        resultXLS.setMa_SV(data.get(ColumnName.Ma_SV).textValue());
        resultXLS.setHo_lot(data.get(ColumnName.Ho_lot).textValue());
        resultXLS.setTen(data.get(ColumnName.Ten).textValue());
        resultXLS.setMa_MH(data.get(ColumnName.Ma_MH).textValue());
        resultXLS.setMa_NH(data.get(ColumnName.Ma_NH).textValue());
        resultXLS.setTo(data.get(ColumnName.To).textValue());
        resultXLS.setTGDIEM(Float.parseFloat(data.get(ColumnName.TGDIEM).textValue()));
        resultXLS.setTGSAI(abs(Float.parseFloat(data.get(ColumnName.TGSAI).textValue())));
        resultXLS.setMA_DE(data.get(ColumnName.Ma_de).textValue());
        resultXLS.setSR(data.get(ColumnName.SR).textValue());
        resultXLS.setNumberOfQuestionResult(numberOfQuestion);

        if (data.get(ColumnName.DIEM_1) != null) {
            resultXLS.setDIEM_1(Float.parseFloat(data.get(ColumnName.DIEM_1).textValue()));
        }
        else {
            resultXLS.setDIEM_1((float)0);
        }
        if (data.get(ColumnName.Tu_luan) != null) {
            resultXLS.setTu_luan(Float.parseFloat(data.get(ColumnName.Tu_luan).textValue()));
        }
        else {
            resultXLS.setTu_luan((float)0);
        }

        List<ResultQuestionExcel> resultQuestionExcelList = new ArrayList<ResultQuestionExcel>();
        resultQuestionExcelList = readResultQuestionExcel(numberOfQuestion, data);
        resultXLS.setResultQuestionExcelList(resultQuestionExcelList);
        return resultXLS;
    }

    public List<ResultQuestionExcel> readResultQuestionExcel (Integer numberOfQuestion, JsonNode data){
        List<ResultQuestionExcel> resultQuestionExcelList = new ArrayList<ResultQuestionExcel>();

        for (int i=1; i<= numberOfQuestion; i++){
            ResultQuestionExcel resultQuestionExcel = new ResultQuestionExcel();
            ResultNumberQuestionExcel resultNumberQuestionExcel = new ResultNumberQuestionExcel();
            resultNumberQuestionExcel.QuestionNumber = i;
            ResultAnwerExcel resultAnwerExcel = new ResultAnwerExcel();
            ColumnName.setQuestion(i);
            if (data.get(ColumnName.getQuestion()) == null){
                resultAnwerExcel.answer = null;
                resultAnwerExcel.checkAnswer = null;
            }
            else {
                if (data.get(ColumnName.getQuestion()).textValue().charAt(0) == Answer.A) {
                    resultAnwerExcel.answer = Answer.A;
                }
                if (data.get(ColumnName.getQuestion()).textValue().charAt(0) == Answer.B) {
                    resultAnwerExcel.answer = Answer.B;
                }
                if (data.get(ColumnName.getQuestion()).textValue().charAt(0) == Answer.C) {
                    resultAnwerExcel.answer = Answer.C;
                }
                if (data.get(ColumnName.getQuestion()).textValue().charAt(0) == Answer.D) {
                    resultAnwerExcel.answer = Answer.D;
                }
                if (data.get(ColumnName.getQuestion()).textValue().charAt(0) == Answer.E) {
                    resultAnwerExcel.answer = Answer.E;
                }
                if (String.valueOf(data.get(ColumnName.getQuestion()).textValue().charAt(1)).equals(CheckAnswer.D)) {
                    resultAnwerExcel.checkAnswer = true;
                }
                if (String.valueOf(data.get(ColumnName.getQuestion()).textValue().charAt(1)).equals(CheckAnswer.S)) {
                    resultAnwerExcel.checkAnswer = false;
                }
            }
            resultQuestionExcel.resultNumberQuestionExcel = resultNumberQuestionExcel;
            resultQuestionExcel.resultAnwerExcel = resultAnwerExcel;
            resultQuestionExcelList.add(resultQuestionExcel);
        }
        return resultQuestionExcelList;
    }
}
