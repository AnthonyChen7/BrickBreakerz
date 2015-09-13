package brickbreaker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;







//rendering for our brick breaker game
public class GamePanel extends JPanel 
{
	private static final String OVER = "Game Over";
	private static final String WIN = "WIN!!!!";
	private static final String REPLAY = "PRESS R TO REPLAY!!";
	
	private Game game;
	private ImagePanel ip;
	
	//set size of background and color of it and update it with game to be displayed
	
	public GamePanel(Game game)
	{
		Image i = new ImageIcon("C:\\Users\\Achen\\Desktop\\download.png").getImage();
		
		setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT) );
		
		//setBackground(Color.BLACK);
		//this.setBackground(Color.white);
		
		
		this.game = game;
		
		ip = new ImagePanel("C:\\Users\\Achen\\Desktop\\download.png");
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		ip.paintComponent(g);
		
		
		
		drawGame(g);
		
		
		
		if(game.isGameOver())
		{
			GameOver(g);
		}
		
		if(game.isWin())
		{
			gameWin(g);
		}
		
	}

	//draws win game on screen and display reset button
	private void gameWin(Graphics g) 
	{
		
		Color saved = g.getColor();
		
		g.setColor(new Color(255,255,255));
		
		g.setFont(new Font("Arial", 50, 50));
		
		g.drawString(WIN, Game.WIDTH / 2 - 200, Game.HEIGHT / 2);
		
		g.drawString(REPLAY, Game.WIDTH / 2 -300, Game.HEIGHT / 2 + 50);
		
		g.setColor(saved);
//		Color saved = g.getColor();
//		g.setColor(new Color( 0, 0, 0));
//		g.setFont(new Font("Arial", 20, 20));
//		FontMetrics fm = g.getFontMetrics();
//		centreString(OVER, g, fm, SIGame.HEIGHT / 2);
//		centreString(REPLAY, g, fm, SIGame.HEIGHT / 2 + 50);
//		g.setColor(saved);
		
	}

	
	
	//draws the game onto g
	private void drawGame(Graphics g) 
	{
		//if(game.isGameOver() == false  && game.isWin() == false)
		//{
		
		drawmainBoard(g);
		
		//draw the ball
		drawBall(g);
		
		drawBricks(g);
		
		//draws the counter
		drawCounter(g);
		
		//}
		
	}
	


	//draws the ball onto g
	private void drawBall(Graphics g) 
	{
		if(game.isGameOver() == false && game.isWin() == false)
		{
		
		Color savedCol = g.getColor();
		
		Ball b = game.getBall();
		
		g.setColor(Ball.bColor);
		
		g.drawOval((b.getX() + b.getX() + Ball.SIZE_X/2)/2, (b.getY() + b.getY() + Ball.SIZE_Y/2)/2, Ball.SIZE_X, Ball.SIZE_Y);
		
		g.fillOval((b.getX() + b.getX() + Ball.SIZE_X/2)/2, (b.getY() + b.getY() + Ball.SIZE_Y/2)/2, Ball.SIZE_X, Ball.SIZE_Y);
		
		g.setColor(savedCol);
		
		}
	}

	//draws the bricks onto g
	private void drawBricks(Graphics g) 
	{
		if(game.isGameOver() == false && game.isWin() == false)
		{
		
		for(Brick b: game.getBricks())
		{
			drawBrick(g,b);
		}
		
		}
//		for(Invader next : game.getInvaders()) {
//			drawInvader(g, next);
//		}
	}
	
	//draws the brick b onto g
	private void drawBrick(Graphics g, Brick b) 
	{
		if(game.isGameOver() == false && game.isWin() == false)
		{
		
		Color savedCol = g.getColor();
		g.setColor(Brick.brickColor);
		
		//g.fillOval(b.getX() - Brick.SIZE_X / 2, b.getY() - Brick.SIZE_Y/2, Brick.SIZE_X, Brick.SIZE_Y);
		g.fillRect(b.getX(), b.getY(), Brick.SIZE_X, Brick.SIZE_Y);
		
		g.setColor(savedCol);
		
		}
		
		
		//		Color savedCol = g.getColor();
//		g.setColor(Invader.COLOR);
//		g.fillOval(i.getX() - Invader.SIZE_X / 2, i.getY() - Invader.SIZE_Y / 2, Invader.SIZE_X, Invader.SIZE_Y);
//		g.setColor(savedCol);
		
	}

	//draws mainboard onto g
	private void drawmainBoard(Graphics g) 
	{
		if(game.isGameOver() == false && game.isWin() == false)
		{
		
		mainBoard mb = game.getmb();
		
		Color savedColor = g.getColor();
		
		g.setColor(mainBoard.mbColor);
		
		//g.fillRect(mb.getX()- mainBoard.SIZE_X / 2, mainBoard.Y_POS - mainBoard.SIZE_Y / 2, mainBoard.SIZE_X, mainBoard.SIZE_Y);
		
		g.drawRect(mb.getX(), mainBoard.Y_POS, mainBoard.SIZE_X, mainBoard.SIZE_Y);
		
		g.fillRect(mb.getX(), mainBoard.Y_POS, mainBoard.SIZE_X, mainBoard.SIZE_Y);
		
		g.setColor(savedColor);
		
		}
		
//		Tank t = game.getTank();
//		Color savedCol = g.getColor();
//		g.setColor(Tank.COLOR);
//		g.fillRect(t.getX() - Tank.SIZE_X / 2, Tank.Y_POS - Tank.SIZE_Y / 2, Tank.SIZE_X, Tank.SIZE_Y);
//		g.setColor(savedCol);
		
	}
	
	private void drawCounter(Graphics g) 
	{
		Color savedCol = g.getColor();
		
		int counter = game.getCounter();
		
		g.setColor(new Color(255,255,255));
		
		g.setFont(new Font("Arial", Font.BOLD, 30) );
		
		g.drawString("Lives Remaining: "+counter, Game.WIDTH-350,40);
		
		g.setColor(savedCol);
		
		
	}
	
	// Draws the "game over" message and replay instructions
		// modifies: g
		// effects:  draws "game over" and replay instructions onto g
		private void GameOver(Graphics g)
		{
			Color saved = g.getColor();
			
			g.setColor(new Color(255,20,147));
			
			g.setFont(new Font ("Arial", 30, 30));
			
			FontMetrics fm = g.getFontMetrics();
			
			g.drawString(OVER, Game.WIDTH/2, Game.HEIGHT / 2);
			
			g.drawString(REPLAY, Game.WIDTH / 2 + 50, Game.HEIGHT / 2 + 50);
			
//			g.setColor(new Color(0,0,0) );
//			
//			mainBoard mb = game.getmb();
//			
//			g.clearRect(mb.getX()  , mainBoard.Y_POS, mainBoard.SIZE_X, mainBoard.SIZE_Y);
//			
//			
//			
//			Ball b = game.getBall();
//			
//			g.fillOval((b.getX() + b.getX() + Ball.SIZE_X/2)/2 , (b.getY() + b.getY() + Ball.SIZE_Y/2)/2 , Ball.SIZE_X, Ball.SIZE_Y);
			
			
			g.setColor(saved);
//			Color saved = g.getColor();
//			g.setColor(new Color( 0, 0, 0));
//			g.setFont(new Font("Arial", 20, 20));
//			FontMetrics fm = g.getFontMetrics();
//			centreString(OVER, g, fm, SIGame.HEIGHT / 2);
//			centreString(REPLAY, g, fm, SIGame.HEIGHT / 2 + 50);
//			g.setColor(saved);
		}
		
		private class ImagePanel extends JPanel
		{
			private Image img;
			
			public ImagePanel(String img)
			{
				this(new ImageIcon(img).getImage());
			}
			
			public ImagePanel(Image img)
			{
				this.img = img;
				
				//Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
				Dimension size = new Dimension(Game.WIDTH, Game.HEIGHT);
				
				setPreferredSize(new Dimension(Game.WIDTH,Game.HEIGHT ));
				//setPreferredSize(size);
				
				//setMinimumSize(size);
				
				//setMaximumSize(size);
				
				//setSize(size);
				
				setLayout(null);
			}
			
			public void paintComponent(Graphics g)
			{
				
				//g.drawImage(img, 0, 0, null);
				g.drawImage(img, 0, 0, Game.WIDTH, Game.HEIGHT, null);
			}
		}
	


}
