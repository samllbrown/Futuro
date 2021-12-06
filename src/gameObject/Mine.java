package gameObject;

import javafx.scene.image.Image;

/**
 * Mine.java
 * @author Sam B, Debbie L
 * Last Mod Date: 02/12/2021
 * Description: Mine Item, mimics the poison weapon (instakill) from the functional spec. Inherits from Item Class.
 */
public class Mine extends Item {
	private static final int X_RANGE = 0;
	private static final int Y_RANGE = 0;
	public static final int USES = 1;
	private static final int DAMAGE = 1000;
	public static final Image ITEM_IMAGE = new Image("file:res/Sprites/mine.png",50, 50, false, false);

	/**
	 * Instantiates a Mine Item.
	 * Uses Item super constructor to set damage and uses for the Mine object.
	 * @param x Starting x coordinate of Mine Item.
	 * @param y Starting y coordinate of Mine Item.
	 */
	public Mine(int x, int y) {
		super(x, y, DAMAGE, USES);
		setXRange(X_RANGE);
		setYRange(Y_RANGE);
	}

	/**
	 * Retrieve the image of the Mine for the GUI
	 * @return the Mine sprite image
	 */
	@Override
	public Image getImage() {
		return ITEM_IMAGE;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return String.format("%s,%d,%d,%d", "MINE",this.getGridX(),this.getGridY(),this.uses);
	}

}
