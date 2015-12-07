//The view
package spaceShot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class Space extends JPanel implements ActionListener{

	final static int RIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	final static int BOTTOM = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	Random ran = new Random();
	Logo logo;
	SpaceShip ship;
	int sensitive = 50;
	ArrayList bullets = new ArrayList();
	ArrayList stars = new ArrayList();
	Timer time = new Timer(50, this); //Requires implementing actionListener ...not sure why. Read up on it
	
	Space(){
		ship = new SpaceShip(RIGHT/2, BOTTOM-50, Color.CYAN);
		logo = new Logo(100, 100);
		setBackground(Color.BLACK);
		listeners();
		generateStars();
		time.start();
	}
	
	//PAINTING PROTOCOL
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		stars(g);
		
		g.setColor(ship.getColor());
		g.fillPolygon(ship.getImage());
		
		g.drawImage(logo.getImage(), (RIGHT-logo.getImage().getWidth(null))/2, (BOTTOM-logo.getImage().getHeight(null))/4, null);
		bullets(g);
	}

	private void generateStars(){
		for(int i = 0; i< RIGHT; i=i+100){
			for(int j = 0; j< BOTTOM; j=j+100){
				stars.add(new Star(i-50+ran.nextInt(100), j-50+ran.nextInt(100), ran.nextInt(3)+1));
			}
		}
	}
	
	//Make stars -- These should be objects in the future because they will update
	private void stars(Graphics g){
		g.setColor(Color.WHITE);
		for(int i = 0; i<stars.size(); i++){
			Star temp = (Star) stars.get(i);
			int[] dim = temp.getStar();
			g.fillOval(dim[0], dim[1], dim[2], dim[2]);
		}
	}
	private void bullets(Graphics g){
		g.setColor(Color.YELLOW);
		for(int i = 0; i<bullets.size(); i++){
			Bullet temp = (Bullet) bullets.get(i);
			int[] loc = temp.getBullet();
			g.fillRect(loc[0], loc[1], 5, 15);
		}
	}
	
	
	//Temporary controller
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
				bullets.add(new Bullet(ship.getX(), ship.getY()));
				ship.getY();
				repaint();
			}
		}
		);
		
	}
	
	public void actionPerformed(ActionEvent e){
		for(int i = 0; i<bullets.size(); i++){
			Bullet bullet = (Bullet) bullets.get(i);
			bullet.updatePosition(0, -15);
			System.out.println("TEST");
		}
		for(int i = 0; i<stars.size(); i++){
			Star star = (Star) stars.get(i);
			star.updatePosition(0, 5);
			
			if(star.getStar()[1]>BOTTOM){
				star.reset();
			}
		}
		repaint();
	}
}
