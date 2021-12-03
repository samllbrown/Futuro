package OLD.STUFF.board.gameObject;

import OLD.STUFF.board.Tile;
import OLD.STUFF.board.inventory.InventoryItem;

public class GameObjectFactory {
	public static Mech readMech(String id) {
		// format of id:
		// M,X,Y,HEALTH,TYPE,PREG,SECSTILADULT
		String[] mechComponents = id.split(",");
		char chartype = mechComponents[4].charAt(0);
		MechType mechType = null;
		switch (Character.toUpperCase(chartype)) {
			case 'R':
				mechType = MechType.RESOURCE;
				break;
			case 'P':
				mechType = MechType.PRODUCTION;
				break;
			default:
				System.err.println("error");
				break;
			// death mech needs to be done
		}

		Mech newMech = new Mech(mechType, Integer.valueOf(mechComponents[1]), Integer.valueOf(mechComponents[2]), Integer.valueOf(mechComponents[3]),
				(Integer.valueOf(mechComponents[5]) == 0), false, false);

		return newMech;
	}

	public static Tile makeTile(char type, int x, int y) throws Exception {
		TileType tileType;
		switch (Character.toUpperCase(type)) {
			case 'W':
				tileType = TileType.WALL;
				break;
			case 'P':
				tileType = TileType.PATH;
				break;
			case 'T':
				tileType = TileType.TUNNEL;
				break;
			case ' ':
				throw new Exception("Generic tile passed to makeTile...check the formatting of the tiles in file?");
			default:
				throw new Exception(String.format("Tile type %c does not exist", type));
		}
		return new Tile(tileType, x, y);
	}


	public static Item readItem(String id) {
		return null;
	}
	public static InventoryItem readInventoryItem(String id) {
		return null;
	}
	// needs to be done
//	public static Mech readMech(String id) {
//		return null;
//	}
//
//	// needs to be done
//	public static Item readItem(String id) {
//		return null;
//	}
//
//	// needs to be done
//	public static InventoryItem readInventoryItem(String id) {
//		return null;
//	}
//
//	public static Mech makeMech(char type, int x, int y, int xDir, int yDir) {
//		return new Mech(type, x, y, xDir, yDir);
//	}
//
//	// might be better to use a custom Exception for this
//	public static Tile makeTile(char type, int x, int y) throws Exception {
//		type = Character.toUpperCase(type);
//		switch (type) {
//			case 'W':
//				return new Wall(x, y);
//			case 'P':
//				return new Path(x,y);
//			case 'T':
//				return new Tunnel(x,y);
//			case ' ':
//				throw new Exception("Generic tile passed to makeTile...check the formatting of the tiles in file?");
//			default:
//				throw new Exception(String.format("Tile type %c does not exist", type));
//		}
//	}
//
//	// public static Item
//	// possibly not the right way of going about things, as Item is an abstract class...
//	public static Item makeItemFromName(String name, int atX, int atY) throws Exception {
//		switch (name.toUpperCase()) {
//			case "ACID":
//				return new Acid(atX, atY);
//			case "EMP":
//				return new EMP(atX, atY);
//			case "LIGHTNING":
//				return new Lightning(atX, atY);
//			case "PUDDLE":
//				return new Puddle(atX, atY, 100);
//			case "REMODEL":
//				return new Remodel(atX, atY);
//			case "DEATHMECH":
//				// xdir and ydir should be random or something
//				int xDir = 1;
//				int yDir = 0;
//				return new DeathMech(atX, atY, xDir, yDir);
//			case "MINE":
//				return new Mine(atX, atY);
//			default:
//				throw new Exception(String.format("Invalid Item Type for Creation: %s", name));
//		}
//	}
//
//	// try-catches instead of throwing??
//	public static Grid makeGrid(String tiles, int height, int width) throws Exception {
//		Grid grid = new Grid(height, width);
//		grid.populateGrid(tiles);
//		return grid;
//	}
}
