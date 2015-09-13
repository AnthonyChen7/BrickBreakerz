package brickbreaker;

import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;




public class Game 
{
	public static final int WIDTH = 800;
	
	public static final int HEIGHT = 600;
	
	private mainBoard mb;
	
	private int counter;
	
	private Ball b;
	
	private List<Brick> bricks;
	
	private boolean isGameOver;
	
	private boolean isWin;
	
	boolean ballFired;
	
	List<Brick> bricksToRemove;
	
	//creates BirckBreaker game, and construct bricks and puts mainboard at center of screen and place ball on mainboard
	public Game()
	{
		//bricks = new ArrayList<Brick>();
		
		setUp();
	}
	
	// Sets / resets the game
	// modifies: this
	// effects:  init list of bricks, initializes mainboard at center of game screen
	private void setUp() 
	{
		//int i = 0;
		
		//counter = 0;
		counter = 3;
		mb = new mainBoard(WIDTH/2);
		isGameOver = false;
		isWin = false;
		
		bricks = new ArrayList<Brick>(9);
		//now we need to init bricks
		
		 bricksToRemove =  new ArrayList<Brick>();
		
		for(int x = 200; x<500; x = x + 110 ) //TODO
		 //for(int x = 200; x<300; x = x + 110 )
		{
			
			 for(int  y = 100; y < 400; y = y+100)
			 //for(int  y = 100; y < 200; y = y+100)
			 {
				Brick b = new Brick(x,y);
				bricks.add(b);
			}
		}
		 //initiate ball!!!!!!!!!!!!!!!!!!!!!!!!
		
		initBall();
		
	}
	
	//sets ball to be on top of board
	private void initBall()
	{
		b = new Ball( (this.mb.getX() + this.mb.getX() + mainBoard.SIZE_X)/2 - (Ball.SIZE_X)  , mainBoard.Y_POS - 40  );
		b.setdX(0);
		b.setdY(0);
		ballFired = false;
	}
	
	public boolean getballFired()
	{
		return ballFired;
	}
	
	public List<Brick> getBricksRemoved()
	{
		return this.bricksToRemove;
	}
	
	//Fires the ball from the board, once fired, ball will go up
	private void fireBall()
	{
		b.setdY(-3);
		ballFired = true;
		b.move();
	}
	
	// Fires a missile
	// modifies: this
	// effects:  fires a missile if max number of missiles in play has
	//           not been exceeded, otherwise silently returns
//	private void fireMissile() {
//		if (missiles.size() < MAX_MISSILES) {
//			Missile m = new Missile(tank.getX(), Tank.Y_POS);
//			missiles.add(m);
//		}
//	}
	
	//update game on clock tick
	public void update()
	{	
		if(this.isGameOver == false && this.isWin == false)
		{
		//move main board
		mb.move();
		
		//move ball
		b.move();
		
		if(this.ballFired == true)
		{
		this.checkCollisionwithmb();
		
		this.checkCollisionwithBricks();
		}
		
		this.checkCollisionwithGameWall();
		
		this.checkCounter();
		}
		
		
		
		
		
		
		
		
		//get rid of bricks
		//this.checkCollision();
		
		
		
		this.checkGameOver();
		
		this.checkGameWon();
		
	}
	
	// Updates the game on clock tick
	// modifies: this
	// effects:  updates tank, missiles and invaders
//	public void update() {
//		moveMissiles();
//		moveInvaders();
//		tank.move();
//		
//		checkMissiles();
//		invade();
//		checkCollisions();
//		checkGameOver();
//	}
	
	public boolean isWin()
	{
		return isWin;
	}
	
	public boolean isGameOver()
	{
		return isGameOver;
	}
	
	public List<Brick> getBricks()
	{
		return bricks;
	}
	
	
	
	public mainBoard getmb()
	{
		return mb;
	}
	
	//this is in charge of all the keyboard controls of the game
	public void boardControl(int keycode) //when u press key
	{
		if(keycode == KeyEvent.VK_X)
		{
			System.exit(0);
		}
		
		if(keycode == KeyEvent.VK_R && (this.isGameOver == true))
		{
			setUp();
		}
		
		if(  (this.isWin == true) && (keycode == KeyEvent.VK_R)    )
		{
			setUp();
		}
		
		if(this.isGameOver == false && this.isWin == false)
		{
		
		if(this.ballFired == false)
		{
		
			if(keycode == KeyEvent.VK_SPACE)
			{
			fireBall();
			}
		
		}
		
		if(keycode == KeyEvent.VK_X)
		{
			System.exit(0);
		}
		else
		{
			Control(keycode);
		}
		
	
		}
	}
	
	//this will control left/right direction of main board
	private void Control(int keycode)
	{
		if(keycode == KeyEvent.VK_LEFT || keycode == KeyEvent.VK_KP_LEFT )
		{
			mb.setdx(-10);
			
			mb.move();
			
			if( this.ballFired == false)
			{

				b.setX( (mb.getX() + mb.getX() + mainBoard.SIZE_X) / 2 - Ball.SIZE_X   );
			}
		}
		
		if(keycode == KeyEvent.VK_RIGHT || keycode == KeyEvent.VK_KP_RIGHT )
		{
			mb.setdx(10);
			
			mb.move();
			
			if( this.ballFired == false)
			{

				b.setX( (mb.getX() + mb.getX() + mainBoard.SIZE_X) / 2 - Ball.SIZE_X   );
			}
		}
	}
	

	
	//Checks for collision between ball and brick and then remove brick from play
//	private void checkCollision()
//	{
//		List<Brick> bricksToRemove =  new ArrayList<Brick>();
//		
//		for(Brick br : bricks)
//		{
//			if(b.collidedWith(br))
//			{
//				bricksToRemove.add(br);
//			}
//		}
//		
//		bricks.removeAll(bricksToRemove);
//	}
	
