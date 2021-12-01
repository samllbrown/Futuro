package inventory;

import gameObject.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Locale;

public abstract class InventoryItem extends ImageView {
    protected final int MAX_ITEM_USES = 4;
    protected final String itemName;
    protected final Image itemSprite;
    protected int remainingUses;

    public InventoryItem(String name) {
        this.itemName = name;
        // this needs to be changed
        this.itemSprite = getImageForName(name);
        this.remainingUses = MAX_ITEM_USES;
    }

    public InventoryItem(String name, int remainingUses) {
        this.itemName = name;
        // this needs to be changed
        this.itemSprite = null;
        // NEED TO CHECK IF GREATER THAN MAX, LESS THAN 0 FOR REMAINING USES
        this.remainingUses = remainingUses;
    }

    public static Image getImageForName(String name) {
        switch(name.toUpperCase(Locale.ROOT)) {
            case "LIGHTNING":
                return Lightning.ITEM_IMAGE;
            case "ACID":
                return Acid.ITEM_IMAGE;
            case "EMP":
                return EMP.ITEM_IMAGE;
            case "MINE":
                return Mine.ITEM_IMAGE;
            case "PUDDLE":
                return Puddle.ITEM_IMAGE;
            case "REMODEL_P_TO_R":
                // THIS ABSOLUTELY, 100% DEFINITELY NEEDS TO BE CHANGED
                return Remodel.ITEM_IMAGE_P_TO_R;
            case "REMODEL_R_TO_P":
                return Remodel.ITEM_IMAGE_R_TO_P;
            case "DEATH_MECH":
            	return DeathMech.ITEM_IMAGE;
            default:
                System.err.println("Probably should be throwing an error here");
                return null;
        }
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
