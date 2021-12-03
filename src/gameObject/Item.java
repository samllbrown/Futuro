package gameObject;

import javafx.scene.image.Image;
import java.awt.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Item.
 */
public abstract class Item extends Rectangle {

	/** The y. */
	private int x, y;

	/** The x range of the item. */
	private int xRange;

	/** The y range of the item. */
	private int yRange;

	/** The x range of the item. */
	private int damage;
	
	/** The uses. */
	protected int uses;

	/** The is ready for destroy. */
	public boolean isReadyForDestroy = false;

/**
 * Instantiates a new item.
 *
 * @param x the x
 * @param y the y
 * @param damage the damage
 * @param uses the uses
 */
//	protected Item(String itemID, int xPos, int yPos, int xRange, int yRange) {
	protected Item(int x, int y, int damage, int uses) {
		this.x = x;
		this.y = y;
		this.damage = damage;
		this.uses = uses;
	}

	/**
	 * Sets the x range.
	 *
	 * @param xRange the new x range
	 */
	public void setXRange(int xRange) {
		this.xRange = xRange;
	}

	/**
	 * Sets the y range.
	 *
	 * @param yRange the new y range
	 */
	public void setYRange(int yRange) {
		this.yRange = yRange;
	}

	/**
	 * Gets the x range.
	 *
	 * @return the x range
	 */
	public int getXRange() {
		return this.xRange;
	}

	/**
	 * Gets the y range.
	 *
	 * @return the y range
	 */
	public int getYRange() {
		return this.yRange;
	}

	/**
	 * Gets the grid X.
	 *
	 * @return the grid X
	 */
	public int getGridX() {
		return this.x;
	}

	/**
	 * Gets the grid Y.
	 *
	 * @return the grid Y
	 */
	public int getGridY() {
		return this.y;
	}

	/**
	 * Gets the image of the item.
	 *
	 * @return the image
	 */
	public abstract Image getImage();

	/**
	 * Checks if is ready for destroy.
	 *
	 * @return true, if is ready for destroy
	 */
	public boolean isReadyForDestroy() {
		return (this.uses == 0);
	}

	/**
	 * Act - This performs an action on a mech
	 * from an item
	 * @param someMech the inputed mech
	 */
	public void act(Mech someMech) {
		if (this.uses != 0) {
			someMech.takeDamage(this.damage);
			this.uses--;
		} else {
			System.err.println("Cannot act on mech as uses left <= 0");
		}
	}
}
