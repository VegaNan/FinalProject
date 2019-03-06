package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CharacterCreationController implements Initializable {
	@FXML 
	private TextField characterNameField;
	@FXML
	private RadioButton knightButton;
	@FXML
	private RadioButton leprechaunButton;
	@FXML
	private RadioButton wizardButton;
	@FXML
	private ToggleGroup characterSelection;

	
	public void goBackMM(ActionEvent event) throws IOException {
		changeScene("/view/MainMenu.fxml", event);
	}

	public void startGame(ActionEvent event) throws IOException {
		createPlayer();
		changeScene("/view/Map1.fxml", event);
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

	
	private void createPlayer() {
		String playerName = characterNameField.getText();
		String playerClass;
		//Switch does not accept selected toggle for argument :(
		if(characterSelection.getSelectedToggle().equals(knightButton)) {
			playerClass = "Knight";
		}
		else if(characterSelection.getSelectedToggle().equals(wizardButton)) {
			playerClass = "Wizard";
		}
		else {
			playerClass = "Leprechaun";
		}
		System.out.println(playerName + ": " + playerClass);
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
}
