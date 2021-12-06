package board;

import gameObject.GameObjectFactory;
import gameObject.TileType;

/**
 * Grid.java
 * @author Sam R
 * Last Mod Date: 03/12/2021
 */
public class Grid {

    /** The width. */
    private final int width;

    /** The height. */
    private final int height;

    /** The grid. */
    private Tile[][] grid;

    /**
     * Instantiate a new Grid, with a 2d array of Tiles.
     *
     * @param width width of the grid
     * @param height height of the grid
     */
    public Grid(int width, int height) {
	this.width = width;
	this.height = height;
	this.grid = new Tile[width][height];
    }

    /**
     * Retrieve Tile as a specified position column x, row y.
     * @param x x-coord
     * @param y y-coord
     * @return Tile at column x, row y
     */
    public Tile getTileAt(int x, int y) {
	return this.grid[x][y];
    }

    /**
     * Retrieve Tile as a specified position column coords.x, row coords.y
     * @param cords a Pair of coords
     * @return Tile at coords
     */
    public Tile getTileAt(Pair cords) {
	return this.grid[cords.x][cords.y];
    }

    /**
     * Retrieves grid width.
     *
     * @return grid width
     */
    public int getWidth() {
	return this.width;
    }

    /**
     * Retrieves grid height.
     *
     * @return grid height
     */
    public int getHeight() {
	return this.height;
    }

    /**
     * Retrieves all the tiles in the Grid.
     *
     * @return tiles in grid
     */
    public Tile[][] getGrid() {
	return this.grid;
    }

    /**
     * Tiles read from a file and grid populated accordingly.
     * with each row in the file representing a set of tiles.
     *
     * @param tiles ...
     * @throws Exception the exception
     */
    public void populateGrid(String tiles) throws Exception {
	String[] rows = tiles.split("\n");
	for (int i = 0; i < rows.length; i++) {
	    String[] rowTiles = rows[i].split("");
	    for (int j = 0; j < rowTiles.length; j++) {
		Tile t = GameObjectFactory.makeTile(rowTiles[j].charAt(0), j, i);
		this.grid[j][i] = t;
		// allTiles.add(t);
	    }
	}
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
	return this.getGridAsString();
    }

    /**
     * Gets the grid as string.
     *
     * @return the grid as string
     */
    private String getGridAsString() {
	String tiles = "";
	for (int i = 0; i < this.width; i++) {
	    for (int j = 0; j < this.height; j++) {
		char type = 'X';
		TileType currentTileType = this.grid[i][j].getTileType();
		switch (currentTileType) {
		case PATH:
		    type = 'P';
		    break;
		case TUNNEL:
		    type = 'T';
		    break;
		case WALL:
		    type = 'W';
		    break;
		}
		tiles += String.valueOf(type);
	    }
	    tiles += "\n";
	}
	return tiles;
    }
}
