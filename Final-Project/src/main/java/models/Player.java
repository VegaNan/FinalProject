package models;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Player extends Character {
	protected int xp;
	protected int nextLevelXP;

	public Player(int x, int y, int w, int h, Image img, int strBase, int intBase, int luckBase, int level, String name) {
		super(x, y, w, h, img, strBase, intBase, luckBase, level, name);
		
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
		return getStrength();
	}

	@Override
	public int specialAttack() {
		return getIntelligence();
	}

	@Override
	public int defend() {
		return getEquippedArmor().ArmorRating;
	}

	@Override
	public String getStats() {
		StringBuilder sb = new StringBuilder();
		sb.append("Player Stats").append("\nStrength: ").append(getStrength())
		.append("\nIntelligence: ").append(getIntelligence());
		return sb.toString();
	}



}
