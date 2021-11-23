package gameObject;

import board.Grid;
import board.Path;
import board.Tile;
import board.Tunnel;
import board.Wall;

public class GameObjectFactory {
	
	public static Mech makeMech(char type, int x, int y, int xDir, int yDir) {
		return new Mech(type, x, y, xDir, yDir);
	}
	
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
			case ' ':
				throw new Exception("Generic tile passed to makeTile...check the formatting of the tiles in file?");
			default:
				throw new Exception(String.format("Tile type %c does not exist", type));
		}
	}
	
	// public static Item
	// possibly not the right way of going about things, as Item is an abstract class...
	public static Item makeItemFromName(String name, int atX, int atY, int xDir, int yDir) throws Exception {
		switch (name.toUpperCase()) {
			case "ACID":
				return new Acid(atX, atY);
			case "EMP":
				return new EMP(atX, atY);
			case "LIGHTNING":
				return new Lightning(atX, atY);
			case "PUDDLE":
				return new Puddle(atX, atY, 100);
			case "REMODEL":
				return new Remodel(atX, atY);
			case "DEATHMECH":
				return new DeathMech(atX, atY, xDir, yDir);
			case "MINE":
				return new Mine(atX, atY);
			default:
				throw new Exception(String.format("Invalid Item Type for Creation: %s", name));
		}
	}
	
	// try-catches instead of throwing??
	public static Grid makeGrid(String tiles, int height, int width) throws Exception {
		Grid grid = new Grid(height, width);
		grid.populateGrid(tiles);
		return grid;
	}
}
