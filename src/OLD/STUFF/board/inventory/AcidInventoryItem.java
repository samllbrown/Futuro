package OLD.STUFF.board.inventory;

public class AcidInventoryItem extends InventoryItem {

	private static final String name = "ACID";
	private static int usesLeft; 
	
    public AcidInventoryItem() {
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
