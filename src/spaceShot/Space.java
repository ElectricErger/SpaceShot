//The view
package spaceShot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Space extends JPanel{

	final static int RIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	final static int BOTTOM = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	Random ran = new Random();
	Logo logo;
	SpaceShip ship;
	int sensitive = 10;
	ArrayList bullets;
	
	Space(){
		ship = new SpaceShip(RIGHT/2, BOTTOM-50, Color.CYAN);
		logo = new Logo(100, 100);
		setBackground(Color.BLACK);
		listeners();
	}
	
	//PAINTING PROTOCOL
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		stars(g);
		
		g.setColor(ship.getColor());
		g.fillPolygon(ship.getImage());
		
		g.drawImage(logo.getImage(), 550, 100, null);
	}
	//Make stars -- These should be objects in the future because they will update
	private void stars(Graphics g){
		g.setColor(Color.WHITE);
		for(int i = 0; i< RIGHT; i=i+100){
			for(int j = 0; j< BOTTOM; j=j+100){
				g.fillOval(i-50+ran.nextInt(100), j-50+ran.nextInt(100), 3, 3);
			}
		}
	}
	
	private void listeners(){
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "Move Up");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "Move Down");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "Move Left");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "Move Right");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "Fire");
		
		this.getActionMap().put("Move Up", new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				ship.updatePosition(0, -sensitive);
				repaint();
			}
		}
		);		
		this.getActionMap().put("Move Down", new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				ship.updatePosition(0, sensitive);
				repaint();
			}
		}
		);		
		this.getActionMap().put("Move Left", new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				ship.updatePosition(-sensitive, 0);
				repaint();
			}
		}
		);		
		this.getActionMap().put("Move Right", new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				ship.updatePosition(sensitive, 0);
				repaint();
			}
		}
		);
		this.getActionMap().put("Fire", new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				bullets.add(new Bullet(ship.getX()+25, ship.getY()+25));
				repaint();
			}
		}
		);
	}
}
