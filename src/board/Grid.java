package board;

//import java.util.Arrays;

import gameObject.GameObjectFactory;

public class Grid {
	private int width;
	private int height;
	
	private Tile[][] grid;
	
	public Grid(int height, int width) {
		this.width = width;
		this.height = height;
		this.grid = new Tile[height][width];
	}

	public Tile getTileAt(int x, int y) {
		return this.grid[y][x];
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void setHeight(int height) {
		this.height = height;
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
				this.grid[i][j] = GameObjectFactory.makeTile(rowTiles[j].charAt(0), i, j);
			}
		}
	}
	
//	public static void main(String[] args) throws Exception {
//		String test = "WWWPPPWWP\n"
//				+ "WWWPPWWPW\n"
//				+ "PPPPWWPPW\n"
//				+ "WWWPWPPPP";
//		Grid grid = new Grid(4, 9);
//		grid.populateGrid(test);
//		System.out.println(Arrays.deepToString(grid.getGrid()));
//		System.out.println(grid.getTileAt(7, 0).toString());
//	}
}
