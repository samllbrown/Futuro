package gameObject;

import javafx.scene.image.Image;

/**
 * EMP.java
 * @author
 * @version
 * Last Mod Date:
 * Description: Emp Item, mimics the bomb weapon from the functional spec. Inherits from Item Class.
 */
public class EMP extends Item {
	private static final int X_RANGE = 4;
	private static final int Y_RANGE = 4;
	private static final int USES = 1;

	public static final Image ITEM_IMAGE = new Image("file:res/Sprites/emp.png",50, 50, false, false);

	private static final int DAMAGE = 100;

	/**
	 * Instantiates an EMP Item.
	 * Uses Item super constructor to set the location, damage and uses for the EMP object.
	 * @param x Starting x coordinate of EMP Item.
	 * @param y Starting y coordinate of EMP Item.
	 */
	public EMP(int x, int y) {
		super(x, y, DAMAGE, USES);
		setXRange(X_RANGE);
		setYRange(Y_RANGE);
	}

	/**
	 * Retrieve the image of the EMP for the GUI
	 * @return the EMP sprite image
	 */
	@Override
	public Image getImage() {
		return ITEM_IMAGE;
	}
}
