package gameObject;

public class Remodel extends Item {
	// could make these final ints in abstract item class instead (maybe?)
	// is 0 x and y range because it acts on the tile it is on
	private static final int X_RANGE = 0;
	private static final int Y_RANGE = 0;
	
	public Remodel(int x, int y) {
		super(x, y);
	}
	
	private void remodelMech(Mech mech) {
		// figure out how to deal with possible pregnant mech
		//mech.setType((mech.getType() == 'P' ? 'R' : 'P'));
	}

	@Override
	public void act(Mech someMech) {
		this.remodelMech(someMech);
	}

}
