package gameObject;

import javafx.scene.image.Image;

public class Puddle extends Item {
	private static final int X_RANGE = 0;
	private static final int Y_RANGE = 0;
	
	private int health;
	
	public Puddle(int x, int y, int health) {
		super(x, y);
		this.health = 100;
	}

	@Override
	public void act(Mech someMech) {
		// TODO Auto-generated method stub
		// changes the direction of a mech
	}

	@Override
	public Image getImage() {
		Image img = new Image("file:res/Sprites/puddle.png",50, 50, false, false);
		return img;
	}

}
