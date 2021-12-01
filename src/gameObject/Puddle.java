package gameObject;

import javafx.scene.image.Image;

public class Puddle extends Item {
	private static final int X_RANGE = 0;
	private static final int Y_RANGE = 0;
	
	private int health;
	public static final Image ITEM_IMAGE = new Image("file:res/Sprites/tileW.png",50, 50, false, false);
	public Puddle(int x, int y) {
		super(x, y);
		setXRange(X_RANGE);
		setYRange(Y_RANGE);
	}

	@Override
	public void act(Mech mech) {

	}

	@Override
	public Image getImage() {
		return ITEM_IMAGE;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

}
