package OLD.STUFF.board.inventory;

public class DeathMechInventoryItem extends InventoryItem {

	private static final String name = "DEATH_MECH";
	private static int usesLeft; 
	
    public DeathMechInventoryItem() {
        super(name);
    	usesLeft = MAX_ITEM_USES;
    }

    public static void used() {
    	usesLeft-- ;
    }
    public static String getName() {
    	return name;
    }
    public static int getUses() {
    	return usesLeft;
    }
    public void syncUses() {
    	usesLeft = remainingUses;
    }
}

