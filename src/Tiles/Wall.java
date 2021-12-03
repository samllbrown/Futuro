package Tiles;

import NewStuff.Globals;
import javafx.scene.image.Image;

public class Wall extends Tile {
    private static final TileType TILE_TYPE = TileType.WALL;
    private static final Image TUNNEL_IMAGE = Globals.WALL_TILE_IMAGE;

    public Wall(int gridX, int gridY) {
        super(gridX, gridY, TILE_TYPE, TUNNEL_IMAGE);
    }

}
