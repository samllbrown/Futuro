package board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import board.Tile;

import board.Grid;
import gameObject.GameObjectFactory;
import gameObject.Item;
import gameObject.Mech;
import gameObject.MechType;
import inventory.Inventory;
import javafx.scene.control.Button;
import managers.Breeder;
import managers.MechManager;
import services.AudioPlayer;

/**
 * Level.java
 * @author Sam R, Sam B, Mart
 * Last Mod Date: 06/12/2021
 */
public class Level {

    /** The Constant WINNING_NUMBER_OF_MECHS. */
    private static final int WINNING_NUMBER_OF_MECHS = 0;

    /** The Constant SCORE_PER_KILL. */
    private static final int SCORE_PER_KILL = 10;

    /** The level ID. */
    private int levelID;

    /** The num of mechs to lose. */
    private int numOfMechsToLose;

    /** The current score. */
    private int currentScore;

    /** The expected secs. */
    private int expectedSecs;

    /** The elapsed time. */
    private int elapsedTime;

    /** The inventory. */
    private Inventory inventory;

    /** The items. */
    private ArrayList<Item> items;

    /** The mechs. */
    private ArrayList<Mech> mechs;

    /** The grid. */
    private Grid grid;

    /** The item respawn rate. */
    private int itemRespawnRate;

    /** The breeder. */
    private Breeder breeder;

    /** The label. */
    private Button label;

    /**
     * Instantiates a new Level.
     *
     * @param levelID the level ID
     * @param inventory the inventory for the level
     * @param numberOfMechsToLose number of mechs for that level to count as a loss for the player
     * @param currentScore current score on the level
     * @param expectedSeconds expected time to complete the level in seconds
     * @param elapsedTime elapsed time player spent on level in seconds
     * @param mechs list of mechs in level
     * @param grid the grid format of the level
     * @param itemRespawnRate the item respawn rate
     */
    public Level(int levelID, Inventory inventory, int numberOfMechsToLose, int currentScore, int expectedSeconds,
	    int elapsedTime, ArrayList<Mech> mechs, Grid grid, int itemRespawnRate) {
	this.levelID = levelID;
	this.inventory = inventory;
	this.numOfMechsToLose = numberOfMechsToLose;
	this.currentScore = currentScore;
	this.expectedSecs = expectedSeconds;
	this.elapsedTime = elapsedTime;
	this.mechs = mechs;
	this.grid = grid;
	this.items = new ArrayList<>();
	this.itemRespawnRate = itemRespawnRate;
	this.breeder = new Breeder();
	this.label = new Button(Integer.toString(this.currentScore));
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
	String levelAsString = "";
	levelAsString += this.levelID + "\n";
	levelAsString += this.grid.getWidth() + "\n";
	levelAsString += this.grid.getHeight() + "\n";
	levelAsString += this.grid.toString();
	levelAsString += this.mechs.size() + "\n";
	levelAsString += this.getMechsAsString();
	levelAsString += this.getItems().size() + "\n";
	levelAsString += this.getItemsAsString();
	levelAsString += this.inventory.size() + "\n";
	levelAsString += this.inventory.toString();
	levelAsString += this.currentScore + "\n";
	levelAsString += this.elapsedTime + "\n";
	levelAsString += this.expectedSecs + "\n";
	levelAsString += this.numOfMechsToLose;
	return levelAsString;
    }

    /**
     * Gets the items as string.
     *
     * @return the items as string
     */
    private String getItemsAsString() {
	String itemsAsString = "";
	for (Item i : this.items) {
	    itemsAsString += i.toString() + "\n";
	}
	return itemsAsString;
    }

    /**
     * Gets the mechs as string.
     *
     * @return the mechs as string
     */
    private String getMechsAsString() {
	String mechsAsString = "";
	for (Mech m : this.mechs) {
	    mechsAsString += m.toString();
	    mechsAsString += "\n";
	}
	return mechsAsString;
    }

    /**
     * Adds an item to the list of items in a level.
     * @param i item to be added
     */
    public void addItem(Item i) {
	this.items.add(i);
	this.grid.getTileAt(i.getGridX(), i.getGridY()).setCurrentItem(i);
    }

