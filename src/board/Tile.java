package board;

import java.util.ArrayList;

import gameObject.Item;
import gameObject.Mech;
import gameObject.TileType;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import managers.Game;

public class Tile extends Rectangle {
	private ArrayList<Mech> currentMechs;

	private Item currentItem;

	private Image img;
	private TileType tileType;
	private boolean visibleTile;
	// coords not pixels
	// type could be enum
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

	public boolean isWalkable() {
		return (this.tileType.walkable == 1);
	}

	public Image getImage() {
		return this.img;
	}

	public boolean isVisibleTile() {
		return this.visibleTile;
	}

	public TileType getTileType() {
		return this.tileType;
	}
	public static Image getImageForType(TileType tileType) {
		switch(tileType) {
			case PATH:
				return new Image("file:res/Sprites/grassMid.png",50, 50, false, false);
			default:
				return new Image("file:res/Sprites/tileW.png",50, 50, false, false);
		}
	}

	public void setCurrentItem(Item item) {
		this.currentItem = item;
	}

	public Item getCurrentItem() {
		return this.currentItem;
	}

	public ArrayList<Mech> getMechs() {
		return this.currentMechs;
	}

	public void addMech(Mech m) {
		this.currentMechs.add(m);
	}

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