package OLD.STUFF.board.inventory;

public class PuddleInventoryItem extends InventoryItem{

	private static final String name = "PUDDLE";
	private static int usesLeft; 
	
    public PuddleInventoryItem() {
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
