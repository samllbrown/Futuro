package managers;

import java.util.ArrayList;
import java.util.HashMap;

import gameObject.GameObjectFactory;
import gameObject.Item;

public class Inventory {

	private HashMap<String, InventoryItem> inventoryItems;

	public Inventory() {
		this.inventoryItems = new HashMap<>();
	}

	public Item useItem(String name, int atX, int atY) throws Exception {
		if(inventoryItems.get(name).getRemainingUses() == 0) {
			// make an exceptions class plz
			throw new Exception("Item has been used too many times to use it again");
		} else {
			inventoryItems.get(name).reduceUses();
			GameObjectFactory.makeItemFromName(name, atX, atY, )
		}

	}

}
