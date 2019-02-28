package models;

import java.util.Arrays;

import models.Character;
import enums.PotionType;
import interfaces.Consumable;

public class Potion extends Item implements Consumable {
	protected PotionType effect;
	protected int effectNum;

	public Potion(PotionType effect, int effectNum) {
		setEffect(effect);
		setEffectNum(effectNum);
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
		case LUCK:
			this.name = "Dexterity Potion";
			break;
		case HEALING:
			this.name = "Dexterity Potion";
			break;
		case STRENGTH:
			this.name = "Strength Potion";
			break;
		case INTELLIGENCE:
			this.name = "Intelligence Potion";
			break;
		}
	}

	public PotionType getEffect() {
		return effect;
	}

	public void setEffect(PotionType effect) {
		this.effect = effect;
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
		case LUCK:
			System.out.println(
					target.getName() + " used a " + this.name + " and gained " + this.effectNum + " points of Luck");
			target.setLuckMod(this.effectNum);
			break;
		case STRENGTH:
			System.out.println(target.getName() + " used a " + this.name + " and gained " + this.effectNum
					+ " points of Strength");
			target.setStrMod(this.effectNum);
			break;
		case INTELLIGENCE:
			System.out.println(target.getName() + " used a " + this.name + " and gained " + this.effectNum
					+ " points of Intelligence");
			target.setIntMod(this.effectNum);
			break;
		case HEALING:
			System.out.println(
					target.getName() + " used a " + this.name + " and gained " + this.effectNum + " points of Healing");
			target.setCurrentHP(target.getBaseHP());
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Otherpotion [effect=").append(effect).append(", effectNum=").append(effectNum)
				.append(", value=").append(value).append(", name=").append(name).append(", items=")
				.append(Arrays.toString(items)).append("]");
		return builder.toString();
	}

	@Override
	public void use(java.lang.Character target) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}
