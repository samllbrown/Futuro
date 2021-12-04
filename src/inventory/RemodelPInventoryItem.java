package inventory;

public class RemodelPInventoryItem extends InventoryItem{

	public static final String name = "REMODEL_P_TO_R";
		
	public RemodelPInventoryItem() {
		super(name);
	}
	
	public RemodelPInventoryItem(int uses) {
    	super(name, uses);
    }
    
    public String getName() {
    	return RemodelPInventoryItem.name;
    }
}
