package managers;

import board.Level;

public class Game {
	
	private Level level;
	
	
	public Game(Level level) {	
		setLevel(level);
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
}
