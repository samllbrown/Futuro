package managers;

import board.Grid;
import gameObject.Mech;

import java.util.ArrayList;

/**
 * Breeder.Java
 * @author Sam R
 * @version
 * Last Mod Date: 05/12/2021
 */
public class Breeder {
    private ArrayList<BreedingPair> breedingPairsQueue;
    private ArrayList<Mech> children;
    private ArrayList<Mech> pregnantMechs = new ArrayList<>();

    public Breeder() {
        this.breedingPairsQueue = new ArrayList<>();
        this.children = new ArrayList<>();
        this.pregnantMechs = new ArrayList<>();
    }

    public void update(ArrayList<Mech> mechs, Grid grid) throws Exception {
        ArrayList<BreedingPair> toRemove = new ArrayList<>();
        for(BreedingPair bp : this.breedingPairsQueue) {
            if(bp.getTimeLeft() == 0) {
                toRemove.add(bp);
                bp.m1.setIsBreeding(false);
                bp.m1.setBreedingCoolDown(30);
                bp.m2.setIsBreeding(false);
                bp.m2.setBreedingCoolDown(30);
                bp.m1.move(grid);
                bp.m2.move(grid);
                switch(bp.m1.getType()) {
                    case RESOURCE:
                        bp.m2.makePregnant();
                        this.pregnantMechs.add(bp.m2);
                        break;
                    case PRODUCTION:
                        bp.m1.makePregnant();
                        this.pregnantMechs.add(bp.m1);
                        break;
                }
            } else {
                bp.decreaseTimeLeft();
            }
        }

        ArrayList<Mech> tempPregs = new ArrayList<>(this.pregnantMechs);

        for(Mech pm : tempPregs) {
            if(pm.getPregnancyTimer() <= 0 && (pm.getHealth() > 0)) {
                for(int i = 0; i < 5; i++) {
                    MechType[] mechTypes = {MechType.PRODUCTION, MechType.RESOURCE};
                    Random rand = new Random();
                    Mech baby = new Mech (mechTypes[rand.nextInt(2)], pm.getGridX(), pm.getGridY(), 100, false, true, false);
                    baby.setTimeUntilAdult(60);
                    grid.getTileAt(new Pair(pm.getGridX(), pm.getGridY())).addMech(baby);
                    mechs.add(baby);
                }
                this.pregnantMechs.remove(pm);
                pm.setPregnant(false);
            } else {
                pm.reducePregnancyTimer();
            }
        }
        this.breedingPairsQueue.removeAll(toRemove);
    }

    private void addBreedingPair(BreedingPair bp) {
        this.breedingPairsQueue.add(bp);
    }

    public void breed(Mech m1, Mech m2) {
        this.addBreedingPair(new BreedingPair(m1, m2));
        audioPlayer.playBreedSound();
        m1.setIsBreeding(true);
        m2.setIsBreeding(true);
    }
}
