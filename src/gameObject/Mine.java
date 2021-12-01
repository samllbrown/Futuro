package gameObject;

import javafx.scene.image.Image;

public class Mine extends Item {
	private static final int X_RANGE = 0;
	private static final int Y_RANGE = 0;
	
	private static final int DAMAGE = 1000;
	public static final Image ITEM_IMAGE = new Image("file:res/Sprites/mine.png",50, 50, false, false);
	public Mine(int x, int y) {
		super(x, y, DAMAGE);
		setXRange(X_RANGE);
		setYRange(Y_RANGE);
	}
	
//	@Override
//	public void act(Mech mech) {
//		mech.takeDamage(DAMAGE);
//	}

	@Override
	public Image getImage() {
		return ITEM_IMAGE;
	}
	

}
