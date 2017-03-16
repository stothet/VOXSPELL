package gamingMode;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import actions.QuitAction;
import fileManagement.FileHandling;
import gameRendering.gameReward;
import gameRendering.setGame;
import menus.Settings;
import menus.Window;
import sound.Festival;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.Color;


/**
 * The purpose of this class is to provide the user with a review option where they are able to spell words that
 * that they had failed in previous new spelling quizzes. So this class obtains all of the failed words and asks
 * the user to spell out each of these words one by one until there are no more words failed.
 * The user can also exit out of this function at any time by pressing the back button
 * @author stim599
 *
 **/

public class Review extends JFrame implements ActionListener,KeyListener{
	
	//makes a field to render the game reward
	public static gameReward flappyBird;
	
	// creating the review visual GUI with the required buttons, font and colour scheme.
	private JButton btnN = new JButton("Submit");
	private JButton btnV = new JButton("Back to Main Menu");
	private JButton btnR = new JButton("");
	JPanel panel = new JPanel();
	private JTextField textField;
	JButton btnNewButton;
	public ArrayList<String> failed;
	private ArrayList<String> failed_copy=new ArrayList<String>();
	private int track=0;
	String s;
	JLabel lblSelectLevel = new JLabel();
	private int _count=0;
	private final JLabel lblNewLabel = new JLabel("");
	private final JTextArea textArea = new JTextArea();
	JLabel lblRelisten = new JLabel("Listen Again");
	private String _character;

