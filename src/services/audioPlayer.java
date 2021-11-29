package services;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class audioPlayer {
	
	public static void playMainMenu() {
        String bip = getCurrentWorkingDirectory() + "\\src\\music\\ratmusic.mp3";
        System.out.println(bip);
        Media hit = new Media(new File(bip).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
	}
	
	public static void playDeathSound() {
		String bip = getCurrentWorkingDirectory() + "\\src\\music\\mech_death.mp3";
        System.out.println(bip);
        Media hit = new Media(new File(bip).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
	}
	
    private static String getCurrentWorkingDirectory() {
        String userDirectory = System.getProperty("user.dir");
        return userDirectory;
    }

}
