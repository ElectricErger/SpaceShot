package spaceShot;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {

	Audio(){
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(this.getClass().getResource("SpacePirates.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.loop(2);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public void laserSound(){ 
			try {
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(this.getClass().getResource("laser.wav"));
				Clip clip = AudioSystem.getClip();
				clip.open(audioIn);
				clip.start();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				e.printStackTrace();
				System.out.println("I fucked up");
			}
	}
	public void explotionSound(){
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(this.getClass().getResource("explosion.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}
}