	// creating the visual for review and adding in the action listeners for the buttons
	public Review(ArrayList<String> words){
		
		File fi=new File("./config/character");
        _character=(new FileHandling()).readFromFile(fi);
		
		setBackground(new Color(250, 250, 210));

		failed=words;

		for(String ww: words){
			failed_copy.add(ww);
		}
		panel.setBackground(new Color(224, 255, 255));
		s=failed.get(0);
		panel.setPreferredSize(new Dimension(800, 700));
		panel.setLayout(null);
		btnN.setBackground(new Color(65, 105, 225));
		btnN.setForeground(new Color(255, 255, 224));
		btnN.setFont(new Font("Purisa", Font.BOLD, 18));
		btnN.setBounds(606, 324, 168, 54);
		//adding buttons
		panel.add(btnN);
		btnN.addActionListener(this);
		btnR.setBackground(new Color(255, 255, 255));
		btnR.setIcon(new ImageIcon("./resources/play.png"));
		btnR.setBounds(662, 453, 55, 54);
		panel.add(btnR);
		btnV.setForeground(new Color(255, 255, 224));
		btnV.setBackground(new Color(65, 105, 225));
		btnV.setFont(new Font("LM Roman 9", Font.BOLD, 16));
		btnV.setBounds(12, 12, 238, 37);
		panel.add(btnV);
		
		btnR.addActionListener(this);
		btnV.addActionListener(this);
		
		 //window listener to show a pop up when user tries to close the application
        addWindowListener(new WindowAdapter() {
              public void windowClosing(WindowEvent e) {
                   //code obtained from http://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
                  Object[] options = {"Yes, Please",
                    "No I want to stay!"};
                    int num = JOptionPane.showOptionDialog(panel,
                            "Are you sure you want to leave the game already?",
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

		getContentPane().add(panel, BorderLayout.SOUTH);
		lblSelectLevel.setForeground(new Color(128, 0, 0));
		lblSelectLevel.setFont(new Font("Purisa", Font.BOLD, 25));

		lblSelectLevel.setBounds(12, 411, 526, 171);
		panel.add(lblSelectLevel);

		btnNewButton = new JButton("Play a Game");
		btnNewButton.setForeground(new Color(255, 255, 224));
		btnNewButton.setBackground(new Color(65, 105, 225));
		btnNewButton.setFont(new Font("Purisa", Font.BOLD, 20));
		btnNewButton.setBounds(211, 616, 194, 52);
		panel.add(btnNewButton);
		btnNewButton.setVisible(false);

		textField = new JTextField();
		textField.setBounds(133, 330, 310, 46);
		panel.add(textField);
		textField.setColumns(10);

		
		lblRelisten.setFont(new Font("Purisa", Font.BOLD, 20));
		lblRelisten.setForeground(new Color(128, 0, 0));
		lblRelisten.setBounds(624, 519, 150, 46);
		panel.add(lblRelisten);
		lblNewLabel.setIcon(null);
		lblNewLabel.setBounds(278, 52, 203, 251);
		lblNewLabel.setIcon(new ImageIcon("./resources/"+_character+"_2.png"));
		
		panel.add(lblNewLabel);
		textArea.setBounds(473, 41, 232, 105);
		textArea.setEditable(false);
		textArea.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		panel.add(textArea);
		//textArea corresponds to the speech bubble of the character
		textArea.append("   Just like New Spelling,\n   click Submit whenever\n   ready, play button to listen\n   to the word again and \n   back button to go to\n   main menu.");
		pack();

		textField.requestFocusInWindow();
        panel.getRootPane().setDefaultButton(btnN);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon("./resources/bubble.png"));
        lblNewLabel_1.setBounds(426, 12, 325, 183);
        panel.add(lblNewLabel_1);
		setTitle("Review! it's never too late to improve");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		btnNewButton.addActionListener(this);


	}

	// method returns the current word being tested through the use of the track field.
	public String getWord(){
		String w=failed.get(track);
		return w;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Back to Main Menu")){
			// if back button is pressed go back to the main menu and close this current window
			(new QuitAction()).BackToMainMenu(this, panel);

		}

		else if(e.getSource().equals(btnR)){
			// if relisten is pressed say the word again
			(new Settings()).say(s);

		}

		else if(e.getActionCommand().equals("Video")){
			// call the video method which then runs the video window which plays the video with other options
			// such as play/pause, mute and the option to view another video reward.
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
			if(textField.getText().isEmpty()||!(textField.getText().matches("[a-zA-Z]+"))){
				JOptionPane.showMessageDialog( null, "Please type in letters of the alphabet, i.e a-z or A-Z" );
			}
			else{
				// call the reviewSecondChance method to check for the spelling in the users second attempt
				this.reviewSecondChance(s);
				textField.setText(null);
			}

		}

		else if(e.getActionCommand().equals("Submit")){

			_count++;
			// count increases and the check method is called to check the spelling of the word.
			if(textField.getText().isEmpty()||!(textField.getText().matches("[a-zA-Z]+"))){
				JOptionPane.showMessageDialog( null, "Can only accept characters A-Z or a-z" );
			}
			else{
				//the submit button basically checks if the spelling is right
				this.check(s);
				textField.setText(null);
			}

		}
		
		else if(e.getActionCommand().equals("Play a Game")){

			 //Clicking on this button will display a game
            //instructions will be visible
			setGame n=new setGame();
			this.setVisible(false);
			n.setVisible(true);

		}
	}

	/**
	 * This method basically checks the spelling of the user at their first attempt
	 *
	 * 
	 */
	public void check(String word) {
	String ans= textField.getText();
		//checks for first attempt
		// check the first attempt spelling of the word
		if(ans.equalsIgnoreCase(word)){
			// remove the word from the failed list if it has been mastered
			failed_copy.remove(failed_copy.indexOf(word));
			textField.setText(null);
			lblSelectLevel.setText("Amazing");
			textArea.setText(null);
			textArea.append("   You've got it!, now just like\n   the Spelling Quiz you have an\n   even more special reward waiting\n   for you but you will have\n   to master all the words first");
			lblSelectLevel.setText("");
			nextWord("Amazing  ;;!!!");
			
		}
		else{
			// give the user a second chance to spell the word again
			lblSelectLevel.setText("Oops, Give it another go");
			lblSelectLevel.setFont(new Font("Purisa", Font.BOLD, 20));
			// say the word twice this time
			(new Settings()).say("Oops, Give it another go"+ "  .,\n\n\n" + ",  ;,.!!    "+ word +",  ;,.         !!!\n  " + word+";,.");
			btnN.setText("Try Again");
		}
		(new FileHandling()).overwrite(failed_copy);
	}

	/**
	 * This method basically checks the spelling of the user at their second attempt
	 *
	 * 
	 */
	public void reviewSecondChance(String word) {
		// checking the spelling the second time
		String ans= textField.getText();
		if(ans.equalsIgnoreCase(word)){
			lblSelectLevel.setFont(new Font("Purisa", Font.BOLD, 40));
			nextWord("That's right!.");
			lblSelectLevel.setText("That's right");
			//if answer is right on second try, word is added to faulted file and not in failed txt anymore
			failed_copy.remove(failed_copy.indexOf(word));
			textField.setText(null);
		}
		else{
			//if word is failed on both tries, festival calls are made to spell out the word
			lblSelectLevel.setFont(new Font("Purisa", Font.BOLD, 25));
			lblSelectLevel.setText("Sorry, \n\n thats Not quite right");
			nextWord("Sorry, \n\n thats Not quite right !!!;,.");
			//text file is updated
			(new FileHandling()).overwrite(failed_copy);
		}
		textArea.setText(null);
		textArea.append("   You've got it!, now just like\n   the Spelling Quiz you have an\n   even more special reward waiting\n   for you but you will have\n   to master all the words first");
		lblSelectLevel.setText("");
		textField.setText(null);
		//submit button is then made visible, try again is invisible again
	}

	/**
	 * This method is reached if level has been completed that is, the user has gotten all the failed words
	 * mastered
	 *
	 * 
	 */  
	public void endOfGame(){
		btnN.setVisible(false);
		btnR.setVisible(false);
		lblRelisten.setVisible(false);
		btnNewButton.setVisible(true);
		
		textArea.setText(null);
		textArea.append(" Would you like to play a game?");
		lblSelectLevel.setText("Would you like to play?");
		lblSelectLevel.setFont(new Font("Purisa", Font.BOLD, 30));
	
	}
	
	/**
	 *This method is called when the user has spelt the given word correctly or has used up all their 
	 *chances. It makes a festival call to say the next word.
	 */
	public void nextWord(String m){

		if(failed_copy.isEmpty()){
			// if the words spelt have reached the number of words available, then go to endOfGame meaning there are no
			// more words left for user to review.
			// this also happens when the failed list is empty
			
			endOfGame();

		}
		else{
			track++;
			if(failed_copy.size()>1){
				s=getWord();
			}
			// track field increases and say next word.
			(new Settings()).say(m+"Please, spell.,\n\n\n"+s+";,.");
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