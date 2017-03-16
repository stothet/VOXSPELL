package gamingMode;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame;


public class BarChart extends ApplicationFrame{
		ChartFrame frame;
		 public ArrayList<Record> stats2=new ArrayList<Record>();
	public BarChart( String applicationTitle , String chartTitle )
	{
		super( applicationTitle );        
		JFreeChart barChart = ChartFactory.createBarChart(
				chartTitle,           
				"Level",            
				"Mastered Words",            
				createDataset(),          
				PlotOrientation.VERTICAL,           
				true, true, false);
		
		frame = new ChartFrame("VOXSPELL statistics", barChart);
		frame.setResizable(false);
		frame.getChartPanel().setRangeZoomable(false);
		frame.getChartPanel().setInitialDelay(400);
		frame.setVisible(true);
		frame.setSize(750, 500);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				frame.dispose();
			}
		});
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
	}
	private CategoryDataset createDataset( )
	{

		final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );  
		
		stats2=(new PersonalBests()).stats;
		
		 for(Record f : stats2){
			 dataset.addValue( f.count , f.level+"" , "Level "+f.level );  
	        }
	 

		return dataset; 
	}
}
