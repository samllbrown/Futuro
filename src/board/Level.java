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
import gameObject.MechType;
import inventory.Inventory;

public class Level {
	private static final int WINNING_NUMBER_OF_MECHS = 0;
	private static final int SCORE_PER_KILL = 10;

	private int levelID;
	private int numOfMechsToLose;
	private int currentScore;

	private int expectedSecs;
	private int elapsedTime;

	private Inventory inventory;
	
	private ArrayList<Item> items;
	private ArrayList<Mech> mechs;

	private Grid grid;

	//private HashMap<Pair, Item> coordsToItems;
	// probably can't have a hashmap of coordstomechs because collisions are possible :/
	//private HashMap<Pair, Mech> ccordsToMechs;
	//private final int MECH_KILL_SCORE = 5;
	//private final int BABY_MECHS_FROM_MOTHER = 5;

	public Level(int levelID, Inventory inventory, int numberOfMechsToLose, int currentScore, int expectedSeconds,
				 int elapsedTime, ArrayList<Mech> mechs, Grid grid) {
		this.levelID = levelID;
		this.inventory = inventory;
		this.numOfMechsToLose = numberOfMechsToLose;
		this.currentScore = currentScore;
		this.expectedSecs = expectedSeconds;
		this.elapsedTime = elapsedTime;
		this.mechs = mechs;
		this.grid = grid;
	}

	public void addItem(Item i) {
		this.items.add(i);
	}

	private int getPointsForKill(Mech m) {
		int points = m.isPregnant() ? (SCORE_PER_KILL * (Mech.NUM_OF_BABIES_IF_BIRTHING + 1)) : SCORE_PER_KILL;
		return points;
	}

	private void killMech(Mech m) {
		this.currentScore += getPointsForKill(m);
		this.mechs.remove(m);
	}

	private void updateMechs() throws Exception {
		ArrayList<Mech> currentMechsCopy = new ArrayList<>(this.mechs);
		for(Mech m : currentMechsCopy) {
			Tile currentMechTile = this.getGrid().getTileAt(m.getGridX(), m.getGridY());
			Item currentItemOnTile = currentMechTile.getCurrentItem();

			if(currentItemOnTile != null) {
				System.out.println("Item is acting on mech");
				currentItemOnTile.act(m);
				System.out.println("Mech's health is now = " + m.getHealth());
			}

			if(m.getHealth() <= 0) {
				this.killMech(m);
				System.err.println("A mech has died");
			} else {
				for(Mech om : this.getGrid().getTileAt(m.getGridX(), m.getGridY()).getMechs()) {
					System.out.println("This mech is on another tile with a mech");
					if(m.canBreedWith(om)) {
						System.err.println("BREEDING BREEDING");
						// then they will start breeding
						// the breeding occurs for 5 seconds
						// whilst breeding, they do not move
						// after breeding they go their own way
						// how can we do this without making the entire thread wait :/
						// we need to break
					} else {
						System.err.println("eee");
					}
				}
			}
			m.move(this.getGrid());
		}
	}

	public void updateItems() throws Exception {
		ArrayList<Item> currentItemCopy = new ArrayList<>(this.items);
		for(Item i : currentItemCopy) {
			this.getGrid().getTileAt(i.getGridX(), i.getGridY()).setCurrentItem(i);
			if(i.isReadyForDestroy) {
				removeItem(i);
			}
		}
	}
	
	private void removeItem(Item i) {
		this.items.remove(i);
	}

	public void update() throws Exception {
		// this for loop should probs just go into an init method
		this.updateItems();
		this.updateMechs();
	}



	// need some validation
	public void removeMech(Mech m) {
		this.mechs.remove(m);
	}

	public void addMech(Mech m) {
		this.mechs.add(m);
	}

	public Grid getGrid() {
		return this.grid;
	}

