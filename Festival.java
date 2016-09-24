package Sound;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class Festival  extends SwingWorker<Void,Void>{

	String _festivalVoiceCurrent;
	String _textToSayCurrent;
	
public void festivalSaysText( final String _festivalVoice, String textToSay){
	_festivalVoiceCurrent=_festivalVoice;
	_textToSayCurrent=textToSay;
	
	execute();
				
}

@Override
protected Void doInBackground() throws Exception {
	try{
		
		String o="hello";
		String command = "echo "+o+" | festival --tts";
		ProcessBuilder pb = new ProcessBuilder ("/bin/bash", "-c", command);
		
			pb.start();
		
		
		
		command = "("+_festivalVoiceCurrent+")";
		pb = new ProcessBuilder (command);
		pb.start();
		
		
		command = "(SayText \"hello\" )";
		pb = new ProcessBuilder(command);
		pb.start();
		
		
		command = "(quit)";
		pb = new ProcessBuilder(command);
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
	return null;
}
}
