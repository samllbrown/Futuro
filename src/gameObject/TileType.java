package gameObject;

public enum TileType {
    PATH(1, 1, 1), TUNNEL(0, 0, 1), WALL(1, 0, 0);

    final int visible;
    final int placeable;
    final int walkable;

    TileType(int visible, int placeable, int walkable) {
        this.visible = visible;
        this.placeable = placeable;
        this.walkable = walkable;
    }
}
