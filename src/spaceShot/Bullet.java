package spaceShot;

public class Bullet {
	int[] position = {0,0};
	Bullet(int x, int y){
		position[0] = x;
		position[1] = y;
	}
	
	public void updatePosition(int dx, int dy){
		position[0] += dx;
		position[1] += dy;
	}
	
	public int[] getBullet(){
		return position;
	}
}
