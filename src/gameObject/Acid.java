package gameObject;

import javafx.scene.image.Image;

public class Acid extends Item {

	private static final int X_RANGE = 1;
	private static final int Y_RANGE = 1;
	
	private int DURATION = 5; //5 ticks or seconds
	
	private int DAMAGE_OVER_TIME_ADULT = 25; //25 Damage per tick
	
	private int DAMAGE_OVER_TIME_BABY = 10; //25 Damage per tick
	
	public Acid(int atX, int atY) {
		super(atX, atY);
	}
	
	@Override
	public void act(Mech mech) {
		if(mech.getType() == MechType.BABY) {
			
		}
	}

	@Override
	public Image getImage() {
		Image img = new Image("file:res/Sprites/acid.png",50, 50, false, false);
		return img;
	}
}
