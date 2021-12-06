package inventory;

/**
 * RemodelPInventoryItem.java
 * @author Sam R, Illia L.
 * Last Mod Date: 02/12/2021
 * Description: handles the remodel item
 */
public class RemodelPInventoryItem extends InventoryItem {

    /** The Constant name. */
    public static final String name = "REMODEL_P_TO_R";

    /**
     * Instantiates a new remodel P inventory item.
     */
    public RemodelPInventoryItem() {
	super(name);
    }

    /**
     * Instantiates a new remodel P inventory item.
     *
     * @param uses - the amount of uses left
     */
    public RemodelPInventoryItem(int uses) {
	super(name, uses);
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
	return RemodelPInventoryItem.name;
    }
}