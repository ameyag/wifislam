package wifislam.problem;

import java.util.ArrayList;

import org.jfree.chart.ChartFactory; 
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 

public class ChartingTool extends ApplicationFrame{


	public ChartingTool(String title) { 

		super(title); 

		ArrayList<LocationCoordinates> coordinates = DataParser.parse();
		LocationCoordinates bestEstimate = DistanceBasedEstimation.getBestLocationEstimate(coordinates);

		XYSeriesCollection dataset = new XYSeriesCollection(); 

		XYSeries series1 = new XYSeries("People's Guesses"); 
		//			series1.add(1.0, 4.5); 
		//			series1.add(4.4, 3.2); 

		for(LocationCoordinates tempLocation : coordinates)
		{
			if(!(tempLocation.xcoordinate.equals(bestEstimate.xcoordinate)
					&&tempLocation.ycoordinate.equals(bestEstimate.ycoordinate)))
			{
				series1.add(tempLocation.xcoordinate,tempLocation.ycoordinate);
			}
		}
		dataset.addSeries(series1); 


		XYSeries series2 = new XYSeries("Estimated Position"); 
		series2.add(bestEstimate.xcoordinate,bestEstimate.ycoordinate);
		//			series2.add(3.2, 8.5); 
		//			series2.add(4.9, 3.7); 
		dataset.addSeries(series2); 
		JFreeChart chart = ChartFactory.createScatterPlot( 
				"Plot for estimating Jessica's position in the Mall ", // title 
				"X", "Y", // axis labels 
				dataset, // dataset 
				PlotOrientation.VERTICAL, 
				true, // legend? yes 
				true, // tooltips? yes 
				false // URLs? no 
				); 
		ChartPanel chartPanel = new ChartPanel(chart); 
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270)); 
		setContentPane(chartPanel); 

	} 

	public static void runThis() { 
		ChartingTool demo = new ChartingTool("Scatter Plot Demo"); 
		demo.pack(); 
		RefineryUtilities.centerFrameOnScreen(demo); 
		demo.setVisible(true); 
	} 

}



