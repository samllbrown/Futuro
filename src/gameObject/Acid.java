package gameObject;

import javafx.scene.image.Image;

public class Acid extends Item {

	private static final int X_RANGE = 3;
	private static final int Y_RANGE = 3;
	public static final Image ITEM_IMAGE = new Image("file:res/Sprites/acid.png",50, 50, false, false);
	public static final Image ITEM_IMAGE_PUDDLE = new Image("file:res/Sprites/acidPuddle.png",50, 50, false, false);
	
	private int DURATION = 5; //5 ticks or seconds
	
	private static int DAMAGE_OVER_TIME_ADULT = 25; //25 Damage per tick
	private static int DAMAGE_OVER_TIME_BABY = 10; //25 Damage per tick
	
	public Acid(int atX, int atY) {
		super(atX, atY, DAMAGE_OVER_TIME_ADULT);
		setXRange(X_RANGE);
		setYRange(Y_RANGE);
	}
	
	@Override
	public void act(Mech mech) {
		mech.takeDamage(mech.getIsBaby() ? DAMAGE_OVER_TIME_BABY : DAMAGE_OVER_TIME_ADULT);
		System.err.println("A MECH HAS TAKEN DAMAGE");
	}

	@Override
	public Image getImage() { return ITEM_IMAGE_PUDDLE;}


	public Image getPlacedImage() { return ITEM_IMAGE;}
}
