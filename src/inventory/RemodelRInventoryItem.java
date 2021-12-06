package inventory;

/**
 * RemodelRInventoryItem.java
 * @author Sam R, Illia L.
 * @version 1
 * Last Mod Date: 27/11/2021
 * Description: handles the remodel item
 */
public class RemodelRInventoryItem extends InventoryItem{

    /** The Constant name. */
    public static final String name = "REMODEL_R_TO_P";

    /**
     * Instantiates a new remodel R inventory item.
     */
    public RemodelRInventoryItem() {
        super(name);
    }

    /**
     * Instantiates a new remodel R inventory item.
     *
     * @param uses - the amount of uses left
     */
    public RemodelRInventoryItem(int uses) {
        super(name, uses);
    }

    /**
     * Gets the name.
     *
     * @return the name of the item
     */
    public String getName() {
        return RemodelRInventoryItem.name;
    }
  /**
   * Instantiates a new remodel R inventory item.
   */
  public RemodelRInventoryItem() {
    super(name);
  }
}
