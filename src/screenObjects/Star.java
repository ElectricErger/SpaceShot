package screenObjects;

public class Star {
	int[] details = {0,0,0}; //x,y,radius
	public Star(int x, int y, int r){
		details[0] = x;
		details[1] = y;
		details[2] = r;
	}
	
	public void updatePosition(int deltaX, int deltaY){
		details[0] += deltaX;
		details[1] += deltaY;
	}
	
	public void reset(){
		details[1] = 0;
	}
	
	public int[] getStar(){
		return details;
	}
}
