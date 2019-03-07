package models;

import java.util.Arrays;

import utilities.RNG;

public class Weapon extends Item {
	protected int Damage;

	public Weapon(int Damage, String name, int value) {
		super(name, value);
		setName(Damage);
		setValue(this.name);
	}

	public void setName(int damage) {
		if (damage < 11) {
			this.name = "Lene";
		} else if (damage < 21 && damage > 10) {
			this.name = "Pocket Knife";
		} else if (damage < 41 && damage > 20) {
			this.name = "Small Dagger";
		} else if (damage < 61 && damage > 40) {
			this.name = "Soldier's Sword";
		} else if (damage < 81 && damage > 60) {
			this.name = "Heavy Clamore";
		} else if (damage > 80 && damage < 100) {
			this.name = "Flaming Sword";
		} else {
			this.name = "Wrath of the Gods";
		}
	}

	public String getName() {
		return this.name;
	}

	public void setValue(String name) {
		switch (name) {
		case "Butterknife":
			this.value = RNG.generateInt(1, 30);
			break;
		case "Pocket Knife":
			this.value = RNG.generateInt(30, 100);
			break;
		case "Small Dagger":
			this.value = RNG.generateInt(100, 200);
			break;
		case "Soldier's Sword":
			this.value = RNG.generateInt(200, 300);
			break;
		case "Heavy Clamore":
			this.value = RNG.generateInt(300, 400);
			break;
		case "Flaming Sword":

			this.value = RNG.generateInt(490, 400);
			break;
		case "Wrath of the Gods":
			this.value = 500;
			break;
		}
	}

	public int getValue() {
		return this.value;
	}

	public int getDamage() {
		return Damage;
	}

	public void setDamage(int damage) {
		Damage = damage;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Weapon [Damage=");
		builder.append(Damage);
		builder.append(", value=");
		builder.append(value);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}

}
