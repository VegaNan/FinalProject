package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class MainMenuController implements Initializable {

	public void startNewGame(ActionEvent event) throws IOException {
		changeScene("/view/CharacterCreation.fxml", event);
	}

	public void loadGame(ActionEvent event) throws IOException {
		changeScene("/view/LoadGame.fxml", event);
		
		//Loads file selection through a GUI with filter for only Krebs files
		ExtensionFilter extensionFilter = new ExtensionFilter("Krebs Files", "*.krebs");
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().add(extensionFilter);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		File loadFile = fileChooser.showOpenDialog(window);
	}
	
	private void changeScene(String filename, ActionEvent event) throws IOException {
		// parent takes in the file
		Parent parent = FXMLLoader.load(getClass().getResource(filename));
		// makes new scene based on parent
		Scene scene = new Scene(parent);
		// takes in the stage of this class
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		// sets the scene
		window.setScene(scene);
		// displays the scene
		window.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
}
