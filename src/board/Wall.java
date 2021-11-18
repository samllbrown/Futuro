package board;

public class Wall extends Tile {
	//maybe these should be final constants instead?
	public Wall(int xPos, int yPos) {
		super(xPos, yPos);
		super.walkable = false;
		super.visible = true;
	}
	
	@Override
	public String toString() {
		return "W";
	}
	
}
