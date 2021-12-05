package inventory;

import java.util.HashMap;


import javafx.scene.control.Label;

/**
 * Inventory.java
 * @author Sam R, Illia L.
 * @version 1
 * Last Mod Date: 27/11/2021
 * Description: handles all items in inventory 
 */
public class Inventory {

    /** The inventory items. */
    private HashMap<String, InventoryItem> inventoryItems;
    
    /** The labels. */
    private HashMap<String, Label> labels;

    /**
     * Instantiates a new inventory.
     */
    public Inventory() {
        HashMap<String, InventoryItem> inventoryItems = new HashMap<String, InventoryItem>();
        HashMap<String, Label> labels = new HashMap<String, Label>();
        this.inventoryItems = inventoryItems;
        this.labels = labels;
    }
    
    public Inventory(HashMap<String, InventoryItem> inventoryItems, HashMap<String, Label> labels) {
    	this.inventoryItems = inventoryItems;
    	this.labels = labels;
    }

    /**
     * Instantiates a new inventory.
     *
     * @param ID the id
     */
    public Inventory(int ID) {
        HashMap<String, InventoryItem> inventoryItems = new HashMap<String, InventoryItem>();
        HashMap<String, Label> labels = new HashMap<String, Label>();

        inventoryItems.put(DeathMechInventoryItem.name, new DeathMechInventoryItem());
        inventoryItems.put(AcidInventoryItem.name, new AcidInventoryItem());
        inventoryItems.put(EMPInventoryItem.name, new EMPInventoryItem());
        inventoryItems.put(LightningInventoryItem.name, new LightningInventoryItem());
        inventoryItems.put(EMPInventoryItem.name, new MineInventoryItem());
        inventoryItems.put(PuddleInventoryItem.name, new PuddleInventoryItem());
        inventoryItems.put(RemodelPInventoryItem.name, new RemodelPInventoryItem());
        inventoryItems.put(RemodelRInventoryItem.name, new RemodelRInventoryItem());

        this.inventoryItems = inventoryItems;
        for(var i: inventoryItems.entrySet()) {
        	labels.put(i.getKey(), i.getValue().getLabel());
        }
    }
    	
	    /**
	     * Use item.
	     *
	     * @param name the item name
	     */
    public void useItem(String name){
        if(inventoryItems.get(name).getRemainingUses() == 0) {

        } else {
            inventoryItems.get(name).reduceUses();
        }
    }

    /**
     * Adds a item to the Inventory.
     *
     * @param invItem the inv item
     */
    public void addItem(InventoryItem invItem) {

        this.inventoryItems.put(invItem.itemName, invItem);
        this.labels.put(invItem.itemName, invItem.getLabel());

    }

    /**
     * Gets the amount of uses an item has left.
     *
     * @param name the name
     * @return the item uses
     */
    public int getItemUses(String name) {
        return inventoryItems.get(name).getRemainingUses();
    }

    /**
     * Gets the label.
     *
     * @param name the name
     * @return the label
     */
    public Label getLabel(String name){
        return labels.get(name);
    }

    /**
     * Gets all items.
     *
     * @return the items
     */
    public HashMap<String, InventoryItem> getItems(){
        return this.inventoryItems;
    }
    
    public HashMap<String, Label> getLabels(){
        return this.labels;
    }
}
