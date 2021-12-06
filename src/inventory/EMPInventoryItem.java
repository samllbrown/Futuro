package inventory;

/**
 * EMPInventoryItem.java
 * @author Sam R, Illia L.
 * Last Mod Date: 02/12/2021
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
     * Retrieve the EMPInventoryItem name.
     * @return the EMPInventoryItem name
     */
    public String getName() {
        return EMPInventoryItem.name;
    }
}