import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Stroke;

import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a line chart using
 * data from an {@link XYDataset}.
 * <p>
 * IMPORTANT NOTE: THIS DEMO IS DOCUMENTED IN THE JFREECHART DEVELOPER GUIDE.
 * DO NOT MAKE CHANGES WITHOUT UPDATING THE GUIDE ALSO!!
 */
public class LineChart {

	
	ChartPanel chartPanel;
    /**
     * Creates a new demo.
     *
     * @param title the frame title.
     */
    public LineChart() {
        XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        chartPanel = new ChartPanel(chart);
        //prevents font stretching
        chartPanel.setMinimumDrawHeight(100);
        //chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        //setContentPane(chartPanel);
    }
    
    public ChartPanel getChartPanel(){
    	return chartPanel;
    }

    /**
     * Creates a sample dataset.
     *
     * @return a sample dataset.
     */
    private static XYDataset createDataset() {
        XYSeries series1 = new XYSeries("First");
        series1.add(1.0, 1.0);
        series1.add(1.5, 1.3);
        series1.add(2.0, 2.1);
        series1.add(2.5, 1.9);
        series1.add(3.0, 2.4);
        series1.add(3.5, 3.6);
        series1.add(4.0, 4.3);
        series1.add(4.5, 4.2);
        series1.add(5.0, 4.1);
        series1.add(5.5, 4.15);
        series1.add(6.0, 4.2);
        series1.add(7.0, 4.55);
        series1.add(7.5, 4.2);
        series1.add(8.0, 4.35);
       /*
        XYSeries series2 = new XYSeries("Second");
        series2.add(1.0, 4.0);
        series2.add(1.5, 4.3);
        series2.add(2.0, 4.1);
        series2.add(2.5, 4.2);
        series2.add(3.0, 3.8);
        series2.add(3.5, 3.6);
        series2.add(4.0, 3.8);
        series2.add(4.5, 4.2);
        series2.add(5.0, 3.6);
        series2.add(5.5, 3.35);
        series2.add(6.0, 3.1);
        series2.add(7.0, 2.6);
        series2.add(7.5, 2.2);
        series2.add(8.0, 1.35);
       */
       /*
        XYSeries series3 = new XYSeries("Third");
        series3.add(3.0, 4.0);
        series3.add(4.0, 3.0);
        series3.add(5.0, 2.0);
        series3.add(6.0, 3.0);
        series3.add(7.0, 6.0);
        series3.add(8.0, 3.0);
        series3.add(9.0, 4.0);
        series3.add(10.0, 3.0);
        */
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        //dataset.addSeries(series2);
        //dataset.addSeries(series3);
        return dataset;
    }

    /**
     * Creates a chart.
     *
     * @param dataset the data for the chart.
     *
     * @return a chart.
     */
    private static JFreeChart createChart(XYDataset dataset) {
// create the chart...
        JFreeChart chart = ChartFactory.createXYLineChart(
                "", // chart title
                "", // x axis label
                "Temp (¼C)", // y axis label
                dataset, // data
                PlotOrientation.VERTICAL,
                false, // include legend
                true, // tooltips
                false // urls
                );
        chart.setAntiAlias(false);
       
        
// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);
// get a reference to the plot for further customisation...
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setBackgroundPaint(new Color(30,49,84));
        plot.setAxisOffset(new RectangleInsets(1.0, 3.0, 3.0, 1.0));
        Color lighterBlue = new Color(34,70,136);
        plot.setDomainGridlinePaint(lighterBlue);
        plot.setRangeGridlinePaint(lighterBlue);
        plot.setDomainGridlineStroke(new BasicStroke(1.0f));
        plot.setRangeGridlineStroke(new BasicStroke(1.0f));
        
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(0, 255, 0));
        //renderer.setSeriesPaint(1, Color.CYAN);
        //renderer.setShapesVisible(true);
        //renderer.setShapesFilled(true);
        
        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setVisible(false);
        
        // change the auto tick unit selection to integer units only...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        Font font = new Font("Helvetica", Font.BOLD, 14);
        rangeAxis.setLabelFont(font);
// OPTIONAL CUSTOMISATION COMPLETED.
        return chart;
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args ignored.
     */
 /*
    public static void main(String[] args) {
        LineChart demo = new LineChart("Line Chart Demo 2");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
    */
}