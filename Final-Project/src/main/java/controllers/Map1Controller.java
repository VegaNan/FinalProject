package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.Player;

public class Map1Controller {
	@FXML private Player player1 = new Player(300,300,100,100, Color.BLUE, 1,1,1,1);
	@FXML private AnchorPane ap = new AnchorPane();
	Scene scene = ap.getScene();
	scene.setOnKeyPressed(new EventHandler<KeyEvent>())
	{
		@Override
		public void handler(KeyEvent event)
		{
			switch(event.getCode())
			{
			case W:
				break;
			case S:
				break;
			case A:
				break;
			case D:
				break;
			}
		}
	}
}