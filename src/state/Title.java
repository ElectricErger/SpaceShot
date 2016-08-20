package state;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import screenObjects.Sprite;
import spaceShot.Space;

public class Title extends State{
	Sprite logo;

	public Title(){
		logo = new Sprite(0,0);
		logo.setImage("res/logo3.png");
		logo.setX((Space.RIGHT-logo.getImage().getWidth(null))/2);
		logo.setY((Space.BOTTOM-logo.getImage().getHeight(null))/4);
	}
	
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(
				logo.getImage(),
				logo.getX(),
				logo.getY(),
				null);
		//Press enter to start
		g.setFont(new Font("MONOSPACED", Font.PLAIN, 40));
		String s = "Press Enter To Start";
		g.drawString(
				s,
				(Space.RIGHT-g.getFontMetrics().stringWidth(s))/2,
				(Space.BOTTOM)*3/4);
	}


	@Override
	public void tick() {
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		int input = e.getKeyCode();
		switch (input){
		case KeyEvent.VK_ENTER:
			try {
				Space.game.start();
			} catch (IllegalStateChange e1) {
				e1.printStackTrace();
			}
		}
	}


	@Override
	public void keyRelased(KeyEvent e) {
		int input = e.getKeyCode();
	}
	
}
