package controllers;

import java.awt.Label;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.Main;

public class MainMenuController implements Initializable {

	public void startNewGame(ActionEvent event) throws IOException {

		changeScene("CharacterCreation.fxml", event);
	}

	public void loadGame(ActionEvent event) throws IOException {
		changeScene("LoadGame.fxml", event);
	}

	private void changeScene(String filename, ActionEvent event) throws IOException {
		Parent parent;
		parent = FXMLLoader.load(getClass().getClassLoader().getResource(filename));
		Scene scene = new Scene(parent);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
}
