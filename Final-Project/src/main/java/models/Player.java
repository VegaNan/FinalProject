package models;

import javafx.scene.image.Image;

public class Player extends Character {
	protected int xp;
	protected int nextLevelXP;
	protected boolean defend = false;
	
	public Player(int x, int y, int w, int h, Image img, int strBase, int intBase, int luckBase, int level, String name) {
		super(x, y, w, h, img, strBase, intBase, luckBase, level, name);
		
	}

	public void setDefend(boolean defend) {
		this.defend = defend;
	}
	
	public boolean getDefend() {
		return defend;
	}
	
	public void checkLevelUp(int xp, int nextLevelXP) {
		do {
			if (xp >= nextLevelXP) {
				this.level++;
				setNextLevelXP(this.level);
			}
		} while (xp >= nextLevelXP);
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public int getNextLevelXP() {
		return nextLevelXP;
	}

	public int setNextLevelXP(int level) {
		if (level == 1) {
			return 1000;
		} else {
			return (level * 1000) + setNextLevelXP(level - 1);
		}
	}
	
	@Override
	public void takeDamage(int damage) {
		setCurrentHP(getCurrentHP() - damage);
	}

	@Override
	public int attack() {
		int damage = 5 + getStrMod() + equippedWeapon.getDamage();
		return damage;
	}

	@Override
	public int specialAttack() {
		setCurrentEnergy(getCurrentEnergy() - 5);
		int specialDamage = 10 + getIntMod();
		if(specialDamage < 0) {
			specialDamage = 0;
		}
		return specialDamage;
	}

	@Override
	public int defend() {
		return getEquippedArmor().armorRating;
	}

	@Override
	public String getStats() {
		StringBuilder sb = new StringBuilder();
		sb.append("Player Stats").append("\nStrength: ").append(getStrength())
		.append("\nIntelligence: ").append(getIntelligence());
		return sb.toString();
	}



}
