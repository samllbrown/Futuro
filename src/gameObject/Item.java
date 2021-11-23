package gameObject;


public abstract class Item {
	//private String itemID;
	private int xPos;
	private int yPos;

	
//	protected Item(String itemID, int xPos, int yPos, int xRange, int yRange) {
	protected Item(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}


	public int getxPos() {
		return this.xPos;
	}

	public int getyPos() {
		return this.yPos;
	}

	// every item will act on some mech
	public abstract void act(Mech someMech);
}
