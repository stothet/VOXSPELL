package Main;


import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;


import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextArea;

import Main.Level.Levels;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Color;


public class viewStatistics extends JFrame {
	JPanel panel = new JPanel();
	JTextArea textArea = new JTextArea();

	int _level;


	public viewStatistics(){
		panel.setBackground(new Color(47, 79, 79));
		panel.setForeground(new Color(0, 0, 0));
		panel.setPreferredSize(new Dimension(600,600));
		panel.setLayout(null);

		getContentPane().add(panel, BorderLayout.NORTH);

		
		textArea.setBounds(61, 130, 500, 413);
		panel.add(textArea);

		JLabel lblAccuracyRates = new JLabel("Accuracy Rates");
		lblAccuracyRates.setForeground(new Color(255, 250, 205));
		lblAccuracyRates.setFont(new Font("Bitstream Charter", Font.BOLD, 30));
		lblAccuracyRates.setBounds(190, 65, 332, 40);
		panel.add(lblAccuracyRates);
		pack();

		setTitle("See how awesome you are doing!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

	}

	public void print() throws IOException{
		Window w=new Window(true);
		Level l=new Level();
		int vstats,count;
		
		for (Levels f : Levels.values()) {
			String scan;
			FileReader in = new FileReader(f.file);
			BufferedReader br = new BufferedReader(in);
			while(br.ready()){
				scan=br.readLine();
				vstats=Integer.parseInt(scan);
				count=(l.getInput(f.value)).size();
				textArea.append(f.name+": "+(vstats/count)+" \n");
			}
		}


	}


}
