package board;

/**
 * Pair.java
 * @author Sam R
 * Last Mod Date: 02/12/2021
 */
public class Pair {

    /** The x and y. */
    public int x, y;

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
     * @param p Pair of coords
     * @return the new Pair of coord values
     */
    public Pair add(Pair p) {
	return new Pair(this.x + p.x, this.y + p.y);
    }

    /**
     * Subtracts a new Pair of coords to this current one.
     * @param p Pair of coords
     * @return the new Pair of coord values
     */
    public Pair sub(Pair p) {
	return new Pair(this.x - p.x, this.y - p.y);
    }

    /**
     * Multiples a new Pair of coords to this current one.
     * @param p Pair of coords
     * @return the new Pair of coord values
     */
    public Pair mult(Pair p) {
	return new Pair(this.x * p.x, this.y * p.y);
    }

    /**
     * Divides this Pair of coords by a new one.
     * @param p pair of coords divided by
     * @return the new Pair of coord values
     */
    public Pair div(Pair p) {
	return new Pair(this.x / p.x, this.y / p.y);
    }
}
