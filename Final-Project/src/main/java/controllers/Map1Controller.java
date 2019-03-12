
package controllers;

import java.awt.TextField;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import enums.ArmorType;
import enums.MonsterType;
import enums.PotionType;
import enums.SpaceType;
import enums.WeaponType;
import interfaces.Equippable;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Armor;
import models.Item;
import models.Map;
import models.MapType;
import models.MiscItem;
import models.Monster;
import models.Player;
import models.Potion;
import models.Space;
import models.Vendor;
import models.Weapon;
import utilities.RNG;

public class Map1Controller implements Initializable {

	@FXML
	GridPane map1Grid;
	@FXML
	Button gameOverButton;
	@FXML
	Button doorButton;

	public HashMap<String, Space> spaces = new HashMap<>();
	public Map map1 = new Map(spaces);
	public static Player player1;
	public Monster monster1;
	public boolean move;

	HBox itemBox;

	public Map1Controller() {
	}

	public Map1Controller(Player player) {
		player1 = player;
		importPlayer(player);
	}

	public void getItems() {
		Stage window = new Stage();
		// Creates a pop up that allows user to view items
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
				item.setMaxSize(200, 200);
				Label label = new Label(player1.getItemBag().get(i).toString());
				Button use = new Button("Use");
				Potion potion = (Potion) player1.getItemBag().get(i);
				label = new Label(potion.toString());

				int index = i;
				use.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent arg0) {
						potion.use(player1);
						player1.getItemBag().remove(index);
						Stage window = (Stage) ((Node) arg0.getSource()).getScene().getWindow();
						window.close();
					}
				});
				label.autosize();
				item.getChildren().add(label);
				item.getChildren().add(use);
			}
			if (player1.getItemBag().get(i) instanceof Weapon) {
				Label label = new Label(player1.getItemBag().get(i).toString());
				Button equip = new Button("Equip");
				Weapon weapon = (Weapon) player1.getItemBag().get(i);
				label = new Label(weapon.toString());
				equip.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent arg0) {
						weapon.equip(player1);
					}
				});
			}
			if (player1.getItemBag().get(i) instanceof Armor) {
				Label label = new Label(player1.getItemBag().get(i).toString());
				Button equip = new Button("Equip");
				Armor armor = (Armor) player1.getItemBag().get(i);
				label = new Label(armor.toString());
				equip.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent arg0) {
						armor.equip(player1);
					}
				});
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
		playerLabel.setMinSize(500, 200);

		// Display monster stats
		StringBuilder monstersb = new StringBuilder(monster.getName()).append(" lvl ").append(monster.getLevel());
		monstersb.append("\n").append(monster.getCurrentHP()).append(" / ").append(monster.getBaseHP());
		Label monsterLabel = new Label(monstersb.toString());
		monsterLabel.setMinSize(500, 200);

		if (monster.getCurrentHP() < 1) {
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

		// Create buttons for options
		Button specialAttack = new Button("Special Attack");
		Button normalAttack = new Button("Normal Attack");
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
					MainMenuController.saveGame(player1.getName(), player1);
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
					MainMenuController.saveGame(player1.getName(), player1);
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
				if (checkDeath(monster)) {
					window.close();
					MainMenuController.saveGame(player1.getName(), player1);
				}
			}
		});
		usePotion.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				getItems();
