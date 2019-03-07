package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CharacterCreatorController implements Initializable{
    public void loadSCene(Stage stage){
    	try {
    		Parent root = FXMLLoader.load(getClass().getResource("CharacterCreator.fxml"));
    		
    		Scene scene = new Scene(root);
    		
    		stage.setScene(scene);
    		stage.show();
    	} catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	
    }
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
