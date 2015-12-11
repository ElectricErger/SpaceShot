//Controller
package spaceShot;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

import javax.imageio.ImageIO;

import javafx.scene.media.*;

import javax.swing.*;

public class SpaceShot {
	public static void main(String[] args) throws IOException{
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
		//WARNING DOESN'T WORK ON ALL MACHINES
		String songFile = "file://C:/Users/Maximillian/Documents/Programming/SpaceShot/src/spaceShot/SpacePirates.mp3";
		//+System.getProperty("user.dir")+"\\src\\spaceShot\\SpacePirates.mp3";
		System.out.println(songFile);
		Media hit = new Media(songFile);
		MediaPlayer mediaPlayer = new MediaPlayer(hit);
		mediaPlayer.play();
		
		//Blit and blast baby
		window.pack();
		window.setVisible(true);

	}
}

