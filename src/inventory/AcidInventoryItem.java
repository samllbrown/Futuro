package inventory;

/**
 * AcidInventoryItem.java
 * @author Sam R, Illia L.
 * @version 1
 * Last Mod Date: 27/11/2021
 * Description: handles the acid inventory item
 */
public class AcidInventoryItem extends InventoryItem {

    /** The Constant name. */
    public static final String name = "ACID";

    /**
     * Instantiates a new acid inventory item.
     */
    public AcidInventoryItem() {
	super(name);
    }

    /**
     * Instantiates a new acid inventory item.
     *
     * @param uses - the amount of uses left
     */
    public AcidInventoryItem(int uses) {
	super(name, uses);
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
	return AcidInventoryItem.name;
    }
}