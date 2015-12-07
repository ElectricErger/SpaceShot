//The view
package spaceShot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.JPanel;

public class Space extends JPanel{

	final static int RIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	final static int BOTTOM = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	Random ran = new Random();
	Logo logo;
	Space(){
		SpaceShip ship = new SpaceShip(100,100);
		logo = new Logo(100, 100);
		setBackground(Color.BLACK);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		stars(g);
		
		int[] ax = {RIGHT/2-50,RIGHT/2,RIGHT/2+50}; //WARNING! RIGHT NOW POSSITION IS NOT A FUNCTION OF SHIP
		int[] ay = {BOTTOM-50,BOTTOM-100,BOTTOM-50};
		g.setColor(Color.CYAN);
		g.fillPolygon(new Polygon(ax,ay,3));
		
		g.drawImage(logo.getImage(), 550, 100, null);
	}
	private void stars(Graphics g){
		g.setColor(Color.WHITE);
		for(int i = 0; i< RIGHT; i=i+100){
			for(int j = 0; j< BOTTOM; j=j+100){
				g.fillOval(i-50+ran.nextInt(100), j-50+ran.nextInt(100), 3, 3);
			}
		}
	}
}
