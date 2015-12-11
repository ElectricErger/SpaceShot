package spaceShot;

public class GameState {
	boolean title;
	boolean inGame;
	boolean gameOver;
	int level;
	int score;
	
	GameState(){
		title = true;
		inGame = false;
		gameOver = false;
		level = 1;
		score = 0;
	}
	
	//Change state
	public void start(){
		title = false;
		inGame = true;
	}	
	public void gameOver(){
		inGame = false;
		gameOver = true;
	}	
	public void nextLevel(){
		level++;
	}
	public void changeScore(int i){
		score+=i;
	}
	
	//Getters
	public boolean titleScreen(){
		return title;
	}
	public boolean inGame(){
		return inGame;
	}
	public boolean isGameOver(){
		return gameOver;
	}
	public int level(){
		return level;
	}
	public int getScore(){
		return score;
	}
}
