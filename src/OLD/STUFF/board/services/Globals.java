package OLD.STUFF.board.services;

import OLD.STUFF.board.gameObject.Direction;
import OLD.STUFF.board.gameObject.MechType;

public class Globals {
    public static final String PLAYER_FILE = "players.txt";
    public static final MechType[] NORMAL_MECH_TYPES = {MechType.PRODUCTION, MechType.RESOURCE};
    public static final Direction[] NON_STATIONARY_DIRECTIONS = {Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT};
}
