package state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import screenObjects.Bullet;
import screenObjects.Rock;
import screenObjects.SpaceShip;
import screenObjects.Star;
import spaceShot.Space;

public class InGame extends State {
//	g.setColor(Color.GRAY);
	final int RIGHT = Space.RIGHT;
	final int BOTTOM = Space.BOTTOM;
	
	Random ran;
	SpaceShip ship;
	ArrayList<Star> stars;
	public static ArrayList<Rock> rocks;
	int score;
	boolean fire;
	
	int shipSpeed = 30; //Make an option for this later
	int rockSpeed = 5;
	
	//Astroid thread
	Thread astr = new Thread(){
		public synchronized void run(){
			int numRocks = 20;
			Random makeRock = new Random();
			while (true){
				if(rocks.size() < numRocks && makeRock.nextBoolean()){ rocks.add(new Rock()); }
				try {Thread.sleep(100);} 
				catch (InterruptedException e) {}
			}
		}
	};
	
	public InGame(){
		//Initialize all objects
		ship = new SpaceShip(RIGHT/2, BOTTOM-50, Color.CYAN);
		stars = new ArrayList<Star>();
		rocks = new ArrayList<Rock>();
		ran = new Random();
		
		score = 0;
		fire = false;
		
		generateStars();
		astr.start(); //If rocks leave the field delete them

	}
	
	private void generateStars(){
		for(int i = 0; i< RIGHT; i=i+100){
			for(int j = 0; j< BOTTOM; j=j+100){
				stars.add(new Star(i-50+ran.nextInt(100), j-50+ran.nextInt(100), ran.nextInt(3)+1));
			}
		}
	}
	
	
	@Override
	public void paint(Graphics g) {
		stars(g);
		ship(g);
		rocks(g);
		bullets(g);
		score(g);
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
		ArrayList<Bullet> bullets = ship.getBullets();
		for(Bullet b : bullets){ b.paint(g); }
	}
	private void rocks(Graphics g){
		g.setColor(Color.GRAY);
		for(Rock r : rocks){
			r.printRock(g);
		}
	}
	private void score(Graphics g){ 
		String scoreStr = "Score: "+ score;
		g.setFont(new Font("MONOSPACED", Font.PLAIN, 40));
		g.drawString(scoreStr, (Space.RIGHT-g.getFontMetrics().stringWidth(scoreStr)), 40);
	}


	public void tick() {
		shipUpdater();
		bulletUpdater();
		asteroidUpdater();
		starUpdater();
		collisions();
	}
	
	//Update functions on clock tick
	private void shipUpdater(){ ship.updatePosition(); }
	private void bulletUpdater(){ ship.shoot(fire); }
	private void starUpdater(){
		for(int i = 0; i<stars.size(); i++){
			Star star = (Star) stars.get(i);
			star.updatePosition(0, 5);
			
			if(star.getStar()[1]>BOTTOM){
				star.reset();
			}
		}
	}
	private synchronized void asteroidUpdater(){
		for(int i = 0; i<rocks.size(); i++){
			Rock rock = rocks.get(i);
			rock.updatePosition();
			if(rock.getY()>BOTTOM){ //Need new way of getting Rock Y position
				rocks.remove(i);
				rock.setDestroyed();
			}
		}
	}
	private synchronized void collisions(){ //Consider moving this over to ship for multiplayer support
		//Collisions between asteroids and bullets
		for(Rock r: rocks){
			ArrayList<Bullet> bullets = ship.getBullets();
			for(Bullet b: bullets){ //We did it
				if(r.getBox().intersects(b.getBox())){
					GameState.sound.explotionSound();
					r.setDestroyed();
					rocks.remove(r);
					bullets.remove(b);
					score++;
					break;
				}
			}
			
			//Lose condition
			if(r.getBox().intersects(ship.getBox())){
				try { Space.game.gameOver(); }
				catch (IllegalStateChange e) {
					e.printStackTrace();
					System.exit(1);
				}
				
				//Reset the variables? Or make null....
				bullets = new ArrayList<Bullet>();
				ship = new SpaceShip(RIGHT/2, BOTTOM-50, Color.CYAN);
				rocks = new ArrayList<Rock>();
			}
		}
	}


	public void keyPressed(KeyEvent e) {
		int input = e.getKeyCode();
		switch(input){
		case KeyEvent.VK_LEFT:
			ship.moveLeft();
			break;
		case KeyEvent.VK_UP:
			ship.moveUp();
			break;
		case KeyEvent.VK_RIGHT:
			ship.moveRight();
			break;
		case KeyEvent.VK_DOWN:
			ship.moveDown();
			break;
		case KeyEvent.VK_SPACE:
			fire = true;
			break;
			
		}
	}

	public void keyRelased(KeyEvent e) {
		int input = e.getKeyCode();
		switch(input){
		case KeyEvent.VK_LEFT:
			ship.stopLeft();
			break;
		case KeyEvent.VK_UP:
			ship.stopUp();
			break;
		case KeyEvent.VK_RIGHT:
			ship.stopRight();
			break;
		case KeyEvent.VK_DOWN:
			ship.stopDown();
			break;
		case KeyEvent.VK_SPACE:
			fire = false;
			break;
		}
	}
 
}
