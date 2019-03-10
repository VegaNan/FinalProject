package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	
	static Map1Controller loadedGame;
	
	public void startNewGame(ActionEvent event) throws IOException {
		changeScene("/view/CharacterCreation.fxml", event);
	}

	public void loadGame(ActionEvent event) throws IOException {
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
				fileIn = new FileInputStream(path);
				objectIn = new ObjectInputStream(fileIn);
				loadedGame = (Map1Controller) objectIn.readObject();
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
	}
	
	private static void saveGame() {
		File path= new File("Default file.krebs");
		FileOutputStream fileOut = null;
		ObjectOutputStream objectOut = null;
		try {
			fileOut = new FileOutputStream(path);
			objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(loadedGame);
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
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
}
