package models;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public abstract class Character extends Rectangle{
	protected String name;
	protected boolean isAlive;
	protected int coordX;
	protected int coordY;
	protected int level;
	protected int strBase;
	protected int intBase;
	protected int luckBase;
	protected int strMod = 0;
	protected int intMod = 0;
	protected int luckMod = 0;
	protected int baseHP;
	protected int currentHP;
	protected int baseEnergy;
	protected int currentEnergy;
	protected int money;
	protected Weapon equippedWeapon;
	protected Armor equippedArmor;
	protected ArrayList<Item> itemBag = new ArrayList<>();

	public ArrayList<Item> getItemBag() {
		return itemBag;
	}
	
	public void setItemBag(ArrayList<Item> itemBag) {
		this.itemBag = itemBag;
	}
	
	public void addItem(Item item) {
		itemBag.add(item);
	}
	
	public void removeItem(int index) {
		itemBag.remove(index);
	}
	
	public Character(int coordX, int coordY, int w, int h, Image img, int strBase, int intBase, int luckBase, int level, String name) {
		setStrBase(strBase);
		setIntBase(intBase);
		setLuckBase(luckBase);
		setLevel(level);
		setName(name);
		setBaseHP(getStrBase() * 10);
		setCurrentHP(getBaseHP());
		setBaseEnergy(getIntBase() * 7);
		setCurrentEnergy(getBaseEnergy());
		setCoordX(coordX);
		setCoordY(coordY);
		setWidth(w);
		setHeight(h);
		setFill(new ImagePattern(img));
	}

	public int getStrBase() {
		return strBase;
	}

	public void setStrBase(int strBase) {
		if(strBase > -1) {
			this.strBase = strBase;
			this.strMod = generateDamageBonus();
		}
	}
	
	private int generateDamageBonus() {
		if(getStrBase() > 15) {
			return (getStrBase() - 15);
		}
		else if(getStrBase() < 10) {
			return (getStrBase() - 10);
		}
		else {
			return 0;
		}
	}

	public int getIntBase() {
		return intBase;
	}

	public void setIntBase(int intBase) {
		if(intBase > -1) {
			this.intBase = intBase;
			this.intMod = generateSpecialAttackBonus();
		}
	}
	
	private int generateSpecialAttackBonus() {
		if(getIntBase() > 15) {
			return ((getIntBase() - 15)*2);
		}
		else if(getIntBase() < 10) {
			return ((getIntBase() - 10)*2);
		}
		else {
			return 0;
		}
	}

	public int getLuckBase() {
		return luckBase;
	}

	public void setLuckBase(int luckBase) {
		if(luckBase > -1) {
			this.luckBase = luckBase;
			this.luckMod = generateDodgeBonus();
		}
	}
	
	private int generateDodgeBonus() {
		if(getLuckBase() > 15) {
			return ((getLuckBase() - 15)/2);
		}
		else if(getLuckBase() < 11) {
			return ((getLuckBase() - 11)/2);
		}
		else {
			return 0;
		}
	}

	public int getStrMod() {
		return strMod;
	}

	public int getIntMod() {
		return intMod;
	}

	public int getLuckMod() {
		return luckMod;
	}

	public int getBaseHP() {
		return baseHP;
	}

	public void setBaseHP(int baseHP) {
		this.baseHP = baseHP;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int currentHP) {
		this.currentHP = currentHP;
	}
	
	public int getBaseEnergy() {
		return this.baseEnergy;
	}
	
	public void setBaseEnergy(int baseEnergy) {
		this.baseEnergy = baseEnergy;
	}
	public int getCurrentEnergy() {
		return this.currentEnergy;
	}
	
	public void setCurrentEnergy(int currentEnergy) {
		this.currentEnergy = currentEnergy;
	}

	public Weapon getEquippedWeapon() {
		return equippedWeapon;
	}

	public void setEquippedWeapon(Weapon equippedWeapon) {
		this.equippedWeapon = equippedWeapon;
	}

	public Armor getEquippedArmor() {
		return equippedArmor;
	}

	public void setEquippedArmor(Armor equippedArmor) {
		this.equippedArmor = equippedArmor;
	}

	public int getStrength() {
		return this.strBase + this.strMod;
	}

	public int getIntelligence() {
		return this.intBase + this.intMod;
	}

	public int getLuck() {
		return this.luckBase + this.luckMod;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getCoordX() {
		return coordX;
	}

	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	public int getCoordY() {
		return coordY;
	}

	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}
	
	public void setMoney(int money) {
		this.money = money;
	}
	
	public int getMoney(int money) {
		return money;
	}

	public abstract void takeDamage(int damage);

	public abstract int attack();
	
	public abstract int specialAttack();
	
	public abstract int defend();
	
	public abstract String getStats();

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getName())
		.append("\nLevel ").append(getLevel())
		.append("\nStrength ").append(getStrBase()).append(" + ").append(getStrMod())
		.append("\nIntelligence ").append(getIntBase()).append(" + ").append(getIntMod())
		.append("\nLuck ").append(getLuckBase()).append(" + ").append(getLuckMod())
		.append("\nHP ").append(getCurrentHP()).append(" / ").append(getBaseHP())
		.append("\nEnergy ").append(getCurrentEnergy()).append(" / ").append(getBaseEnergy())
		.append("\nEquipped Weapon: ").append(getEquippedWeapon())
		.append("\nEquipped Armor: ").append(getEquippedArmor());
		return builder.toString();
	}

}
