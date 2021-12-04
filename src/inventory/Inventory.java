package inventory;

import java.util.HashMap;


import javafx.scene.control.Label;

public class Inventory {

	private HashMap<String, InventoryItem> inventoryItems;
	
	
	public Inventory() {

		HashMap<String, InventoryItem> inventoryItems = new HashMap<String, InventoryItem>();
		HashMap<String, Label> labels = new HashMap<String, Label>();
		this.inventoryItems = inventoryItems;
		this.labels = labels;

	}
	public Inventory(int ID) {
		HashMap<String, InventoryItem> inventoryItems = new HashMap<String, InventoryItem>();
		HashMap<String, Label> labels = new HashMap<String, Label>();

		inventoryItems.put(DeathMechInventoryItem.name, new DeathMechInventoryItem());
		inventoryItems.put(AcidInventoryItem.name, new AcidInventoryItem());
		inventoryItems.put(EMPInventoryItem.name, new EMPInventoryItem());
		inventoryItems.put(LightningInventoryItem.name, new LightningInventoryItem());
		inventoryItems.put(MineInventoryItem.name, new MineInventoryItem());
		inventoryItems.put(PuddleInventoryItem.name, new PuddleInventoryItem());
		inventoryItems.put(RemodelPInventoryItem.name, new RemodelPInventoryItem());
		inventoryItems.put(RemodelRInventoryItem.name, new RemodelRInventoryItem());
		

		this.inventoryItems = inventoryItems;
	}

	public void useItem(String name){
		if(inventoryItems.get(name).getRemainingUses() == 0) {

		} else {
			inventoryItems.get(name).reduceUses();
		}
	}

	public void addItem(InventoryItem invItem) {
		
		this.inventoryItems.put(invItem.itemName, invItem);
		this.labels.put(invItem.itemName, invItem.getLabel());
		
	}
	
	public int getItemUses(String name) {
		return inventoryItems.get(name).getRemainingUses();
	}
	
	
	
	public Label getLabel(String name){
		return labels.get(name);
	}
	
	public HashMap<String, InventoryItem> getItems(){
		return this.inventoryItems;
	}

}
