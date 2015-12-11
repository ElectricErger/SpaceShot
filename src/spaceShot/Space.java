//The view displays the state of objects
package spaceShot;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Space extends JPanel{
	final static int RIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	final static int BOTTOM = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	//All the things I could need to blit
	Logo logo;
	SpaceShip ship;
	ArrayList<Bullet> bullets = new ArrayList();
	ArrayList<Star> stars = new ArrayList();
	ArrayList<Rock> rocks = new ArrayList();
	
	GameState game;
	
	Space(){
	}
	
	//PAINTING PROTOCOL
	//Alternatively I could create a series of methods to print things individually.
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		stars(g);
		ship(g);
		rocks(g);
		bullets(g);
		
		String scoreStr = "Score: "+game.getScore();
		g.setFont(new Font("MONOSPACED", Font.PLAIN, 40));
		g.drawString(scoreStr, (RIGHT-g.getFontMetrics().stringWidth(scoreStr)), 40);
		
		if (game.titleScreen()){
			g.drawImage(logo.getImage(), (RIGHT-logo.getImage().getWidth(null))/2, (BOTTOM-logo.getImage().getHeight(null))/4, null);
			//Press enter to start
			g.setFont(new Font("MONOSPACED", Font.PLAIN, 40));
			String s = "Press Enter To Start";
			g.drawString(s, (RIGHT-g.getFontMetrics().stringWidth(s))/2, (BOTTOM)*3/4);
		}
		if(game.isGameOver()){
			String s = "GAME OVER";
			g.setFont(new Font("MONOSPACED", Font.PLAIN, 60));
			g.drawString(s, (RIGHT-g.getFontMetrics().stringWidth(s))/2, BOTTOM/2);
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
	
	
	public void updateBullets(ArrayList<Bullet> bull){
		this.bullets = bull;
	}
	public void updateRocks(ArrayList<Rock> ro){
		this.rocks = ro;
	}
	public void updateShip(SpaceShip s){
		this.ship = s;
	}
	public void updateStars(ArrayList<Star> st){
		this.stars = st;
	}
	public void gameLoader(GameState g){
		this.game = g;
	}
	public void updateLogo(Logo logo) {
		this.logo = logo;
	}
}
