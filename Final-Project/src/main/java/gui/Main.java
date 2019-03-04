package gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import enums.SceneType;
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
	
	private static Stage primaryStage;

	public static void run() {
		launch();
	}
		
	@Override
	public void start(Stage primaryStage) {
		//Make a method to ask for which scene, 
		//GameMaster.getScene
		//primaryStage.setScene(Scene);
		try {
			Group g = new Group();
			primaryStage.setResizable(false);
			primaryStage.setScene(startMenu(primaryStage));
			primaryStage.setTitle("Main Menu");
			primaryStage.show();
			this.primaryStage = primaryStage;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sceneController(SceneType sceneType) {
		if(sceneType == SceneType.MAINMENU) {
			primaryStage.setScene(mainMenu());
		}else if(sceneType == SceneType.MAP1) {
			System.out.println("map 1");
		}else if(sceneType == SceneType.MAP2) {
			System.out.println("map 2");
		}else if(sceneType == SceneType.MAP3) {
			System.out.println("map 3");
		}else if(sceneType == SceneType.MAP4) {
			System.out.println("map 4");
		}else if(sceneType == SceneType.NEWGAME) {
			primaryStage.setScene(startNewGame());
		}
		primaryStage.show();
	}
		
	public Scene startMenu(Stage primaryStage) {
		Label Start = new Label();
		VBox root = new VBox();
		root.setAlignment(Pos.CENTER);	
		Start.setMinSize(200, 200);
		Button StartButton = new Button("Start");
		Button testingMaps = new Button("Testing Maps");
		VBox switchBox = new VBox();
		switchBox.setAlignment(Pos.CENTER);
		switchBox.setPadding(new Insets(20, 80, 20, 80));
		switchBox.getChildren().add(StartButton);
		root.getChildren().addAll(Start, switchBox);
		root.getChildren().add(testingMaps);
		Scene scene = new Scene(root, 800, 800);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		StartButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				sceneController(SceneType.MAINMENU);
			}
		});
		
		testingMaps.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SpaceType[][] spaceType = new SpaceType[5][5];
				for(int j = 0; j < spaceType.length ; j++) {
					for(int i = 0; i < spaceType[j].length; i++) {
						spaceType[j][i] = SpaceType.EMPTY;
					}
				}
				loadMap(spaceType);	
			}
		});
		
		return scene;
	}
	
	public void map1() {
		SpaceType[][] spaceType = new SpaceType[10][10];
		for(int j = 0; j < spaceType.length ; j++) {
			for(int i = 0; i < spaceType[j].length; i++) {
				spaceType[j][i] = SpaceType.EMPTY;
			}
		}
		loadMap(spaceType);	
	}
	
	public Scene mainMenu() {
		
		Button startNew = new Button("Start New Game");
		Button loadGame = new Button("Load Game");
		Button quit = new Button("Quit");
		VBox menuOptions = new VBox();
		Scene scene = new Scene(menuOptions, 800, 800);
		menuOptions.setAlignment(Pos.CENTER);
		menuOptions.setPadding(new Insets(20, 80, 20, 80));
		menuOptions.getChildren().add(startNew);
		menuOptions.getChildren().add(loadGame);
		menuOptions.getChildren().add(quit);
		
		startNew.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				sceneController(SceneType.NEWGAME);
			}
		});
		loadGame.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				startGame();
			}
		});
		quit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.close();
			}
		});
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		return scene;
	}
	
	public Scene startNewGame() {
		Button start = new Button("Start");
		Button quit = new Button("Quit");
		VBox menuOptions = new VBox();
		Scene scene = new Scene(menuOptions, 800, 800);
		menuOptions.setAlignment(Pos.CENTER);
		menuOptions.setPadding(new Insets(20, 80, 20, 80));
		menuOptions.getChildren().add(start);
		menuOptions.getChildren().add(quit);
		return scene;
	}
	
	public Scene startGame() {
		ChoiceDialog mainMenu = new ChoiceDialog();
		VBox menuOptions = new VBox();
		Scene scene = new Scene(menuOptions, 800, 800);
		menuOptions.setAlignment(Pos.CENTER);
		menuOptions.setPadding(new Insets(20, 80, 20, 80));
		return scene;
	}
	
	public Scene loadMap(SpaceType[][] spaceTypes) {
		
		BorderPane root = new BorderPane();
		VBox vBox = new VBox();
		HBox hBox = new HBox();
		TilePane map = new TilePane();

		root.setTop(vBox);
		root.setLeft(hBox);
		root.setPadding(new Insets(10));
		root.setCenter(map);
		
	    vBox.setPadding(new Insets(15, 12, 15, 12));
	    vBox.setSpacing(10);
	    vBox.setStyle("-fx-background-color: #336699;");
	     
	    hBox.setPadding(new Insets(15, 12, 15, 12));
	    hBox.setSpacing(10);
	    hBox.setStyle("-fx-background-color: #336699;");
		
		ImageView pages[] = new ImageView[225];
		for (int i=0; i<pages.length; i++) {
			FileInputStream fileIn = null;
			try {
				fileIn = new FileInputStream("graphics/map"+".png");
				pages[i] = new ImageView(new Image(fileIn));
				pages[i].setFitHeight(50);
				pages[i].setFitWidth(50);
				pages[i].setPreserveRatio(true);
			}catch(FileNotFoundException fnf) {
				System.out.println("Path does not exist.");
			}catch(IOException ioe) {
				System.out.println("Either input stream could not close");
				ioe.printStackTrace();
			}finally {
				try {
				fileIn.close();
				}catch(IOException ioe) {
					System.out.println("Either input stream could not close");
					ioe.printStackTrace();
				}
			}
		    map.getChildren().add(pages[i]);  
		}
		
		map.setPadding(new Insets(5,5,5,5));;
		map.setPrefColumns(15);
		map.setPrefRows(15);
		map.setPrefTileHeight(50);
		map.setPrefTileWidth(50);
		map.setStyle("-fx-background-color: DAE6F3;");

		Scene scene = new Scene(root, 800, 800);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		return scene;	
	}

}

