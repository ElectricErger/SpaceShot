package spaceShot;

import java.awt.*;
import javax.swing.*;

public class SpaceShip extends JComponent {
	final static int LEFT = 0;
	final static int RIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	final static int TOP = 0;
	final static int BOTTOM = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	int[] position = {RIGHT/2, BOTTOM-30};
	int x[] = {position[0]-25,position[0],position[0]+25};
	int y[] = {position[1]+25,position[1]-25,position[1]+25};
	Polygon ship = new Polygon(x,y,x.length);
	
	
	public SpaceShip(){
		setBounds(x[0], y[1], 50, 50);
	}
	
	
	public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.CYAN);
        g.fillPolygon(ship);
		setBounds(x[0], y[1], 50, 50);
	}
	
	public int[] updatePosition(int deltaX, int deltaY){
		if(validUpdate(deltaX, deltaY)){
			
			x[0] = x[0]+deltaX;
			x[1] = x[1]+deltaX;
			x[2] = x[2]+deltaX;
			
			y[0] = y[0]+deltaY;
			y[1] = y[1]+deltaY;
			y[2] = y[2]+deltaY;
			
			int[] position = {x[1], y[1]/2};
			setBounds(x[0], y[1], 50, 50);
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
}
