package managers;

import gameObject.Mech;

/**
 * BreedingPair.java
 * @author  Sam R, Sam B
 * Last Mod Date: 28/11/2021
 */

public class BreedingPair {
    public Mech m1;
    public Mech m2;
    private int timeLeft;

    /**
     * creates the pair to breed
     * @param m1 first mech
     * @param m2 second mech
     */
    public BreedingPair(Mech m1, Mech m2) {
	this.m1 = m1;
	this.m2 = m2;
	// 5 seconds to breed
	this.timeLeft = 5;
    }

    /**
     * time left on breading
     * @return time left
     */
    public int getTimeLeft() {
	return this.timeLeft;
    }

    /**
     * decreases the time left on breeding
     */
    public void decreaseTimeLeft() {
	this.timeLeft--;
    }

}
