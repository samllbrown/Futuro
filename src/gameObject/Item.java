package gameObject;


public abstract class Item {
	private String itemID;
	private int xPos; 
	private int yPos; 
	private int xRange;
	private int yRange;
	
	public Item(String itemID, int xPos, int yPos, int xRange, int yRange) {
		this.itemID = itemID;
		this.xPos = xPos;
		this.yPos = yPos;
		this.xRange = xRange;
		this.yRange = yRange;
	}
	
}
