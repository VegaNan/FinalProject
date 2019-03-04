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

public class LoadGameController implements Initializable {

	public void back(ActionEvent event) throws IOException {
		changeScene("MainMenu.fxml", event);
	}

	public void changeScene(String filename, ActionEvent event) throws IOException {
		Parent parent;
		parent = FXMLLoader.load(getClass().getClassLoader().getResource(filename));
		Scene scene = new Scene(parent);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setTitle("Main Menu");
		window.setScene(scene);
		window.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
