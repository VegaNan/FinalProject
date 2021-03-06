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

public class GameWonController implements Initializable{

	public void returnMM(ActionEvent event)throws IOException
	{
		changeScene("/view/MainMenu.fxml", event);
	}
	public void quit(ActionEvent event)
	{
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.close();
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
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
