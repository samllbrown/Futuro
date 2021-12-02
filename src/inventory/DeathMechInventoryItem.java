package inventory;

public class DeathMechInventoryItem extends InventoryItem {

	private static final String name = "DEATH_MECH";
	
    public DeathMechInventoryItem() {
        super("DEATH_MECH");
    }
    
    public static String getName() {
    	return name;
    }
}

