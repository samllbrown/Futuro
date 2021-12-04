package inventory;

public class MineInventoryItem extends InventoryItem{

    public static final String name = "MINE";

    public MineInventoryItem() {
        super(name);
    }

    public MineInventoryItem(int uses) {
        super(name, uses);
    }

    public String getName() {
        return MineInventoryItem.name;
    }
}