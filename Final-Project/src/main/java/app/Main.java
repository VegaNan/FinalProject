package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	private FXMLLoader loader = new FXMLLoader();

	public static void run() {
		launch();
	}

	@Override
	public void start(Stage primaryStage) {
		// Make a method to ask for which scene,
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			scene.getStylesheets().add("/view/application.css");
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public FXMLLoader getLoader() {
		return loader;
	}

	public void setLoader(FXMLLoader loader) {
		this.loader = loader;
	}

}
