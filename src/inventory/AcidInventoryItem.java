package inventory;

public class AcidInventoryItem extends InventoryItem {

    public static final String name = "ACID";

    public AcidInventoryItem() {
        super(name);
    }

    public AcidInventoryItem(int uses) {
        super(name, uses);
    }

    public String getName() {
        return AcidInventoryItem.name;
    }
}