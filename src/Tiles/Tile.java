package Tiles;

import NewTest.GameObject;
import javafx.scene.image.Image;

public abstract class Tile extends GameObject {
    protected TileType tileType;

    public Tile(int gridX, int gridY, TileType tileType, Image image) {
        super(gridX, gridY);
        this.tileType = tileType;
    }

    protected void setTileType(TileType tileType) {
        this.tileType = tileType;
    }

    public TileType getTileType() {
        return this.tileType;
    }

    public boolean isPlaceable() {
        return (this.tileType.equals(TileType.PATH));
    }

    public boolean isHidden() {
        return (this.tileType.equals(TileType.TUNNEL));
    }

    public boolean isWalkable() {
        return (!(this.tileType.equals(TileType.WALL)));
    }

}
