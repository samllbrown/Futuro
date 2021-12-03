package Items;

import Mechs.Mech;
import NewTest.GameObject;

public abstract class Item extends GameObject {

    public Item(int gridX, int gridY) {
        super(gridX, gridY);
    }

    public abstract void actOn(Mech mech);
}
