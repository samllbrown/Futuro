package gameObject;

import board.Grid;
import board.Pair;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import managers.Game;
import services.Globals;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Mech.java
 * @author Sam R, illia
 * Last Mod Date: 06/12/2021
 */
public class Mech extends Rectangle {

    /** The type. */
    private MechType type;

    /** The y. */
    private int x, y;

    /** The Constant NUM_OF_BABIES_IF_BIRTHING. */
    public static final int NUM_OF_BABIES_IF_BIRTHING = 5;

    /** The current direction. */
    private Direction currentDirection;

    /** The current cords. */
    private Pair currentCords;

    /** The time until adult. */
    private int timeUntilAdult;

    /** The pregnancy timer. */
    private int pregnancyTimer;

    /** The health. */
    protected int health;

    /** The pregnant. */
    private boolean pregnant;

    /** The num of babies. */
    private int numOfBabies;

    /** The prev Y. */
    private int prevX, prevY;

    /** The img. */
    private Image img;

    /** The is baby. */
    private boolean isBaby;

    /** The is sterile. */
    private boolean isSterile;

    /** The is breeding. */
    private boolean isBreeding;

    /** The seconds until adult. */
    private int secondsUntilAdult;

    /** The breeding cool down. */
    private int breedingCoolDown;

    /**
     * Instantiates a Mech.
     *
     * @param type mech type (resource/production/death)
     * @param x starting x position of the Mech
     * @param y starting y position of the Mech
     * @param health health of the Mech
     * @param pregnant can the Mech get pregnant
     * @param isBaby does Mech have a baby
     * @param isSterile is the Mechs sterile (unable to reproduce)
     */
    public Mech(MechType type, int x, int y, int health, boolean pregnant, boolean isBaby, boolean isSterile) {
	setWidth(Game.TILE_SIZE);
	setHeight(Game.TILE_SIZE);
	relocate(x * Game.TILE_SIZE, y * Game.TILE_SIZE);
	this.type = type;
	this.x = x;
	this.y = y;
	this.timeUntilAdult = 0;
	this.breedingCoolDown = 0;
	this.currentCords = new Pair(x, y);
	this.currentDirection = Direction.RIGHT;
	this.health = health;
	this.pregnancyTimer = 0;
	this.pregnant = pregnant;
	this.isBaby = isBaby;
	this.img = isBaby ? new Image("file:res/Sprites/babyMechP.png", 50, 50, false, false) : getImageForType(type);
	this.isSterile = isSterile;
	this.secondsUntilAdult = 0;
	setFill(new ImagePattern(this.img));
    }

    /**
     * Checks if the Mech can breed with another Mech (based on their types, state of pregnancy etc).
     * @param otherMech potential mech to be bred with
     * @return true or false depending on if Mech can breed
     */
    public boolean canBreedWith(Mech otherMech) {
	// the mechs must be opposite types
	// both mechs must be both NOT CURRENTLY BREEDING
	// the mech who is a resource mech must NOT be pregnant
	// neither mech can be a death mech
	// both mechs must NOT be sterile
	boolean bothOppositeTypes = (this.type != otherMech.getType());
	boolean neitherSterile = !(this.isSterile || otherMech.isSterile());
	boolean neitherCurrentlyBreeding = !(this.isBreeding || otherMech.isBreeding());
	boolean neitherPregnant = !(this.isPregnant() || otherMech.isPregnant());
	boolean bothNotDeathMech = !((this.type != MechType.DEATH) && (otherMech.getType() != MechType.DEATH));
	return bothOppositeTypes && neitherSterile && neitherCurrentlyBreeding && neitherPregnant && bothNotDeathMech;
    }

    /**
     * Act on.
     *
     * @param otherMech the other mech
     */
    public void actOn(Mech otherMech) {
    }

    /**
     * Grow into adult.
     */
    public void growIntoAdult() {
	assert (this.isBaby);
	assert (this.timeUntilAdult <= 0);
	this.isBaby = false;
	this.setImage(getImageForType(this.type));
    }

    /**
     * Sets the time until adult.
     *
     * @param time the new time until adult
     */
    public void setTimeUntilAdult(int time) {
	this.timeUntilAdult = time;
    }

    /**
     * Gets the time until adult.
     *
     * @return the time until adult
     */
    public int getTimeUntilAdult() {
	return this.timeUntilAdult;
    }

    /**
     * Sets the checks if is breeding.
     *
     * @param bool the new checks if is breeding
     */
    public void setIsBreeding(boolean bool) {
	this.isBreeding = bool;
    }

    /**
     * Reduce time until adult.
     */
    public void reduceTimeUntilAdult() {
	this.timeUntilAdult--;
    }

