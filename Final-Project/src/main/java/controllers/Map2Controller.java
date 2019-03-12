
package controllers;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import enums.ArmorType;
import enums.MonsterType;
import enums.PotionType;
import enums.SpaceType;
import enums.WeaponType;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.*;
import utilities.RNG;

public class Map2Controller implements Initializable, Serializable{

	@FXML
	GridPane map2Grid;
	@FXML
	Button gameOverButton;
	@FXML
	Button doorButton;

	public HashMap<String, Space> spaces = new HashMap<>();
	public Map map2 = new Map(spaces);
	public static Player player1;
	public Monster monster1;
	public boolean move;

	HBox itemBox;

	public Map2Controller() {
	}
	
	public Map2Controller(Player player) {
		player1 = player;
	}
	
	public void getItems() {

		// Creates a pop up that allows user to view items
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

			// Adds potion to the view if user has a potion
			if (player1.getItemBag().get(i).name.contains("Potion")) {
				item.setMaxSize(100, 100);
				Label label = new Label(player1.getItemBag().get(i).toString());
				Button use = new Button("Use");
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

		// Monster uses random attack
		// If monster has enough energy and randNum is special attack, use special
		// attack
		if (randNum == 1 && monster.getCurrentEnergy() >= 5) {

			// If player chose to defend, reduce damage
			if (player1.getDefend()) {

				// Prevent player from gaining life if damage is negative
				player1.takeDamage(
						(monster.specialAttack() - player1.defend() > 0 ? monster.specialAttack() - player1.defend()
								: 0));
				player1.setDefend(false);
			} else {
				player1.takeDamage(monster.specialAttack());
			}

			// If monster doesn't have enough energy or special attack is not selected, use
			// normal attack
		} else {

			// If player chose to defend, reduce damage
			if (player1.getDefend()) {

				// Prevent player from gaining life if damage is negative
				player1.takeDamage((monster.attack() - player1.defend() > 0 ? monster.attack() - player1.defend() : 0));
				player1.setDefend(false);
			} else {
				player1.takeDamage(monster.attack());
			}
		}
	}

	// Updates stats to display correctly
	public HBox updateStats(Monster monster) {
		HBox stats = new HBox();
		// Display player stats
		StringBuilder playersb = new StringBuilder(player1.getName()).append(" lvl ").append(player1.getLevel())
				.append("\n HP").append(player1.getCurrentHP()).append(" / ").append(player1.getBaseHP())
				.append("\nEnergy: ").append(player1.getCurrentEnergy()).append(" / ").append(player1.getBaseEnergy());
		Label playerLabel = new Label(playersb.toString());
		playerLabel.setMinSize(300, 100);

		// Display monster stats
		StringBuilder monstersb = new StringBuilder(monster.getName());
		monstersb.append("\n").append(monster.getCurrentHP()).append(" / ").append(monster.getBaseHP());
		Label monsterLabel = new Label(monstersb.toString());
		monsterLabel.setMinSize(300, 100);
		
		if(monster.getCurrentHP() < 1) {
			monster.setAlive(false);
			player1.setXp(monster.getXPYield() + player1.getXp());
		}

		stats.getChildren().add(playerLabel);
		stats.getChildren().add(monsterLabel);
		stats.setMinSize(400, 400);
		return stats;
	}

	// Closes pop up windows after 3 seconds to prevent users from needing to
	// manually close windows
	public void popupCloseWindow(Stage window) {
		PauseTransition wait = new PauseTransition(Duration.seconds(3));
		wait.setOnFinished((e) -> {
			window.close();
			wait.playFromStart();
		});
		wait.play();
	}

	// Combat View
	public void combatView(Monster monster) {
		Stage window = new Stage();
		window.setOnCloseRequest(event -> {
			event.consume();
		});
		Pane combat = new AnchorPane();
		combat.setPrefSize(700, 700);
		HBox stats = updateStats(monster);
		HBox battle = new HBox();

		Button specialAttack = new Button("Special Attack");
		Button normalAttack;
		if(monster.getMonsterType().equals(MonsterType.KREBS)) {
			 normalAttack = new Button("Stab it in the face!");
		}
		else {
			 normalAttack = new Button("Normal Attack");
		}
		Button defend = new Button("Defend");
		Button usePotion = new Button("Use Potion");
		Button runAway = new Button("Run Away");

		// If player has enough energy, display special attack
		if (player1.getCurrentEnergy() >= 5) {
			battle.getChildren().add(specialAttack);
		}

		// Add default options
		battle.getChildren().add(normalAttack);
		battle.getChildren().add(defend);

		// If player has a potion, add option to use it
		if (player1.getItemBag().toString().contains("Potion")) {
			battle.getChildren().add(usePotion);
		}
		battle.getChildren().add(runAway);
		battle.autosize();

		// Use a special attack
		specialAttack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				// Prevents user from using special attack if they don't have enough energy
				if (player1.getCurrentEnergy() > 4) {
					monster.takeDamage(player1.specialAttack());
				} else {

					// Gives user helpful message informing them they cannot use a special attack
					Stage window = new Stage();
					AnchorPane pane = new AnchorPane();
					pane.setPrefSize(70, 70);
					Label error = new Label("You do not have enough energy to use a special attack!");
					HBox container = new HBox();
					container.getChildren().add(error);
					pane.getChildren().add(container);
					Scene scene = new Scene(pane);
					window.setScene(scene);
					window.sizeToScene();
					window.show();

					popupCloseWindow(window);
				}

				// Updates view
				stats.getChildren().clear();
				stats.getChildren().add(updateStats(monster));
				if (monster.isAlive()) {
					monsterTurn(monster);
				}
				stats.getChildren().clear();
				stats.getChildren().add(updateStats(monster));

				// Check if combat is over
				if (checkDeath(monster)) {
					window.close();
				}
			}
		});

		// Uses normal attack
		normalAttack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				// Player attacks monster
				monster.takeDamage(player1.attack());

				// Update stats
				stats.getChildren().clear();
				stats.getChildren().add(updateStats(monster));

				// If monster is alive, they get a turn
				if (monster.isAlive()) {
					monsterTurn(monster);
					stats.getChildren().clear();
					stats.getChildren().add(updateStats(monster));
				}

				// Check if combat is over
				if (checkDeath(monster)) {
					window.close();
				}
			}
		});

		// Handles defend option
		defend.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				// Sets player's defense variable
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

				if (checkDeath(monster)) {
					window.close();
				}
			}
		});
		runAway.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				// TODO quit combat w rand chance
				int chance = RNG.generateInt(1, 10) + 10;
				// Informs user you cannot escape from battles with Krebs
				if (monster.getMonsterType().equals(MonsterType.KREBS)) {
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

					popupCloseWindow(bossWindow);

				} else {
					if (RNG.generateInt(1, 10) + player1.getLuck() > chance) {
						Stage bossWindow = new Stage();
						AnchorPane pane = new AnchorPane();
						pane.setPrefSize(70, 70);
						Label label = new Label("You escaped!");
						HBox escape = new HBox();
						escape.getChildren().add(label);
						pane.getChildren().add(escape);
						Scene bossScene = new Scene(pane);
						bossWindow.setScene(bossScene);
						bossWindow.sizeToScene();
						bossWindow.show();
						popupCloseWindow(bossWindow);
						window.close();
						move = true;
					} else {

						// Gives user a message if they failed to escape
						Stage window = new Stage();
						AnchorPane pane = new AnchorPane();
						pane.setPrefSize(70, 70);
						Label label = new Label("You failed to escape the battle!");
						HBox escape = new HBox();
						escape.getChildren().add(label);
						pane.getChildren().add(escape);
						Scene bossScene = new Scene(pane);
						window.setScene(bossScene);
						window.sizeToScene();
						window.show();

						popupCloseWindow(window);
					}
				}
				stats.getChildren().clear();
				stats.getChildren().add(updateStats(monster));
				monsterTurn(monster);
				stats.getChildren().clear();
				stats.getChildren().add(updateStats(monster));

				if (checkDeath(monster)) {
					// window.close();
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

	public void vendorView() {
		Stage window = new Stage();
		window.setOnCloseRequest(event -> {
			event.consume();
		});
		Pane vendor = new AnchorPane();
		vendor.setPrefSize(700, 700);
		VBox playerItems = new VBox();
		VBox vendorItems = new VBox();

		Label vendorLabel = new Label();

		Label playerLabel = new Label(player1.printItemBag(player1.getItemBag()));

		Button Buy = new Button("Buy");
		Button Sell = new Button("Sell");
	}

	// If there is a death, window will close, if player won, dropLoot
	public boolean checkDeath(Monster monster) {
		boolean death = false;
		if (monster.getCurrentHP() <= 0) {
			monster.setAlive(false);
			dropLoot(monster);
			death = true;
			move = true;
		}
		if (player1.getCurrentHP() <= 0) {
			player1.setAlive(false);
			death = true;
			gameOverButton.fire();
		}
		return death;
	}

	public void gameOver(ActionEvent event) {
		changeScene("/view/GameOver.fxml", event);
	}

	public void dropLoot(Monster monster) {
		Stage window = new Stage();
		itemBox = new HBox();
		// loops throw monster's item bag and prints it to the window
		// Goes through monsterLoot and creates a box to tell user what they dropped
		for (int i = 0; i < monster.getItemBag().size(); i++) {
			Pane itemDisplay = new Pane();
			itemDisplay.setMinSize(200, 200);
			Label label = new Label(monster.getItemBag().get(i).toString());
			// if the item is a potion print it to the window
			if (itemDisplay.toString().contains("Potion")) {
				Potion potion = (Potion) monster.getItemBag().get(i);
				label = new Label(potion.toString());
			}
			// adding the items to the player's inventory
			for (Item loot : monster.getItemBag()) {
				player1.getItemBag().add(loot);
			}
			// displaying the items
			itemDisplay.getChildren().add(label);
			itemBox.getChildren().add(itemDisplay);
		}
		// if there is no loot
		// Informs user that monster dropped no loot
		if (monster.getItemBag().isEmpty()) {
			Label label = new Label("No loot was dropped");
			itemBox.getChildren().add(label);
		}
		// giving xp to player
		player1.setXp(player1.getXp() + monster.getXPYield());
		// checking if player levels up
		int prevLevel = player1.getLevel();
		player1.checkLevelUp(player1.getXp(), player1.getNextLevelXP());
		if (prevLevel < player1.getLevel()) {
		}
		Scene scene = new Scene(itemBox);
		window.setScene(scene);
		window.show();
	}

	public void initSpaces(Map map2) {

		// init safe spaces
		Image monImg = new Image("/images/grass.png");
		Image safeImg = new Image("/images/tile.png");
		Image doorImg = new Image("/images/door.png");
		Image wallImg = new Image("/images/wall.png");
		Image krebsinatorImg = new Image("/images/krebsinator.png");
		// setting up door
		Space door = new Space(193, 111, SpaceType.DOOR, doorImg);
		map2.getSpaces().put(4 + " " + 0, door);
		map2Grid.add((Node) door, 4, 0);
		// sets the krebsinator background
		Space krebsinatorBG = new Space(193, 111, SpaceType.EMPTY, safeImg);
		map2Grid.add(krebsinatorBG, 4, 1);
		// setting up the krebsinator
		Space krebsinator = new Space(193, 111, SpaceType.BOSS, krebsinatorImg);
		map2.getSpaces().put(4 + " " + 1, krebsinator);
		map2Grid.add(krebsinator, 4, 1);
		// setting safe spaces
		for (int i = 7; i < 10; i++) {
			Space emptySp = new Space(193, 111, SpaceType.EMPTY, safeImg);
			map2.getSpaces().put(4 + " " + i, emptySp);
			map2Grid.add((Node) emptySp, 4, i);
		}
		// setting monster spaces left of path
		for (int i = 0; i < 3; i++) {
			for (int i2 = 0; i2 < 10; i2++) {
				Space monSp = new Space(193, 111, SpaceType.MONSTER_ENCOUNTER, monImg);
				map2.getSpaces().put(i + " " + i2, monSp);
				map2Grid.add((Node) monSp, i, i2);
			}
		}
		// setting monster wall spaces right
		for (int i = 2; i < 10; i++) {
			Space monSp = new Space(193, 111, SpaceType.MONSTER_ENCOUNTER, monImg);
			map2.getSpaces().put(3 + " " + i, monSp);
			map2Grid.add(monSp, 3, i);
		}
		// setting monster wall spaces left
		for (int i = 2; i < 10; i++) {
			Space monSp = new Space(193, 111, SpaceType.MONSTER_ENCOUNTER, monImg);
			map2.getSpaces().put(5 + " " + i, monSp);
			map2Grid.add(monSp, 5, i);
		}
		// setting monster spaces right of the path
		for (int i = 6; i < 10; i++) {
			for (int i2 = 0; i2 < 10; i2++) {
				Space monSp = new Space(193, 111, SpaceType.MONSTER_ENCOUNTER, monImg);
				map2.getSpaces().put(i + " " + i2, monSp);
				map2Grid.add((Node) monSp, i, i2);
			}
		}
		// more monster spaces
		for (int i = 2; i < 8; i++) {
			Space monSp = new Space(193, 111, SpaceType.MONSTER_ENCOUNTER, monImg);
			map2.getSpaces().put(4 + " " + i, monSp);
			map2Grid.add(monSp, 4, i);
		}
		// wall spaces
		for (int i = 0; i < 2; i++) {
			Space wallSp = new Space(193, 111, SpaceType.BLOCK, wallImg);
			map2.getSpaces().put(3 + " " + i, wallSp);
			map2Grid.add(wallSp, 3, i);
		}
		for (int i = 0; i < 2; i++) {
			Space wallSp = new Space(193, 111, SpaceType.BLOCK, wallImg);
			map2.getSpaces().put(5 + " " + i, wallSp);
			map2Grid.add(wallSp, 5, i);
		}
	}

	public boolean isBlocked(int x, int y) {
		if (map2.getSpaces().get(x + " " + y).getSt() == SpaceType.BLOCK) {
			return true;
		} else {
			return false;
		}
	}

	public void checkSpace() {

		// Creates combat if space is a monster_encounter space
		Space sp = map2.getSpaces().get(player1.getCoordX() + " " + player1.getCoordY());
		if (sp.getSt() == SpaceType.MONSTER_ENCOUNTER) {
			int randEn = RNG.generateInt(0, 10);
			if (randEn == 10) {
				move = false;
				combatView(createMonster());
			}
		} else if (sp.getSt() == SpaceType.BOSS) {
			Boss boss = new Boss(0, 0, 0, 0, null, 0, 0, 0, 0, null, null);
			combatView(boss);
		}

		// Goes to next map if space is a door
		else if (sp.getSt() == SpaceType.DOOR) {
			player1.setMapLocation("/view/Map3.fxml");
			doorButton.fire();
		}

		// Allows user to interact with the vendor
		else if (sp.getSt() == SpaceType.VENDOR) {
			// TODO implement Vendor view
		}
	}

	public void nextMap(ActionEvent event) {
		changeScene("/view/Map3.fxml", event);
	}

	public Monster createMonster() {

		// Selects random monster type based on chance
		MonsterType monsterType = MonsterType.OGRE;
		int chance = RNG.generateInt(0, 100);

		// 50% chance of OGRE
		if (chance < 50) {
			monsterType = MonsterType.OGRE;
		}

		// 30% chance of WITCH
		else if (chance < 70) {
			monsterType = MonsterType.WITCH;
		}

		// 20% chance of DRAGON
		else if (chance < 90) {
			monsterType = MonsterType.DRAGON;
		}

		// 10% chance of ALPACA
		else if (chance < 100) {
			monsterType = MonsterType.SUPREME_EMPEROR_OVERLORD_ALPACA;
		}

		Image monImg = new Image("/images/enemy.png");
		Monster monster = new Monster(player1.getCoordX(), player1.getCoordY(), 193, 110, monImg, 1, 1, 1, 1, null, monsterType);
		monster.setItemBag(createLootBag());
		return monster;
	}
	
	public ArrayList<Item> createLootBag() {
		ArrayList<Item> itemBag = new ArrayList<>();
		int itemNum = RNG.generateInt(0, player1.getLevel() + 2);
		for (int i = 0; i < itemNum; i++) {
			String name = "Misc Item";
			Item item = new MiscItem(name, player1.getLevel());
			int itemType = RNG.generateInt(1, 5);
			switch (itemType) {
			case 1:

				// Selects random armor type (Has 1% chance of legendary armor
				ArmorType armorType = ArmorType.DEFAULT_ARMOR;
				int chance = RNG.generateInt(0, 100);
				if (chance < 2) {
					armorType = ArmorType.FABLED_ARMOR_OF_OOP;
				}

				// 48% chance of weakest armor
				else if (chance < 50) {
					armorType = ArmorType.ROGUES_CLOAK;
				}

				// 30% chance of moderate armor
				else if (chance < 80) {
					armorType = ArmorType.SOLDIERS_ARMOR;
				}

				// 20% chance of good armor
				else if (chance < 100) {
					armorType = ArmorType.HEAVY_ARMOR;
				}
				item = new Armor(armorType);
				break;
			case 2:
			case 3:
			case 4:
				// Selects random potion
				int potionTypeInt = RNG.generateInt(0, PotionType.class.getEnumConstants().length - 1);
				PotionType potionType = PotionType.class.getEnumConstants()[potionTypeInt];
				
				//Adds higher chance for potion to be healing by reselecting if it's not healing
				if(!potionType.equals(PotionType.HEALING)) {
					potionTypeInt = RNG.generateInt(0, PotionType.class.getEnumConstants().length - 1);
					potionType = PotionType.class.getEnumConstants()[potionTypeInt];
					if(!potionType.equals(PotionType.HEALING)) {
						potionTypeInt = RNG.generateInt(0, PotionType.class.getEnumConstants().length - 1);
						potionType = PotionType.class.getEnumConstants()[potionTypeInt];
						if(!potionType.equals(PotionType.HEALING)) {
							potionTypeInt = RNG.generateInt(0, PotionType.class.getEnumConstants().length - 1);
							potionType = PotionType.class.getEnumConstants()[potionTypeInt];
						}
					}
				}
				switch (potionType) {
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
				item = new Potion(potionType, RNG.generateInt(1, 10), name, 1);
				break;
			case 5:

				// Selects random weapon type
				// Has 1% chance of legendary weapon
				WeaponType weaponType = WeaponType.LENE;
				chance = RNG.generateInt(0, 100);
				if (chance < 2) {
					weaponType = WeaponType.WRATH_OF_THE_GODS;
				}

				// 49% chance of weakest weapon
				else if (chance < 50) {
					weaponType = WeaponType.POCKET_KNIFE;
				}

				// 20% chance of next highest weapon
				else if (chance < 70) {
					weaponType = WeaponType.SMALL_DAGGER;
				}

				// 10% chance of moderate weapon
				else if (chance < 80) {
					weaponType = WeaponType.SOLDIERS_SWORD;
				}

				// 15% chance of good weapon
				else if (chance < 95) {
					weaponType = WeaponType.HEAVY_CLAYMORE;
				}

				// 5% chance of good armor
				else if (chance < 100) {
					weaponType = WeaponType.FLAMING_SWORD;
				}
				item = new Weapon(weaponType);
				break;
			}
			itemBag.add(item);
		}
		return itemBag;
	}
	

	// Updates player1 variable with user input from CharacterCreationController
	public void importPlayer() {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(getClass().getResource("/view/CharacterCreation.fxml"));
			loader.load();

			// Set up controller
			CharacterCreationController controller = loader.getController();
			player1 = controller.getPlayer();
			System.out.println("this is the importer :)" + player1.toString());
			// TODO bug here?
			initSpaces(map2);
			player1.setCoordX(4);
			player1.setCoordY(8);
			map2Grid.add((Node) player1, player1.getCoordX(), player1.getCoordY());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 	
	public static void importPlayer(Player player) {
 		player1 = player;
		Image img = new Image("/images/knight.png");
 		player1.setImage(193, 110, img);
 	}

	// Movement methods
	public void moveLeft() {
		if (player1.getCoordX() != 0 && !isBlocked(player1.getCoordX()-1, player1.getCoordY())) {
			player1.setCoordX(player1.getCoordX() - 1);
			movePlayer();
		}
	}

	public void moveRight() {
		if (player1.getCoordX() != 9 && !isBlocked(player1.getCoordX()+1, player1.getCoordY())) {
			player1.setCoordX(player1.getCoordX() + 1);
			movePlayer();
		}
	}

	public void moveUp() {
		if (player1.getCoordY() != 0 && !isBlocked(player1.getCoordX(), player1.getCoordY()-1)) {
			player1.setCoordY(player1.getCoordY() - 1);
			movePlayer();
		}
	}

	public void moveDown() {
		if (player1.getCoordY() != 9 && !isBlocked(player1.getCoordX(), player1.getCoordY()+1)) {
			player1.setCoordY(player1.getCoordY() + 1);
			movePlayer();
		}
	}

	public void movePlayer() {
		map2Grid.getChildren().remove(player1);
		map2Grid.add((Node) player1, player1.getCoordX(), player1.getCoordY());
		checkSpace();
	}

	private void changeScene(String filename, ActionEvent event) {
		// parent takes in the file
		Parent parent;
		try {
			parent = FXMLLoader.load(getClass().getResource(filename));
			// makes new scene based on parent
			Scene scene = new Scene(parent);
			// takes in the stage of this class
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			// sets the scene
			window.setScene(scene);
			// displays the scene
			window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		move = true;

		// Sets up movement based on user keyPress
		map2Grid.setOnKeyPressed(key -> {
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

				// Allows user to open inventory
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
		Image monImg = new Image("/images/enemy.png");
		// Set up the map
		importPlayer();
	}
}
