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
import models.Player;

public class LoadGameController implements Initializable {

	public void back(ActionEvent event) throws IOException {
		changeScene("/view/MainMenu.fxml", event);
	}

	public void changeScene(String filename, ActionEvent event) throws IOException {
		// parent takes in the file
		Parent parent = FXMLLoader.load(getClass().getResource(filename));
		// makes new scene based on parent
		Scene scene = new Scene(parent);
		scene.getStylesheets().add("/view/application.css");
		// takes in the stage of this class
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		// sets the scene
		window.setScene(scene);
		// displays the scene
		window.show();
		
	}

	public void startGame(ActionEvent event) {
		Player loadedPlayer = MainMenuController.getLoadedPlayer();
		try {
			Map1Controller.importPlayer(loadedPlayer);
			changeScene(loadedPlayer.getMapLocation(), event);
			if(loadedPlayer.getMapLocation().contains("Map1")) {
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

}
