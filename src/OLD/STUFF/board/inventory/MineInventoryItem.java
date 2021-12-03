package OLD.STUFF.board.inventory;

public class MineInventoryItem extends InventoryItem{

	private static final String name = "MINE";
	private static int usesLeft; 
	
    public MineInventoryItem() {
        super(name);
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
