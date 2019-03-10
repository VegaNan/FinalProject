
package controllers;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import enums.MonsterType;
import enums.PotionType;
import enums.SpaceType;
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
import models.Armor;
import models.Item;
import models.Map;
import models.MapType;
import models.MiscItem;
import models.Monster;
import models.Player;
import models.Potion;
import models.Space;
import utilities.RNG;

public class Map1Controller extends MapType implements Initializable, Serializable{
	
	public Map1Controller(String saveName) {
		super(saveName, "/view/Map1.fxml");
	}
	

	@FXML
	GridPane map1Grid;
	@FXML
	Button button;
	public HashMap<String, Space> spaces = new HashMap<>();
	public Map map1 = new Map(spaces);
	public static Player player1;
	public Monster monster1;
	public boolean move;

	HBox itemBox;

	public void getItems() {
		Stage window = new Stage();
		Scene scene = new Scene(updateItems());
		window.setScene(scene);
		window.sizeToScene();
		window.show();
	}
	
	public Pane updateItems() {
		Pane items = new AnchorPane();
		itemBox = new HBox();
		for (int i = 0; i < player1.getItemBag().size(); i++) {
			Pane item = new Pane();
			if (player1.getItemBag().get(i).name.contains("Potion")) {
				item.setMaxSize(100, 100);
				Label label = new Label(player1.getItemBag().get(i).toString());
				Button use = new Button("use");
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
				label.autosize();
				item.getChildren().add(label);
				item.getChildren().add(use);
			}
			itemBox.getChildren().add(item);
		}
		items.getChildren().add(itemBox);
		return items;
	}	
	
	public void monsterTurn(Monster monster) {
		int randNum = RNG.generateInt(1, 2);
		if(randNum == 1 && monster.getCurrentEnergy() >= 5) {
			if(player1.getDefend()) {
				player1.takeDamage(monster.specialAttack() - player1.defend());
				player1.setDefend(false);
			}else {
				player1.takeDamage(monster.specialAttack());
			}
		}else if(randNum == 2) {
			if(player1.getDefend()) {
				player1.takeDamage(monster.attack() - player1.defend());
				player1.setDefend(false);
			}else {
				player1.takeDamage(monster.attack());
			}
		}
	}

	//Updates stats to display correctly
	public HBox updateStats(Monster monster) {
		HBox stats = new HBox();
		
		StringBuilder playersb = new StringBuilder(player1.getName());
		playersb.append("\n").append(player1.getCurrentHP()).append(" / ").append(player1.getBaseHP());
		Label playerLabel = new Label(playersb.toString());
		playerLabel.setMinSize(300, 100);
		
		StringBuilder monstersb = new StringBuilder(monster.getName());
		monstersb.append("\n").append(monster.getCurrentHP()).append(" / ").append(monster.getBaseHP());
		Label monsterLabel = new Label(monstersb.toString());
		monsterLabel.setMinSize(300, 100);
		
		stats.getChildren().add(playerLabel);
		stats.getChildren().add(monsterLabel);
		stats.setMinSize(400, 400);
		return stats;
	}
	
	//Combat View
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

		StringBuilder playersb = new StringBuilder(player1.getName());
		playersb.append("\n").append(player1.getCurrentHP()).append(" / ").append(player1.getBaseHP());
		Label playerLabel = new Label(playersb.toString());
		playerLabel.setMinSize(300, 100);
		StringBuilder monstersb = new StringBuilder(monster.getName());
		monstersb.append("\n").append(monster.getCurrentHP()).append(" / ").append(monster.getBaseHP());
		Label monsterLabel = new Label(monstersb.toString());
		monsterLabel.setMinSize(300, 100);

		stats.getChildren().add(playerLabel);
		stats.getChildren().add(monsterLabel);

		if(player1.getCurrentEnergy() >=5) {
			battle.getChildren().add(specialAttack);
		}
		battle.getChildren().add(normalAttack);
		battle.getChildren().add(defend);
		if(player1.getItemBag().toString().contains("Potion")) {
			battle.getChildren().add(usePotion);
		}
		battle.getChildren().add(runAway);
		battle.autosize();

		specialAttack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				monster.takeDamage(player1.specialAttack());
				stats.getChildren().clear();
				stats.getChildren().add(updateStats(monster));
				monsterTurn(monster);
				stats.getChildren().clear();
				stats.getChildren().add(updateStats(monster));
				
