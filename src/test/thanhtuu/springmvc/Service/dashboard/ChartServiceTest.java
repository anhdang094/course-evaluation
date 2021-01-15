package thanhtuu.springmvc.Service.dashboard;

import org.junit.Assert;
import org.junit.Test;
import thanhtuu.springmvc.Constains.Chart;
import thanhtuu.springmvc.Temporary.Chart.DataForCreateChart;
import thanhtuu.springmvc.Todo.DataChart;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by anh.dang on 05/27/2017.
 */
public class ChartServiceTest {

    @Test
    public void getListChart() throws Exception {
        ChartService chartService = new ChartService();
        List<String> listChart = chartService.getListChart();
        Assert.assertEquals(listChart.size(), 6);
        Assert.assertEquals(listChart.get(0), Chart.ColumnChart);
        Assert.assertEquals(listChart.get(1), Chart.PieChart);
        Assert.assertEquals(listChart.get(2), Chart.LineChart);
        Assert.assertEquals(listChart.get(3), Chart.TableChart);
        Assert.assertEquals(listChart.get(4), Chart.BarChart);
        Assert.assertEquals(listChart.get(5), Chart.ScatterChart);
    }

    @Test
    public void getDataChartForCreateChart() throws Exception {
        ChartService chartService = new ChartService();
        DataForCreateChart data = new DataForCreateChart();
        data.setTypeStatistic("1");
        Assert.assertEquals(data.getTypeStatistic(), "1");
        Assert.assertNotEquals(data.getTypeStatistic(), "2");
    }
}