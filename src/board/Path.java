package board;

public class Path extends Tile {
	
	private static final char type = 'P';
	private static final boolean IS_WALKABLE = true;
	private static final boolean IS_VISIBLE = true;
	private static final boolean IS_PLACEABLE = true;

	
	public Path(int xPos, int yPos) {
		super(xPos, yPos, IS_WALKABLE, IS_VISIBLE, IS_PLACEABLE);
	}
	
	public String toString() {
		return String.format(type + " tile at x:%d, y:%d", this.getXPos(), this.getYPos());
	}
}
