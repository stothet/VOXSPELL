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

public class Review extends JFrame implements ActionListener{
	private JButton btnN = new JButton("Submit");
	private JButton btnV = new JButton("Back");
	private JButton btnR = new JButton("Re-Listen");
	private JButton btnS = new JButton("Practice");
	JPanel panel = new JPanel();
	private JTextField textField;
	ArrayList<String> failed=new ArrayList<String>();
	ArrayList<String> words;
	private int track=0,score;
	private String s;
	JLabel lblSelectLevel = new JLabel();
	JLabel lblNewLabel = new JLabel();

	public Review(){
		panel.setPreferredSize(new Dimension(600,600));
		panel.setLayout(null);
		btnN.setBounds(139, 500, 126, 25);
		//adding buttons
		panel.add(btnN);
		btnN.addActionListener(this);
		btnR.setBounds(374, 500, 139, 25);
		panel.add(btnR);
		btnV.setBounds(12, 12, 139, 25);
		panel.add(btnV);
		btnS.setBounds(31, 131, 94, 25);
		panel.add(btnS);
		btnR.addActionListener(this);
		btnV.addActionListener(this);
		btnS.addActionListener(this);
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
		
		getContentPane().add(panel);
	
		
		lblSelectLevel.setText(s=getWord());
		lblSelectLevel.setBounds(181, 131, 233, 252);
		panel.add(lblSelectLevel);
		
		
		JButton btnNewButton = new JButton("Video");
		btnNewButton.setBounds(31, 250, 117, 25);
		panel.add(btnNewButton);
		
		
		JLabel lblNewLabel = new JLabel("Score: 0");
		lblNewLabel.setBounds(464, 58, 70, 17);
		panel.add(lblNewLabel);
		
		btnS.setVisible(false);
		btnNewButton.setVisible(false);
		
		textField = new JTextField();
		textField.setBounds(231, 56, 114, 19);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("View Statistics");
		btnNewButton_2.setBounds(432, 280, 149, 76);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("Next Word");
		btnNewButton_1.setBounds(215, 545, 117, 25);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_3 = new JButton("Try Again");
		btnNewButton_3.setBounds(355, 545, 117, 25);
		panel.add(btnNewButton_3);
		pack();
		
		btnNewButton_1.addActionListener(this);
		btnNewButton_2.addActionListener(this);
		btnNewButton_3.addActionListener(this);

		setTitle("Welcome to the Spelling Aid");
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

		else if(e.getActionCommand().equals("Practice")){
			panel.setVisible(false);
			(new NewSpelling(words)).setVisible(true);

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
