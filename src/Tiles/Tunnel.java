package Tiles;

import NewTest.Globals;
import javafx.scene.image.Image;

public class Tunnel extends Path {
    private static final TileType TILE_TYPE = TileType.TUNNEL;
    private static final Image TUNNEL_IMAGE = Globals.PATH_TILE_IMAGE;

    public Tunnel(int gridX, int gridY) {
        super(gridX, gridY);
        super.setImage(TUNNEL_IMAGE);
        super.setTileType(TILE_TYPE);
    }
}
