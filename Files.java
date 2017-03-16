package gamingMode;

/**
 * This enum is created for all the Levels, their values, names and file locations to store mastered 
 * words, for easier file management in the application
 *
 * @author stim599
 */
public enum Files{

	level1("./config/Level1",1,"Level 1"),level2("./config/Level2",2,"Level 2"),level3("./config/Level3",3,"Level 3"),level4("./config/Level4",4,"Level 4"),level5("./config/Level5",5,"Level 5"),level6("./config/Level6",6,"Level 6"),level7("./config/Level7",7,"Level 7"),level8("./config/Level8",8,"Level 8"),level9("./config/Level9",9,"Level 9"),level10("./config/Level10",10,"Level 10"),level11("./config/Level11",11,"Level 11");
	public String file,name;
	public int value;

	// constructor allows the copying of the values of both file and value into fields.
	private Files(String file, int value, String name) {
		this.value = value;
		this.file = file;
		this.name = name;
	}
}
