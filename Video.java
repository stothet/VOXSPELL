package Main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import Main.Window;

public class Video extends JFrame implements ActionListener{
	JPanel panel = new JPanel();
	private ArrayList<String> words;
	private int value;


	public Video(){
		panel.setPreferredSize(new Dimension(600,600));

		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("Play/Pause");
		panel.add(btnNewButton, BorderLayout.SOUTH);
		
		JButton btnNewButton_1 = new JButton("Next Level");
		panel.add(btnNewButton_1, BorderLayout.WEST);
		
		JButton btnNewButton_2 = new JButton("Practice");
		panel.add(btnNewButton_2, BorderLayout.EAST);
		
		JButton btnNewButton_3 = new JButton("Quit");
		panel.add(btnNewButton_3, BorderLayout.NORTH);
		pack();

		setTitle("Welcome to the Spelling Aid");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if(e.getActionCommand().equals("Quit")){
			
			Window n=new Window();
			panel.setVisible(false);
			n.setVisible(true);

		}

		else if(e.getActionCommand().equals("Practice")){
			
			panel.setVisible(false);
			(new NewSpelling(words)).setVisible(true);

		}

		else if(e.getActionCommand().equals("Play/Pause")){
			

		}

		else if(e.getActionCommand().equals("Next Level")){
			
			Window w=new Window(true);
			int lvl = w._level;
			lvl++;
	        Level l=new Level();
			try {
				words=l.getInput((lvl));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	        panel.setVisible(false);
			(new NewSpelling(words)).setVisible(true);

		}

	}
}
