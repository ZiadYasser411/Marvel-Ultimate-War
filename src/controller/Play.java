package controller;

import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Play extends Application{
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception{
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root,1000,562);
		//game icon
		Image icon = new Image(Files.newInputStream(Paths.get("images/bg and btn/marvel-icon-png-11.jpg")));
		//background of scene 1
		Image image = new Image(Files.newInputStream(Paths.get("images/bg and btn/try.jpg")));
		//image of 1 player
		Image onep = new Image(Files.newInputStream(Paths.get("images/bg and btn/1player.png")));
		//image of 2 players
		Image twop = new Image(Files.newInputStream(Paths.get("images/bg and btn/2players.png")));
		//setting icon
		primaryStage.getIcons().add(icon);
		//choosing buttons
		Button one = new Button();
		one.setTranslateX(500);
		one.setTranslateY(300);
		one.setMinSize(100, 100);
		one.setGraphic(new ImageView(onep));
		Button two = new Button();
		two.setTranslateX(500);
		two.setTranslateY(400);
		two.setMinSize(100, 100);
		two.setGraphic(new ImageView(twop));
		//1 player game
		one.setOnMouseEntered(e-> one.setOpacity(0.5));
		one.setOnMouseExited(e-> one.setOpacity(100));
		ComputerMode computer = new ComputerMode();
		one.setOnAction(e-> {
			try {
				computer.start(primaryStage);
			} catch (Exception e2) {
				System.out.println("Operation cannot be done");
			}
		});
		//2 players game
		two.setOnMouseEntered(e-> two.setOpacity(0.5));
		two.setOnMouseExited(e-> two.setOpacity(100));
		GUI g = new GUI();
		two.setOnAction(e-> {
			try {
				g.start(primaryStage);
			} catch (Exception e1) {
				System.out.println("Operation cannot be done");
			}
		});
		//game title
		primaryStage.setTitle("Marvel: Ultimate War");
		root.getChildren().addAll(new ImageView(image),one,two);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
