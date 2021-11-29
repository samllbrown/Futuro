package gameObject;

import javafx.scene.image.Image;

public class Acid extends Item {

	private static final int X_RANGE = 3;
	private static final int Y_RANGE = 3;
	
	private int DURATION = 5; //5 ticks or seconds
	
	private int DAMAGE_OVER_TIME_ADULT = 25; //25 Damage per tick
	
	private int DAMAGE_OVER_TIME_BABY = 10; //25 Damage per tick
	
	public Acid(int atX, int atY) {
		super(atX, atY);
		setXRange(X_RANGE);
		setYRange(Y_RANGE);
	}
	
	@Override
	public void act(Mech mech) {
		if(mech.getIsBaby() == true) {
			mech.setHealthFromDamage(DAMAGE_OVER_TIME_BABY);
		}
		else {
			mech.setHealthFromDamage(DAMAGE_OVER_TIME_ADULT);
		}
	}

	@Override
	public Image getImage() {
		Image img = new Image("file:res/Sprites/acid.png",50, 50, false, false);
		return img;
	}
}
