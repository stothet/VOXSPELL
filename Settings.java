package Main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.DefaultComboBoxModel;

public class Settings extends JFrame implements ActionListener{
	private JButton btnV = new JButton("Back");
	private JButton btnS = new JButton("Clear");
	JPanel panel = new JPanel();
	private JComboBox comboBox = new JComboBox();
	String _festivalVoice = "voice_rab_diphone";

	public Settings(){
		panel.setPreferredSize(new Dimension(600,600));
		panel.setLayout(null);
		btnV.setBounds(12, 12, 139, 25);
		panel.add(btnV);
		btnS.setBounds(440, 245, 94, 25);
		panel.add(btnS);
		btnV.addActionListener(this);
		btnS.addActionListener(this);

		getContentPane().add(panel);

		JLabel lblSelectLevel = new JLabel("Clear");
		lblSelectLevel.setBounds(40, 245, 262, 25);
		panel.add(lblSelectLevel);

		JLabel lblNewLabel = new JLabel("Select Voice");
		lblNewLabel.setBounds(57, 431, 232, 17);
		panel.add(lblNewLabel);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Voice 1 (Default/RAB)", "Voice 2 (KAL)"}));


		comboBox.setBounds(358, 427, 176, 24);
		panel.add(comboBox);

		JButton btnNewButton = new JButton("Apply Changes");
		btnNewButton.setBounds(358, 534, 199, 25);
		panel.add(btnNewButton);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 314, 588, 25);
		panel.add(separator);
		pack();

		setTitle("Welcome to the Spelling Aid");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public void setVoice(String choice){
		_festivalVoice= choice;
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
			this.setVoice(_choice);

		}

	}
}
