package inventory;

public class PuddleInventoryItem extends InventoryItem{

    public static final String name = "PUDDLE";

    public PuddleInventoryItem() {
        super(name);
    }

    public PuddleInventoryItem(int uses) {
        super(name, uses);
    }

    public String getName() {
        return PuddleInventoryItem.name;
    }
}