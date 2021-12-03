package inventory;

public class LightningInventoryItem extends InventoryItem{

	private static final String name = "LIGHTNING";
	private static int usesLeft; 
		
	    public LightningInventoryItem() {
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
