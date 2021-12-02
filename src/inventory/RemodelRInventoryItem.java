package inventory;

public class RemodelRInventoryItem extends InventoryItem{

	private static final String name = "REMODEL_R_TO_P";
	private static int usesLeft; 
	
	public RemodelRInventoryItem() {
		super(name);
    	usesLeft = MAX_ITEM_USES;
	}
    
    public static String getName() {
    	return name;
    }
    public static void used() {
    	usesLeft-- ;
    }
    public static int getUses() {
    	return usesLeft;
    }
    public void syncUses() {
    	usesLeft = remainingUses;
    }
}
