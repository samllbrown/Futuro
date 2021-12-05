package gameObject;

import java.util.Locale;

import board.Grid;
import board.Path;
import board.Tile;
import board.Tunnel;
import board.Wall;
import inventory.*;

/**
 * GameObjectFactory.java
 * @author
 * @version
 * Last Mod Date:
 */
public class GameObjectFactory {

	/**
	 * Reads a Mech given their unique ID (from the Level file).
	 * @param id format of mech id: M,X,Y,HEALTH,TYPE,PREG,SECSTILADULT
	 * @return a Mech object created from id
	 */
	public static Mech readMech(String id) {
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
				(Integer.valueOf(mechComponents[5]) == 1), false, false);

		return newMech;
	}

	/**
	 * Creates a Tile for the game given type, and position on board.
	 * @param type tiletype
	 * @param x x position
	 * @param y y position
	 * @return the created Tile
	 * @throws Exception given Tile type doesn't exist or is incorrect
	 */
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
		String[] idComp = id.split(",");
		int atX = Integer.valueOf(idComp[1]);
		int atY = Integer.valueOf(idComp[2]);
		int usesLeft = Integer.valueOf(idComp[3]);
		Item item = null;
		switch(idComp[0]) {
			case "ACID":
				item = new Acid(atX, atY);
				item.setUses(usesLeft);
				break;
			case "EMP":
				item = new EMP(atX, atY);
				item.setUses(usesLeft);
				break;
			case "LIGHTNING":
				item = new Lightning(atX, atY);
				item.setUses(usesLeft);
				break;
			case "DEATHMECHITEM":
				System.err.println("Not adding a death mech item because deathmech mech already adds deathmechitem to the level");
				break;
			case "MINE":
				item = new Mine(atX, atY);
				item.setUses(usesLeft);
				break;
			case "PUDDLE":
				item = new Puddle(atX, atY);
				item.setUses(usesLeft);
				break;
		}
		return item;
	}
  
  /**
	 * Reads and creates items for a player's inventory.
	 * @param id format of item id: itemType, itemUses
	 * @return the created Item
	 */
	public static InventoryItem readInventoryItem(String id) {

        String[] itemInfo = id.split(",");
        String itemType = itemInfo[0];
        int usesLeft = Integer.parseInt(itemInfo[1]);

        switch(itemType.toUpperCase(Locale.ROOT)) {
        case "LTG":
            return new LightningInventoryItem(usesLeft);
        case "ACD":
            return new AcidInventoryItem(usesLeft);
        case "EMP":
            return new EMPInventoryItem(usesLeft);
        case "MIN":
            return new MineInventoryItem(usesLeft);
        case "PDL":
            return new PuddleInventoryItem(usesLeft);
        case "RTR":
            return new RemodelPInventoryItem(usesLeft);
        case "RTP":
            return new RemodelRInventoryItem(usesLeft);
        case "DMC":
            return new DeathMechInventoryItem(usesLeft);
        default:
            System.out.println(itemType);
            System.out.println(usesLeft);
            System.err.println("Probably should be throwing an error here");
            return null;
        }
	}
}
