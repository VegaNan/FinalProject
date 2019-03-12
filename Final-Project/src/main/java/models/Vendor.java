package models;

import java.util.ArrayList;

import controllers.CharacterCreationController;
import enums.MiscItemType;
import utilities.RNG;

public class Vendor {
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
//		addItem(item);
//		addItem(item1);
//		addItem(item2);
		
	}
	
	public String printItemBag(ArrayList<Item> itemBag) {
		String itemList = "";
		for(int i = 0; i < itemBag.size(); i++) {
			StringBuilder sb = new StringBuilder();
			
			
			 itemList +=  itemBag.get(i).toString();
		}
		return itemList;
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
