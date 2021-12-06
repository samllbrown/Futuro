package gameObject;

import javafx.scene.image.Image;

/**
 * EMP.java
 * @author Sam B, Debbie
 * Last Mod Date:
 * Description: Puddle Item, mimics the no-entry-sign from the functional spec. Inherits from Item Class.
 */
public class Puddle extends Item {

    /** The Constant X_RANGE. */
    private static final int X_RANGE = 0;

    /** The Constant Y_RANGE. */
    private static final int Y_RANGE = 0;

    /** The Constant USES. */
    private static final int USES = 3;

    /** The health. */
    private int health;

    /** The Constant ITEM_IMAGE. */
    public static final Image ITEM_IMAGE = new Image("file:res/Sprites/puddle.png", 50, 50, false, false);

    /**
     * Instantiates a Puddle Item.
     * Uses Item super constructor to set the location, damage and uses for the Puddle object.
     * @param x Starting x coordinate of EMP Item.
     * @param y Starting y coordinate of EMP Item.
     */
    public Puddle(int x, int y) {
	super(x, y, 0, USES);
	this.health = 15;
	setXRange(X_RANGE);
	setYRange(Y_RANGE);
    }

    /**
     * Act of forcing a mech to turn around after hitting a puddle.
     * Overridden from method in Item class, puddle should disappear after 5 hits.
     * @param mech mech the action is being performed on
     */
    @Override
    public void act(Mech mech) {
	try {
	    if (super.uses != 0) {
		mech.turn("AROUND");
		this.health -= 5;
		super.uses -= 1;
		if (this.health == 0) {
		    System.err.println("THIS SHOULD NO LONGER BE HERE");
		}
	    }
	} catch (Exception e) {
	    System.err.println("PUDDLE HAS THROWN AN ERROR WHILST TRYING TO TURN THE MECH AROUND");
	}
    }

    /**
     * Retrieve the image of the Puddle for the GUI.
     *
     * @return the Puddle sprite image
     */
    @Override
    public Image getImage() {
	return ITEM_IMAGE;
    }

    /**
     * Retrieves the "health" of the puddle.
     * "health" being how many times a mech can hit a puddle before it's not there anymore
     * @return health
     */
    public int getHealth() {
	return health;
    }

    /**
     * Sets the "health" of the puddle.
     * "health" being how many times a mech can hit a puddle before it's not there anymore
     *
     * @param health the new health
     */
    public void setHealth(int health) {
	this.health = health;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
	return String.format("%s,%d,%d,%d", "PUDDLE", this.getGridX(), this.getGridY(), this.uses);
    }

}
