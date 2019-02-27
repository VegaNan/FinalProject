package models;

import java.util.Arrays;

import models.Character;
import enums.PotionType;
import interfaces.Consumable;

public class Potion extends Item implements Consumable {
	protected PotionType effect;
	protected int effectNum;

	public Potion() {
		setEffect(1);
		setEffectNum(50);
		setName(this.effect);
	}

	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getName() {
		return this.name;
	}

	public void setName(PotionType effect) {
		switch (effect) {
		case Dexterity:
			this.name = "Dexterity Potion";
			break;
		case Strength:
			this.name = "Strength Potion";
			break;
		case Intelligence:
			this.name = "Intelligence Potion";
			break;
		}
	}

	public PotionType getEffect() {
		return effect;
	}

	public void setEffect(int num) {
		switch (num) {
		case 0:
			this.effect = PotionType.Dexterity;
			break;
		case 1:
			this.effect = PotionType.Strength;
			break;
		case 2:
			this.effect = PotionType.Intelligence;
			break;
		}
	}

	public int getEffectNum() {
		return effectNum;
	}

	public void setEffectNum(int effectNum) {
		this.effectNum = effectNum;
	}

	@Override
	public void use(Character target) {
		switch (this.effect) {
		case Dexterity:
			System.out.println(target.getName() + " used a " + this.name + " and gained " + this.effectNum
					+ " points of Dexterity");
			target.setDexMod(this.effectNum);
			break;
		case Strength:
			System.out.println(target.getName() + " used a " + this.name + " and gained " + this.effectNum
					+ " points of Strength");
			target.setStrMod(this.effectNum);
			break;
		case Intelligence:
			System.out.println(target.getName() + " used a " + this.name + " and gained " + this.effectNum
					+ " points of Intelligence");
			target.setIntMod(this.effectNum);
			break;
		}
	}

	@Override
	public void getDescription() {
		System.out.println(this.name + ": increases " + this.effect + " by " + this.effectNum + " points");
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Otherpotion [effect=").append(effect).append(", effectNum=").append(effectNum)
				.append(", value=").append(value).append(", name=").append(name).append(", items=")
				.append(Arrays.toString(items)).append("]");
		return builder.toString();
	}

}
