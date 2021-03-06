package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import models.Player;

public class GameMaster {

	static Game loadedGame;

	public static void run() {
		menu();
	}

	private static void menu() {
		boolean quit = false;
		do {
			String[] options = { "Start a New Game", "Load Game", "Exit" };
			// Use GUI
			int menuSelection = 0;
			switch (menuSelection) {
			case 1:
				createGame();
				GameView();
				break;
			case 2:
				loadGame();
				GameView();
				break;
			case 3:
				quit = true;
				break;
			}
		} while (!quit);
	}

	public static void createGame() {

		Player player = new Player(0, 0, 0, 0, null, 0, 0, 0, 0, null);

		loadedGame = new Game(player);
		// Add game creation logic
	}

	private static Player createPlayer() {
		String playerName;
		String characterName = null;
		// Add player logic for GUI
		int currentXP = 0;
		int level = 0;
		Player playerCreated = new Player(level, level, level, level, null, level, level, level, level, characterName);
		return playerCreated;
	}

	private static void GameView() {
		System.out.println(loadedGame.toString());
		campaignViewMenu();
	}

	private static void campaignViewMenu() {
		boolean exit = false;
		do {
			String[] options = { "Change the title", "Add a Character", "Remove a Character", "Add XP to a Character",
					"Save", "Quit" };
			// Add logic for this for GUI
			int selection = 0;
			switch (selection) {
			case 1:

				break;
			case 2:

				break;
			case 3:

				break;
			case 4:

				break;
			case 5:
				saveGame();
				break;
			case 6:
				exit = true;
				break;
			}
		} while (!exit);
	}

	public static String[] loadGame() {
		String path = "save";
		File ppath = new File(path);

		String[] options = { "" };

		if (ppath.exists()) {

			FileInputStream fileIn = null;
			ObjectInputStream objectIn = null;
			try {
				fileIn = new FileInputStream(path);
				objectIn = new ObjectInputStream(fileIn);
				loadedGame = (Game) objectIn.readObject();
			} catch (FileNotFoundException fnf) {
				System.out.println("Path does not exist.");
			} catch (ClassNotFoundException cnfe) {
				System.out.println("Deserialization failed...");
				cnfe.printStackTrace();
			} catch (IOException ioe) {
				System.out.println("Either input stream could not close");
				ioe.printStackTrace();
			} finally {
				try {
					objectIn.close();
					fileIn.close();
				} catch (IOException ioe) {
					System.out.println("Either input stream could not close");
					ioe.printStackTrace();
				}
			}
		}
		return options;
	}

	private static void saveGame() {
		String path = "path";
		FileOutputStream fileOut = null;
		ObjectOutputStream objectOut = null;
		try {
			fileOut = new FileOutputStream(new File(path));
			objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(loadedGame);

		} catch (IOException ioe) {
			System.out.println("Serialization failed...");
			ioe.printStackTrace();
		} finally {
			try {
				objectOut.close();
				fileOut.close();
			} catch (IOException ioe) {
				System.out.println("Either out stream could not close");
				ioe.printStackTrace();
			}
		}
	}

}
