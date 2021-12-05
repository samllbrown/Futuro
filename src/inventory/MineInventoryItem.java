package inventory;

/**
 * MineInventoryItem.java
 * @author Sam R
 * @version 1
 * Last Mod Date: 27/11/2021
 * Description: handles the item mine inventory item
 */
public class MineInventoryItem extends InventoryItem{

    /** The Constant name. */
    public static final String name = "MINE";

    /**
     * Instantiates a new mine inventory item.
     */
    public MineInventoryItem() {
        super(name);
    }

    /**
     * Instantiates a new mine inventory item.
     *
     * @param uses the uses
     */
    public MineInventoryItem(int uses) {
        super(name, uses);
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return MineInventoryItem.name;
    }
}