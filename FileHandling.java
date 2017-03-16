package fileManagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import menus.Settings;

/**
 * The purpose of this class is to take care of all the actions related to a file such as writing, reading or searching actions
 * @author stim599
 *
 */

public class FileHandling {
	JFileChooser jfc = new JFileChooser();
	ArrayList<String> words = new ArrayList<String>();

	/**
	 * This method is used when a JFileChooser is needed to select a text file to change the Spelling List
	 * @return file that has been selected/set as Spelling List
	 *
	 */
	public File chooseFile(){
		//checks whether the file is a text file type by setting a filter
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt", "text files","TEXT FILES");
		jfc.setFileFilter(filter);

		//if selection has been made
		if (jfc.showOpenDialog(null)==jfc.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			if(filter.accept(file)){
				return file;
			}else{
				//dialog box appears when the selected file is not valid
				JOptionPane.showMessageDialog((new Settings()).panel,
						"Input Spelling List is not a text file",
						"Error",
						JOptionPane.ERROR_MESSAGE);
				return null;
			}
			//if selection is not made, file selection is set to a default list
		} else {
			File f=new File("./config/NZCER-spelling-lists.txt");
			return f;
		}

	}

	/**
	 * This method is used to set the class' Spelling List file selection field
	 * @return the current file that has the Spelling List
	 *
	 */
	public String setFile(){
		File f=new File("./config/file_selection");
		String _file="";
		_file=readFromFile(f);
		return _file;
	}

	/**
	 * This method stores the failed words into an arraylist
	 * @return an arraylist that stores all the failed words from the file "failed"
	 *
	 */
	public ArrayList<String> getFailed() throws IOException{
		File f=new File("./config/failed");
		String scan;
		FileReader in = new FileReader(f);
		BufferedReader br = new BufferedReader(in);
		while(br.ready()){
			scan=br.readLine();
			words.add(scan);
		}
		return words;
	}

	/**
	 * This method checks if the selected file in the JFileChooser is valid or not
	 * @return boolean: true when valid; false when invalid
	 *
	 */
	public boolean valid(File file) throws IOException{
		String scan;
		int count=0,word=0;

		boolean flag=false;
		FileReader in = new FileReader(file);
		BufferedReader br = new BufferedReader(in);
		while(br.ready()){
			scan=br.readLine();
			 //reads first character
			String f=scan.substring(0,1);
			try{
				if(f.equals("%")){
					 //reads next 5 characters into a string
					String sub=scan.substring(1, 6);
					char a=scan.charAt(7);
					if(sub.equals("Level")){
						//checks if last character is a digit
						if(Character.isDigit(a)){
							flag=true;
							count++;
						}
						 //if any of the above conditions are not met, validity is set to false
                        //and loop is exited
						else{
							flag=false;
							break;
						}
					}else{
						flag=false;
						break;
					}
				}
				else{
					//checks if any word in a level contains a number
					if((scan.matches(".*\\d+.*"))){
						flag=false;
						break;
					}
					else{
						word++;
					}
				}
			}
			//any exceptions caught will automatically mark this file as invalid
			catch(Exception e){
				flag=false;
				break;
			}
		}
		if(word<100){
			flag=false;
		}
		 //level count needs to be atleast 11
		if(count!=11){
			flag=false;
		}
		return flag;
	}

	/**
	 * This method adds a single word to a file
	 * 
	 */
	public void addToFile(String word,File file) {
		//adds a word to a file using Java I/O
		// print out all the words to the particular file
		try(FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw))
		{
			out.println(word);
		} catch (Exception e) {

		}
	}

	/**
	 * This method reads a single word from a file
	 * @return the word read from the file
	 *
	 */
	public String readFromFile(File file) {
		String s="";
		if(file.exists()){
			String scan;
			FileReader in;
			try {
				in = new FileReader(file);
				BufferedReader br = new BufferedReader(in);
				while(br.ready()){
					scan=br.readLine();
					s=scan;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return s;
	}

	/**
	 * This method updates the file "failed" by clearing all of it's contents and then adding all the 
	 * arraylist elements into the file "failed"
	 *
	 */
	public void overwrite(ArrayList<String> word){

		try {
			File fw = new File ("./config/failed");
			String command = ">./config/failed";
			ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", command);
			Process p=pb.start();
			p.waitFor();
			for(String w:word){
				addToFile(w,fw);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
