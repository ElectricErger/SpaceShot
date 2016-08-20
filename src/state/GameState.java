package state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.lang.Exception;

import screenObjects.Rock;
import spaceShot.Audio;

public class GameState {
	final static int TITLE = 0;
	final static int INGAME = 1;
	final static int GAMEOVER = 2;
	int state;
	State stateObj;
	int lvl;
	
	public static Audio sound;
	
	public GameState(){
		state = TITLE;
		stateObj = new Title();
		lvl = 0;
		
		sound = new Audio();
	}
	
	//Change state
	public void start() throws IllegalStateChange{
		switch (state){
		case TITLE:
			stateObj = new InGame();
			state = INGAME;
			break;
		case INGAME:
			throw new IllegalStateChange("Cannot start game, while in a game.");
		case GAMEOVER:
			stateObj = new InGame();
			state = INGAME;
			break;
		}
	}	
	public void gameOver() throws IllegalStateChange{
		switch (state){
		case TITLE:
			throw new IllegalStateChange("You can't lose a game you haven't started yet.");
		case INGAME:
			stateObj = new GameOver();
			state = GAMEOVER;
			break;
		case GAMEOVER:
			throw new IllegalStateChange("You have already lost.");
		}
	}	
	public void nextLevel() throws IllegalStateChange{
		switch(state){
		case TITLE:
			throw new IllegalStateChange("You must start the game before moving to level 1");
		case INGAME:
			lvl++;
			break;
		case GAMEOVER:
			throw new IllegalStateChange("You have lost, please play again to move to level 1");
		}
	}
	
	//Getters
	public int getState(){ return state; }
	public State getStateObj(){ return stateObj; }
	public int getLevel(){ return lvl; }

	
	public void paint(Graphics g){
		//Update frame and object states on the clock.
		stateObj.tick();
		stateObj.paint(g);
	}

	public void keyPressed(KeyEvent e) { stateObj.keyPressed(e); }
	public void keyRelased(KeyEvent e) { stateObj.keyRelased(e); }
}

//If you change states we will yell at you.
class IllegalStateChange extends Exception{
	static String err = "Cannot change state";
	
	public IllegalStateChange() {super(err);} //Default
	public IllegalStateChange(String msg) {super(msg);} //Throw the message you have
	public IllegalStateChange(Throwable msg) {super(msg);}
	
	//I would like it to say the name of the states, but that seems like I would want a dictionary
	//An upgrade for a later time
	public IllegalStateChange(int stateA, int stateB) {
		super("Cannot change state from: " + stateA + ", to:" + stateB);
	}
}

abstract class State{
	public abstract void paint(Graphics g);
	public abstract void keyPressed(KeyEvent e);
	public abstract void keyRelased(KeyEvent e);
	public abstract void tick();
}