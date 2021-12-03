package gameObject;

import javafx.scene.image.Image;

public class Lightning extends Item {
	private static final int X_RANGE = 2;
	private static final int Y_RANGE = 2;
	public static final Image ITEM_IMAGE = new Image("file:res/Sprites/lightning.png",50, 50, false, false);
	public static final int USES = 1;
	private int DURATION = 3; //3 ticks or seconds
	
	public Lightning(int atX, int atY) {
		super(atX, atY, 0, USES);
		setXRange(X_RANGE);
		setYRange(Y_RANGE);
	}
	@Override
	public void act(Mech mech) {
		if(super.uses != 0) {
			mech.setSterile(true);
			super.uses--;
		}

	}
	@Override
	public Image getImage() {
		return ITEM_IMAGE;
	}

}
