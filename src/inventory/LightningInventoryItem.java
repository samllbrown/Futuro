package inventory;

public class LightningInventoryItem extends InventoryItem{

    public static final String name = "LIGHTNING";

        public LightningInventoryItem() {
            super(name);
        }

        public LightningInventoryItem(int uses) {
            super(name, uses);
        }

        public String getName() {
            return LightningInventoryItem.name;
        }
}
