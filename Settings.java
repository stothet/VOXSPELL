package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSeparator;

import Sound.Festival;

import javax.swing.DefaultComboBoxModel;
import java.awt.Font;

public class Settings extends JFrame implements ActionListener{
	private JButton btnV = new JButton("Back");
	private JButton btnS = new JButton("Clear");
	JPanel panel = new JPanel();
	private JComboBox comboBox = new JComboBox();
	public String _festivalVoice;

	public Settings(){
		panel.setBackground(new Color(0, 128, 128));
		panel.setPreferredSize(new Dimension(600,600));
		panel.setLayout(null);
		btnV.setBounds(12, 12, 94, 25);
		panel.add(btnV);
		btnS.setFont(new Font("LM Roman 9", Font.BOLD, 14));
		btnS.setBounds(440, 245, 94, 25);
		panel.add(btnS);
		btnV.addActionListener(this);
		btnS.addActionListener(this);

		getContentPane().add(panel);

		JLabel lblSelectLevel = new JLabel("Clear");
		lblSelectLevel.setForeground(new Color(255, 255, 0));
		lblSelectLevel.setFont(new Font("LM Roman 9", Font.BOLD, 25));
		lblSelectLevel.setBounds(40, 245, 262, 25);
		panel.add(lblSelectLevel);

		JLabel lblNewLabel = new JLabel("Select Voice");
		lblNewLabel.setFont(new Font("LM Roman 9", Font.BOLD, 25));
		lblNewLabel.setForeground(new Color(255, 255, 0));
		lblNewLabel.setBounds(40, 420, 272, 56);
		panel.add(lblNewLabel);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"voice_rab_diphone", "voice_kal_diphone"}));


		comboBox.setBounds(358, 427, 176, 24);
		panel.add(comboBox);

		JButton btnNewButton = new JButton("Apply Changes");
		btnNewButton.setFont(new Font("LM Roman 9", Font.BOLD, 14));
		btnNewButton.setBounds(358, 534, 199, 25);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(this);
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 314, 588, 25);
		panel.add(separator);
		pack();

		setTitle("Settings for all your tinkering");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}


	public void clear() throws FileNotFoundException{


		try {
			String command = ">failed.txt";
			ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", command);
			pb.start();

			command = ">Level1.txt";
			pb = new ProcessBuilder("/bin/bash", "-c", command);
			pb.start();

			command = ">Level1.txt";
			pb = new ProcessBuilder("/bin/bash", "-c", command);
			pb.start();

			command = ">Level2.txt";
			pb = new ProcessBuilder("/bin/bash", "-c", command);
			pb.start();

			command = ">Level3.txt";
			pb = new ProcessBuilder("/bin/bash", "-c", command);
			pb.start();

			command = ">Level4.txt";
			pb = new ProcessBuilder("/bin/bash", "-c", command);
			pb.start();

			command = ">Level5.txt";
			pb = new ProcessBuilder("/bin/bash", "-c", command);
			pb.start();

			command = ">Level6.txt";
			pb = new ProcessBuilder("/bin/bash", "-c", command);
			pb.start();

			command = ">Level7.txt";
			pb = new ProcessBuilder("/bin/bash", "-c", command);
			pb.start();

			command = ">Level8.txt";
			pb = new ProcessBuilder("/bin/bash", "-c", command);
			pb.start();

			command = ">Level9.txt";
			pb = new ProcessBuilder("/bin/bash", "-c", command);
			pb.start();

			command = ">Level10.txt";
			pb = new ProcessBuilder("/bin/bash", "-c", command);
			pb.start();

			command = ">Level11.txt";
			pb = new ProcessBuilder("/bin/bash", "-c", command);
			pb.start();

			command = ">attemptedwords";
			pb = new ProcessBuilder("/bin/bash", "-c", command);
			pb.start();



		} catch (Exception e1) {
			e1.printStackTrace();
		}


	}

	@Override
	public void actionPerformed(ActionEvent e) {


		if(e.getActionCommand().equals("Back")){
			Window n=new Window();
			panel.setVisible(false);
			n.setVisible(true);

		}

		else if(e.getActionCommand().equals("Clear")){
			try {
				clear();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}

		}

		else if(e.getActionCommand().equals("Apply Changes")){
			String _choice = (String)comboBox.getSelectedItem();
			File file = new File ("./voice");
			try(FileWriter fw = new FileWriter(file, true);
					BufferedWriter bw = new BufferedWriter(fw);
					PrintWriter out = new PrintWriter(bw))
			{
				out.println(_choice);
			} catch (Exception e4) {

			}
			_festivalVoice=_choice;
			say("This is the current voice");

		}

	}
	
	public String say(String first){
		Festival textToSay=new Festival();
		textToSay.festivalSaysText(_festivalVoice,first);
		return first;
	}
}