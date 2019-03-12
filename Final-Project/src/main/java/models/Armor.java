package models;

import enums.ArmorType;
import interfaces.Equippable;
import utilities.RNG;

public class Armor extends Item implements Equippable{
	protected int armorRating;
	protected int damageReduction;
	protected ArmorType armorType;

	public Armor(ArmorType armorType) {
		super(armorType.toString(), 1);
		setArmorType(armorType);
		setArmorRating(armorType);
		setDamageReduction(getArmorRating());
		determineValue(armorType);
	}
	
	public ArmorType getArmorType() {
		return armorType;
	}
	
	public void setArmorType(ArmorType armorType) {
		this.armorType = armorType;
	}

	public String getName() {
		return name;
	}

	public void setArmorRating(ArmorType armorType) {
		switch(armorType) {
		case DEFAULT_ARMOR:
			setName("A basic tunic");
			armorRating = 1;
			break;
		case ROGUES_CLOAK:
			setName("Rogue's Cloak");
			armorRating = RNG.generateInt(1, 12);
			break;
		case SOLDIERS_ARMOR:
			setName("Soldier's Armor");
			armorRating = RNG.generateInt(11, 15);
			break;
		case HEAVY_ARMOR:
			setName("Heavy Armor");
			armorRating = RNG.generateInt(15, 17);
			break;
		case FABLED_ARMOR_OF_OOP:
			setName("Fabled Armor of OOP");
			armorRating = RNG.generateInt(17, 25);
			break;
		}
	}

	public int getValue() {
		return value;
	}

	public void determineValue(ArmorType armorType) {
		switch(armorType) {
		case DEFAULT_ARMOR:
			setValue(0);
			break;
		case ROGUES_CLOAK:
			setValue(400);
			break;
		case SOLDIERS_ARMOR:
			setValue(1000);
			break;
		case HEAVY_ARMOR:
			setValue(1400);
			break;
		case FABLED_ARMOR_OF_OOP:
			setValue(2000);
			break;
		}
	}

	public int getArmorRating() {
		return armorRating;
	}

	public int getDamageReduction() {
		return damageReduction;
	}

	public void setDamageReduction(int damageReducation) {
		damageReduction = damageReducation;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getName())
		.append("\nArmorRating: ").append(getArmorRating())
		.append("\nDamageReduction: ").append(getDamageReduction())
		.append("\nValue: ").append(getValue());
		return builder.toString();
	}

	@Override
	public boolean equip() {
		boolean equipped = false;
		return equipped;
	}

	@Override
	public String getDescription() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		return sb.toString();
	}

}
