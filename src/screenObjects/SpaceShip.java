//Spaceship model
package screenObjects;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.*;

import state.GameState;

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
	
	int speed = 1;
	int dxMax, dyMax;
	int dx, dy;
	boolean l, r, u, d;
	boolean inertialDampener; //T=Stop pressing key=stop moving, F=Maintain momentum

	int bulletSpeed = 20;
	static ArrayList<Bullet> bullets;
	int bulletLevel	;
	
	//Issue: Tighten controls - when dx is turned on accel until you reach max speed
	//	When the off signal is found dx is turned off causing a decel
	//	When reversed dx in inverted allowing the same type of accel

	//We keep the position, and the polygon
	public SpaceShip(int x, int y, Color c){
		dxMax = dyMax = 30;
		dx = dy = 0;
		l = r = u = d = false;
		
		position[0] = x;
		position[1] = y; 
		this.x = new int[] {position[0]-25,position[0],position[0]+25};
		this.y = new int[] {position[1]+25,position[1]-25,position[1]+25};
		this.c = c;
		ship = new Polygon(this.x,this.y,this.x.length);
		//super (x,y); //Passes the arguments to sprite. Now we have met our extend requirements and have a proper sprite
		
		bullets = new ArrayList<Bullet>();
		inertialDampener = false; 
	}
	
	//GETTERS
	public Color getColor(){ return c; }
	public Polygon getImage(){ return ship; }
	public int getX(){ return position[0]; }
	public int getY(){ return position[1]; }
	public int getXVelocity(){ return dx; }
	public int getYVelocity(){ return dy; }
	public Rectangle getBox(){
		return new Rectangle(position[0]-25, position[1]-25, 50, 50);
	}
	public ArrayList<Bullet> getBullets() { return bullets; }
	
	//MOVEMENT
	
	/* Possible combination of actions:
	 * 	Press 1 direction - Move that direction
	 * 	Release 1 direction - Stop moving
	 * 	Press 2 orthogonal directions - Move both directions
	 * 	Press 2, release 1 - Move in the 1 direction pressed
	 * 	Press 2 colinear directions - Don't move
	 * 	Press 2 colinear, release 1 - Move the one direction
	 *  
	 * */
	
	//Moves are called on key press
	public void moveLeft(){ l = true; }
	public void moveUp(){ u = true; }
	public void moveRight(){ r = true; }
	public void moveDown(){ d = true; }
	//Stops are called on key release
	public void stopLeft(){ l = false; }
	public void stopUp(){ u = false; }
	public void stopRight(){ r = false; }
	public void stopDown(){ d = false; }
	
	//Updates position on clock tick
	public void updatePosition(){
		update_dx();
		if(validUpdate_dx()){
			x[0] += dx;
			x[1] += dx;
			x[2] += dx;
		}

		update_dy();
		if(validUpdate_dy()){
			y[0] += dy;
			y[1] += dy;
			y[2] += dy;
		}
		
		position[0] = x[1];
		position[1] = y[1]+25;
		ship = new Polygon(x,y,x.length); // recreates the polygon in a new space
	}
	//Determine what the new dx and dy should be
	public void update_dx(){
		if(inertialDampener){ //Key = Motion
			if((l && r) || (!l && !r)){ dx = 0; }
			else if(l && !r){ dx = -speed; }
			else { dx = speed; }
			
		} else{ //Maintain momentum
			//l&&r and !(l&&r) both make no change
			if( l && !r){
				if( dx > -dxMax )	dx -= speed;
			} else if( !l && r){
				if( dx < dxMax) dx += speed;
			}
		}
	}
	public void update_dy(){
		if(inertialDampener){
			if((u && d) || (!u && !d)){ dy = 0; }
			else if(u & !d){ dy = -speed; }
			else { dy = speed; }
		} else {
			if( u && !d){
				if( dy > -dyMax) dy -= speed;
			} else if( !u && d){
				if( dy < dyMax ) dy += speed;
			}
		}
	}	
	//Determine if we can update, or will we fly off screen
	private boolean validUpdate_dx(){
		if((dx+x[0] > LEFT) && (dx+x[2] < RIGHT)){
			return true;
		}
		return false; //it's not okay
	}
	private boolean validUpdate_dy(){
		if((dy+y[0] > TOP) && (dy+y[2] < BOTTOM)){
			return true; //It's all good
		}
		return false; //it's not okay
	}

	
	//SHOOTING FUNCTIONS
	
	//On clock tick, what do we want to change about our bullets
	public void shoot(boolean fire) {
		if(fire){
			bullets.add(new Bullet(this.getX(), this.getY()));
			bullets.add(new Seaker(this.getX(), this.getY()));
			GameState.sound.laserSound();
		}
		for(int i = 0; i<bullets.size(); i++){
			Bullet bullet = (Bullet) bullets.get(i);
			bullet.updatePosition(0, -bulletSpeed);
			
			if(bullet.getBullet()[1]<0){
				bullets.remove(i);
			}
		}
		
	}

}
