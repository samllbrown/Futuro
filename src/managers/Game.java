package managers;

import java.io.IOException;

import board.Level;
import services.MessageOfTheDay;

public class Game {
	
	private Level level;
	private Player currentPlayer;
	private String messageOfTheDay;
	private Boolean isPaused;
	
	
	public Game(Level level) {	
		setLevel(level);
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	
	//BAD IMPLEMENTATION
	public String getMessageOfTheDay() {
		String message;
		try {
			message = MessageOfTheDay.getMessageOfTheDay();
		} catch (IOException e) {
			message = "Error" + e;
		}
		setMessageOfTheDay(message);
		return messageOfTheDay;
	}

	public void setMessageOfTheDay(String messageOfTheDay) {
		this.messageOfTheDay = messageOfTheDay;
	}
}
