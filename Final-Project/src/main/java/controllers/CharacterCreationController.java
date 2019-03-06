package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CharacterCreationController implements Initializable {
	public void goBackMM(ActionEvent event) throws IOException {
		changeScene("/view/MainMenu.fxml", event);
	}
	public void startGame(ActionEvent event) throws IOException
	{
		changeScene("/view/Map1.fxml", event);
	}
	private void changeScene(String filename, ActionEvent event) throws IOException
	{
		Parent parent = FXMLLoader.load(getClass().getResource(filename));
		Scene scene = new Scene(parent);
		scene.getStylesheets().add("/view/application.css");
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
}
