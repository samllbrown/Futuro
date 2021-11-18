package board;

import java.util.ArrayList;

import board.Grid;
import gameObject.Mech;
import inventory.Inventory;

public class Level {
	
	private final char[] mechTypes = ['', '', '']
	
	private final int MAX_ITEM_USES = 4;
	private final int WINNING_NUMBER_OF_MECHS = 0;
	private final int MECH_KILL_SCORE = 5;
	private final int BABY_MECHS_FROM_MOTHER = 5;
	
	private Grid grid;
	private Inventory inventory;
	
	private int width;
	private int height;
	private int losingNumberOfMechs; 
	private int currentScore;
	private int expectedSecondsToComplete;
	private int timeElapsed; // This might need to be in the game class, same with won/lost
	
	private ArrayList<Mech> currentMechs;
	
	public Level(int height, int width, Inventory inventory, int loosingNumberOfMechs, int currentScore, int expectedSecondsToComplete, int timeElapsed,
			ArrayList<Mech> currentMechs) {
		this.height = height;
		this.width = width;
		this.inventory = inventory;
		this.losingNumberOfMechs = loosingNumberOfMechs;
		this.currentScore = currentScore;
		this.timeElapsed = timeElapsed;
		this.currentMechs = currentMechs;
	}
	
	private void birthMechs(Mech fromMech) {
		for(int i = 0; i < 5; i++) {
			
		}
	}

	
	public boolean isCompleted() {
		return (this.currentMechs.size() == this.losingNumberOfMechs || this.currentMechs.size() == this.WINNING_NUMBER_OF_MECHS);
	}
	
	private void scoreKill(Mech mech) {
		this.currentScore += (mech.isPregnant() ? (MECH_KILL_SCORE * BABY_MECHS_FROM_MOTHER) : MECH_KILL_SCORE);
	}
	
	private void processDeadMech(Mech deadMech) {
		this.scoreKill(deadMech);
		this.currentMechs.remove(deadMech);
	}
	
	
	private void updateMech(Mech mech) {
		if(mech.getHealth() < 0) {
			this.processDeadMech(mech);
		} else if (mech.isPregnant() && (mech.getBirthTimer() == 0)) {
			
		}
	}
	
	private void updateMechs() {
		for(Mech mech : this.currentMechs) {
			if(mech.getHealth() < 0) {
				this.processDeadMech(mech);
			} else if (mech.isPregnant() && ) {
				
			}
		}

	}
	
	public void update() {
		// performs a tick
		// every item should do "its thing" to whatever is on its tile
		// every mech should move to a new tile after items have done "their thing"
		// if a mech is ready to give birth, we should add new mechs accordingly and
		// set the pregnant mech's isPregnant var to false.
		// more is done
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getLosingNumberOfMechs() {
		return losingNumberOfMechs;
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
}
