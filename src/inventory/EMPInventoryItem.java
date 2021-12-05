package inventory;

/**
 * EMPInventoryItem.java
 * @author Sam R, Illia L.
 * @version 1
 * Last Mod Date: 27/11/2021
 * Description: handles the EMP inventory item
 */
public class EMPInventoryItem extends InventoryItem{

    /** The Constant name. */
    public static final String name = "EMP";

    /**
     * Instantiates a new EMP inventory item.
     */
    public EMPInventoryItem() {
        super(name);
    }

    /**
     * Instantiates a new EMP inventory item.
     *
     * @param uses - the amount of uses left
     */
    public EMPInventoryItem(int uses) {
        super(name, uses);
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return EMPInventoryItem.name;
    }
}