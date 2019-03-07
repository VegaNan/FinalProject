package controllers;

import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
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

public class Map1Controller implements Initializable, KeyListener{
	@FXML GridPane map1Grid;
	Player player1;
	public void keyPressed(KeyEvent e)
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
			System.out.println("HELLO NEW WORLD!");
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
	@Override
	public void keyPressed(java.awt.event.KeyEvent e) {
		System.out.println("hello world");		
	}
	@Override
	public void keyReleased(java.awt.event.KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("hello world");		
		
	}
	@Override
	public void keyTyped(java.awt.event.KeyEvent e) {
		System.out.println("hello world");		
		// TODO Auto-generated method stub
		
	}
}