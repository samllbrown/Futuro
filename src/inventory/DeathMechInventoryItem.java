package inventory;

public class DeathMechInventoryItem extends InventoryItem {

    public static final String name = "DEATH_MECH";

    public DeathMechInventoryItem() {
        super(name);
    }

    public DeathMechInventoryItem(int uses) {
        super(name, uses);
    }

    public String getName() {
        return DeathMechInventoryItem.name;
    }
}
