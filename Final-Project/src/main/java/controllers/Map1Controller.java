package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import models.Monster;
import models.Player;

public class Map1Controller implements Initializable {
	@FXML
	GridPane map1Grid;
	@FXML
	Button myButton;

	Player player1;
	Monster monster1;

	public void moveLeft() {
		if (player1.getCoordX() != 0) {
			player1.setCoordX(player1.getCoordX() - 1);
			movePlayer();
		}
	}

	public void moveRight() {
		if (player1.getCoordX() != 9) {
			player1.setCoordX(player1.getCoordX() + 1);
			movePlayer();
		}
	}

	public void moveUp() {
		if (player1.getCoordY() != 0) {
			player1.setCoordY(player1.getCoordY() - 1);
			movePlayer();
		}
	}

	public void moveDown() {
		if (player1.getCoordY() != 9) {
			player1.setCoordY(player1.getCoordY() + 1);
			movePlayer();
		}
	}

	public void movePlayer() {
		map1Grid.getChildren().remove(player1);
		map1Grid.add((Node) player1, player1.getCoordX(), player1.getCoordY());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		map1Grid.setOnKeyPressed(key -> {
			KeyCode keycode = key.getCode();
			switch (keycode) {
			case W:
				moveUp();
				break;
			case S:
				moveDown();
				break;
			case A:
				moveLeft();
				break;
			case D:
				moveRight();
				break;
			default:
				break;
			}
		});
		monster1 = new Monster(6, 6, 193, 110, Color.RED, 1, 1, 1, 1);
		player1 = new Player(5, 5, 193, 110, Color.BLUE, 1, 1, 1, 1);
		map1Grid.add((Node) player1, player1.getCoordX(), player1.getCoordY());
		map1Grid.add((Node) monster1, monster1.getCoordX(), player1.getCoordY());
	}
}