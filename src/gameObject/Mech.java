package gameObject;
import board.Grid;
import board.Tile;
import board.Pair;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import managers.Game;
import services.Globals;
//import services.AudioPlayer;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Mech.java
 * @author
 * @version
 * Last Mod Date:
 */
public class Mech extends Rectangle {
	// X_RANGE AND Y_RANGE should define where this item
	// acts relative to its current x and y co-ord
//	private static final int X_RANGE = 0;
//	private static final int Y_RANGE = 0;
//
//	// could be an int instead
//	private char type;
//	private char age;
//
//	private boolean isPregnant;
//
//	private int health;
//	private int secondsUntilAdult;
//
//	private int currentXPos;
//	private int xDir;
//	private int currentYPos;
//	private int yDir;
//
//	private int speed = 1;
//
//
//	private Tile[] neighbourTiles;
	/*
	* for the movement:
	* Illia's suggestion:
	* for every move, get the neighbour tiles and check them
	* */
	private MechType type;

	private int x, y;

	public static final int NUM_OF_BABIES_IF_BIRTHING = 5;

	private Direction currentDirection;
	private Pair currentCords;
	private int timeUntilAdult;
	private int pregnancyTimer;
	protected int health;
	private boolean pregnant;
	private int numOfBabies;

	private int prevX, prevY;
	private Image img;

	private boolean isBaby;

	private boolean isSterile;
	private boolean isBreeding;

	private int breedingCoolDown;

	// haven't implemented age functionality

	/**
	 * Instantiates a Mech
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
		this.img = isBaby ? new Image("file:res/Sprites/babyMech.png", 50, 50, false, false) : getImageForType(type);
		this.isBaby = isBaby;
		this.isSterile = isSterile;
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


	public void actOn(Mech otherMech) {
		// breed
	}

	public void growIntoAdult() {
		assert (this.isBaby);
		assert (this.timeUntilAdult <= 0);
		this.isBaby = false;
		this.setImage(getImageForType(this.type));
	}

	public void setTimeUntilAdult(int time) {
		this.timeUntilAdult = time;
	}

	public int getTimeUntilAdult() {
		return this.timeUntilAdult;
	}

	public void setIsBreeding(boolean bool) {
		this.isBreeding = bool;
	}

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
		// need to create baby speed thingy
		Mech myBaby = new Mech(babyType, this.x, this.y, 100, false, true, true);
		myBaby.setCurrentDirection(babyDirection);
		return myBaby;
	}

	public int getBreedingCoolDown() {
		return this.breedingCoolDown;
	}

	public void setBreedingCoolDown(int newBreedingCoolDown) {
		this.breedingCoolDown = newBreedingCoolDown;
	}

	public void resetBreedingCoolDown() {
		this.breedingCoolDown = 0;
	}

	public void reduceBreedingCoolDown() {
		if(this.breedingCoolDown > 0) {
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
//				this.currentDirection = new Pair(this.currentDirection.getYDir(), (-1 * this.currentDirection.getXDir()));
				case "LEFT":
					return Direction.fromPair(new Pair((-1 * currentDirection.getYDir()), currentDirection.getXDir()));
//			case "FORWARD":
//				this.currentDirection = this.currentDirection;
				default:
					return currentDirection;
			}
		} catch(Exception e) {
			System.err.println("ERROR GETTING DIRECTION FROM STATIC TURN DIRECTION");
			return null;
		}
	}

	/**
	 * Turning a mech in a relative direction.
	 * @param relativeDir given relative direction
	 * @throws Exception
	 */
	public void turn(String relativeDir) throws Exception {
		switch (relativeDir) {
			case "AROUND":
				this.currentDirection = Direction.fromPair(this.currentDirection.toPair().mult(new Pair(-1, -1)));
				break;
			case "RIGHT":
				this.currentDirection = Direction.fromPair(new Pair(this.currentDirection.getYDir(), (-1 * this.currentDirection.getXDir())));
				break;
//				this.currentDirection = new Pair(this.currentDirection.getYDir(), (-1 * this.currentDirection.getXDir()));
			case "LEFT":
				this.currentDirection = Direction.fromPair(new Pair((-1 * this.currentDirection.getYDir()), this.currentDirection.getXDir()));
				break;
//			case "FORWARD":
//				this.currentDirection = this.currentDirection;
			default:
				this.currentDirection = this.currentDirection;
		}
	}

	/**
	 * Retrieves num of babies the Mech has
	 * @return num of babies
	 */
	public int getNumOfBabies() {
		return numOfBabies;
	}

	/**
	 * Set the num of babies a Mech has.
	 * @param numOfBabies
	 */
	public void setNumOfBabies(int numOfBabies) {
		this.numOfBabies = numOfBabies;
	}

