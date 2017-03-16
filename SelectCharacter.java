package menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import fileManagement.FileHandling;

import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;

/**
 * The purpose of this class is to select a character before heading off the main menu of the 
 * application
 * This class contains the starting main method
 * @author stim599
 *
 */

public class SelectCharacter extends JFrame{
	JPanel panel = new JPanel();
	JFrame frame = new JFrame();

	// constructor makes sure that the correct buttons and fields are added to the window frame and that they have the
	// correct font with size and colours and so on.
	// the action listeners for these are also added.
	public SelectCharacter(){

		panel.setBackground(new Color(144, 238, 144));
		panel.setPreferredSize(new Dimension(600,600));

		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("  Please pick a Character");
		lblNewLabel.setBounds(56, 64, 512, 56);
		lblNewLabel.setFont(new Font("Purisa", Font.BOLD, 30));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		panel.add(lblNewLabel);
		
		 //ensures only one character is picked and remains throughout the game
			String command = ">./config/character";
			(new Settings()).bashCommands(command);

			//for when the Panda is selected
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Gerald");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// choice from radio button is added to character file
				File file = new File ("./config/character");
				(new FileHandling()).addToFile(e.getActionCommand(), file);
				Window n=new Window();
				dispose();
				n.setVisible(true);
			}
		});
		rdbtnNewRadioButton.setBounds(114, 271, 149, 23);
		rdbtnNewRadioButton.setForeground(new Color(0, 0, 0));
		rdbtnNewRadioButton.setFont(new Font("Purisa", Font.BOLD, 18));
		rdbtnNewRadioButton.setBackground(new Color(144, 238, 144));
		panel.add(rdbtnNewRadioButton);

		JLabel pic = new JLabel("");
		pic.setIcon(new ImageIcon("./resources/panda.png"));
		pic.setBounds(87, 110, 192, 150);
		panel.add(pic);

		JRadioButton rdbtnGiraffe = new JRadioButton("Tara");
		rdbtnGiraffe.setActionCommand("Tara");
		rdbtnGiraffe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				File file = new File ("./config/character");
				(new FileHandling()).addToFile(e.getActionCommand(), file);
				Window n=new Window();
				dispose();
				n.setVisible(true);
			}
		});
		rdbtnGiraffe.setBounds(388, 473, 149, 23);
		rdbtnGiraffe.setForeground(new Color(0, 0, 0));
		rdbtnGiraffe.setFont(new Font("Purisa", Font.BOLD, 18));
		rdbtnGiraffe.setBackground(new Color(144, 238, 144));
		panel.add(rdbtnGiraffe);

		JRadioButton rdbtnCat = new JRadioButton("Ruby");
		rdbtnCat.setActionCommand("Ruby");
		rdbtnCat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				File file = new File ("./config/character");
				(new FileHandling()).addToFile(e.getActionCommand(), file);
				Window n=new Window();
				dispose();
				n.setVisible(true);
			}
		});
		rdbtnCat.setBounds(399, 271, 149, 23);
		rdbtnCat.setForeground(new Color(0, 0, 0));
		rdbtnCat.setFont(new Font("Purisa", Font.BOLD, 18));
		rdbtnCat.setBackground(new Color(144, 238, 144));
		
		panel.add(rdbtnCat);

		JRadioButton rdbtnOwl = new JRadioButton("Edward");
		rdbtnOwl.setActionCommand("Edward");
		rdbtnOwl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				File file = new File ("./config/character");
				(new FileHandling()).addToFile(e.getActionCommand(), file);
				Window n=new Window();
				dispose();
				n.setVisible(true);
			}
		});
		rdbtnOwl.setBounds(129, 473, 149, 23);
		rdbtnOwl.setForeground(new Color(0, 0, 0));
		rdbtnOwl.setFont(new Font("Purisa", Font.BOLD, 18));
		rdbtnOwl.setBackground(new Color(144, 238, 144));
		panel.add(rdbtnOwl);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("./resources/cat.png"));
		label.setBounds(370, 110, 162, 161);
		panel.add(label);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("./resources/owl.png"));
		label_1.setBounds(92, 291, 199, 174);
		panel.add(label_1);

		JLabel lblWelcomeToVoxspell = new JLabel(" Welcome to VOXSPELL!!");
		lblWelcomeToVoxspell.setForeground(new Color(0, 0, 0));
		lblWelcomeToVoxspell.setFont(new Font("Purisa", Font.BOLD, 27));
		lblWelcomeToVoxspell.setBounds(87, 12, 408, 56);
		panel.add(lblWelcomeToVoxspell);
		
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
                  //if not
                    else{
                        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    }
              }
            });

		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon("./resources/giraffe.png"));
		label_4.setBounds(370, 279, 154, 186);
		panel.add(label_4);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	 // running the main menu GUI on initial start up.

	public static void main(String[] args){

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				SelectCharacter frame;
				try {
					// displaying the main menu
					frame = new SelectCharacter();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}
}
