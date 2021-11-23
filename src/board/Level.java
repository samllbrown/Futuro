package board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import board.Path;
import board.Tile;
import board.Tunnel;
import board.Wall;

import board.Grid;
import gameObject.GameObjectFactory;
import gameObject.Item;
import gameObject.Mech;
import inventory.Inventory;

public class Level {

	private final char[] mechTypes = {'R', 'P'};
	
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
	private int levelID;
	// this shouldn't be static because it could be a different level (i think)
	// basically, if you load up a level which is level, say, 3 then you're saying that the next level
	// id is 2
	private int nextLevelID;
	
	private ArrayList<Mech> currentMechs;
	private ArrayList<Item> currentItemsInPlay;
	
	// maybe it's a good idea to let every mech access the grid?
	// it sounds a bit off tbh but idk
	// if every mech has access to the grid, then they have access to all the tiles
	// if every item has access to the grid, then they have access to all the tiles
	// every tile should hold the thing on it???
	
	public Level(int levelId, int height, int width, Inventory inventory, int loosingNumberOfMechs, int currentScore, int expectedSecondsToComplete, int timeElapsed,
			ArrayList<Mech> currentMechs, Grid grid) {
		this.height = height;
		this.width = width;
		this.inventory = inventory;
		this.losingNumberOfMechs = loosingNumberOfMechs;
		this.currentScore = currentScore;
		this.timeElapsed = timeElapsed;
		this.currentMechs = currentMechs;
		this.levelID = levelId;
		this.nextLevelID = this.levelID+1;
		this.grid = grid;
		//this.initMechs();
		//this.initItems();
	}

	private void initItems() {
		Tile[][] gridTiles = grid.getGrid();
		for(Item item : currentItemsInPlay) {
			gridTiles[item.getxPos()][item.getyPos()].getItemsOnTile().add(item);
		}
	}

	// ideally, this could be done in the initItems() method, so check this later on;
	private void initMechs() {
		// initialise mechs and their positions, etc
		Tile[][] gridTiles = grid.getGrid();
		for(Mech mech : currentMechs) {
			gridTiles[mech.getCurrentXPos()][mech.getCurrentYPos()].addItemToTile(mech);
		}
	}
	
	private void birthMechs(Mech fromMech) {
		for(int i = 0; i < 5; i++) {
			// need to update mech class to make sure that we can add baby mechs, etc
			// this code, obviously, is incomplete and serves only to show how the functionality
			// of the actual game *might* work.
			Random rand = new Random();
			// X DIR AND Y DIR NEEDS TO BE CHANGED;
			Mech babyMech = GameObjectFactory.makeMech(mechTypes[rand.nextInt(2)], fromMech.getCurrentXPos(), fromMech.getCurrentYPos(), 0, 1);
			this.addMech(babyMech);
		}
		// allow for a new pregnancy timer?
		fromMech.setPregnant(false);
	}
	
	private void addMech(Mech mech) {
		this.currentMechs.add(mech);
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
		} else if (mech.isPregnant() && mech.readyToBirth()) {
			this.birthMechs(mech);
		}

		mech.move(this.grid);
		
		// TO KEEP THINGS LINEAR, WE SHOULD UPDATE THE POSITION OF TH MECH RELATIVE TO THE GRID OR SOMETHING HERE
		// THE MOVE METHOD WILL UPDATE THE MECH'S CURRENT X AND Y SO WE DON'T NEED TO WORRY ABOUT THAT (FOR THE MECH)
		// WE JUST NEED TO FIGURE OUT THE BEST WAY TO UPDATE POSITIONS OF THE MECHS RELATIVE TO THE BOARD
		// THERE ARE A FEW WAYS OF DOING IT
	}
	
	private void updateItem(Item item) {
		// if item.timer() == 0 then act?
		// if item.getClass == puddle and puddle.health == 0 then die?
		// if item is spreadable, spread?
		// if item is on the same tile as a mech, actOn the mech?
	}
	
	private void updateMechs() {
		this.currentMechs.forEach(mech -> updateMech(mech));
	}
	
	private void updateItems() {
		this.currentItemsInPlay.forEach(item -> updateItem(item));
	}
	
	private void updateScene() {
		// ?
	}
	
	public void update() {
		if(this.isCompleted()) {
			// end the level
		} else {
			updateItems();
			updateMechs();
			updateScene();
		}
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

	public int getLevelID() {
		return levelID;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
}
