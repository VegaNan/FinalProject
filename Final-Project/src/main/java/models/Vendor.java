package models;

import java.util.ArrayList;

import utilities.RNG;

public class Vendor {
	protected ArrayList<Item> inventory = new ArrayList<>();

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
	public int buyingItems() {
		return 0;
		
	}

}
