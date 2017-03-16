package gamingMode;

import java.util.Comparator;

/**
 * The purpose of this class is to record Levels based data
 * @author stim599
 *
 */

public class Record implements Comparator<Record>{

	public int count;
	int level;
	int size;
	public boolean answer;

	public Record(int count, int level,int size) {
		this.count=count;
		this.level=level;
		this.size=size;
	}
	
	public Record(boolean flag, int size) {
		answer=flag;
		this.size=size;
	}
	
	public Record() {
		 //default
	}
	
	/**
	 * The purpose of this method is to compare the Record objects based on the count field
	 * method enforced by Comparator interface
	 *
	 */

	@Override
	public int compare(Record d1, Record d) {
		return d.count - d1.count;
	}

}
