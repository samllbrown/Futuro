package services;

import gameObject.Direction;
import gameObject.MechType;

/**
 * Globals.java
 * @author
 * @version
 * Last Mod Date:
 * Description: Contains global constant variables needed.
 */
public class Globals {
    public static final String PLAYER_FILE = "players.txt";
    public static final MechType[] NORMAL_MECH_TYPES = {MechType.PRODUCTION, MechType.RESOURCE};
    public static final Direction[] NON_STATIONARY_DIRECTIONS = {Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT};
}
