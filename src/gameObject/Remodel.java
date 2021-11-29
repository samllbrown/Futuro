package gameObject;

import javafx.scene.image.Image;

public class Remodel extends Item {
	
	private boolean isMaleRemodel;
	
	public Remodel(int x, int y, boolean isMaleRemodel) {
		super(x, y);
		this.isMaleRemodel = isMaleRemodel;
	}
	
	private void remodelMech(Mech mech) {
		if(isMaleRemodel && mech.getType() == MechType.PRODUCTION) {
			mech.setType(MechType.RESOURCE);
		}
		if(isMaleRemodel == false && mech.getType() == MechType.RESOURCE) {
			mech.setType(MechType.PRODUCTION);
		}
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
