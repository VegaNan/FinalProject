package gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import enums.SpaceType;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static void run() {
		launch();
	}
		
	@Override
	public void start(Stage primaryStage) {
		try {
			Group g = new Group();
			g.getChildren().add(new Button("New button"));
			
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
		Button testing = new Button("Testing");
		VBox menuOptions = new VBox();
		Scene scene = new Scene(menuOptions, 1600, 800);
		menuOptions.setAlignment(Pos.CENTER);
		menuOptions.setPadding(new Insets(20, 80, 20, 80));
		menuOptions.getChildren().add(startNew);
		menuOptions.getChildren().add(loadGame);
		menuOptions.getChildren().add(quit);
		menuOptions.getChildren().add(testing);
		
		startNew.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				startNewGame(primaryStage);
			}
		});
		loadGame.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				startGame(primaryStage);
			}
		});
		quit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
			}
		});
		
		testing.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				loadMap(primaryStage, new SpaceType[5][5]);	
			}
		});
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Main Menu");
		primaryStage.show();
	}
	
	public void startNewGame(Stage primaryStage) {
		Button start = new Button("Start");
		Button quit = new Button("Quit");
		VBox menuOptions = new VBox();
		Scene scene = new Scene(menuOptions, 1600, 800);
		menuOptions.setAlignment(Pos.CENTER);
		menuOptions.setPadding(new Insets(20, 80, 20, 80));
		menuOptions.getChildren().add(start);
		menuOptions.getChildren().add(quit);
	}
	
	public void startGame(Stage primaryStage) {
		
		ChoiceDialog mainMenu = new ChoiceDialog();

		VBox menuOptions = new VBox();
		Scene scene = new Scene(menuOptions, 1600, 800);
		menuOptions.setAlignment(Pos.CENTER);
		menuOptions.setPadding(new Insets(20, 80, 20, 80));

	}
	
	public void loadMap(Stage primaryStage, SpaceType[][] spaceTypes) {
		Label Start = new Label();
		
		BorderPane root = new BorderPane();
		VBox vBox = new VBox();
		HBox hBox = new HBox();
		TilePane map = new TilePane();
		map.setPadding(new Insets(5,0,5,0));
		map.setVgap(4);
		map.setHgap(4);
		map.setPrefColumns(2);
		map.setStyle("-fx-background-color: DAE6F3;");
		
		ImageView pages[] = new ImageView[8];
		for (int i=0; i<8; i++) {
			FileInputStream fileIn = null;
			ObjectInputStream objectIn = null;
			try {
				fileIn = new FileInputStream("graphics/chart_"+(i+1)+".png");
				new ImageView(new Image(fileIn));
				objectIn = new ObjectInputStream(fileIn);
			}catch(FileNotFoundException fnf) {
				System.out.println("Path does not exist.");
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
		     pages[i] = new ImageView();


		     map.getChildren().add(pages[i]);
		}
		
		root.getChildren().add(vBox);
		root.getChildren().add(hBox);
		root.getChildren().add(map);
		
		
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
		primaryStage.setScene(scene);
		primaryStage.setTitle("Testing");
		primaryStage.show();
		
	}

}

