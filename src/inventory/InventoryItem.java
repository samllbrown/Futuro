package inventory;

import gameObject.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Locale;

public abstract class InventoryItem extends ImageView {
    protected static final int MAX_ITEM_USES = 4;
    public final String itemName;
    protected final Image itemSprite;
    protected int remainingUses;

    public InventoryItem(String name) {
        this.itemName = name;
        // this needs to be changed
        this.itemSprite = GameObjectFactory.getImageForName(name);
        this.remainingUses = MAX_ITEM_USES;
        System.out.println(itemSprite);
    }

    public InventoryItem(String name, int remainingUses) {
        this.itemName = name;
        // this needs to be changed
        this.itemSprite = GameObjectFactory.getImageForName(name);
        // NEED TO CHECK IF GREATER THAN MAX, LESS THAN 0 FOR REMAINING USES
        this.remainingUses = remainingUses;
    }

    



    public int getRemainingUses() {
        return this.remainingUses;
    }

    public void reduceUses() {
        this.remainingUses--;
        syncUses();
    }

    public void reduceUses(int uses) {
        this.remainingUses -= uses;
        syncUses();
    }

    public Image getSprite() {
    	return itemSprite;
    }
    
    public abstract void syncUses();



}