    /**
     * Giving birth to a Mech.
     * Given the mech is of the right type (production) it can give birth
     * @return a baby Mech
     */
    public Mech birthMech() {
	assert this.type.equals(MechType.PRODUCTION);
	Random random = new Random();
	MechType babyType = Globals.NORMAL_MECH_TYPES[random.nextInt(2)];
	Direction babyDirection = Globals.NON_STATIONARY_DIRECTIONS[random.nextInt(4)];
	Mech myBaby = new Mech(babyType, this.x, this.y, 100, false, true, true);
	myBaby.setCurrentDirection(babyDirection);
	return myBaby;
    }

    /**
     * Gets the breeding cool down.
     *
     * @return the breeding cool down
     */
    public int getBreedingCoolDown() {
	return this.breedingCoolDown;
    }

    /**
     * Sets the breeding cool down.
     *
     * @param newBreedingCoolDown the new breeding cool down
     */
    public void setBreedingCoolDown(int newBreedingCoolDown) {
	this.breedingCoolDown = newBreedingCoolDown;
    }

    /**
     * Reset breeding cool down.
     */
    public void resetBreedingCoolDown() {
	this.breedingCoolDown = 0;
    }

    /**
     * Reduce breeding cool down.
     */
    public void reduceBreedingCoolDown() {
	if (this.breedingCoolDown > 0) {
	    this.breedingCoolDown--;
	}
    }

    /**
     * Checks if the Mech is currently breeding.
     * @return isBreeding boolean val
     */
    public boolean isBreeding() {
	return this.isBreeding;
    }

    /**
     * Sets current direction of the Mech.
     * @param dir current direction of Mech
     */
    public void setCurrentDirection(Direction dir) {
	this.currentDirection = dir;
    }

    /**
     * Retrieves the direction the Mech is turning based on their relative direction.
     * @param relativeDir mech's relative direction to where they are facing
     * @param currentDirection mech's current direction
     * @return updated Direction mech is turning
     */
    private static Direction getTurnDirection(String relativeDir, Direction currentDirection) {
	try {
	    switch (relativeDir) {
	    case "AROUND":
		return Direction.fromPair(currentDirection.toPair().mult(new Pair(-1, -1)));
	    case "RIGHT":
		return Direction.fromPair(new Pair(currentDirection.getYDir(), (-1 * currentDirection.getXDir())));
	    case "LEFT":
		return Direction.fromPair(new Pair((-1 * currentDirection.getYDir()), currentDirection.getXDir()));
	    default:
		return currentDirection;
	    }
	} catch (Exception e) {
	    System.err.println("ERROR GETTING DIRECTION FROM STATIC TURN DIRECTION");
	    return null;
	}
    }

    /**
     * Turning a mech in a relative direction.
     *
     * @param relativeDir given relative direction
     * @throws Exception the exception
     */
    public void turn(String relativeDir) throws Exception {
	switch (relativeDir) {
	case "AROUND":
	    this.currentDirection = Direction.fromPair(this.currentDirection.toPair().mult(new Pair(-1, -1)));
	    break;
	case "RIGHT":
	    this.currentDirection = Direction
		    .fromPair(new Pair(this.currentDirection.getYDir(), (-1 * this.currentDirection.getXDir())));
	    break;
	case "LEFT":
	    this.currentDirection = Direction
		    .fromPair(new Pair((-1 * this.currentDirection.getYDir()), this.currentDirection.getXDir()));
	    break;
	default:
	    this.currentDirection = this.currentDirection;
	}
    }

    /**
     * Retrieves num of babies the Mech has.
     *
     * @return num of babies
     */
    public int getNumOfBabies() {
	return numOfBabies;
    }

    /**
     * Set the num of babies a Mech has.
     *
     * @param numOfBabies the new num of babies
     */
    public void setNumOfBabies(int numOfBabies) {
	this.numOfBabies = numOfBabies;
    }

    /**
     * Retrieves the next position of the Mech.
     *
     * @param inDirection the direction the Mech is moving in
     * @return new Pair of coords where Mech is positioned
     */
    private Pair getNextPos(Direction inDirection) {
	return this.currentCords.add(inDirection.toPair());
    }

