package OLD.STUFF.board.services;

import java.io.File;
import java.util.ArrayList;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class audioPlayer {
	
	public static ArrayList<MediaPlayer> currentMusicList = new ArrayList<MediaPlayer>();
	
	public static void playMainMenu() {
        String bip = getCurrentWorkingDirectory() + "\\src\\OLD.STUFF.board.music\\ratmusic.mp3";
        System.out.println(bip);
        Media hit = new Media(new File(bip).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
        currentMusicList.add(mediaPlayer);
	}
	
	public static void playDeathSound() {
		String bip = getCurrentWorkingDirectory() + "\\src\\OLD.STUFF.board.music\\mech_death.mp3";
        System.out.println(bip);
        Media hit = new Media(new File(bip).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
        currentMusicList.add(mediaPlayer);
	}
	
    private static String getCurrentWorkingDirectory() {
        String userDirectory = System.getProperty("user.dir");
        return userDirectory;
    }
    
    public static void stopAllMusic() {
    	for(MediaPlayer m : currentMusicList) {
    		m.stop();
    	}
    	currentMusicList.clear();
    }

}
