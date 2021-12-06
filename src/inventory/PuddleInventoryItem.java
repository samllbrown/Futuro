package inventory;

/**
 * PuddleInventoryItem.java
 * @author Sam R, Illia L.
 * @version 1
 * Last Mod Date: 27/11/2021
 * Description: handles the puddle inventory item 
 */
public class PuddleInventoryItem extends InventoryItem {

    /** The Constant name. */
    public static final String name = "PUDDLE";

    /**
     * Instantiates a new puddle inventory item.
     */
    public PuddleInventoryItem() {
	super(name);
    }

    /**
     * Instantiates a new puddle inventory item.
     *
     * @param uses - the amount of uses left
     */
    public PuddleInventoryItem(int uses) {
	super(name, uses);
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
	return PuddleInventoryItem.name;
    }
}