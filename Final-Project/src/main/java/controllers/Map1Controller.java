package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.Player;

public class Map1Controller implements Initializable{
	@FXML private AnchorPane ap = new AnchorPane();
	Scene scene = ap.getScene();

	public void move() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event)
			{
				switch(event.getCode()){
				case W:
					break;
				case S:
					break;
				case A:
					break;
				case D:
					break;
				default:
					break;
				}
			}

		});
	}

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}