package gameObject;

public class Acid extends Item {
	// don't know what the actual range of these will be
	// 
	private static final int X_RANGE = 1;
	private static final int Y_RANGE = 1;
	
	public Acid(int atX, int atY) {
		super(atX, atY);
	}
	
	@Override
	public void act(Mech someMech) {
		// TODO Auto-generated method stub
		// spreads itself over the grid?
		// damages mech in its range?
	}

}
