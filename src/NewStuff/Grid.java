package NewStuff;

import Tiles.Tile;

public class Grid {
    private final int tileWidth;
    private final int tileHeight;

    private final double pixelWidth;
    private final double pixelHeight;

    private Tile[][] grid;

    public Grid(int tileWidth, int tileHeight) {
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.grid = new Tile[tileWidth][tileHeight];
        this.pixelWidth = this.tileWidth * NewTest.Globals.TILE_WIDTH_PIXELS;
        this.pixelHeight = this.tileHeight * NewTest.Globals.TILE_HEIGHT_PIXELS;
    }

    public Tile getTileAt(int gridX, int gridY) {
        return this.grid[gridX][gridY];
    }

    public Tile getTileAt(Pair coordinates) {
        return this.grid[coordinates.x][coordinates.y];
    }

    public Tile[][] getGrid() {
        return this.grid;
    }

    public int getTileWidth() {
        return this.tileWidth;
    }

    public int getTileHeight() {
        return this.tileHeight;
    }

    public double getPixelWidth() {
        return this.pixelWidth;
    }

    public double getPixelHeight() {
        return this.pixelHeight;
    }


    public void populateGrid(String tiles) throws Exception {
        String[] rows = tiles.split("\n");
        for(int i = 0; i < rows.length; i++) {
            String[] rowTiles = rows[i].split("");
            for(int j = 0; j < rowTiles.length; j++) {
                Tile t = NewTest.Factory.makeTile(rowTiles[j].charAt(0), j, i);
                this.grid[j][i] = t;
                //allTiles.add(t);
            }
        }
    }

}
