package models;

import java.io.Serializable;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import utilities.RNG;

public class Player extends Character implements Serializable{

	private static final long serialVersionUID = 1L;
	protected int xp;
	protected int nextLevelXP;
	protected boolean defend = false;
	protected String mapLocation;
	
	public Player() {
		super();
	}
	
	public Player(int x, int y, int w, int h, Image img, int strBase, int intBase, int luckBase, int level, String name) {
		super(x, y, w, h, img, strBase, intBase, luckBase, level, name);
		this.nextLevelXP = setNextLevelXP(level);
		setMapLocation("/view/Map1.fxml");
	}

	public String getMapLocation() {
		return mapLocation;
	}

	public void setMapLocation(String mapLocation) {
		this.mapLocation = mapLocation;
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
				this.nextLevelXP = setNextLevelXP(this.level); 
			}
		} while (xp >= this.nextLevelXP);
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
		System.out.println(level);
		if (level == 1) {
			return 1000;
		} else {
			level -= 1;
			return (level * 1000) + setNextLevelXP(level);
		}
	}
	@Override
	public void takeDamage(int damage) {
		if(damage > 0) {
			int dmg = damage - getEquippedArmor().getDamageReduction();
			setCurrentHP(getCurrentHP() - dmg);
		}
	}

	@Override
	public int attack() {
		int chance = RNG.generateInt(1, 20);
		int playerCrit = RNG.generateInt(1, 20) + getLuckMod();
		int damage;
		damage = 5 + getStrength() + equippedWeapon.getDamage();
		if(playerCrit > chance) {
			damage = damage * 2;
		}
		return damage;
	}

	@Override
	public int specialAttack() {
		int chance = RNG.generateInt(1, 20);
		int playerCrit = RNG.generateInt(1, 20) + getLuckMod();
		int specialDamage = 10 + getIntMod();
		if(playerCrit > chance) {
			specialDamage *= 2;
		}
		setCurrentEnergy(getCurrentEnergy() - 5);
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