//				stats.getChildren().clear();
//				stats.getChildren().add(updateStats(monster));
//				monsterTurn(monster);
//				stats.getChildren().clear();
//				stats.getChildren().add(updateStats(monster));

				if (checkDeath(monster)) {
					window.close();
					MainMenuController.saveGame(player1.getName(), player1);
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
					System.out.println("Monster ded");
					MainMenuController.saveGame(player1.getName(), player1);
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

	@FXML
	private RadioButton sword;
	@FXML
	private RadioButton knife;
	@FXML
	private RadioButton dagger;
	@FXML
	private ToggleGroup buySelection;
	@FXML
	private Button exit;
	@FXML
	private TextField sellItemField;
	@FXML
	private Label healthPotion;

	public void vendorView() {
		Stage window = new Stage();
		//window.setOnCloseRequest(event -> {
		//	event.consume();
		//});
		Pane vendor = new AnchorPane();
		Scene scene = new Scene(vendor);
		vendor.setPrefSize(700, 700);
		HBox playerItems = new HBox();
		HBox vendorItems = new HBox();

		String music = "/audio/BattleMusic.mp3";
		URL resource = getClass().getResource(music);
		Media media;
		try {
			media = new Media((resource).toURI().toString());
			MediaPlayer player = new MediaPlayer(media);
			player.play();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		// change to buyVendor.fxml

		Label playerLabel = new Label();
		Vendor ven = new Vendor();

		Label vendorLabel = new Label(ven.printItemBag(ven.getItemBag()));

		for(int i = 0; i < player1.getItemBag().size(); i++) {
			VBox itemBox = new VBox();
			Label item = new Label(player1.getItemBag().get(i).toString());
			item.setPrefSize(200, 200);
			Button Sell = new Button("Sell");
			int ii = i;
			Sell.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					player1.setMoney(player1.getMoney() + player1.getItemBag().get(ii).value);
					player1.removeItem(ii);					
					if (exit.getOnMousePressed() != null) {
						window.close();
					}
				}
			});
			itemBox.getChildren().add(item);
			itemBox.getChildren().add(Sell);
			playerItems.getChildren().add(itemBox);
		}
		
		int chance = 1;
		ArrayList<Item> itemBag = new ArrayList<>();
		int itemNum = RNG.generateInt(5, player1.getLevel() + 10);

		for (int i = 0; i < itemNum; i++) {
			String name = "Misc Item";
			Item item = new MiscItem(name, player1.getLevel());
			int itemType = RNG.generateInt(1, 5);
			int itemInt;
			switch (itemType) {
			case 1:

				// Selects random armor type (Has 1% chance of legendary armor
				ArmorType armorType = ArmorType.DEFAULT_ARMOR;
				chance = RNG.generateInt(0, 100);
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
				if (!potionType.equals(PotionType.HEALING)) {
					potionTypeInt = RNG.generateInt(1, 5);
				} else {
					potionTypeInt = (player1.getLevel() * 10) + RNG.generateInt(1, 10);
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
				item = new Potion(potionType, potionTypeInt, name, potionTypeInt);
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
		
		for(int i = 0; i < itemBag.size(); i++) {
			VBox itemBox = new VBox();
			Label item = new Label(itemBag.get(i).toString());
			item.setPrefSize(200, 200);
			Button Buy = new Button("Buy");
			itemBox.getChildren().add(item);
			itemBox.getChildren().add(Buy);
			int ii = i;
			Buy.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					player1.setMoney(player1.getMoney() - itemBag.get(ii).value);
					player1.addItem(itemBag.get(ii));
				}
			});
			vendorItems.getChildren().add(itemBox);
		}
		
		playerItems.getChildren().add(playerLabel);
		playerItems.setMinSize(700, 200);
		vendorItems.getChildren().add(vendorLabel);
		vendorItems.setMinSize(700, 200);
		vendor.getChildren().add(vendorItems);
		vendor.getChildren().add(playerItems);
		window.setScene(scene);
		window.show();

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
			// displaying the items
			itemDisplay.getChildren().add(label);
			itemBox.getChildren().add(itemDisplay);
		}
		
		for (Item loot : monster.getItemBag()) {
			player1.getItemBag().add(loot);
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

	public void initSpaces(Map map1) {
		// images
		Image monImg = new Image("/images/grass.png");
		Image safeImg = new Image("/images/tile.png");
		Image doorImg = new Image("/images/door.png");
		Image vendorImg = new Image("/images/vendor.png");
		// setting door space
		Space door = new Space(193, 111, SpaceType.DOOR, doorImg);
		map1.getSpaces().put(4 + " " + 0, door);
		map1Grid.add((Node) door, 4, 0);
		// setting safe spaces
		for (int i = 1; i < 10; i++) {
			Space sp = new Space(193, 111, SpaceType.EMPTY, safeImg);
			map1.getSpaces().put(4 + " " + i, sp);
			map1Grid.add((Node) sp, 4, i);
		}

		// setting monster spaces left of path
		for (int i = 0; i < 3; i++) {
			for (int i2 = 0; i2 < 10; i2++) {
				Space sp = new Space(193, 111, SpaceType.MONSTER_ENCOUNTER, monImg);
				map1.getSpaces().put(i + " " + i2, sp);
				map1Grid.add((Node) sp, i, i2);
			}
		}
		// setting monster top half col 3
		for (int i = 0; i < 5; i++) {
			Space sp = new Space(193, 111, SpaceType.MONSTER_ENCOUNTER, monImg);
			map1.getSpaces().put(3 + " " + i, sp);
			map1Grid.add((Node) sp, 3, i);
		}
		// setting monster bottom half col 3
		for (int i = 6; i < 10; i++) {
			Space sp = new Space(193, 111, SpaceType.MONSTER_ENCOUNTER, monImg);
			map1.getSpaces().put(3 + " " + i, sp);
			map1Grid.add((Node) sp, 3, i);
		}
		// vendor space
		Space vendor = new Space(193, 111, SpaceType.VENDOR, vendorImg);
		map1.getSpaces().put(3 + " " + 5, vendor);
		map1Grid.add((Node) vendor, 3, 5);
		// setting monster spaces right of the path
		for (int i = 5; i < 10; i++) {
			for (int i2 = 0; i2 < 10; i2++) {
				Space sp = new Space(193, 111, SpaceType.MONSTER_ENCOUNTER, monImg);
				map1.getSpaces().put(i + " " + i2, sp);
				map1Grid.add((Node) sp, i, i2);
			}
		}
		map1.getSpaces().remove(3, 6);
	}

	public void checkSpace() {

		// Creates combat if space is a monster_encounter space
		Space sp = map1.getSpaces().get(player1.getCoordX() + " " + player1.getCoordY());
		if (sp.getSt() == SpaceType.MONSTER_ENCOUNTER) {
			int randEn = RNG.generateInt(0, 10);
			if (randEn == 10) {
				move = false;
				combatView(createMonster());
			}
		} else if (sp.getSt() == SpaceType.BOSS) {
			// TODO implement boss combat
		}

		// Goes to next map if space is a door
		else if (sp.getSt() == SpaceType.DOOR) {
			player1.setMapLocation("/view/Map2.fxml");
			doorButton.fire();
		}

		// Allows user to interact with the vendor
		else if (sp.getSt() == SpaceType.VENDOR) {
			vendorView();
		}
	}

	public void nextMap(ActionEvent event) {
		player1.setCoordX(4);
		player1.setCoordY(9);
		player1.setMapLocation("/view/Map2.fxml");
		changeScene("/view/Map2.fxml", event);
		Map2Controller.importPlayer(player1);
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
		ArrayList<Item> itemBag = new ArrayList<>();
		int itemNum = RNG.generateInt(0, player1.getLevel() + 2);
		Monster monster = new Monster(player1.getCoordX(), player1.getCoordY(), 193, 110, monImg, 1, 1, 1, 1, null,
				monsterType);

		for (int i = 0; i < itemNum; i++) {
			String name = "Misc Item";
			Item item = new MiscItem(name, player1.getLevel());
			int itemType = RNG.generateInt(1, 5);
			int itemInt;
			switch (itemType) {
			case 1:

				// Selects random armor type (Has 1% chance of legendary armor
				ArmorType armorType = ArmorType.DEFAULT_ARMOR;
				chance = RNG.generateInt(0, 100);
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
				if (!potionType.equals(PotionType.HEALING)) {
					potionTypeInt = RNG.generateInt(1, 5);
				} else {
					potionTypeInt = (player1.getLevel() * 10) + RNG.generateInt(1, 10);
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
				item = new Potion(potionType, potionTypeInt, name, potionTypeInt);
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
		monster.setItemBag(itemBag);
		return monster;
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

			initSpaces(map1);
			map1Grid.add((Node) player1, player1.getCoordX(), player1.getCoordY());

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
		initSpaces(map1);
	}
}
