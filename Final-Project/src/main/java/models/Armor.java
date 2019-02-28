package models;

import interfaces.Equippable;

public class Armor extends Item implements Equippable{
	protected int ArmorRating;
	protected int DamageReduction;

	public Armor() {
		setArmorRating(17);
		setDamageReduction(10);
		setName(this.ArmorRating, this.DamageReduction);
		setValue(this.name);
	}

	public String getName() {
		return this.name;
	}

	public void setName(int armorRating, int damageReduction) {
		if (armorRating < 12) {
			this.name = "Rogue's Cloak";
		} else if (armorRating > 11 && armorRating < 15) {
			this.name = "Soldier's Armor";
		} else if (armorRating >= 15) {
			if (armorRating == 17 && damageReduction == 10) {
				this.name = "Fabled Armor of Horan";
			} else {
				this.name = "Heavy Armor";
			}
		}
	}

	public int getValue() {
		return this.value;
	}

	public void setValue(String name) {
		switch (name) {
		case "Cloth Cloak":
			this.value = 400;
			break;
		case "Soldier's Armor":
			this.value = 1000;
			break;
		case "Heavy Armor":
			this.value = 1400;
			break;
		case "Fabled Armor of Horan":
			this.value = 1500;
			break;
		}
	}

	public int getArmorRating() {
		return ArmorRating;
	}

	public void setArmorRating(int armorRating) {
		ArmorRating = armorRating;
	}

	public int getDamageReduction() {
		return DamageReduction;
	}

	public void setDamageReduction(int damageReducation) {
		DamageReduction = damageReducation;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Armor \t").append(name).append(", \t\t\tArmorRating=").append(ArmorRating)
				.append(", \tDamageReduction=").append(DamageReduction).append(", \tAgilityModifier=")
				.append(", \tvalue=").append(value);
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
		return sb.toString();
	}

}
