package Main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Sound.Festival;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.BorderLayout;

public class Review extends JFrame implements ActionListener{
	private JButton btnN = new JButton("Submit");
	private JButton btnV = new JButton("Back");
	private JButton btnR = new JButton("");
	JPanel panel = new JPanel();
	private JTextField textField;
	ArrayList<String> failed=new ArrayList<String>();
	ArrayList<String> words;
	private int track=0,score;
	private String s;
	JLabel lblSelectLevel = new JLabel();
	JLabel lblNewLabel = new JLabel();

	public Review(){
		panel.setBackground(new Color(25, 25, 112));
		panel.setPreferredSize(new Dimension(600,600));
		panel.setLayout(null);
		btnN.setFont(new Font("LM Roman 9", Font.BOLD, 14));
		btnN.setBounds(72, 545, 126, 25);
		//adding buttons
		panel.add(btnN);
		btnN.addActionListener(this);
		btnR.setBackground(new Color(255, 255, 255));
		btnR.setIcon(new ImageIcon("/afs/ec.auckland.ac.nz/users/m/k/mkim907/unixhome/Downloads/VOXSPELL/play.png"));
		btnR.setFont(new Font("LM Roman 9", Font.BOLD, 14));
		btnR.setBounds(459, 273, 127, 120);
		panel.add(btnR);
		btnV.setFont(new Font("LM Roman 9", Font.BOLD, 14));
		btnV.setBounds(12, 12, 139, 25);
		panel.add(btnV);
		btnR.addActionListener(this);
		btnV.addActionListener(this);
		btnN.addActionListener(this);

		File f=new File("./failed");
		if(f.exists()){
			String scan;
			FileReader in;
			try {
				in = new FileReader(f);
			
			BufferedReader br = new BufferedReader(in);
			while(br.ready()){
				scan=br.readLine();
				failed.add(scan);
			}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		getContentPane().add(panel, BorderLayout.SOUTH);
		lblSelectLevel.setFont(new Font("LM Roman Caps 10", Font.BOLD, 40));
		lblSelectLevel.setForeground(new Color(255, 255, 0));
	
		
		lblSelectLevel.setText(s=getWord());
		lblSelectLevel.setBounds(12, 131, 429, 334);
		panel.add(lblSelectLevel);
		
		
		JButton btnNewButton = new JButton("Video");
		btnNewButton.setFont(new Font("LM Roman 9", Font.BOLD, 14));
		btnNewButton.setBounds(217, 477, 117, 25);
		panel.add(btnNewButton);
		
		
		JLabel lblNewLabel = new JLabel("Score: 0");
		lblNewLabel.setForeground(new Color(255, 255, 0));
		lblNewLabel.setFont(new Font("LM Roman Dunhill 10", Font.BOLD, 25));
		lblNewLabel.setBounds(436, 12, 152, 48);
		panel.add(lblNewLabel);
		btnNewButton.setVisible(false);
		
		textField = new JTextField();
		textField.setBounds(172, 56, 219, 48);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("Try Again");
		btnNewButton_3.setFont(new Font("LM Roman 9", Font.BOLD, 14));
		btnNewButton_3.setBounds(355, 545, 117, 25);
		panel.add(btnNewButton_3);
		pack();
		btnNewButton_3.addActionListener(this);

		setTitle("Review: Never too late to improve!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		
	}
	
	public String getWord(){
		String w=failed.get(track);
		track++;
		return w;
	}

	public String say(String first){
		//Settings s=new Settings();
		//Festival textToSay=new Festival();
		//textToSay.festivalSaysText(s._festivalVoice,first);
		return first;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {


		if(e.getActionCommand().equals("Back")){
			Window n=new Window();
			panel.setVisible(false);
			n.setVisible(true);

		}

		else if(e.getActionCommand().equals("Re-Listen")){
			//say(s);

		}

		else if(e.getActionCommand().equals("View Statistics")){


		}

		/*else if(e.getActionCommand().equals("Video")){
			Video n=new Video();
			panel.setVisible(false);
			n.setVisible(true);

		}*/

		else if(e.getActionCommand().equals("Try Again")){
			//submit button is set to invisible, try again button is set to visible

			this.reviewSecondChance(s);
			textField.setText(null);


		}

		else if(e.getActionCommand().equals("Next Word")){
			if(failed.isEmpty()){
				JOptionPane.showMessageDialog( null, "No more words to review" );
			}
			else{
				lblSelectLevel.setText(s=getWord());
			}
			textField.setText(null);
		}

		else if(e.getActionCommand().equals("Submit")){

			
				this.check(s);

		}
	}

	public void check(String word) {
		Level l=new Level();
		String ans= textField.getText();
		
		//checks for first attempt
		if(ans.equalsIgnoreCase(word)){
			lblSelectLevel.setText("Correct");
			//say("Correct");
			score++;
			lblNewLabel.setText("Score: "+score);
			failed.remove(word);
			textField.setText(null);
			
		}
		else{
			//if answer is wrong, makes a call to festival through bash to say the messages
			lblSelectLevel.setText("Incorrect, Try once more");
			//say("Incorrect, Try once more");
			//say(""+word+","+word);
			lblSelectLevel.setText(s);
			

		}

	}

	public void reviewSecondChance(String word) {
		String ans= textField.getText();
		if(ans.equalsIgnoreCase(word)){
			lblSelectLevel.setText("Correct");
			//say("Correct");
			//if answer is right on second try, word is added to faulted file and not in failed txt anymore
			failed.remove(word);
			textField.setText(null);
		}
		else{
			//if word is failed on both tries, festival calls are made to spell out the word
			lblSelectLevel.setText("Incorrect");
			//say("The spelling is");
			for(int i=0;i<word.length();i++){
				char letter=word.charAt(i);
				//say(""+letter);
			}
		}
		//text file is updated
		overwrite(failed);
		//submit button is then made visible, try again is invisible again
	}
	
	public void overwrite(ArrayList<String> word){
		//overwrite updates the file by clearing all of it's contents and then adding all the arraylist elements into
				//file
				try {
					File fw = new File ("./failed.txt");
					String command = ">failed.txt";
					ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", command);
					Process p=pb.start();
					p.waitFor();
					for(String w:word){
						addToFile(w,fw);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
	}
	
	public void addToFile(String word,File file) {
		//adds a word to a file using Java I/O
		try(FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw))
		{
			out.println(word);
		} catch (Exception e) {

		}
	}
}
