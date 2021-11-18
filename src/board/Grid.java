package board;

import java.util.Arrays;

//import java.util.Arrays;

import gameObject.GameObjectFactory;

public class Grid {
	private final int width;
	private final int height;
	
	private Tile[][] grid;
	
	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
		this.grid = new Tile[width][height];
	}

	public Tile getTileAt(int x, int y) {
		// should we do x-1, y-1 ?
		// need to make sure that the correct x and y are being accessed
		return this.grid[x][y];
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public Tile[][] getGrid() {
		return this.grid;
	}
	
	// tiles will be read from a file
	// each grid in a file is represented row by row
	// each row is separated by a newline
	public void populateGrid(String tiles) throws Exception {
		String[] rows = tiles.split("\n");
		for(int i = 0; i < rows.length; i++) {
			String[] rowTiles = rows[i].split("");
			for(int j = 0; j < rowTiles.length; j++) {
				this.grid[j][i] = GameObjectFactory.makeTile(rowTiles[j].charAt(0), i, j);
			}
		}
	}
}