				if(checkDeath(monster)) {
					window.close();
				}
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
				stats.getChildren().clear();
				stats.getChildren().add(updateStats(monster));
				monsterTurn(monster);
				stats.getChildren().clear();
				stats.getChildren().add(updateStats(monster));

				if(checkDeath(monster)) {
					window.close();
				}
			}
		});
		defend.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				player1.defend();
				stats.getChildren().clear();
				stats.getChildren().add(updateStats(monster));
				monsterTurn(monster);
				stats.getChildren().clear();
				stats.getChildren().add(updateStats(monster));
			
			}
		});
		usePotion.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				getItems();
				stats.getChildren().clear();
				stats.getChildren().add(updateStats(monster));
				monsterTurn(monster);
				stats.getChildren().clear();
				stats.getChildren().add(updateStats(monster));
				
				if(checkDeath(monster)) {
					window.close();
				}
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
				stats.getChildren().clear();
				stats.getChildren().add(updateStats(monster));
				monsterTurn(monster);
				stats.getChildren().clear();
				stats.getChildren().add(updateStats(monster));
				
				if(checkDeath(monster)) {
					window.close();
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
	//If there is a death, window will close, if player won, dropLoot
	public boolean checkDeath(Monster monster) {
		boolean death = false;
		if(monster.getCurrentHP() <= 0){
			monster.setAlive(false);
			dropLoot(monster);
			death = true;
		}
		if(player1.getCurrentHP() <= 0) {
			player1.setAlive(false);
			death = true;
			dropLoot(monster);
		}
		return death;
	}
	
	public void dropLoot(Monster monster) {
		Stage window = new Stage();
		itemBox = new HBox();
		
		for (int i = 0; i < monster.getItemBag().size(); i++) {
			Pane item = new Pane();
			item.setMinSize(200, 200);
			Label label = new Label(monster.getItemBag().get(i).toString());
			if(item.toString().contains("Potion")) {
				Potion potion = (Potion) monster.getItemBag().get(i);
				label = new Label(potion.toString());
			}
			item.getChildren().add(label);
			itemBox.getChildren().add(item);
		}
		
		if(monster.getItemBag().isEmpty()) {
			Label label = new Label("No loot was dropped");
			itemBox.getChildren().add(label);
		}
		
	
		Scene scene = new Scene(itemBox);
		window.setScene(scene);
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
			combatView(createMonster());
		} else if (sp.getSt() == SpaceType.BOSS) {
		}
		else if(sp.getSt() == SpaceType.DOOR)
		{
			
		}
	}
	
	public Monster createMonster() {
		int randNum = RNG.generateInt(0, MonsterType.values().length);
		Image monImg = new Image("file:graphics/character/big_demon_idle_anim_f0.png");
		MonsterType monsterType = MonsterType.values()[randNum];
		ArrayList<Item> itemBag = new ArrayList<>();
		int itemNum = RNG.generateInt(0, player1.getLevel());
		Monster monster = new Monster(player1.getCoordX(), player1.getCoordY(), 193, 110, monImg, 1, 1, 1, 1, "Supreme", monsterType);
		
		for(int i =0; i < itemNum; i ++) {
			String name = "Misc Item";
			Item item = new MiscItem(name, player1.getLevel());
			int itemType = RNG.generateInt(1, 3);
			int itemInt = RNG.generateInt(monster.getLevel(), player1.getLevel());
			switch (itemType){
			case 1:
				name = "Armor";
				item = new Armor(name, itemInt);
				break;
			case 2:
				int potionTypeInt = RNG.generateInt(0, PotionType.values().length);
				PotionType potionType = PotionType.values()[potionTypeInt];
				switch(potionType) {
				case HEALING:
					name = "Healing Potion";
					break;
				case INTELLIGENCE:
					name = "Intelligence Potion";
					break;
				case LUCK:					
					name = "Luck Potion";
					break;
				case STRENGTH:
					name = "Strength Potion";
					break;
				}
				item = new Potion(potionType, itemInt, name, itemInt);
				break;
			case 3:
				break;
			}
			itemBag.add(item);
		}
		monster.setItemBag(itemBag);
		return monster;
	}
	

 	public void importPlayer() {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(getClass().getResource("/view/CharacterCreation.fxml"));
			loader.load();
			// Set up controller
			CharacterCreationController controller = loader.getController();
			player1 = controller.getPlayer();
			map1Grid.add((Node) player1, player1.getCoordX(), player1.getCoordY());
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
		initSpaces(map1);
		map1Grid.add((Node) monster1, monster1.getCoordX(), monster1.getCoordY());
	}
}
