package gameObject;

import board.Grid;
import javafx.scene.image.Image;

public class EMP extends Item {
	private static final int X_RANGE = 1;
	private static final int Y_RANGE = 1;
	private int timer;

	public EMP(int x, int y) {
		super(x, y);
		this.timer = 5;
	}
	
	@Override
	public void act(Mech someMech) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}

//	private void spread(Grid grid) {
//		int xCells[];
//		item yCells[];
//	}

}
