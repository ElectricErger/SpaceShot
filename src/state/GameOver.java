package state;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import spaceShot.Space;

public class GameOver extends State {
	final static int RIGHT = Space.RIGHT;
	final static int BOTTOM = Space.BOTTOM;
	
	@Override
	public void paint(Graphics g) {
		String s = "GAME OVER";
		g.setFont(new Font("MONOSPACED", Font.PLAIN, 60));
		g.drawString(s, (RIGHT-g.getFontMetrics().stringWidth(s))/2, BOTTOM/2);
	}


	@Override
	public void tick() {}

	@Override
	public void keyPressed(KeyEvent e) {
		int input = e.getKeyCode();
		switch(input){
		case KeyEvent.VK_ENTER:
			try { Space.game.start(); }
			catch (IllegalStateChange e1) {
				e1.printStackTrace();
			}
			break;
		}
	}

	@Override
	public void keyRelased(KeyEvent e) {
		int input = e.getKeyCode();
	}

}
