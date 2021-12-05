package inventory;

/**
 * LightningInventoryItem.java
 * @author Sam R
 * @version 1
 * Last Mod Date: 27/11/2021
 * Description: handles the lightning inventory item
 */
public class LightningInventoryItem extends InventoryItem{

    /** The Constant name. */
    public static final String name = "LIGHTNING";

        /**
         * Instantiates a new lightning inventory item.
         */
        public LightningInventoryItem() {
            super(name);
        }

        /**
         * Instantiates a new lightning inventory item.
         *
         * @param uses the uses
         */
        public LightningInventoryItem(int uses) {
            super(name, uses);
        }

        /**
         * Gets the name.
         *
         * @return the name
         */
        public String getName() {
            return LightningInventoryItem.name;
        }
}
