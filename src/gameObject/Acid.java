package gameObject;

import javafx.scene.image.Image;

public class Acid extends Item {

	private int xRange = 3;
	private int yRange = 3;
	public static final Image ITEM_IMAGE = new Image("file:res/Sprites/acid.png",50, 50, false, false);
	private int duration = 5; //5 ticks or seconds
	
	private static int DAMAGE_OVER_TIME_ADULT = 25; //25 Damage per tick
	private static int DAMAGE_OVER_TIME_BABY = 10; //25 Damage per tick
	
	public Acid(int atX, int atY) {
		super(atX, atY, DAMAGE_OVER_TIME_ADULT);
		setXRange(xRange);
		setYRange(yRange);
	}
	
	@Override
	public void act(Mech mech) {
        while (duration != 0) {
            mech.takeDamage(mech.getIsBaby() ? DAMAGE_OVER_TIME_BABY : DAMAGE_OVER_TIME_ADULT);
            System.err.println("A MECH HAS TAKEN DAMAGE");
            xRange++;
            yRange++;
            duration--;
        }
        if (duration == 0) {
            xRange--;
            yRange--;
            if ((xRange & yRange) == 0) {
                //remove the item from the board ???
            }
        }
	}

	@Override
	public Image getImage() { return ITEM_IMAGE_PUDDLE;}


	public Image getPlacedImage() { return ITEM_IMAGE;}
}
