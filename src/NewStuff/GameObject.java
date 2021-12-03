package NewStuff;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public abstract class GameObject extends Rectangle {
    private static final double PIXEL_WIDTH = Globals.TILE_WIDTH_PIXELS;
    private static final double PIXEL_HEIGHT = Globals.TILE_HEIGHT_PIXELS;

    private int gridX;
    private int gridY;

    private double pixelX;
    private double pixelY;

    private Image img;

    public GameObject(int gridX, int gridY) {
        super(PIXEL_WIDTH, PIXEL_HEIGHT);
        this.gridX = gridX;
        this.gridY = gridY;
    }

    public Image getImage() {
        return this.img;
    }

    public void setImage(Image image) {
        this.img = image;
    }
}
