package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Start extends Application {
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Label Start = new Label();
			VBox root = new VBox();
			root.setAlignment(Pos.CENTER);	
			Start.setMinSize(2000, 200);
			
			Button StartButton = new Button("Start");
			Button startNew = new Button("Start New Game");
			Button loadGame = new Button("Load Game");
			Button quit = new Button("Quit");
			StartButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					VBox menuOptions = new VBox();

					Scene scene = new Scene(menuOptions, 1600, 800);
					menuOptions.setAlignment(Pos.CENTER);
					menuOptions.setPadding(new Insets(20, 80, 20, 80));
					menuOptions.getChildren().add(startNew);
					menuOptions.getChildren().add(loadGame);
					menuOptions.getChildren().add(quit);
					
					primaryStage.setScene(scene);
					primaryStage.setTitle("Main Menu");
					primaryStage.show();
				}
			});
			
			VBox switchBox = new VBox();
			switchBox.setAlignment(Pos.CENTER);
			switchBox.setPadding(new Insets(20, 80, 20, 80));
			switchBox.getChildren().add(StartButton);
			
			root.getChildren().addAll(Start, switchBox);
			
			Scene scene = new Scene(root, 1600, 800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Main Menu");
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}


}