	/**
	 * Retrieves the next position of the Mech
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
	 * @param onGrid the game grid
	 * @throws Exception
	 */
	public void move(Grid onGrid) throws Exception {
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
	public Image getImage(){
		return this.img;
	}

	/**
	 * Sets image of Mech based on type.
	 */
	public void setImage() {
		this.img = this.getImageForType(this.type);
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
				img = new Image("file:res/Sprites/mechR.png",50, 50, false, false);
				break;
			case PRODUCTION:
				System.err.println("IS PRODUCTION AND ADDING FOR THIS");
				img = new Image("file:res/Sprites/mechP.png",50, 50, false, false);
				break;
			case DEATH:
				System.err.println("IS death AND ADDING FOR THIS");
				img = new Image("file:res/Sprites/mechD.png", 50, 50, false, false);
				break;
		}
		return img;
	}

	/**
	 * Retrieve mech type.
	 * @return mech type
	 */
	public MechType getType() {
		return this.type;
	}

	/**
	 * Check if mech is pregnant.
	 * @return boolean val of pregnant state
	 */
	public boolean isPregnant() {
		return this.pregnant;
	}

	/**
	 * Set mech's pregnant state
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
	 * Retrieve the Mech's health
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
		if(this.health <= 0) {
			System.err.println("I have lived a good life. Now, I die.");
		}
	}
	// should itemId be a thing in the constructor for item?
	// need to update this constructor
//	public Mech(char type, int xPos, int yPos, int xDir, int yDir) {
//		super(xPos, yPos);
//		this.currentXPos = xPos;
//		this.currentYPos = yPos;
//		this.xDir = xDir;
//		this.yDir = yDir;
//	}
//
//	@Override
//	public String toString() {
//		return String.format("\nMech x pos: %d\nMech Y pos: %d", this.currentXPos, this.currentYPos);
//	}
//
//	private void switchDirection(Grid grid) {
//		Pair[] pairs = {new Pair(this.currentXPos, this.currentYPos+1), new Pair(this.currentXPos+1, this.currentYPos),
//	new Pair(this.currentXPos, this.currentYPos-1), new Pair(this.currentXPos-1, this.currentYPos)};
//		Random rand = new Random();
//		int choice = rand.nextInt(4);
//		while(!grid.getTileAt(pairs[choice].x, pairs[choice].y).isWalkable()) {
//			choice = rand.nextInt(4);
//			System.out.println(choice);
//		}
//		System.out.println("Found x and y: " + pairs[choice].x + "," + pairs[choice].y);
//		setXAndY(pairs[choice]);
//	}
//
//	public void setXAndY(Pair pair) {
//		this.currentXPos = pair.x;
//		this.currentYPos = pair.y;
//	}
//
//	public void move(Grid grid) {
//		int nextXPos = (this.currentXPos + (this.xDir * speed));
//		int nextYPos = (this.currentYPos + (this.yDir * speed));
//		System.out.println("Next x pos : " + nextXPos);
//		System.out.println("Next y pos : " + nextYPos);
//		if(!grid.getTileAt(nextXPos, nextYPos).isWalkable()) {
//			switchDirection(grid);
//		} else {
//			this.currentXPos = nextXPos;
//			this.currentYPos = nextYPos;
//		}
//	}
//
//
//	public boolean readyToBirth() {
//		// ???
//		return true;
//	}
//
//	// check validity somewhere else
//	public void mate(Mech otherMech) {
//		if(this.type != otherMech.getType() && (!otherMech.isPregnant())) {
//			switch (otherMech.getType()) {
//				case 'R':
//					this.isPregnant = true;
//				default:
//					otherMech.setPregnant(true);
//			}
//		} else {
//
//		}
//		// what happens when they mate?
//		// they both stay on the same tile for a few moments
//		// one of them becomes pregnant
//		// once they're done, they both leave
//	}
//
//	public void dealWithPuddle(Puddle puddle) {
//
//	}
//
//	@Override
//	public void act(Mech someMech) {
//		this.mate(someMech);
//	}
//
//
//	public char getType() {
//		return type;
//	}
//
//	public void setType(char type) {
//		this.type = type;
//	}
//
//	public char getAge() {
//		return age;
//	}
//
//	public void setAge(char age) {
//		this.age = age;
//	}
//
//
//
//	public int getHealth() {
//		return health;
//	}
//
//	public void setHealth(int health) {
//		this.health = health;
//	}
//
//	public int getSecondsUntilAdult() {
//		return secondsUntilAdult;
//	}
//
//	public void setSecondsUntilAdult(int secondsUntilAdult) {
//		this.secondsUntilAdult = secondsUntilAdult;
//	}
//
//	public int getCurrentXPos() {
//		return this.currentXPos;
//	}
//
//	public void setCurrentXPos(int currentXPos) {
//		this.currentXPos = currentXPos;
//	}
//
//	public int getxDir() {
//		return xDir;
//	}
//
//	public void setxDir(int xDir) {
//		this.xDir = xDir;
//	}
//
//	public int getCurrentYPos() {
//		return this.currentYPos;
//	}
//
//	public void setCurrentYPos(int currentYPos) {
//		this.currentYPos = currentYPos;
//	}
//
//	public int getyDir() {
//		return this.yDir;
//	}
//
//	public void setyDir(int yDir) {
//		this.yDir = yDir;
//	}	

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
