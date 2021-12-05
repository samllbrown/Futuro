package services;

import gameObject.Direction;
import gameObject.MechType;

public class Globals {
    public static final String PLAYER_FILE = "players.txt";
    public static final MechType[] NORMAL_MECH_TYPES = {MechType.PRODUCTION, MechType.RESOURCE};
    public static final Direction[] NON_STATIONARY_DIRECTIONS = {Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT};
    public static final String LEVEL1 = "file:res/Levels/LEVEL_1.txt";
    public static final String LEVEL2 = "file:res/Levels/LEVEL_2.txt";
    public static final String LEVEL3 = "file:res/Levels/LEVEL_3.txt";
    public static final String LEVEL4 = "file:res/Levels/LEVEL_4.txt";
    public static final String LEVEL5 = "file:res/Levels/LEVEL_5.txt";
}
