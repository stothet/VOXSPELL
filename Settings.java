package menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

import actions.QuitAction;
import fileManagement.FileHandling;
import gamingMode.Record;
import sound.Festival;

import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.HeadlessException;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
/**
 * The purpose of this class is to both display the settings window as well as allow the user to clear * all of the
 * files which store any previous game play information, choose a file to replace the current spelling * list and also choose a festival voice from 2 options.
 * @author stim599
 *
 */
public class Settings extends JFrame implements ActionListener{
	private static final String OK = "";
	// adding in any required buttons to the GUI
	private JButton btnV = new JButton("Back");
	private JButton btnS = new JButton("Clear");
	public JPanel panel = new JPanel();
	private JComboBox<String> comboBox = new JComboBox<String>();
	public String _festivalVoice;
	private String _voice;

	// constructor makes sure that the correct buttons and fields are added to the window frame and that they have the
	// correct font with size and colours and so on.
	// the action listeners for these are also added.
	public Settings(){

		panel.setBackground(new Color(0, 128, 128));
		panel.setPreferredSize(new Dimension(600,600));
		panel.setLayout(null);
		btnV.setBounds(12, 12, 94, 25);
		panel.add(btnV);
		btnS.setFont(new Font("Dialog", Font.BOLD, 18));
		btnS.setBounds(395, 118, 117, 43);
		panel.add(btnS);
		btnV.addActionListener(this);
		btnS.addActionListener(this);

		getContentPane().add(panel);

		JLabel lblSelectLevel = new JLabel("Clear");
		lblSelectLevel.setForeground(new Color(255, 255, 0));
		lblSelectLevel.setFont(new Font("Purisa", Font.BOLD, 25));
		lblSelectLevel.setBounds(40, 136, 262, 25);
		panel.add(lblSelectLevel);

		JLabel lblNewLabel = new JLabel("Select Voice");
		lblNewLabel.setFont(new Font("Purisa", Font.BOLD, 25));
		lblNewLabel.setForeground(new Color(255, 255, 0));
		lblNewLabel.setBounds(40, 262, 272, 56);
		panel.add(lblNewLabel);
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Nasser", "Mark"}));
		//comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"voice_rab_diphone", "voice_kal_diphone"}));

		 //window listener to show a pop up when user tries to close the application
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //code obtained from http://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
                Object[] options = {"Yes, Please",
                "No I want to stay!"};
                int num = JOptionPane.showOptionDialog(panel,
                        "Are you sure you want to leave already?",
                        "Attention!",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,null);
                //if yes
                if(num==0){
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                //or not
                else{
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });


		comboBox.setBounds(343, 281, 176, 24);
		panel.add(comboBox);
		JButton btnNewButton = new JButton("Apply Changes");
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 18));
		btnNewButton.setBounds(331, 354, 199, 43);
		panel.add(btnNewButton);

		JLabel lblChooseWordList = new JLabel("Choose Word List");
		lblChooseWordList.setFont(new Font("Purisa", Font.BOLD, 25));
		lblChooseWordList.setForeground(Color.YELLOW);
		lblChooseWordList.setBounds(40, 473, 310, 43);
		panel.add(lblChooseWordList);

		JButton btnNewButton_1 = new JButton("Choose");
		btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 18));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean ans=false;
				//a filechooser appears and asks the user to pick a text file
				FileHandling sf=new FileHandling();
				File fs=sf.chooseFile();
				File file2 = new File ("./config/customList");
				try {
					ans = sf.valid(fs);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					//if valid
					if(ans){
						// file is first cleared
                        // file selection is later updated
						bashCommands(">./config/file_selection");
						// choice from the drop down menu is added to the voice choice file
						File file = new File ("./config/file_selection");
						(new FileHandling()).addToFile(fs.getPath(),file);
						 //all previous data with the older list is cleared
						clear();
					}
					//otherwise a dialog box is shown
					else{
						JOptionPane.showMessageDialog(panel,
								"Input Spelling List does not match format. Please choose another\n"
										+ "Format is:\n"
										+ "%Level 1\n"
										+ "words\n"
										+ "%Level 2\n"
										+ ".\n"
										+ ".\n"
										+ ".\n"
										+ "%Level 11\n"
										+ "words again\n",
										"Error",
										JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(414, 475, 117, 43);
		panel.add(btnNewButton_1);
		btnNewButton.addActionListener(this);
		pack();

		setTitle("Settings for all your tinkering");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	}


	public void clear() throws FileNotFoundException{

		// This method clears all of the files where any of the previous game play information is stored.
		// so the failed words and attempted words files are cleared as well as the voice choice file.
		// along with this the stats being stored in each of the level files are also cleared.
		//Custom button text

		bashCommands(">./config/failed");
		bashCommands(">./config/voice");
		bashCommands(">./config/file_selection");
		File file = new File ("./config/file_selection");
		(new FileHandling()).addToFile("./NZCER-spelling-lists.txt", file);
		for(int i=1;i<=11;i++){
			bashCommands(">./config/Level"+i+"");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {


		// if the back button is pressed, go back to displaying the main menu GUI
		if(e.getActionCommand().equals("Back")){
			(new QuitAction()).BackToMainMenu(this, panel);
		}

		// if the clear button is pressed, call the clear method to clear all of the files
		else if(e.getActionCommand().equals("Clear")){
			try {
				Object[] options = {"Yes, please",
				"No, thanks"};
				int n = JOptionPane.showOptionDialog(panel,
						"Are you sure you want to clear your progress?",
						"Attention!",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options,null);
				if(n==0){
					clear();
					JOptionPane.showMessageDialog(panel,
							"Your statistics have been cleared");
				}
			}
			catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}

		}
		// to change the festival voice, the apply changes button is pressed once selecting a voice from the drop down menu.
		// this is then added to the voice file for future reference.
		else if(e.getActionCommand().equals("Apply Changes")){
			bashCommands(">./config/voice");
			// choice from the drop down menu is added to the voice choice file
			String _choice = (String)comboBox.getSelectedItem();
			String voice;
			if(_choice.equals("Sam")){
				voice = "voice_rab_diphone";
			}else{
				voice = "voice_kal_diphone";
			}
			File file = new File ("./config/voice");
			(new FileHandling()).addToFile(voice,file);
			// the field is set with the current choice and a preview is given to the user
			_festivalVoice=voice;
			sayVoice("Hi, I am"+_choice);
		}
	}

	// method allows for a preview to be said to the user of the voice choice they have made.
	// this is done through sending a sample string to the text to say festival method in the festival class.
	public void sayVoice(String voice){
		Festival textToSay=new Festival();
		textToSay.festivalSaysText(_festivalVoice,voice);
	}

	// this method sends the string that needs to be said along with the currently chosen festival voice which is read in
	// from the voice choice file and these are then sent as inputs into the festivalSaysText in the festival class.
	public void say(String first){
		Festival textToSay=new Festival();
		File f=new File("./config/voice");
		_voice=(new FileHandling()).readFromFile(f);
		textToSay.festivalSaysText(_voice,first);
	}

	//this method runs a single bash command
	public void bashCommands(String c){
		ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", c);
		try {
			pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}