package inventory;

public class RemodelPInventoryItem extends InventoryItem{

	private static final String name = "REMODEL_P_TO_R";
		
	public RemodelPInventoryItem() {
		super(name);
	}
	    
	public static String getName() {
	    return name;
	}
}
