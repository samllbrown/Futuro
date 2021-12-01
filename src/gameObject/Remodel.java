package gameObject;

import javafx.scene.image.Image;

public class Remodel extends Item {
	
	private boolean isMaleRemodel;

	public static final Image ITEM_IMAGE_R_TO_P = new Image("file:res/Sprites/RtoP.png",50, 50, false, false);
	public static final Image ITEM_IMAGE_P_TO_R = new Image("file:res/Sprites/PtoR.png",50, 50, false, false);

	public Remodel(int x, int y, boolean isMaleRemodel) {
		super(x, y);
		this.isMaleRemodel = isMaleRemodel;
	}
	
	private void remodelMech(Mech mech) {
		mech.setType((this.isMaleRemodel ? MechType.PRODUCTION : MechType.RESOURCE));
	}

	@Override
	public void act(Mech someMech) {
		this.remodelMech(someMech);
	}

	@Override
	public Image getImage() {
		return (isMaleRemodel ? ITEM_IMAGE_R_TO_P : ITEM_IMAGE_P_TO_R);
	}

}
