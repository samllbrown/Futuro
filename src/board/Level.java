package board;

import managers.Inventory;

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
	
	
	public Level() {
	}
}
