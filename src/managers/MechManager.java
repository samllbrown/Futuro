package managers;

import board.Grid;
import board.Tile;
import gameObject.DeathMech;
import gameObject.Item;
import gameObject.Mech;
import gameObject.MechType;

import java.util.ArrayList;

/**
 * MechManager.java
 *
 * @author Sam R, Illia
 * @version 2
 * Last mod Date 29/11/2021
 */
public class MechManager {

    /**
     * checks mechs for damage
     *
     * @param mechs array list of mechs to be checked
     * @param grid the game grid
     * @throws Exception
     */
    public static void checkMechsForDamageFromItems(ArrayList<Mech> mechs, Grid grid) throws Exception {
        ArrayList<Mech> normalMechs = new ArrayList<>();
        for(Mech m : mechs) {
            if (m.getType() != MechType.DEATH) {
                normalMechs.add((Mech) m);
                normalMechs.add(m);
            }
        }

        for(Mech mech : normalMechs) {
            Tile currentPath = grid.getTileAt(mech.getGridX(), mech.getGridY());
            if(currentPath.getCurrentItem() != null) {
                Item itemOnCurrentTile = currentPath.getCurrentItem();
                itemOnCurrentTile.act(mech);
            }
        }
    }

    /**
     * checks mech to see if its dead
     *
     * @param mechs array list of mechs to be checked
     * @param grid the game grid
     * @throws Exception
     */
    public static void checkMechsForDeath(ArrayList<Mech> mechs, Grid grid) throws Exception {
        ArrayList<DeathMech> deathMechs = new ArrayList<>();

        for(Mech mech : mechs) {
            if(mech.getType() == MechType.DEATH) {
                deathMechs.add((DeathMech) mech);
            }
        }

        for(DeathMech deathMech : deathMechs) {
            Tile currentTile = grid.getTileAt(deathMech.getGridX(), deathMech.getGridY());
            for(Mech nm : currentTile.getOtherMechsOnTile(deathMech)) {
                if(nm.getType() != MechType.DEATH) {
                    deathMech.actOn(nm);
                }
            }
        }
    }
}