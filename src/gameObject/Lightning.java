package gameObject;

import javafx.scene.image.Image;

public class Lightning extends Item {
	private static final int X_RANGE = 1;
	private static final int Y_RANGE = 1;
	public Lightning(int atX, int atY) {
		super(atX, atY);
	}
	@Override
	public void act(Mech someMech) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Image getImage() {
		Image img = new Image("file:res/Sprites/lightning.png",50, 50, false, false);
		return img;
	}

}
