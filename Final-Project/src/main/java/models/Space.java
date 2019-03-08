package models;

import enums.SpaceType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Space extends Rectangle{
	protected SpaceType St;
	
	public Space(int w, int h, SpaceType sT, Image img)
	{
		setWidth(w);
		setHeight(h);
		setFill(new ImagePattern(img));
	}
	public SpaceType getSt() {
		return St;
	}
	public void setSt(SpaceType st) {
		St = st;
	}
	
}
