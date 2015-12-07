//Abstract model
package spaceShot;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Sprite {
	int x,y,height, width;
	Image image;
	int[] poly;
	
	Sprite(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	//Either you set an image or a polygon. If you have both...we have weirdness. We'll deal with other issues later.
	public void setImage(String img){
		ImageIcon temp = new ImageIcon(img);
		image = temp.getImage();
	}
	public void setPoly(int[][] points){
		System.arraycopy(points, 0, poly, 0, points.length);
	}
	public void setX(){
		
	}
	public void setY(){
		
	}
	
	public Image getImage(){
		return image;
	}
	public int[] getPoly(){
		return poly;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
}
