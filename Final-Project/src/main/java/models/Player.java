package models;

import javafx.scene.paint.Color;

public class Player extends Character {
	protected int xp;
	protected int nextLevelXP;

	public Player(int x, int y, int w, int h, Color color, int strBase, int intBase, int luckBase, int level, String name) {
		super(strBase, intBase, luckBase, level, name);
		setTranslateX(x);
		setTranslateY(y);
	}

	public void moveLeft() {
		setTranslateX(getTranslateX() + 5);
	}

	public void moveRight() {
		setTranslateX(getTranslateX() - 5);
	}

	public void moveUp() {
		setTranslateY(getTranslateY() - 5);
	}

	public void moveDown() {
		setTranslateY(getTranslateY() + 5);
	}

	public void checkLevelUp(int xp, int nextLevelXP) {
		do {
			if (xp >= nextLevelXP) {
				this.level++;
				setNextLevelXP(this.level);
			}
		} while (xp >= nextLevelXP);
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public int getNextLevelXP() {
		return nextLevelXP;
	}

	public int setNextLevelXP(int level) {
		if (level == 1) {
			return 1000;
		} else {
			return (level * 1000) + setNextLevelXP(level - 1);
		}
	}

	@Override
	public int takeDamage(int damage) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int attack() {
		// TODO Auto-generated method stub
		return 0;
	}

}