    /**
     * Calculate points for a mech kill (used for updating a player's score), considers if a mech is pregnant or not.
     *
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
	AudioPlayer.playDeathSound();
    }

    /**
     * Updating game interactions for Mechs.
     * e.g. Checking whether any mechs are dead, if they're interacting with an item
     *
     * @throws Exception the exception
     */
    private void updateMechs() throws Exception {
	label.setText("Score: " + Integer.toString(currentScore));
	MechManager.checkMechsForDeath(this.mechs, this.grid);
	MechManager.checkMechsForDamageFromItems(this.mechs, this.grid);

	ArrayList<Mech> currentMechs = new ArrayList<>(this.mechs);

	for (Mech mech : currentMechs) {
	    if (mech.getHealth() <= 0) {
		this.killMech(mech);
		AudioPlayer.playDeathSound();
		System.out.println("Update Mechs method has detected a mech with <= 0 hp, so it ded");
	    } else {
		if (mech.getIsBaby()) {
		    if (mech.getTimeUntilAdult() == 0) {
			System.err.println("Should now be an adult tbh");
			mech.growIntoAdult();
		    } else {
			mech.reduceTimeUntilAdult();
		    }
		} else {
		    mech.reduceBreedingCoolDown();
		}
	    }
	}

	for (Mech m : currentMechs) {
	    if (m.getType() != MechType.DEATH) {
		boolean partenr = 0 != grid.getTileAt(m.getGridX(), m.getGridY()).getBreedableMechsOnTile(m).size();
		if (((!m.getIsBaby()) && (!m.isSterile()) && (!m.isPregnant()) && (!m.isBreeding()) && partenr)) {
		    System.out.println("Eurika2");
		    ArrayList<Mech> availableMechs = new ArrayList<>();
		    availableMechs = (grid.getTileAt(m.getGridX(), m.getGridY()).getBreedableMechsOnTile(m));
		    if (availableMechs.size() != 0) {
			Random rand = new Random();
			System.err.println("AVAILABLE MECHS: " + availableMechs.size());
			Mech breedWith = availableMechs.get(rand.nextInt(availableMechs.size()));
			this.breeder.breed(m, breedWith);
		    }
		}
	    }
	}

	this.mechs.forEach(m -> {
	    try {
		if (!m.isBreeding()) {
		    this.getGrid().getTileAt(m.getGridX(), m.getGridY()).removeMech(m);
		    m.move(this.grid);
		    this.getGrid().getTileAt(m.getGridX(), m.getGridY()).addMech(m);
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	});
    }

    /**
     * Update game interaction with Items
     * Placing items on the grid, if an item has been used it then gets destroyed and removed from list of current items in level.
     *
     * @throws Exception the exception
     */
    public void updateItems() throws Exception {
	ArrayList<Item> currentItemCopy = new ArrayList<>(this.items);
	for (Item i : currentItemCopy) {
	    this.getGrid().getTileAt(i.getGridX(), i.getGridY()).setCurrentItem(i);
	    if (i.isReadyForDestroy()) {
		System.err.println("Gets destroyed");
		this.removeItem(i);
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
     * Update inventory items.
     */
    private void updateInventoryItems() {
	for (var i : this.inventory.getItems().entrySet()) {
	    Random random = new Random();
	    if (random.nextInt(itemRespawnRate) == itemRespawnRate - 1) {
		i.getValue().regenUse();
		this.inventory.getLabel(i.getKey()).setText(Integer.toString(this.inventory.getItemUses(i.getKey())));
	    }

	}
    }

    /**
     * Update the whole Level by updating the items and the mechs in the Level.
     *
     * @throws Exception the exception
     */
    public void update() throws Exception {
	this.updateItems();
	this.breeder.update(this.mechs, grid);
	this.updateMechs();
	this.updateInventoryItems();
    }

    /**
     * Removes a mech from Level.
     * @param m mech to be removed
     */
    public void removeMech(Mech m) {
	this.mechs.remove(m);
	this.grid.getTileAt(m.getGridX(), m.getGridY()).removeMech(m);
    }

    /**
     * Add a mech to the Level.
     * @param m mech to be added
     */
    public void addMech(Mech m) {
	this.mechs.add(m);
	this.grid.getTileAt(m.getGridX(), m.getGridY()).addMech(m);
    }

    /**
     * Retrieve the grid layout of the Level.
     *
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

    /**
     * Retrieves losing number of Mechs for the Level.
     * @return number of mechs to lose
     */
    public int getLosingNumberOfMechs() {
	return numOfMechsToLose;
    }

    /**
     * Retrieve current score of Level.
     *
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
     * Sets the time elapsed on Level.
     *
     * @param timeElapsed time in seconds
     */
    public void setTimeElapsed(int timeElapsed) {
	this.elapsedTime = timeElapsed;
    }

    /**
     * Retrieves the inventory for the Level.
     *
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
     * Sets the inventory for the Level.
     *
     * @param inventory Inventory object for the Level
     */
    public void setInventory(Inventory inventory) {
	this.inventory = inventory;
    }

    /**
     * Retrieves the list of items in the Level.
     *
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

    /**
     * Gets the button.
     *
     * @return the button
     */
    public Button getButton() {
	return this.label;
    }
}
