package gameObject;

import javafx.scene.image.Image;

public class Acid extends Item {

	
	public Acid(int atX, int atY) {
		super(atX, atY);
	}
	
	@Override
	public void act(Mech someMech) {
		// TODO Auto-generated method stub
		// spreads itself over the grid?
		// damages mech in its range?
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}

}
