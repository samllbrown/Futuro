package gameObject;

public class DeathMech extends Mech {
	private static final int DAMAGE = 100;
	
	public DeathMech(int x, int y, int xDir, int yDir) {
		super('D', x, y, xDir, yDir);
	}
	
	public void move() {
		
	}
	
	@Override
	public void act(Mech someMech) {
		// reduces the health of the mech
	} 

}
