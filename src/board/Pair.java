package board;

import gameObject.Direction;


/**
 * Pair.java
 * @author
 * @version
 * Last Mod Date:
 */
public class Pair {
    public int x,y;

    /**
     * Instantiates a Pair of coords (x,y).
     * @param x x val
     * @param y y val
     */
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Adds a new Pair of coords to this current one.
     * @param p
     * @return the new Pair of coord values
     */
    public Pair add(Pair p) {
        return new Pair(this.x + p.x, this.y + p.y);
    }

    /**
     * Subtracts a new Pair of coords to this current one.
     * @param p
     * @return the new Pair of coord values
     */
    public Pair sub(Pair p) {
        return new Pair(this.x - p.x, this.y - p.y);
    }

    /**
     * Multiples a new Pair of coords to this current one.
     * @param p
     * @return the new Pair of coord values
     */
    public Pair mult(Pair p) {
        return new Pair(this.x*p.x, this.y*p.y);
    }

    /**
     *
     * @param p
     * @return
     */
    public Pair div(Pair p) {
        return new Pair(this.x/p.x, this.y/p.y);
    }

//    public Pair toPair(Direction direction) {
//        return new Pair(direction.getXDir(), direction.getYDir());
//    }
}
