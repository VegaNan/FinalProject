package models;

import java.io.Serializable;

import enums.MonsterType;
import javafx.scene.image.Image;

public class Boss extends Monster implements Serializable{

	public Boss(int x, int y, int w, int h, Image img, int strBase, int intBase, int luckBase, int level, String name, MonsterType monster) {
		super(x, y, w, h, img, strBase, intBase, luckBase, level, name, monster);

		// TODO Auto-generated constructor stub
	}

}
