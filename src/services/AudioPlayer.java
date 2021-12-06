package services;

import java.io.File;

import java.util.ArrayList;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * AudioPlayer.java
 * @author Sam B
 * Last Mod Date: 27/11/2021
 */
public class AudioPlayer {

    /**
     * creates an array list for current music
     */
    public static ArrayList<MediaPlayer> currentMusicList = new ArrayList<MediaPlayer>();

    /**
     * instantiates the breed sound
     */
    public static void playBreedSound() {
	String bip = getCurrentWorkingDirectory() + "\\src\\music\\breed.mp3";
	System.out.println(bip);
	Media hit = new Media(new File(bip).toURI().toString());
	MediaPlayer mediaPlayer = new MediaPlayer(hit);
	mediaPlayer.play();
	currentMusicList.add(mediaPlayer);
    }

    /**
     * instantiates the ingame music
     */
    public static void playInGameMusic() {
	String bip = getCurrentWorkingDirectory() + "\\src\\music\\ingamemusic.mp3";
	System.out.println(bip);
	Media hit = new Media(new File(bip).toURI().toString());
	MediaPlayer mediaPlayer = new MediaPlayer(hit);
	mediaPlayer.play();
	currentMusicList.add(mediaPlayer);
    }

    /**
     * Play main menu sound.
     */
    public static void playMainMenu() {
	String bip = getCurrentWorkingDirectory() + "\\src\\music\\ratmusic.mp3";
	System.out.println(bip);
	Media hit = new Media(new File(bip).toURI().toString());
	MediaPlayer mediaPlayer = new MediaPlayer(hit);
	mediaPlayer.play();
	currentMusicList.add(mediaPlayer);
    }

    /**
     * Play death sound of a mech.
     */
    public static void playDeathSound() {
	String bip = getCurrentWorkingDirectory() + "\\src\\music\\mech_death.mp3";
	System.out.println(bip);
	Media hit = new Media(new File(bip).toURI().toString());
	MediaPlayer mediaPlayer = new MediaPlayer(hit);
	mediaPlayer.play();
	currentMusicList.add(mediaPlayer);
    }

    /**
     * Gets the current working directory.
     *
     * @return the current working directory
     */
    private static String getCurrentWorkingDirectory() {
	String userDirectory = System.getProperty("user.dir");
	return userDirectory;
    }

    /**
     * Stop all currently playing music.
     */
    public static void stopAllMusic() {
	for (MediaPlayer m : currentMusicList) {
	    m.stop();
	}
	currentMusicList.clear();
    }
}
