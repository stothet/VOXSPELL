package Main;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.Font;


public class Window extends JFrame implements ActionListener{
	private JButton btnN = new JButton("New Spelling");
	private JButton btnV = new JButton("View Statistics");
	private JButton btnR = new JButton("Review");
	private JButton btnS = new JButton("Settings");
	JPanel panel = new JPanel();

	int _level;

	private JComboBox comboBox = new JComboBox();

	private boolean ans;

	public Window(boolean ans){
		this.ans=ans;
	}

	public Window(){
		panel.setBackground(new Color(0, 128, 128));
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 100, 0)));
		panel.setPreferredSize(new Dimension(600,600));
		panel.setLayout(null);
		btnN.setBackground(new Color(255, 250, 205));
		btnN.setBounds(39, 384, 126, 65);
		//adding buttons
		panel.add(btnN);
		btnN.addActionListener(this);
		btnR.setBackground(new Color(255, 250, 205));
		btnR.setBounds(206, 384, 84, 65);
		panel.add(btnR);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"}));
		comboBox.setBounds(326, 525, 251, 24);

		panel.add(comboBox);
		btnV.setBackground(new Color(255, 250, 205));
		btnV.setBounds(326, 384, 139, 65);
		panel.add(btnV);
		btnS.setBackground(new Color(255, 250, 205));
		btnS.setBounds(494, 384, 94, 65);
		panel.add(btnS);
		btnR.addActionListener(this);
		btnV.addActionListener(this);
		btnS.addActionListener(this);

		getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblSelectLevel = new JLabel("Select Level");
		lblSelectLevel.setForeground(new Color(255, 250, 240));
		lblSelectLevel.setFont(new Font("Cantarell", Font.BOLD, 20));
		lblSelectLevel.setBounds(39, 512, 251, 33);
		panel.add(lblSelectLevel);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("./14466478_10154557473139513_2095106893_o.jpg"));
		lblNewLabel.setBounds(-346, -13, 1012, 389);
		panel.add(lblNewLabel);
		pack();

		setTitle("Welcome to the VOXSPELL Spelling Aid");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	public static void main(String[] args){
		//makes all the files, unless already created
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Window frame;
				try {
					frame = new Window();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<String> words=null;

		_level = Integer.parseInt((String)comboBox.getSelectedItem());
		Level l=new Level();
		try {
			words=l.getInput((_level));
		} catch (NumberFormatException | IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		if(e.getActionCommand().equals("New Spelling")){

			NewSpelling r=new NewSpelling(words,_level);
			this.setVisible(false);
			r.setVisible(true);
			r.say(r.words.get(0));


		}

		else if(e.getActionCommand().equals("Review")){
			Review r=new Review();
			this.setVisible(false);
			r.setVisible(true);
			r.say(r.failed.get(0));
		}

		else if(e.getActionCommand().equals("View Statistics")){

			viewStatistics r=new viewStatistics();
			panel.setVisible(false);
			r.setVisible(true);
			

		}
		else if(e.getActionCommand().equals("Settings")){
			Settings s=new Settings();
			this.setVisible(false);
			s.setVisible(true);
		}

	}
}
