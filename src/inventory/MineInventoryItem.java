package inventory;

public class MineInventoryItem extends InventoryItem{

	private static final String name = "MINE";
	
    public MineInventoryItem() {
        super(name);
    }
    
    public static String getName() {
    	return name;
    }
	
}
