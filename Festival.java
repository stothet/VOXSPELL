package sound;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JOptionPane;

/**
 * The purpose of this class is to take any of the festival strings that need to be said by festival and then using
 * bash processes, say them with the chosen festival voice.
 * executor service is used in this method as a way of making sure the GUI doesn't freeze when any festival commands
 * are being read out.
 * @author stim599
 *
 */

public class Festival{

	// fields for the aspects of the festival. This includes one of the two voice choices for festival as well as the
	// string that needs to be said by festival
	String _festivalVoiceCurrent;
	String _textToSayCurrent;


	public void festivalSaysText( String _festivalVoice, String textToSay){
		// setting the field and string variable to the local fields
		_festivalVoiceCurrent=_festivalVoice;
		_textToSayCurrent=textToSay;
		// using an executor service thread so that only one single thread is used and any of the added jobs to festival create
		// a queue on this one thread. This is so there is no overlap of festival calls.
		ExecutorService executorService = Executors.newSingleThreadExecutor();




		// run method conducts the bash commands on the single executor thread
		executorService.execute(new Runnable() {
			public void run() {

				try{		

					// arraylist of process that need to occur one by one so that the festival string is said out with the
					// current chosen festival voice
					ArrayList<String> commands = new ArrayList<String>();
					commands.add("festival");
					commands.add("("+_festivalVoiceCurrent+")");
					commands.add("(SayText \""+_textToSayCurrent+"\" )");
					ProcessBuilder pb = new ProcessBuilder(commands);

					pb.start();			
					Process process = pb.start();

					BufferedReader stderr = new BufferedReader(new InputStreamReader(process.getErrorStream()));

					// wait for makes sure that the festival has completed before going onto the next festival command. This
					// makes sure that there is no overlap of festival commands
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

		// the single executor service thread then shuts down after the festival string has been said. It becomes live again
		// when this class is called from the other classes.
		executorService.shutdown();	

	}
	

}