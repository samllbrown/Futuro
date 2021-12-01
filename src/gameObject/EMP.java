package gameObject;

import board.Grid;
import javafx.scene.image.Image;

public class EMP extends Item {
	private static final int X_RANGE = 4;
	private static final int Y_RANGE = 4;

	public static final Image ITEM_IMAGE = new Image("file:res/Sprites/emp.png",50, 50, false, false);

	private static final int DAMAGE = 100;

	public EMP(int x, int y) {
		super(x, y, DAMAGE);
		setXRange(X_RANGE);
		setYRange(Y_RANGE);
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
