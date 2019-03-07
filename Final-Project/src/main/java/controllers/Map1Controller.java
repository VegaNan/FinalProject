package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import models.Player;

public class Map1Controller implements Initializable{
	@FXML private AnchorPane ap = new AnchorPane();
	@FXML private Button testing;
	Scene scene = ap.getScene();
	
	Player activePlayer;

	public void importPlayer(Player player) {
		activePlayer = player;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
}
