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
	
	public static void run() {
		launch();
	}
		
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setScene(startMenu(primaryStage));
			primaryStage.setTitle("Main Menu");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Scene startMenu(Stage primaryStage) {
		Label Start = new Label();
		VBox root = new VBox();
		root.setAlignment(Pos.CENTER);	
		Start.setMinSize(200, 200);
		Button StartButton = new Button("Start");
		StartButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mainMenu(primaryStage);
			}
		});
		
		VBox switchBox = new VBox();
		switchBox.setAlignment(Pos.CENTER);
		switchBox.setPadding(new Insets(20, 80, 20, 80));
		switchBox.getChildren().add(StartButton);
		root.getChildren().addAll(Start, switchBox);
		Scene scene = new Scene(root, 1600, 800);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		return scene;
	}
	
	public void mainMenu(Stage primaryStage) {
		Button startNew = new Button("Start New Game");
		Button loadGame = new Button("Load Game");
		Button quit = new Button("Quit");
		VBox menuOptions = new VBox();
		Scene scene = new Scene(menuOptions, 1600, 800);
		menuOptions.setAlignment(Pos.CENTER);
		menuOptions.setPadding(new Insets(20, 80, 20, 80));
		menuOptions.getChildren().add(startNew);
		menuOptions.getChildren().add(loadGame);
		menuOptions.getChildren().add(quit);
		startNew.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
			}
		});
		loadGame.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
			}
		});
		quit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
			}
		});
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Main Menu");
		primaryStage.show();
	}
	
	
	


}

