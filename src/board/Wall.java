package board;

public class Wall extends Tile {

	private static final char type = 'W';
	private static final boolean IS_WALKABLE = false;
	private static final boolean IS_VISIBLE = true;
	private static final boolean IS_PLACEABLE = false;
	
	public Wall(int xPos, int yPos) {
		super(xPos, yPos, IS_WALKABLE, IS_VISIBLE, IS_PLACEABLE); 
	}
	
	public String toString() {
		return String.format(type + " tile at x:%d, y:%d", this.getXPos(), this.getYPos());
	}
}
