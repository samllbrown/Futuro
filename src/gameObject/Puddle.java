package gameObject;

import javafx.scene.image.Image;

public class Puddle extends Item {
	private static final int X_RANGE = 0;
	private static final int Y_RANGE = 0;
	
	private int health;
	
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
		Image img = new Image("file:res/Sprites/tileP.png",50, 50, false, false);
		return img;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

}
