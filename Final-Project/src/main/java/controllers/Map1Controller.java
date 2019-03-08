

package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import enums.PotionType;
import enums.SpaceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import models.Map;
import models.Monster;
import models.Player;
import models.Potion;
import models.Space;

public class Map1Controller implements Initializable {
	@FXML
	GridPane map1Grid;
	@FXML
	Button button;
	
	Player player1;
	Monster monster1;
	boolean move;
	
	HBox itemBox;

	public void getItems() {
		itemBox = new HBox();
		player1.addItem(new Potion(PotionType.HEALING, 10, "Healing Potion", 15));
		
		for(int i = 0; i < player1.getItemBag().size(); i++) {
			Pane item = new Pane();
			item.setMaxSize(100, 100);
			Label label = new Label(player1.getItemBag().get(i).toString());
			if(player1.getItemBag().get(i).name.contains("Potion")) {
				Potion potion = (Potion) player1.getItemBag().get(i);
				label = new Label(potion.toString());
			}
			label.autosize();
			item.getChildren().add(label);
			itemBox.getChildren().add(item);
		}
		map1Grid.add(itemBox, 1, 1);
	}

	public void importPlayer() {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(getClass().getResource("/view/CharacterCreation.fxml"));
			loader.load();
			// Set up controller
			CharacterCreationController controller = loader.getController();
			player1 = controller.getPlayer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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
	public void initSpaces(Map map1)
	{
		//init safe spaces
		Image monImg = new Image("/view/grass.png");
		Image safeImg = new Image("/view/tile.png");
		//setting safe spaces
		for(int i = 0; i < 10; i++)
		{
			Space sp = new Space(193, 111, SpaceType.EMPTY, safeImg);
			map1.getSpaces().put(4 + "" + i, sp);
			map1Grid.add((Node)sp, 4, i);
		}
		//setting monster spaces left of path
		for(int i = 0; i < 4; i++)
		{
			for(int i2 = 0; i2 < 10; i2++)
			{
			Space sp = new Space(193, 111, SpaceType.MONSTER_ENCOUNTER, monImg);
			map1.getSpaces().put(i + "" + i2, sp);
			map1Grid.add(sp, i, i2);
			}
		}
		//setting monster spaces right of the path
		for(int i = 5; i < 10; i++)
		{
			for(int i2 = 0; i2 < 10; i2++)
			{
				Space sp = new Space(193, 111, SpaceType.MONSTER_ENCOUNTER, monImg);
				map1.getSpaces().put(i + "" + i2, sp);
				map1Grid.add(sp, i, i2);
			}
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		move = true;
		map1Grid.setOnKeyPressed(key -> {
			KeyCode keycode = key.getCode();
			if(move) {
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
				case I:
					getItems();
					break;
				default:
					break;
				}	
			}else if(keycode.equals(KeyCode.I)) {
				//TODO remove item node 
			}
		});
		HashMap<String , Space> spaces = new HashMap<>();
		Map map1 = new Map(spaces);
		Image monImg = new Image("file:graphics/character/big_demon_idle_anim_f0.png");
		monster1 = new Monster(6, 6, 193, 110, monImg, 1, 1, 1, 1, null);
		importPlayer();
		initSpaces(map1);
		map1Grid.add((Node) player1, player1.getCoordX(), player1.getCoordY());
		map1Grid.add((Node) monster1, monster1.getCoordX(), player1.getCoordY());
	}
}
