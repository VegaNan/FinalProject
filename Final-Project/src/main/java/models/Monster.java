package models;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Monster extends Character {
	protected int xpYield;

	protected ArrayList<Item> lootItems = new ArrayList<>();

	public Monster(int x, int y, int w, int h, Image img, int strBase, int intBase, int luckBase, int level, String name) {
		super(x, y, w, h, img, strBase, intBase, luckBase, level, name);
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

	public ArrayList<Item> getLootItems() {
		return lootItems;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		return builder.toString();
	}

	@Override
	public int specialAttack() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int defend() {
		// TODO Auto-generated method stub
		return 0;
	}

}
