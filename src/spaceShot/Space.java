//The view
package spaceShot;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
	int sensitive = 10;
	ArrayList<Bullet> bullets = new ArrayList();
	ArrayList<Star> stars = new ArrayList();
	ArrayList<Rock> rocks = new ArrayList();
	Timer time = new Timer(50, this); //Requires implementing actionListener ...not sure why. Read up on it
	Timer ast = new Timer(100, this);
	boolean title = true;
	boolean gameOver = false;
	int score = 0;
	boolean fire = false;
	
	Space(){
		ship = new SpaceShip(RIGHT/2, BOTTOM-50, Color.CYAN);
		logo = new Logo(100, 100);
		setBackground(Color.BLACK);
		//listeners();
		generateStars();
		time.start();
		(new astr()).start();
		//Start listener...I still need this?
		addKeyListener(new listen());
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Start");
		this.getActionMap().put("Start",  new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				title = false;
				}
			}
		);
	}
	
	//PAINTING PROTOCOL
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		stars(g);
		ship(g);
		rocks(g);
		bullets(g);
		
		String scoreStr = "Score: "+score;
		g.setFont(new Font("MONOSPACED", Font.PLAIN, 40));
		g.drawString(scoreStr, (RIGHT-g.getFontMetrics().stringWidth(scoreStr)), 40);
		
		if (title){
			g.drawImage(logo.getImage(), (RIGHT-logo.getImage().getWidth(null))/2, (BOTTOM-logo.getImage().getHeight(null))/4, null);
			//Press enter to start
			g.setFont(new Font("MONOSPACED", Font.PLAIN, 40));
			String s = "Press Enter To Start";
			g.drawString(s, (RIGHT-g.getFontMetrics().stringWidth(s))/2, (BOTTOM)*3/4);
		}
		if(gameOver){
			String s = "GAME OVER";
			g.setFont(new Font("MONOSPACED", Font.PLAIN, 60));
			g.drawString(s, (RIGHT-g.getFontMetrics().stringWidth(s))/2, BOTTOM/2);
		}
	}

	private void generateStars(){
		for(int i = 0; i< RIGHT; i=i+100){
			for(int j = 0; j< BOTTOM; j=j+100){
				stars.add(new Star(i-50+ran.nextInt(100), j-50+ran.nextInt(100), ran.nextInt(3)+1));
			}
		}
	}
	
	//Print the things to screen
	private void ship(Graphics g){
		g.setColor(ship.getColor());
		g.fillPolygon(ship.getImage());
	}
	private void stars(Graphics g){
		g.setColor(Color.WHITE);
		for(Star s : stars){
			int[] dim = s.getStar();
			g.fillOval(dim[0], dim[1], dim[2], dim[2]);
		}
	}
	private void bullets(Graphics g){
		g.setColor(Color.YELLOW);
		for(Bullet b : bullets){
			int[] loc = b.getBullet();
			g.fillRect(loc[0], loc[1], 5, 15);
		}
	}
	
	private void rocks(Graphics g){
		g.setColor(Color.GRAY);
		for(Rock r : rocks){
			g.fillPolygon(r.getImage());
		}
	}
	

	//Temporary controller
	private void listeners(){
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "Move Up");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "Move Down");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "Move Left");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "Move Right");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "Fire");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Start");
		
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
				repaint();
			}
		}
		);
		this.getActionMap().put("Start",  new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				title = false;
			}
		});
		
	}


	
	//Updater
	public void actionPerformed(ActionEvent e){
		shipUpdater();
		bulletUpdater();
		asteroidUpdater();
		starUpdater();
		collisions();
		repaint();
	}
	
	private void shipUpdater(){
		ship.updatePosition();
	}
	private void bulletUpdater(){
		if(fire){
			bullets.add(new Bullet(ship.getX(), ship.getY()));
		}
		for(int i = 0; i<bullets.size(); i++){
			Bullet bullet = (Bullet) bullets.get(i);
			bullet.updatePosition(0, -15);
			
			if(bullet.getBullet()[1]<0){
				bullets.remove(i);
			}
		}
	}
	private void starUpdater(){
		for(int i = 0; i<stars.size(); i++){
			Star star = (Star) stars.get(i);
			star.updatePosition(0, 5);
			
			if(star.getStar()[1]>BOTTOM){
				star.reset();
			}
		}
	}
	private void asteroidUpdater(){
		for(int i = 0; i<rocks.size(); i++){
			Rock rock = rocks.get(i);
			rock.updatePosition(0, 10);
			
			if(rock.getRock()[1]>BOTTOM){
				rocks.remove(i);
			}
		}
	}
	private void collisions(){
		//Collisions between asteroids and bullets
		for(int r = 0; r<rocks.size(); r++){
			Rock rockCol = rocks.get(r);
			for(int b = 0; b<bullets.size(); b++){ //We did it
				if(rockCol.getBox().intersects(bullets.get(b).getBox())){
					rocks.remove(r);
					bullets.remove(b);
					score++;
				}
			}
			if(rockCol.getBox().intersects(ship.getBox())){ //You lose
				gameOver = true;
				bullets = new ArrayList();
				ship = new SpaceShip(RIGHT/2, BOTTOM-50, Color.CYAN);
				rocks = new ArrayList();
			}
		}
	}

	
	//New thread for making astroids
	class astr extends Thread{
		public void run(){
			while (true){
				if(rocks.size()<10 && !title && !gameOver){
					rocks.add(new Rock(ran.nextInt(RIGHT), -20));
				}
				try {Thread.sleep(ran.nextInt(10)*100);}
				catch (InterruptedException e) {}
			}
		}
	}
	//A class needed to call keys
	class listen extends KeyAdapter{
		//This method is called whenever I press a key
		public void keyPressed(KeyEvent e){
			System.out.println("KEY IN");
			int input = e.getKeyCode();
			switch(input){
				case KeyEvent.VK_RIGHT:
					ship.moveX(1);
					break;
				case KeyEvent.VK_DOWN:
					ship.moveY(1);
					break;
				case KeyEvent.VK_LEFT:
					ship.moveX(-1);
					break;
				case KeyEvent.VK_UP:
					ship.moveY(-1);
					break;
				case KeyEvent.VK_SPACE:
					fire = true;
					break;
			}
		}
		//The Updater calls this method. The result is a function of what keys were pressed recently
		public void keyReleased(KeyEvent e){
			int input = e.getKeyCode();
			switch(input){
				case KeyEvent.VK_RIGHT:
					ship.moveX(0);
					break;
				case KeyEvent.VK_DOWN:
					ship.moveY(0);
					break;
				case KeyEvent.VK_LEFT:
					ship.moveX(0);
					break;
				case KeyEvent.VK_UP:
					ship.moveY(0);
					break;
				case KeyEvent.VK_SPACE:
					fire = false;
					break;
			}
		}
	}
}
