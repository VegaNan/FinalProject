package models;

public class Armor extends Item {
	protected int ArmorRating;
	protected int DamageReduction;
	protected int AgilityModifier;

	public Armor() {
		setArmorRating(17);
		setDamageReduction(10);
		setAgilityModifier(this.ArmorRating);
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
		} else if (armorRating >= 15 && armorRating < 17) {
			this.name = "Heavy Armor";
		} else {
			this.name = "Fabled Armor of Horan";
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

	public int getAgilityModifier() {
		return AgilityModifier;
	}

	public void setAgilityModifier(int armorRating) {
		switch (armorRating) {
		case 8:
			this.AgilityModifier = 0;
			break;
		case 9:
			this.AgilityModifier = -1;
			break;
		case 10:
			this.AgilityModifier = -1;
			break;
		case 11:
			this.AgilityModifier = -2;
			break;
		case 12:
			this.AgilityModifier = -3;
			break;
		case 13:
			this.AgilityModifier = -4;
			break;
		case 14:
			this.AgilityModifier = -5;
			break;
		case 15:
			this.AgilityModifier = -5;
			break;
		case 16:
			this.AgilityModifier = -6;
			break;
		case 17:
			this.AgilityModifier = -6;
			break;
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Armor \t[name=").append(name).append(", \t\t\tArmorRating=").append(ArmorRating)
				.append(", \tDamageReduction=").append(DamageReduction).append(", \tAgilityModifier=")
				.append(AgilityModifier).append(", \tvalue=").append(value).append("]");
		return builder.toString();
	}

}
