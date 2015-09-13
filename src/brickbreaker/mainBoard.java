package brickbreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
//Later Java created Graphics 2D, which extends from graphics
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class mainBoard
{
	//public static final int DX = 2; //!!!
	
	public static final int SIZE_X = 110; //!!!
	
	public static final int SIZE_Y = 60; //!!! originally 10
	
	public static final int Y_POS = 550; //!!!
	
	public static final Color mbColor = new Color(124,252,0); //!!!
	
	private int x;
	
	private int dx;
	
	//constructs main board, makes main board at posn (x, Y_POS)
	public mainBoard(int x)
	{
		this.x = x;
	}
	
	public int getX()
	{
		return x;
	}
	
	public void setX(int x)
	{
		this.x = x;
		
		handleBoundary();
	}
	
	public int getdx()
	{
		return dx;
	}
	
	public void setdx(int dx)
	{
		this.dx = dx;
	}
	
	public void move()
	{
		x = x + dx;
		
		handleBoundary();
	}
	
	
	
	public void handleBoundary()
	{
		if( (this.x < 0) )
		{
			//System.out.println("x is " + this.x);
			
			x = 0;
			
			//System.out.println("x is now " + this.x);
			
		}
		else
		{
			if(x + SIZE_X  > Game.WIDTH) //!!!
			{
				//System.out.println("x is " + this.x);
				x   = Game.WIDTH - SIZE_X; //!!!
				//System.out.println("x is now " + this.x);
			}
		}
	}
	
	//determines if ball hits top of board
//	public boolean ballHitTop(Ball b)
//	{
//		
//	}
	
//	public void keyPressed(java.awt.event.KeyEvent e) //deal with when main board is moving
//	{
//		int key = e.getKeyCode();
//		
//		if(key == KeyEvent.VK_LEFT)
//		{
//			dx = -1;
//		}
//		
//		if(key == KeyEvent.VK_RIGHT)
//		{
//			dx = 1;
//		}
//	}
	
	//now we deal with when we release the left/right key, we will want mainbaord to stop moving
	
	
	
}
