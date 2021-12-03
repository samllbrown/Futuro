package NewStuff;

import javafx.scene.image.Image;

import java.io.File;

public class Globals {
    public static final double TILE_HEIGHT_PIXELS = 50.0;
    public static final double TILE_WIDTH_PIXELS = 50.0;

    public static final Image PATH_TILE_IMAGE = new Image("file:res/Sprites/grassMid.png", TILE_WIDTH_PIXELS, TILE_HEIGHT_PIXELS, false, false);
    public static final Image WALL_TILE_IMAGE = new Image("file:res/Sprites/tileW.png", TILE_WIDTH_PIXELS, TILE_HEIGHT_PIXELS, false, false);
    public static final Image TUNNEL_TILE_IMAGE = new Image("file:res/Sprites/tileT.png",TILE_WIDTH_PIXELS, TILE_HEIGHT_PIXELS, false, false);

    public static final Image LIGHTNING_IMAGE = new Image("file:res/Sprites/lightning.png", TILE_WIDTH_PIXELS, TILE_HEIGHT_PIXELS, false, false);
    public static final Image EMP_IMAGE = new Image("file:res/Sprites/emp.png", TILE_WIDTH_PIXELS, TILE_HEIGHT_PIXELS, false, false);
    public static final Image ACID_IMAGE = new Image("file:res/Sprites/acid.png", TILE_WIDTH_PIXELS, TILE_HEIGHT_PIXELS, false, false);
    public static final Image ACID_PUDDLE_IMAGE = new Image("file:res/Sprites/AcidPuddle.png", TILE_WIDTH_PIXELS, TILE_HEIGHT_PIXELS, false, false);
    public static final Image MINE_IMAGE = new Image("file:res/Sprites/mine.png", TILE_WIDTH_PIXELS, TILE_HEIGHT_PIXELS, false, false);
    public static final Image PUDDLE_IMAGE = new Image("file:res/Sprites/puddle.png", TILE_WIDTH_PIXELS, TILE_HEIGHT_PIXELS, false, false);
    public static final Image REMODEL_R_TO_P_IMAGE = new Image("file:res/Sprites/RtoP.png", TILE_WIDTH_PIXELS, TILE_HEIGHT_PIXELS, false, false);
    public static final Image REMODEL_P_TO_R_IMAGE = new Image("file:res/Sprites/PtoR.png", TILE_WIDTH_PIXELS, TILE_HEIGHT_PIXELS, false, false);

    public static final File PLAYERS_FILE = new File("file:Players.txt");

}
