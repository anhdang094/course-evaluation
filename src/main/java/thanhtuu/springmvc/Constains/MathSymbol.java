package thanhtuu.springmvc.Constains;

/**
 * Created by anh.dang on 3/4/2017.
 */
public enum MathSymbol {
    Math_AND("AND"), Math_OR("OR"), Math_BIGER(">"), Math_SMALLER("<"), Math_SAME("="), Math_SAME_BIGER(">="), Math_SAME_SMALLER("<=");

    private String symbol;

    MathSymbol(String symbol){
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
