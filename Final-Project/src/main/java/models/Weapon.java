package models;

import utilities.RNG;

public class Weapon extends Item {
	protected int DamageMin;
	protected int DamageMax;

	public Weapon() {
		setDamageMin(1);
		setDamageMax(RNG.generateInt((this.DamageMin + 10), this.DamageMin));
		setName(this.DamageMax, this.DamageMin);
		setValue(this.name);
	}

	public void setName(int DamageMax, int DamageMin) {
		if (DamageMax < 11) {
			this.name = "Butterknife";
		} else if (DamageMax < 21 && DamageMax > 10) {
			this.name = "Pocket Knife";
		} else if (DamageMax < 41 && DamageMax > 20) {
			this.name = "Small Dagger";
		} else if (DamageMax < 61 && DamageMax > 40) {
			this.name = "Soldier's Sword";
		} else if (DamageMax < 81 && DamageMax > 60) {
			this.name = "Heavy Clamore";
		} else if (DamageMax > 80) {
			if (DamageMax == 100 && DamageMin == 90) {
				this.name = "Wrath of the Gods";
			} else {
				this.name = "Flaming Sword";
			}
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

	public int getDamageMin() {
		return DamageMin;
	}

	public void setDamageMin(int damageMin) {
		DamageMin = damageMin;
	}

	public int getDamageMax() {
		return DamageMax;
	}

	public void setDamageMax(int damageMax) {
		DamageMax = damageMax;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Weapon \t[name=").append(name).append(", \t\t\tDamageMin=").append(DamageMin)
				.append(", \t\tDamageMax=").append(DamageMax).append(", \t\t\t\t\tvalue=").append(value).append("]");
		return builder.toString();
	}
}
