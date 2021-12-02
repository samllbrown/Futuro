package inventory;

public class AcidInventoryItem extends InventoryItem {

	private static final String name = "ACID";
	
    public AcidInventoryItem() {
        super(name);
    }
    
    public static String getName() {
    	return name;
    }
}
