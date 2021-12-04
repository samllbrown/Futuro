package gameObject;

import javafx.scene.image.Image;

/**
 * Lightning.java
 * @author
 * @version
 * Last Mod Date:
 * Description: Lightning Item, mimics the sterilisation weapon from the functional spec. Inherits from Item Class.
 */
public class Lightning extends Item {
	private static final int X_RANGE = 2;
	private static final int Y_RANGE = 2;
	public static final Image ITEM_IMAGE = new Image("file:res/Sprites/lightning.png",50, 50, false, false);
	public static final int USES = 1;
	private int DURATION = 3; //3 ticks or seconds

	/**
	 * Instantiates a lightning Item.
	 * Uses Item super constructor to set the location, damage and uses for the lightning object.
	 * @param atX Starting x coordinate of Lightning Item.
	 * @param atY Starting y coordinate of Lightning Item.
	 */
	public Lightning(int atX, int atY) {
		super(atX, atY, 0, USES);
		setXRange(X_RANGE);
		setYRange(Y_RANGE);
	}

	/**
	 * Act of doing "sterilising" to a Mech (can't reproduce).
	 * Overridden from method in Item class.
	 * @param mech mech the action is being performed on
	 */
	@Override
	public void act(Mech mech) {
		if(super.uses != 0) {
			mech.setSterile(true);
			super.uses--;
		}

	}

	@Override
	public String generateItemID() {
		return "LIGHTNING_ITEM," + this.getX() + "," + this.getY();
	}

	/**
	 * Retrieve the image of the Acid for the GUI
	 * @return the Acid sprite image
	 */
	@Override
	public Image getImage() {
		return ITEM_IMAGE;
	}

}
