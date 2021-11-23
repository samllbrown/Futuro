package managers;

import gameObject.Item;
import javafx.scene.image.Image;

public abstract class InventoryItem {
    protected final int MAX_ITEM_USES = 4;
    protected final String itemName;
    protected final Image itemSprite;
    protected int remainingUses;

    public InventoryItem(String name) {
        this.itemName = name;
        // this needs to be changed
        this.itemSprite = null;
        this.remainingUses = MAX_ITEM_USES;
    }

    public int getRemainingUses() {
        return this.remainingUses;
    }

    public void reduceUses() {
        this.remainingUses--;
    }

    public void reduceUses(int uses) {
        this.remainingUses -= uses;
    }

    public String getName() {
        return this.itemName;
    }

}
