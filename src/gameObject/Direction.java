package gameObject;

import board.Pair;

/**
 * Direction.java
 * @author
 * @version
 * Last Mod Date:
 */
public enum Direction {

    RIGHT(1,0), LEFT(-1,0), UP(0,1), DOWN(0,-1), STAT(0,0);

    private final int xDir;
    private final int yDir;

    /**
     * Instantiates a Direction
     * @param xDir direction in x plane
     * @param yDir direction in y plane
     */
    Direction(int xDir, int yDir) {
        this.xDir = xDir;
        this.yDir = yDir;
    }

    /**
     * Determines the direction from a Pair of coordinates.
     * Compares the given Pair to constant direction values in class to determine direction of coords.
     * @param p Pair object.
     * @return the resulting direction
     * @throws Exception Invalid Pair of coords given.
     */
    public static Direction fromPair(Pair p) throws Exception {
        for(Direction dir : Direction.values()) {
            if(dir.xDir == p.x && dir.yDir == p.y) {
                return dir;
            }
        }
        throw new Exception("Invalid p");
    }

    /**
     * Retrieve direction in the x plane.
     * @return x-plane direction
     */
    public int getXDir() {
        return this.xDir;
    }

    /**
     * Retrieve direction in the y plane.
     * @return y-plane direction
     */
    public int getYDir() {
        return this.yDir;
    }

    /**
     * Converts a set of directions to a Pair of coordinates
     * @return a Pair (xDir, yDir).
     */
    public Pair toPair() {
        return new Pair(this.xDir, this.yDir);
    }

}
