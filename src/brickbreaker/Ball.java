package brickbreaker;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.*;
import java.awt.geom.Line2D;


//color database = javascripter.net/faq/rgntohex.htm
public class Ball 
{
	public static final Color bColor = new Color(255,255,0);
	
	public static final int SIZE_X = 20; //!!!
	
	public static final int SIZE_Y = 20; //!!!
	
	//TO DRAW CIRCLE, USE DRAW OVAL METHOD, SIZE_X = SIZE_Y
	
	
	private int dx;
	
	private int dy;
	
	private int x;
	private int y;
	
	private double centerX;
	private double centerY;
	
	private Ellipse2D B;
	
	//constructor
	public Ball(int x, int y)
	{
		this.x = x;
		
		this.y = y;
		
		centerX = ( x + x + (SIZE_X/2) ) / 2;
		
		centerY = ( y + y + (SIZE_Y/2) ) / 2;
		
		 B = new Ellipse2D.Double(this.getX(), this.getY(),
				Ball.SIZE_X, Ball.SIZE_Y);
	}
	
	public Ellipse2D getB()
	{
		return B;
	}
	
	//getters and setters
	public double getcenterX()
	{
		return centerX;
	}
	
	public double getcenterY()
	{
		return centerY;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setX(int x)
	{
		this.x = x;
		
	}
	
	public void setY(int y)
	{
		this.y = y;
		
	}
	
	public void setdX(int dx)
	{
		this.dx = dx;
		
	}
	
	public void setdY(int dy)
	{
		this.dy = dy;
		
	}
	
	public void move() //!!!
	{
		x= x + dx;
		y = y + dy;
		
		
	}
	
	
	
	

	
	//determines if a ball hits a certain brick b
	public boolean collidedWith(Brick b) 
	{
//		Ellipse2D e = new Ellipse2D.Double(0, 0, 80, 130);// create ellipse
//		// start at (0,0), width = 80 hiehgt = 130
		
		Rectangle BrickBoundingRect = new Rectangle(b.getX() , b.getY(), Brick.SIZE_X, Brick.SIZE_Y);
		
		Ellipse2D BallBoundingCirc = new Ellipse2D.Double(this.getX(), this.getY(),
				Ball.SIZE_X, Ball.SIZE_Y);
		
		//return BrickBoundingRect.intersects(missileBoundingRect);
		
		return BallBoundingCirc.getBounds().intersects(BrickBoundingRect.getBounds());
		
	}
	
	/**
	 * 
	 * Let's look at an example. Think the top square as the ball (I can't draw that nice). When the ball collides from left, 
	 * it should move right and if it's from right, then go left.

enter image description here

You can achieve this with a simple thing. Don't change the horizontal velocity but instead reverse the vertical one.

if (ball.getBounds().intersects(paddle.getBounds()))
{
    ball.setVy(-ball.getVy());
}
	 */
	
	//determines if ball hits top of the main board
	public boolean hitTopmb(mainBoard mb)
	{
		Ellipse2D bally = new Ellipse2D.Double(this.x, this.y, Ball.SIZE_X, Ball.SIZE_Y);
		
		Point2D startTopmb = new Point2D.Double(mb.getX(), mainBoard.Y_POS);
		
		Point2D endTopmb = new Point2D.Double(mb.getX()+ mainBoard.SIZE_X, mainBoard.Y_POS);
		
		Line2D  Topmb = new Line2D.Double(startTopmb,endTopmb);
		
		return bally.getBounds().intersectsLine(Topmb);
	}
	
	//determines if ball hits left side of mb
	public boolean hitLeftSidemb(mainBoard mb)
	{
		Ellipse2D bally = new Ellipse2D.Double(this.x, this.y, Ball.SIZE_X, Ball.SIZE_Y);
		
		Point2D startLeftmb = new Point2D.Double(mb.getX(), mainBoard.Y_POS);
		
		Point2D endLeftmb = new Point2D.Double(mb.getX(), mainBoard.Y_POS + mainBoard.SIZE_Y);
		
		Line2D  Leftmb = new Line2D.Double(startLeftmb,endLeftmb);
		
		
		return bally.getBounds().intersectsLine(Leftmb);
	}
	
	//determines if ball hits right side of mb
	public boolean hitRightSidemb(mainBoard mb)
	{
		Ellipse2D bally = new Ellipse2D.Double(this.x, this.y, Ball.SIZE_X, Ball.SIZE_Y);
		
		Point2D startRightmb = new Point2D.Double(mb.getX()+mainBoard.SIZE_X, mainBoard.Y_POS);
		
		Point2D endRightmb = new Point2D.Double(mb.getX()+mainBoard.SIZE_X, mainBoard.Y_POS + mainBoard.SIZE_Y);
		
		Line2D  Rightmb = new Line2D.Double(startRightmb,endRightmb);
		
		
		return bally.getBounds().intersectsLine(Rightmb);
	}
	
	//determines if the bottom of the brick is hit by ball
	public boolean hitBot(Brick b)
	{
		Ellipse2D bally = new Ellipse2D.Double(this.x, this.y, Ball.SIZE_X, Ball.SIZE_Y);
		
		Point2D startbot = new Point2D.Double(b.getX() , b.getY() + Brick.SIZE_Y);
		
		Point2D endbot = new Point2D.Double(b.getX()+Brick.SIZE_X, b.getY()+Brick.SIZE_Y);
		
		Line2D  bot = new Line2D.Double(startbot,endbot);
		
		
		return bally.getBounds().intersectsLine(bot);
	}
	
	//determines if top of the brick is hit by the ball
	public boolean hitTop(Brick b)
	{
		Ellipse2D bally = new Ellipse2D.Double(this.x, this.y, Ball.SIZE_X, Ball.SIZE_Y);
		
		Point2D starttop = new Point2D.Double(b.getX() , b.getY() );
		
		Point2D endtop = new Point2D.Double(b.getX()+Brick.SIZE_X, b.getY() );
		
		Line2D  top = new Line2D.Double(starttop,endtop);
		
		
		return bally.getBounds().intersectsLine(top);
	}
	
//	if(  ( this.x   ) < mb.getX() + (Ball.SIZE_X + 1)    )
//	{
//		
//		//if(   ( this.x + Ball.SIZE_X - 1 ) >= mb.getX() - 5     ) //-4 for now
//		if(   ( this.x  ) >= mb.getX() - Ball.SIZE_X - 1     )
//		{
//			return true;
//		}
//		
//	}
	
	//determine if left side of brick is hit by the ball
	public boolean hitLeft(Brick b)
	{
		Ellipse2D bally = new Ellipse2D.Double(this.x, this.y, Ball.SIZE_X, Ball.SIZE_Y);
		
		Point2D startLeft = new Point2D.Double(b.getX() , b.getY() );
		
		Point2D endLeft = new Point2D.Double(b.getX() , b.getY() + Brick.SIZE_Y );
		
		Line2D  left = new Line2D.Double(startLeft,endLeft);
		
		
		return bally.getBounds().intersectsLine(left);
	}
	
	//determines if right side of brick is hit by the ball
	public boolean hitRight(Brick b)
	{
		Ellipse2D bally = new Ellipse2D.Double(this.x, this.y, Ball.SIZE_X, Ball.SIZE_Y);
		
		Point2D startRight = new Point2D.Double(b.getX() + Brick.SIZE_X , b.getY() );
		
		Point2D endRight = new Point2D.Double(b.getX() + Brick.SIZE_X , b.getY() + Brick.SIZE_Y );
		
		Line2D  right = new Line2D.Double(startRight,endRight);
		
		
		return bally.getBounds().intersectsLine(right);
	}
}
