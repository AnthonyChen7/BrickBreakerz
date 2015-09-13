package brickbreaker;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

//import ca.ubc.cpsc210.spaceinvaders.model.Missile;

public class Brick 
{
	private int x;
	private int y;
	
	public static final int SIZE_X = 55;
	
	public static final int SIZE_Y = 10;
	
	//need width and height of brick
	
	public static final Color brickColor = new Color(255,0,255);
	
	public Brick(int x, int y)
	{
		this.x = x;
		
		this.y = y;
		
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
	

}
