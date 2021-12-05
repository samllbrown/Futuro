package gameObject;

import javafx.scene.image.Image;
import org.w3c.dom.css.Rect;

import java.awt.*;

public abstract class Item extends Rectangle {
	//private String itemID;
	private int x, y;

	private int xRange;
	private int yRange;
	private int damage;
	protected int uses;
	
	public boolean isReadyForDestroy = false;

//	protected Item(String itemID, int xPos, int yPos, int xRange, int yRange) {
	protected Item(int x, int y, int damage, int uses) {
		this.x = x;
		this.y = y;
		this.damage = damage;
		this.uses = uses;
	}

	public int getUses() {
		return this.uses;
	}

	public void setUses(int uses) {
		this.uses = uses;
	}

	@Override
	public abstract String toString();

	public void setXRange(int xRange) {
		this.xRange = xRange;
	}
	
	public void setYRange(int yRange) {
		this.yRange = yRange;
	}
	
	public int getXRange() {
		return this.xRange;
	}
	
	public int getYRange() {
		return this.yRange;
	}
	
	public int getGridX() {
		return this.x;
	}

	public int getGridY() {
		return this.y;
	}
	public abstract Image getImage();

	public boolean isReadyForDestroy() {
		return (this.uses == 0);
	}

	public void act(Mech someMech) {
		if(this.uses != 0) {
			someMech.takeDamage(this.damage);
			this.uses--;
		} else {
			System.err.println("Cannot act on mech as uses left <= 0");
		}
	}
}
