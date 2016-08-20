//The window
package spaceShot;

import java.awt.*;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import state.GameState;

public class Space extends JPanel{
	public final static int RIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public final static int BOTTOM = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	public static GameState game;
	
	//The GSM will maintain the state and pass on the painting responsibility to the live state
	Space(){
		this.setBackground(Color.BLACK);
		game = new GameState();	
	}
	
	//PAINTING PROTOCOL - On the clock tick
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		game.paint(g);	
	}

	public void keyPressed(KeyEvent e) {
		game.keyPressed(e);
		
	}
	public void keyReleased(KeyEvent e) {
		game.keyRelased(e);
		
	}
}
