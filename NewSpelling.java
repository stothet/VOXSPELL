package gamingMode;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import actions.QuitAction;
import fileManagement.FileHandling;
import fileManagement.Level;
import menus.Settings;
import menus.Window;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.Color;


/**
 * The purpose of this class is to allow for the new spelling quiz play through. The actual game play is present in this
 * class as well as the visual aspect of the GUI.
 * @author stim599
 *
 */
public class NewSpelling extends JFrame implements ActionListener,KeyListener{
	// creating new spelling quiz GUI with all of the buttons, images, fonts, sizes, colours etc.
	// also adding in the action listeners for these buttons

	private boolean done=false;
	public JButton btnN = new JButton("Submit");
	private EmbeddedMediaPlayerComponent mediaComponent;
	public EmbeddedMediaPlayer audio;
	private JButton btnV = new JButton("Back to Main Menu");
	private JButton btnR = new JButton("");
	private JButton btnS = new JButton("Restart Level");
	private JLabel lblNewLabel_4 = new JLabel("");
	private JPanel panel = new JPanel();
	private JTextField textField;
	private JButton btnNewButton_1, btnNewButton;
	public ArrayList<String> words,failed;
	public ArrayList<String> mastered=new ArrayList<String>();
	private int track=0,_wordCount=1,score=0,level;
	private JLabel lblSelectLevel = new JLabel();
	private JLabel lblNewLabel = new JLabel("Score: 0");
	private JLabel lblNewLabel_1 = new JLabel();
	private final JLabel lblNewLabel_2 = new JLabel("Listen Again");
	private final JLabel lblNewLabel_5 = new JLabel("");
	private final JTextArea textArea = new JTextArea();
	private final JLabel lblNewLabel_7 = new JLabel("");
	private String _character,s;
	private final JButton btnNewButton_2 = new JButton("Audio Reward");

