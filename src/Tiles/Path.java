package Tiles;

import Items.Item;
import Mechs.Mech;
import NewTest.Globals;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Path extends Tile {
    private static final TileType TILE_TYPE = TileType.PATH;
    private static final Image PATH_IMAGE = Globals.PATH_TILE_IMAGE;

    private ArrayList<Mech> currentMechs;
    private Item currentItem;

    public Path(int gridX, int gridY) {
        super(gridX, gridY, TILE_TYPE, PATH_IMAGE);
        this.currentMechs = new ArrayList<>();
        this.currentItem = null;
    }

    public void removeCurrentItem() {
        this.currentItem = null;
    }

    // check if the current item is in use?
    public void setCurrentItem(Item item) {
        this.currentItem = item;
    }

    public Item getCurrentItem() {
        return this.currentItem;
    }

    public ArrayList<Mech> getCurrentMechs() {
        return this.currentMechs;
    }

    // we might need a limit on how many mechs can be on a tile at any given time
    public void addMech(Mech mech) {
        this.currentMechs.add(mech);
    }

    public void removeMech(Mech mech) throws Exception {
        if(this.currentMechs.contains(mech)) {
            this.currentMechs.remove(mech);
        } else {
            throw new Exception("Specified mech does not exist on this tile");
        }
    }

}