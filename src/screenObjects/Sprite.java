//Abstract model
package screenObjects;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Sprite {
	public int x,y,height, width;
	Image image;
	
	//Note X and Y are the top left coordinates
	
	public Sprite(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	//Either you set an image or a polygon.
	public void setImage(String img){
		ImageIcon temp = new ImageIcon(img);
		image = temp.getImage();
	}
	public void setX( int newX ){ x = newX; }
	public void setY( int newY ){ y = newY; }
	
	public Image getImage(){ return image; }
	public int getX(){ return x; }
	public int getY(){ return y; }
}
