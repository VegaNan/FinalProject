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
	
	private static void menu() {
		boolean quit = false;
		do {
			String[] options = {"Start a New Campaign", "Load a Campaign", "Exit"};
			//Use GUI
			int menuSelection = 0;
			switch(menuSelection) {
			case 1:
				createGame();
				GameView();
				break;
			case 2:
				if(loadGame()) {
					GameView();
				}
				break;
			case 3:
				quit = true;
				break;
			}
		}while(!quit);
	}
	
	private static void createGame() {
		loadedGame = new Game();
		//Add game creation logic
	}
	
	private static String promptForValidInput(String parameter) {
		String input;
		boolean invalid = true;
		
		do {
			//Add logic here for GUI input
			input = "Valid";
			if(input == null ) {
				invalid = true;
				System.out.println("The "+ parameter + " cannot be null. \nTry again");
			}else if(input.isEmpty()) {
				invalid = true;
				System.out.println("The "+ parameter + " cannot be empty. \nTry again");
			}else if(input.trim().isEmpty()) {
				invalid = true;
				System.out.println("The "+ parameter + " cannot be whitespace.  \nTry again");
			}else {
				invalid = false;
			}
		}while(invalid);
		return input;
	}
	
	private static Player createPlayer(){
		String playerName = promptForValidInput("player name");
		String characterName = promptForValidInput("character name");
		//Add player logic for GUI
		int currentXP = 0;
		int level = 0;
		Player playerCreated = new Player();		
		return playerCreated;
	}
	
	private static void GameView() {
		System.out.println(loadedGame.toString());
		campaignViewMenu();
	}
	
	private static void campaignViewMenu() {
		boolean exit = false;
		do {
			String[] options = {"Change the title", "Add a Character","Remove a Character",
								"Add XP to a Character", "Save","Quit"};
			//Add logic for this for GUI
			int selection = 0;
			switch(selection) {
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
		}while (!exit);
	}
	

	private static boolean loadGame() {
		boolean complete = false;
		String path = promptForValidInput("path to the save file");
		File ppath = new File(path);
		if(ppath.exists()) {
			FileInputStream fileIn = null;
			ObjectInputStream objectIn = null;
			try {
			
				fileIn = new FileInputStream(path);
				objectIn = new ObjectInputStream(fileIn);
				loadedGame = (Game) objectIn.readObject();
				complete = true;
			}catch(FileNotFoundException fnf) {
				System.out.println("Path does not exist.");
			}catch(ClassNotFoundException cnfe) {
				System.out.println("Deserialization failed...");
				cnfe.printStackTrace();
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
		}
		return complete;
	}
	
	private static void saveGame() {
		String path = promptForValidInput("path");
		FileOutputStream fileOut = null;
		ObjectOutputStream objectOut = null;
		try {
			fileOut = new FileOutputStream(new File (path));
			objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(loadedGame);
			
		} catch(IOException ioe) {
			System.out.println("Serialization failed...");
			ioe.printStackTrace();
		}finally {
			try {
				objectOut.close();
				fileOut.close();
			}catch(IOException ioe){
				System.out.println("Either out stream could not close");
				ioe.printStackTrace();
			}
		}
	}
	
	
	

}

