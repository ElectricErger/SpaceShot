//Controller
package spaceShot;

import java.applet.*;
import java.awt.Color;
import java.awt.event.*;
import java.io.*;
import java.net.URL;

import javafx.scene.media.*;

import javax.sound.sampled.*;
import javax.swing.*;

public class SpaceShot {
	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		//Setup window
		JFrame window = new JFrame("SpaceShot");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.BLACK);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH); //Full dimensions
		window.setUndecorated(true); //True full screen
		
		Space field = new Space();
		Controller controller = new Controller(field);
		window.add(field);
		
		//Bind Keys
		window.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "EXIT");
		window.getRootPane().getActionMap().put("EXIT", new AbstractAction(){ 
			        public void actionPerformed(ActionEvent e){
			            window.dispose();
			            System.exit(0);
			        }
			    }
		);
		
		//Music
		AudioInputStream audioIn = AudioSystem.getAudioInputStream(SpaceShot.class.getResource("SpacePirates.wav"));
		Clip clip = AudioSystem.getClip();
		clip.open(audioIn);
		clip.start();
		
		//Blit and blast baby
		window.pack();
		window.setVisible(true);

	}
}

