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
		panel.setPreferredSize(new Dimension(600,600));
		panel.setLayout(null);
		btnN.setBounds(52, 5, 126, 25);
		//adding buttons
		panel.add(btnN);
		btnN.addActionListener(this);
		btnR.setBounds(183, 5, 84, 25);
		panel.add(btnR);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"}));
		comboBox.setBounds(326, 525, 251, 24);

		panel.add(comboBox);
		btnV.setBounds(309, 5, 139, 25);
		panel.add(btnV);
		btnS.setBounds(453, 5, 94, 25);
		panel.add(btnS);
		btnR.addActionListener(this);
		btnV.addActionListener(this);
		btnS.addActionListener(this);

		getContentPane().add(panel);

		JLabel lblSelectLevel = new JLabel("Select Level");
		lblSelectLevel.setBounds(39, 530, 186, 15);
		panel.add(lblSelectLevel);
		pack();

		setTitle("Welcome to the Spelling Aid");
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

			NewSpelling r=new NewSpelling(words);
			this.setVisible(false);
			r.setVisible(true);

		}

		else if(e.getActionCommand().equals("Review")){
			Review r=new Review();
			this.setVisible(false);
			r.setVisible(true);
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