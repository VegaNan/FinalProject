package models;

import java.io.Serializable;

import enums.WeaponType;
import interfaces.Equippable;
import utilities.RNG;

public class Weapon extends Item implements Serializable, Equippable{
	private static final long serialVersionUID = 1L;
	protected int damage;
	protected WeaponType weaponType;

	public Weapon(WeaponType weaponType) {
		super(weaponType.toString(), 1);
		determineWeapon(weaponType);
	}

	public void determineWeapon(WeaponType weaponType) {
		int tmpDam;
		int tmpVal;
		switch(weaponType) {
		case LENE:
			tmpDam = RNG.generateInt(1, 3);
			tmpVal = RNG.generateInt(1, 15);
			break;
		case FLAMING_SWORD:
			tmpDam = RNG.generateInt(17, 20);
			tmpVal = RNG.generateInt(400, 490);
			break;
		case HEAVY_CLAYMORE:
			tmpDam = RNG.generateInt(13, 16);
			tmpVal = RNG.generateInt(300, 400);
			break;
		case POCKET_KNIFE:
			tmpDam = RNG.generateInt(4, 7);
			tmpVal = RNG.generateInt(30, 100);
			break;
		case SMALL_DAGGER:
			tmpDam = RNG.generateInt(8, 10);
			tmpVal = RNG.generateInt(100, 200);
			break;
		case SOLDIERS_SWORD:
			tmpDam = RNG.generateInt(11, 13);
			tmpVal = RNG.generateInt(200, 300);
			break;
		case WRATH_OF_THE_GODS:
			tmpDam = RNG.generateInt(21, 50);
			tmpVal = RNG.generateInt(490, 500);
			break;
		default:
			tmpDam = 1;
			tmpVal = 1;
			break;
		}
		setDamage(tmpDam);
		setValue(tmpVal);
	}

	public String getName() {
		return this.name;
	}

	public int getValue() {
		return value;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(getName());
		builder.append("\nWeapon Damage: ").append(getDamage())
		.append("\nValue=").append(getValue());
		return builder.toString();
	}
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void equip(Player player1) {
		player1.setEquippedWeapon(this);
	}
}
