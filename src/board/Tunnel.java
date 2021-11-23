package board;

public class Tunnel extends Tile {
	// should every tile have access to the thing(s) on it or
	// should every thing have access to the tile it is on
	// tunnel should never be visible
	private static final boolean IS_VISIBLE = false;
	
	//maybe these should be final constants instead?
	public Tunnel(int xPos, int yPos) {
		super(xPos, yPos, true, IS_VISIBLE, true);
	}
	
	public String toString() {
		return (String.format("Tunnel tile at x:%d, y:%d", this.getXPos(), this.getYPos()));
	}
	
}
