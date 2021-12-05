package managers;

import gameObject.Mech;

public class BreedingPair {
    public Mech m1;
    public Mech m2;
    private int timeLeft;

    public BreedingPair(Mech m1, Mech m2) {
        this.m1 = m1;
        this.m2 = m2;
        m1.setIsBreeding(true);
        m2.setIsBreeding(true);
        // 5 seconds to breed
        this.timeLeft = 5;
    }

    public int getTimeLeft() {
        return this.timeLeft;
    }

    public void decreaseTimeLeft() {
        this.timeLeft--;
    }
    //lol
}
