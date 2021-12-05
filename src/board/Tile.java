package board;

import java.util.ArrayList;
import java.util.stream.Collectors;

import gameObject.Item;
import gameObject.Mech;
import gameObject.MechType;
import gameObject.TileType;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import managers.Game;

/**
 * Tile.java
 * @author
 * @version
 * Last Mod Date:
 */

/**
 * The Tile class represents a tile on the game canvas.
 */
public class Tile extends Rectangle {
	private ArrayList<Mech> currentMechs;

	private Item currentItem;

	private Image img;
	private TileType tileType;
	private boolean visibleTile;

	/**
	 *Tile class constructor, includes all data a tile needs to know (e.g it's image, mechs on the tile etc.).
	 * @param tileType what type of tile the constructed tile is (walkable,visible or placeable)
	 * @param x x-coord of tile
	 * @param y y-coord of tile
	 */
	public Tile(TileType tileType, int x, int y) {
		setWidth(Game.TILE_SIZE);
		setHeight(Game.TILE_SIZE);
		relocate(x * Game.TILE_SIZE, y * Game.TILE_SIZE);
		this.tileType = tileType;
		this.img = getImageForType(tileType);
		this.currentMechs = new ArrayList<>();
		this.visibleTile = (this.tileType == TileType.TUNNEL ? false : true);
		setFill(new ImagePattern(this.img));
		this.currentItem = null;
	}

	public ArrayList<Mech> getOtherMechsOnTile(Mech differentToMech) throws Exception {
		if(this.currentMechs.contains(differentToMech)) {
			ArrayList<Mech> otherMechsOnTile = new ArrayList<>(currentMechs);
			otherMechsOnTile.remove(differentToMech);
			return otherMechsOnTile;
		} else {
			throw new Exception("Could not get the other mechs on the same tile as the mech given is not on this tile");
		}
	}

	public ArrayList<Mech> getBreedableMechsOnTile(Mech forMech) {
		ArrayList<Mech> breedableMechs = new ArrayList<>();
		if(this.currentMechs.contains(forMech)) {
			for(Mech nm : currentMechs) {
				if(nm.getType() != MechType.DEATH && (!nm.equals(forMech))) {
					breedableMechs.add(nm);
				}
			}
		}
		return new ArrayList<Mech>(breedableMechs.stream().filter(normalMech ->
				((!normalMech.isPregnant()) && (!normalMech.getIsBaby()) && (!normalMech.isSterile()) && (normalMech.getType() != forMech.getType()))).collect(Collectors.toList()));
	}

	/**
	 * Checks if the Tile allows mechs to walk on it
	 * @return true if Tile is walkable
	 */
	public boolean isWalkable() {
		return (this.tileType.walkable == 1);
	}

	/**
	 * Retrieves image of the Tile.
	 * @return sprite image of tile
	 */
	public Image getImage() {
		return this.img;
	}

	/**
	 * Checks if the Tile is visible in the game.
	 * @return true if a Tile is visible
	 */
	public boolean isVisibleTile() {
		return this.visibleTile;
	}

	/**
	 * Gets the type of Tile object
	 * @return the tile type
	 */
	public TileType getTileType() {
		return this.tileType;
	}

	/**
	 *Retrieves image of Specific tile types from sprite file
	 * @param tileType specific type of tile
	 * @return Image of tile
	 */
	public static Image getImageForType(TileType tileType) {
		switch(tileType) {
			case PATH:
				return new Image("file:res/Sprites/grassMid.png",50, 50, false, false);
			case TUNNEL:
				return new Image("file:res/Sprites/tileT.png", 50, 50, false, false);
			default:
				return new Image("file:res/Sprites/tileW.png",50, 50, false, false);
		}
	}

	/**
	 * Sets item on the tile.
	 * @param item item being placed on tile
	 */
	public void setCurrentItem(Item item) {
		this.currentItem = item;
	}

	/**
	 * Gets the item on the tile.
	 * @return item on tile
	 */
	public Item getCurrentItem() {
		return this.currentItem;
	}

	/**
	 *Gets all the Mechs on the Tile object
	 * @return list of Mechs on tile
	 */
	public ArrayList<Mech> getMechs() {
		return this.currentMechs;
	}

	/**
	 *Remove a mech from the Tile object.
	 * @param m mech to be removed
	 */
	public void removeMech(Mech m) {
		this.currentMechs.remove(m);
	}

	/**
	 *Adds a mech to the Tile
	 * @param m mech to be added
	 */
	public void addMech(Mech m) {
		this.currentMechs.add(m);
	}

	/**
	 * Check if a Tile has a mech on it.
	 * @return true, if number of mechs on the Tile is zero
	 */
	public boolean hasMech() {
		return this.currentMechs.size() != 0;
	}
}
// Tile<T> instead of subclassing each individual type of tile?
//public abstract class Tile {
//	// need to add tileType ++ tiles enumeration here (if we're doing it this way)
//	// xPos and yPos -- make these final?
//	final protected int xPos;
//	final protected int yPos;
//	final protected boolean walkable;
//	final protected boolean visible;
//	final protected boolean placeable;
//
//	protected ArrayList<Item> itemsOnTile;
//
//	public Tile(int xPos, int yPos, boolean walkable, boolean visible, boolean placeable) {
//		this.xPos = xPos;
//		this.yPos = yPos;
//		this.walkable = walkable;
//		this.visible = visible;
//		this.placeable = placeable;
//	}
//
//	public boolean hasMech() {
//		for(Item i : itemsOnTile) {
//			if(i.getClass().getCanonicalName().equals("gameObject.Mech")) {
//				return true;
//			}
//		}
//		return false;
//	}
//
//	public ArrayList<Mech> getCurrentMechs() {
//		ArrayList<Mech> mechs = new ArrayList<>();
//		for(Item i : this.itemsOnTile) {
//			if(i.getClass().getCanonicalName() == "gameObject.Mech") {
//				mechs.add((Mech) i);
//			}
//		}
//		return mechs;
//	}
//
//	public ArrayList<Item> getItemsOnTile() {
//		return this.itemsOnTile;
//	}
//
//	public void addItemToTile(Item item) {
//		this.itemsOnTile.add(item);
//	}
//
//	public void removeItemFromTile(Item item) {
//		this.itemsOnTile.remove(item);
//	}
//
//	public int getXPos() {
//		return this.xPos;
//	}
//
//	public int getYPos() {
//		return this.yPos;
//	}
//
//	public boolean isWalkable() {
//		return this.walkable;
//	}
//
//	public boolean isVisible() {
//		return this.visible;
//	}
//
//	public boolean isPlaceable() {
//		return this.placeable;
//	}
//
//	abstract public String toString();
//}