package gameObject;

import javafx.scene.image.Image;
import java.awt.*;

/**
 * Item.java
 * @author Sam R, Sam B
 * Description:The Class Item serves as a base for all items.
 * Last Mod Date: 04/12/2021
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
	 * @param x      the x coordinate of the starting position
	 * @param y      the y coordinate of the starting position
	 * @param damage the amount damage the item can perform on a mech
	 * @param uses   the amount of uses left of the item
	 */
	protected Item(int x, int y, int damage, int uses) {
		this.x = x;
		this.y = y;
		this.damage = damage;
		this.uses = uses;
	}

	/**
	 * Gets the uses.
	 *
	 * @return the uses
	 */
	public int getUses() {
		return this.uses;
	}

	/**
	 * Sets the uses.
	 *
	 * @param uses the new uses
	 */
	public void setUses(int uses) {
		this.uses = uses;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public abstract String toString();
  
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
	 * @return the image of the item
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
	 * Act - This performs an action on a mech from an item
	 * 
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
