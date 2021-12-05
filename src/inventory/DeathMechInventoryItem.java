package inventory;

/**
 * DeathMechInventoryItem.java
 * @author Sam R, Illia L.
 * @version 1
 * Last Mod Date: 27/11/2021
 * Description: handles the death mech inventory item
 */
public class DeathMechInventoryItem extends InventoryItem {

    /** The Constant name. */
    public static final String name = "DEATH_MECH";

    /**
     * Instantiates a new death mech inventory item.
     */
    public DeathMechInventoryItem() {
        super(name);
    }

    /**
     * Instantiates a new death mech inventory item.
     *
     * @param uses - the amount of uses left
     */
    public DeathMechInventoryItem(int uses) {
        super(name, uses);
    }

    /**
     * Gets the name of the death mech item.
     *
     * @return the name
     */
    public String getName() {
        return DeathMechInventoryItem.name;
    }
}
