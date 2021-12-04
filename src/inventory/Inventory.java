package inventory;

import java.util.HashMap;

import gameObject.GameObjectFactory;
import gameObject.Item;
import javafx.scene.control.Label;

public class Inventory {

	private HashMap<String, InventoryItem> inventoryItems;
	private HashMap<String, Label> labels = new HashMap<String, Label>();
	
	public Inventory() {
		HashMap<String, InventoryItem> inventoryItems = new HashMap<String, InventoryItem>();
		HashMap<String, Label> labels = new HashMap<String, Label>();

		inventoryItems.put(DeathMechInventoryItem.getName(), new DeathMechInventoryItem());
		inventoryItems.put(AcidInventoryItem.getName(), new AcidInventoryItem());
		inventoryItems.put(EMPInventoryItem.getName(), new EMPInventoryItem());
		inventoryItems.put(LightningInventoryItem.getName(), new LightningInventoryItem());
		inventoryItems.put(MineInventoryItem.getName(), new MineInventoryItem());
		inventoryItems.put(RemodelPInventoryItem.getName(), new RemodelPInventoryItem());
		inventoryItems.put(RemodelRInventoryItem.getName(), new RemodelRInventoryItem());
		

		this.inventoryItems = inventoryItems;
		
		for(var i : this.inventoryItems.entrySet()) {
			labels.put(i.getKey(), i.getValue().getLabel());
		}

		this.labels = labels;
	}

	public void useItem(String name){
		if(inventoryItems.get(name).getRemainingUses() == 0) {

		} else {
			inventoryItems.get(name).reduceUses();
		}
	}

	public void addItem(InventoryItem invItem) {
		//this.inventoryItems.put(invItem.getName(), invItem);
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
	
	public HashMap<String, Label> getLabels(){
		return this.labels;
	}
	
	

}
