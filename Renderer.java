package gameRendering;

import java.awt.Graphics;

import javax.swing.JPanel;

import gamingMode.Review;
/**
 * This class is used to render the gameplay in gameReward which mimics a popular mobile game, which many kids are familiar with. It serves as another type of reward.
 * Code has been acquired from a tutorial.
 * Programming Flappy Bird in Java! (Full Tutorial) . (2015, January 24). Retrieved from https://www.youtube.com/watch?v=I1qTZaUcFX0
 *
 */
public class Renderer extends JPanel
{

	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		setGame.flappyBird.repaint(g);
	}
	
}