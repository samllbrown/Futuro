package inventory;

/**
 * DeathMechInventoryItem.java
 * @author David Terence-Abanulo
 * @version 1
 * Last Mod Date: 05/12/2021
 */
public class DeathMechInventoryItem extends InventoryItem {

    public static final String name = "DEATH_MECH";

    /**
     * Instantiates a new DeathMechInventory Item
     */
    public DeathMechInventoryItem() {
        super(name);
    }

    /**
     *Instantiates a new DeathMechInventory Item with the number of uses the item has
     * @param uses number of uses
     */
    public DeathMechInventoryItem(int uses) {
        super(name, uses);
    }

    /**
     * Retrieve the dmInventoryItem name.
     * @return the dmInventoryItem name
     */
    public String getName() {
        return DeathMechInventoryItem.name;
    }
}
