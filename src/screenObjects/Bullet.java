package screenObjects;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.Random;

import state.InGame;

public class Bullet {
	//Consider making dx and dy constants for the class
	int[] position = {0,0};
	
	public Bullet(int x, int y){
		position[0] = x;
		position[1] = y;
	}
	
	public void paint(Graphics g){ g.fillRect(position[0], position[1], 5, 15); }
	
	public void updatePosition(int dx, int dy){
		position[0] += dx;
		position[1] += dy;
	}
	
	public int[] getBullet(){
		return position;
	}
	
	public Rectangle getBox(){
		return new Rectangle(position[0], position[1], 5, 15);
	}
}

class Seaker extends Bullet{
	Polygon bullet;
	Rock target;
	
	public Seaker(int x, int y) {
		super(x, y);
		
		int rockSize = InGame.rocks.size();
		Random gen = new Random();
		target = InGame.rocks.get(gen.nextInt(rockSize));
	}
	
	@Override
	public void paint(Graphics g){
			bullet = new Polygon(
					new int[] {position[0], position[0]+5, position[0]+10, position[0]+5},
					new int[] {position[1], position[1]-5, position[1], position[1]-2},
					4);
			g.fillPolygon(bullet);
		}
	
	@Override
	public void updatePosition(int dx, int dy){
		if(targetLost()){
			SpaceShip.bullets.remove(this);
		}
		
		//dx and dy is the total distance, we should reassign this based off what we need
		int vectorMag = (int) Math.sqrt(dx*dx + dy*dy);

		//From "position" to "target" what's our heading
		int targetX = target.getX();
		int targetY = target.getY();		

		//Right triangle
			//Go in this direction
		int distanceX = targetX - position[0];
		int distanceY = targetY - position[1];
			//On a screen it's +90's from a graph
		double theta = Math.atan((double)distanceY/distanceX);
		if(distanceX < 0){ theta += 180; } //Arctan only goes into quadrants I and IV, II and III are +180
		
		//Multiply heading by vector length
		position[0] += (int) (vectorMag*Math.cos(theta)); 
		position[1] += (int) (vectorMag*Math.sin(theta));
	}
	
	public boolean targetLost(){ return target.isDestroyed(); }
}
