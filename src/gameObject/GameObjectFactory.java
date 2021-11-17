package gameObject;

import board.Grid;
import board.Path;
import board.Tile;
import board.Tunnel;
import board.Wall;

public class GameObjectFactory {
	
	// might be better to use a custom Exception for this
	public static Tile makeTile(char type, int x, int y) throws Exception {
		type = Character.toUpperCase(type);
		switch (type) {
			case 'W':
				return new Wall(x, y);
			case 'P':
				return new Path(x,y);
			case 'T':
				return new Tunnel(x,y);
			case 'X':
				throw new Exception("Generic tile passed to makeTile...check the formatting of the tiles in file?");
			default:
				throw new Exception(String.format("Tile type %c does not exist", type));
		}
	}
	
	// try-catches instead of throwing??
	public static Grid makeGrid(String tiles, int height, int width) throws Exception {
		Grid grid = new Grid(height, width);
		grid.populateGrid(tiles);
		return grid;
	}
}
