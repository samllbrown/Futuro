package gameObject;

import javafx.scene.image.Image;

public abstract class Item {
	//private String itemID;
	private int x, y;

	
//	protected Item(String itemID, int xPos, int yPos, int xRange, int yRange) {
	protected Item(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getGridX() {
		return this.x;
	}

	public int getGridY() {
		return this.y;
	}
	public abstract Image getImage();
	
	// every item will act on some mech
	public abstract void act(Mech someMech);
}
