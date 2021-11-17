package board;

import managers.Inventory;
import board.Grid;

public class Level {
	
	private final int MAX_ITEM_USES = 4;
	private final int WINNING_NUMBER_OF_MECHS = 0;
	private final int MECH_KILL_SCORE = 5;
	private final int BABY_MECH_FROM_MOTHER = 5;
	
	private Grid grid;
	private Inventory inventory;
	
	private int width;
	private int height;
	private int loosingNumberOfMechs; 
	private int currentScore;
	private int expectedSecondsToComplete;
	private int timeElapsed; // This might need to be in the game class, same with won/lost
	
	
	public Level(int height, int width, Inventory inventory, int loosingNumberOfMechs, int currentScore, int expectedSecondsToComplete, int timeElapsed, Grid grid) {
		this.height = height;
		this.width = width;
		this.inventory = inventory;
		this.loosingNumberOfMechs = loosingNumberOfMechs;
		this.currentScore = currentScore;
		this.timeElapsed = timeElapsed;
		this.setGrid(grid);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getLoosingNumberOfMechs() {
		return loosingNumberOfMechs;
	}

	public int getCurrentScore() {
		return currentScore;
	}

	public void setCurrentScore(int currentScore) {
		this.currentScore = currentScore;
	}

	public int getExpectedSecondsToComplete() {
		return expectedSecondsToComplete;
	}

	public int getTimeElapsed() {
		return timeElapsed;
	}

	public void setTimeElapsed(int timeElapsed) {
		this.timeElapsed = timeElapsed;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}
}
