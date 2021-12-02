package gameObject;

import javafx.scene.image.Image;

public class Puddle extends Item {
	private static final int X_RANGE = 0;
	private static final int Y_RANGE = 0;
	private static final int USES = 3;

	private int health;
	public static final Image ITEM_IMAGE = new Image("file:res/Sprites/puddle.png",50, 50, false, false);
	public Puddle(int x, int y) {
		super(x, y, 0, USES);
		this.health = 15;
		setXRange(X_RANGE);
		setYRange(Y_RANGE);
	}

	@Override
	public void act(Mech mech) {
		try {
			if(super.uses != 0) {
				mech.turn("AROUND");
				this.health -= 5;
				super.uses -= 1;
				if(this.health == 0) {
					System.err.println("THIS SHOULD NO LONGER BE HERE");
				}
			}
		} catch(Exception e) {
			System.err.println("PUDDLE HAS THROWN AN ERROR WHILST TRYING TO TURN THE MECH AROUND");
		}
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
