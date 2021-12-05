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

		//inventoryItems.put(DeathMechInventoryItem.getName(), new DeathMechInventoryItem());
		//inventoryItems.put(AcidInventoryItem.getName(), new AcidInventoryItem());

		this.inventoryItems = inventoryItems;
	}

	public void useItem(String name){
		if(inventoryItems.get(name).getRemainingUses() == 0) {

		} else {
			inventoryItems.get(name).reduceUses();
		}
	}

	@Override
	public String toString() {
		String asString = "";
		for(String i : inventoryItems.keySet()) {
			asString += (i + "," + inventoryItems.get(i).getRemainingUses() + "\n");
		}
		return asString;
	}

	public int size() {
		return inventoryItems.keySet().size();
	}

	public void addItem(InventoryItem invItem) {
		//this.inventoryItems.put(invItem.getName(), invItem);
	}
	
	public HashMap<String, InventoryItem> getHashMap(){
		return this.inventoryItems;
	}

}
