package board;

public class Path extends Tile {
	// maybe these should be final constants instead?
	public Path(int xPos, int yPos) {
		super(xPos, yPos, true, true, true);
	}
	
	public String toString() {
		//return (String.format("Path tile at x:%d, y:%d", this.getXPos(), this.getYPos()));
		return "P";
	}
}
