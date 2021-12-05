package inventory;

import gameObject.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Locale;

/**
 * InventoryItem.java
 * @author Sam R, Illia L.
 * @version 1
 * Last Mod Date: 27/11/2021
 * Description: the blueprint for a new item. 
 */

public abstract class InventoryItem extends ImageView {
    
    /** The Constant MAX_ITEM_USES. */
    protected static final int MAX_ITEM_USES = 4;
    
    /** The item name. */
    public final String itemName;
    
    /** The item sprite. */
    protected final Image itemSprite;
    
    /** The remaining uses. */
    protected int remainingUses;
    
    /** The label. */
    protected Label label;

    /**
     * Instantiates a new inventory item.
     *
     * @param name the name
     */
    public InventoryItem(String name) {
        this.itemName = name;
        // this needs to be changed
        this.itemSprite = getImageForName(name);
        this.remainingUses = MAX_ITEM_USES;
        this.label =  new Label(Integer.toString(remainingUses));
        System.out.println(itemSprite);
    }

    public void setUses(int uses) {
        this.remainingUses = remainingUses;
    }

    /**
     * Instantiates a new inventory item.
     *
     * @param name the name
     * @param remainingUses the remaining uses
     */
    public InventoryItem(String name, int remainingUses) {
        this.itemName = name;
        // this needs to be changed
        this.itemSprite = getImageForName(name);
        // NEED TO CHECK IF GREATER THAN MAX, LESS THAN 0 FOR REMAINING USES
        this.label =  new Label(Integer.toString(remainingUses));
        this.remainingUses = remainingUses;
    }

    /**
     * Gets the image for name.
     *
     * @param name the name of item
     * @return the image for the name of the item
     */
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
    
    /**
     * Gets the item for name.
     *
     * @param name the name
     * @param xCord the x cord
     * @param yCord the y cord
     * @return the item for name
     */
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

    /**
     * Gets the remaining uses.
     *
     * @return the remaining uses
     */
    public int getRemainingUses() {
        return this.remainingUses;
    }

    /**
     * Reduce uses.
     */
    public void reduceUses() {
        this.remainingUses--;
    }

    /**
     * Reduce uses.
     *
     * @param uses the uses
     */
    public void reduceUses(int uses) {
        this.remainingUses -= uses;
    }
    
    public void regenUse() {
    	if(remainingUses < MAX_ITEM_USES) {
    		this.remainingUses++;
    	}
    }

    /**
     * Gets the sprite.
     *
     * @return the sprite
     */
    public Image getSprite() {
    	return itemSprite;
    }
    
    /**
     * Gets the label.
     *
     * @return the label
     */
    public Label getLabel() {
    	return this.label;
    }
}
