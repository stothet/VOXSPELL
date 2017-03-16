package gamingMode;
 
 
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.MatteBorder;

import org.jfree.ui.RefineryUtilities;

import fileManagement.Level;
import menus.Window;
 
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
 
/**
 * The purpose of this class is to show the statistics of the user either in a list form with grading
 * and comments or a bar graph to depict the same but with colourful bars. 
 * @author stim599
 *
 */
 
public class PersonalBests extends JFrame implements ActionListener{
    // button for main menu created
    public ArrayList<Record> stats=new ArrayList<Record>();
    private JButton btnS = new JButton("Back");
    JPanel panel = new JPanel();
    JTextArea txt = new JTextArea();
    JLabel pic_label = new JLabel(""); 
 
    int _level,i=0;
 
    private boolean ans;
 
    // setting the fonts, colours, images and titles in the window and for the buttons as well as the frame.
    public PersonalBests(boolean ans){
        this.ans=ans;
    }
    public PersonalBests(){
        panel.setBackground(new Color(0, 128, 128));
        panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 100, 0)));
        panel.setPreferredSize(new Dimension(600, 700));
        panel.setLayout(null);
        btnS.setFont(new Font("LM Roman 9", Font.BOLD, 28));
        btnS.setBackground(new Color(255, 250, 205));
        btnS.setBounds(12, 12, 162, 65);
        panel.add(btnS);
        btnS.addActionListener(this);
 
      //window listener to show a pop up when user tries to close the application
        addWindowListener(new WindowAdapter() {
        	 //code obtained from http://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
            public void windowClosing(WindowEvent e) {
                Object[] options = {"Yes, Please",
                "No I want to stay!"};
                int num = JOptionPane.showOptionDialog(panel,
                		"Are you sure you want to leave the game already?",
                        "Attention!",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,null);
                //if selected
                if(num==0){
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                //otherwise
                else{
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
 
 
        getContentPane().add(panel, BorderLayout.NORTH);
 
 
        txt.setEditable(false);
        txt.setForeground(new Color(240, 248, 255));
        txt.setFont(new Font("Lucida Sans", Font.BOLD, 19));
        txt.setBackground(new Color(0, 128, 128));
        txt.setBounds(33, 190, 516, 476);
        panel.add(txt);
 
        JLabel lblNewLabel = new JLabel("Progress scoreboard starting from best!");
        lblNewLabel.setForeground(new Color(255, 250, 240));
        lblNewLabel.setFont(new Font("Purisa", Font.BOLD, 22));
        lblNewLabel.setBounds(23, 114, 544, 54);
        panel.add(lblNewLabel);
         
        JButton btnNewButton = new JButton("A cooler chart!");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 //displays the graphical form of the user's performance
               displayChart();
            }
        });
        btnNewButton.setBackground(new Color(255, 250, 205));
        btnNewButton.setFont(new Font("Purisa", Font.BOLD, 20));
        btnNewButton.setBounds(325, 13, 242, 65);
        panel.add(btnNewButton);
 
 
 
        //displays the list form of the user's performance
        displayStats();
         
 
        pack();
 
        setTitle("See how amazing you are doing!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
 
        if(e.getActionCommand().equals("Back")){
            // if back button is pressed, go back to main menu by setting this as visible and setting current window as
            // invisible.
            Window n=new Window();
            this.setVisible(false);
            n.setVisible(true);
 
        }
 
    }
    
    /**
     * This method basically constructs and returns a Record object that stores the mastered 
     * words count, total words count of a level.
     *
     */
    public Record updateStats(Files f) {
        int level=f.value;
        File fi=new File(f.file);
        int count=0;
        int size=1;
     // get number of words present in a particular level's file and obtain mastered word count from this
        String scan;
        Level l=new Level();
        try {
            size=l.getSize(level);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            FileReader in = new FileReader(fi);
            BufferedReader br = new BufferedReader(in);
 
            while(br.ready()){
                scan=br.readLine();
                count++;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Record st=new Record(count,level,size);
        return st;
    }
    
    /**
     * This method displays the Statistics on a text area with comments and grading
     *
     */   
       
    public void displayStats(){
        for(Files f : Files.values()){
            stats.add(updateStats(f));
        }
        Collections.sort(stats, new Record());
        for(Record f : stats){
            if(f.count>f.size){
                f.count=f.size;
            }
            String grade;
            if((f.count/f.size)>0.9){
                grade="A+ : Outstanding!";
            }else if((f.count/f.size)>0.6){
                grade="A : Good Job!";
            }else if(f.count==0){
                grade="No progress yet!";
            }
            else{
                grade="A- : Keep it up!";
            }
            if(f.level>9){
                txt.append("Level "+f.level+"         "+f.count+" out of "+f.size+"   "+grade+"\n");
            }
            else{
                txt.append("Level "+f.level+"           "+f.count+" out of "+f.size+"   "+grade+"\n");
            }
        }
 
    }
    
    /**
     * This method basically creates and displays the bar graph in a seperate window
     *
     */ 
    public void displayChart(){
 
          BarChart bc = new BarChart("VOXSPELL statistics", "See how much progress you have made!");
          bc.pack( );        
          RefineryUtilities.centerFrameOnScreen( bc );        
    }
}