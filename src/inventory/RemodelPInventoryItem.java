package inventory;

public class RemodelPInventoryItem extends InventoryItem{

	private static final String name = "REMODEL_P_TO_R";
	private static int usesLeft; 
		
	public RemodelPInventoryItem() {
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
