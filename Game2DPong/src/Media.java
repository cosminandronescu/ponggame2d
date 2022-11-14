import java.io.*;

import javax.sound.sampled.*;


public class Media {

	
	//sound effect for the interactions
	public void Audio(String path) {
		try {
			String dir = System.getProperty("user.dir");
			path = dir + path;
			File borderAudio = new File(path);
			if (borderAudio.exists()) {
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(borderAudio);
			Clip clip = AudioSystem.getClip();
			clip.open(audioInput);
			clip.start();
			}
			else System.out.println("Couldn't get Audio file!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//background music
	public void Music(String path) {
		try {
			String dir = System.getProperty("user.dir");
			path = dir + path;
			File music = new File(path);
			if (music.exists()) {
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(music);
			Clip clip = AudioSystem.getClip();
			clip.open(audioInput);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
			else System.out.println("Couldn't get Music Audio file!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

