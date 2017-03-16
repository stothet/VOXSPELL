package gameRendering;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.border.MatteBorder;

import gameRendering.gameReward;

import java.awt.Color;
import java.awt.Font;

/**
 * The purpose of this class is to create the set up before starting the game, that is asking the user to specify difficulty and also loads the character
 * @author stim599
 *
 */

public class setGame extends JFrame{
	// button for main menu created
	public static gameReward flappyBird;
	private JButton btnR = new JButton("Let's Game!");
	JPanel panel = new JPanel();

	String _level;


	// setting the fonts, colours, images and titles in the window and for the buttons as well as the frame.
	// adding all of these features to the main menu GUI
	public setGame(){

		panel.setBackground(new Color(255, 192, 203));
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 100, 0)));
		panel.setPreferredSize(new Dimension(400, 440));
		panel.setLayout(null);

		getContentPane().add(panel, BorderLayout.SOUTH);

		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"beginner", "intermediate", "advanced", "super advanced"}));
		comboBox.setBounds(90, 187, 221, 24);
		panel.add(comboBox);

		//asks user to select a difficulty from a list in a comboBox
		JLabel lblSelectDifficulty = new JLabel("Select Difficulty");
		lblSelectDifficulty.setFont(new Font("Purisa", Font.BOLD, 26));
		lblSelectDifficulty.setForeground(new Color(128, 0, 0));
		lblSelectDifficulty.setBounds(66, 96, 275, 53);
		panel.add(lblSelectDifficulty);

		btnR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_level = (String)comboBox.getSelectedItem();
				dispose();
				try {
					 //answer is then passed as parameter to the gameReward constructor
					flappyBird=new gameReward(_level);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnR.setForeground(new Color(128, 0, 0));
		btnR.setBounds(90, 333, 240, 65);
		btnR.setFont(new Font("Purisa", Font.BOLD, 25));
		btnR.setBackground(new Color(255, 250, 205));
		panel.add(btnR);

		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

}