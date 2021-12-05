package managers;

import board.Grid;
import board.Path;
import board.Tile;
import gameObject.DeathMech;
import gameObject.Item;
import gameObject.Mech;
import gameObject.MechType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MechManager {

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
                itemOnCurrentTile.actOn(mech);
            }
        }
    }
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


    //lol
}
