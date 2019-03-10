package models;

import java.io.Serializable;
import java.util.ArrayList;

import controllers.CharacterCreationController;
import utilities.RNG;

public class Vendor implements Serializable{
	protected ArrayList<Item> inventory = new ArrayList<>();
	

	public ArrayList<Item> getItemBag() {
		return inventory;
	}
	
	public void setItemBag(ArrayList<Item> inventory) {
		this.inventory = inventory;
	}
	
	public void addItem(Item item) {
		inventory.add(item);
	}
	
	public void removeItem(int index) {
		inventory.remove(index);
	}

	public Vendor() {

	}

	// Selling items to the vendor
	public int sellingItems(Item item) {
		int goldAmount = 0;
		int itemValue = item.getValue();

		// Vendor will buy items for 90% of the items original price
		if (itemValue >= 10) {
			goldAmount = itemValue - (itemValue / 10);

		} else {
			int randValue = RNG.generateInt(1, 7);
			goldAmount = itemValue - randValue;

		}
		return goldAmount;
	}

	// Buying items from the vendor
	public int buyingItems(Item item) {
		int goldAmount = 0;
		int itemValue = item.getValue();
		goldAmount = itemValue * (-1);

		return goldAmount;

	}

}
