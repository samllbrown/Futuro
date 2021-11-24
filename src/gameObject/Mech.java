package gameObject;
import board.Grid;
import board.Tile;
import board.Pair;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Random;

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

	private int speed = 1;


	private Tile[] neighbourTiles;

	// should itemId be a thing in the constructor for item?
	// need to update this constructor
	public Mech(char type, int xPos, int yPos, int xDir, int yDir) {
		super(xPos, yPos);
		this.currentXPos = xPos;
		this.currentYPos = yPos;
		this.xDir = xDir;
		this.yDir = yDir;
	}

	@Override
	public String toString() {
		return String.format("\nMech x pos: %d\nMech Y pos: %d", this.currentXPos, this.currentYPos);
	}

	private void switchDirection(Grid grid) {
		Pair[] pairs = {new Pair(this.currentXPos, this.currentYPos+1), new Pair(this.currentXPos+1, this.currentYPos),
	new Pair(this.currentXPos, this.currentYPos-1), new Pair(this.currentXPos-1, this.currentYPos)};
		Random rand = new Random();
		int choice = rand.nextInt(4);
		while(!grid.getTileAt(pairs[choice].x, pairs[choice].y).isWalkable()) {
			choice = rand.nextInt(4);
			System.out.println(choice);
		}
		System.out.println("Found x and y: " + pairs[choice].x + "," + pairs[choice].y);
		setXAndY(pairs[choice]);
	}

	public void setXAndY(Pair pair) {
		this.currentXPos = pair.x;
		this.currentYPos = pair.y;
	}

	public void move(Grid grid) {
		int nextXPos = (this.currentXPos + (this.xDir * speed));
		int nextYPos = (this.currentYPos + (this.yDir * speed));
		System.out.println("Next x pos : " + nextXPos);
		System.out.println("Next y pos : " + nextYPos);
		if(!grid.getTileAt(nextXPos, nextYPos).isWalkable()) {
			switchDirection(grid);
		} else {
			this.currentXPos = nextXPos;
			this.currentYPos = nextYPos;
		}
	}
	
	
	public boolean readyToBirth() {
		// ???
		return true; 
	}
	
	// check validity somewhere else
	public void mate(Mech otherMech) {
		if(this.type != otherMech.getType()) {
			switch (otherMech.getType()) {
				case 'R':
					this.isPregnant = true;
				default:
					otherMech.setPregnant(true);
			}
		} else {

		}
		// what happens when they mate?
		// they both stay on the same tile for a few moments
		// one of them becomes pregnant
		// once they're done, they both leave
	}
	
	public void dealWithPuddle(Puddle puddle) {

	}

	@Override
	public void act(Mech someMech) {
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
		return this.currentXPos;
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
		return this.currentYPos;
	}

	public void setCurrentYPos(int currentYPos) {
		this.currentYPos = currentYPos;
	}

	public int getyDir() {
		return this.yDir;
	}

	public void setyDir(int yDir) {
		this.yDir = yDir;
	}

	
	
}
