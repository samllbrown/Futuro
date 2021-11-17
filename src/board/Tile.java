package board;

// Tile<T> instead of subclassing each individual type of tile?

public class Tile {
	// need to add tileType ++ tiles enumeration here (if we're doing it this way)
	// xPos and yPos -- make these final?
	protected int xPos;
	protected int yPos;
	protected boolean walkable;
	protected boolean visible;
	
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

	public void setXPos(int newXPos) {
		this.xPos = newXPos;
	}
	
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