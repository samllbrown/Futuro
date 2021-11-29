package gameObject;

import javafx.scene.image.Image;

public class Lightning extends Item {
	private static final int X_RANGE = 2;
	private static final int Y_RANGE = 2;
	
	private int DURATION = 3; //3 ticks or seconds
	
	public Lightning(int atX, int atY) {
		super(atX, atY);
		setXRange(X_RANGE);
		setYRange(Y_RANGE);
	}
	@Override
	public void act(Mech mech) {
		mech.setSterile(true);
		
	}
	@Override
	public Image getImage() {
		Image img = new Image("file:res/Sprites/lightning.png",50, 50, false, false);
		return img;
	}

}
