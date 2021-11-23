package board;

public class Path extends Tile {
	// maybe these should be final constants instead?
	private static final boolean IS_WALKABLE = false;
	private static final boolean IS_VISIBLE = true;
	private static final boolean IS_PLACEABLE = true;
	
	public Path(int xPos, int yPos) {
		super(xPos, yPos, IS_WALKABLE, IS_VISIBLE, IS_PLACEABLE);
	}
	
	public String toString() {
		//return (String.format("Path tile at x:%d, y:%d", this.getXPos(), this.getYPos()));
		return "P";
	}
}
