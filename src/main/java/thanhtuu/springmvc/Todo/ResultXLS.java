package thanhtuu.springmvc.Todo;
import thanhtuu.springmvc.Temporary.ResultExcel.ResultQuestionExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anh.dang on 2/7/2017.
 */
public class ResultXLS {
    private String Ma_SV;
    private String Ho_lot;
    private String Ten;
    private String Ma_MH;
    private String Ma_NH;
    private String To;
    private Float TGDIEM;
    private Float TGSAI;
    private String MA_DE;
    private Float DIEM_1;
    private String SR;
    private Float Tu_luan;
    private List<ResultQuestionExcel> resultQuestionExcelList = new ArrayList<ResultQuestionExcel>();
    private Integer numberOfQuestionResult;


    public String getMa_SV() {
        return Ma_SV;
    }

    public String getHo_lot() {
        return Ho_lot;
    }

    public String getTen() {
        return Ten;
    }

    public String getMa_MH() {
        return Ma_MH;
    }

    public String getMa_NH() {
        return Ma_NH;
    }

    public String getTo() {
        return To;
    }

    public Float getTGDIEM() {
        return TGDIEM;
    }

    public Float getTGSAI() {
        return TGSAI;
    }

    public String getMA_DE() {
        return MA_DE;
    }

    public Float getDIEM_1() {
        return DIEM_1;
    }

    public String getSR() {
        return SR;
    }

    public Float getTu_luan() {
        return Tu_luan;
    }

    public List<ResultQuestionExcel> getResultQuestionExcelList() {
        return resultQuestionExcelList;
    }

    public Integer getNumberOfQuestionResult() {
        return numberOfQuestionResult;
    }

    public void setMa_SV(String ma_SV) {
        Ma_SV = ma_SV;
    }

    public void setHo_lot(String ho_lot) {
        Ho_lot = ho_lot;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public void setMa_MH(String ma_MH) {
        Ma_MH = ma_MH;
    }

    public void setMa_NH(String ma_NH) {
        Ma_NH = ma_NH;
    }

    public void setTo(String to) {
        To = to;
    }

    public void setTGDIEM(Float TGDIEM) {
        this.TGDIEM = TGDIEM;
    }

    public void setTGSAI(Float TGSAI) {
        this.TGSAI = TGSAI;
    }

    public void setMA_DE(String MA_DE) {
        this.MA_DE = MA_DE;
    }

    public void setDIEM_1(Float DIEM_1) {
        this.DIEM_1 = DIEM_1;
    }

    public void setSR(String SR) {
        this.SR = SR;
    }

    public void setTu_luan(Float tu_luan) {
        Tu_luan = tu_luan;
    }

    public void setResultQuestionExcelList(List<ResultQuestionExcel> resultQuestionExcelList) {
        this.resultQuestionExcelList = resultQuestionExcelList;
    }

    public void setNumberOfQuestionResult(Integer numberOfQuestionResult) {
        this.numberOfQuestionResult = numberOfQuestionResult;
    }
}
