//Astroid model
package spaceShot;

import java.awt.*;
import javax.swing.*;

public class Rock {
	final static int LEFT = 0;
	final static int RIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	final static int TOP = 0;
	final static int BOTTOM = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	int[] position = {50,50};
	Polygon rock;
	int x[] = {0, 30, 60, 75, 90, 65, 40, 20};
	int y[] = {50, 0, 0, 40, 50, 90, 70, 80};
	
	public Rock(int x, int y){
		position[0] = x;
		position[1] = y;
		this.x = new int[] {x-50, x-20, x+10, x+25, x+40, x+15, x-10, x-30};
		this.y = new int[] {y, y-50, y-50, y-10, y, y+40, y+20, y+30};
		rock = new Polygon(this.x, this.y, this.x.length);
	}

	
	public int[] updatePosition(int deltaX, int deltaY){
		for(int i = 0; i<this.x.length; i++){
			this.x[i] += deltaX;
			this.y[i] += deltaY;
		}
		
		position[0] = x[4]-40;
		position[1] = y[0];
		rock = new Polygon(x, y, x.length);
		return position;
	}
	
	public Polygon getImage(){
		return rock;
	}
	
	public int[] getRock(){
		return position;
	}
	
	public Rectangle getBox(){
		return new Rectangle(x[0],y[1], 90, 90);
	}
}
