import java.awt.Color;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.MeterInterval;
import org.jfree.chart.plot.MeterPlot;
import org.jfree.data.Range;
import org.jfree.data.general.DefaultValueDataset;


public class MeterPlotChart {

	ChartPanel chartPanel;
	
	MeterPlotChart(){
		DefaultValueDataset dataset = new DefaultValueDataset(50.0);
       
        MeterPlot plot = new MeterPlot(dataset);
        plot.addInterval(new MeterInterval("High", new Range(80.0, 100.0)));
        plot.setDialOutlinePaint(Color.white);
        JFreeChart chart = new JFreeChart("Meter Chart 2", JFreeChart.DEFAULT_TITLE_FONT, plot, false);
        
		chartPanel = new ChartPanel(chart);
	}
	
	ChartPanel getChartPanel(){
		return chartPanel;
	}
	
	
}
