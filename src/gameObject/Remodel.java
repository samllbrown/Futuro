package gameObject;

import javafx.scene.image.Image;

public class Remodel extends Item {
	
	private boolean isMaleRemodel;
	
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
		Image img = null;
		if(isMaleRemodel) {
			img = new Image("file:res/Sprites/RtoP.png",50, 50, false, false);
		}
		else {
			img = new Image("file:res/Sprites/PtoR.png",50, 50, false, false);
		}	
		return img;
	}

}
