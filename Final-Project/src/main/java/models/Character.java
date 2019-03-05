package models;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import models.Armor;
import models.Weapon;

public abstract class Character extends Rectangle{
	protected Color color;
	protected String name;
	protected boolean isAlive;
	protected int level;
	protected int strBase;
	protected int intBase;
	protected int luckBase;
	protected int strMod = 0;
	protected int intMod = 0;
	protected int luckMod = 0;
	protected int baseHP;
	protected int currentHP;
	protected Weapon equippedWeapon;
	protected Armor equippedArmor;

	public Character(int strBase, int intBase, int luckBase, int level) {
		setStrBase(strBase);
		setIntBase(intBase);
		setLuckBase(luckBase);
		setBaseHP(level);
		setCurrentHP(this.baseHP);
	}

	public int getStrBase() {
		return strBase;
	}

	public void setStrBase(int strBase) {
		this.strBase = strBase;
	}

	public int getIntBase() {
		return intBase;
	}

	public void setIntBase(int intBase) {
		this.intBase = intBase;
	}

	public int getLuckBase() {
		return luckBase;
	}

	public void setLuckBase(int luckBase) {
		this.luckBase = luckBase;
	}

	public int getStrMod() {
		return strMod;
	}

	public void setStrMod(int strmod) {
		this.strMod = strmod;
	}

	public int getIntMod() {
		return intMod;
	}

	public void setIntMod(int intmod) {
		this.intMod = intmod;
	}

	public int getLuckMod() {
		return luckMod;
	}

	public void setLuckMod(int luckmod) {
		this.luckMod = luckmod;
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

	public abstract int takeDamage(int damage);

	public abstract int attack();

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Character [name=");
		builder.append(name);
		builder.append(", level=");
		builder.append(level);
		builder.append(", strBase=");
		builder.append(strBase);
		builder.append(", intBase=");
		builder.append(intBase);
		builder.append(", luckBase=");
		builder.append(luckBase);
		builder.append(", strMod=");
		builder.append(strMod);
		builder.append(", intMod=");
		builder.append(intMod);
		builder.append(", luckMod=");
		builder.append(luckMod);
		builder.append(", baseHP=");
		builder.append(baseHP);
		builder.append(", currentHP=");
		builder.append(currentHP);
		builder.append(", equippedWeapon=");
		builder.append(equippedWeapon);
		builder.append(", equippedArmor=");
		builder.append(equippedArmor);
		builder.append("]");
		return builder.toString();
	}

}
