package gameObject;

import javafx.scene.image.Image;

/**
 * DeathMech.java
 * @author Sam B 
 * Description: The death mech class (Not item, extends Mech) 
 * Last Mod Date: 27/11/2021
 */
public class DeathMech extends Mech {

	/** The dm item. */
	private Item dmItem;

	/** The Constant DAMAGE. */
	private static final int DAMAGE = 100;

	/** The Constant USES. */
	public static final int USES = 5;

	/** The kills left of the death mech. */
	private int killsLeft;

	/** The Constant ITEM_IMAGE. */
	public static final Image ITEM_IMAGE = new Image("file:res/Sprites/mechD.png", 50, 50, false, false);

	/**
	 * Instantiates a new death mech.
	 *
	 * @param x the starting x of the mech
	 * @param y the starting y of the mech
	 */
	public DeathMech(int x, int y) {
		super(MechType.DEATH, x, y, 100, false, false, true);
		Item deathMechItem = new Item(x, y, DAMAGE, USES) {
			@Override
			public Image getImage() {
				return ITEM_IMAGE;
			}
		};
		this.killsLeft = USES;
		this.dmItem = deathMechItem;
	}

	/**
	 * Gets the death item.
	 *
	 * @return the death item
	 */
	public Item getDeathItem() {
		return this.dmItem;
	}

	/**
	 * Function to handle the death mech interacting with a mech
	 *
	 * @param mech the mech
	 */
	@Override
	public void actOn(Mech mech) {
		if (killsLeft != 0) {
			mech.takeDamage(DAMAGE);
			killsLeft--;
			super.health -= 20;
			System.out.println("DEATH MECH HAS KILLED A MECH");
		}
	}

	/**
	 * Gets the image of the death mech.
	 *
	 * @return the image
	 */
	public Image getImage() {
		return ITEM_IMAGE;
	}
}