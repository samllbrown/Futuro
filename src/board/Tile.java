package board;

// Tile<T> instead of subclassing each individual type of tile?

public class Tile {
	// need to add tileType ++ tiles enumeration here (if we're doing it this way)
	// xPos and yPos -- make these final?
	final protected int xPos;
	final protected int yPos;
	final protected boolean walkable;
	final protected boolean visible;
	
	public Tile(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	@Override
	public String toString() {
		return String.format("X");
	}
	
	public int getXPos() {
		return this.xPos;
	}

	public int getYPos() {
		return this.yPos;
	}
	
	// needed?
	public void setXPos(int newXPos) {
		this.xPos = newXPos;
	}
	
	// needed?
	public void setYPos(int newYPos) {
		this.yPos = newYPos;
	}
	
	public boolean isWalkable() {
		return this.walkable;
	}
	
	public boolean isVisible() {
		return this.visible;
	}
	
	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
}