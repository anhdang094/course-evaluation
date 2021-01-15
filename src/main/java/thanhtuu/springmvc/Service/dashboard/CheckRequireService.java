package thanhtuu.springmvc.Service.dashboard;

import com.fasterxml.jackson.databind.JsonNode;
import org.mvel2.MVEL;
import thanhtuu.springmvc.Constains.ColumnNameAdd;
import thanhtuu.springmvc.Constains.MathSymbol;
import thanhtuu.springmvc.Temporary.ResultExcel.ResultQuestionExcel;
import thanhtuu.springmvc.Todo.ResultXLS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by anh.dang on 3/4/2017.
 */
public class CheckRequireService {

    private String removeSpace(String str) {
        while (str.startsWith(" ")){
            str = str.substring(1);
        }
        while (str.endsWith(" ")){
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    private boolean checkObjectResultRowBySame (Integer numberOfQuestion, Integer numberI, JsonNode objectResultRow, String strBefore, String strAfter){
        if (strBefore.equals(ColumnNameAdd.Tra_loi)){
            ReadResultExcelService readResultExcelService = new ReadResultExcelService();
            List<ResultQuestionExcel> resultQuestionExcelList = readResultExcelService.readResultQuestionExcel(numberOfQuestion, objectResultRow);
            if (resultQuestionExcelList.get(numberI - 1).resultAnwerExcel.answer != null) {
                if (resultQuestionExcelList.get(numberI - 1).resultAnwerExcel.answer.toString().equals(strAfter)) {
                    return true;
                } else {
                    return false;
                }
            }
            else {
                return false;
            }
        }
        else if (strBefore.equals(ColumnNameAdd.Dap_An)){
            ReadResultExcelService readResultExcelService = new ReadResultExcelService();
            List<ResultQuestionExcel> resultQuestionExcelList = readResultExcelService.readResultQuestionExcel(numberOfQuestion, objectResultRow);
            Boolean strAfterBool = false;
            if (strAfter.equals("1")){
                strAfterBool = true;
            }
            else if (strAfter.equals("S")){
                strAfterBool = false;
            }
            if (resultQuestionExcelList.get(numberI - 1).resultAnwerExcel.checkAnswer != null) {
                if (resultQuestionExcelList.get(numberI - 1).resultAnwerExcel.checkAnswer == strAfterBool) {
                    return true;
                } else {
                    return false;
                }
            }
            else {
                return false;
            }
        }
        else if (objectResultRow.get(strBefore).textValue().equals(strAfter)){
            return true;
        }
        else {
            try {
                if (Float.parseFloat(objectResultRow.get(strBefore).textValue()) == Float.parseFloat(strAfter)) {
                    return true;
                } else {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }

    private boolean checkObjectResultRowByBigger (JsonNode objectResultRow, String strBefore, String strAfter){
        try {
            if (Float.parseFloat(objectResultRow.get(strBefore).textValue()) > Float.parseFloat(strAfter)) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return true;
        }

    }

    private boolean checkObjectResultRowBySmall (JsonNode objectResultRow, String strBefore, String strAfter){
        try {
            if (Float.parseFloat(objectResultRow.get(strBefore).textValue()) < Float.parseFloat(strAfter)) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return true;
        }
    }

    private boolean checkObjectResultRowBySameSmall (JsonNode objectResultRow, String strBefore, String strAfter){
        try {
            if (Float.parseFloat(objectResultRow.get(strBefore).textValue()) <= Float.parseFloat(strAfter)) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return true;
        }
    }

    private boolean checkObjectResultRowBySameBig (JsonNode objectResultRow, String strBefore, String strAfter){
        try {
            if (Float.parseFloat(objectResultRow.get(strBefore).textValue()) >= Float.parseFloat(strAfter)) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return true;
        }
    }

    public boolean checkObjectResultByRequire(Integer numberOfQuestion, Integer numberI, JsonNode objectResultRow, String required){

        List<String> stringList = new ArrayList<>();
        int start = 0;
        int end = 0;
        for (int i=0; i< required.length(); i++){
            if (required.charAt(i) == '['){
                start = i;
            }
            if (required.charAt(i) == ']'){
                end = i;
                String temp = required.substring(start + 1, end);
                stringList.add(temp);
                start = 0;
            }
        }

        for (int i=0; i<stringList.size(); i++){
            String strSearch = stringList.get(i);

            if (strSearch.contains(MathSymbol.Math_SAME_BIGER.getSymbol())){
                String strBefore = removeSpace(strSearch.substring(0, strSearch.indexOf(MathSymbol.Math_SAME_BIGER.getSymbol())));
                String strAfter = removeSpace(strSearch.substring(strSearch.indexOf(MathSymbol.Math_SAME_BIGER.getSymbol()) + 2));
                required = required.replace("[" + strSearch + "]", Boolean.toString(checkObjectResultRowBySameBig(objectResultRow, strBefore,strAfter)));
            }
            else if (strSearch.contains(MathSymbol.Math_SAME_SMALLER.getSymbol())){
                String strBefore = removeSpace(strSearch.substring(0, strSearch.indexOf(MathSymbol.Math_SAME_SMALLER.getSymbol())));
                String strAfter = removeSpace(strSearch.substring(strSearch.indexOf(MathSymbol.Math_SAME_SMALLER.getSymbol()) + 2));
                required = required.replace("[" + strSearch + "]", Boolean.toString(checkObjectResultRowBySameSmall(objectResultRow, strBefore,strAfter)));
            }
            else if (strSearch.contains(MathSymbol.Math_SAME.getSymbol())){
                String strBefore = removeSpace(strSearch.substring(0, strSearch.indexOf(MathSymbol.Math_SAME.getSymbol())));
                String strAfter = removeSpace(strSearch.substring(strSearch.indexOf(MathSymbol.Math_SAME.getSymbol()) + 1));
                required = required.replace("[" + strSearch + "]", Boolean.toString(checkObjectResultRowBySame(numberOfQuestion, numberI, objectResultRow, strBefore,strAfter)));
            }
            else if (strSearch.contains(MathSymbol.Math_BIGER.getSymbol())){
                String strBefore = removeSpace(strSearch.substring(0, strSearch.indexOf(MathSymbol.Math_BIGER.getSymbol())));
                String strAfter = removeSpace(strSearch.substring(strSearch.indexOf(MathSymbol.Math_BIGER.getSymbol()) + 1));
                required = required.replace("[" + strSearch + "]", Boolean.toString(checkObjectResultRowByBigger(objectResultRow, strBefore,strAfter)));
            }
            else if (strSearch.contains(MathSymbol.Math_SMALLER.getSymbol())){
                String strBefore = removeSpace(strSearch.substring(0, strSearch.indexOf(MathSymbol.Math_SMALLER.getSymbol())));
                String strAfter = removeSpace(strSearch.substring(strSearch.indexOf(MathSymbol.Math_SMALLER.getSymbol()) + 1));
                required = required.replace("[" + strSearch + "]", Boolean.toString(checkObjectResultRowBySmall(objectResultRow, strBefore,strAfter)));
            }
        }
        required = required.replaceAll(MathSymbol.Math_AND.getSymbol(), "&&");
        required = required.replaceAll(MathSymbol.Math_OR.getSymbol(), "||");

        return (Boolean)(MVEL.eval(required));
    }
}