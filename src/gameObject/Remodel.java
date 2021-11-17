package gameObject;

public class Remodel extends Item {

	public Remodel(int x, int y, int xRange, int yRange) {
		super(x, y, xRange, yRange);
	}
	
	private void remodelMech(Mech mech) {
		// figure out how to deal with possible pregnant mech
		mech.setType((mech.getType() == 'P' ? 'R' : 'P'));
	}

	@Override
	public void act(Mech someMech) {
		this.remodelMech(someMech);
	}

}
