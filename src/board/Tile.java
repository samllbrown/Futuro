package board;
import gameObject.Item;

import java.util.ArrayList;
// Tile<T> instead of subclassing each individual type of tile?

public abstract class Tile {
	// need to add tileType ++ tiles enumeration here (if we're doing it this way)
	// xPos and yPos -- make these final?
	final protected int xPos;
	final protected int yPos;
	final protected boolean walkable;
	final protected boolean visible;
	final protected boolean placeable;
	protected ArrayList<Item> itemsOnTile;

	public Tile(int xPos, int yPos, boolean walkable, boolean visible, boolean placeable) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.walkable = walkable;
		this.visible = visible;
		this.placeable = placeable;
	}

	public ArrayList<Item> getItemsOnTile() {
		return this.itemsOnTile;
	}

	public void addItemToTile(Item item) {
		this.itemsOnTile.add(item);
	}

	public void removeItemFromTile(Item item) {
		this.itemsOnTile.remove(item);}

	@Override
	//X means it's just a tile, not a path, not a tunnel, not a wall, a tile
	public String toString() {
		return String.format("X");
	}
	
	public int getXPos() {
		return this.xPos;
	}

	public int getYPos() {
		return this.yPos;
	}
	
	public boolean isWalkable() {
		return this.walkable;
	}
	
	public boolean isVisible() {
		return this.visible;
	}
	
}