
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
import javafx.scene.paint.Color;
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

	private Player activePlayer;
	
	public Player getPlayer() {
		activePlayer = createPlayer();
		return activePlayer;
	}
	
	public void goBackMM(ActionEvent event) throws IOException {
		changeScene("/view/MainMenu.fxml", event);
	}

	public void startGame(ActionEvent event) throws IOException {
		
		loadMapScene("/view/Map1.fxml", event, getPlayer());
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
	
	private void loadMapScene(String filename, ActionEvent event, Player player) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(filename));
		
		// parent takes in the file
		Parent parent = loader.load();
		// makes new scene based on parent
		Scene scene = new Scene(parent);
		Map1Controller controller = loader.getController();
		
		// takes in the stage of this class
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		// sets the scene
		window.setScene(scene);
		// displays the scene
		window.show();
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
		Player player = new Player(5, 5, 100, 100, img, str, intelligence, luck, 1, playerName);
		System.out.println(player.toString());
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		leprechaunButton.setSelected(true);
	}
}
//
//package controllers;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.ResourceBundle;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.RadioButton;
//import javafx.scene.control.TextField;
//import javafx.scene.control.ToggleGroup;
//import javafx.scene.paint.Color;
//import javafx.stage.Stage;
//import models.Player;
//import utilities.RNG;
//
//public class CharacterCreationController implements Initializable {
//	@FXML 
//	private TextField characterNameField;
//	@FXML
//	private RadioButton knightButton;
//	@FXML
//	private RadioButton leprechaunButton;
//	@FXML
//	private RadioButton wizardButton;
//	@FXML
//	private ToggleGroup characterSelection;
//
//	private Player activePlayer;
//	
//	public Player getPlayer() {
//		activePlayer = createPlayer();
//		return activePlayer;
//	}
//	
//	public void goBackMM(ActionEvent event) throws IOException {
//		changeScene("/view/MainMenu.fxml", event);
//	}
//
//	public void startGame(ActionEvent event) throws IOException {
//		
//		loadMapScene("/view/Map1.fxml", event, getPlayer());
//	}
//
//	private void changeScene(String filename, ActionEvent event) throws IOException {
//		// parent takes in the file
//		Parent parent = FXMLLoader.load(getClass().getResource(filename));
//		// makes new scene based on parent
//		Scene scene = new Scene(parent);
//		// takes in the stage of this class
//		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//		// sets the scene
//		window.setScene(scene);
//		// displays the scene
//		window.show();
//	}
//	
//	private void loadMapScene(String filename, ActionEvent event, Player player) throws IOException {
//		FXMLLoader loader = new FXMLLoader();
//		loader.setLocation(getClass().getResource(filename));
//		
//		// parent takes in the file
//		Parent parent = loader.load();
//		// makes new scene based on parent
//		Scene scene = new Scene(parent);
//		Map1Controller controller = loader.getController();
//		
//		// takes in the stage of this class
//		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//		// sets the scene
//		window.setScene(scene);
//		// displays the scene
//		window.show();
//	}
//
//	
//	private Player createPlayer() {
//		String playerName = characterNameField.getText();
//		if(characterNameField.getText().trim().isEmpty()) {
//			playerName = "Buffalo";
//		}
//		String playerClass;
//		//Switch does not accept selected toggle for argument :(
//		if(characterSelection.getSelectedToggle().equals(knightButton)) {
//			playerClass = "Knight";
//		}
//		else if(characterSelection.getSelectedToggle().equals(wizardButton)) {
//			playerClass = "Wizard";
//		}
//		else {
//			playerClass = "Leprechaun";
//		}
//		int str = generateStat();
//		int intelligence = generateStat();
//		int luck = generateStat();
//		switch(playerClass) {
//		case "Knight":
//			str += 10;
//			break;
//		case "Wizard":
//			intelligence += 10;
//			break;
//		case "Leprechaun":
//			luck += 10;
//			break;
//		}
//
//		Player player = new Player(5, 5, 100, 100, Color.DARKMAGENTA, str, intelligence, luck, 1, playerName);
//		System.out.println(player.toString());
//		return player;
//	}
//	private int generateStat() {
//		int stat = RNG.generateInt(3, 18);
//		if(stat > 16) {
//			int secondRoll = RNG.generateInt(1, 6);
//			stat += secondRoll;
//			if(secondRoll == 6) {
//				stat += RNG.generateInt(1, 6);
//			}
//		}
//		return stat;
//	}
//
//	@Override
//	public void initialize(URL location, ResourceBundle resources) {
//		leprechaunButton.setSelected(true);
//	}
//}
//
