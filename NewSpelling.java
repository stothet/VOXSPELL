package Main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
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
import java.awt.BorderLayout;


public class NewSpelling extends JFrame implements ActionListener{
	private JButton btnN = new JButton("Submit");
	private JButton btnV = new JButton("Back");
	private JButton btnR = new JButton("Re-Listen");
	private JButton btnS = new JButton("Practice");
	JPanel panel = new JPanel();
	private JTextField textField;
	JButton btnNewButton_1, btnNewButton;
	private ArrayList<String> words;
	private int track=0;
	String s;
	private final JButton btnNewButton_3 = new JButton("Next Word");
	//private int count;
	private int _wordCount;
	private int score=0;
	JLabel lblSelectLevel = new JLabel();
	JLabel lblNewLabel = new JLabel("Score: 0");
	JLabel lblNewLabel_1 = new JLabel();


	public enum Files{
		level1("./Level1",1),level2("./Level2",2),level3("./Level3",3),level4("./Level4",4),level5("./Level5",5),level6("./Level6",6),level7("./Level7",7),level8("./Level8",8),level9("./Level9",9),level10("./Level10",10),level11("./Level11",11);
		public String file;
		public int value;

		private Files(String file, int value) {
			this.value = value;
			this.file = file;
		}
	}


	public NewSpelling(ArrayList<String> words){

		this.words=words;

		panel.setPreferredSize(new Dimension(600,600));
		panel.setLayout(null);
		btnN.setBounds(85, 526, 126, 25);
		//adding buttons
		panel.add(btnN);
		btnN.addActionListener(this);
		btnR.setBounds(376, 526, 139, 25);
		panel.add(btnR);
		btnV.setBounds(12, 12, 139, 25);
		panel.add(btnV);
		btnS.setBounds(31, 131, 94, 25);
		panel.add(btnS);
		btnR.addActionListener(this);
		btnV.addActionListener(this);
		btnS.addActionListener(this);

		getContentPane().add(panel, BorderLayout.SOUTH);

		lblSelectLevel.setText(s=getWord());
		lblSelectLevel.setBounds(181, 131, 233, 252);
		panel.add(lblSelectLevel);

		this.say(s);

		btnNewButton = new JButton("Video");
		btnNewButton.setBounds(31, 250, 117, 25);
		panel.add(btnNewButton);

		btnNewButton_1 = new JButton("Next Level");
		btnNewButton_1.setBounds(36, 358, 117, 25);
		panel.add(btnNewButton_1);

		btnS.setVisible(false);
		btnNewButton_1.setVisible(false);
		btnNewButton.setVisible(false);


		lblNewLabel.setBounds(464, 58, 70, 17);
		panel.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(231, 56, 114, 19);
		panel.add(textField);
		textField.setColumns(10);
		btnNewButton_3.setBounds(161, 563, 117, 25);

		panel.add(btnNewButton_3);

		JButton btnTryAgain = new JButton("Try Again");
		btnTryAgain.setBounds(308, 563, 117, 25);
		panel.add(btnTryAgain);
		lblNewLabel_1.setBounds(464, 342, 70, 15);
		
		panel.add(lblNewLabel_1);
		pack();

		setTitle("Welcome to the Spelling Aid");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		btnNewButton.addActionListener(this);
		btnNewButton_1.addActionListener(this);
		btnNewButton_3.addActionListener(this);
		btnTryAgain.addActionListener(this);
	}

	public String getWord(){
		String w=words.get(track);
		track++;
		return w;
	}

	public String say(String first){
		Settings s=new Settings();
		Festival textToSay=new Festival();
		textToSay.festivalSaysText(s._festivalVoice,first);
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
			say(s);

		}

		else if(e.getActionCommand().equals("View Statistics")){


		}

		else if(e.getActionCommand().equals("Video")){
			Video n=new Video();
			panel.setVisible(false);
			n.setVisible(true);

		}

		else if(e.getActionCommand().equals("Try Again")){
			//submit button is set to invisible, try again button is set to visible

			this.failed(s);
			textField.setText(null);


		}

		else if(e.getActionCommand().equals("Practice")){
			panel.setVisible(false);
			(new NewSpelling(words)).setVisible(true);

		}

		else if(e.getActionCommand().equals("Next Level")){
			Window w=new Window(true);
			//int lvl=  w._level;
			int lvl=0;
			lvl++;
			Level l=new Level();
			try {
				words=l.getInput((lvl));
			} catch (NumberFormatException | IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			panel.setVisible(false);
			(new NewSpelling(words)).setVisible(true);

		}

		else if(e.getActionCommand().equals("Next Word")){
			if(_wordCount==10 && score>8){
				Window w=new Window(true);
				Level l=new Level();
				for (Files f : Files.values()) {
					if(f.value==w._level){
						l.updateStats(score, new File(f.file));
					}
				}
				endOfGame();
				
			}
			else if(_wordCount==10){
				Window w=new Window(true);
				Level l=new Level();
				for (Files f : Files.values()) {
					if(f.value==w._level){
						l.updateStats(score, new File(f.file));
					}
					JOptionPane.showMessageDialog( null, "Session has Ended, click on New Spelling for another round" );
				}
			}
				else{
					lblSelectLevel.setText(s=getWord());
				}
				textField.setText(null);
			}

			else if(e.getActionCommand().equals("Submit")){

				if(textField.getText().isEmpty()||!(textField.getText().matches("[a-zA-Z]+"))){
					JOptionPane.showMessageDialog( null, "Can only accept characters A-Z or a-z" );
				}
				else{
					//the submit button basically checks if the spelling is right
					this.check(s);
				}

			}
		}

		public void check(String word) {
			Level l=new Level();
			String ans= textField.getText();
			_wordCount++;
			//checks for first attempt
			if(ans.equalsIgnoreCase(word)){
				lblSelectLevel.setText("Correct");
				//say("Correct");
				score++;
				lblNewLabel.setText("Score: "+score);
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

		public void failed(String word) {
			//similar to check, failed gives the user a second chance after try again is clicked on
			String ans= textField.getText();
			//txtOutput.append(ans+ "\n");
			if(ans.equalsIgnoreCase(word)){
				//if answer is right on second try, word is added to faulted file
				lblSelectLevel.setText("Correct");
				//say("Correct");
				//addToFile(word,Faulted);
				textField.setText(null);
			}
			else{
				lblSelectLevel.setText("Incorrect");
				//say("Incorrect");
				//if word is failed on both tries, it is added to failed file (for review) and original file (for viewing statistics)
				File fw = new File ("./failed.txt");
				addToFile(word, fw);
			}
			//submit button is then made visible, try again is invisible again
		}

		public void endOfGame(){
			btnNewButton.setVisible(true);
			btnNewButton_1.setVisible(true);
			btnS.setVisible(true);


			btnN.setVisible(false);
			btnR.setVisible(false);

			lblSelectLevel.setText("Would you like to watch \n a Video, Practice this level more \n or Move on to the \n Next Level?");
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
		
		public void editStats() throws IOException{
			Window w=new Window(true);
			Level l=new Level();
			int i = l.getStats(w._level, words);
			lblNewLabel_1.setText("Accuracy: \n"+i);
		}
	}
