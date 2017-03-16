package actions;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gamingMode.NewSpelling;
import gamingMode.PersonalBests;
import gamingMode.Review;
import menus.Settings;
import menus.Window;

public class QuitAction {
	
	//this method just leads the current window to that of the main menu
	public void BackToMainMenu(Object o, JPanel panel){
		//a dialog box pops up when this method is called
		Object[] options = {"Yes",
		"Cancel"};
		int num = JOptionPane.showOptionDialog(panel,
				"Are you sure you want to go back?",
				"Attention!",
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,null);
		//if yes is selected
		if(num==0){
		Window n=new Window();
		//checks from which class this method was called
		//then main menu window is shown instead of the current
		if(o instanceof NewSpelling)
		{
		((NewSpelling) o).setVisible(false);
		n.setVisible(true);
		}else if(o instanceof Review){
			((Review) o).setVisible(false);
			n.setVisible(true);
		}else if(o instanceof Settings){
			((Settings) o).setVisible(false);
			n.setVisible(true);
		}else if(o instanceof PersonalBests){
			((PersonalBests) o).setVisible(false);
			n.setVisible(true);
		}
		}
		else{
			//do nothing
		}
		
	}
	
}
