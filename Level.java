package Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class Level {

	ArrayList<String> words = new ArrayList<String>();
	ArrayList<String> attempted = new ArrayList<String>();
	//ArrayList<String> vstats = new ArrayList<String>();
	public int _score=0,vstats;
	private boolean getout=false;;
	//HashMap<String, Integer> _stats = new HashMap<String, Integer>();

	public enum Levels{
		level1("./Level1",1,"Level 1"),level2("./Level2",2,"Level 2"),level3("./Level3",3,"Level 3"),level4("./Level4",4,"Level 4"),level5("./Level5",5,"Level 5"),level6("./Level6",6,"Level 6"),level7("./Level7",7,"Level 7"),level8("./Level8",8,"Level 8"),level9("./Level9",9,"Level 9"),level10("./Level10",10,"Level 10"),level11("./Level11",11,"Level 11");
		public String file;
		public int value;
		public String name;

		Levels(String file, int value,String name) {
			this.value = value;
			this.file = file;
			this.name = name;
		}
	}

	public ArrayList<String> getInput(int lvl) throws IOException{
		File f=new File("./NZCER-spelling-lists.txt");
		if(f.exists()){
			String scan;
			FileReader in = new FileReader(f);
			BufferedReader br = new BufferedReader(in);
			String equate="%Level "+lvl;
			lvl++;
			String end="%Level "+(lvl);
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
				if(getout){
					break;
				}
			}
		}
		else{
			return null;
		}
		while(attempted.size()<10){
			int index;
			Random random=new Random();
			index=random.nextInt((words.size()-1) - 0 + 1) + 0;
			attempted.add(words.get(index));
			try(FileWriter fw = new FileWriter("./attemptedwords", true);
					BufferedWriter bw = new BufferedWriter(fw);
					PrintWriter out = new PrintWriter(bw))
			{
				out.println(words.get(index));
			} catch (Exception e) {

			}
		}
		return attempted;
	}

	public void updateStats(int count,File file){
		try(FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw))
		{
			out.println(count);
		} catch (Exception e) {

		}
	}

	public void getFailed() throws IOException{
		words.clear();
		File f=new File("./.failed");
		if(f.exists()){
			String scan;
			FileReader in = new FileReader(f);
			BufferedReader br = new BufferedReader(in);
			while(br.ready()){
				scan=br.readLine();
				words.add(scan);
			}
		}
	}

	public int getStats(int level, ArrayList<String> input) throws IOException{
		
		String foo=null;
		
		for (Levels f : Levels.values()) {
			if(f.value==level){
				foo=f.file;
			}
		}

		String scan;
		FileReader in = new FileReader(foo);
		BufferedReader br = new BufferedReader(in);
		while(br.ready()){
			scan=br.readLine();
			vstats=Integer.parseInt(scan);
		}
		
		return vstats/(input.size());
	}
	
	public int get_Stats(int level, ArrayList<String> input) throws IOException{
		
		String foo=null;
		
		for (Levels f : Levels.values()) {
			if(f.value==level){
				foo=f.file;
			}
		}

		String scan;
		FileReader in = new FileReader(foo);
		BufferedReader br = new BufferedReader(in);
		while(br.ready()){
			scan=br.readLine();
			vstats=Integer.parseInt(scan);
		}
		
		return vstats/(input.size());
	}
}
