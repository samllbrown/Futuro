package inventory;

public class PuddleInventoryItem extends InventoryItem{

	private static final String name = "PUDDLE";
	
    public PuddleInventoryItem() {
        super(name);
    }
    
    public static String getName() {
    	return name;
    }
}
