package models;

import java.util.ArrayList;

public class Monster extends Character{
	protected int xpYield;
	
	protected ArrayList<Item> lootItems = new ArrayList<>();
	
	public Monster(int strBase, int intBase, int luckBase, int level) {
		super(strBase, intBase, luckBase, level);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int takeDamage(int damage) {
		setCurrentHP(getCurrentHP() - damage);
		return 0;
	}

	@Override
	public int attack() {
		// TODO Auto-generated method stub
		return getStrength();
	}
	
	public ArrayList<Item> getLootItems(){
		return lootItems;
	}

}
