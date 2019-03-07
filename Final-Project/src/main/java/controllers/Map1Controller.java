package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.Player;

public class Map1Controller implements Initializable {
	@FXML GridPane map1Grid;
	Player player1;
	public void keyPressed(KeyEvent e) throws IOException
	{
		System.out.println("Hello");
		KeyCode key = e.getCode();
		switch(key)
		{
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
	}
	public void moveLeft()
	{
		player1.setTranslateX(player1.getTranslateX()-1);
		movePlayer();
		System.out.println("moveLeft");
	}
	public void moveRight()
	{
		player1.setTranslateX(player1.getTranslateX()+1);
		movePlayer();
		System.out.println("moveRight");
	}
	public void moveUp()
	{
		player1.setTranslateY(player1.getTranslateY()-1);
		movePlayer();
		System.out.println("moveUp");
	}
	public void moveDown()
	{
		player1.setTranslateY(player1.getTranslateY()+1);
		movePlayer();
		System.out.println("moveDown");
	}
	public void movePlayer()
	{
		map1Grid.getChildren().remove(player1);
		map1Grid.add((Node)player1, (int)player1.getTranslateX(), (int)player1.getTranslateY());
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		player1  = new Player(5, 5, 100, 100, Color.BLUE, 1, 1, 1, 1);
		map1Grid.add((Node)player1, (int)player1.getTranslateX(), (int)player1.getTranslateY());		
	}
}
