package gameObject;

import javafx.scene.image.Image;

/**
 * Acid.java
 * @author Sam B, Debbie L
 * Last Mod Date: 06/12/2021
 * Description: Acid Item, mimics the gas weapon from the functional spec. Inherits from Item Class.
 */
public class Acid extends Item {

	/** The Constant X_RANGE. */
	private static final int X_RANGE = 3;
	
	/** The Constant Y_RANGE. */
	private static final int Y_RANGE = 3;
	
	/** The Constant ITEM_IMAGE. */
	public static final Image ITEM_IMAGE = new Image("file:res/Sprites/acid.png",50, 50, false, false);
	
	/** The Constant ITEM_IMAGE_PUDDLE. */
	public static final Image ITEM_IMAGE_PUDDLE = new Image("file:res/Sprites/acidPuddle.png",50, 50, false, false);
	
	/** The Constant USES. */
	private static final int USES = 4;
	
	/** The duration. */
	private int DURATION = 5; //5 ticks or seconds
	
	/** The damage over time adult. */
	private static int DAMAGE_OVER_TIME_ADULT = 25; //25 Damage per tick
	
	/** The damage over time baby. */
	private static int DAMAGE_OVER_TIME_BABY = 10; //25 Damage per tick

	/**
	 * Instantiates an Acid Item.
	 * Uses Item super constructor to set the location, damage and uses for the acid object.
	 * @param atX Starting x coordinate of Acid Item.
	 * @param atY Starting y coordinate of Acid Item.
	 */
	public Acid(int atX, int atY) {
		super(atX, atY, DAMAGE_OVER_TIME_ADULT, USES);
		setXRange(X_RANGE);
		setYRange(Y_RANGE);
	}

	/**
	 * Act of doing damange to a Mech.
	 * Damage done per tick of the game, overridden from method in Item class.
	 * @param mech mech the action is being performed on
	 */
	@Override
	public void act(Mech mech) {
		if(super.uses != 0) {
			mech.takeDamage(mech.getIsBaby() ? DAMAGE_OVER_TIME_BABY : DAMAGE_OVER_TIME_ADULT);
			System.err.println("A MECH HAS TAKEN DAMAGE");
			super.uses--;
		}
	}

	/**
	 * Retrieve the image of the Acid for the GUI.
	 *
	 * @return the Acid sprite image
	 */
	@Override
	public String toString() {
		return String.format("%s,%d,%d,%d", "ACID",this.getGridX(),this.getGridY(),this.uses);
	}

	/**
	 * Gets the image.
	 *
	 * @return the image
	 */
	@Override
	public Image getImage() { return ITEM_IMAGE_PUDDLE;}
}
