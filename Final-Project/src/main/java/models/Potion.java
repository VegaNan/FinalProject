package models;

import java.io.Serializable;

import enums.PotionType;
import interfaces.Consumable;

public class Potion extends Item implements Consumable, Serializable{
	protected PotionType effect;
	protected int effectNum;

	public Potion(PotionType effect, int effectNum, String name, int value) {
		super(name, value);

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

	public void use(Character target) {
		switch (this.effect) {
		case LUCK:
			System.out.println(
					target.getName() + " used a " + this.name + " and gained " + this.effectNum + " points of Luck");
			target.setLuckBase(this.effectNum);
			break;
		case STRENGTH:
			System.out.println(target.getName() + " used a " + this.name + " and gained " + this.effectNum
					+ " points of Strength");
			target.setStrBase(this.effectNum);
			break;
		case INTELLIGENCE:
			System.out.println(target.getName() + " used a " + this.name + " and gained " + this.effectNum
					+ " points of Intelligence");
			target.setIntBase(this.effectNum);
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
		builder.append(name).append("\n").append(effect).append(" + ").append(effectNum)
				.append("\nvalue: ").append(value);

		return builder.toString();
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}
