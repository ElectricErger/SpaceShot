package spaceShot;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class SpaceShot {
	//Dimensions of the field
	final static int LEFT = 0;
	final static int RIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	final static int TOP = 0;
	final static int BOTTOM = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	final static int UPPER_RANGE = BOTTOM/3; //The farthest up you can go
	
	static int sensitive = 10;
	
	public static void main(String[] args) throws IOException{
		//Setup window
		JFrame window = new JFrame("SpaceShot");
		window.setLayout(null);
		//window.setLayout(new BorderLayout());
		//window.setLayout(new GridBagLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.BLACK);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH); //Full dimensions
		window.setUndecorated(true); //True full screen

		//Make ship
		SpaceShip ship = new SpaceShip();
		//window.add(ship, BorderLayout.SOUTH);
		window.add(ship);
		ship.setBounds(100, 100, 50, 50);
		
		Rock ast = new Rock();
		ast.setBounds(200, 200, 50, 50);
		window.add(ast);
		
		//Display the logo
		Logo logo = new Logo();
		//window.add(logo, BorderLayout.NORTH);
		//window.add(logo);
		
		
		//Bind Keys
		window.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "EXIT");
		ship.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "Move Up");
		ship.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "Move Down");
		ship.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "Move Left");
		ship.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "Move Right");
		window.getRootPane().getActionMap().put("EXIT", new AbstractAction(){ 
			        public void actionPerformed(ActionEvent e){
			            window.dispose();
			        }
			    }
		);
		ship.getActionMap().put("Move Up", new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				ship.updatePosition(0, -sensitive); ship.repaint();
			}
		}
		);		
		ship.getActionMap().put("Move Down", new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				ship.updatePosition(0, sensitive); ship.repaint();
			}
		}
		);		
		ship.getActionMap().put("Move Left", new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				ship.updatePosition(-sensitive, 0); ship.repaint();
			}
		}
		);		
		ship.getActionMap().put("Move Right", new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				ship.updatePosition(sensitive, 0); ship.repaint();
			}
		}
		);

		
		
		
		//Blit and blast baby
		window.pack();
		window.setVisible(true);
		
		
		
		//Game play
	}
}

