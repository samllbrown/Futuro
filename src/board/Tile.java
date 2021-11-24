package board;

import java.util.ArrayList;

import gameObject.Item;
import gameObject.Mech;

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

	public boolean hasMech() {
		for(Item i : itemsOnTile) {
			if(i.getClass().getCanonicalName().equals("gameObject.Mech")) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<Mech> getCurrentMechs() {
		ArrayList<Mech> mechs = new ArrayList<>();
		for(Item i : this.itemsOnTile) {
			if(i.getClass().getCanonicalName() == "gameObject.Mech") {
				mechs.add((Mech) i);
			}
		}
		return mechs;
	}

	public ArrayList<Item> getItemsOnTile() {
		return this.itemsOnTile;
	}
    
	public void addItemToTile(Item item) {
		this.itemsOnTile.add(item);
	}

	public void removeItemFromTile(Item item) {
		this.itemsOnTile.remove(item);
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
	
	public boolean isPlaceable() {
		return this.placeable;
	}

	abstract public String toString();
}