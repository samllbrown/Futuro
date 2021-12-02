package inventory;

public class DeathMechInventoryItem extends InventoryItem {

	private static final String name = "DEATH_MECH";
	
    public DeathMechInventoryItem() {
        super(name);
    }
    
    public static String getName() {
    	return name;
    }
}

