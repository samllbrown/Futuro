package gameObject;

import board.Grid;
import javafx.scene.image.Image;

public class EMP extends Item {
	private static final int X_RANGE = 4;
	private static final int Y_RANGE = 4;
	public static final Image ITEM_IMAGE = new Image("file:res/Sprites/emp.png",50, 50, false, false);
	public EMP(int x, int y) {
		super(x, y);
		setXRange(X_RANGE);
		setYRange(Y_RANGE);
	}
	
	@Override
	public void act(Mech someMech) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Image getImage() {
		return ITEM_IMAGE;
	}

//	private void spread(Grid grid) {
//		int xCells[];
//		item yCells[];
//	}

}
