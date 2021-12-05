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
import managers.Breeder;
import managers.MechManager;
import services.audioPlayer;

/**
 * Level.java
 * @author
 * @version
 * Last Mod Date:
 */
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

	private Breeder breeder;

	private Grid grid;

	//private HashMap<Pair, Item> coordsToItems;
	// probably can't have a hashmap of coordstomechs because collisions are possible :/
	//private HashMap<Pair, Mech> ccordsToMechs;
	//private final int MECH_KILL_SCORE = 5;
	//private final int BABY_MECHS_FROM_MOTHER = 5;

	/**
	 * Instantiates a new Level.
	 * @param levelID the level ID
	 * @param inventory the inventory for the level
	 * @param numberOfMechsToLose number of mechs for that level to count as a loss for the player
	 * @param currentScore current score on the level
	 * @param expectedSeconds expected time to complete the level in seconds
	 * @param elapsedTime elapsed time player spent on level in seconds
	 * @param mechs list of mechs in level
	 * @param grid the grid format of the level
	 */
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
		this.items = new ArrayList<>();
		this.breeder = new Breeder();
	}

	/**
	 * Adds an item to the list of items in a level.
	 * @param i item to be added
	 */
	public void addItem(Item i) {
		this.items.add(i);
	}

	/**
	 * Calculate points for a mech kill (used for updating a player's score), considers if a mech is pregnant or not
	 * @param m mech that has been killed
	 * @return the points for killing that mech
	 */
	private int getPointsForKill(Mech m) {
		int points = m.isPregnant() ? (SCORE_PER_KILL * (Mech.NUM_OF_BABIES_IF_BIRTHING + 1)) : SCORE_PER_KILL;
		return points;
	}

	/**
	 * Killing a mech by removing them from the grid and level.
	 * Also updates score when mech is killed.
	 * @param m mech that has been killed
	 */
	private void killMech(Mech m) {
		this.currentScore += getPointsForKill(m);
		this.getGrid().getTileAt(m.getGridX(), m.getGridY()).removeMech(m);
		this.mechs.remove(m);
	}

	/**
	 *Updating game interactions for Mechs.
	 *e.g. Checking whether any mechs are dead, if they're interacting with an item
	 * @throws Exception
	 */
	@Deprecated
	private void updateMechsOld() throws Exception {
		ArrayList<Mech> currentMechsCopy = new ArrayList<>(this.mechs);
		for(Mech m : currentMechsCopy) {
			if(m.getHealth() <= 0) {
				this.killMech(m);
				audioPlayer.playDeathSound();
				System.err.println("A mech has died");
			} else {

				Tile currentMechTile = this.getGrid().getTileAt(m.getGridX(), m.getGridY());
				Item currentItemOnTile = currentMechTile.getCurrentItem();

				if (currentItemOnTile != null) {
					System.out.println("Item is acting on mech");
					currentItemOnTile.act(m);
					System.out.println("Mech's health is now = " + m.getHealth());
				}
				for (Mech om : this.getGrid().getTileAt(m.getGridX(), m.getGridY()).getMechs()) {
					if ((!m.getType().equals(MechType.DEATH)) && m.canBreedWith(om)) {
						System.err.println("BREEDING BREEDING");
						// then they will start breeding
						// the breeding occurs for 5 seconds
						// whilst breeding, they do not move
						// after breeding they go their own way
						// how can we do this without making the entire thread wait :/
						// we need to break
					}
				}
			}
			this.getGrid().getTileAt(m.getGridX(), m.getGridY()).removeMech(m);
			m.move(this.getGrid());
			this.getGrid().getTileAt(m.getGridX(), m.getGridY()).addMech(m);
			if (m.getType().equals(MechType.DEATH)) {
				ArrayList<Mech> killTheseMechsOkay = new ArrayList<>(this.getGrid().getTileAt(m.getGridX(), m.getGridY()).getMechs());
				killTheseMechsOkay.remove(m);
				for (Mech om : killTheseMechsOkay) {
					audioPlayer.playDeathSound();
					this.killMech(om);
				}
			}
		}
	}

	private void updateMechs() throws Exception {
		MechManager.checkMechsForDeath(this.mechs, this.grid);
		MechManager.checkMechsForDamageFromItems(this.mechs, this.grid);

		ArrayList<Mech> currentMechs = new ArrayList<>(this.mechs);

		for(Mech mech : currentMechs) {
			if(mech.getHealth() <= 0) {
				this.killMech(mech);
				//audioPlayer.playDeathSound();
				System.out.println("Update Mechs method has detected a mech with <= 0 hp, so it ded");
			}
		}
		
		for(Mech m : currentMechs) {
			if(m.getType() != MechType.DEATH) {
				boolean partenr = 0 != grid.getTileAt(m.getGridX(), m.getGridY()).getBreedableMechsOnTile(m).size();
				if(((!m.getIsBaby()) && (!m.isSterile()) && (!m.isPregnant()) && (!m.isBreeding()) && partenr)) {
					System.out.println("Eurika2");
					ArrayList<Mech> availableMechs = new ArrayList<>();
					availableMechs = (grid.getTileAt(m.getGridX(), m.getGridY()).getBreedableMechsOnTile(m));
					Random rand = new Random();
					System.err.println(availableMechs.size());
					Mech breedWith = availableMechs.get(rand.nextInt(availableMechs.size()));
					this.breeder.breed(m, breedWith);
				}
			}
		}
		//MechManager.checkMechsForBreeding(this.mechs, this.grid);
		this.mechs.forEach(m -> {
			try {
				this.getGrid().getTileAt(m.getGridX(), m.getGridY()).removeMech(m);
				m.move(this.grid);
				this.getGrid().getTileAt(m.getGridX(), m.getGridY()).addMech(m);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Update game interaction with Items
	 * Placing items on the grid, if an item has been used it then gets destroyed and removed from list of current items in level
	 * @throws Exception
	 */
	public void updateItems() throws Exception {
		ArrayList<Item> currentItemCopy = new ArrayList<>(this.items);
		for(Item i : currentItemCopy) {
			this.getGrid().getTileAt(i.getGridX(), i.getGridY()).setCurrentItem(i);
			if(i.isReadyForDestroy()) {
				System.err.println("Gets destroyed");
				this.removeItem(i);
				//this.getGrid().getTileAt(i.getGridX(), i.getGridY()).setCurrentItem(null);
			}
		}
	}

	/**
	 * Removing an item from Level (and the physical grid for the level).
	 * @param i item to be removed
	 */
	private void removeItem(Item i) {
		this.items.remove(i);
		this.getGrid().getTileAt(i.getGridX(), i.getGridY()).setCurrentItem(null);
	}

	/**
	 * Update the whole Level by updating the items and the mechs in the Level.
	 * @throws Exception
	 */
	public void update() throws Exception {
		// this for loop should probs just go into an init method
		this.updateItems();
		this.updateMechs();
	}


	/**
	 * Removes a mech from Level.
	 * @param m mech to be removed
	 */
	public void removeMech(Mech m) {
		this.mechs.remove(m);
	}

	/**
	 * Add a mech to the Level.
	 * @param m mech to be added
	 */
	public void addMech(Mech m) {
		this.mechs.add(m);
	}

	/**
	 * Retrieve the grid layout of the Level
	 * @return the grid for the Level
	 */
	public Grid getGrid() {
		return this.grid;
	}

	/**
	 * Retrieve the Mechs in the Level.
	 * @return list of mechs in Level
	 */
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

	/**
	 * Retrieves losing number of Mechs for the Level.
	 * @return number of mechs to lose
	 */
	public int getLosingNumberOfMechs() {
		return numOfMechsToLose;
	}

	/**
	 * Retrieve current score of Level
	 * @return current score
	 */
	public int getCurrentScore() {
		return currentScore;
	}

	/**
	 * Sets the score of the Level.
	 * @param currentScore score value to set as the current score of level
	 */
	public void setCurrentScore(int currentScore) {
		this.currentScore = currentScore;
	}

	/**
	 * Retrieves the expected seconds to complete Level.
	 * @return expected seconds
	 */
	public int getExpectedSecondsToComplete() {
		return expectedSecs;
	}

	/**
	 * Retrieves the time elapsed playing the Level.
	 * @return elasped seconds
	 */
	public int getTimeElapsed() {
		return elapsedTime;
	}

	/**
	 * Sets the time elapsed on Level
	 * @param timeElapsed time in seconds
	 */
	public void setTimeElapsed(int timeElapsed) {
		this.elapsedTime = timeElapsed;
	}

	/**
	 * Retrieves the inventory for the Level
	 * @return the inventory for Level
	 */
	public Inventory getInventory() {
		return inventory;
	}

	/**
	 * Retrieves the level ID for the Level.
	 * @return the level ID
	 */
	public int getLevelID() {
		return levelID;
	}

	/**
	 * Sets the inventory for the Level
	 * @param inventory Inventory object for the Level
	 */
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	/**
	 * Retrieves the list of items in the Level
	 * @return items
	 */
	public ArrayList<Item> getItems() {
		return items;
	}

	/**
	 * Set the list of items in the Level .
	 * @param items items to set as the items in level
	 */
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
}
