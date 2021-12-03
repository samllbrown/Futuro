package OLD.STUFF.board.gameObject;

import javafx.scene.image.Image;

public class DeathMech extends Mech{

	private Item dmItem;
	private static final int DAMAGE = 100;
	public static final int USES = 5;
	private int killsLeft;
	public static final Image ITEM_IMAGE = new Image("file:res/Sprites/mechD.png",50, 50, false, false);

	public DeathMech(int x, int y) {
		super(MechType.DEATH,x,y,100,false,false,true);
		Item deathMechItem = new Item(x,y,DAMAGE, USES) {
			@Override
			public Image getImage() {
				return ITEM_IMAGE;
			}
		};
		this.killsLeft = USES;
		this.dmItem  = deathMechItem;
	}

	public Item getDeathItem(){
		return this.dmItem;
	}

	@Override
	public void actOn(Mech mech) {
		if(killsLeft != 0) {
			mech.takeDamage(DAMAGE);
			killsLeft--;
			super.health -= 20;
			System.out.println("DEATH MECH HAS KILLED A MECH");
		}
	}

	public Image getImage() {
		return ITEM_IMAGE;
	}
}