package menus;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import javax.swing.border.MatteBorder;

import fileManagement.FileHandling;
import fileManagement.Level;
import gamingMode.NewSpelling;
import gamingMode.PersonalBests;
import gamingMode.Review;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseEvent;

/**
 * The purpose of this class is to show my main window frame which features the main menu of the prototype.
 * The different buttons as well as the action listeners for these buttons are featured in this class.
 * When these buttons are pressed, the appropriate action is taken by calling the correct classes and their methods
 * to carry out the correct implementation.
 * @author stim599
 *
 */

public class Window extends JFrame implements ActionListener{
	// button for main menu created
	private JButton btnN = new JButton("Start a Spelling Quiz");
	private JButton btnR = new JButton("Review Mistakes");
	private JButton btnS = new JButton("Settings");
	JPanel panel = new JPanel();
	int _level;
	private JComboBox<String> comboBox = new JComboBox<String>();
	private String _character;
	public int _files=11;
	JTextArea txtrHiThere = new JTextArea();
	
	// setting the fonts, colours, images and titles in the window and for the buttons as well as the frame.
	// adding all of these features to the main menu GUI
	public Window(){

		//character is updated
		File fi=new File("./config/character");
        _character=(new FileHandling()).readFromFile(fi);
		
		
		txtrHiThere.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		txtrHiThere.setText(" ");
		txtrHiThere.setBackground(new Color(255, 250, 205));
		txtrHiThere.setBounds(252, 470, 198, 100);
		txtrHiThere.setEditable(false);
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				//updates speech bubble
				txtrHiThere.setText(null);
				txtrHiThere.append("Hi guys! I am "+_character+"\nLet's learn words together\nwith VOXSPELL."+" Move your\nmouse over each of these\n buttons to see what they do");
			}
		});
		panel.add(txtrHiThere);
		txtrHiThere.append("Hi guys! I am "+_character+"\nLet's learn words together\nwith VOXSPELL."+" Move your\nmouse over each of these\n buttons to see what they do");
		
		panel.setBackground(new Color(0, 128, 128));
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 100, 0)));
		panel.setPreferredSize(new Dimension(800, 880));
		panel.setLayout(null);
		btnN.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				//updates speech bubble
				txtrHiThere.setText(null);
				txtrHiThere.append("You will be asked to\nspell 10 words"+"from the level you\npick when you click on the\nlittle arrow below the button");
			}
		});
		btnN.setBounds(509, 593, 263, 65);
		btnN.setFont(new Font("Purisa", Font.BOLD, 18));
		btnN.setBackground(new Color(255, 250, 205));
		//adding buttons
		panel.add(btnN);
		btnN.addActionListener(this);
		btnR.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				//updates speech bubble
				txtrHiThere.setText(null);
				txtrHiThere.append("You will be asked to review\nsome of the mistakes in the\npast quizzes. If you haven't\nstarted one already,don't\nworry about it. Start\na new quiz instead. :)");
			}
		});
		btnR.setBounds(568, 792, 204, 65);
		btnR.setFont(new Font("Purisa", Font.BOLD, 18));
		btnR.setBackground(new Color(255, 250, 205));
		panel.add(btnR);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3","4","5","6","7","8","9","10","11"}));
		comboBox.setBounds(509, 705, 263, 24);
		panel.add(comboBox);
		btnS.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				//updates speech bubble
				txtrHiThere.setText(null);
				txtrHiThere.append("  Here you can change\n  all your settings: like\n  deleting saved progress,\n  changing voices and\n  getting a new wordlist");
			}
		});
		btnS.setBounds(25, 792, 170, 65);
		btnS.setFont(new Font("Purisa", Font.BOLD, 19));
		btnS.setBackground(new Color(255, 250, 205));
		panel.add(btnS);
		btnR.addActionListener(this);
		btnS.addActionListener(this);
		getContentPane().add(panel, BorderLayout.SOUTH);
		JLabel lblSelectLevel = new JLabel("Select Level");
		lblSelectLevel.setBounds(334, 696, 157, 33);
		lblSelectLevel.setForeground(new Color(255, 250, 240));
		lblSelectLevel.setFont(new Font("Purisa", Font.BOLD, 20));
		panel.add(lblSelectLevel);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(-231, -25, 1056, 436);
		lblNewLabel.setIcon(new ImageIcon("./resources/main_menu_image.jpg"));
		panel.add(lblNewLabel);
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("./resources/"+_character+"_2.png"));
		lblNewLabel_1.setBounds(54, 485, 180, 297);
		panel.add(lblNewLabel_1);
		
		 //window listener to show a pop up when user tries to close the application
        addWindowListener(new WindowAdapter() {
            //code obtained from http://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
              public void windowClosing(WindowEvent e) {
                  Object[] options = {"Yes, Please",
                    "No I want to stay!"};
                    int num = JOptionPane.showOptionDialog(panel,
                            "Are you sure you want to leave the game already?",
                            "Attention!",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,null);
                  //if yes
                    if(num==0){
                        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    }
                  //otherwise
                    else{
                        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    }
              }
            });
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("./resources/bubble_main.png"));
		lblNewLabel_2.setBounds(207, 423, 281, 200);
		panel.add(lblNewLabel_2);
		
		JButton progbutton = new JButton("See how you're going!");
		progbutton.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				//speech bubble updated
				txtrHiThere.setText(null);
				txtrHiThere.append(" Here, you can see how you\n are going! It will show you\n how many words you have\n mastered as well as a cool\n graph to help you see\n your performance");
			}
		});
		progbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//leads to the statistics window
				PersonalBests s=new PersonalBests();
				dispose();
				s.setVisible(true);
				
			}
		});
		progbutton.setBackground(new Color(255, 250, 205));
		progbutton.setFont(new Font("Purisa", Font.BOLD, 16));
		progbutton.setBounds(231, 792, 292, 65);
		panel.add(progbutton);
		pack();
		setTitle("Welcome to the VOXSPELL Spelling Aid");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	// running the main menu GUI on initial start up.
	
	public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        if (JOptionPane.showConfirmDialog(panel, 
            "Are you sure to close this window?", "Attention!", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<String> words=null;
		// obtaining the chosen level from the drop down menu.
		 // code retrieved http://www.codejava.net/java-se/swing/jcombobox-basic-tutorial-and-examples
		_level = Integer.parseInt((String)comboBox.getSelectedItem());
		Level l=new Level();
		try {
			// obtaining the words from the word list file for this particular level from the level class
			// which deals with the IO for the levels.
			// Then store all of these words in a words arraylist.
			words=l.getInput((_level));
		} catch (NumberFormatException | IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if(e.getActionCommand().equals("Start a Spelling Quiz")){
			// if the new spelling button has been pressed, set the new spelling window to be visible and set main
			// window GUI to be invisible and pass in the
			// level as well as the words arraylist into this class. 
			// Then ask festival to say the first word that needs to be spelt.
			NewSpelling r=new NewSpelling(words,_level);
			this.setVisible(false);
			
			r.setVisible(true);
			(new Settings()).say("Please, spell.,\n\n\n"+r.words.get(0)+";,.");

		}
		else if(e.getActionCommand().equals("Review Mistakes")){
			words.clear();
			// if review button is pressed, then read in the words from the failed list and add these into the
			// words arraylist
			try {
				words=(new FileHandling()).getFailed();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// if there are no failed words present then ask to try new spelling first.
			if(words.isEmpty()){
				JOptionPane.showMessageDialog( null, "Woo hoo! Looks like you have no words to review, please start a new Quiz" );
			}
			else{
				// set the main window GUI to be invisible and display the review window.
				// call the appropriate constructor of the review class to pass in the words arraylist and make festival
				// say the first word of the failed words arraylist.
				Review r=new Review(words);
				this.setVisible(false);
				r.setVisible(true);
				(new Settings()).say("Please, spell.,\n\n\n"+r.failed.get(0)+";,.");
			}
		}

		// if the settings button has been pressed then make the current main window GUI to be invisible and then display
		// the settings window.
		
		else if(e.getActionCommand().equals("Settings")){
			Settings s=new Settings();
			this.setVisible(false);
			s.setVisible(true);
		}
		
	}
}