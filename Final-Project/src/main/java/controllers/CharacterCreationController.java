
package controllers;

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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.Player;
import utilities.RNG;

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

	private static Player activePlayer;
	
	public Player getPlayer() {
		Player player = activePlayer;
		return player;
	}
	
	public void goBackMM(ActionEvent event) throws IOException {
		changeScene("/view/MainMenu.fxml", event);
	}

	public void startGame(ActionEvent event) {
		activePlayer = createPlayer();
		System.out.println("This is characterCreation player");
		System.out.println(activePlayer);
		System.out.println("This is the return of getPlayer");
		System.out.println(getPlayer());
		changeScene("/view/Map1.fxml", event);
		System.out.println("This is return of getPlayer after change scene");
		System.out.println(getPlayer());
		importPlayerData("/view/Map1.fxml");
	}

	private void changeScene(String filename, ActionEvent event) {
		// parent takes in the file
		Parent parent;
		try {
			parent = FXMLLoader.load(getClass().getResource(filename));
			// makes new scene based on parent
			Scene scene = new Scene(parent);
			// takes in the stage of this class
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			// sets the scene
			window.setScene(scene);
			// displays the scene
			window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void importPlayerData(String filename) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(filename));
		try {			
			loader.load();
			// makes new scene based on parent
			Map1Controller controller = loader.getController();
			controller.importPlayer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	private Player createPlayer() {
		String playerName = characterNameField.getText();
		if(characterNameField.getText().trim().isEmpty()) {
			playerName = "Buffalo";
		}
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
		int str = generateStat();
		int intelligence = generateStat();
		int luck = generateStat();
		switch(playerClass) {
		case "Knight":
			str += 10;
			break;
		case "Wizard":
			intelligence += 10;
			break;
		case "Leprechaun":
			luck += 10;
			break;
		}
		Image img = new Image("/view/knight.png");
		Player player = new Player(4, 9, 193, 110, img, str, intelligence, luck, 1, playerName);
		return player;
	}
	private int generateStat() {
		int stat = RNG.generateInt(3, 18);
		if(stat > 16) {
			int secondRoll = RNG.generateInt(1, 6);
			stat += secondRoll;
			if(secondRoll == 6) {
				stat += RNG.generateInt(1, 6);
			}
		}
		return stat;
	}
	
	private void setPlayer(Player player) {
		activePlayer = player;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		leprechaunButton.setSelected(true);
	}
}