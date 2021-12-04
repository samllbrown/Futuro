package inventory;

public class RemodelRInventoryItem extends InventoryItem{

    public static final String name = "REMODEL_R_TO_P";

    public RemodelRInventoryItem() {
        super(name);
    }

    public RemodelRInventoryItem(int uses) {
        super(name, uses);
    }

    public String getName() {
        return RemodelRInventoryItem.name;
    }
}