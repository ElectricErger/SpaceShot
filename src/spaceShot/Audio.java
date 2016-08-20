package spaceShot;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {

	AudioInputStream song, laser, explotion;
	
	public Audio(){
		try {
			//Get file from resources folder
			ClassLoader classLoader = getClass().getClassLoader();

			song = AudioSystem.getAudioInputStream(
					new File(classLoader.getResource("SpacePirates.wav").getFile()));
			laser = AudioSystem.getAudioInputStream(
					new File(classLoader.getResource("laser.wav").getFile()));
			explotion = AudioSystem.getAudioInputStream(
					new File(classLoader.getResource("explosion.wav").getFile()));
			
			playSong();
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void playSong(){
		Clip clip;
		try {
			clip = AudioSystem.getClip();
			clip.open(song);
			clip.loop(2);
		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
		
	}
	public void laserSound(){ 
			try {
				Clip clip = AudioSystem.getClip();
				clip.open(laser);
				clip.start();
			} catch (IOException | LineUnavailableException e) {
				e.printStackTrace();
				System.out.println("I fucked up");
			}
	}
	public void explotionSound(){
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(explotion);
			clip.start();
		} catch (IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}
}
