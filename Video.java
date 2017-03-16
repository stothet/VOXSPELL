package gamingMode;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import java.awt.Color;
import java.awt.Font;

/**
 * The purpose of this class is to play the video reward as well as being able to pause, play and mute the video using vlcj.
 * Here an inverted version of the video is also shown through using ffmpeg.
 * This is presented in a GUI.
 * This code has been retrieved from Nasser Giacaman's ACP lecture about VLCJ
 * @author ssin653
 * Code obtained from A3
 */

public class Video{

	private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
	final EmbeddedMediaPlayer video;
	JButton btnNewButton = new JButton("Stop");
	JButton btnNewVid = new JButton("Play New Video Reward");


	public Video() {

		// creating an inverted version of the video in order for it to be shown as an additional
		// video reward.
		String command = "ffmpeg -i .Llama_Drama.avi -vf lutrgb=\"r=negval:g=negval:b=negval\" .output.avi";
		ProcessBuilder pb = new ProcessBuilder ("/bin/bash", "-c", command);
		try {
			pb.start();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}



		// creating the JFrame with all the required buttons and panels and adding action listeners to these.
		JFrame frame = new JFrame("Llama Caminandes to fulfill your heart!");

		mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
		mediaPlayerComponent.setBounds(0, 0, 1205, 721);
		mediaPlayerComponent.getVideoSurface().setBounds(-11, 0, 1196, 721);
		video = mediaPlayerComponent.getMediaPlayer();


		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		panel.setLayout(null);
		panel.add(mediaPlayerComponent);
		mediaPlayerComponent.setLayout(null);

		frame.setContentPane(panel);

		// if mute button is pressed mute the video

		JToggleButton btnMute = new JToggleButton("Mute");
		btnMute.setBackground(new Color(240, 230, 140));
		btnMute.setFont(new Font("LM Roman 9", Font.BOLD, 37));
		btnMute.setBounds(721, 711, 206, 164);
		panel.add(btnMute);
		btnMute.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				video.mute();

			}
		});

		// if play new video reward button is pressed, play the inverted ffmpeg version of the video in the video panel.
		
		btnNewVid.setBackground(new Color(255, 250, 205));
		btnNewVid.setFont(new Font("LM Roman Caps 10", Font.BOLD, 22));
		btnNewVid.setBounds(0, 714, 386, 164);
		panel.add(btnNewVid);
		btnNewVid.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {	
				if(e.getActionCommand().equals("Play New Video Reward")){
					video.playMedia("./.output.avi");
					btnNewVid.setText("Play Original Video");}
				else if(e.getActionCommand().equals("Play Original Video")){
					video.playMedia("./.Llama_Drama.avi");
					btnNewVid.setText("Play New Video Reward");}
			}

		});

		// if the window is closed then stop the video from playing in the background.
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				video.stop();
			}
		});

		// when play/pause button is pressed either play or pause the video.
		JButton btnPly = new JButton("Play/Pause");
		btnPly.setFont(new Font("Dialog", Font.BOLD, 32));
		btnPly.setBounds(382, 715, 339, 159);
		panel.add(btnPly);
		btnPly.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				video.pause();
			}
		});


		btnNewButton.setBackground(new Color(218, 165, 32));
		btnNewButton.setFont(new Font("LM Roman 9", Font.BOLD, 30));
		btnNewButton.setBounds(922, 714, 283, 164);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("Stop")){
					video.stop();
					btnNewButton.setText("Play Again");}
				else if(e.getActionCommand().equals("Play Again")){
					video.play();
					btnNewButton.setText("Stop");}
			}
		});


		// setting the frame size and making it visible.
		frame.setLocation(100, 100);
		frame.setSize(1200, 900);
		frame.setVisible(true);

		// playing the video in the video panel.
		String filename = ".Llama_Drama.avi";
		video.playMedia(filename);
	}
}