package managers;

import gameObject.Mech;

public class Breeder {
    private static final int SECONDS_TO_BREED = 5;
    private Mech mech;
    private Mech otherMech;

    public Breeder(Mech mech, Mech otherMech) {
        this.mech = mech;
        this.otherMech = otherMech;
    }

    public void breedSecond(int currentSecond) {

    }
}
