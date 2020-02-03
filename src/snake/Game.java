package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements KeyListener, ActionListener {
	private ImageIcon titleImage; 
	
	private int[] snakexlength = new int [750];
	private int[] snakeylength = new int [750];
	
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	
	private ImageIcon leftmouth; 
	private ImageIcon rightmouth; 
	private ImageIcon downmouth; 
	private ImageIcon upmouth; 
	private ImageIcon body;
	//private ImageIcon food;
	
	private int lengthofsnake = 3;
	private int moves = 0;
	
	private Timer timer;
	private int delay = 100;
	
	private int [] foodx = {45,55,65,75,85,95,105,115,125,135,145,155,165,175,
			185,195,205,215,225,235,245,255,265,275,285,295,305,315,325,335,345,355,365};
	private int [] foody = {95,105,115,125,135,145,155,165,175,
			185,195,205,215,225,235,245,255,265,275,285,295,305,315,325,335,345};
	
	private ImageIcon  food;
	private Random random = new Random();
	
	private int xpos = random.nextInt(33);
	private int ypos = random.nextInt(26);
	
	
	private int scores = 0;
	
	public Game()
	{
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	public void paint(Graphics g)
	{
		
		if(moves == 0)
		{
			snakexlength[2] = 75;
			snakexlength[1] = 85;
			snakexlength[0] = 95;
			
			snakeylength[2] = 95;
			snakeylength[1] = 95;
			snakeylength[0] = 95;
			
		}
		//border
		g.setColor(Color.white);
		g.drawRect(24, 10, 551, 55);
		
		//title
		titleImage = new ImageIcon("title.jpg");
		titleImage.paintIcon(this, g, 25, 11);
		
		//border
		g.setColor(Color.WHITE);
		g.drawRect(24, 74, 851, 577);
		
		//background
		g.setColor(Color.black);
		g.fillRect(25, 75, 850, 575);
		
		//draw scores
		g.setColor(Color.black);
		g.setFont(new Font("arial", Font.PLAIN, 14));
	    g.drawString("Scores " +scores, 780,30);
		
		//draw length
		g.setColor(Color.black);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Length " +lengthofsnake, 780, 50);
		
		
		
		rightmouth = new ImageIcon("head.png");
		
		rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
		
		for(int i=0; i<lengthofsnake; i++)
		{
			if(i == 0 && right)
			{
				rightmouth = new ImageIcon("head.png");
				rightmouth.paintIcon(this, g, snakexlength[i], snakeylength[i]);
			}
			
			if(i == 0 && left)
			{
				leftmouth = new ImageIcon("head.png");
				leftmouth.paintIcon(this, g, snakexlength[i], snakeylength[i]);
			}
			
			if(i == 0 && up)
			{
				upmouth = new ImageIcon("head.png");
				upmouth.paintIcon(this, g, snakexlength[i], snakeylength[i]);
			}
			
			if(i == 0 && down)
			{
				downmouth = new ImageIcon("head.png");
				downmouth.paintIcon(this, g, snakexlength[i], snakeylength[i]);
			}
			
			if( i != 0 )
			{
				body = new ImageIcon("dot.png");
				body.paintIcon(this, g, snakexlength[i], snakeylength[i]);
			}
		}
		
//		leftmouth = new ImageIcon("");
//		upmouth = new ImageIcon("");
//		downmouth = new ImageIcon("");
		
		food = new ImageIcon("apple.png");
		
		if(foodx[xpos] == snakexlength[0] && foody[ypos] == snakeylength[0])
		{
			scores++;
			lengthofsnake++;
			xpos = random.nextInt(33);
			ypos = random.nextInt(26);
		}
		
		food.paintIcon(this, g, foodx[xpos], foody[ypos]);
		
		g.dispose();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.restart();
		if(right)
		{
			for(int i= lengthofsnake-1; i>=0; i--)
			{
				snakeylength[i+1] = snakeylength[i];
			}
			for(int i=lengthofsnake; i>=0; i--)
			{
				if( i == 0)
				snakexlength[i] = snakexlength[i] + 10;
				else
				{
					snakexlength[i] = snakexlength[i-1];
				}
				if(snakexlength[i] > 850)
				{
					snakexlength[i] = 25;
				}
			}
			repaint();
		}
		if(left)
		{
			for(int i= lengthofsnake-1; i>=0; i--)
			{
				snakeylength[i+1] = snakeylength[i];
			}
			for(int i=lengthofsnake; i>=0; i--)
			{
				if( i == 0)
				snakexlength[i] = snakexlength[i] - 10;
				else
				{
					snakexlength[i] = snakexlength[i-1];
				}
				if(snakexlength[i] < 25)
				{
					snakexlength[i] = 850;
				}
			}
			repaint();
		}
		if(up)
		{
			for(int i= lengthofsnake-1; i>=0; i--)
			{
				snakexlength[i+1] = snakexlength[i];
			}
			for(int i=lengthofsnake; i>=0; i--)
			{
				if( i == 0)
				snakeylength[i] = snakeylength[i] - 10;
				else
				{
					snakeylength[i] = snakeylength[i-1];
				}
				if(snakeylength[i] < 90)
				{
					snakeylength[i] = 625;
				}
			}
			repaint();
		}
		if(down)
		{
			for(int i= lengthofsnake-1; i>=0; i--)
			{
				snakexlength[i+1] = snakexlength[i];
			}
			for(int i=lengthofsnake; i>=0; i--)
			{
				if( i == 0)
				snakeylength[i] = snakeylength[i] + 10;
				else
				{
					snakeylength[i] = snakeylength[i-1];
				}
				if(snakeylength[i] > 625)
			
					snakeylength[i] = 90;
				}
			}
			repaint();
		
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			moves++;
			right = true;
			if(!left)
			{
				right = true;
			}
			else
			{
				right = false;
				left = true;
			}
			up = false;
			down = false;
		}
		
		
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			moves++;
			left = true;
			if(!right)
			{
				left = true;
			}
			else
			{
				left = false;
				right = true;
			}
			up = false;
			down = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			moves++;
			up = true;
			if(!down)
			{
				up = true;
			}
			else
			{
				up = false;
				down = true;
			}
			right = false;
			left =  false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			moves++;
			down = true;
			if(!up)
			{
				down = true;
			}
			else
			{
				down = false;
				up = true;
			}
			left = false;
			right =  false;
		}
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
