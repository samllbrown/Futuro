package inventory;

/**
 * EMPInventoryItem.java
 * @author
 * @version 1
 * Last Mod Date: 05/12/2021
 */
public class EMPInventoryItem extends InventoryItem{

    public static final String name = "EMP";

    /**
     * Instantiates a new EMPInventory Item
     */
    public EMPInventoryItem() {
        super(name);
    }

    /**
     *Instantiates a new EMPInventory Item with the number of uses the item has
     * @param uses number of uses
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