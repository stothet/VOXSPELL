package Sound;

import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;



public class Festival{

	String _festivalVoiceCurrent;
	String _textToSayCurrent;
	
	private int amount;
	
	
	
	
public void festivalSaysText( String _festivalVoice, String textToSay){
	_festivalVoiceCurrent=_festivalVoice;
	_textToSayCurrent=textToSay;
	ExecutorService executorService = Executors.newSingleThreadExecutor();
	
	
	
	

	executorService.execute(new Runnable() {
	    public void run() {
	    	
	    	try{		
	    		
				 ArrayList<String> commands = new ArrayList<String>();
			        commands.add("festival");
			        commands.add("("+_festivalVoiceCurrent+")");
			        commands.add("(SayText \""+_textToSayCurrent+"\" )");
			        ProcessBuilder pb = new ProcessBuilder(commands);
				
				pb.start();			
				Process process = pb.start();
				
				//BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
				BufferedReader stderr = new BufferedReader(new InputStreamReader(process.getErrorStream()));

				//this waits for the process to complete
				int exitStatus = process.waitFor();

				if (exitStatus == 0) {
					//do nothing
				} else {
					String line;
					while ((line = stderr.readLine()) != null) {
						JOptionPane.showMessageDialog( null, line );
					}
				}

			} catch (Exception e1) {
				e1.printStackTrace();
			}
	    	
	    }
	});
	
	executorService.shutdown();	
	
	
	/*
	try {
	     // Wait a while for existing tasks to terminate
	     if (!executorService.awaitTermination(2, TimeUnit.SECONDS)) {
	    	 executorService.shutdown(); 
	     }
	}// Cancel currently executing tasks
	       // Wait a while for tasks to respond to being cancelled
	   catch (InterruptedException ie) {
	     // (Re-)Cancel if current thread also interrupted
		 //  executorService.shutdown();
	     // Preserve interrupt status
	     Thread.currentThread().interrupt();
	   }
	*/
	//execute();
				
}






}