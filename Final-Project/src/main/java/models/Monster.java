package models;

import java.util.ArrayList;

import enums.MonsterType;
import javafx.scene.image.Image;
import utilities.RNG;

public class Monster extends Character {
	protected int xpYield;
	protected MonsterType monster;
	protected ArrayList<Item> lootItems = new ArrayList<>();

	public Monster(int x, int y, int w, int h, Image img, int strBase, int intBase, int luckBase, int level, String name, MonsterType monster) {
		super(x, y, w, h, img, strBase, intBase, luckBase, level, name);
		setName(monster.toString());
		setMonsterType(monster);
		int str = 1;
		int intelligence = 1;
		int luck = 1;
		switch(monster) {
		case GENERIC_OGRE:
			str = RNG.generateInt(1, 8) * level;
			intelligence = RNG.generateInt(1, 2) * level;
			luck = RNG.generateInt(1, 8) * level;
			break;
		case GENERIC_WITCH:
			str = RNG.generateInt(1, 3) * level;
			intelligence = RNG.generateInt(1, 8) * level;
			luck = RNG.generateInt(1, 8) * level;
			break;
		case GENERIC_DRAGON:
			str = RNG.generateInt(10, 15) * level;
			intelligence = RNG.generateInt(10, 15) * level;
			luck = RNG.generateInt(10, 15) * level;
			break;
		case SUPREME_EMPEROR_OVERLORD_ALPACA:
			str = RNG.generateInt(3, 40) * level;
			intelligence = RNG.generateInt(3, 40) * level;
			luck = RNG.generateInt(3, 40) * level;
			break;
		}
		setStrBase(str);
		setIntBase(intelligence);
		setLuckBase(luck);
		setBaseHP(getStrBase() * 10);
		setCurrentHP(getBaseHP());
		setBaseEnergy(getIntBase() * 7);
		setCurrentEnergy(getBaseEnergy());
	}

	public MonsterType getMonsterType() {
		return monster;
	}
	
	public void setMonsterType(MonsterType monster) {
		this.monster = monster;
	}

	@Override
	public void takeDamage(int damage) {
		setCurrentHP(getCurrentHP() - damage);
	}

	@Override
	public int attack() {
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

	@Override
	public String getStats() {
		// TODO Auto-generated method stub
		return null;
	}


}
