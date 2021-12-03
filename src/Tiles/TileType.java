package Tiles;

public enum TileType {
    PATH(true, true), TUNNEL(false, true), WALL(false, false);

    public final boolean placeable;
    public final boolean walkable;

    TileType(boolean placeable, boolean walkable) {
        this.placeable = placeable;
        this.walkable = walkable;
    }
}
