import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.DynamicTimeSeriesCollection;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
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
public class MultiDynamicLineChart {

	
	private static final int SUBPLOT_COUNT = 1;
	
	ChartPanel chartPanel;

	DynamicTimeSeriesCollection[] dataset;
	
	private Timer timer;
	private static final int SLOW = 100;
	
	 private static final Random random = new Random();
	 private static final float MINMAX = 100;
	 private static final int COUNT = 60;
	 private static float lastValue[];
	 
	 private JFreeChart chart;
	
    public MultiDynamicLineChart() {
	
    	lastValue = new float[SUBPLOT_COUNT];
    	
    	//CREATE DATASET ARRAY
    	dataset = new DynamicTimeSeriesCollection[SUBPLOT_COUNT];
    	
    	//MAIN PLOT
    	CombinedDomainXYPlot mainPlot = new CombinedDomainXYPlot(new DateAxis("Time"));
    	
    	//EACH SUBPLOT
    	for(int i=0;i<SUBPLOT_COUNT;i++){
	    	//final JFreeChart result = ChartFactory.createTimeSeriesChart(
	        //        "", "", "Temp(�C)", dataset, false, false, false);
			
    		dataset[i] = new DynamicTimeSeriesCollection(1, COUNT, new Second());
            dataset[i].setTimeBase(new Second(0, 0, 0, 1, 3, 2013));
            dataset[i].addSeries(gaussianData(i), 0, "");
    	
	        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
	        renderer.setSeriesPaint(0, new Color(0, 255, 0));
	        renderer.setBaseShapesVisible(false);
	        
	        
	        DateAxis domain = new DateAxis("");
	        /*domain.setAutoRange(true);
	        domain.setTickUnit(new DateTickUnit(DateTickUnitType.SECOND,5));
	        domain.setVisible(false);
	       */
	        ValueAxis range = new NumberAxis();
	        range.setRange(-100, 100);
	        //range.setAutoRange(true);
	        
	        XYPlot plot = new XYPlot(dataset[i], null, range, renderer);
	       
	        plot.setBackgroundPaint(new Color(30,49,84));
	        plot.setAxisOffset(new RectangleInsets(1.0, 3.0, 3.0, 1.0));
	        Color lighterBlue = new Color(34,70,136);
	        plot.setDomainGridlinePaint(lighterBlue);
	        plot.setRangeGridlinePaint(lighterBlue);
	        plot.setDomainGridlineStroke(new BasicStroke(1.0f));
	        plot.setRangeGridlineStroke(new BasicStroke(1.0f));
	        
	        Font font = new Font("Helvetica", Font.BOLD, 14);
	        range.setLabelFont(font);
	        
	        mainPlot.add(plot);
    	}
    	
    	//CREATE MAIN CHART
    	chart = new JFreeChart("", mainPlot);
    	chart.setAntiAlias(false);
        chart.removeLegend();
        
        chartPanel = new ChartPanel(chart);
        chartPanel.setMinimumDrawHeight(100);
    	
        ////////////
        /*
    	final DynamicTimeSeriesCollection dataset = new DynamicTimeSeriesCollection(1, COUNT, new Second());
        dataset.setTimeBase(new Second(0, 0, 0, 1, 1, 2011));
        dataset.addSeries(gaussianData(), 0, "");
        JFreeChart chart = createChart(dataset);
        chart.setAntiAlias(false);
        chart.removeLegend();
        
        chartPanel = new ChartPanel(chart);
        chartPanel.setMinimumDrawHeight(100);
        */
        /////////////
        
        timer = new Timer(SLOW, new ActionListener() {

            float[] newData = new float[1];

            @Override
            public void actionPerformed(ActionEvent e) {
            	chart.setNotify(false);
                for(int i=0;i<SUBPLOT_COUNT;i++){
	            	newData[0] = randomValue(i);
	                dataset[i].advanceTime();
	                dataset[i].appendData(newData);
                }
                chart.setNotify(true);
           }
        });
        
    }
    
    public void start() {
        timer.start();
    }
    
    private JFreeChart createChart(final XYDataset dataset) {
        final JFreeChart result = ChartFactory.createTimeSeriesChart(
            "", "", "Temp(�C)", dataset, false, false, false);
        final XYPlot plot = result.getXYPlot();
       // plot.setDomainPannable(true);
        
        plot.setBackgroundPaint(new Color(30,49,84));
        plot.setAxisOffset(new RectangleInsets(1.0, 3.0, 3.0, 1.0));
        Color lighterBlue = new Color(34,70,136);
        plot.setDomainGridlinePaint(lighterBlue);
        plot.setRangeGridlinePaint(lighterBlue);
        plot.setDomainGridlineStroke(new BasicStroke(1.0f));
        plot.setRangeGridlineStroke(new BasicStroke(1.0f));
        
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(0, 255, 0));
        
        DateAxis domain = (DateAxis)plot.getDomainAxis();
        domain.setAutoRange(true);
        domain.setTickUnit(new DateTickUnit(DateTickUnitType.SECOND,15));
        domain.setVisible(false);
        
        ValueAxis range = plot.getRangeAxis();
        range.setRange(-100, 100);
        //range.setAutoRange(true);
        
        Font font = new Font("Helvetica", Font.BOLD, 14);
        range.setLabelFont(font);
        
        // change the auto tick unit selection to integer units only...
      //  NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
       // rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
      //  Font font = new Font("Helvetica", Font.BOLD, 14);
       // rangeAxis.setLabelFont(font);

        return result;
    }
    
   
    
    private float randomValue(int i) {
        lastValue[i] = lastValue[i] + ((float)random.nextGaussian() * 5);
    	return lastValue[i];
    }
    
    private float[] gaussianData(int poop) {
        float[] a = new float[COUNT];
        for (int i = 0; i < a.length; i++) {
            a[i] = randomValue(poop);
        }
        return a;
    }
    
    public ChartPanel getChartPanel(){
    	return chartPanel;
    }

}