	// creating the visual and adding action listeners
	public NewSpelling(ArrayList<String> words, int level){

		//getting character for this window
		File fi=new File("./config/character");
		_character=(new FileHandling()).readFromFile(fi);

		setBackground(new Color(250, 250, 210));
		s=words.get(0);
		this.level=level;

		Level l=new Level();

		this.words=words;
		panel.setBackground(new Color(224, 255, 255));
		panel.setPreferredSize(new Dimension(800, 700));
		panel.setLayout(null);
		lblSelectLevel.setIcon(null);
		lblSelectLevel.setForeground(new Color(139, 0, 0));
		lblSelectLevel.setFont(new Font("Purisa", Font.BOLD, 27));

		lblSelectLevel.setBounds(121, 417, 272, 183);
		panel.add(lblSelectLevel);


		textField = new JTextField();
		textField.setBounds(121, 346, 272, 46);
		panel.add(textField);
		textField.setColumns(10);
		textField.requestFocusInWindow();
		btnN.setForeground(new Color(255, 255, 224));
		btnN.setBackground(new Color(65, 105, 225));
		btnN.setFont(new Font("Purisa", Font.BOLD, 20));
		btnN.setBounds(525, 340, 184, 54);
		//adding buttons
		panel.add(btnN);
		btnN.addActionListener(this);
		btnR.setBackground(new Color(255, 255, 255));
		btnR.setIcon(new ImageIcon("./resources/play.png"));
		btnR.setBounds(655, 456, 54, 54);
		panel.add(btnR);
		btnV.setForeground(new Color(255, 255, 224));
		btnV.setBackground(new Color(65, 105, 225));
		btnV.setFont(new Font("LM Roman 9", Font.BOLD, 16));
		btnV.setBounds(12, 11, 236, 45);
		panel.add(btnV);
		btnS.setForeground(new Color(255, 255, 224));
		btnS.setBackground(new Color(65, 105, 225));
		btnS.setFont(new Font("Purisa", Font.BOLD, 17));
		btnS.setBounds(12, 634, 170, 54);
		btnS.setVisible(false);
		panel.add(btnS);
		btnR.addActionListener(this);
		btnV.addActionListener(this);
		btnS.addActionListener(this);

		 //setting up audio functionality to play an audio reward (song)
		mediaComponent = new EmbeddedMediaPlayerComponent();
		mediaComponent.setBounds(0, 0, 10, 10);
		mediaComponent.getVideoSurface().setBounds(-11, 0, 1196, 721);
		panel.add(mediaComponent);
		audio = mediaComponent.getMediaPlayer();
		panel.add(mediaComponent);


		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				 //plays the song
				audio.playMedia(".song.mp3");

			}
		});

		btnNewButton_2 .setVisible(false);

		getContentPane().add(panel, BorderLayout.SOUTH);
		btnNewButton = new JButton("Video Reward");
		btnNewButton.setForeground(new Color(255, 255, 224));
		btnNewButton.setBackground(new Color(65, 105, 225));
		btnNewButton.setFont(new Font("Purisa", Font.BOLD, 18));
		btnNewButton.setBounds(194, 634, 184, 54);
		panel.add(btnNewButton);

		btnNewButton_1 = new JButton("Next Level");
		btnNewButton_1.setForeground(new Color(255, 255, 224));
		btnNewButton_1.setBackground(new Color(65, 105, 225));
		btnNewButton_1.setFont(new Font("Purisa", Font.BOLD, 18));
		btnNewButton_1.setBounds(617, 634, 156, 54);
		panel.add(btnNewButton_1);
		btnNewButton_1.setVisible(false);
		btnNewButton.setVisible(false);
		lblNewLabel.setForeground(new Color(128, 0, 0));
		lblNewLabel.setFont(new Font("LM Roman Dunhill 10", Font.BOLD, 25));

		lblNewLabel.setBounds(683, 32, 145, 46);
		panel.add(lblNewLabel);
		lblNewLabel_1.setBounds(464, 342, 70, 15);

		panel.add(lblNewLabel_1);
		lblNewLabel_2.setForeground(new Color(128, 0, 0));
		lblNewLabel_2.setFont(new Font("L M Roman Caps10", Font.BOLD, 21));
		lblNewLabel_2.setBounds(617, 522, 194, 37);

		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(586, 126, 225, 49);
		panel.add(lblNewLabel_3);

		 //writes the label that describes what level we are on
		for (Files f : Files.values()) {
			if(f.value==level){
				lblNewLabel_3.setText(f.name);
			}
		}

		 //window listener to show a pop up when user tries to close the application
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //code obtained from http://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
                Object[] options = {"Yes, Please",
                "No I want to stay!"};
                int num = JOptionPane.showOptionDialog(panel,
                        "Are you sure you want to leave this game already?",
                        "Attention!",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,null);
                //if yes is selected
                if(num==0){
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                //otherwise
                else{
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

		lblNewLabel_3.setForeground(new Color(128, 0, 0));
		lblNewLabel_3.setFont(new Font("L M Roman Caps10", Font.BOLD, 25));

		lblNewLabel_4.setText("Words left to spell: "+ words.size());
		lblNewLabel_4.setFont(new Font("LM Roman Dunhill 10", Font.BOLD, 25));
		lblNewLabel_4.setForeground(new Color(139, 0, 0));
		lblNewLabel_4.setBounds(544, 70, 244, 60);
		panel.add(lblNewLabel_4);
		lblNewLabel_5.setIcon(null);
		lblNewLabel_5.setBounds(58, 68, 156, 254);
		lblNewLabel_5.setIcon(new ImageIcon("./resources/"+_character+"_2.png"));

		panel.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setBounds(122, 187, 70, 15);
		panel.add(lblNewLabel_6);
		textArea.setBounds(273, 54, 215, 105);
		textArea.setEditable(false);
		textArea.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		panel.add(textArea);
		 //textArea corresponds to the speech bubble of the character
		textArea.append("Click Submit whenever ready,\nplay button to listen to the\nword again and back button\nto go to main menu.");

		try {
			failed=(new FileHandling()).getFailed();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		lblNewLabel_7.setIcon(new ImageIcon("./resources/bubble.png"));
		lblNewLabel_7.setBounds(220, 22, 314, 191);

		panel.add(lblNewLabel_7); 
		pack();
		panel.getRootPane().setDefaultButton(btnN);
		btnNewButton_2.setForeground(new Color(255, 255, 224));
		btnNewButton_2.setBackground(new Color(65, 105, 225));
		btnNewButton_2.setFont(new Font("Purisa", Font.BOLD, 18));
		btnNewButton_2.setBounds(408, 634, 184, 54);

		panel.add(btnNewButton_2);
		btnNewButton.addActionListener(this);
		btnNewButton_1.addActionListener(this);
		textField.requestFocusInWindow();

		setTitle("New Quiz, Good Luck!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLocationRelativeTo(null);
		setResizable(false);
	}


	public String getWord(){
		// track is the field which keeps track of what word out of the 10 in the words arraylist we are currently upto.
		// so this method simply returns the current word being tested.
		String w=words.get(track);
		return w;
	}

	@Override
	public void actionPerformed(ActionEvent e) {


		if(e.getActionCommand().equals("Back to Main Menu")){
			// if back button is pressed, go back to main menu by setting this as visible and setting current window as
			// invisible.
			if(audio.isPlaying()){
				audio.pause();
			}
			(new QuitAction()).BackToMainMenu(this, panel);

		}

		else if(e.getSource().equals(btnR)){
			// say the current word again
			(new Settings()).say(s);

		}

		else if(e.getActionCommand().equals("Video Reward")){
			// run the vlcj method to run the window for the video when the play video reward button is pressed
			NativeLibrary.addSearchPath(
					RuntimeUtil.getLibVlcLibraryName(), "/Applications/vlc-2.0.0/VLC.app/Contents/MacOS/lib"
					);
			Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);

			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					new Video();
				}
			});

		}

		else if(e.getActionCommand().equals("Try Again")){
			//submit button is set to invisible, try again button is set to visible
			// error message is sent saying there is incorrect answer format
			btnR.setVisible(false);
			lblNewLabel_2.setVisible(false);
			if(textField.getText().isEmpty()||!(textField.getText().matches("[a-zA-Z]+"))){
				JOptionPane.showMessageDialog( null, "Please type in letters of the alphabet, i.e a-z or A-Z" );
			}
			else{
				this.failed(s);
				textField.setText(null);
			}

		}

		else if(e.getActionCommand().equals("Restart Level")){
			// when practice is pressed, get the current level words again and store it into the words arraylist and
			// then show the new spelling GUI again and then say the first word in the arraylist.
			Level l=new Level();
			try {
				words=l.getInput(level);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			NewSpelling r=new NewSpelling(words,level);
			this.setVisible(false);
			r.setVisible(true);
			(new Settings()).say("Please, spell.,\n\n\n"+r.words.get(0)+";,.");

		}

		else if(e.getActionCommand().equals("Next Level")){
			// when next level button is pressed, increase the level field and then get the words for this next level
			// by using the IO of the level class. 
			// Then store these words into the words arraylist and then say the first word.
			level++;
			Level l=new Level();
			try {
				words=l.getInput((level));
			} catch (NumberFormatException | IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			NewSpelling r=new NewSpelling(words,level);
			this.setVisible(false);
			r.setVisible(true);
			(new Settings()).say("Please, spell.,\n\n\n"+r.words.get(0)+";,.");

		}
		else if(e.getActionCommand().equals("Submit")){

			// check the spelling
			if(textField.getText().isEmpty()||!(textField.getText().matches("[a-zA-Z]+"))){
				JOptionPane.showMessageDialog( null, "Sorry, numbers and symbol do not match this word" );
			}
			else{
				//the submit button basically checks if the spelling is right
				// send the word into the check method to check if spelling is correct
				this.check(s);
				textField.setText(null);
			}

		}
	}
	
	/**
	 * This method basically checks the spelling of the user at their first attempt
	 *
	 * 
	 */
	public void check(String word) {

		Level l=new Level();
		String ans= textField.getText();
		_wordCount++;
		lblNewLabel_4.setText("Words left to spell: "+(words.size()-_wordCount+1));
		//checks for first attempt
		// checks if the answer is correct
		if(ans.equalsIgnoreCase(word)){

			lblSelectLevel.setText("Fantastic!");
			// score for current level increases
			score++;
			lblNewLabel.setText("Score: "+score);
			textField.setText(null);
			if(!mastered.contains(s)){
				mastered.clear();
				for (Files f : Files.values()) {
					if(f.value==level){
						// write the word out to the particular level file
						File fw=new File(f.file);
						(new FileHandling()).addToFile(word, fw);
					}
				}
			}

			// set button to say next word
			lblNewLabel_2.setVisible(false);
			textField.setVisible(false);
			btnR.setVisible(false);
			textField.setVisible(true);
			textArea.setText(null);
			btnR.setVisible(true);
			textArea.setText(null);
			textArea.append("Good going!!\nAt the end of this level, if\nyou get a score of atleast\n9/10 you will be presented\nwith a special reward.Exciting! ");
			lblNewLabel_2.setVisible(true);
			lblSelectLevel.setText("");
			nextWord("Fantastic!!! Please, spell.,\n\n\n",_wordCount,score);
			textField.setText(null);
			textField.requestFocusInWindow();
		}
		else{
			// if its not correct on first attempt say try once more
			lblSelectLevel.setFont(new Font("Purisa", Font.BOLD, 25));
			(new Settings()).say("Oops! Try again,"+ "  .,\n\n\n" + ",  ;,.\n\n    "+ word +",  ;,.         \n " + word+";,.");
			lblSelectLevel.setText("Oops! Try Again");
			btnN.setText("Try Again");
			textArea.setText(null);
			if(!done){
				textArea.append("You will get\nanother chance when you get\na word wrong the first time");
				done=true;
			}else{
				textArea.append("And now you are good to go!\nAt the end of this level, if\nyou get a score of atleast\n9/10 you will be presented\nwith a special reward.Exciting! ");	
			}
		}

	}

	/**
	 * This method basically checks the spelling of the user at their second attempt
	 *
	 * 
	 */
	public void failed(String word) {
		//similar to check, failed gives the user a second chance after try again is clicked on
		String ans= textField.getText();
		if(ans.equalsIgnoreCase(word)){
			//if answer is right on second try, word is added to faulted file
			lblSelectLevel.setFont(new Font("Purisa", Font.BOLD, 25));
			lblSelectLevel.setText("Good Job!");
			nextWord("Good Job ;;;;;;''''',,,,...  Please, spell.,\n\n\n",_wordCount,score);
		}
		else{
			lblSelectLevel.setFont(new Font("Purisa", Font.BOLD, 20));
			nextWord("Sorry, \n\nThat's not quite right ;; ,,,, Please, spell.;;;,\n\n\n",_wordCount,score);
			lblSelectLevel.setText("Sorry, \n\nThat's not quite right");
			//if word is failed on both tries, it is added to failed file (for review) and original file (for viewing statistics)
			if(!failed.contains(word)){
				File fw = new File ("./config/failed");
				(new FileHandling()).addToFile(word, fw);
			}
		}
		lblNewLabel_2.setVisible(false);
		textField.setVisible(false);
		btnR.setVisible(false);
		textField.setVisible(true);
		textArea.setText(null);
		btnR.setVisible(true);

		textArea.append("And now you are good to go!\nAt the end of this level, if\nyou get a score of atleast\n9/10 you will be presented\nwith a special reward.Exciting! ");

		lblNewLabel_2.setVisible(true);
		lblSelectLevel.setText("");
		textField.setText(null);
		textField.requestFocusInWindow();
		//submit button is then made visible, try again is invisible again
	}

	/**
	 * This method is reached if level has been completed that is, the user has gotten 9 or more words
	 * mastered
	 *
	 * 
	 */
	public void endOfGame(){
		// end of game if level has been completed meaning user has gotten 9 or more words mastered
		lblNewLabel_2.setVisible(false);
		btnNewButton.setVisible(true);
		if(level!=11){
			btnNewButton_1.setVisible(true);
		}
		btnNewButton_2 .setVisible(true);
		btnS.setVisible(true);


		btnN.setVisible(false);
		btnR.setVisible(false);

		 //plays audio file based on character
		lblSelectLevel.setText("Pick an option");
		String command = "mpg123 ."+_character+".mp3";
		(new Settings()).bashCommands(command);
		lblSelectLevel.setFont(new Font("Purisa", Font.BOLD, 25));
	}

	/**
	 *This method is called when the user has spelt the given word correctly or has used up all their 
	 *chances. It makes a festival call to say the next word.
	 */
	public void nextWord(String message, int _wordCount, int score ){
		if(_wordCount==11 && score>8){
			// if we have reached the end of level meaning the 10 words have been spelt and the score is 9 or more
			// meaning end of level has been reached and so user needs to be asked to watch video reward, to practice
			// the level more or go to next level.
			textArea.setText(null);
			textArea.append(" What would you like to do\n next?");
			endOfGame();

		}
		else if(_wordCount==11){
			// if we have reached the end of the level but user didnt get 9 or more correct, meaning we go back to the
			// main menu as user has not completed the level.
			JOptionPane.showMessageDialog(panel,
					"Sorry! Try this level again");
			Window n=new Window();
			this.setVisible(false);
			n.setVisible(true);
		}
		else{
			// increase the track field stating we have gone to the next word and we say this next word by calling the
			// getWord method which then gets the next word to say according to the track.
			// the button is then set to submit.
			track++;
			s=getWord();
			(new Settings()).say(message+s+";,.");
			btnN.setText("Submit");
		}
		textField.setText(null);

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		//enter does the same functionality as clicking on btnN
		if (e.getKeyCode()==KeyEvent.VK_ENTER){
			btnN.doClick();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}