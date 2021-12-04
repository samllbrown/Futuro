package board;

import java.util.ArrayList;
import java.util.Arrays;

//import java.util.Arrays;

import gameObject.GameObjectFactory;

/**
 * Grid.java
 * @author
 * @version
 * Last Mod Date:
 */
public class Grid {
	private final int width;
	private final int height;
	
	private Tile[][] grid;
	//private ArrayList<Tile> allTiles;
	/*
	* PLEASE BE AWARE THAT THIS READS IN THE TILES AND CONSTRUCTS THE ARRAY TOP TO BOTTOM
	* I.E. THE ORIGIN OF X,Y IS TOP LEFT, NOT BOTTOM LEFT
	* */

	/**
	 * Instantiate a new Grid, with a 2d array of Tiles
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
	 *Retrieve Tile as a specified position column coords.x, row coords.y
	 * @param cords a Pair of coords
	 * @return Tile at coords
	 */
	public Tile getTileAt(Pair cords) {
		return this.grid[cords.x][cords.y];
	}

	/**
	 * Retrieves grid width
	 * @return grid width
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * Retrieves grid height
	 * @return grid height
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * Retrieves all the tiles in the Grid
	 * @return tiles in grid
	 */
	public Tile[][] getGrid() {
		return this.grid;
	}
	
	// tiles will be read from a file
	// each grid in a file is represented row by row
	// each row is separated by a newline

	/**
	 * Tiles read from a file and grid populated accordingly.
	 * with each row in the file representing a set of tiles.
	 * @param tiles ...
	 * @throws Exception
	 */
	public void populateGrid(String tiles) throws Exception {
		String[] rows = tiles.split("\n");
		for(int i = 0; i < rows.length; i++) {
			String[] rowTiles = rows[i].split("");
			for(int j = 0; j < rowTiles.length; j++) {
				Tile t = GameObjectFactory.makeTile(rowTiles[j].charAt(0), j, i);
				this.grid[j][i] = t;
				//allTiles.add(t);
			}
		}
	}

	public String getGridAsString() {
		String tiles = "";
		for(int i = 0; i < this.width; i++) {
			for(int j = 0; j < this.height; j++) {
				tiles += this.grid[i][j];
			}
		}
		return tiles;
	}
}
