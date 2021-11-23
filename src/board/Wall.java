package board;

public class Wall extends Tile {
	//maybe these should be final constants instead?
	
	private static final boolean IS_WALKABLE = false;
	private static final boolean IS_VISIBLE = true;
	private static final boolean IS_PLACEABLE = false;
	
	public Wall(int xPos, int yPos) {
     super(xPos, yPos, IS_WALKABLE, IS_VISIBLE, IS_PLACEABLE); 
	}
	
	
	@Override
	public String toString() {
		return "W";
	}
	
}
