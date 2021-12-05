package board;

import java.util.ArrayList;
import java.util.Arrays;

//import java.util.Arrays;

import gameObject.GameObjectFactory;
import gameObject.TileType;

public class Grid {
	private final int width;
	private final int height;
	
	private Tile[][] grid;
	//private ArrayList<Tile> allTiles;
	/*
	* PLEASE BE AWARE THAT THIS READS IN THE TILES AND CONSTRUCTS THE ARRAY TOP TO BOTTOM
	* I.E. THE ORIGIN OF X,Y IS TOP LEFT, NOT BOTTOM LEFT
	* */
	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
		this.grid = new Tile[width][height];
		//this.allTiles = new ArrayList<>();
	}

//	public ArrayList<Tile> getAllTiles() {
//		return this.allTiles;
//	}

	public Tile getTileAt(int x, int y) {
		// should we do x-1, y-1 ?
		// need to make sure that the correct x and y are being accessed
		return this.grid[x][y];
	}

	public Tile getTileAt(Pair cords) {
		return this.grid[cords.x][cords.y];
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
				Tile t = GameObjectFactory.makeTile(rowTiles[j].charAt(0), j, i);
				this.grid[j][i] = t;
				//allTiles.add(t);
			}
		}
	}

	@Override
	public String toString() {
		return this.getGridAsString();
	}

	private String getGridAsString() {
		String tiles = "";
		for(int i = 0; i < this.width; i++) {
			for(int j = 0; j < this.height; j++) {
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
