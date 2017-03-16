package gameRendering;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import menus.Window;


/**
 * This class is used for the gameplay which mimics a popular mobile game, which many kids are familiar with. It serves as another type of reward.
 * Code has been acquired from a tutorial.
 * Programming Flappy Bird in Java! (Full Tutorial) . (2015, January 24). Retrieved from https://www.youtube.com/watch?v=I1qTZaUcFX0
 *
 */

public class gameReward implements ActionListener, MouseListener
{

	public final int WIDTH = 800, HEIGHT = 800;

	public Renderer renderer;

	public Rectangle bird;

	public String answer;

	public ArrayList<Rectangle> columns;

	public int ticks, yMotion, score;

	public boolean gameOver, started;

	public Random rand;

	BufferedImage img;

	int diff=0;
	
	JFrame jframe = new JFrame();

	private String _character;

	public gameReward(String answer) throws IOException
	{
		
		File f=new File("./config/character");
		if(f.exists()){
			String scan;
			FileReader in;
			try {
				in = new FileReader(f);
				BufferedReader br = new BufferedReader(in);
				while(br.ready()){
					scan=br.readLine();
					_character=scan;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		f = new File("./resources/"+_character+"_icon.png");
		img = ImageIO.read(f);
		this.answer=answer;
		
		if(answer.equals("beginner")){
			diff=40;
		}
		else if(answer.equals("intermediate")){
			diff=30;
		}
		else if(answer.equals("advanced")){
			diff=25;
		}
		else if(answer.equals("super advanced")){
			diff=20;
		}
		Timer timer = new Timer(diff, this);

		renderer = new Renderer();
		rand = new Random();

		jframe.add(renderer);
		jframe.setTitle("Special Game Reward");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(WIDTH, HEIGHT);
		jframe.addMouseListener(this);
		jframe.setResizable(false);
		jframe.setVisible(true);

		bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
		columns = new ArrayList<Rectangle>();

		addColumn(true);
		addColumn(true);
		addColumn(true);
		addColumn(true);

		timer.start();
	}

	public void addColumn(boolean start)
	{
		int space = 300;
		int width = 100;
		int height = 50 + rand.nextInt(300);

		if (start)
		{
			columns.add(new Rectangle(WIDTH + width + columns.size() * 300, HEIGHT - height - 120, width, height));
			columns.add(new Rectangle(WIDTH + width + (columns.size() - 1) * 300, 0, width, HEIGHT - height - space));
		}
		else
		{
			columns.add(new Rectangle(columns.get(columns.size() - 1).x + 600, HEIGHT - height - 120, width, height));
			columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0, width, HEIGHT - height - space));
		}
	}

	public void paintColumn(Graphics g, Rectangle column)
	{
		g.setColor(Color.green.darker());
		g.fillRect(column.x, column.y, column.width, column.height);
	}

	public void jump()
	{
		if (gameOver)
		{
			bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
			columns.clear();
			yMotion = 0;
			score = 0;

			addColumn(true);
			addColumn(true);
			addColumn(true);
			addColumn(true);

			gameOver = false;
		}

		if (!started)
		{
			started = true;
		}
		else if (!gameOver)
		{
			if (yMotion > 0)
			{
				yMotion = 0;
			}

			yMotion -= 10;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		int speed = 10;

		ticks++;

		if (started)
		{
			for (int i = 0; i < columns.size(); i++)
			{
				Rectangle column = columns.get(i);

				column.x -= speed;
			}

			if (ticks % 2 == 0 && yMotion < 15)
			{
				yMotion += 2;
			}

			for (int i = 0; i < columns.size(); i++)
			{
				Rectangle column = columns.get(i);

				if (column.x + column.width < 0)
				{
					columns.remove(column);

					if (column.y == 0)
					{
						addColumn(false);
					}
				}
			}

			bird.y += yMotion;

			for (Rectangle column : columns)
			{
				if (column.y == 0 && bird.x + bird.width / 2 > column.x + column.width / 2 - 10 && bird.x + bird.width / 2 < column.x + column.width / 2 + 10)
				{
					score++;
				}

				if (column.intersects(bird))
				{
					gameOver = true;

					if (bird.x <= column.x)
					{
						bird.x = column.x - bird.width;

					}
					else
					{
						if (column.y != 0)
						{
							bird.y = column.y - bird.height;
						}
						else if (bird.y < column.height)
						{
							bird.y = column.height;
						}
					}
				}
			}

			if (bird.y > HEIGHT - 120 || bird.y < 0)
			{
				gameOver = true;
			}

			if (bird.y + yMotion >= HEIGHT - 120)
			{
				bird.y = HEIGHT - 120 - bird.height;
				gameOver = true;
			}
		}

		renderer.repaint();
	}

	public void repaint(Graphics g)
	{
		g.setColor(Color.cyan);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		g.setColor(Color.orange);
		g.fillRect(0, HEIGHT - 120, WIDTH, 120);

		g.setColor(Color.green);
		g.fillRect(0, HEIGHT - 120, WIDTH, 20);

		g.setColor(Color.red);
		g.drawImage(img, bird.x, bird.y, null);

		for (Rectangle column : columns)
		{
			paintColumn(g, column);
		}

		g.setColor(Color.white);
		g.setFont(new Font("Arial", 1, 70));

		if (!started)
		{
			g.drawString("Click the mouse to start!", 10, HEIGHT / 2 - 70);
			g.drawString("And keep clicking to", 10, HEIGHT / 2);
			g.drawString("get to the other side",10, HEIGHT / 2 + 70);
		}

		if (gameOver)
		{
			
			Window n=new Window();
			jframe.setVisible(false);
			n.setVisible(true);
			
		}

		if (!gameOver && started)
		{
			g.drawString(String.valueOf(score), WIDTH / 2 - 25, 100);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		jump();
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
	}
}
