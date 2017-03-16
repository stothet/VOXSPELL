package fileManagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

/**
 * The purpose of this class is to allow the other classes to have access to the aspects of the current spelling list. 
 * It gets the size for each level from the different level files as well as the words that will be 
 * attempted and failed words files for new spelling and review.
 * @author stim599
 *
 */


public class Level {

	ArrayList<String> words = new ArrayList<String>();
	ArrayList<String> attempted = new ArrayList<String>();
	public int _score=0,size;
	private boolean getout=false;
	private String _file="";

	// getting the input from the spelling list for the specified level.
	public ArrayList<String> getInput(int lvl) throws IOException{
		_file=(new FileHandling()).setFile();
		//if file is different from default, this will be set as the absolute path to that file
		if(_file.equals("")){
			_file="./config/NZCER-spelling-lists.txt";
		}
		File f=new File(_file);
		if(f.exists()){
			String scan;
			FileReader in = new FileReader(f);
			BufferedReader br = new BufferedReader(in);
			// where we should start reading from the file
			String equate="%Level "+lvl;
			int lvl2=lvl+1;
			// end of where we should stop reading in the file
			String end="%Level "+(lvl2);
			// scan the file adding the word from the line in the file into the words arraylist
			// keep scanning until the end is reached in which case stop the buffered reader.
			while(br.ready()){
				scan=br.readLine();
				if(scan.equals(equate)){
					while(true){
						scan=br.readLine();
						if(scan.equals(end)||!br.ready()){
							getout=true;
							break;
						}
						scan=scan.trim();
						words.add(scan);
					}
				}
				if(getout){
					break;
				}
			}

		}
		else{
			return null;
		}
		// if we are wanting the first level there are only 10 words so we send all of the words in the arraylist since
		// these 10 words will always be constant
		if(lvl==1){
			return words;
		}
		else{

			// if it is not level 1, then we have to get random words from the words arraylist list and add them to the
			// attempted words arraylist until it's size has reached 10 which means we can now return this arraylist
			// corresponding to the 10 words required for the new spelling quiz.
			while(attempted.size()<10){
				int index;
				Random random=new Random();
				index=random.nextInt((words.size()-1) - 0 + 1) + 0;
				attempted.add(words.get(index));
			}
		}
		return attempted;
	}

	

	/**
	 * this method gets the size of the number of words present in the file of a certain level.
	 * it reads the corresponding level's words and adds them to the words arraylist. The size of this  
	 * arraylist is then returned.
	 *
	 */
	public int getSize(int lvl) throws IOException{
		(new FileHandling()).setFile();
		if(_file.equals("")){
			_file="./config/NZCER-spelling-lists.txt";
		}
		File f=new File(_file);
		if(f.exists()){
			String scan;
			FileReader in = new FileReader(f);
			BufferedReader br = new BufferedReader(in);
			String equate="%Level "+lvl;
			int lvl2=lvl+1;
			String end="%Level "+(lvl2);
			while(br.ready()){
				scan=br.readLine();
				if(scan.equals(equate)){
					while(true){
						scan=br.readLine();
						if(scan.equals(end)||!br.ready()){
							getout=true;
							break;
						}
						words.add(scan);
					}
				}
			}

		}
		return words.size();
	}

}

