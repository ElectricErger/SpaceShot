//Controller. Creates and interacts with objects
package spaceShot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.Timer;


public class Controller implements ActionListener {
	final static int RIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	final static int BOTTOM = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	int shipSpeed = 30; //Make an option for this later
	int rockSpeed = 10;
	int bulletSpeed = 20;
	
	Space field;
	SpaceShip ship;
	Logo logo;
	GameState currentGame;
	
	Random ran = new Random();
	ArrayList<Bullet> bullets = new ArrayList();
	ArrayList<Star> stars = new ArrayList();
	ArrayList<Rock> rocks = new ArrayList();
	Timer time = new Timer(50, this); //Requires implementing actionListener ...not sure why. Read up on it
	boolean fire = false;
	Audio sound;
	
	
	Controller(Space field){
		this.field = field;
		field.setFocusable(true);
		sound = new Audio(); //Music and sound effects
		
		currentGame = new GameState();
		field.gameLoader(currentGame);
		protocol();
	}

	private void protocol(){ //The flow of the game is controlled here
		initiateSetup();
		
		time.start(); //Update rocks, stars, ship and detect collisions
		(new astr()).start(); //If rocks leave the field delete them
	}
	
	private void initiateSetup(){
		ship = new SpaceShip(RIGHT/2, BOTTOM-50, Color.CYAN);
		logo = new Logo(100, 100);
		field.updateShip(ship);
		field.updateRocks(rocks);
		field.updateLogo(logo);
		field.updateBullets(bullets);
		
		field.setBackground(Color.BLACK);
		generateStars();
		field.updateStars(stars);
		
		field.addKeyListener(new Listen());
	}
	
	private void generateStars(){
		for(int i = 0; i< RIGHT; i=i+100){
			for(int j = 0; j< BOTTOM; j=j+100){
				stars.add(new Star(i-50+ran.nextInt(100), j-50+ran.nextInt(100), ran.nextInt(3)+1));
			}
		}
	}
	

	//NEXT MOVES:
	//Explotion.gif=
	
	
	//Updater
	public void actionPerformed(ActionEvent e){
		shipUpdater();
		bulletUpdater();
		asteroidUpdater();
		starUpdater();
		collisions();
		field.repaint();
	}
	
	private void shipUpdater(){
		ship.updatePosition();
	}
	private void bulletUpdater(){
		if(fire){
			bullets.add(new Bullet(ship.getX(), ship.getY()));
			sound.laserSound();
		}
		for(int i = 0; i<bullets.size(); i++){
			Bullet bullet = (Bullet) bullets.get(i);
			bullet.updatePosition(0, -bulletSpeed);
			
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
	private synchronized void asteroidUpdater(){
		for(int i = 0; i<rocks.size(); i++){
			Rock rock = rocks.get(i);
			rock.updatePosition();
			//rock.updatePosition(0, rockSpeed);
			
			if(rock.getRock()[1]>BOTTOM){
				rocks.remove(i);
			}
		}
	}
	private synchronized void collisions(){
		//Collisions between asteroids and bullets
		for(int r = 0; r<rocks.size(); r++){
			Rock rockCol = rocks.get(r);
			for(int b = 0; b<bullets.size(); b++){ //We did it
				if(rockCol.getBox().intersects(bullets.get(b).getBox())){
					sound.explotionSound();
					rocks.remove(r);
					bullets.remove(b);
					currentGame.changeScore(1);
					break; //If I don't break then we could get a rock number that is out of bounds of the array list
				}
			}
			if(rockCol.getBox().intersects(ship.getBox())){ //You lose
				currentGame.gameOver();
				bullets = new ArrayList();
				ship = new SpaceShip(RIGHT/2, BOTTOM-50, Color.CYAN);
				rocks = new ArrayList();
			}
		}
	}

	
	//New thread for making astroids
	class astr extends Thread{
		public synchronized void run(){
			while (true){
				if(rocks.size()<10 && currentGame.inGame()){
					rocks.add(new Rock(ran.nextInt(RIGHT), -20));
				}
				try {Thread.sleep(ran.nextInt(10)*100);}
				catch (InterruptedException e) {}
			}
		}
	}
	//A class needed to call keys
	private class Listen extends KeyAdapter{
		//This method is called whenever I press a key
		@Override
		public void keyPressed(KeyEvent e){
			//System.out.println("Key down");
			int xVel = ship.getXVelocity();
			int yVel = ship.getYVelocity();
			int input = e.getKeyCode();
			switch(input){
				case KeyEvent.VK_RIGHT:
					if(xVel<=0){
						ship.moveX(shipSpeed);
					}
					break;
				case KeyEvent.VK_DOWN:
					if(yVel<=0){
						ship.moveY(shipSpeed);
					}
					break;
				case KeyEvent.VK_LEFT:
					if(xVel>=0){
						ship.moveX(-shipSpeed);
					}
					break;
				case KeyEvent.VK_UP:
					if(yVel>=0){
						ship.moveY(-shipSpeed);
					}
					break;
				case KeyEvent.VK_SPACE:
					fire = true;
					break;
				case KeyEvent.VK_ENTER:
					if(!currentGame.inGame){
						currentGame.start();
					}
					break;
			}
		}
		//The Updater calls this method. The result is a function of what keys were pressed recently
		@Override
		public void keyReleased(KeyEvent e){
			int input = e.getKeyCode();
			switch(input){
				case KeyEvent.VK_RIGHT:
					ship.moveX(-shipSpeed);
					break;
				case KeyEvent.VK_DOWN:
					ship.moveY(-shipSpeed);
					break;
				case KeyEvent.VK_LEFT:
					ship.moveX(shipSpeed);
					break;
				case KeyEvent.VK_UP:
					ship.moveY(shipSpeed);
					break;
				case KeyEvent.VK_SPACE:
					fire = false;
					break;
			}
		}
	}
}
