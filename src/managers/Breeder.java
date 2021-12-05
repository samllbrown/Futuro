package managers;

import board.Grid;
import board.Pair;
import gameObject.Mech;
import gameObject.MechType;

import java.util.ArrayList;
import java.util.Random;

public class Breeder {
    private ArrayList<BreedingPair> breedingPairsQueue;
    private ArrayList<Mech> children;
    public Breeder() {
        this.breedingPairsQueue = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    public void update(ArrayList<Mech> mechs, Grid grid) {
        ArrayList<BreedingPair> toRemove = new ArrayList<>();
        for(BreedingPair bp : this.breedingPairsQueue) {
            if(bp.getTimeLeft() == 0) {
                for(int i = 0; i < 5; i++) {
                    MechType[] mechTypes = {MechType.PRODUCTION, MechType.RESOURCE};
                    Random rand = new Random();
                    Mech baby = new Mech (mechTypes[rand.nextInt(2)], bp.m1.getGridX(), bp.m1.getGridY(), 100, false, true, false);
                    baby.setTimeUntilAdult(10);
                    grid.getTileAt(new Pair(bp.m1.getGridX(), bp.m1.getGridY())).addMech(baby);
                    mechs.add(baby);
                }
                toRemove.add(bp);
                bp.m1.setIsBreeding(false);
                bp.m1.setIsBreeding(false);
            } else {
                bp.decreaseTimeLeft();
            }
        }
        this.breedingPairsQueue.removeAll(toRemove);
    }

    private void addBreedingPair(BreedingPair bp) {
        this.breedingPairsQueue.add(bp);
    }

    public void breed(Mech m1, Mech m2) {
        this.addBreedingPair(new BreedingPair(m1, m2));
    }
    //lol
}
