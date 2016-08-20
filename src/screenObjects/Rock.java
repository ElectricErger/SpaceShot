//Astroid model
package screenObjects;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

import spaceShot.Space;

public class Rock{
	int left, top;
	int x[];
	int y[];
	int speed = 10;
	int rad = 40;
	int numberOfEdges;
	Polygon rock;
	int positionIndex;
	boolean destroyed;
	
	//Issue: Rect need's top left point (not assured)
	//Issue: Polygons shouldn't cross over
	//SOLUTION: Generate using radial formulas
	
	public Rock(){
		Random ran = new Random();
		
		int initialX = ran.nextInt(Space.RIGHT);
		
		//Make the rock itself
		numberOfEdges = ran.nextInt(20)+5;
		this.x = new int[numberOfEdges]; Arrays.fill(this.x, initialX);
		this.y = new int[numberOfEdges];
		int degreePerEdge = 360/numberOfEdges;
		int degreeOffset = ran.nextInt(degreePerEdge); //So the first coordinate isn't always the same
		
		for(int i=0; i<numberOfEdges; i++){
			int radius = ran.nextInt(rad)+rad/2; //We can double the radius this way sadly
			double theta = (degreePerEdge*i + degreeOffset)*Math.PI/180;
			this.x[i] += (int) (radius*Math.cos(theta));
			this.y[i] = (int) (radius*Math.sin(theta));
		}
		
		//Finalize the properties
		positionIndex = numberOfEdges/2;
		rock = new Polygon(this.x, this.y, numberOfEdges);
		speed += ran.nextInt(3);
		destroyed = false;
	}

	public void updatePosition(){
		for(int i = 0; i<this.x.length; i++){
			this.y[i] += speed;
		}
		rock = new Polygon(x, y, x.length);
	}
	
	public Polygon getImage(){ return rock; }
	public void printRock(Graphics g){	g.fillPolygon(rock); }
	public Rectangle getBox(){
		return new Rectangle(
				x[positionIndex],
				y[positionIndex]-rad,
				rad*2,
				rad*2);
	}

	public int getX(){ return x[0]; }
	public int getY(){ return y[0]; }
	public boolean isDestroyed(){ return destroyed; }
	public void setDestroyed(){ destroyed = true; }
}
