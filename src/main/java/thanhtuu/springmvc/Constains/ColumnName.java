package thanhtuu.springmvc.Constains;

/**
 * Created by anh.dang on 2/7/2017.
 */
public class ColumnName {
    public static String Ma_SV = "F_MASV";
    public static String Ho_lot = "F_HOLOT";
    public static String Ten = "F_TEN";
    public static String Ma_NH = "F_MANH";
    public static String To = "F_TO";
    public static String TGDIEM = "TGDIEM";
    public static String TGSAI = "TGSAI";
    public static String Ma_MH = "F_MAMH";
    public static String Ma_de = "MADE";
    public static String DIEM_1 = "F_DIEM1";
    public static String SR = "SR";
    public static String Tu_luan = "TL";

    private static String question;

    public static String getQuestion() {
        return question;
    }

    public static void setQuestion(int number) {
        question = "C" + number;
    }
}
