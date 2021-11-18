package gameObject;

public class Mech extends Item {
	// X_RANGE AND Y_RANGE should define where this item
	// acts relative to its current x and y co-ord
	private static final int X_RANGE = 0;
	private static final int Y_RANGE = 0;
	
	// could be an int instead
	private char type;
	private char age;
	
	private boolean isPregnant;
	
	private int health;
	private int secondsUntilAdult;
	
	private int currentXPos;
	private int xDir;
	private int currentYPos;
	private int yDir;
	
	// should itemId be a thing in the constructor for item?
	// need to update this constructor
	public Mech(char type, int xPos, int yPos) {
		super(xPos, yPos, X_RANGE, Y_RANGE);
	}
	
	// assert type is female/production mech?
	public void giveBirth() {
		// ??
	}
	
	public void move() {
		
	}
	
	
	public boolean readyToBirth() {
		// ???
		return true; 
	}
	
	// check validity somewhere else
	public void mate(Mech otherMech) {
		// what happens when they mate?
		// they both stay on the same tile for a few moments
		// one of them becomes pregnant
		// once they're done, they both leave
	}
	
	public void dealWithPuddle(Puddle puddle) {
	}

	@Override
	public void act(Mech someMech) {
		// TODO Auto-generated method stub
		this.mate(someMech);
	}
	
	
	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}
	
	public char getAge() {
		return age;
	}

	public void setAge(char age) {
		this.age = age;
	}
	

	public boolean isPregnant() {
		return isPregnant;
	}
	
	public void setPregnant(boolean isPregnant) {
		this.isPregnant = isPregnant;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getSecondsUntilAdult() {
		return secondsUntilAdult;
	}

	public void setSecondsUntilAdult(int secondsUntilAdult) {
		this.secondsUntilAdult = secondsUntilAdult;
	}

	public int getCurrentXPos() {
		return currentXPos;
	}

	public void setCurrentXPos(int currentXPos) {
		this.currentXPos = currentXPos;
	}

	public int getxDir() {
		return xDir;
	}

	public void setxDir(int xDir) {
		this.xDir = xDir;
	}

	public int getCurrentYPos() {
		return currentYPos;
	}

	public void setCurrentYPos(int currentYPos) {
		this.currentYPos = currentYPos;
	}

	public int getyDir() {
		return yDir;
	}

	public void setyDir(int yDir) {
		this.yDir = yDir;
	}

	
	
}
