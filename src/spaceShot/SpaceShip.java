//Spaceship model
package spaceShot;

import java.awt.*;

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
	
	public int[] updatePosition(int deltaX, int deltaY){
		if(validUpdate(deltaX, deltaY)){
			
			x[0] = x[0]+deltaX;
			x[1] = x[1]+deltaX;
			x[2] = x[2]+deltaX;
			
			y[0] = y[0]+deltaY;
			y[1] = y[1]+deltaY;
			y[2] = y[2]+deltaY;
			
			position[0] = x[1];
			position[1] = y[1];

			ship = new Polygon(x,y,x.length); // recreates the polygon in a new space
			return position;
		}
		return position;
	}
	
	private boolean validUpdate(int deltaX, int deltaY){
		if((deltaX+x[0] > LEFT) && (deltaX+x[2] < RIGHT)){
			if((deltaY+y[0] > TOP) && (deltaY+y[2] < BOTTOM)){
				return true; //It's all good
			}
		}
		return false; //it's not okay
	}
	

	public Rectangle getBox(){
		return new Rectangle(position[0]-25, position[1]-25, 50, 50);
	}
}
