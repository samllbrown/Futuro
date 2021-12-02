package gameObject;

import board.Pair;

public enum Direction {

    RIGHT(1,0), LEFT(-1,0), UP(0,1), DOWN(0,-1), STAT(0,0);

    private final int xDir;
    private final int yDir;

    Direction(int xDir, int yDir) {
        this.xDir = xDir;
        this.yDir = yDir;
    }

    public static Direction fromPair(Pair p) throws Exception {
        for(Direction dir : Direction.values()) {
            if(dir.xDir == p.x && dir.yDir == p.y) {
                return dir;
            }
        }
        throw new Exception("Invalid p");
    }

    public int getXDir() {
        return this.xDir;
    }

    public int getYDir() {
        return this.yDir;
    }

    public Pair toPair() {
        return new Pair(this.xDir, this.yDir);
    }

}
