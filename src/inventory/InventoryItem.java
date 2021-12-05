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
        this.itemSprite = getImageForName(name);
        this.remainingUses = MAX_ITEM_USES;
        System.out.println(itemSprite);
    }

    public void setUses(int uses) {
        this.remainingUses = remainingUses;
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
    public static Item getItemForName(String name, int xCord, int yCord) {
        switch(name.toUpperCase(Locale.ROOT)) {
            case "LIGHTNING":
                return new Lightning(xCord, yCord);
            case "ACID":
                return new Acid(xCord, yCord);
            case "EMP":
                return new EMP(xCord, yCord);
            case "MINE":
                return new Mine(xCord, yCord);
            case "PUDDLE":
                return new Puddle(xCord, yCord);
            case "REMODEL_P_TO_R":
                return new Remodel(xCord, yCord, false);
            case "REMODEL_R_TO_P":
                return new Remodel(xCord, yCord, true);
            case "DEATH_MECH":
            	return new DeathMech(xCord, yCord).getDeathItem();
            default:
                System.err.println("Probably should be throwing an error here 2");
                return null;
        }
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

    //getName abstract static meaning that all items should


}
