package inventory;

import java.util.HashMap;

import gameObject.GameObjectFactory;
import gameObject.Item;

public class Inventory {

	private HashMap<String, InventoryItem> inventoryItems;
	
	public Inventory() {
		HashMap<String, InventoryItem> inventoryItems = new HashMap<String, InventoryItem>();
		inventoryItems.put(DeathMechInventoryItem.getName(), new DeathMechInventoryItem());
		inventoryItems.put(AcidInventoryItem.getName(), new AcidInventoryItem());
		inventoryItems.put(EMPInventoryItem.getName(), new EMPInventoryItem());
		inventoryItems.put(LightningInventoryItem.getName(), new LightningInventoryItem());
		inventoryItems.put(MineInventoryItem.getName(), new MineInventoryItem());
		inventoryItems.put(PuddleInventoryItem.getName(), new PuddleInventoryItem());
		inventoryItems.put(RemodelPInventoryItem.getName(), new RemodelPInventoryItem());
		inventoryItems.put(RemodelRInventoryItem.getName(), new RemodelRInventoryItem());
		this.inventoryItems = inventoryItems;
	}

	public Item useItem(String name, int atX, int atY) throws Exception {
		if(inventoryItems.get(name).getRemainingUses() == 0) {
			// make an exceptions class plz
			throw new Exception("Item has been used too many times to use it again");
		} else {
			inventoryItems.get(name).reduceUses();
			//return GameObjectFactory.makeItemFromName(name, atX, atY);
			return null;
		}
	}

	public void addItem(InventoryItem invItem) {
		//this.inventoryItems.put(invItem.getName(), invItem);
	}
	
	public HashMap<String, InventoryItem> getHashMap(){
		return this.inventoryItems;
	}

}
