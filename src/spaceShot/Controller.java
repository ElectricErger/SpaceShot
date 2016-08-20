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

import screenObjects.SpaceShip;


public class Controller implements ActionListener {
	int RIGHT = Space.RIGHT;
	int BOTTOM = Space.BOTTOM;
	
	final double FPS = 60;
	
	Space field;

	Random ran = new Random();
	Timer time = new Timer((int) (1000/FPS), this); //Requires implementing actionListener ...not sure why. Read up on it
	
	Controller(Space field){ //
		this.field = field;
		field.setFocusable(true);

		field.addKeyListener(new Listen());

		time.start(); //Listener clock
	}
	
	//Frame updater
	public void actionPerformed(ActionEvent e){
		field.repaint();
	}

	//Key listener
	private class Listen extends KeyAdapter{
		//This method is called whenever I press a key
		@Override
		public void keyPressed(KeyEvent e){ field.keyPressed(e); }
		//The Updater calls this method. The result is a function of what keys were pressed recently
		@Override
		public void keyReleased(KeyEvent e){ field.keyReleased(e); }
	}
}
