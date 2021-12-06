package gameObject;

/**
 * TileType.java
 * @author Sam R
 * Last Mod Date: 02/12/2021
 */

public enum TileType {

    /** The path. */
    PATH(1, 1, 1),
    /** The tunnel. */
    TUNNEL(0, 0, 1),
    /** The wall. */
    WALL(1, 0, 0);

    /** The visible. */
    public final int visible;

    /** The placeable. */
    public final int placeable;

    /** The walkable. */
    public final int walkable;

    /**
     * Constructor for TileType.
     *
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
