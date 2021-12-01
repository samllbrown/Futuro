package gameObject;

import javafx.scene.image.Image;

public class DeathMech {
	private static final int DAMAGE = 100;
	private int killsLeft = 5;
	public static final Image ITEM_IMAGE = new Image("file:res/Sprites/mechD.png",50, 50, false, false);


	public DeathMech(int x, int y, int xDir, int yDir) {
		Item deathMechItem = new Item(x,y,DAMAGE) {
			@Override
			public Image getImage() {
				return ITEM_IMAGE;
			}
		};



	}

	public void act(Mech mech) {
		while (killsLeft!= 0) {
			mech.takeDamage(DAMAGE);
			killsLeft--;
			System.err.println("DEATH MECH HAS KILLED A MECH");
		}
	}

	public Image getImage() {
		return ITEM_IMAGE;
	}
}
