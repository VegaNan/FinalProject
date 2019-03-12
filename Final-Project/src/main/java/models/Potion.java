package models;

import models.Character;
import enums.PotionType;
import interfaces.Consumable;
import javafx.scene.control.Label;

public class Potion extends Item implements Consumable {
	protected PotionType effect;
	protected int effectNum;

	public Potion(PotionType effect, int effectNum, String name, int value) {
		super(name, value);

		setEffect(effect);
		setEffectNum(effectNum);
		setName(getEffect());
		setValue(getEffectNum() * 5);
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
			setName("Potion of Luck");
			break;
		case HEALING:
			setName("Healing Potion");
			break;
		case STRENGTH:
			setName("Strength Potion");
			break;
		case INTELLIGENCE:
			setName("Intelligence Potion");
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
		switch (getEffect()) {
		case LUCK:
			System.out.println(target.getName() + " used a " + getName() + " and gained " + getEffectNum() + " points of Luck");
			target.setLuckBase(target.getLuckBase() + getEffectNum());
			break;
		case STRENGTH:
			System.out.println(target.getName() + " used a " + getName() + " and gained " + getEffectNum() + " points of Strength");
			target.setStrBase(target.getStrBase() + getEffectNum());
			break;
		case INTELLIGENCE:
			System.out.println(target.getName() + " used a " + getName() + " and gained " + getEffectNum() + " points of Intelligence");
			target.setIntBase(target.getIntBase() + getEffectNum());
			break;
		case HEALING:
			System.out.println(target.getName() + " used a " + getName() + " and gained " + getEffectNum() + " points of Healing");
			target.setCurrentHP(target.getCurrentHP() + getEffectNum());
			break;
		}
		//Sets effect num to 0 in case you try to use multiple times
		setEffectNum(0);
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
		StringBuilder sb = new StringBuilder("This potion will: ");
		switch(getEffect()) {
		case LUCK:
			sb.append("Boost the user's luck by ");
			break;
		case HEALING:
			sb.append("Heal the user by ");
			break;
		case STRENGTH:
			sb.append("Boost user's strength by ");
			break;
		case INTELLIGENCE:
			sb.append("Boost user intelligence by");
			break;
		}
		sb.append(getEffectNum()).append(" points");
		return null;
	}

}
