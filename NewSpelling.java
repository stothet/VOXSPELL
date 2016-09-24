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
import javax.swing.SwingUtilities;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import Sound.Festival;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.Color;


public class NewSpelling extends JFrame implements ActionListener{
	private JButton btnN = new JButton("Submit");
	private JButton btnV = new JButton("Back");
	private JButton btnR = new JButton("");
	private JButton btnS = new JButton("Practice");
	JPanel panel = new JPanel();
	private JTextField textField;
	JButton btnNewButton_1, btnNewButton;
	private ArrayList<String> words;
	private int track=0;
	String s;
	private int _wordCount=1;
	private int score=0;
	private int level;
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


	public NewSpelling(ArrayList<String> words, int level){
		setBackground(new Color(250, 250, 210));

		this.level=level;
		
		this.words=words;
		panel.setBackground(new Color(72, 61, 139));

		panel.setPreferredSize(new Dimension(600,600));
		panel.setLayout(null);
		btnN.setFont(new Font("LM Roman 9", Font.BOLD, 14));
		btnN.setBounds(179, 518, 194, 54);
		//adding buttons
		panel.add(btnN);
		btnN.addActionListener(this);
		btnR.setBackground(new Color(255, 255, 255));
		btnR.setIcon(new ImageIcon("/afs/ec.auckland.ac.nz/users/m/k/mkim907/unixhome/Downloads/VOXSPELL/play.png"));
		btnR.setBounds(443, 306, 136, 96);
		panel.add(btnR);
		btnV.setFont(new Font("LM Roman 9", Font.BOLD, 14));
		btnV.setBounds(12, 12, 70, 25);
		panel.add(btnV);
		btnS.setFont(new Font("LM Roman 9", Font.BOLD, 14));
		btnS.setBounds(37, 463, 94, 25);
		panel.add(btnS);
		btnR.addActionListener(this);
		btnV.addActionListener(this);
		btnS.addActionListener(this);

		getContentPane().add(panel, BorderLayout.SOUTH);
		lblSelectLevel.setForeground(new Color(255, 255, 0));
		lblSelectLevel.setFont(new Font("L M Roman Caps10", Font.BOLD, 40));

		lblSelectLevel.setText(s=getWord());
		lblSelectLevel.setBounds(37, 93, 377, 337);
		panel.add(lblSelectLevel);

		this.say(s);

		btnNewButton = new JButton("Video");
		btnNewButton.setFont(new Font("LM Roman 9", Font.BOLD, 14));
		btnNewButton.setBounds(239, 463, 117, 25);
		panel.add(btnNewButton);

		btnNewButton_1 = new JButton("Next Level");
		btnNewButton_1.setFont(new Font("LM Roman 9", Font.BOLD, 14));
		btnNewButton_1.setBounds(464, 463, 117, 25);
		panel.add(btnNewButton_1);

		btnS.setVisible(false);
		btnNewButton_1.setVisible(false);
		btnNewButton.setVisible(false);
		lblNewLabel.setForeground(new Color(255, 255, 0));
		lblNewLabel.setFont(new Font("LM Roman Dunhill 10", Font.BOLD, 25));


		lblNewLabel.setBounds(432, 0, 156, 46);
		panel.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(179, 35, 166, 46);
		panel.add(textField);
		textField.setColumns(10);
		lblNewLabel_1.setBounds(464, 342, 70, 15);
		
		panel.add(lblNewLabel_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(432, 58, 156, 204);
		panel.add(textArea);
		pack();

		setTitle("New Quiz, Good Luck!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		btnNewButton.addActionListener(this);
		btnNewButton_1.addActionListener(this);
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

			this.failed(s);
			textField.setText(null);


		}

		else if(e.getActionCommand().equals("Practice")){
			panel.setVisible(false);
			(new NewSpelling(words,level)).setVisible(true);

		}

		else if(e.getActionCommand().equals("Next Level")){
			level++;
			Level l=new Level();
			try {
				words=l.getInput((level));
			} catch (NumberFormatException | IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			panel.setVisible(false);
			(new NewSpelling(words,level)).setVisible(true);

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
					btnN.setText("Submit");
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
				say("Correct");
				score++;
				lblNewLabel.setText("Score: "+score);
				textField.setText(null);
				btnN.setText("Next Word");


			}
			else{
				//if answer is wrong, makes a call to festival through bash to say the messages
				lblSelectLevel.setText("Incorrect, Try once more");
				say("Incorrect, Try once more");
				say(""+word+","+word);
				lblSelectLevel.setText(s);
				btnN.setText("Try Again");


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
				say("Incorrect");
				//if word is failed on both tries, it is added to failed file (for review) and original file (for viewing statistics)
				File fw = new File ("./failed.txt");
				addToFile(word, fw);
			}
			btnN.setText("Submit");
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
