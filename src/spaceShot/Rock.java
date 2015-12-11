//Astroid model
package spaceShot;

import java.awt.*;
import java.util.Random;

import javax.swing.*;

public class Rock {
	final static int LEFT = 0;
	final static int RIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	final static int TOP = 0;
	final static int BOTTOM = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	int[] position = {50,50};
	Polygon rock;
	int x[];
	int y[];
	int speed = 10;
	int width = 80;
	int height = 80;
	
	public Rock(int x, int y){
		Random ran = new Random();
		position[0] = x;
		position[1] = y;
		this.x = new int[]{
				x-width/2,
				x-width/2+ran.nextInt(20),
				x-width/2+20+ran.nextInt(20),
				x-width/2+50+ran.nextInt(30),
				x+width/2,
				x+width/2-ran.nextInt(20),
				x+width/2-30-ran.nextInt(20),
				x+width/2-50-ran.nextInt(30)
				};
		this.y = new int[]{
				y,
				y-height/2+10+ran.nextInt(20),
				y-height/2,
				y-height/2+10+ran.nextInt(20),
				y,
				y+height/2-ran.nextInt(20),
				y+height/2-ran.nextInt(10),
				y+height/2-10-ran.nextInt(20),
		};
		rock = new Polygon(this.x, this.y, this.x.length);
		speed += ran.nextInt(3);
	}

	public void updatePosition(){
		for(int i = 0; i<this.x.length; i++){
			this.y[i] += speed;
		}
		
		position[1] = y[0];
		rock = new Polygon(x, y, x.length);
	}
	
	public Polygon getImage(){
		return rock;
	}
	
	public int[] getRock(){
		return position;
	}
	
	public Rectangle getBox(){
		return new Rectangle(x[0],y[0]-height/2, width, height);
	}
}
