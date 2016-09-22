package Sound;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class Festival {

public void festivalSaysText( String _festivalVoice, String textToSay){
	try{
		String command = "festival";
		ProcessBuilder pb = new ProcessBuilder ("/bin/bash", "-c", command);
		pb.start();
		
		
		command = "("+_festivalVoice+")";
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
		
	}
	
}