    /**
     * Moving the Mech on the game grid.
     * Mech is moved based on all the possible locations it can get to given it's current direction
     * and considering whether the type of tile being stepped on.
     *
     * @param onGrid the game grid
     * @throws Exception the exception
     */
    public void move(Grid onGrid) throws Exception {
	Pair possibleNextCoords = this.getNextPos(this.currentDirection);
	ArrayList<String> turns = new ArrayList<>();
	turns.add("FORWARD");
	turns.add("LEFT");
	turns.add("RIGHT");
	Random rand = new Random();

	if (!(onGrid.getTileAt(possibleNextCoords).isWalkable())) {
	    if ((!onGrid.getTileAt(this.getNextPos(getTurnDirection("RIGHT", currentDirection))).isWalkable())
		    && (!onGrid.getTileAt(this.getNextPos(getTurnDirection("LEFT", currentDirection))).isWalkable())) {
		this.turn("AROUND");
		this.currentCords = this.currentCords.add(this.currentDirection.toPair());
		if (this.isBaby) {
		    this.currentCords.add(new Pair(1, 1));
		}
	    } else {
		turns.remove("FORWARD");
		turns = new ArrayList<String>(turns.stream().filter(
			dirStr -> (onGrid.getTileAt(this.getNextPos(getTurnDirection(dirStr, this.currentDirection))))
				.isWalkable())
			.collect(Collectors.toList()));
		this.turn(turns.get(rand.nextInt(turns.size())));
		this.currentCords = this.currentCords.add(this.currentDirection.toPair());
		if (this.isBaby) {
		    this.currentCords.add(new Pair(1, 1));
		}
	    }
	} else {
	    turns = new ArrayList<String>(turns.stream().filter(
		    dirStr -> (onGrid.getTileAt(this.getNextPos(getTurnDirection(dirStr, this.currentDirection))))
			    .isWalkable())
		    .collect(Collectors.toList()));
	    this.turn(turns.get(rand.nextInt(turns.size())));
	    this.currentCords = this.currentCords.add(this.currentDirection.toPair());
	    if (this.isBaby) {
		this.currentCords.add(new Pair(1, 1));
	    }
	}
	this.x = this.currentCords.x;
	this.y = this.currentCords.y;
    }

    /**
     * Retrieve mech's baby state.
     * @return boolean val of "is mech a baby"
     */
    public boolean getIsBaby() {
	return this.isBaby;
    }

    /**
     * Retrieve image of Mech.
     * @return image of Mech
     */
    public Image getImage() {
	return this.img;
    }

    /**
     * Sets image of Mech based on type.
     *
     * @param img the new image
     */
    public void setImage(Image img) {
	this.img = img;
    }

    /**
     * Retrieves Mech's x position on grid.
     * @return x position
     */
    public int getGridX() {
	return this.x;
    }

    /**
     * Retrieves Mech's y position on grid.
     * @return y position
     */
    public int getGridY() {
	return this.y;
    }

    /**
     * Gets the image for the Mech depending on its type.
     * @param type mech type
     * @return Image of mech
     */
    public static Image getImageForType(MechType type) {
	Image img = null;
	switch (type) {
	case RESOURCE:
	    System.err.println("IS RESOURCE AND ADDING FOR THIS");
	    img = new Image("file:res/Sprites/mechR.png", 50, 50, false, false);
	    break;
	case PRODUCTION:
	    System.err.println("IS PRODUCTION AND ADDING FOR THIS");
	    img = new Image("file:res/Sprites/mechP.png", 50, 50, false, false);
	    break;
	case DEATH:
	    System.err.println("IS death AND ADDING FOR THIS");
	    img = new Image("file:res/Sprites/mechD.png", 50, 50, false, false);
	    break;
	}
	return img;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
	char typeAsChar = this.type.toString().charAt(0);
	return String.format("M,%d,%d,%d,%c,%d,%d", this.getGridX(), this.getGridY(), this.getHealth(), typeAsChar,
		(this.isPregnant() ? 1 : 0), this.getTimeUntilAdult());
    }

    /**
     * Retrieve mech type.
     * @return mech type
     */
    public MechType getType() {
	return this.type;
    }

    /**
     * Reduce pregnancy timer.
     */
    public void reducePregnancyTimer() {
	this.pregnancyTimer--;
    }

    /**
     * Make pregnant.
     */
    public void makePregnant() {
	this.setPregnant(true);
	this.pregnancyTimer = 10;
    }

    /**
     * Sets the pregnancy timer.
     *
     * @param time the new pregnancy timer
     */
    public void setPregnancyTimer(int time) {
	this.pregnancyTimer = 10;
    }

    /**
     * Gets the pregnancy timer.
     *
     * @return the pregnancy timer
     */
    public int getPregnancyTimer() {
	return this.pregnancyTimer;
    }

    /**
     * Check if mech is pregnant.
     * @return boolean val of pregnant state
     */
    public boolean isPregnant() {
	return this.pregnant;
    }

    /**
     * Set mech's pregnant state.
     *
     * @param isPregnant boolean val of pregnant state
     */
    public void setPregnant(boolean isPregnant) {
	this.pregnant = isPregnant;
    }

    /**
     * Set the Mech type.
     * @param type given mech type
     */
    public void setType(MechType type) {
	this.type = type;
    }

    /**
     * Retrieve the Mech's health.
     *
     * @return health of the Mech
     */
    public int getHealth() {
	return this.health;
    }

    /**
     * Gives damage to the mech and reduces their health accordingly.
     * @param damage amount of damage
     */
    public void takeDamage(int damage) {
	this.health -= damage;
	if (this.health <= 0) {
	    System.err.println("I have lived a good life. Now, I die.");
	}
    }

    /**
     * Checks sterile state of the mech (true if a mech has been sterilised).
     * @return sterile state of mech
     */
    public boolean isSterile() {
	return isSterile;
    }

    /**
     * Sets sterile state of mech.
     * @param isSterile sterile state of mech.
     */
    public void setSterile(boolean isSterile) {
	this.isSterile = isSterile;
    }
}
