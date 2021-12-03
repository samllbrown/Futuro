package OLD.STUFF.board;

public class Pair {
    public int x,y;
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Pair add(Pair p) {
        return new Pair(this.x + p.x, this.y + p.y);
    }
    public Pair sub(Pair p) {
        return new Pair(this.x - p.x, this.y - p.y);
    }
    public Pair mult(Pair p) {
        return new Pair(this.x*p.x, this.y*p.y);
    }
    public Pair div(Pair p) {
        return new Pair(this.x/p.x, this.y/p.y);
    }

//    public Pair toPair(Direction direction) {
//        return new Pair(direction.getXDir(), direction.getYDir());
//    }
}