	private void checkCollisionwithBricks()
	{
		//List<Brick> bricksToRemove =  new ArrayList<Brick>();
		
		for(Brick br : bricks)
		{
			if(b.collidedWith(br))
			{
				if(b.hitTop(br)) //if ball hit top of brick
				{
					b.setdY(-3);
					b.move();
				}
				
				//if ball hits bot of brick
				if(b.hitBot(br))
				{
					b.setdY(3);
					b.move();
				}
				
				//if ball hits left side of brick
				if(b.hitLeft(br))
				{
					/**
					 * 2 cases: center of ball is higher but not equal to center of brick, center of ball is equal or lower than center of brick
					 */
					if(b.getcenterY() < ( br.getY() + br.getY() + Brick.SIZE_Y )/2   )
					{
						b.setdX(-3);
						b.setdY(-3);
						b.move();
					}
					else
					{
						b.setdX(-3);
						b.setdY(3);
						b.move();
					}
				}
				
				//if ball hits right side of brick
				/**
				 * 2 cases: center of ball is higher but not equal to center of brick, center of ball is equal or lower than center of brick
				 */
				if(b.hitRight(br))
				{
				
				if(b.getcenterY() < ( br.getY() + br.getY() + Brick.SIZE_Y )/2   )
				{
					b.setdX(3);
					b.setdY(-3);
					b.move();
				}
				else
				{
					b.setdX(3);
					b.setdY(3);
					b.move();
				}
				
				}
				
				
				bricksToRemove.add(br);
				//bricks.remove(br);
			}
		}
		
		bricks.removeAll(bricksToRemove);
	}
	
	//checks for which side of wall of game is hit, then translate the ball
	private void checkCollisionwithGameWall() 
	{	
		//hit top of screen
		
		if(b.getY() < 0)
		{
			b.setdY(3);
			b.move();
		}
		
		//hit left side of screen
		if( (b.getX()) < 0)
		{
			b.setdX(3);
			b.move();
		}
		
		//hit right side of screen
		if ( b.getX() > Game.WIDTH - Ball.SIZE_X )
		{
			b.setdX(-3);
			b.move();
		}
	}
	
	//checks to see which side of main board ball has collided with and move ball from there
	private void checkCollisionwithmb()
	{
		if(b.hitTopmb(mb) == true) //if ball hit top of main board
		{
			b.setdY(-3);
			b.move();
		}
		
		//check if ball hit left side of mainboard
		if( b.hitLeftSidemb(mb) )
		{
			//now there r 2 cases
			
			//if ball's center was higher than left side of mainboard's center, ball's direction would go towards 10 o'clock
			
			if( b.getcenterY() < ( mainBoard.Y_POS + (mainBoard.Y_POS + mainBoard.SIZE_Y)  ) /2     )
			{
				b.setdX(-3);
				b.setdY(-3);
				b.move();
			}
			else //this means, ball's center was lower than or equal to side of mainboard's center, ball will now move to 7 o'clock
			{
				b.setdX(-3);
				b.setdY(3);
				b.move();
			}
			
		}
		
		//check if ball hit right side of mainboard
		if(b.hitRightSidemb(mb) )
		{
			
			
			//if ball's center was higher than right side of mainboard's center, ball's direction would go towards 2 o'clock
			
			if( b.getcenterY() < ( mainBoard.Y_POS + (mainBoard.Y_POS + mainBoard.SIZE_Y)  ) /2     )
			{
				b.setdX(3);
				b.setdY(-3);
				b.move();
			}
			else //this means, ball's center was lower than or equal to side of mainboard's center, ball will now move to 4 o'clock
			{
				b.setdX(3);
				b.setdY(3);
				b.move();
			}
		}
	}
	
	//when ball goes below y-posn of mainboard, counter is increased, once counter = 3, its game over
	private void checkGameOver()
	{
		if( this.counter == 0 )
		{
			this.isGameOver = true;
		}
	
		if(this.isGameOver == true)
		{
			bricks.clear();
			this.b = null;
			this.mb = null;
			
		}
	}
	
	//this will check the counter, everytime the ball goes lower than the main baord's y-posn, counter go up
	private void checkCounter() 
	{
		
		if( b.getY() > mainBoard.Y_POS + mainBoard.SIZE_Y )
		{
			counter--;
			
			this.ballFired = false;
			this.initBall();
		}
		
		
		
	}
	
	//when there are no bricks left in the brick list, u won the game!!!!
	private void checkGameWon()
	{
		//if( bricks.size() == 0)
		if( this.bricksToRemove.size() == 9)
		{
			this.isWin = true;
		}
		
		if(this.isWin == true)
		{
			this.b = null;
			this.mb = null;
		}
	}
	
	//we want the main board to stop moving when we release our hands off the left/right directional keys
	public void releaseControl(int keyCode) 
	{
		if(keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_KP_LEFT )
		{
			mb.setdx(0);
			
			mb.move();
			
			if( this.ballFired == false)
			{

				b.setX( (mb.getX() + mb.getX() + mainBoard.SIZE_X) / 2 - Ball.SIZE_X   );
			}
		}
		
		if(keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_KP_RIGHT )
		{
			mb.setdx(0);
			
			mb.move();
			
			if( this.ballFired == false)
			{

				b.setX( (mb.getX() + mb.getX() + mainBoard.SIZE_X) / 2 - Ball.SIZE_X   );
			}
		}
		
	}

	public Ball getBall() 
	{
		
		return this.b;
	}

	public int getCounter() 
	{
		
		return counter;
	}

}
