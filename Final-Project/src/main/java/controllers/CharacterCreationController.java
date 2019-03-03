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
		Parent mainMenuParent;
		mainMenuParent = FXMLLoader.load(getClass().getClassLoader().getResource("MainMenu.fxml"));
		Scene scene = new Scene(mainMenuParent);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//		mainMenuParent.getStylesheets().add("application.css");
		window.setTitle("Main Menu");
		window.setScene(scene);
		window.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
}
