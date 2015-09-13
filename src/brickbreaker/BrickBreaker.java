package brickbreaker;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.Timer;



//brick breaker main window which game is played
public class BrickBreaker extends JFrame
{
	private static final int INTERVAL = 20;
	private Game game;
	private GamePanel gamePanel;
	
	// Constructs main window
	// effects: sets up window in which Brick Breaker game will be played
	public BrickBreaker() 
	{
		
		super("Brick Breakerz");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setUndecorated(true);
		
		game = new Game();
		gamePanel = new GamePanel(game);
		
		add(gamePanel);
		
		addKeyListener(new KeyHandler() );
		
		pack();
		
		setLocationRelativeTo(null);
		
		setVisible(true);
		
		addTimer();
		

	}
	
	// Set up timer
	// modifies: none
	// effects:  initializes a timer that updates game each
	//           INTERVAL milliseconds
	private void addTimer() {
		Timer t = new Timer(INTERVAL, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				
				//if(game.isGameOver() == false && game.isWin() == false)
				//{
				
				game.update();
				//game.getBall().move();
				gamePanel.repaint(); 
				//sp.update();
				
				//}
			}
		});
		
		t.start();
	}
	
	/*
	 * A key handler to respond to key events
	 */
	private class KeyHandler extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) 
		{
			
			//if(game.isWin() == false && game.isGameOver() == false)
			//{
			game.boardControl(e.getKeyCode());
			//}
		}
		
		@Override
		public void keyReleased(KeyEvent e)
		{
			if(game.isWin() == false && game.isGameOver() == false)
			{
			
			game.releaseControl(e.getKeyCode());
			
			}
		}
		
		
	}
	
	public static void main(String[] args)
	{
		new BrickBreaker();
		
		
	}
}
