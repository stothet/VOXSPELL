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
    	
    	
    	
    	
        JFrame frame = new JFrame("The Awesome Mediaplayer");

        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();

        final EmbeddedMediaPlayer video = mediaPlayerComponent.getMediaPlayer();
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(mediaPlayerComponent, BorderLayout.CENTER);
        
        frame.setContentPane(panel);

        JButton btnMute = new JButton("Mute");
        panel.add(btnMute, BorderLayout.NORTH);
        btnMute.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				video.mute();
			}
		});
        
        JButton btnNewVid = new JButton("Play New Video Reward");
        panel.add(btnNewVid, BorderLayout.EAST);
        btnNewVid.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {			
				video.playMedia("output.avi");
			
			}

			
		});
        
        
        
        
        JButton btnPly = new JButton("Play/Pause");
        panel.add(btnPly , BorderLayout.SOUTH);
        btnPly .addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				video.pause();
			}
		});
       
        
        
        frame.setLocation(100, 100);
        frame.setSize(1050, 600);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        String filename = "big_buck_bunny_1_minute.avi";
        video.playMedia(filename);
    }
}