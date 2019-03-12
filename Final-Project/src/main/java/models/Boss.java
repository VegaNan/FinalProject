package models;

import enums.MonsterType;
import javafx.scene.image.Image;

public class Boss extends Monster{

	public Boss(int x, int y, int w, int h, Image img, int strBase, int intBase, int luckBase, int level, String name, MonsterType monster) {
		super(x, y, w, h, img, strBase, intBase, luckBase, level, name, monster);
		int xp = level * 100 + ((getStrength() + getIntelligence()) / 2);
		setXPYield(xp);
	}

}
