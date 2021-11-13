public abstract class Tile {
	// need to add tileType ++ tiles enumeration here (if we're doing it this way)
	private int xPos;
	private int yPos;

	public Tile(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
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
}
