package inventory;

public class RemodelRInventoryItem extends InventoryItem{

	private static final String name = "REMODEL_R_TO_P";
	
	public RemodelRInventoryItem() {
		super(name);
	}
	    
	public static String getName() {
	    return name;
	}
	
}
