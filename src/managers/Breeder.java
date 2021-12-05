package managers;

import gameObject.Mech;

/**
 * Breeder.Java
 * @author
 * @version
 * Last Mod Date: 05/12/2021
 */
public class Breeder {
    private static final int SECONDS_TO_BREED = 5;
    private Mech mech;
    private Mech otherMech;

    /**
     * Instantiates a Breeder, breeds mechs together.
     * @param mech mech1
     * @param otherMech mech2
     */
    public Breeder(Mech mech, Mech otherMech) {
        this.mech = mech;
        this.otherMech = otherMech;
    }

    public void breedSecond(int currentSecond) {

    }
}
