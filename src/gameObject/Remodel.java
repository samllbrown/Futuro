package gameObject;

import javafx.scene.image.Image;

/**
 * Remodel.java
 * @author Sam B, Debbie L, ...
 * @version
 * Last Mod Date:
 * Description: Model Item, mimics the male/female sex change item from the functional spec. Inherits from Item Class.
 */
public class Remodel extends Item {

    private boolean isMaleRemodel;
    private static final int USES = 1;
    public static final Image ITEM_IMAGE_R_TO_P = new Image("file:res/Sprites/RtoP.png", 50, 50, false, false);
    public static final Image ITEM_IMAGE_P_TO_R = new Image("file:res/Sprites/PtoR.png", 50, 50, false, false);

    /**
     * Instantiates a Puddle Item.
     * Uses Item super constructor to set the location and what form of remodelling the item is.
     * @param x Starting x coordinate of Remodel Item.
     * @param y Starting y coordinate of Remodel Item.
     * @param isMaleRemodel checking if the remodel item is effecting a male or female mech
     */
    public Remodel(int x, int y, boolean isMaleRemodel) {
	super(x, y, 0, USES);
	this.isMaleRemodel = isMaleRemodel;
    }

    /**
     * Remodels a mech depending if they are a male or female and can be remodelled (e.g not a death mech).
     * @param mech mech to be remodelled
     */
    private void remodelMech(Mech mech) {
	if (mech.getType() != MechType.DEATH) {
	    if (mech.getType() == MechType.PRODUCTION && this.isMaleRemodel == false) {
		mech.setType(MechType.RESOURCE);
		mech.setImage(Mech.getImageForType(mech.getType()));
	    } else if (mech.getType() == MechType.RESOURCE && this.isMaleRemodel == true) {
		mech.setType(MechType.PRODUCTION);
		mech.setImage(Mech.getImageForType(mech.getType()));
	    }
	    this.isReadyForDestroy = true;
	}
    }

    /**
     * Act of remodelling a mech.
     * Overridden from method in Item class, puddle should disappear after 1 use.
     * @param mech mech the action is being performed on
     */
    @Override
    public void act(Mech mech) {
	if (super.uses != 0) {
	    this.remodelMech(mech);
	    super.uses--;
	}
    }

    /**
     * Retrieve the image of the Remodel item for the GUI
     * @return the Remodel sprite image
     */
    @Override
    public Image getImage() {
	return (isMaleRemodel ? ITEM_IMAGE_R_TO_P : ITEM_IMAGE_P_TO_R);
    }

    @Override
    public String toString() {
	return String.format("%s,%d,%d,%d", (isMaleRemodel ? "REMODEL_R_TO_P" : "REMODEL_P_TO_R"), this.getGridX(),
		this.getGridY(), this.uses);
    }
}
