package inventory;

public class EMPInventoryItem extends InventoryItem{

    public static final String name = "EMP";

    public EMPInventoryItem() {
        super(name);
    }

    public EMPInventoryItem(int uses) {
        super(name, uses);
    }

    public String getName() {
        return EMPInventoryItem.name;
    }
}