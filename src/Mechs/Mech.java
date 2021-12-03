package Mechs;

import NewStuff.GameObject;
import NewStuff.Grid;
import NewStuff.Pair;

public abstract class Mech extends GameObject {
    private int health;
    // any mech can act on another mech, or on a puddle
    // any mech could be a baby at any given time EXCEPT DEATH MECH
    public Mech(int gridX, int gridY, int health) {
        super(gridX, gridY);
        this.health = health;
    }

    public int getHealth() {
        return this.health;
    }

    public void decreaseHealth(int healthPoints) {
        this.health -= healthPoints;
    }

    public boolean isReadyToDie() {
        return (this.health <= 0);
    }

    public abstract void actOn(NormalMech mech);

    // NOTE TO SELF AND ILLIA:
    // MOVE COULD JUST TAKE IN THE NEIGHBOUR TILES, AND THEN DO THE PROCESSING FROM THERE
    // IT PROBABLY MAKES A BIT MORE SENSE TO PASS THE ACTUAL NEIGHBOURING TILES RATHER THAN THE ENTIRE GRID
    public void move(Grid onGrid) {
        Pair possibleNextCoords = this.getNextPos(this.currentDirection);
        ArrayList<String> turns = new ArrayList<>();
        turns.add("FORWARD");
        turns.add("LEFT");
        turns.add("RIGHT");
        //turns.add("AROUND");
        Random rand = new Random();
        // if the next tile in the current direction isn't walkable (i.e. if it's a wall)

        if (!(onGrid.getTileAt(possibleNextCoords).isWalkable())) {
            // if the
            //if((!(onGrid.getTileAt(this.getNextPos(Direction.RIGHT)).isWalkable())) && (!(onGrid.getTileAt(this.getNextPos(Direction.LEFT)).isWalkable()))) {
            if((!onGrid.getTileAt(this.getNextPos(getTurnDirection("RIGHT",currentDirection))).isWalkable()) && (!onGrid.getTileAt(this.getNextPos(getTurnDirection("LEFT",currentDirection))).isWalkable())) {
                this.turn("AROUND");
                this.currentCords = this.currentCords.add(this.currentDirection.toPair());
            } else {
                turns.remove("FORWARD");
                turns = new ArrayList<String>(turns.stream().filter(dirStr ->
                        (onGrid.getTileAt(this.getNextPos(getTurnDirection(dirStr, this.currentDirection)))).isWalkable()).collect(Collectors.toList()));
                this.turn(turns.get(rand.nextInt(turns.size())));
                this.currentCords = this.currentCords.add(this.currentDirection.toPair());
            }
        } else {
            turns = new ArrayList<String>(turns.stream().filter(dirStr ->
                    (onGrid.getTileAt(this.getNextPos(getTurnDirection(dirStr, this.currentDirection)))).isWalkable()).collect(Collectors.toList()));
            this.turn(turns.get(rand.nextInt(turns.size())));
            this.currentCords = this.currentCords.add(this.currentDirection.toPair());
        }
//		this.currentCords = this.currentCords.add(this.currentDirection.toPair());
        this.x = this.currentCords.x;
        this.y = this.currentCords.y;
    }


}
