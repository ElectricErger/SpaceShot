package spaceShot;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class Logo extends JComponent{
	final static int LEFT = 0;
	final static int RIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	final static int TOP = 0;
	final static int BOTTOM = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	Image logoBuff = null;
	Logo() throws IOException{
		//Opening screen
		logoBuff = ImageIO.read(new File("src/spaceShot/logo2.png"));
	}
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(logoBuff, (RIGHT-logoBuff.getWidth(null))/2, (BOTTOM*2/3-logoBuff.getHeight(null))/2, null);
	}
}
