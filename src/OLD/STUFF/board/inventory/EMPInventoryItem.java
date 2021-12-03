package OLD.STUFF.board.inventory;

public class EMPInventoryItem extends InventoryItem{

	private static final String name = "EMP";
	private static int usesLeft; 
	
    public EMPInventoryItem() {
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
