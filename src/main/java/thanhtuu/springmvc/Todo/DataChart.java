package thanhtuu.springmvc.Todo;

import java.util.List;

/**
 * Created by anh.dang on 2/9/2017.
 */
public class DataChart {
    private String rowData;
    private List<Integer> valueData;
    private Integer typeStatistic;

    public String getRowData() {
        return rowData;
    }

    public void setRowData(String rowData) {
        this.rowData = rowData;
    }

    public List<Integer> getValueData() {
        return valueData;
    }

    public void setValueData(List<Integer> valueData) {
        this.valueData = valueData;
    }

    public Integer getTypeStatistic() {
        return typeStatistic;
    }

    public void setTypeStatistic(Integer typeStatistic) {
        this.typeStatistic = typeStatistic;
    }
}
