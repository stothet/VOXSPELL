package Main;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;


public class Video {
	
    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;

    public Video() {
    	
    	String command = "ffmpeg -i big_buck_bunny_1_minute.avi -vf lutrgb=\"r=negval:g=negval:b=negval\" output.avi";
		ProcessBuilder pb = new ProcessBuilder ("/bin/bash", "-c", command);
		try {
			pb.start();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	
    	
    	
        JFrame frame = new JFrame("The video reward to fullfill your heart!");

        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        mediaPlayerComponent.setBounds(73, 102, 1041, 589);
        mediaPlayerComponent.getVideoSurface().setBounds(0, 0, 849, 550);

        final EmbeddedMediaPlayer video = mediaPlayerComponent.getMediaPlayer();
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 128, 128));
        panel.setLayout(null);
        panel.add(mediaPlayerComponent);
        mediaPlayerComponent.setLayout(null);
        
        frame.setContentPane(panel);

        JButton btnMute = new JButton("Mute");
        btnMute.setBackground(new Color(240, 230, 140));
        btnMute.setFont(new Font("LM Roman 9", Font.BOLD, 16));
        btnMute.setBounds(73, 12, 111, 64);
        panel.add(btnMute);
        btnMute.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				video.mute();
			}
		});
        
        JButton btnNewVid = new JButton("Play New Video Reward");
        btnNewVid.setBackground(new Color(255, 250, 205));
        btnNewVid.setFont(new Font("LM Roman Caps 10", Font.BOLD, 25));
        btnNewVid.setBounds(695, 10, 418, 64);
        panel.add(btnNewVid);
        btnNewVid.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {			
				video.playMedia("output.avi");
			
			}

			
		});
        
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                video.stop();
            }
        });
        
        
        JButton btnPly = new JButton("Play/Pause");
        btnPly.setBounds(502, 722, 224, 101);
        panel.add(btnPly);
        btnPly .addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				video.pause();
			}
		});
       
        
        
        frame.setLocation(100, 100);
        frame.setSize(1200, 900);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        String filename = "big_buck_bunny_1_minute.avi";
        video.playMedia(filename);
    }
}