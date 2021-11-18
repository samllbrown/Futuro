package gameObject;


public abstract class Item {
	//private String itemID;
	private int xPos;
	private int yPos;
	// ranges need looking at. they don't work as we expect them too.
	private int xRange;
	private int yRange;
	
//	protected Item(String itemID, int xPos, int yPos, int xRange, int yRange) {
	protected Item(int xPos, int yPos, int xRange, int yRange) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.xRange = xRange;
		this.yRange = yRange;
	}
	
	// every item will act on some mech
	public abstract void act(Mech someMech);
}
