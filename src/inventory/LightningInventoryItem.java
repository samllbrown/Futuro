package inventory;

public class LightningInventoryItem extends InventoryItem{

	private static final String name = "LIGHTNING";
		
	    public LightningInventoryItem() {
	        super(name);
	    }
	    
	    public static String getName() {
	    	return name;
	    }
}
