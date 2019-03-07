package models;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import models.Armor;
import models.Weapon;

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
	protected Weapon equippedWeapon;
	protected Armor equippedArmor;

	public Character(int coordX, int coordY, int w, int h, Color color, int strBase, int intBase, int luckBase, int level) {
		setStrBase(strBase);
		setIntBase(intBase);
		setLuckBase(luckBase);
		setBaseHP(level);
		setCurrentHP(this.baseHP);
		setCoordX(coordX);
		setCoordY(coordY);
		setWidth(w);
		setHeight(h);
		setFill(color);
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

	public abstract int takeDamage(int damage);

	public abstract int attack();

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(name);
		builder.append(" level ");
		builder.append(level);
		builder.append("strength ");
		builder.append(strBase);
		builder.append(" + ");
		builder.append(strMod);
		builder.append("intelligence");
		builder.append(intBase);
		builder.append(" + ");
		builder.append(intMod);
		builder.append("luck");
		builder.append(luckBase);
		builder.append(" + ");
		builder.append(luckMod);
		builder.append("HP ");
		builder.append(currentHP);
		builder.append(" / ");
		builder.append(baseHP);
		builder.append("Equipped Weapon: ");
		builder.append(equippedWeapon);
		builder.append("Equipped Armor: ");
		builder.append(equippedArmor);
		return builder.toString();
	}

}