	public ArrayList<Mech> getMechs() {
		return this.mechs;
	}

//	private void initItemsInPlay() {
//		Tile[][] gridTiles = grid.getGrid();
//		this.currentItemsInPlay.forEach(i -> gridTiles[i.getxPos()][i.getyPos()].addItemToTile(i));
//		this.currentMechs.forEach(m -> gridTiles[m.getxPos()][m.getyPos()].addItemToTile(m));
//	}
//
//	private void placeFromInventory(String name, int atX, int atY) {
//		try {
//			this.inventory.useItem(name, atX, atY);
//		} catch(Exception e) {
//			// somehow mention the fact that they're trying to use an item that they can't use
//			System.err.println("Cannot use item. Used maximum amount of times already");
//		}
//	}
//
//	private void birthMechs(Mech fromMech) {
//		for(int i = 0; i < 5; i++) {
//			// need to update mech class to make sure that we can add baby mechs, etc
//			// this code, obviously, is incomplete and serves only to show how the functionality
//			// of the actual game *might* work.
//			Random rand = new Random();
//			// X DIR AND Y DIR NEEDS TO BE CHANGED;
//			Mech babyMech = GameObjectFactory.makeMech(mechTypes[rand.nextInt(2)], fromMech.getCurrentXPos(), fromMech.getCurrentYPos(), 0, 1);
//			this.addMech(babyMech);
//		}
//		// allow for a new pregnancy timer?
//		fromMech.setPregnant(false);
//	}
//
//	private void addItem(Item item) {
//		Tile[][] gridTiles = grid.getGrid();
//		gridTiles[item.getxPos()][item.getxPos()].addItemToTile(item);
//	}
//
//	private void addMech(Mech mech) {
//		this.currentMechs.add(mech);
//	}
//
//	public boolean isCompleted() {
//		return (this.currentMechs.size() == this.numOfMechsToLose || this.currentMechs.size() == this.WINNING_NUMBER_OF_MECHS);
//	}
//
//	private void scoreKill(Mech mech) {
//		this.currentScore += (mech.isPregnant() ? (MECH_KILL_SCORE * BABY_MECHS_FROM_MOTHER) : MECH_KILL_SCORE);
//	}
//
//	private void processDeadMech(Mech deadMech) {
//		this.scoreKill(deadMech);
//		this.currentMechs.remove(deadMech);
//	}
//
//	private void updateMech(Mech mech) {
//		if(mech.getHealth() < 0) {
//			this.processDeadMech(mech);
//		} else if (mech.isPregnant() && mech.readyToBirth()) {
//			this.birthMechs(mech);
//		} else if(this.grid.getTileAt(mech.getCurrentXPos(), mech.getCurrentYPos()).hasMech()){
//			// not efficient will be dealt with later cba
//			ArrayList<Mech> occupyingMechs = this.grid.getTileAt(mech.getCurrentXPos(), mech.getCurrentYPos()).getCurrentMechs();
//			for(Mech m : occupyingMechs) {
//				mech.mate(m);
//			}
//		}
//		mech.move(this.grid);
//	}
//
//	private void updateItem(Item item) {
//		// won't work for puddles so ye
//		if(this.grid.getTileAt(item.getxPos(), item.getyPos()).hasMech()) {
//			ArrayList<Mech> mechs = this.grid.getTileAt(item.getxPos(), item.getyPos()).getCurrentMechs();
//			for(Mech m : mechs) {
//				item.act(m);
//			}
//		}
//		// if item.timer() == 0 then act?
//		// if item.getClass == puddle and puddle.health == 0 then die?
//		// if item is spreadable, spread?
//		// if item is on the same tile as a mech, actOn the mech?
//	}
//
//	private void updateMechs() {
//		this.currentMechs.forEach(mech -> updateMech(mech));
//	}
//
//	private void updateItems() {
//		this.currentItemsInPlay.forEach(item -> updateItem(item));
//	}
//
//	private void updateScene() {
//		// ???
//	}
//
//	public void update() {
//		if(this.isCompleted()) {
//			// end the level
//		} else {
//			updateItems();
//			updateMechs();
//			updateScene();
//		}
//		// performs a tick
//		// every item should do "its thing" to whatever is on its tile
//		// every mech should move to a new tile after items have done "their thing"
//		// if a mech is ready to give birth, we should add new mechs accordingly and
//		// set the pregnant mech's isPregnant var to false.
//		// more is done
//	}

	public int getLosingNumberOfMechs() {
		return numOfMechsToLose;
	}

	public int getCurrentScore() {
		return currentScore;
	}

	public void setCurrentScore(int currentScore) {
		this.currentScore = currentScore;
	}

	public int getExpectedSecondsToComplete() {
		return expectedSecs;
	}

	public int getTimeElapsed() {
		return elapsedTime;
	}

	public void setTimeElapsed(int timeElapsed) {
		this.elapsedTime = timeElapsed;
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

	public ArrayList<Item> getItems() {
		return items;
	}
	
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
}
