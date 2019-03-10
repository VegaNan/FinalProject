
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import enums.MonsterType;
import enums.PotionType;
import enums.SpaceType;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Item;
import models.Map;
import models.Monster;
import models.Player;
import models.Potion;
import models.Space;
import utilities.RNG;

public class Map1Controller implements Initializable {
	@FXML
	GridPane map1Grid;
	@FXML
	Button button;
	HashMap<String, Space> spaces = new HashMap<>();
	Map map1 = new Map(spaces);
	Player player1;
	Monster monster1;
	boolean move;

	HBox itemBox;

	public void getItems() {
		Stage window = new Stage();
		Pane items = new AnchorPane();
		itemBox = new HBox();

		player1.addItem(new Potion(PotionType.HEALING, 10, "Healing Potion", 15));

		for (int i = 0; i < player1.getItemBag().size(); i++) {
			Pane item = new Pane();
			item.setMaxSize(100, 100);
			Label label = new Label(player1.getItemBag().get(i).toString());
			Button use = new Button("use");
			if (player1.getItemBag().get(i).name.contains("Potion")) {
				Potion potion = (Potion) player1.getItemBag().get(i);
				label = new Label(potion.toString());
				int index = i;
				use.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent arg0) {
						potion.use(player1);
						ArrayList<Item> itemBag = player1.getItemBag();
						itemBag.remove(index);
						player1.setItemBag(itemBag);
					}
				});
			}
			label.autosize();
			item.getChildren().add(label);
			item.getChildren().add(use);
			itemBox.getChildren().add(item);
		}

		items.getChildren().add(itemBox);
		items.autosize();
		Scene scene = new Scene(items);
		window.setScene(scene);
		window.sizeToScene();
		window.show();
	}

	// TODO open these in new window
	public void combatView(Monster monster) {
		Stage window = new Stage();
		Pane combat = new AnchorPane();
		combat.setPrefSize(700, 700);
		HBox stats = new HBox();
		HBox battle = new HBox();

		Button specialAttack = new Button("Special Attack");
		Button normalAttack = new Button("Normal Attack");
		Button defend = new Button("Defend");
		Button usePotion = new Button("Use Potion");
		Button runAway = new Button("Run Away");

		StringBuilder playersb = new StringBuilder();
		playersb.append(player1.getCurrentHP()).append(" / ").append(player1.getBaseHP());
		Label playerLabel = new Label(playersb.toString());
		playerLabel.setMinSize(300, 100);
		StringBuilder monstersb = new StringBuilder();
		monstersb.append(monster.getCurrentHP()).append(" / ").append(monster.getBaseHP());
		Label monsterLabel = new Label(monstersb.toString());
		monsterLabel.setMinSize(300, 100);

		stats.getChildren().add(playerLabel);
		stats.getChildren().add(monsterLabel);

		battle.getChildren().add(specialAttack);
		battle.getChildren().add(normalAttack);
		battle.getChildren().add(defend);
		battle.getChildren().add(usePotion);
		battle.getChildren().add(runAway);
		battle.autosize();

		specialAttack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				monster.takeDamage(player1.specialAttack());
			}
		});
		normalAttack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				monster.takeDamage(player1.attack());
				if(monster.getCurrentHP() == 0)
				{
					monster.setAlive(false);
				}
				else
				{
					player1.takeDamage(monster.attack());
				}
			}
		});
		defend.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				player1.defend();
			}
		});
		usePotion.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				getItems();
			}
		});
		runAway.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				//TODO quit combat w rand chance
				int chance = RNG.generateInt(1, 10) + 10;
				if(monster.getMonsterType().equals(MonsterType.KREBS)) {
					Stage bossWindow = new Stage();
					AnchorPane pane = new AnchorPane();
					pane.setPrefSize(70, 70);
					Label label = new Label("YOU CANNOT ESCAPE THE KREBS");
					HBox escape = new HBox();
					escape.getChildren().add(label);
					pane.getChildren().add(escape);
					Scene bossScene = new Scene(pane);
					bossWindow.setScene(bossScene);
					bossWindow.sizeToScene();
					bossWindow.show();
				}else {
					if(RNG.generateInt(1,  10) + player1.getLuckMod() > chance) {
						window.close();
					}
					else {
						Stage bossWindow = new Stage();
						AnchorPane pane = new AnchorPane();
						pane.setPrefSize(70, 70);
						Label label = new Label("You failed to escape the battle!");
						HBox escape = new HBox();
						escape.getChildren().add(label);
						pane.getChildren().add(escape);
						Scene bossScene = new Scene(pane);
						bossWindow.setScene(bossScene);
						bossWindow.sizeToScene();
						bossWindow.show();
					}
				}
			}
		});

		combat.getChildren().add(stats);
		combat.getChildren().add(battle);
		combat.autosize();
		Scene scene = new Scene(combat);
		window.setScene(scene);
		window.sizeToScene();
		window.show();

	}

	public void initSpaces(Map map1) {
		// init safe spaces
		Image monImg = new Image("/view/grass.png");
		Image safeImg = new Image("/view/tile.png");
		// setting safe spaces
		for (int i = 0; i < 10; i++) {
			Space sp = new Space(193, 111, SpaceType.EMPTY, safeImg);
			map1.getSpaces().put(4 + " " + i, sp);
			map1Grid.add((Node) sp, 4, i);
		}
		// setting monster spaces left of path
		for (int i = 0; i < 4; i++) {
			for (int i2 = 0; i2 < 10; i2++) {
				Space sp = new Space(193, 111, SpaceType.MONSTER_ENCOUNTER, monImg);
				map1.getSpaces().put(i + " " + i2, sp);
				map1Grid.add((Node)sp, i, i2);
			}
		}
		// setting monster spaces right of the path
		for (int i = 5; i < 10; i++) {
			for (int i2 = 0; i2 < 10; i2++) {
				Space sp = new Space(193, 111, SpaceType.MONSTER_ENCOUNTER, monImg);
				map1.getSpaces().put(i + " " + i2, sp);
				map1Grid.add((Node)sp, i, i2);
			}
		}
	}

	public void checkSpace() {
		// if space door
		Space sp = map1.getSpaces().get(player1.getCoordX() + " " + player1.getCoordY());
		if (sp.getSt() == SpaceType.MONSTER_ENCOUNTER) {
			combatView(monster1);
		} else if (sp.getSt() == SpaceType.BOSS) {
		}
		else if(sp.getSt() == SpaceType.DOOR)
		{
			
		}
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
		checkSpace();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		move = true;
		map1Grid.setOnKeyPressed(key -> {
			KeyCode keycode = key.getCode();
			if (move) {
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
				case UP:
					moveUp();
					break;
				case DOWN:
					moveDown();
					break;
				case LEFT:
					moveLeft();
					break;
				case RIGHT:
					moveRight();
					break;
				case I:
					getItems();
					break;
				default:
					break;
				}
			} else if (keycode.equals(KeyCode.I)) {
				// TODO remove item node
			}
		});
		Image monImg = new Image("file:graphics/character/big_demon_idle_anim_f0.png");
		monster1 = new Monster(6, 6, 193, 110, monImg, 1, 1, 1, 1, "Supreme", MonsterType.GENERIC_OGRE);
		importPlayer();
		initSpaces(map1);
		map1Grid.add((Node) player1, player1.getCoordX(), player1.getCoordY());
		map1Grid.add((Node) monster1, monster1.getCoordX(), player1.getCoordY());
	}
}
