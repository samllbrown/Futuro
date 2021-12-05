package managers;

import board.Grid;
import board.Pair;
import gameObject.Mech;
import gameObject.MechType;

import java.util.ArrayList;

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
                    Mech baby = new Mech (MechType.PRODUCTION, bp.m1.getGridX(), bp.m1.getGridY(), 100, false, true, false);
                    grid.getTileAt(new Pair(bp.m1.getGridX(), bp.m1.getGridY())).addMech(baby);
                    mechs.add(baby);
                }
                toRemove.add(bp);
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
