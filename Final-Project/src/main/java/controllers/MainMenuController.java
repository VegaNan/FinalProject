package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Player;

public class MainMenuController implements Initializable {
	
	public void startNewGame(ActionEvent event) throws IOException {
		changeScene("/view/CharacterCreation.fxml", event);
	}

	public void loadGame(ActionEvent event) throws IOException{
		ActionEvent ourEvent = event;
		Player loadedPlayer = new Player();
		changeScene("/view/LoadGame.fxml", event);
		String path = "saves";
		File initialFile = new File(path);
		
		//Loads file selection through a GUI with filter for only Krebs files
		ExtensionFilter extensionFilter = new ExtensionFilter("Krebs Files", "*.krebs");
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select Your Save File");
		fileChooser.getExtensionFilters().add(extensionFilter);
		fileChooser.initialDirectoryProperty().set(initialFile);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		File loadFile = fileChooser.showOpenDialog(window);
		
		if(loadFile.exists()) {
			FileInputStream fileIn = null;
			ObjectInputStream objectIn = null;
			try {
				fileIn = new FileInputStream(loadFile);
				objectIn = new ObjectInputStream(fileIn);
				loadedPlayer = (Player)objectIn.readObject();
			}catch(FileNotFoundException fnf) {
				System.out.println("Path does not exist.");
			}catch(ClassNotFoundException cnfe) {
				System.out.println("Deserialization failed...");
				cnfe.printStackTrace();
			}catch(IOException ioe) {
				System.out.println("Either input stream could not close");
				ioe.printStackTrace();
			}finally {
				try {
				objectIn.close();
				fileIn.close();
				}catch(IOException ioe) {
					System.out.println("Either input stream could not close");
					ioe.printStackTrace();
				}
			}
		}
		//TODO need to start game with this player
		changeScene(loadedPlayer.getMapLocation(), ourEvent);
	}
	
	protected static void saveGame(String name, Player player) {
		File path= new File(name + ".krebs");
		FileOutputStream fileOut = null;
		ObjectOutputStream objectOut = null;
		try {
			fileOut = new FileOutputStream("saves/" + path);
			objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(player);
		} catch(IOException ioe) {
			System.out.println("Serialization failed...");
			ioe.printStackTrace();
		}finally {
			try {
				objectOut.close();
				fileOut.close();
			}catch(IOException ioe){
				System.out.println("Either out stream could not close");
				ioe.printStackTrace();
			}
		}
	}
	
	public void exit (ActionEvent event) throws IOException {
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.close();
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
	
	
	private static MediaPlayer mediaPlayer;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		String music = "/audio/MenuMusic.mp3";
		URL resource = getClass().getResource(music);
		Media media;
		try {
			media = new Media((resource).toURI().toString());
			mediaPlayer = new MediaPlayer(media);
			mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
			mediaPlayer.play();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
}
