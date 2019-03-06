package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.Player;

public class Map1Controller implements Initializable {
	@FXML GridPane map1Grid;
	@FXML Scene Map1;
	public void move(ActionEvent event)
	{
	String id = ((Node) event.getSource()).getId();
	
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Player player1 = new Player(100, 100, Color.BLUE, 1, 1, 1, 1);
		map1Grid.add(player1, 4, 9);
		
	}
}
