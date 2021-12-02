package inventory;

public class EMPInventoryItem extends InventoryItem{

	private static final String name = "EMP";
	
    public EMPInventoryItem() {
        super(name);
    }
    
    public static String getName() {
    	return name;
    }
}
