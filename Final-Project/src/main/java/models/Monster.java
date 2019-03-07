package models;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class Monster extends Character{
	protected int xpYield;
	
	protected ArrayList<Item> lootItems = new ArrayList<>();
	
	public Monster(int x, int y, int w, int h, Color color, int strBase, int intBase, int luckBase, int level, String name) {
		super(x, y, w, h, color, strBase, intBase, luckBase, level, name);
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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		return builder.toString();
	}

}
