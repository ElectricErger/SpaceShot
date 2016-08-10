//Spaceship model
package screenObjects;

import java.awt.*;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class SpaceShip{ //In the future extend from Sprite
	final static int LEFT = 0;
	final static int RIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	final static int TOP = 0;
	final static int BOTTOM = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	int[] position = {RIGHT/2, BOTTOM-30};
	int x[];
	int y[];
	Color c;
	Polygon ship;
	int dx = 0;
	int dy = 0;
	
	//We keep the position, and the polygon
	public SpaceShip(int x, int y, Color c){
		position[0] = x;
		position[1] = y; 
		this.x = new int[] {position[0]-25,position[0],position[0]+25};
		this.y = new int[] {position[1]+25,position[1]-25,position[1]+25};
		this.c = c;
		ship = new Polygon(this.x,this.y,this.x.length);
		//super (x,y); //Passes the arguments to sprite. Now we have met our extend requirements and have a proper sprite
	}
	
	public Color getColor(){
		return c;
	}
	public Polygon getImage(){
		return ship;
	}
	public int getX(){
		return position[0];
	}
	public int getY(){
		return position[1];
	}
	public int getXVelocity(){
		return dx;
	}
	public int getYVelocity(){
		return dy;
	}
	
	public void moveX(int x){
		dx += x;
	}
	public void moveY(int y){
		dy += y;
	}
	


	public void updatePosition(){
		//System.out.println("Current position: "+ position[0] + " of " + RIGHT);
		if(validUpdate(dx, dy)){
			x[0] += dx;
			x[1] += dx;
			x[2] += dx;
			
			y[0] += dy;
			y[1] += dy;
			y[2] += dy;
			
			position[0] = x[1];
			position[1] = y[1]+25;

			//System.out.println("Update: " + (double)position[0]/(double)RIGHT);
			ship = new Polygon(x,y,x.length); // recreates the polygon in a new space
		}
	}
	
	private boolean validUpdate(int deltaX, int deltaY){
		//System.out.print("Position: ("+position[0]+","+position[1]+")");
		if((deltaX+x[0] > LEFT) && (deltaX+x[2] < RIGHT)){
			if((deltaY+y[0] > TOP) && (deltaY+y[2] < BOTTOM)){
				return true; //It's all good
			}
		}
	//	System.out.println();
		return false; //it's not okay
	}


	public Rectangle getBox(){
		return new Rectangle(position[0]-25, position[1]-25, 50, 50);
	}
}
