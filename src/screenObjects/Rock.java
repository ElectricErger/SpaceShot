//Astroid model
package screenObjects;

import java.awt.*;
import java.util.Random;

public class Rock{
	
	int x[];
	int y[];
	int speed = 10;
	int width = 80;
	int height = 80;
	int polygonSize;
	Polygon rock;
	
	public Rock(){
		Random ran = new Random();
		
		polygonSize = ran.nextInt(20)+5;
		this.x = new int[polygonSize];
		this.y = new int[polygonSize];
		
		for(int i=0; i<polygonSize; i++){
			this.x[i] = ran.nextInt(width);
			this.y[i] = ran.nextInt(height);
		}
		
		rock = new Polygon(this.x, this.y, polygonSize);
		speed += ran.nextInt(3);
	}

	public void updatePosition(){
		for(int i = 0; i<this.x.length; i++){
			this.y[i] += speed;
		}
		rock = new Polygon(x, y, x.length);
	}
	
	public Polygon getImage(){
		return rock;
	}
	public void printRock(Graphics g){
		g.fillPolygon(rock);
	}
	public Rectangle getBox(){
		return new Rectangle(x[0],y[0]-height/2, width, height);
	}
}
