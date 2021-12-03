package gameObject;

/**
 * TileType.java
 * @author
 * @version
 * Last Mod Date:
 * Description: Enumerates through different Tile types based on their attributes (visible,placeable & walkable)
 */

public enum TileType {
    PATH(1, 1, 1), TUNNEL(0, 0, 1), WALL(1, 0, 0);


	public final int visible;
    public final int placeable;
    public final int walkable;

    /**
     * Constructor for TileType
     * @param visible visible attribute (can the tile be seen) of a tile type, val = 1 if "true" and 0 if "false".
     * @param placeable placeable attribute (can an item be placed on the tile) of a tile type, val = 1 if "true" and 0 if "false".
     * @param walkable walkable attribute (can a mech walk on the tile) of a tile type, val = 1 if "true" and 0 if "false".
     */
    TileType(int visible, int placeable, int walkable) {
        this.visible = visible;
        this.placeable = placeable;
        this.walkable = walkable;
    }
}
