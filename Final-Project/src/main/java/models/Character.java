package models;

import models.Armor;
import models.Weapon;

public abstract class Character {
	
	protected String name;
	protected int strBase;
	protected int intBase;
	protected int dexBase;
	protected int strMod = 0;
	protected int intMod = 0;
	protected int dexMod = 0;
	protected int baseHP;
	protected int currentHP;
	protected Weapon equippedWeapon;
	protected Armor equippedArmor;

	public Character() {
		setStrBase(1);
		setIntBase(1);
		setDexBase(1);
		setBaseHP(getStrength() * 10);
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

	public int getDexBase() {
		return dexBase;
	}

	public void setDexBase(int dexBase) {
		this.dexBase = dexBase;
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

	public int getDexMod() {
		return dexMod;
	}

	public void setDexMod(int dexmod) {
		this.dexMod = dexmod;
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

	public int getDexterity() {
		return this.dexBase + this.dexMod;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public abstract int takeDamage(int damage);

	public abstract int attack();

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(name).append("[strBase=").append(strBase).append(", intBase=").append(intBase)
				.append(", dexBase=").append(dexBase).append(", strMod=").append(strMod).append(", intMod=")
				.append(intMod).append(", dexMod=").append(dexMod).append(", baseHP=").append(baseHP)
				.append(", currentHP=").append(currentHP).append(", equippedWeapon=").append(equippedWeapon.name)
				.append(", equippedArmor=").append(equippedArmor.name).append("\noverall strength=")
				.append(getStrength()).append(", overall itelligence=").append(getIntelligence())
				.append(", overall dexterity=").append(getDexterity()).append("]");
		return builder.toString();
	}

}

}
