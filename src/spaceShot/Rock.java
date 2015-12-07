//Astroid model
package spaceShot;

import java.awt.*;
import javax.swing.*;

public class Rock extends JComponent {
	final static int LEFT = 0;
	final static int RIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	final static int TOP = 0;
	final static int BOTTOM = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	int x[] = {100, 120, 150, 170, 190, 150, 120};
	int y[] = {50, 75, 50, 20, 10, 25, 35};
	Polygon rock = new Polygon(x,y,x.length);
	
	int[] position = {145, 30};
	
	public Rock(){
		setBounds(x[0], y[1], 50, 50);
	}
	
	
	public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.GREEN);
        g.fillPolygon(rock);
		setBounds(x[0], y[1], 50, 50);
	}
	
	/*
	public int[] updatePosition(int deltaX, int deltaY){
		if(validUpdate(deltaX, deltaY)){
			
			x[0] = x[0]+deltaX;
			x[1] = x[1]+deltaX;
			x[2] = x[2]+deltaX;
			
			y[0] = y[0]+deltaY;
			y[1] = y[1]+deltaY;
			y[2] = y[2]+deltaY;
			
			int[] position = {x[1], y[1]/2};
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
	*/
}
