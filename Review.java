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


public class Review extends JFrame implements ActionListener{
	private JButton btnN = new JButton("Submit");
	private JButton btnV = new JButton("Back");
	private JButton btnR = new JButton("");
	JPanel panel = new JPanel();
	private JTextField textField;
	JButton btnNewButton;
	private ArrayList<String> failed;
	private int track=0;
	String s;
	private int _wordCount=1;
	private int score=0;
	JLabel lblSelectLevel = new JLabel();
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


	public Review(ArrayList<String> words){
		setBackground(new Color(250, 250, 210));

		failed=words;
		panel.setBackground(new Color(0, 0, 128));

		panel.setPreferredSize(new Dimension(600,600));
		panel.setLayout(null);
		btnN.setFont(new Font("LM Roman 9", Font.BOLD, 14));
		btnN.setBounds(179, 518, 194, 54);
		//adding buttons
		panel.add(btnN);
		btnN.addActionListener(this);
		btnR.setBackground(new Color(255, 255, 255));
		btnR.setIcon(new ImageIcon("./play.png"));
		btnR.setBounds(443, 306, 136, 96);
		panel.add(btnR);
		btnV.setFont(new Font("LM Roman 9", Font.BOLD, 14));
		btnV.setBounds(12, 12, 70, 25);
		panel.add(btnV);
		btnR.addActionListener(this);
		btnV.addActionListener(this);

		getContentPane().add(panel, BorderLayout.SOUTH);
		lblSelectLevel.setForeground(new Color(255, 255, 0));
		lblSelectLevel.setFont(new Font("L M Roman Caps10", Font.BOLD, 40));

		lblSelectLevel.setText(s=getWord());
		lblSelectLevel.setBounds(37, 93, 377, 337);
		panel.add(lblSelectLevel);

		//this.say(s);

		btnNewButton = new JButton("Video");
		btnNewButton.setFont(new Font("LM Roman 9", Font.BOLD, 14));
		btnNewButton.setBounds(239, 463, 117, 25);
		panel.add(btnNewButton);
		btnNewButton.setVisible(false);

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
	}

	public String getWord(){
		String w=failed.get(track);
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
			//say(s);

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

			this.reviewSecondChance(s);
			textField.setText(null);


		}

		else if(e.getActionCommand().equals("Next Word")){
			if(failed.isEmpty()){
				/*Window w=new Window(true);
				Level l=new Level();
				for (Files f : Files.values()) {
					if(f.value==w._level){
						l.updateStats(score, new File(f.file));
					}
				}*/
				endOfGame();

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
		String ans= textField.getText();
		//checks for first attempt
		if(ans.equalsIgnoreCase(word)){
			lblSelectLevel.setText("Correct");
			//say("Correct");
			failed.remove(failed.indexOf(word));
			textField.setText(null);
			btnN.setText("Next Word");


		}
		else{
			//if answer is wrong, makes a call to festival through bash to say the messages
			lblSelectLevel.setText("Incorrect, Try once more");
			//say("Incorrect, Try once more");
			//say(""+word+","+word);
			lblSelectLevel.setText(s);
			btnN.setText("Try Again");


		}

		overwrite(failed);
	}

	public void reviewSecondChance(String word) {
		String ans= textField.getText();
		if(ans.equalsIgnoreCase(word)){
			lblSelectLevel.setText("Correct");
			//say("Correct");
			//if answer is right on second try, word is added to faulted file and not in failed txt anymore
			failed.remove(failed.indexOf(word));
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
					File fw = new File ("./failed");
					String command = ">failed";
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

	public void endOfGame(){
		btnNewButton.setVisible(true);

		btnN.setVisible(false);
		btnR.setVisible(false);

		lblSelectLevel.setText("Would you like to watch a Video");
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
		int i = l.getStats(w._level, failed);
		lblNewLabel_1.setText("Accuracy: \n"+i);
	}
}
