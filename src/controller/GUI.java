package controller;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import engine.Game;
import engine.Player;
import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.abilities.Ability;
import model.abilities.AreaOfEffect;
import model.world.AntiHero;
import model.world.Champion;
import model.world.Condition;
import model.world.Cover;
import model.world.Direction;
import model.world.Hero;
import model.world.Villain;

public class GUI extends Application {
	Stage temp1;
	static Scene scene1;
	int count;
	String p1text = null;
	String n1 = null;
	String n2=null;
	String p2text = null;
	String[] images = new String[6];
	BorderPane root3;
	Game game;
	Button button1;
	Button button2;
	Button button3;
	Button button4;
	Button button5;
	Button button6;
	ArrayList<Cover> covers = new ArrayList<Cover>();
	ArrayList<Button> buttons = new ArrayList<Button>();
	ArrayList<Champion> players = new ArrayList<Champion>();
	Button cover1;
	Button cover2;
	Button cover3;
	Button cover4;
	Button cover5;
	Image coversHover;
	Image backbtn;
	ArrayList<Champion> turns = new ArrayList<Champion>();
	ArrayList<Champion> teams = new ArrayList<Champion>();
	String s = "";
	Label currChamp;
	Label trnOrder;
	Label ExceptionInfo;
	Player winner;
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception{
		Stage temp = new Stage();
		temp1 = new Stage();
		BorderPane root = new BorderPane();
		BorderPane root1 = new BorderPane();
		BorderPane root2 = new BorderPane();
		BorderPane rootWin = new BorderPane();
		root3 = new BorderPane();
		BorderPane rootChamps = new BorderPane();
		scene1 = new Scene(root,1000,562);
		Scene scene2 = new Scene(root1,1000,562);
		Scene scene3 = new Scene(root2,1000,562);
		Scene scene4 = new Scene(root3,1000,562);
		Scene winScene = new Scene(rootWin,1000,562);
		Button up;
		Button down;
		Button left;
		Button right;
		Button attack;
		Button cast1;
		Button cast2;
		Button cast3;
		Button cast4;
		Button leader;
		Button endturn;
		//game title
		primaryStage.setTitle("Marvel: Ultimate War");
		//game icon
		Image icon = new Image(Files.newInputStream(Paths.get("images/bg and btn/marvel-icon-png-11.jpg")));
		//background of scene 1
		Image img = new Image(Files.newInputStream(Paths.get("images/bg and btn/try.jpg")));
		//background of scene 2
		Image img2 = new Image(Files.newInputStream(Paths.get("images/bg and btn/scene2.jpg")));
		//background of scene 3
		Image img3 = new Image(Files.newInputStream(Paths.get("images/bg and btn/scene3.png")));
		//background of scene 4
		Image img4 = new Image(Files.newInputStream(Paths.get("images/bg and btn/battleScene background 2.png")));
		//cover image
		Image cover = new Image(Files.newInputStream(Paths.get("images/bg and btn/cover.png")));
		//buttons graphics	
		Image startbtn = new Image(Files.newInputStream(Paths.get("images/bg and btn/start.png")));
		backbtn = new Image(Files.newInputStream(Paths.get("images/bg and btn/back.png")));
		Image upbtn = new Image(Files.newInputStream(Paths.get("images/bg and btn/up.png")));
		Image downbtn = new Image(Files.newInputStream(Paths.get("images/bg and btn/down.png")));
		Image rightbtn = new Image(Files.newInputStream(Paths.get("images/bg and btn/right.png")));
		Image leftbtn = new Image(Files.newInputStream(Paths.get("images/bg and btn/left.png")));
		Image attackbtn = new Image(Files.newInputStream(Paths.get("images/bg and btn/attack.png")));
		Image castbtn1 = new Image(Files.newInputStream(Paths.get("images/bg and btn/cast ability 1.png")));
		Image castbtn2 = new Image(Files.newInputStream(Paths.get("images/bg and btn/cast ability 2.png")));
		Image castbtn3 = new Image(Files.newInputStream(Paths.get("images/bg and btn/cast ability 3.png")));
		Image castbtn4 = new Image(Files.newInputStream(Paths.get("images/bg and btn/cast ability 4.png")));
		Image endturnbtn = new Image(Files.newInputStream(Paths.get("images/bg and btn/end turn.png")));
		Image leaderbtn = new Image(Files.newInputStream(Paths.get("images/bg and btn/use leader ability.png")));
		Image Instructions = new Image(Files.newInputStream(Paths.get("images/bg and btn/instructions.jpg")));
		Image instruction = new Image(Files.newInputStream(Paths.get("images/bg and btn/instructionsbtn.png")));
		coversHover = new Image(Files.newInputStream(Paths.get("images/bg and btn/coversHover.jpg")));
		Image champsHover = new Image(Files.newInputStream(Paths.get("images/bg and btn/champsHover.jpg")));
		Image WinBg = new Image(Files.newInputStream(Paths.get("images/bg and btn/win scene.jpg")));
		//glow effects
	    //Blue
	    DropShadow Blue= new DropShadow();
	    Blue.setOffsetY(0f);
	    Blue.setOffsetX(0f);
	    Blue.setColor(Color.BLUE);
	    Blue.setWidth(30);
	    Blue.setHeight(30);
	    //Red
	    DropShadow Red= new DropShadow();
	    Red.setOffsetY(0f);
	    Red.setOffsetX(0f);
	    Red.setColor(Color.RED);
	    Red.setWidth(30);
	    Red.setHeight(30);
	    //White
	    DropShadow White= new DropShadow();
	    White.setOffsetY(0f);
	    White.setOffsetX(0f);
	    White.setColor(Color.WHITE);
	    White.setWidth(30);
	    White.setHeight(30);
		//start button in first scene
		Button btn = new Button();
		btn.setTranslateX(500);
	    btn.setTranslateY(500);
	    btn.setGraphic(new ImageView(startbtn));
	    btn.setOnMouseEntered(e-> {
	    	btn.setOpacity(0.5);
	    });
	    btn.setOnMouseExited(e->{
	    	btn.setOpacity(100);
	    });
	    //Grid in 1st scene
	    GridPane Grid = new GridPane();
	    Grid.setPadding(new Insets(0));
	    Grid.setVgap(50);
	    Grid.setHgap(50);
	    //grid in 2nd scene
	    GridPane Grid2 = new GridPane();
	    Grid2.setPadding(new Insets(50));
	    //grids in 3rd scene
	    GridPane Gridp1 =new GridPane();
	    Gridp1.setPadding(new Insets(80));
	    Gridp1.setHgap(10);
	    Gridp1.setTranslateX(0);
	    Gridp1.setTranslateY(175);
	    GridPane Gridp2 =new GridPane();
	    Gridp2.setPadding(new Insets(80));
	    Gridp2.setHgap(10);
	    Gridp2.setTranslateX(500);
	    Gridp2.setTranslateY(175);
	    //grid for info 2nd scene
	    GridPane Grid3 = new GridPane();
	    Grid3.setPadding(new Insets(50));
	    Label champion = new Label();
	    champion.setMinSize(600, 550);
	    champion.setTextFill(Color.web("#FFFFFF"));
	    champion.setFont(Font.loadFont(Files.newInputStream(Paths.get("fonts/MusticaPro-SemiBold.otf")), 12));
	    GridPane.setConstraints(champion, 0, 0);
	    //grid for teams
	    GridPane Grid4 = new GridPane();
	    Grid4.setPadding(new Insets(50));
	    Grid4.setVgap(100);
	    //grid for battle scene
	    GridPane Grid5 = new GridPane();
	    Grid5.setPadding(new Insets(0));
	    Grid5.setVgap(0);
	    Grid5.setHgap(-10);
	    //grid for curr champion label
	    GridPane Grid6 = new GridPane();
	    Grid5.setPadding(new Insets(10));
	    //grid for turn order
	    GridPane Grid7 = new GridPane();
	    //grid for winner text
	    GridPane Grid8 = new GridPane();
	    Label Winner = new Label();
	    Winner.setFont(Font.loadFont(Files.newInputStream(Paths.get("fonts/Akira Expanded Demo.otf")), 24));
	    Winner.setTextFill(Color.web("#FFFFFF"));
	    Winner.setMinSize(500, 200);
	    GridPane.setConstraints(Winner, 0, 0);
	    Grid8.getChildren().add(Winner);
	    Grid8.setTranslateX(400);
	    Grid8.setTranslateY(300);
	    //adding media
	    File battleFile = new File("videos/battlescenemusic.mp4");
	    File elseFile = new File("videos/elsemusic.mp4");
	    File winningFile = new File("videos/winningscene.mp4");
	    Media battlescenemusic = new Media(battleFile.toURI().toString());
	    Media elsemusic = new Media(elseFile.toURI().toString());
	    Media winningmusic = new Media(winningFile.toURI().toString());
	    MediaPlayer battlescenePlayer = new MediaPlayer(battlescenemusic);
	    MediaPlayer elsePlayer = new MediaPlayer(elsemusic);
	    MediaPlayer winningPlayer = new MediaPlayer(winningmusic);
	    MediaView battlesceneView = new MediaView();
	    MediaView elseView = new MediaView();
	    MediaView winningView = new MediaView();
	    elseView.setMediaPlayer(elsePlayer);
	    elsePlayer.setAutoPlay(true);
	    battlesceneView.setVisible(false);
	    elseView.setVisible(false);
	    winningView.setVisible(false);
	    //player 1 name
	    TextField player1= new TextField();
	    player1.setMinSize(500, 50);
	    player1.setPrefSize(500, 50);
	    player1.setPromptText("Player 1, please enter your name");
	    GridPane.setConstraints(player1, 0, 0);
		//player 2 name
		TextField player2= new TextField();
	    player2.setMinSize(250, 50);
		player2.setPrefSize(250, 50);
		player2.setPromptText("Player 2, please enter your name");
		GridPane.setConstraints(player2, 0, 1);
		Label p1 = new Label();
	    Label p2 = new Label();
	    p1.setMinSize(300, 25);
	    p1.setPrefSize(300, 25);
	    p1.setTextFill(Color.web("#FFFFFF"));
	    p1.setFont(Font.loadFont(Files.newInputStream(Paths.get("fonts/Akira Expanded Demo.otf")), 16));
	    p1.setAlignment(Pos.CENTER);
	    GridPane.setConstraints(p1, 0, 0);
	    p2.setMinSize(300, 25);
	    p2.setPrefSize(300, 25);
	    p2.setTextFill(Color.web("#FFFFFF"));
	    p2.setFont(Font.loadFont(Files.newInputStream(Paths.get("fonts/Akira Expanded Demo.otf")), 16));
	    p2.setAlignment(Pos.CENTER);
	    GridPane.setConstraints(p2, 0, 1);
	    
	    Label pl1 = new Label();
	    pl1.setTextFill(Color.web("#FFFFFF"));
		pl1.setFont(Font.loadFont(Files.newInputStream(Paths.get("fonts/Akira Expanded Demo.otf")), 16));
	    pl1.setMinSize(300,100);
	    pl1.setVisible(true);
	    pl1.setEffect(Blue);
	    Label pl2 = new Label();
		pl2.setTextFill(Color.web("#FFFFFF"));
		pl2.setFont(Font.loadFont(Files.newInputStream(Paths.get("fonts/Akira Expanded Demo.otf")), 16));
	    pl2.setMinSize(300,100);
	    pl2.setVisible(true);
	    pl2.setEffect(Red);
	    //add to grid of first scene
	    Grid.getChildren().addAll(player1,player2);
	    Grid.setTranslateX(250);
	    Grid.setTranslateY(250);
	    //scene 2 start button
	    Button btn2 = new Button();
		btn2.setTranslateX(850);
	    btn2.setTranslateY(500);
	    btn2.setGraphic(new ImageView(startbtn));
	    btn2.setOnMouseEntered(e-> {
	    	btn2.setOpacity(0.5);
	    });
	    btn2.setOnMouseExited(e->{
	    	btn2.setOpacity(100);
	    });
	    //back button scene2
	    Button btn3 = new Button();
	    btn3.setTranslateX(850);
	    btn3.setTranslateY(450);
	    btn3.setGraphic(new ImageView(backbtn));
	    btn3.setOnMouseEntered(e-> {
	    	btn3.setOpacity(0.5);
	    });
	    btn3.setOnMouseExited(e->{
	    	btn3.setOpacity(100);
	    });
	    //battle scene buttons
	    //instructions button
	    Button btn4 = new Button();
	    btn4.setTranslateX(850);
	    btn4.setTranslateY(400);
	    btn4.setGraphic(new ImageView(instruction));
	    btn4.setOnMouseClicked(e->{
	    	BorderPane rootins = new BorderPane();
			Scene insScene = new Scene(rootins,1000,562);
			GridPane covergrid = new GridPane();
			Button back = new Button();
			back.setGraphic(new ImageView(backbtn));
			back.setBackground(null);
			back.setMaxHeight(80);
			back.setMaxWidth(80);
			covergrid.getChildren().addAll(back);
			covergrid.setTranslateX(800);
			covergrid.setTranslateY(480);
			covergrid.setVgap(0);
			GridPane.setConstraints(back, 0, 0);
			rootins.getChildren().addAll(new ImageView(Instructions),covergrid);
			temp1.setAlwaysOnTop(true);
			temp1.setMaxHeight(562);
			temp1.setMaxWidth(1000);
			temp1.setMaximized(true);
	    	temp1.setScene(insScene);
	    	temp1.show();
	    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent arg0) {
					temp1.close();
				}
			});
	    });
	    //buttons' actions
	    btn3.setOnAction(e-> primaryStage.setScene(scene1));
	    btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				if(player1.getText()!="" && player2.getText()!="") {
					primaryStage.setScene(scene2);
					p1text= player1.getText();
					p2text= player2.getText();
					p1.setText(p1text);
					p2.setText(p2text);
					n1=player1.getText();
					n2=player2.getText();
					GridPane name1 = new GridPane();
					name1.setPadding(new Insets(20));
					name1.setTranslateX(65);
					name1.setTranslateY(-35);
					pl1.setText(n1);
					//GridPane.setConstraints(pl1, 0, 0);		    
					GridPane name2 = new GridPane();
					name2.setPadding(new Insets(20));
					name2.setTranslateX(790);
					name2.setTranslateY(-35);
					pl2.setText(n2);
					GridPane.setConstraints(pl2, 0, 0);
					name1.getChildren().add(pl1);
					name2.getChildren().add(pl2);
					root3.getChildren().addAll(name1,name2);
				} else {
					BorderPane rootcast = new BorderPane();
					Scene castScene = new Scene(rootcast,281,281);
					GridPane covergrid = new GridPane();
					Button back = new Button();
					back.setGraphic(new ImageView(backbtn));
					back.setBackground(null);
					covergrid.getChildren().addAll(ExceptionInfo,back);
					covergrid.setTranslateX(40);
					covergrid.setTranslateY(-100);
					covergrid.setVgap(0);
					GridPane.setConstraints(ExceptionInfo, 0, 0);
					GridPane.setConstraints(back, 0, 1);
					ExceptionInfo.setText("Enter valid names.");
					rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
					temp1.setAlwaysOnTop(true);
					temp1.setMaxHeight(281);
					temp1.setMaxWidth(281);
					temp1.setMaximized(true);
			    	temp1.setScene(castScene);
			    	temp1.show();
			    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent arg0) {
							temp1.close();
						}
					});
				}
			}    	
	    });
	    //scene 2 buttons' images importing
	    Image c1 = new Image(Files.newInputStream(Paths.get("images/scene2/cap1.png")));
	    ImageView capt = new ImageView(c1);
	    capt.setVisible(false);
	    Image c2 = new Image(Files.newInputStream(Paths.get("images/scene2/deadpool.png")));
	    ImageView deadp = new ImageView(c2);
	    deadp.setVisible(false);
	    Image c3 = new Image(Files.newInputStream(Paths.get("images/scene2/electro.png")));
	    ImageView elec = new ImageView(c3);
	    elec.setVisible(false);
	    Image c4 = new Image(Files.newInputStream(Paths.get("images/scene2/ghost rider.png")));
	    ImageView ghostr = new ImageView(c4);
	    ghostr.setVisible(false);
	    Image c5 = new Image(Files.newInputStream(Paths.get("images/scene2/hela.png")));
	    ImageView hel = new ImageView(c5);
	    hel.setVisible(false);
	    Image c6 = new Image(Files.newInputStream(Paths.get("images/scene2/hulk.png")));
	    ImageView hul = new ImageView(c6);
	    hul.setVisible(false);
	    Image c7 = new Image(Files.newInputStream(Paths.get("images/scene2/iceman.png")));
	    ImageView icem = new ImageView(c7);
	    icem.setVisible(false);
	    Image c8 = new Image(Files.newInputStream(Paths.get("images/scene2/ironman.png")));
	    ImageView ironm = new ImageView(c8);
	    ironm.setVisible(false);
	    Image c9 = new Image(Files.newInputStream(Paths.get("images/scene2/loki.png")));
	    ImageView lok = new ImageView(c9);
	    lok.setVisible(false);
	    Image c10 = new Image(Files.newInputStream(Paths.get("images/scene2/quicksilver.png")));
	    ImageView quicks = new ImageView(c10);
	    quicks.setVisible(false);
	    Image c11 = new Image(Files.newInputStream(Paths.get("images/scene2/spiderman.png")));
	    ImageView spiderm = new ImageView(c11);
	    spiderm.setVisible(false);
	    Image c12 = new Image(Files.newInputStream(Paths.get("images/scene2/strange.png")));
	    ImageView drs = new ImageView(c12);
	    drs.setVisible(false);
	    Image c13 = new Image(Files.newInputStream(Paths.get("images/scene2/thor.png")));
	    ImageView thorr = new ImageView(c13);
	    thorr.setVisible(false);
	    Image c14 = new Image(Files.newInputStream(Paths.get("images/scene2/venom.png")));
	    ImageView ven = new ImageView(c14);
	    ven.setVisible(false);
	    Image c15 = new Image(Files.newInputStream(Paths.get("images/scene2/yellow jacket.png")));
	    ImageView yellowj = new ImageView(c15);
	    yellowj.setVisible(false);
	    //scene 2 buttons "heheee"
	    	//cap
	    Button captainAmerica = new Button();
	    captainAmerica.setBackground(null);
	    captainAmerica.setGraphic(new ImageView(c1));
	    GridPane.setConstraints(captainAmerica, 0, 0);
	    	//deadpool
	    Button deadpool = new Button();
	    deadpool.setBackground(null);
	    deadpool.setGraphic(new ImageView(c2));
	    GridPane.setConstraints(deadpool, 0, 1);
	    	//electro
	    Button electro = new Button();
	    electro.setBackground(null);
	    electro.setGraphic(new ImageView(c3));
	    GridPane.setConstraints(electro, 0, 2);
	    	//ghostrider
	    Button ghostRider = new Button();
	    ghostRider.setBackground(null);
	    ghostRider.setGraphic(new ImageView(c4));
	    GridPane.setConstraints(ghostRider, 1, 0);
	    	//hela
	    Button hela = new Button();
	    hela.setBackground(null);
	    hela.setGraphic(new ImageView(c5));
	    GridPane.setConstraints(hela, 1, 1);
	    	//hulk
	    Button hulk = new Button();
	    hulk.setBackground(null);
	    hulk.setGraphic(new ImageView(c6));
	    GridPane.setConstraints(hulk, 1, 2);
	    	//iceman
	    Button iceMan = new Button();
	    iceMan.setBackground(null);
	    iceMan.setGraphic(new ImageView(c7));
	    GridPane.setConstraints(iceMan, 2, 0);
	    	//ironMan
	    Button ironMan = new Button();
	    ironMan.setBackground(null);
	    ironMan.setGraphic(new ImageView(c8));
	    GridPane.setConstraints(ironMan, 2, 1);
	    	//loki
	    Button loki = new Button();
	    loki.setBackground(null);
	    loki.setGraphic(new ImageView(c9));
	    GridPane.setConstraints(loki, 2, 2);
    		//quickSilver
	    Button quickSilver = new Button();
	    quickSilver.setBackground(null);
	    quickSilver.setGraphic(new ImageView(c10));
	    GridPane.setConstraints(quickSilver, 3, 0);
	    	//spiderMan
	    Button spiderMan = new Button();
	    spiderMan.setBackground(null);
	    spiderMan.setGraphic(new ImageView(c11));
	    GridPane.setConstraints(spiderMan, 3, 1);
			//drStrange
	    Button drStrange = new Button();
	    drStrange.setBackground(null);
	    drStrange.setGraphic(new ImageView(c12));
	    GridPane.setConstraints(drStrange, 3, 2);
	    	//thor
	    Button thor = new Button();
	    thor.setBackground(null);
	    thor.setGraphic(new ImageView(c13));
	    GridPane.setConstraints(thor, 4, 0);
	    	//venom
	    Button venom = new Button();
	    venom.setBackground(null);
	    venom.setGraphic(new ImageView(c14));
	    GridPane.setConstraints(venom, 4, 1);
	    	//yellowJacket
	    Button yellowJacket = new Button();
	    yellowJacket.setBackground(null);
	    yellowJacket.setGraphic(new ImageView(c15));
	    GridPane.setConstraints(yellowJacket, 4, 2);
	    //players' teams' buttons    
	    Button b1 = new Button();
	    b1.setBackground(null);
	    GridPane.setConstraints(b1, 0, 0);
	    Button b2 = new Button();
	    b2.setBackground(null);
	    GridPane.setConstraints(b2, 1, 0);
	    Button b3 = new Button();
	    b3.setBackground(null);
	    GridPane.setConstraints(b3, 2, 0);
	    Button b4 = new Button();
	    b4.setBackground(null);
	    GridPane.setConstraints(b4, 0, 0);
	    Button b5 = new Button();
	    b5.setBackground(null);
	    GridPane.setConstraints(b5, 1, 0);
	    Button b6 = new Button();
	    b6.setBackground(null);
	    GridPane.setConstraints(b6, 2, 0);

	    button1 = new Button();
	    button1.setBackground(null);
	    button1.setMaxWidth(80);
	    button1.setMaxHeight(80);
	    button1.setTranslateX(415);
	    button1.setTranslateY(451);
	    button1.setEffect(Blue);
	    
	    button2 = new Button();
	    button2.setBackground(null);
	    button2.setMaxWidth(80);
	    button2.setMaxHeight(80);
	    button2.setTranslateX(500);
	    button2.setTranslateY(451);
	    button2.setEffect(Blue);
	    
	    button3 = new Button();
	    button3.setBackground(null);
	    button3.setMaxWidth(80);
	    button3.setMaxHeight(80);
	    button3.setTranslateX(585);
	    button3.setTranslateY(451);
	    button3.setEffect(Blue);
	    
	    button4 = new Button();
	    button4.setBackground(null);
	    button4.setMaxWidth(80);
	    button4.setMaxHeight(80);
	    button4.setTranslateX(415);
	    button4.setTranslateY(111);
	    button4.setEffect(Red);
	    
	    button5 = new Button();
	    button5.setBackground(null);
	    button5.setMaxWidth(80);
	    button5.setMaxHeight(80);
	    button5.setTranslateX(500);
	    button5.setTranslateY(111);
	    button5.setEffect(Red);
	    
	    button6 = new Button();
	    button6.setBackground(null);
	    button6.setMaxWidth(80);
	    button6.setMaxHeight(80);
	    button6.setTranslateX(585);
	    button6.setTranslateY(111);
	    button6.setEffect(Red);
	    
	    up = new Button();
	    up.setGraphic(new ImageView(upbtn));
	    up.setTranslateX(857);
	    up.setTranslateY(446);
	    up.setOnMouseEntered(e-> {
	    	up.setOpacity(0.5);
	    });
	    up.setOnMouseExited(e->{
	    	up.setOpacity(100);
	    });
	    down = new Button();
	    down.setGraphic(new ImageView(downbtn));
	    down.setTranslateX(857);
	    down.setTranslateY(481);
	    down.setOnMouseEntered(e-> {
	    	down.setOpacity(0.5);
	    });
	    down.setOnMouseExited(e->{
	    	down.setOpacity(100);
	    });
	    right = new Button();
	    right.setGraphic(new ImageView(rightbtn));
	    right.setTranslateX(892);
	    right.setTranslateY(481);
	    right.setOnMouseEntered(e-> {
	    	right.setOpacity(0.5);
	    });
	    right.setOnMouseExited(e->{
	    	right.setOpacity(100);
	    });
	    left = new Button();
	    left.setGraphic(new ImageView(leftbtn));
	    left.setTranslateX(822);
	    left.setTranslateY(481);
	    left.setOnMouseEntered(e-> {
	    	left.setOpacity(0.5);
	    });
	    left.setOnMouseExited(e->{
	    	left.setOpacity(100);
	    });
	    attack = new Button();
	    attack.setGraphic(new ImageView(attackbtn));
	    attack.setTranslateX(857);
	    attack.setTranslateY(361);
	    attack.setOnMouseEntered(e-> {
	    	attack.setOpacity(0.5);
	    });
	    attack.setOnMouseExited(e->{
	    	attack.setOpacity(100);
	    });
	    cast1 = new Button();
	    cast1.setGraphic(new ImageView(castbtn1));
	    cast1.setTranslateX(857);
	    cast1.setTranslateY(120);
	    cast1.setOnMouseEntered(e-> {
	    	cast1.setOpacity(0.5);
	    });
	    cast1.setOnMouseExited(e->{
	    	cast1.setOpacity(100);
	    });
	    cast2 = new Button();
	    cast2.setGraphic(new ImageView(castbtn2));
	    cast2.setTranslateX(857);
	    cast2.setTranslateY(180);
	    cast2.setOnMouseEntered(e-> {
	    	cast2.setOpacity(0.5);
	    });
	    cast2.setOnMouseExited(e->{
	    	cast2.setOpacity(100);
	    });
	    cast3 = new Button();
	    cast3.setGraphic(new ImageView(castbtn3));
	    cast3.setTranslateX(857);
	    cast3.setTranslateY(240);
	    cast3.setOnMouseEntered(e-> {
	    	cast3.setOpacity(0.5);
	    });
	    cast3.setOnMouseExited(e->{
	    	cast3.setOpacity(100);
	    });
	    cast4 = new Button();
	    cast4.setGraphic(new ImageView(castbtn4));
	    cast4.setTranslateX(857);
	    cast4.setTranslateY(300);
	    cast4.setOnMouseEntered(e-> {
	    	cast4.setOpacity(0.5);
	    });
	    cast4.setOnMouseExited(e->{
	    	cast4.setOpacity(100);
	    });
	    leader = new Button();
	    leader.setGraphic(new ImageView(leaderbtn));
	    leader.setEffect(White);
	    leader.setTranslateX(500);
	    leader.setTranslateY(33);
	    leader.setOnMouseEntered(e-> {
	    	leader.setOpacity(0.5);
	    });
	    leader.setOnMouseExited(e->{
	    	leader.setOpacity(100);
	    });
	    endturn = new Button();
	    endturn.setGraphic(new ImageView(endturnbtn));
	    endturn.setTranslateX(857);
	    endturn.setTranslateY(400);
	    endturn.setOnMouseEntered(e-> {
	    	endturn.setOpacity(0.5);
	    });
	    endturn.setOnMouseExited(e->{
	    	endturn.setOpacity(100);
	    });
	    
	  // new game
	  Player firstPlayer = new Player(player1.getText());
	  Player secondPlayer = new Player(player2.getText());
	  game = new Game(firstPlayer, secondPlayer);
	  Game.loadAbilities("Abilities.csv");
	  Game.loadChampions("Champions.csv");
	  ArrayList<Champion> availableChampions = Game.getAvailableChampions();
	  ArrayList<Ability> availableAbilities = Game.getAvailableAbilities();
	  //adding covers
	  for(int i=0;i<Game.getBoardwidth();i++) {
		  for(int j=0;j<Game.getBoardheight();j++) {
			 if(game.getBoard()[i][j]instanceof Cover) {
				Cover c = (Cover)game.getBoard()[i][j];
	   			covers.add(c);
	   		 }
		  }
	   }
	    //actions
	    for(int i=0;i<6;i++) {
	    	buttons.add(null);
	    }
	    //buttons for battle
	    Image one = new Image(Files.newInputStream(Paths.get("images/scene2/captainbattle.png")));
	    Image two = new Image(Files.newInputStream(Paths.get("images/scene2/deadpoolbattle.png")));
	    Image three = new Image(Files.newInputStream(Paths.get("images/scene2/electrobattle.png")));
	    Image four = new Image(Files.newInputStream(Paths.get("images/scene2/ghostriderbattle.png")));
	    Image five = new Image(Files.newInputStream(Paths.get("images/scene2/helabattle.png")));
	    Image six = new Image(Files.newInputStream(Paths.get("images/scene2/hulkbattle.png")));
	    Image seven = new Image(Files.newInputStream(Paths.get("images/scene2/icemanbattle.png")));
	    Image eight = new Image(Files.newInputStream(Paths.get("images/scene2/ironmanbattle.png")));
	    Image nine = new Image(Files.newInputStream(Paths.get("images/scene2/lokibattle.png")));
	    Image ten = new Image(Files.newInputStream(Paths.get("images/scene2/quicksilverbattle.png")));
	    Image eleven = new Image(Files.newInputStream(Paths.get("images/scene2/spidermanbattle.png")));
	    Image twelve = new Image(Files.newInputStream(Paths.get("images/scene2/strangebattle.png")));
	    Image thirteen = new Image(Files.newInputStream(Paths.get("images/scene2/thorbattle.png")));
	    Image fourteen = new Image(Files.newInputStream(Paths.get("images/scene2/venombattle.png")));
	    Image fifteen = new Image(Files.newInputStream(Paths.get("images/scene2/yellowjacketbattle.png")));
	    //adding to teams and viewing info
	    captainAmerica.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent click) {
	            if(click.getClickCount() == 2) {
	            	if(game.getFirstPlayer().getTeam().size()<3) {
	            		game.getTurnOrder().insert(availableChampions.get(0));
	            		game.getFirstPlayer().getTeam().add(availableChampions.get(0));
	            	}
	            	else if(game.getFirstPlayer().getTeam().size()==3 && game.getSecondPlayer().getTeam().size()<3) {
	            		game.getTurnOrder().insert(availableChampions.get(0));
	            		game.getSecondPlayer().getTeam().add(availableChampions.get(0));
	            	}
	            	if(game.getSecondPlayer().getTeam().size()==3 && game.getFirstPlayer().getTeam().size()==3) {
	            		deadpool.setDisable(true);
	            		electro.setDisable(true);
	            		ghostRider.setDisable(true);
	            		hela.setDisable(true);
	            		hulk.setDisable(true);
	            		iceMan.setDisable(true);
	            		ironMan.setDisable(true);
	            		loki.setDisable(true);
	            		quickSilver.setDisable(true);
	            		spiderMan.setDisable(true);
	            		drStrange.setDisable(true);
	            		thor.setDisable(true);
	            		venom.setDisable(true);
	            		yellowJacket.setDisable(true);
	            	}
	            	captainAmerica.setDisable(true);
	            	champion.setVisible(false);
	            	if(count==0) {
	            		capt.setVisible(true);
	            		capt.setTranslateX(650);
	            		capt.setTranslateY(80);
	            		b1.setGraphic(new ImageView(c1));
	            		button1.setGraphic(new ImageView(one));
	            		buttons.set(count, button1);
	            		button1.setAccessibleText("Captain America");
	            	}
	            	else if(count==1) {
	            		capt.setVisible(true);
	            		capt.setTranslateX(735);
	            		capt.setTranslateY(80);
	            		b2.setGraphic(new ImageView(c1));
	            		button2.setGraphic(new ImageView(one));
	            		buttons.set(count, button2);
	            		button2.setAccessibleText("Captain America");
	            	}
	            	else if(count==2) {
	            		capt.setVisible(true);
	            		capt.setTranslateX(820);
	            		capt.setTranslateY(80);
	            		b3.setGraphic(new ImageView(c1));
	            		button3.setGraphic(new ImageView(one));
	            		buttons.set(count, button3);
	            		button3.setAccessibleText("Captain America");
	            	}
	            	else if(count==3) {
	            		capt.setVisible(true);
	            		capt.setTranslateX(650);
	            		capt.setTranslateY(210);
	            		b4.setGraphic(new ImageView(c1));
	            		button4.setGraphic(new ImageView(one));
	            		buttons.set(count, button4);
	            		button4.setAccessibleText("Captain America");
	            	}
	            	else if(count==4) {
	            		capt.setVisible(true);
	            		capt.setTranslateX(735);
	            		capt.setTranslateY(210);
	            		b5.setGraphic(new ImageView(c1));
	            		button5.setGraphic(new ImageView(one));
	            		buttons.set(count, button5);
	            		button5.setAccessibleText("Captain America");
	            	}
	            	else if(count==5) {
	            		capt.setVisible(true);
	            		capt.setTranslateX(820);
	            		capt.setTranslateY(210);
	            		b6.setGraphic(new ImageView(c1));
	            		button6.setGraphic(new ImageView(one));
	            		buttons.set(count, button6);
	            		button6.setAccessibleText("Captain America");
	            	}
	            	count++;
	            }
	            else if(click.getClickCount() == 1) {
	            	Hero h1 = ((Hero)availableChampions.get(0));
	            	champion.setText(h1.toString());
	            	champion.setVisible(true);
	            }
	        }
	    });
	    deadpool.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent click) {
	        	if(click.getClickCount() == 2) {
	            	if(game.getFirstPlayer().getTeam().size()<3) {
	            		game.getTurnOrder().insert(availableChampions.get(1));
	            		game.getFirstPlayer().getTeam().add(availableChampions.get(1));
	            	}
	            	else if(game.getFirstPlayer().getTeam().size()==3 && game.getSecondPlayer().getTeam().size()<3) {
	            		game.getTurnOrder().insert(availableChampions.get(1));
	            		game.getSecondPlayer().getTeam().add(availableChampions.get(1));
	            	}
	            	if(game.getSecondPlayer().getTeam().size()==3 && game.getFirstPlayer().getTeam().size()==3) {
		            	captainAmerica.setDisable(true);
	            		electro.setDisable(true);
	            		ghostRider.setDisable(true);
	            		hela.setDisable(true);
	            		hulk.setDisable(true);
	            		iceMan.setDisable(true);
	            		ironMan.setDisable(true);
	            		loki.setDisable(true);
	            		quickSilver.setDisable(true);
	            		spiderMan.setDisable(true);
	            		drStrange.setDisable(true);
	            		thor.setDisable(true);
	            		venom.setDisable(true);
	            		yellowJacket.setDisable(true);
	            	}
	            	deadpool.setDisable(true);
	            	champion.setVisible(false);
	            	if(count==0) {
	            		deadp.setVisible(true);
	            		deadp.setTranslateX(650);
	            		deadp.setTranslateY(80);
	            		b1.setGraphic(new ImageView(c2));
	            		button1.setGraphic(new ImageView(two));
	            		buttons.set(count, button1);
	            		button1.setAccessibleText("Deadpool");
	            	}
	            	else if(count==1) {
	            		deadp.setVisible(true);
	            		deadp.setTranslateX(735);
	            		deadp.setTranslateY(80);
	            		b2.setGraphic(new ImageView(c2));
	            		button2.setGraphic(new ImageView(two));
	            		buttons.set(count, button2);
	            		button2.setAccessibleText("Deadpool");
	            	}
	            	else if(count==2) {
	            		deadp.setVisible(true);
	            		deadp.setTranslateX(820);
	            		deadp.setTranslateY(80);
	            		b3.setGraphic(new ImageView(c2));
	            		button3.setGraphic(new ImageView(two));
	            		buttons.set(count, button3);
	            		button3.setAccessibleText("Deadpool");
	            	}
	            	else if(count==3) {
	            		deadp.setVisible(true);
	            		deadp.setTranslateX(650);
	            		deadp.setTranslateY(210);
	            		b4.setGraphic(new ImageView(c2));
	            		button4.setGraphic(new ImageView(two));
	            		buttons.set(count, button4);
	            		button4.setAccessibleText("Deadpool");
	            	}
	            	else if(count==4) {
	            		deadp.setVisible(true);
	            		deadp.setTranslateX(735);
	            		deadp.setTranslateY(210);
	            		b5.setGraphic(new ImageView(c2));
	            		button5.setGraphic(new ImageView(two));
	            		buttons.set(count, button5);
	            		button5.setAccessibleText("Deadpool");
	            	}
	            	else if(count==5) {
	            		deadp.setVisible(true);
	            		deadp.setTranslateX(820);
	            		deadp.setTranslateY(210);
	            		b6.setGraphic(new ImageView(c2));
	            		button6.setGraphic(new ImageView(two));
	            		buttons.set(count, button6);
	            		button6.setAccessibleText("Deadpool");
	            	}
	            	count++;
	            }
	            else if(click.getClickCount() == 1) {
	            	AntiHero ah1 = ((AntiHero)availableChampions.get(1));
	            	champion.setText(ah1.toString());
	            	champion.setVisible(true);
	            }
	        }
	    });
	    electro.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent click) {
	        	if(click.getClickCount() == 2) {
	            	if(game.getFirstPlayer().getTeam().size()<3) {
	            		game.getTurnOrder().insert(availableChampions.get(3));
	            		game.getFirstPlayer().getTeam().add(availableChampions.get(3));
	            	}
	            	else if(game.getFirstPlayer().getTeam().size()==3 && game.getSecondPlayer().getTeam().size()<3) {
	            		game.getTurnOrder().insert(availableChampions.get(3));
	            		game.getSecondPlayer().getTeam().add(availableChampions.get(3));
	            	}
	            	if(game.getSecondPlayer().getTeam().size()==3 && game.getFirstPlayer().getTeam().size()==3) {
		            	captainAmerica.setDisable(true);
	            		deadpool.setDisable(true);
	            		ghostRider.setDisable(true);
	            		hela.setDisable(true);
	            		hulk.setDisable(true);
	            		iceMan.setDisable(true);
	            		ironMan.setDisable(true);
	            		loki.setDisable(true);
	            		quickSilver.setDisable(true);
	            		spiderMan.setDisable(true);
	            		drStrange.setDisable(true);
	            		thor.setDisable(true);
	            		venom.setDisable(true);
	            		yellowJacket.setDisable(true);
	            	}
	            	electro.setDisable(true);
	            	champion.setVisible(false);
	            	if(count==0) {
	            		elec.setVisible(true);
	            		elec.setTranslateX(650);
	            		elec.setTranslateY(80);
	            		b1.setGraphic(new ImageView(c3));
	            		button1.setGraphic(new ImageView(three));
	            		buttons.set(count, button1);
	            		button1.setAccessibleText("Electro");
	            	}
	            	else if(count==1) {
	            		elec.setVisible(true);
	            		elec.setTranslateX(735);
	            		elec.setTranslateY(80);
	            		b2.setGraphic(new ImageView(c3));
	            		button2.setGraphic(new ImageView(three));
	            		buttons.set(count, button2);
	            		button2.setAccessibleText("Electro");
	            	}
	            	else if(count==2) {
	            		elec.setVisible(true);
	            		elec.setTranslateX(820);
	            		elec.setTranslateY(80);
	            		b3.setGraphic(new ImageView(c3));
	            		button3.setGraphic(new ImageView(three));
	            		buttons.set(count, button3);
	            		button3.setAccessibleText("Electro");
	            	}
	            	else if(count==3) {
	            		elec.setVisible(true);
	            		elec.setTranslateX(650);
	            		elec.setTranslateY(210);
	            		b4.setGraphic(new ImageView(c3));
	            		button4.setGraphic(new ImageView(three));
	            		buttons.set(count, button4);
	            		button4.setAccessibleText("Electro");
	            	}
	            	else if(count==4) {
	            		elec.setVisible(true);
	            		elec.setTranslateX(735);
	            		elec.setTranslateY(210);
	            		b5.setGraphic(new ImageView(c3));
	            		button5.setGraphic(new ImageView(three));
	            		buttons.set(count, button5);
	            		button5.setAccessibleText("Electro");
	            	}
	            	else if(count==5) {
	            		elec.setVisible(true);
	            		elec.setTranslateX(820);
	            		elec.setTranslateY(210);
	            		b6.setGraphic(new ImageView(c3));
	            		button6.setGraphic(new ImageView(three));
	            		buttons.set(count, button6);
	            		button6.setAccessibleText("Electro");
	            	}
	            	count++;
	            }
	            else if(click.getClickCount() == 1) {
	            	Villain v1 = ((Villain)availableChampions.get(3));
	            	champion.setText(v1.toString());
	            	champion.setVisible(true);
	            }
	        }
	    });
	    ghostRider.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent click) {
	        	if(click.getClickCount() == 2) {
	            	if(game.getFirstPlayer().getTeam().size()<3) {
	            		game.getTurnOrder().insert(availableChampions.get(4));
	            		game.getFirstPlayer().getTeam().add(availableChampions.get(4));
	            	}
	            	else if(game.getFirstPlayer().getTeam().size()==3 && game.getSecondPlayer().getTeam().size()<3) {
	            		game.getTurnOrder().insert(availableChampions.get(4));
	            		game.getSecondPlayer().getTeam().add(availableChampions.get(4));
	            	}
	            	if(game.getSecondPlayer().getTeam().size()==3 && game.getFirstPlayer().getTeam().size()==3) {
		            	captainAmerica.setDisable(true);
	            		deadpool.setDisable(true);
	            		electro.setDisable(true);
	            		hela.setDisable(true);
	            		hulk.setDisable(true);
	            		iceMan.setDisable(true);
	            		ironMan.setDisable(true);
	            		loki.setDisable(true);
	            		quickSilver.setDisable(true);
	            		spiderMan.setDisable(true);
	            		drStrange.setDisable(true);
	            		thor.setDisable(true);
	            		venom.setDisable(true);
	            		yellowJacket.setDisable(true);
	            	}
	            	ghostRider.setDisable(true);
	            	champion.setVisible(false);
	            	if(count==0) {
	            		ghostr.setVisible(true);
	            		ghostr.setTranslateX(650);
	            		ghostr.setTranslateY(80);
	            		b1.setGraphic(new ImageView(c4));
	            		button1.setGraphic(new ImageView(four));
	            		buttons.set(count, button1);
	            		button1.setAccessibleText("Ghost Rider");
	            	}
	            	else if(count==1) {
	            		ghostr.setVisible(true);
	            		ghostr.setTranslateX(735);
	            		ghostr.setTranslateY(80);
	            		b2.setGraphic(new ImageView(c4));
	            		button2.setGraphic(new ImageView(four));
	            		buttons.set(count, button2);
	            		button2.setAccessibleText("Ghost Rider");
	            	}
	            	else if(count==2) {
	            		ghostr.setVisible(true);
	            		ghostr.setTranslateX(820);
	            		ghostr.setTranslateY(80);
	            		b3.setGraphic(new ImageView(c4));
	            		button3.setGraphic(new ImageView(four));
	            		buttons.set(count, button3);
	            		button3.setAccessibleText("Ghost Rider");
	            	}
	            	else if(count==3) {
	            		ghostr.setVisible(true);
	            		ghostr.setTranslateX(650);
	            		ghostr.setTranslateY(210);
	            		b4.setGraphic(new ImageView(c4));
	            		button4.setGraphic(new ImageView(four));
	            		buttons.set(count, button4);
	            		button4.setAccessibleText("Ghost Rider");
	            	}
	            	else if(count==4) {
	            		ghostr.setVisible(true);
	            		ghostr.setTranslateX(735);
	            		ghostr.setTranslateY(210);
	            		b5.setGraphic(new ImageView(c4));
	            		button5.setGraphic(new ImageView(four));
	            		buttons.set(count, button5);
	            		button5.setAccessibleText("Ghost Rider");
	            	}
	            	else if(count==5) {
	            		ghostr.setVisible(true);
	            		ghostr.setTranslateX(820);
	            		ghostr.setTranslateY(210);
	            		b6.setGraphic(new ImageView(c4));
	            		button6.setGraphic(new ImageView(four));
	            		buttons.set(count, button6);
	            		button6.setAccessibleText("Ghost Rider");
	            	}
	            	count++;
	            }
	            else if(click.getClickCount() == 1) {
	            	AntiHero ah2 = ((AntiHero)availableChampions.get(4));
	            	champion.setText(ah2.toString());
	            	champion.setVisible(true);
	            }
	        }
	    });
	    hela.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent click) {
	        	if(click.getClickCount() == 2) {
	            	if(game.getFirstPlayer().getTeam().size()<3) {
	            		game.getTurnOrder().insert(availableChampions.get(5));
	            		game.getFirstPlayer().getTeam().add(availableChampions.get(5));
	            	}
	            	else if(game.getFirstPlayer().getTeam().size()==3 && game.getSecondPlayer().getTeam().size()<3) {
	            		game.getTurnOrder().insert(availableChampions.get(5));
	            		game.getSecondPlayer().getTeam().add(availableChampions.get(5));
	            	}
	            	if(game.getSecondPlayer().getTeam().size()==3 && game.getFirstPlayer().getTeam().size()==3){
		            	captainAmerica.setDisable(true);
	            		deadpool.setDisable(true);
	            		electro.setDisable(true);
	            		ghostRider.setDisable(true);
	            		hulk.setDisable(true);
	            		iceMan.setDisable(true);
	            		ironMan.setDisable(true);
	            		loki.setDisable(true);
	            		quickSilver.setDisable(true);
	            		spiderMan.setDisable(true);
	            		drStrange.setDisable(true);
	            		thor.setDisable(true);
	            		venom.setDisable(true);
	            		yellowJacket.setDisable(true);
	            	}
	            	hela.setDisable(true);
	            	champion.setVisible(false);
	            	if(count==0) {
	            		hel.setVisible(true);
	            		hel.setTranslateX(650);
	            		hel.setTranslateY(80);
	            		b1.setGraphic(new ImageView(c5));
	            		button1.setGraphic(new ImageView(five));
	            		buttons.set(count, button1);
	            		button1.setAccessibleText("Hela");
	            	}
	            	else if(count==1) {
	            		hel.setVisible(true);
	            		hel.setTranslateX(735);
	            		hel.setTranslateY(80);
	            		b2.setGraphic(new ImageView(c5));
	            		button2.setGraphic(new ImageView(five));
	            		buttons.set(count, button2);
	            		button2.setAccessibleText("Hela");
	            	}
	            	else if(count==2) {
	            		hel.setVisible(true);
	            		hel.setTranslateX(820);
	            		hel.setTranslateY(80);
	            		b3.setGraphic(new ImageView(c5));
	            		button3.setGraphic(new ImageView(five));
	            		buttons.set(count, button3);
	            		button3.setAccessibleText("Hela");
	            	}
	            	else if(count==3) {
	            		hel.setVisible(true);
	            		hel.setTranslateX(650);
	            		hel.setTranslateY(210);
	            		b4.setGraphic(new ImageView(c5));
	            		button4.setGraphic(new ImageView(five));
	            		buttons.set(count, button4);
	            		button4.setAccessibleText("Hela");
	            	}
	            	else if(count==4) {
	            		hel.setVisible(true);
	            		hel.setTranslateX(735);
	            		hel.setTranslateY(210);
	            		b5.setGraphic(new ImageView(c5));
	            		button5.setGraphic(new ImageView(five));
	            		buttons.set(count, button5);
	            		button5.setAccessibleText("Hela");
	            	}
	            	else if(count==5) {
	            		hel.setVisible(true);
	            		hel.setTranslateX(820);
	            		hel.setTranslateY(210);
	            		b6.setGraphic(new ImageView(c5));
	            		button6.setGraphic(new ImageView(five));
	            		buttons.set(count, button6);
	            		button6.setAccessibleText("Hela");
	            	}
	            	count++;
	            }
	            else if(click.getClickCount() == 1) {
	            	Villain v2 = ((Villain)availableChampions.get(5));
	            	champion.setText(v2.toString());
	            	champion.setVisible(true);
	            }
	        }
	    });
	    hulk.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent click) {
	        	if(click.getClickCount() == 2) {
	            	if(game.getFirstPlayer().getTeam().size()<3) {
	            		game.getTurnOrder().insert(availableChampions.get(6));
	            		game.getFirstPlayer().getTeam().add(availableChampions.get(6));
	            	}
	            	else if(game.getFirstPlayer().getTeam().size()==3 && game.getSecondPlayer().getTeam().size()<3) {
	            		game.getTurnOrder().insert(availableChampions.get(6));
	            		game.getSecondPlayer().getTeam().add(availableChampions.get(6));
	            	}
	            	if(game.getSecondPlayer().getTeam().size()==3 && game.getFirstPlayer().getTeam().size()==3) {
		            	captainAmerica.setDisable(true);
	            		deadpool.setDisable(true);
	            		electro.setDisable(true);
	            		ghostRider.setDisable(true);
	            		hela.setDisable(true);
	            		iceMan.setDisable(true);
	            		ironMan.setDisable(true);
	            		loki.setDisable(true);
	            		quickSilver.setDisable(true);
	            		spiderMan.setDisable(true);
	            		drStrange.setDisable(true);
	            		thor.setDisable(true);
	            		venom.setDisable(true);
	            		yellowJacket.setDisable(true);
	            	}
	            	hulk.setDisable(true);
	            	champion.setVisible(false);
	            	if(count==0) {
	            		hul.setVisible(true);
	            		hul.setTranslateX(650);
	            		hul.setTranslateY(80);
	            		b1.setGraphic(new ImageView(c6));
	            		button1.setGraphic(new ImageView(six));
	            		buttons.set(count, button1);
	            		button1.setAccessibleText("Hulk");
	            	}
	            	else if(count==1) {
	            		hul.setVisible(true);
	            		hul.setTranslateX(735);
	            		hul.setTranslateY(80);
	            		b2.setGraphic(new ImageView(c6));
	            		button2.setGraphic(new ImageView(six));
	            		buttons.set(count, button2);
	            		button2.setAccessibleText("Hulk");
	            	}
	            	else if(count==2) {
	            		hul.setVisible(true);
	            		hul.setTranslateX(820);
	            		hul.setTranslateY(80);
	            		b3.setGraphic(new ImageView(c6));
	            		button3.setGraphic(new ImageView(six));
	            		buttons.set(count, button3);
	            		button3.setAccessibleText("Hulk");
	            	}
	            	else if(count==3) {
	            		hul.setVisible(true);
	            		hul.setTranslateX(650);
	            		hul.setTranslateY(210);
	            		b4.setGraphic(new ImageView(c6));
	            		button4.setGraphic(new ImageView(six));
	            		buttons.set(count, button4);
	            		button4.setAccessibleText("Hulk");
	            	}
	            	else if(count==4) {
	            		hul.setVisible(true);
	            		hul.setTranslateX(735);
	            		hul.setTranslateY(210);
	            		b5.setGraphic(new ImageView(c6));
	            		button5.setGraphic(new ImageView(six));
	            		buttons.set(count, button5);
	            		button5.setAccessibleText("Hulk");
	            	}
	            	else if(count==5) {
	            		hul.setVisible(true);
	            		hul.setTranslateX(820);
	            		hul.setTranslateY(210);
	            		b6.setGraphic(new ImageView(c6));
	            		button6.setGraphic(new ImageView(six));
	            		buttons.set(count, button6);
	            		button6.setAccessibleText("Hulk");
	            	}
	            	count++;
	            }
	            else if(click.getClickCount() == 1) {
	            	Hero h3 = ((Hero)availableChampions.get(6));
	            	champion.setText(h3.toString());
	            	champion.setVisible(true);
	            }
	        }
	    });
	    iceMan.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent click) {
	        	if(click.getClickCount() == 2) {
	            	if(game.getFirstPlayer().getTeam().size()<3) {
	            		game.getTurnOrder().insert(availableChampions.get(7));
	            		game.getFirstPlayer().getTeam().add(availableChampions.get(7));
	            	}
	            	else if(game.getFirstPlayer().getTeam().size()==3 && game.getSecondPlayer().getTeam().size()<3) {
	            		game.getTurnOrder().insert(availableChampions.get(7));
	            		game.getSecondPlayer().getTeam().add(availableChampions.get(7));
	            	}
	            	if(game.getSecondPlayer().getTeam().size()==3 && game.getFirstPlayer().getTeam().size()==3) {
		            	captainAmerica.setDisable(true);
	            		deadpool.setDisable(true);
	            		electro.setDisable(true);
	            		ghostRider.setDisable(true);
	            		hela.setDisable(true);
	            		hulk.setDisable(true);
	            		ironMan.setDisable(true);
	            		loki.setDisable(true);
	            		quickSilver.setDisable(true);
	            		spiderMan.setDisable(true);
	            		drStrange.setDisable(true);
	            		thor.setDisable(true);
	            		venom.setDisable(true);
	            		yellowJacket.setDisable(true);
	            	}
	            	iceMan.setDisable(true);
	            	champion.setVisible(false);
	             	if(count==0) {
	            		icem.setVisible(true);
	            		icem.setTranslateX(650);
	            		icem.setTranslateY(80);
	            		b1.setGraphic(new ImageView(c7));
	            		button1.setGraphic(new ImageView(seven));
	            		buttons.set(count, button1);
	            		button1.setAccessibleText("Iceman");
	            	}
	            	else if(count==1) {
	            		icem.setVisible(true);
	            		icem.setTranslateX(735);
	            		icem.setTranslateY(80);
	            		b2.setGraphic(new ImageView(c7));
	            		button2.setGraphic(new ImageView(seven));
	            		buttons.set(count, button2);
	            		button2.setAccessibleText("Iceman");
	            	}
	            	else if(count==2) {
	            		icem.setVisible(true);
	            		icem.setTranslateX(820);
	            		icem.setTranslateY(80);
	            		b3.setGraphic(new ImageView(c7));
	            		button3.setGraphic(new ImageView(seven));
	            		buttons.set(count, button3);
	            		button3.setAccessibleText("Iceman");
	            	}
	            	else if(count==3) {
	            		icem.setVisible(true);
	            		icem.setTranslateX(650);
	            		icem.setTranslateY(210);
	            		b4.setGraphic(new ImageView(c7));
	            		button4.setGraphic(new ImageView(seven));
	            		buttons.set(count, button4);
	            		button4.setAccessibleText("Iceman");
	            	}
	            	else if(count==4) {
	            		icem.setVisible(true);
	            		icem.setTranslateX(735);
	            		icem.setTranslateY(210);
	            		b5.setGraphic(new ImageView(c7));
	            		button5.setGraphic(new ImageView(seven));
	            		buttons.set(count, button5);
	            		button5.setAccessibleText("Iceman");
	            	}
	            	else if(count==5) {
	            		icem.setVisible(true);
	            		icem.setTranslateX(820);
	            		icem.setTranslateY(210);
	            		b6.setGraphic(new ImageView(c7));
	            		button6.setGraphic(new ImageView(seven));
	            		buttons.set(count, button6);
	            		button6.setAccessibleText("Iceman");
	            	}
	            	count++;
	            }
	            else if(click.getClickCount() == 1) {
	            	Hero h4 = ((Hero)availableChampions.get(7));
	            	champion.setText(h4.toString());
	            	champion.setVisible(true);
	            }
	        }
	    });
	    ironMan.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent click) {
	        	if(click.getClickCount() == 2) {
	            	if(game.getFirstPlayer().getTeam().size()<3) {
	            		game.getTurnOrder().insert(availableChampions.get(8));
	            		game.getFirstPlayer().getTeam().add(availableChampions.get(8));
	            	}
	            	else if(game.getFirstPlayer().getTeam().size()==3 && game.getSecondPlayer().getTeam().size()<3) {
	            		game.getTurnOrder().insert(availableChampions.get(8));
	            		game.getSecondPlayer().getTeam().add(availableChampions.get(8));
	            	}
	            	if(game.getSecondPlayer().getTeam().size()==3 && game.getFirstPlayer().getTeam().size()==3) {
		            	captainAmerica.setDisable(true);
	            		deadpool.setDisable(true);
	            		electro.setDisable(true);
	            		ghostRider.setDisable(true);
	            		hela.setDisable(true);
	            		hulk.setDisable(true);
	            		iceMan.setDisable(true);
	            		loki.setDisable(true);
	            		quickSilver.setDisable(true);
	            		spiderMan.setDisable(true);
	            		drStrange.setDisable(true);
	            		thor.setDisable(true);
	            		venom.setDisable(true);
	            		yellowJacket.setDisable(true);
	            	}
	            	ironMan.setDisable(true);
	            	champion.setVisible(false);
	            	if(count==0) {
	            		ironm.setVisible(true);
	            		ironm.setTranslateX(650);
	            		ironm.setTranslateY(80);
	            		b1.setGraphic(new ImageView(c8));
	            		button1.setGraphic(new ImageView(eight));
	            		buttons.set(count, button1);
	            		button1.setAccessibleText("Ironman");
	            	}
	            	else if(count==1) {
	            		ironm.setVisible(true);
	            		ironm.setTranslateX(735);
	            		ironm.setTranslateY(80);
	            		b2.setGraphic(new ImageView(c8));
	            		button2.setGraphic(new ImageView(eight));
	            		buttons.set(count, button2);
	            		button2.setAccessibleText("Ironman");
	            	}
	            	else if(count==2) {
	            		ironm.setVisible(true);
	            		ironm.setTranslateX(820);
	            		ironm.setTranslateY(80);
	            		b3.setGraphic(new ImageView(c8));
	            		button3.setGraphic(new ImageView(eight));
	            		buttons.set(count, button3);
	            		button3.setAccessibleText("Ironman");
	            	}
	            	else if(count==3) {
	            		ironm.setVisible(true);
	            		ironm.setTranslateX(650);
	            		ironm.setTranslateY(210);
	            		b4.setGraphic(new ImageView(c8));
	            		button4.setGraphic(new ImageView(eight));
	            		buttons.set(count, button4);
	            		button4.setAccessibleText("Ironman");
	            	}
	            	else if(count==4) {
	            		ironm.setVisible(true);
	            		ironm.setTranslateX(735);
	            		ironm.setTranslateY(210);
	            		b5.setGraphic(new ImageView(c8));
	            		button5.setGraphic(new ImageView(eight));
	            		buttons.set(count, button5);
	            		button5.setAccessibleText("Ironman");
	            	}
	            	else if(count==5) {
	            		ironm.setVisible(true);
	            		ironm.setTranslateX(820);
	            		ironm.setTranslateY(210);
	            		b6.setGraphic(new ImageView(c8));
	            		button6.setGraphic(new ImageView(eight));
	            		buttons.set(count, button6);
	            		button6.setAccessibleText("Ironman");
	            	}
	            	count++;
	            }
	            else if(click.getClickCount() == 1) {
	            	Hero h5 = ((Hero)availableChampions.get(8));
	            	champion.setText(h5.toString());
	            	champion.setVisible(true);
	            }
	        }
	    });
	    loki.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent click) {
	        	if(click.getClickCount() == 2) {
	            	if(game.getFirstPlayer().getTeam().size()<3) {
	            		game.getTurnOrder().insert(availableChampions.get(9));
	            		game.getFirstPlayer().getTeam().add(availableChampions.get(9));
	            	}
	            	else if(game.getFirstPlayer().getTeam().size()==3 && game.getSecondPlayer().getTeam().size()<3) {
	            		game.getTurnOrder().insert(availableChampions.get(9));
	            		game.getSecondPlayer().getTeam().add(availableChampions.get(9));
	            	}
	            	if(game.getSecondPlayer().getTeam().size()==3 && game.getFirstPlayer().getTeam().size()==3) {
		            	captainAmerica.setDisable(true);
	            		deadpool.setDisable(true);
	            		electro.setDisable(true);
	            		ghostRider.setDisable(true);
	            		hela.setDisable(true);
	            		hulk.setDisable(true);
	            		iceMan.setDisable(true);
	            		ironMan.setDisable(true);
	            		quickSilver.setDisable(true);
	            		spiderMan.setDisable(true);
	            		drStrange.setDisable(true);
	            		thor.setDisable(true);
	            		venom.setDisable(true);
	            		yellowJacket.setDisable(true);
	            	}
	            	loki.setDisable(true);
	            	champion.setVisible(false);
	               	if(count==0) {
	            		lok.setVisible(true);
	            		lok.setTranslateX(650);
	            		lok.setTranslateY(80);
	            		b1.setGraphic(new ImageView(c9));
	            		button1.setGraphic(new ImageView(nine));
	            		buttons.set(count, button1);
	            		button1.setAccessibleText("Loki");
	            	}
	            	else if(count==1) {
	            		lok.setVisible(true);
	            		lok.setTranslateX(735);
	            		lok.setTranslateY(80);
	            		b2.setGraphic(new ImageView(c9));
	            		button2.setGraphic(new ImageView(nine));
	            		buttons.set(count, button2);
	            		button2.setAccessibleText("Loki");
	            	}
	            	else if(count==2) {
	            		lok.setVisible(true);
	            		lok.setTranslateX(820);
	            		lok.setTranslateY(80);
	            		b3.setGraphic(new ImageView(c9));
	            		button3.setGraphic(new ImageView(nine));
	            		buttons.set(count, button3);
	            		button3.setAccessibleText("Loki");
	            	}
	            	else if(count==3) {
	            		lok.setVisible(true);
	            		lok.setTranslateX(650);
	            		lok.setTranslateY(210);
	            		b4.setGraphic(new ImageView(c9));
	            		button4.setGraphic(new ImageView(nine));
	            		buttons.set(count, button4);
	            		button4.setAccessibleText("Loki");
	            	}
	            	else if(count==4) {
	            		lok.setVisible(true);
	            		lok.setTranslateX(735);
	            		lok.setTranslateY(210);
	            		b5.setGraphic(new ImageView(c9));
	            		button5.setGraphic(new ImageView(nine));
	            		buttons.set(count, button5);
	            		button5.setAccessibleText("Loki");
	            	}
	            	else if(count==5) {
	            		lok.setVisible(true);
	            		lok.setTranslateX(820);
	            		lok.setTranslateY(210);
	            		b6.setGraphic(new ImageView(c9));
	            		button6.setGraphic(new ImageView(nine));
	            		buttons.set(count, button6);
	            		button6.setAccessibleText("Loki");
	            	}
	            	count++;
	            }
	            else if(click.getClickCount() == 1) {
	            	Villain v3 = ((Villain)availableChampions.get(9));
	            	champion.setText(v3.toString());
	            	champion.setVisible(true);
	            }
	        }
	    });
	    quickSilver.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent click) {
	        	if(click.getClickCount() == 2) {
	            	if(game.getFirstPlayer().getTeam().size()<3) {
	            		game.getTurnOrder().insert(availableChampions.get(10));
	            		game.getFirstPlayer().getTeam().add(availableChampions.get(10));
	            	}
	            	else if(game.getFirstPlayer().getTeam().size()==3 && game.getSecondPlayer().getTeam().size()<3) {
	            		game.getTurnOrder().insert(availableChampions.get(10));
	            		game.getSecondPlayer().getTeam().add(availableChampions.get(10));
	            	}
	            	if(game.getSecondPlayer().getTeam().size()==3 && game.getFirstPlayer().getTeam().size()==3) {
		            	captainAmerica.setDisable(true);
	            		deadpool.setDisable(true);
	            		electro.setDisable(true);
	            		ghostRider.setDisable(true);
	            		hela.setDisable(true);
	            		hulk.setDisable(true);
	            		iceMan.setDisable(true);
	            		ironMan.setDisable(true);
	            		loki.setDisable(true);
	            		spiderMan.setDisable(true);
	            		drStrange.setDisable(true);
	            		thor.setDisable(true);
	            		venom.setDisable(true);
	            		yellowJacket.setDisable(true);
	            	}
	            	quickSilver.setDisable(true);
	            	champion.setVisible(false);
	             	if(count==0) {
	            		quicks.setVisible(true);
	            		quicks.setTranslateX(650);
	            		quicks.setTranslateY(80);
	            		b1.setGraphic(new ImageView(c10));
	            		button1.setGraphic(new ImageView(ten));
	            		buttons.set(count, button1);
	            		button1.setAccessibleText("Quicksilver");
	            	}
	            	else if(count==1) {
	            		quicks.setVisible(true);
	            		quicks.setTranslateX(735);
	            		quicks.setTranslateY(80);
	            		b2.setGraphic(new ImageView(c10));
	            		button2.setGraphic(new ImageView(ten));
	            		buttons.set(count, button2);
	            		button2.setAccessibleText("Quicksilver");
	            	}
	            	else if(count==2) {
	            		quicks.setVisible(true);
	            		quicks.setTranslateX(820);
	            		quicks.setTranslateY(80);
	            		b3.setGraphic(new ImageView(c10));
	            		button3.setGraphic(new ImageView(ten));
	            		buttons.set(count, button3);
	            		button3.setAccessibleText("Quicksilver");
	            	}
	            	else if(count==3) {
	            		quicks.setVisible(true);
	            		quicks.setTranslateX(650);
	            		quicks.setTranslateY(210);
	            		b4.setGraphic(new ImageView(c10));
	            		button4.setGraphic(new ImageView(ten));
	            		buttons.set(count, button4);
	            		button4.setAccessibleText("Quicksilver");
	            	}
	            	else if(count==4) {
	            		quicks.setVisible(true);
	            		quicks.setTranslateX(735);
	            		quicks.setTranslateY(210);
	            		b5.setGraphic(new ImageView(c10));
	            		button5.setGraphic(new ImageView(ten));
	            		buttons.set(count, button5);
	            		button5.setAccessibleText("Quicksilver");
	            	}
	            	else if(count==5) {
	            		quicks.setVisible(true);
	            		quicks.setTranslateX(820);
	            		quicks.setTranslateY(210);
	            		b6.setGraphic(new ImageView(c10));
	            		button6.setGraphic(new ImageView(ten));
	            		buttons.set(count, button6);
	            		button6.setAccessibleText("Quicksilver");
	            	}
	            	count++;
	            }
	            else if(click.getClickCount() == 1) {
	            	Villain v4 = ((Villain)availableChampions.get(10));
	            	champion.setText(v4.toString());
	            	champion.setVisible(true);
	            }
	        }
	    });
	    spiderMan.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent click) {
	        	if(click.getClickCount() == 2) {
	            	if(game.getFirstPlayer().getTeam().size()<3) {
	            		game.getTurnOrder().insert(availableChampions.get(11));
	            		game.getFirstPlayer().getTeam().add(availableChampions.get(11));
	            	}
	            	else if(game.getFirstPlayer().getTeam().size()==3 && game.getSecondPlayer().getTeam().size()<3) {
	            		game.getTurnOrder().insert(availableChampions.get(11));
	            		game.getSecondPlayer().getTeam().add(availableChampions.get(11));
	            	}
	            	if(game.getSecondPlayer().getTeam().size()==3 && game.getFirstPlayer().getTeam().size()==3) {
		            	captainAmerica.setDisable(true);
	            		deadpool.setDisable(true);
	            		electro.setDisable(true);
	            		ghostRider.setDisable(true);
	            		hela.setDisable(true);
	            		hulk.setDisable(true);
	            		iceMan.setDisable(true);
	            		ironMan.setDisable(true);
	            		loki.setDisable(true);
	            		drStrange.setDisable(true);
	            		thor.setDisable(true);
	            		venom.setDisable(true);
	            		yellowJacket.setDisable(true);
	            	}
	            	spiderMan.setDisable(true);
	            	champion.setVisible(false);
	            	if(count==0) {
	            		spiderm.setVisible(true);
	            		spiderm.setTranslateX(650);
	            		spiderm.setTranslateY(80);
	            		b1.setGraphic(new ImageView(c11));
	            		button1.setGraphic(new ImageView(eleven));
	            		buttons.set(count, button1);
	            		button1.setAccessibleText("Spiderman");
	            	}
	            	else if(count==1) {
	            		spiderm.setVisible(true);
	            		spiderm.setTranslateX(735);
	            		spiderm.setTranslateY(80);
	            		b2.setGraphic(new ImageView(c11));
	            		button2.setGraphic(new ImageView(eleven));
	            		buttons.set(count, button2);
	            		button2.setAccessibleText("Spiderman");
	            	}
	            	else if(count==2) {
	            		spiderm.setVisible(true);
	            		spiderm.setTranslateX(820);
	            		spiderm.setTranslateY(80);
	            		b3.setGraphic(new ImageView(c11));
	            		button3.setGraphic(new ImageView(eleven));
	            		buttons.set(count, button3);
	            		button3.setAccessibleText("Spiderman");
	            	}
	            	else if(count==3) {
	            		spiderm.setVisible(true);
	            		spiderm.setTranslateX(650);
	            		spiderm.setTranslateY(210);
	            		b4.setGraphic(new ImageView(c11));
	            		button4.setGraphic(new ImageView(eleven));
	            		buttons.set(count, button4);
	            		button4.setAccessibleText("Spiderman");
	            	}
	            	else if(count==4) {
	            		spiderm.setVisible(true);
	            		spiderm.setTranslateX(735);
	            		spiderm.setTranslateY(210);
	            		b5.setGraphic(new ImageView(c11));
	            		button5.setGraphic(new ImageView(eleven));
	            		buttons.set(count, button5);
	            		button5.setAccessibleText("Spiderman");
	            	}
	            	else if(count==5) {
	            		spiderm.setVisible(true);
	            		spiderm.setTranslateX(820);
	            		spiderm.setTranslateY(210);
	            		b6.setGraphic(new ImageView(c11));
	            		button6.setGraphic(new ImageView(eleven));
	            		buttons.set(count, button6);
	            		button6.setAccessibleText("Spiderman");
	            	}
	            	count++;
	            }
	            else if(click.getClickCount() == 1) {
	            	Hero h6 = ((Hero)availableChampions.get(11));
	            	champion.setText(h6.toString());
	            	champion.setVisible(true);
	            }
	        }
	    });
	    drStrange.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent click) {
	        	if(click.getClickCount() == 2) {
	            	if(game.getFirstPlayer().getTeam().size()<3) {
	            		game.getTurnOrder().insert(availableChampions.get(2));
	            		game.getFirstPlayer().getTeam().add(availableChampions.get(2));
	            	}
	            	else if(game.getFirstPlayer().getTeam().size()==3 && game.getSecondPlayer().getTeam().size()<3) {
	            		game.getTurnOrder().insert(availableChampions.get(2));
	            		game.getSecondPlayer().getTeam().add(availableChampions.get(2));
	            	}
	            	if(game.getSecondPlayer().getTeam().size()==3 && game.getFirstPlayer().getTeam().size()==3) {
		            	captainAmerica.setDisable(true);
	            		deadpool.setDisable(true);
	            		electro.setDisable(true);
	            		ghostRider.setDisable(true);
	            		hela.setDisable(true);
	            		hulk.setDisable(true);
	            		iceMan.setDisable(true);
	            		ironMan.setDisable(true);
	            		loki.setDisable(true);
	            		quickSilver.setDisable(true);
	            		spiderMan.setDisable(true);
	            		thor.setDisable(true);
	            		venom.setDisable(true);
	            		yellowJacket.setDisable(true);
	            	}
	            	drStrange.setDisable(true);
	            	champion.setVisible(false);
	            	if(count==0) {
	            		drs.setVisible(true);
	            		drs.setTranslateX(650);
	            		drs.setTranslateY(80);
	            		b1.setGraphic(new ImageView(c12));
	            		button1.setGraphic(new ImageView(twelve));
	            		buttons.set(count, button1);
	            		button1.setAccessibleText("Dr Strange");
	            	}
	            	else if(count==1) {
	            		drs.setVisible(true);
	            		drs.setTranslateX(735);
	            		drs.setTranslateY(80);
	            		b2.setGraphic(new ImageView(c12));
	            		button2.setGraphic(new ImageView(twelve));
	            		buttons.set(count, button2);
	            		button2.setAccessibleText("Dr Strange");
	            	}
	            	else if(count==2) {
	            		drs.setVisible(true);
	            		drs.setTranslateX(820);
	            		drs.setTranslateY(80);
	            		b3.setGraphic(new ImageView(c12));
	            		button3.setGraphic(new ImageView(twelve));
	            		buttons.set(count, button3);
	            		button3.setAccessibleText("Dr Strange");
	            	}
	            	else if(count==3) {
	            		drs.setVisible(true);
	            		drs.setTranslateX(650);
	            		drs.setTranslateY(210);
	            		b4.setGraphic(new ImageView(c12));
	            		button4.setGraphic(new ImageView(twelve));
	            		buttons.set(count, button4);
	            		button4.setAccessibleText("Dr Strange");
	            	}
	            	else if(count==4) {
	            		drs.setVisible(true);
	            		drs.setTranslateX(735);
	            		drs.setTranslateY(210);
	            		b5.setGraphic(new ImageView(c12));
	            		button5.setGraphic(new ImageView(twelve));
	            		buttons.set(count, button5);
	            		button5.setAccessibleText("Dr Strange");
	            	}
	            	else if(count==5) {
	            		drs.setVisible(true);
	            		drs.setTranslateX(820);
	            		drs.setTranslateY(210);
	            		b6.setGraphic(new ImageView(c12));
	            		button6.setGraphic(new ImageView(twelve));
	            		buttons.set(count, button6);
	            		button6.setAccessibleText("Dr Strange");
	            	}
	            	count++;
	            }
	            else if(click.getClickCount() == 1) {
	            	Hero h2 = ((Hero)availableChampions.get(2));
	            	champion.setText(h2.toString());
	            	champion.setVisible(true);
	            }
	        }
	    });
	    thor.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent click) {
	        	if(click.getClickCount() == 2) {
	            	if(game.getFirstPlayer().getTeam().size()<3) {
	            		game.getTurnOrder().insert(availableChampions.get(12));
	            		game.getFirstPlayer().getTeam().add(availableChampions.get(12));
	            	}
	            	else if(game.getFirstPlayer().getTeam().size()==3 && game.getSecondPlayer().getTeam().size()<3) {
	            		game.getTurnOrder().insert(availableChampions.get(12));
	            		game.getSecondPlayer().getTeam().add(availableChampions.get(12));
	            	}
	            	if(game.getSecondPlayer().getTeam().size()==3 && game.getFirstPlayer().getTeam().size()==3) {
		            	captainAmerica.setDisable(true);
	            		deadpool.setDisable(true);
	            		electro.setDisable(true);
	            		ghostRider.setDisable(true);
	            		hela.setDisable(true);
	            		hulk.setDisable(true);
	            		iceMan.setDisable(true);
	            		ironMan.setDisable(true);
	            		loki.setDisable(true);
	            		quickSilver.setDisable(true);
	            		spiderMan.setDisable(true);
	            		drStrange.setDisable(true);
	            		venom.setDisable(true);
	            		yellowJacket.setDisable(true);
	            	}
	            	thor.setDisable(true);
	            	champion.setVisible(false);
	            	if(count==0) {
	            		thorr.setVisible(true);
	            		thorr.setTranslateX(650);
	            		thorr.setTranslateY(80);
	            		b1.setGraphic(new ImageView(c13));
	            		button1.setGraphic(new ImageView(thirteen));
	            		buttons.set(count, button1);
	            		button1.setAccessibleText("Thor");
	            	}
	            	else if(count==1) {
	            		thorr.setVisible(true);
	            		thorr.setTranslateX(735);
	            		thorr.setTranslateY(80);
	            		b2.setGraphic(new ImageView(c13));
	            		button2.setGraphic(new ImageView(thirteen));
	            		buttons.set(count, button2);
	            		button2.setAccessibleText("Thor");
	            	}
	            	else if(count==2) {
	            		thorr.setVisible(true);
	            		thorr.setTranslateX(820);
	            		thorr.setTranslateY(80);
	            		b3.setGraphic(new ImageView(c13));
	            		button3.setGraphic(new ImageView(thirteen));
	            		buttons.set(count, button3);
	            		button3.setAccessibleText("Thor");
	            	}
	            	else if(count==3) {
	            		thorr.setVisible(true);
	            		thorr.setTranslateX(650);
	            		thorr.setTranslateY(210);
	            		b4.setGraphic(new ImageView(c13));
	            		button4.setGraphic(new ImageView(thirteen));
	            		buttons.set(count, button4);
	            		button4.setAccessibleText("Thor");
	            	}
	            	else if(count==4) {
	            		thorr.setVisible(true);
	            		thorr.setTranslateX(735);
	            		thorr.setTranslateY(210);
	            		b5.setGraphic(new ImageView(c13));
	            		button5.setGraphic(new ImageView(thirteen));
	            		buttons.set(count, button5);
	            		button5.setAccessibleText("Thor");
	            	}
	            	else if(count==5) {
	            		thorr.setVisible(true);
	            		thorr.setTranslateX(820);
	            		thorr.setTranslateY(210);
	            		b6.setGraphic(new ImageView(c13));
	            		button6.setGraphic(new ImageView(thirteen));
	            		buttons.set(count, button6);
	            		button6.setAccessibleText("Thor");
	            	}
	            	count++;
	            }
	            else if(click.getClickCount() == 1) {
	            	Hero h7 = ((Hero)availableChampions.get(12));
	            	champion.setText(h7.toString());
	            	champion.setVisible(true);
	            }
	        }
	    });
	    venom.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent click) {
	        	if(click.getClickCount() == 2) {
	            	if(game.getFirstPlayer().getTeam().size()<3) {
	            		game.getTurnOrder().insert(availableChampions.get(13));
	            		game.getFirstPlayer().getTeam().add(availableChampions.get(13));
	            	}
	            	else if(game.getFirstPlayer().getTeam().size()==3 && game.getSecondPlayer().getTeam().size()<3) {
	            		game.getTurnOrder().insert(availableChampions.get(13));
	            		game.getSecondPlayer().getTeam().add(availableChampions.get(13));
	            	}
	            	if(game.getSecondPlayer().getTeam().size()==3 && game.getFirstPlayer().getTeam().size()==3) {
		            	captainAmerica.setDisable(true);
	            		deadpool.setDisable(true);
	            		electro.setDisable(true);
	            		ghostRider.setDisable(true);
	            		hela.setDisable(true);
	            		hulk.setDisable(true);
	            		iceMan.setDisable(true);
	            		ironMan.setDisable(true);
	            		loki.setDisable(true);
	            		quickSilver.setDisable(true);
	            		spiderMan.setDisable(true);
	            		drStrange.setDisable(true);
	            		thor.setDisable(true);
	            		yellowJacket.setDisable(true);
	            	}
	            	venom.setDisable(true);
	            	champion.setVisible(false);
	            	if(count==0) {
	            		ven.setVisible(true);
	            		ven.setTranslateX(650);
	            		ven.setTranslateY(80);
	            		b1.setGraphic(new ImageView(c14));
	            		button1.setGraphic(new ImageView(fourteen));
	            		buttons.set(count, button1);
	            		button1.setAccessibleText("Venom");
	            	}
	            	else if(count==1) {
	            		ven.setVisible(true);
	            		ven.setTranslateX(735);
	            		ven.setTranslateY(80);
	            		b2.setGraphic(new ImageView(c14));
	            		button2.setGraphic(new ImageView(fourteen));
	            		buttons.set(count, button2);
	            		button2.setAccessibleText("Venom");
	            	}
	            	else if(count==2) {
	            		ven.setVisible(true);
	            		ven.setTranslateX(820);
	            		ven.setTranslateY(80);
	            		b3.setGraphic(new ImageView(c14));
	            		button3.setGraphic(new ImageView(fourteen));
	            		buttons.set(count, button3);
	            		button3.setAccessibleText("Venom");
	            	}
	            	else if(count==3) {
	            		ven.setVisible(true);
	            		ven.setTranslateX(650);
	            		ven.setTranslateY(210);
	            		b4.setGraphic(new ImageView(c14));
	            		button4.setGraphic(new ImageView(fourteen));
	            		buttons.set(count, button4);
	            		button4.setAccessibleText("Venom");
	            	}
	            	else if(count==4) {
	            		ven.setVisible(true);
	            		ven.setTranslateX(735);
	            		ven.setTranslateY(210);
	            		b5.setGraphic(new ImageView(c14));
	            		button5.setGraphic(new ImageView(fourteen));
	            		buttons.set(count, button5);
	            		button5.setAccessibleText("Venom");
	            	}
	            	else if(count==5) {
	            		ven.setVisible(true);
	            		ven.setTranslateX(820);
	            		ven.setTranslateY(210);
	            		b6.setGraphic(new ImageView(c14));
	            		button6.setGraphic(new ImageView(fourteen));
	            		buttons.set(count, button6);
	            		button6.setAccessibleText("Venom");
	            	}
	            	count++;
	            }
	            else if(click.getClickCount() == 1) {
	            	AntiHero ah3 = ((AntiHero)availableChampions.get(13));
	            	champion.setText(ah3.toString());
	            	champion.setVisible(true);
	            }
	        }
	    });
	    yellowJacket.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent click) {
	        	if(click.getClickCount() == 2) {
	            	if(game.getFirstPlayer().getTeam().size()<3) {
	            		game.getTurnOrder().insert(availableChampions.get(14));
	            		game.getFirstPlayer().getTeam().add(availableChampions.get(14));
	            	}
	            	else if(game.getSecondPlayer().getTeam().size()<3) {
	            		game.getTurnOrder().insert(availableChampions.get(14));
	            		game.getSecondPlayer().getTeam().add(availableChampions.get(14));
	            	}
	            	if(game.getSecondPlayer().getTeam().size()==3 && game.getFirstPlayer().getTeam().size()==3){
		            	captainAmerica.setDisable(true);
	            		deadpool.setDisable(true);
	            		electro.setDisable(true);
	            		ghostRider.setDisable(true);
	            		hela.setDisable(true);
	            		hulk.setDisable(true);
	            		iceMan.setDisable(true);
	            		ironMan.setDisable(true);
	            		loki.setDisable(true);
	            		quickSilver.setDisable(true);
	            		spiderMan.setDisable(true);
	            		drStrange.setDisable(true);
	            		thor.setDisable(true);
	            		venom.setDisable(true);
	            	}
	            	yellowJacket.setDisable(true);
	            	champion.setVisible(false);
	            	if(count==0) {
	            		yellowj.setVisible(true);
	            		yellowj.setTranslateX(650);
	            		yellowj.setTranslateY(80);
	            		b1.setGraphic(new ImageView(c15));
	            		button1.setGraphic(new ImageView(fifteen));
	            		buttons.set(count, button1);
	            		button1.setAccessibleText("Yellow Jacket");
	            	}
	            	else if(count==1) {
	            		yellowj.setVisible(true);
	            		yellowj.setTranslateX(735);
	            		yellowj.setTranslateY(80);
	            		b2.setGraphic(new ImageView(c15));
	            		button2.setGraphic(new ImageView(fifteen));
	            		buttons.set(count, button2);
	            		button2.setAccessibleText("Yellow Jacket");
	            	}
	            	else if(count==2) {
	            		yellowj.setVisible(true);
	            		yellowj.setTranslateX(820);
	            		yellowj.setTranslateY(80);
	            		b3.setGraphic(new ImageView(c15));
	            		button3.setGraphic(new ImageView(fifteen));
	            		buttons.set(count, button3);
	            		button3.setAccessibleText("Yellow Jacket");
	            	}
	            	else if(count==3) {
	            		yellowj.setVisible(true);
	            		yellowj.setTranslateX(650);
	            		yellowj.setTranslateY(210);
	            		b4.setGraphic(new ImageView(c15));
	            		button4.setGraphic(new ImageView(fifteen));
	            		buttons.set(count, button4);
	            		button4.setAccessibleText("Yellow Jacket");
	            	}
	            	else if(count==4) {
	            		yellowj.setVisible(true);
	            		yellowj.setTranslateX(735);
	            		yellowj.setTranslateY(210);
	            		b5.setGraphic(new ImageView(c15));
	            		button5.setGraphic(new ImageView(fifteen));
	            		buttons.set(count, button5);
	            		button5.setAccessibleText("Yellow Jacket");
	            	}
	            	else if(count==5) {
	            		yellowj.setVisible(true);
	            		yellowj.setTranslateX(820);
	            		yellowj.setTranslateY(210);
	            		b6.setGraphic(new ImageView(c15));
	            		button6.setGraphic(new ImageView(fifteen));
	            		buttons.set(count, button6);
	            		button6.setAccessibleText("Yellow Jacket");
	            	}
	            	count++;
	            }
	            else if(click.getClickCount() == 1) {
	            	Villain v5 = ((Villain)availableChampions.get(14));
	            	champion.setText(v5.toString());
	            	champion.setVisible(true);
	            }
	        }
	    });
	    //current champion details
	    currChamp = new Label();
	    currChamp.setTextFill(Color.web("#FFFFFF"));
	    currChamp.setFont(Font.loadFont(Files.newInputStream(Paths.get("fonts/MusticaPro-SemiBold.otf")), 10));
	    currChamp.setVisible(true);
	    currChamp.setMinSize(270, 400);
	    GridPane.setConstraints(currChamp, 0, 0);
	    Grid6.getChildren().addAll(currChamp);
	    Grid6.setTranslateX(10);
	    Grid6.setTranslateY(80);
	    //turn order label
	    trnOrder = new Label();
	    trnOrder.setTextFill(Color.web("#FFFFFF"));
	    trnOrder.setFont(Font.loadFont(Files.newInputStream(Paths.get("fonts/MusticaPro-SemiBold.otf")), 10));
	    trnOrder.setVisible(true);
	    trnOrder.setMinSize(1000, 30);
	    trnOrder.setAlignment(Pos.CENTER);
	    trnOrder.setFont(Font.loadFont(Files.newInputStream(Paths.get("fonts/Akira Expanded Demo.otf")), 12));
	    GridPane.setConstraints(trnOrder, 0, 0);
	    Grid7.getChildren().addAll(trnOrder);
	    Grid7.setTranslateX(0);
	    Grid7.setTranslateY(520);
	    //covers and startgame method
	    Button strtgame = new Button();
	    strtgame.setGraphic(new ImageView(startbtn));
	    strtgame.setTranslateX(500);
	    strtgame.setTranslateY(500);
	    strtgame.setDisable(true);
	    strtgame.setOnMouseEntered(e-> {
	    	strtgame.setOpacity(0.5);
	    });
	    strtgame.setOnMouseExited(e->{
	    	strtgame.setOpacity(100);
	    });
	    Gridp1.getChildren().addAll(b1,b2,b3);
	    Gridp2.getChildren().addAll(b4,b5,b6);
	    cover1 = new Button();
	    cover1.setGraphic(new ImageView(cover));
	    cover1.setBackground(null);
	    cover1.setEffect(White);
	    cover1.setMaxWidth(75);
	    cover1.setMaxHeight(75);
	    cover1.setTranslateX(330 + ((int)(covers.get(0).getLocation().getY())*85));
	    cover1.setTranslateY(451 - ((int)(covers.get(0).getLocation().getX())*85));
	    cover2 = new Button();
	    cover2.setGraphic(new ImageView(cover));
	    cover2.setBackground(null);
	    cover2.setEffect(White);
	    cover2.setMaxWidth(75);
	    cover2.setMaxHeight(75);
	    cover2.setTranslateX(330 + ((int)(covers.get(1).getLocation().getY())*85));
	    cover2.setTranslateY(451 - ((int)(covers.get(1).getLocation().getX())*85));
	    cover3 = new Button();
	    cover3.setGraphic(new ImageView(cover));
	    cover3.setBackground(null);
	    cover3.setEffect(White);
	    cover3.setMaxWidth(75);
	    cover3.setMaxHeight(75);
	    cover3.setTranslateX(330 + (covers.get(2).getLocation().getY()*85));
	    cover3.setTranslateY(451 - (covers.get(2).getLocation().getX()*85));
	    cover4 = new Button();
	    cover4.setGraphic(new ImageView(cover));
	    cover4.setBackground(null);
	    cover4.setEffect(White);
	    cover4.setMaxWidth(75);
	    cover4.setMaxHeight(75);
	    cover4.setTranslateX(330 + (covers.get(3).getLocation().getY()*85));
	    cover4.setTranslateY(451 - (covers.get(3).getLocation().getX()*85));
	    cover5 = new Button();
	    cover5.setGraphic(new ImageView(cover));
	    cover5.setBackground(null);
	    cover5.setEffect(White);
	    cover5.setMaxWidth(75);
	    cover5.setMaxHeight(75);
	    cover5.setTranslateX(330 + (covers.get(4).getLocation().getY()*85));
	    cover5.setTranslateY(451 - (covers.get(4).getLocation().getX()*85));
	    //choosing leaders  
	    b1.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				game.getFirstPlayer().setLeader(game.getFirstPlayer().getTeam().get(0));
				b1.setDisable(true);
				b2.setDisable(true);
				b3.setDisable(true);
				game.placeChampions();
				s = getTurns();
				trnOrder.setText("Turn order: " +s);
			}
	    });
	    b2.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				game.getFirstPlayer().setLeader(game.getFirstPlayer().getTeam().get(1));
				b1.setDisable(true);
				b2.setDisable(true);
				b3.setDisable(true);
				game.placeChampions();
				s = getTurns();
				trnOrder.setText("Turn order: " +s);
			}
	    });
	    b3.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				game.getFirstPlayer().setLeader(game.getFirstPlayer().getTeam().get(2));
				b1.setDisable(true);
				b2.setDisable(true);
				b3.setDisable(true);
				game.placeChampions();
				s = getTurns();
				trnOrder.setText("Turn order: " +s);
			}
	    });
	    b4.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				game.getSecondPlayer().setLeader(game.getSecondPlayer().getTeam().get(0));
				b4.setDisable(true);
				b5.setDisable(true);
				b6.setDisable(true);
				currChamp.setText(game.getCurrentChampion().stringgame());
			}
	    });
	    b5.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				game.getSecondPlayer().setLeader(game.getSecondPlayer().getTeam().get(1));
				b4.setDisable(true);
				b5.setDisable(true);
				b6.setDisable(true);
				currChamp.setText(game.getCurrentChampion().stringgame());
			}
	    });
	    b6.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				game.getSecondPlayer().setLeader(game.getSecondPlayer().getTeam().get(2));
				b4.setDisable(true);
				b5.setDisable(true);
				b6.setDisable(true);
				currChamp.setText(game.getCurrentChampion().stringgame());
			}
	    });
	    //covers labels
	    Label CoverInfo = new Label();
	    ExceptionInfo = new Label();
	    CoverInfo.setFont(Font.loadFont(Files.newInputStream(Paths.get("fonts/MusticaPro-SemiBold.otf")), 12));
	    CoverInfo.setMinSize(281, 281);
	    CoverInfo.setTextFill(Color.web("#FFFFFF"));
	    ExceptionInfo.setFont(Font.loadFont(Files.newInputStream(Paths.get("fonts/MusticaPro-SemiBold.otf")), 12));
	    ExceptionInfo.setMinSize(281, 281);
	    ExceptionInfo.setTextFill(Color.web("#FFFFFF"));
	    //get covers hp
	    cover1.setOnMouseEntered(e->{
	    	BorderPane rootCover = new BorderPane();
			Scene coverScene = new Scene(rootCover,281,281);
			GridPane covergrid = new GridPane();
			covergrid.getChildren().add(CoverInfo);
			covergrid.setTranslateX(80);
			covergrid.setTranslateY(0);
			CoverInfo.setText("Cover HP: " + covers.get(0).getCurrentHP()+ ".");
			rootCover.getChildren().addAll(new ImageView(coversHover),covergrid);
			temp.setAlwaysOnTop(true);
			temp.setMaxHeight(281);
			temp.setMaxWidth(281);
			temp.setMaximized(true);
	    	temp.setScene(coverScene);
	    	temp.show();
	    });
	    cover1.setOnMouseExited(e->{
	    	temp.close();
	    });
	    cover2.setOnMouseEntered(e->{
	    	BorderPane rootCover = new BorderPane();
			Scene coverScene = new Scene(rootCover,281,281);
			GridPane covergrid = new GridPane();
			covergrid.getChildren().add(CoverInfo);
			covergrid.setTranslateX(80);
			covergrid.setTranslateY(0);
			CoverInfo.setText("Cover HP: " + covers.get(1).getCurrentHP()+ ".");
			rootCover.getChildren().addAll(new ImageView(coversHover),covergrid);
			temp.setAlwaysOnTop(true);
			temp.setMaxHeight(281);
			temp.setMaxWidth(281);
			temp.setMaximized(true);
	    	temp.setScene(coverScene);
	    	temp.show();
	    });
	    cover2.setOnMouseExited(e->{
	    	temp.close();
	    });
	    cover3.setOnMouseEntered(e->{
	    	BorderPane rootCover = new BorderPane();
			Scene coverScene = new Scene(rootCover,281,281);
			GridPane covergrid = new GridPane();
			covergrid.getChildren().add(CoverInfo);
			covergrid.setTranslateX(80);
			covergrid.setTranslateY(0);
			CoverInfo.setText("Cover HP: " + covers.get(2).getCurrentHP()+ ".");
			rootCover.getChildren().addAll(new ImageView(coversHover),covergrid);
			temp.setAlwaysOnTop(true);
			temp.setMaxHeight(281);
			temp.setMaxWidth(281);
			temp.setMaximized(true);
	    	temp.setScene(coverScene);
	    	temp.show();
	    });
	    cover3.setOnMouseExited(e->{
	    	temp.close();
	    });
	    cover4.setOnMouseEntered(e->{
	    	BorderPane rootCover = new BorderPane();
			Scene coverScene = new Scene(rootCover,281,281);
			GridPane covergrid = new GridPane();
			covergrid.getChildren().add(CoverInfo);
			covergrid.setTranslateX(80);
			covergrid.setTranslateY(0);
			CoverInfo.setText("Cover HP: " + covers.get(3).getCurrentHP()+ ".");
			rootCover.getChildren().addAll(new ImageView(coversHover),covergrid);
			temp.setAlwaysOnTop(true);
			temp.setMaxHeight(281);
			temp.setMaxWidth(281);
			temp.setMaximized(true);
	    	temp.setScene(coverScene);
	    	temp.show();
	    });
	    cover4.setOnMouseExited(e->{
	    	temp.close();
	    });
	    cover5.setOnMouseEntered(e->{
	    	BorderPane rootCover = new BorderPane();
			Scene coverScene = new Scene(rootCover,281,281);
			GridPane covergrid = new GridPane();
			covergrid.getChildren().add(CoverInfo);
			covergrid.setTranslateX(80);
			covergrid.setTranslateY(0);
			CoverInfo.setText("Cover HP: " + covers.get(4).getCurrentHP()+ ".");
			rootCover.getChildren().addAll(new ImageView(coversHover),covergrid);
			temp.setAlwaysOnTop(true);
			temp.setMaxHeight(281);
			temp.setMaxWidth(281);
			temp.setMaximized(true);
	    	temp.setScene(coverScene);
	    	temp.show();
	    });
	    cover5.setOnMouseExited(e->{
	    	temp.close();
	    });
	  //get remaining champions info
	    button1.setOnMouseEntered(e->{
	    	BorderPane rootChamp = new BorderPane();
			Scene champScene = new Scene(rootChamp,282,562);
			GridPane covergrid = new GridPane();
			covergrid.getChildren().add(CoverInfo);
			covergrid.setTranslateX(20);
			covergrid.setTranslateY(0);
			for(int j=0;j<teams.size();j++) {
				if(teams.get(j).getName().equals(button1.getAccessibleText())) {
					if(game.getFirstPlayer().getLeader()==teams.get(j)) {
						if(game.isFirstLeaderAbilityUsed()) {
							CoverInfo.setText(teams.get(j).stringremaining() + "Leader. \nLeader ability used.");
						} else {
							CoverInfo.setText(teams.get(j).stringremaining() + "Leader. \nLeader ability not used");
						}
					} else {
						CoverInfo.setText(teams.get(j).stringremaining());
					}
				}
			}
			rootChamp.getChildren().addAll(new ImageView(champsHover),covergrid);
			temp.setAlwaysOnTop(true);
			temp.setMaxHeight(300);
			temp.setMaxWidth(282);
			temp.setMaximized(true);
	    	temp.setScene(champScene);
	    	temp.show();
	    });
	    button1.setOnMouseExited(e->{
	    	temp.close();
	    });
	    button2.setOnMouseEntered(e->{
	    	BorderPane rootChamp = new BorderPane();
			Scene champScene = new Scene(rootChamp,282,562);
			GridPane covergrid = new GridPane();
			covergrid.getChildren().add(CoverInfo);
			covergrid.setTranslateX(20);
			covergrid.setTranslateY(0);
			for(int j=0;j<teams.size();j++) {
				if(teams.get(j).getName().equals(button2.getAccessibleText())) {
					if(game.getFirstPlayer().getLeader()==teams.get(j)) {
						if(game.isFirstLeaderAbilityUsed()) {
							CoverInfo.setText(teams.get(j).stringremaining() + "Leader. \nLeader ability used.");
						} else {
							CoverInfo.setText(teams.get(j).stringremaining() + "Leader. \nLeader ability not used");
						}
					} else {
						CoverInfo.setText(teams.get(j).stringremaining());
					}
				}
			}
			rootChamp.getChildren().addAll(new ImageView(champsHover),covergrid);
			temp.setAlwaysOnTop(true);
			temp.setMaxHeight(300);
			temp.setMaxWidth(282);
			temp.setMaximized(true);
	    	temp.setScene(champScene);
	    	temp.show();
	    });
	    button2.setOnMouseExited(e->{
	    	temp.close();
	    });
	    button3.setOnMouseEntered(e->{
	    	BorderPane rootChamp = new BorderPane();
			Scene champScene = new Scene(rootChamp,282,562);
			GridPane covergrid = new GridPane();
			covergrid.getChildren().add(CoverInfo);
			covergrid.setTranslateX(20);
			covergrid.setTranslateY(0);
			for(int j=0;j<teams.size();j++) {
				if(teams.get(j).getName().equals(button3.getAccessibleText())) {
					if(game.getFirstPlayer().getLeader()==teams.get(j)) {
						if(game.isFirstLeaderAbilityUsed()) {
							CoverInfo.setText(teams.get(j).stringremaining() + "Leader. \nLeader ability used.");
						} else {
							CoverInfo.setText(teams.get(j).stringremaining() + "Leader. \nLeader ability not used");
						}
					} else {
						CoverInfo.setText(teams.get(j).stringremaining());
					}
				}
			}
			rootChamp.getChildren().addAll(new ImageView(champsHover),covergrid);
			temp.setAlwaysOnTop(true);
			temp.setMaxHeight(300);
			temp.setMaxWidth(282);
			temp.setMaximized(true);
	    	temp.setScene(champScene);
	    	temp.show();
	    });
	    button3.setOnMouseExited(e->{
	    	temp.close();
	    });
	    button4.setOnMouseEntered(e->{
	    	BorderPane rootChamp = new BorderPane();
			Scene champScene = new Scene(rootChamp,282,562);
			GridPane covergrid = new GridPane();
			covergrid.getChildren().add(CoverInfo);
			covergrid.setTranslateX(20);
			covergrid.setTranslateY(0);
			for(int j=0;j<teams.size();j++) {
				if(teams.get(j).getName().equals(button4.getAccessibleText())) {
					if(game.getSecondPlayer().getLeader()==teams.get(j)) {
						if(game.isSecondLeaderAbilityUsed()) {
							CoverInfo.setText(teams.get(j).stringremaining() + "Leader. \nLeader ability used.");
						} else {
							CoverInfo.setText(teams.get(j).stringremaining() + "Leader. \nLeader ability not used");
						}
					} else {
						CoverInfo.setText(teams.get(j).stringremaining());
					}
				}
			}
			rootChamp.getChildren().addAll(new ImageView(champsHover),covergrid);
			temp.setAlwaysOnTop(true);
			temp.setMaxHeight(300);
			temp.setMaxWidth(282);
			temp.setMaximized(true);
	    	temp.setScene(champScene);
	    	temp.show();
	    });
	    button4.setOnMouseExited(e->{
	    	temp.close();
	    });
	    button5.setOnMouseEntered(e->{
	    	BorderPane rootChamp = new BorderPane();
			Scene champScene = new Scene(rootChamp,282,562);
			GridPane covergrid = new GridPane();
			covergrid.getChildren().add(CoverInfo);
			covergrid.setTranslateX(20);
			covergrid.setTranslateY(0);
			for(int j=0;j<teams.size();j++) {
				if(teams.get(j).getName().equals(button5.getAccessibleText())) {
					if(game.getSecondPlayer().getLeader()==teams.get(j)) {
						if(game.isSecondLeaderAbilityUsed()) {
							CoverInfo.setText(teams.get(j).stringremaining() + "Leader. \nLeader ability used.");
						} else {
							CoverInfo.setText(teams.get(j).stringremaining() + "Leader. \nLeader ability not used");
						}
					} else {
						CoverInfo.setText(teams.get(j).stringremaining());
					}
				}
			}
			rootChamp.getChildren().addAll(new ImageView(champsHover),covergrid);
			temp.setAlwaysOnTop(true);
			temp.setMaxHeight(300);
			temp.setMaxWidth(282);
			temp.setMaximized(true);
	    	temp.setScene(champScene);
	    	temp.show();
	    });
	    button5.setOnMouseExited(e->{
	    	temp.close();
	    });
	    button6.setOnMouseEntered(e->{
	    	BorderPane rootChamp = new BorderPane();
			Scene champScene = new Scene(rootChamp,282,562);
			GridPane covergrid = new GridPane();
			covergrid.getChildren().add(CoverInfo);
			covergrid.setTranslateX(20);
			covergrid.setTranslateY(0);
			for(int j=0;j<teams.size();j++) {
				if(teams.get(j).getName().equals(button6.getAccessibleText())) {
					if(game.getSecondPlayer().getLeader()==teams.get(j)) {
						if(game.isSecondLeaderAbilityUsed()) {
							CoverInfo.setText(teams.get(j).stringremaining() + "Leader. \nLeader ability used.");
						} else {
							CoverInfo.setText(teams.get(j).stringremaining() + "Leader. \nLeader ability not used");
						}
					} else {
						CoverInfo.setText(teams.get(j).stringremaining());
					}
				}
			}
			rootChamp.getChildren().addAll(new ImageView(champsHover),covergrid);
			temp.setAlwaysOnTop(true);
			temp.setMaxHeight(300);
			temp.setMaxWidth(282);
			temp.setMaximized(true);
	    	temp.setScene(champScene);
	    	temp.show();
	    });
	    button6.setOnMouseExited(e->{
	    	temp.close();
	    });
	    //move
	    up.setOnMouseClicked(e->{
	    	try {
				game.move(Direction.UP);
				updateBoard();
				currChamp.setText(game.getCurrentChampion().stringgame());
			} catch (UnallowedMovementException e1) {
				BorderPane rootUp = new BorderPane();
				Scene upScene = new Scene(rootUp,281,281);
				GridPane covergrid = new GridPane();
				Button back = new Button();
				back.setGraphic(new ImageView(backbtn));
				back.setBackground(null);
				covergrid.getChildren().addAll(ExceptionInfo,back);
				covergrid.setTranslateX(40);
				covergrid.setTranslateY(-100);
				covergrid.setVgap(0);
				GridPane.setConstraints(ExceptionInfo, 0, 0);
				GridPane.setConstraints(back, 0, 1);
				ExceptionInfo.setText("You can't move to \nthis position.");
				rootUp.getChildren().addAll(new ImageView(coversHover),covergrid);
				temp1.setAlwaysOnTop(true);
				temp1.setMaxHeight(281);
				temp1.setMaxWidth(281);
				temp1.setMaximized(true);
		    	temp1.setScene(upScene);
		    	temp1.show();
		    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent arg0) {
						temp1.close();
					}
		    	});
			} catch (NotEnoughResourcesException e1) {
				BorderPane rootUp = new BorderPane();
				Scene upScene = new Scene(rootUp,281,281);
				GridPane covergrid = new GridPane();
				Button back = new Button();
				back.setGraphic(new ImageView(backbtn));
				back.setBackground(null);
				covergrid.getChildren().addAll(ExceptionInfo,back);
				covergrid.setTranslateX(40);
				covergrid.setTranslateY(-100);
				covergrid.setVgap(0);
				GridPane.setConstraints(ExceptionInfo, 0, 0);
				GridPane.setConstraints(back, 0, 1);
				ExceptionInfo.setText("You don't have \nenough resources.");
				rootUp.getChildren().addAll(new ImageView(coversHover),covergrid);
				temp1.setAlwaysOnTop(true);
				temp1.setMaxHeight(281);
				temp1.setMaxWidth(281);
				temp1.setMaximized(true);
		    	temp1.setScene(upScene);
		    	temp1.show();
		    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent arg0) {
						temp1.close();
					}
				});
			}
	    });
	    down.setOnMouseClicked(e->{
	    	try {
				game.move(Direction.DOWN);
				updateBoard();
				currChamp.setText(game.getCurrentChampion().stringgame());
			} catch (UnallowedMovementException e1) {
				BorderPane rootDown = new BorderPane();
				Scene downScene = new Scene(rootDown,281,281);
				GridPane covergrid = new GridPane();
				Button back = new Button();
				back.setGraphic(new ImageView(backbtn));
				back.setBackground(null);
				covergrid.getChildren().addAll(ExceptionInfo,back);
				covergrid.setTranslateX(40);
				covergrid.setTranslateY(-100);
				covergrid.setVgap(0);
				GridPane.setConstraints(ExceptionInfo, 0, 0);
				GridPane.setConstraints(back, 0, 1);
				ExceptionInfo.setText("You can't move \nto this position.");
				rootDown.getChildren().addAll(new ImageView(coversHover),covergrid);
				temp1.setAlwaysOnTop(true);
				temp1.setMaxHeight(281);
				temp1.setMaxWidth(281);
				temp1.setMaximized(true);
		    	temp1.setScene(downScene);
		    	temp1.show();
		    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent arg0) {
						temp1.close();
					}
				});
			} catch (NotEnoughResourcesException e1) {
				BorderPane rootDown = new BorderPane();
				Scene downScene = new Scene(rootDown,281,281);
				GridPane covergrid = new GridPane();
				Button back = new Button();
				back.setGraphic(new ImageView(backbtn));
				back.setBackground(null);
				covergrid.getChildren().addAll(ExceptionInfo,back);
				covergrid.setTranslateX(40);
				covergrid.setTranslateY(-100);
				covergrid.setVgap(0);
				GridPane.setConstraints(ExceptionInfo, 0, 0);
				GridPane.setConstraints(back, 0, 1);
				ExceptionInfo.setText("You don't have \nenough resources.");
				rootDown.getChildren().addAll(new ImageView(coversHover),covergrid);
				temp1.setAlwaysOnTop(true);
				temp1.setMaxHeight(281);
				temp1.setMaxWidth(281);
				temp1.setMaximized(true);
		    	temp1.setScene(downScene);
		    	temp1.show();
		    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent arg0) {
						temp1.close();
					}
				});
			}
	    });
	    left.setOnMouseClicked(e->{
	    	try {
				game.move(Direction.LEFT);
				updateBoard();
				currChamp.setText(game.getCurrentChampion().stringgame());
			} catch (UnallowedMovementException e1) {
				BorderPane rootLeft = new BorderPane();
				Scene leftScene = new Scene(rootLeft,281,281);
				GridPane covergrid = new GridPane();
				Button back = new Button();
				back.setGraphic(new ImageView(backbtn));
				back.setBackground(null);
				covergrid.getChildren().addAll(ExceptionInfo,back);
				covergrid.setTranslateX(40);
				covergrid.setTranslateY(-100);
				covergrid.setVgap(0);
				GridPane.setConstraints(ExceptionInfo, 0, 0);
				GridPane.setConstraints(back, 0, 1);
				ExceptionInfo.setText("You can't move \nto this position.");
				rootLeft.getChildren().addAll(new ImageView(coversHover),covergrid);
				temp1.setAlwaysOnTop(true);
				temp1.setMaxHeight(281);
				temp1.setMaxWidth(281);
				temp1.setMaximized(true);
		    	temp1.setScene(leftScene);
		    	temp1.show();
		    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent arg0) {
						temp1.close();
					}
				});
			} catch (NotEnoughResourcesException e1) {
				BorderPane rootLeft = new BorderPane();
				Scene leftScene = new Scene(rootLeft,281,281);
				GridPane covergrid = new GridPane();
				Button back = new Button();
				back.setGraphic(new ImageView(backbtn));
				back.setBackground(null);
				covergrid.getChildren().addAll(ExceptionInfo,back);
				covergrid.setTranslateX(40);
				covergrid.setTranslateY(-100);
				covergrid.setVgap(0);
				GridPane.setConstraints(ExceptionInfo, 0, 0);
				GridPane.setConstraints(back, 0, 1);
				ExceptionInfo.setText("You don't have \nenough resources.");
				rootLeft.getChildren().addAll(new ImageView(coversHover),covergrid);
				temp1.setAlwaysOnTop(true);
				temp1.setMaxHeight(281);
				temp1.setMaxWidth(281);
				temp1.setMaximized(true);
		    	temp1.setScene(leftScene);
		    	temp1.show();
		    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent arg0) {
						temp1.close();
					}
				});
			}
	    });
	    right.setOnMouseClicked(e->{ 
	    	try {
				game.move(Direction.RIGHT);
				updateBoard();
				currChamp.setText(game.getCurrentChampion().stringgame());
			} catch (UnallowedMovementException e1) {
				BorderPane rootRight = new BorderPane();
				Scene rightScene = new Scene(rootRight,281,281);
				GridPane covergrid = new GridPane();
				Button back = new Button();
				back.setGraphic(new ImageView(backbtn));
				back.setBackground(null);
				covergrid.getChildren().addAll(ExceptionInfo,back);
				covergrid.setTranslateX(40);
				covergrid.setTranslateY(-100);
				covergrid.setVgap(0);
				GridPane.setConstraints(ExceptionInfo, 0, 0);
				GridPane.setConstraints(back, 0, 1);
				ExceptionInfo.setText("You can't move \nto this position.");
				rootRight.getChildren().addAll(new ImageView(coversHover),covergrid);
				temp1.setAlwaysOnTop(true);
				temp1.setMaxHeight(281);
				temp1.setMaxWidth(281);
				temp1.setMaximized(true);
		    	temp1.setScene(rightScene);
		    	temp1.show();
		    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent arg0) {
						temp1.close();
					}
				});
			} catch (NotEnoughResourcesException e1) {
				BorderPane rootRight = new BorderPane();
				Scene rightScene = new Scene(rootRight,281,281);
				GridPane covergrid = new GridPane();
				Button back = new Button();
				back.setGraphic(new ImageView(backbtn));
				back.setBackground(null);
				covergrid.getChildren().addAll(ExceptionInfo,back);
				covergrid.setTranslateX(40);
				covergrid.setTranslateY(-100);
				covergrid.setVgap(0);
				GridPane.setConstraints(ExceptionInfo, 0, 0);
				GridPane.setConstraints(back, 0, 1);
				ExceptionInfo.setText("You don't have \nenough resources.");
				rootRight.getChildren().addAll(new ImageView(coversHover),covergrid);
				temp1.setAlwaysOnTop(true);
				temp1.setMaxHeight(281);
				temp1.setMaxWidth(281);
				temp1.setMaximized(true);
		    	temp1.setScene(rightScene);
		    	temp1.show();
		    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent arg0) {
						temp1.close();
					}
				});
			}
	    });
	    //attack
	    attack.setOnMouseClicked(e->
	    attack.setOnKeyPressed(event->{
	    	if(event.getCode()==KeyCode.W) {
		    	try {
					game.attack(Direction.UP);
					currChamp.setText(game.getCurrentChampion().stringgame());
					s =getTurns();
					trnOrder.setText("Turn Order: "+s);
					updateBoard();
					winner = game.checkGameOver();
					if(winner==game.getFirstPlayer()) {
						Winner.setText(n1);
						primaryStage.setScene(winScene);
						battlescenePlayer.setAutoPlay(false);
						battlescenePlayer.setMute(true);
						winningPlayer.setAutoPlay(true);
					}
					else if (winner==game.getSecondPlayer()) {
						Winner.setText(n2);
						primaryStage.setScene(winScene);
						battlescenePlayer.setAutoPlay(false);
						battlescenePlayer.setMute(true);
						winningPlayer.setAutoPlay(true);
					}
				} catch (ChampionDisarmedException e1) {
					BorderPane rootAttack = new BorderPane();
					Scene attackScene = new Scene(rootAttack,281,281);
					GridPane covergrid = new GridPane();
					Button back = new Button();
					back.setGraphic(new ImageView(backbtn));
					back.setBackground(null);
					covergrid.getChildren().addAll(ExceptionInfo,back);
					covergrid.setTranslateX(40);
					covergrid.setTranslateY(-100);
					covergrid.setVgap(0);
					GridPane.setConstraints(ExceptionInfo, 0, 0);
					GridPane.setConstraints(back, 0, 1);
					ExceptionInfo.setText("Champion cannot attack \nwhile disarmed.");
					rootAttack.getChildren().addAll(new ImageView(coversHover),covergrid);
					temp1.setAlwaysOnTop(true);
					temp1.setMaxHeight(281);
					temp1.setMaxWidth(281);
					temp1.setMaximized(true);
			    	temp1.setScene(attackScene);
			    	temp1.show();
			    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent arg0) {
							temp1.close();
						}
					});
				} catch (NotEnoughResourcesException e1) {
					BorderPane rootAttack = new BorderPane();
					Scene attackScene = new Scene(rootAttack,281,281);
					GridPane covergrid = new GridPane();
					Button back = new Button();
					back.setGraphic(new ImageView(backbtn));
					back.setBackground(null);
					covergrid.getChildren().addAll(ExceptionInfo,back);
					covergrid.setTranslateX(40);
					covergrid.setTranslateY(-100);
					covergrid.setVgap(0);
					GridPane.setConstraints(ExceptionInfo, 0, 0);
					GridPane.setConstraints(back, 0, 1);
					ExceptionInfo.setText("You don't have \nenough resources.");
					rootAttack.getChildren().addAll(new ImageView(coversHover),covergrid);
					temp1.setAlwaysOnTop(true);
					temp1.setMaxHeight(281);
					temp1.setMaxWidth(281);
					temp1.setMaximized(true);
			    	temp1.setScene(attackScene);
			    	temp1.show();
			    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent arg0) {
							temp1.close();
						}
					});
				} catch (InvalidTargetException e1) {
					BorderPane rootAttack = new BorderPane();
					Scene attackScene = new Scene(rootAttack,281,281);
					GridPane covergrid = new GridPane();
					Button back = new Button();
					back.setGraphic(new ImageView(backbtn));
					back.setBackground(null);
					covergrid.getChildren().addAll(ExceptionInfo,back);
					covergrid.setTranslateX(40);
					covergrid.setTranslateY(-100);
					covergrid.setVgap(0);
					GridPane.setConstraints(ExceptionInfo, 0, 0);
					GridPane.setConstraints(back, 0, 1);
					ExceptionInfo.setText("Invalid target.");
					rootAttack.getChildren().addAll(new ImageView(coversHover),covergrid);
					temp1.setAlwaysOnTop(true);
					temp1.setMaxHeight(281);
					temp1.setMaxWidth(281);
					temp1.setMaximized(true);
			    	temp1.setScene(attackScene);
			    	temp1.show();
			    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent arg0) {
							temp1.close();
						}
					});
				}
		    }
		    if(event.getCode()==KeyCode.S) {
		    	try {
					game.attack(Direction.DOWN);
					currChamp.setText(game.getCurrentChampion().stringgame());
					s =getTurns();
					trnOrder.setText("Turn Order: "+s);
					updateBoard();
					winner = game.checkGameOver();
					if(winner==game.getFirstPlayer()) {
						Winner.setText(n1);
						primaryStage.setScene(winScene);
						battlescenePlayer.setAutoPlay(false);
						battlescenePlayer.setMute(true);
						winningPlayer.setAutoPlay(true);
					}
					else if (winner==game.getSecondPlayer()) {
						Winner.setText(n2);
						primaryStage.setScene(winScene);
						battlescenePlayer.setAutoPlay(false);
						battlescenePlayer.setMute(true);
						winningPlayer.setAutoPlay(true);
					}
				} catch (ChampionDisarmedException e1) {
					BorderPane rootAttack = new BorderPane();
					Scene attackScene = new Scene(rootAttack,281,281);
					GridPane covergrid = new GridPane();
					Button back = new Button();
					back.setGraphic(new ImageView(backbtn));
					back.setBackground(null);
					covergrid.getChildren().addAll(ExceptionInfo,back);
					covergrid.setTranslateX(40);
					covergrid.setTranslateY(-100);
					covergrid.setVgap(0);
					GridPane.setConstraints(ExceptionInfo, 0, 0);
					GridPane.setConstraints(back, 0, 1);
					ExceptionInfo.setText("Champion cannot attack \nwhile disarmed.");
					rootAttack.getChildren().addAll(new ImageView(coversHover),covergrid);
					temp1.setAlwaysOnTop(true);
					temp1.setMaxHeight(281);
					temp1.setMaxWidth(281);
					temp1.setMaximized(true);
			    	temp1.setScene(attackScene);
			    	temp1.show();
			    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent arg0) {
							temp1.close();
						}
					});
				} catch (NotEnoughResourcesException e1) {
					BorderPane rootAttack = new BorderPane();
					Scene attackScene = new Scene(rootAttack,281,281);
					GridPane covergrid = new GridPane();
					Button back = new Button();
					back.setGraphic(new ImageView(backbtn));
					back.setBackground(null);
					covergrid.getChildren().addAll(ExceptionInfo,back);
					covergrid.setTranslateX(40);
					covergrid.setTranslateY(-100);
					covergrid.setVgap(0);
					GridPane.setConstraints(ExceptionInfo, 0, 0);
					GridPane.setConstraints(back, 0, 1);
					ExceptionInfo.setText("You don't have \nenough resources.");
					rootAttack.getChildren().addAll(new ImageView(coversHover),covergrid);
					temp1.setAlwaysOnTop(true);
					temp1.setMaxHeight(281);
					temp1.setMaxWidth(281);
					temp1.setMaximized(true);
			    	temp1.setScene(attackScene);
			    	temp1.show();
			    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent arg0) {
							temp1.close();
						}
					});
				} catch (InvalidTargetException e1) {
					BorderPane rootAttack = new BorderPane();
					Scene attackScene = new Scene(rootAttack,281,281);
					GridPane covergrid = new GridPane();
					Button back = new Button();
					back.setGraphic(new ImageView(backbtn));
					back.setBackground(null);
					covergrid.getChildren().addAll(ExceptionInfo,back);
					covergrid.setTranslateX(40);
					covergrid.setTranslateY(-100);
					covergrid.setVgap(0);
					GridPane.setConstraints(ExceptionInfo, 0, 0);
					GridPane.setConstraints(back, 0, 1);
					ExceptionInfo.setText("Invalid target.");
					rootAttack.getChildren().addAll(new ImageView(coversHover),covergrid);
					temp1.setAlwaysOnTop(true);
					temp1.setMaxHeight(281);
					temp1.setMaxWidth(281);
					temp1.setMaximized(true);
			    	temp1.setScene(attackScene);
			    	temp1.show();
			    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent arg0) {
							temp1.close();
						}
					});
				}
		    }
		    if(event.getCode()==KeyCode.D) {
		    	try {
					game.attack(Direction.RIGHT);
					currChamp.setText(game.getCurrentChampion().stringgame());
					s =getTurns();
					trnOrder.setText("Turn Order: "+s);
					updateBoard();
					winner = game.checkGameOver();
					if(winner==game.getFirstPlayer()) {
						Winner.setText(n1);
						primaryStage.setScene(winScene);
						battlescenePlayer.setAutoPlay(false);
						battlescenePlayer.setMute(true);
						winningPlayer.setAutoPlay(true);
					}
					else if (winner==game.getSecondPlayer()) {
						Winner.setText(n2);
						primaryStage.setScene(winScene);
						battlescenePlayer.setAutoPlay(false);
						battlescenePlayer.setMute(true);
						winningPlayer.setAutoPlay(true);
					}
				} catch (ChampionDisarmedException e1) {
					BorderPane rootAttack = new BorderPane();
					Scene attackScene = new Scene(rootAttack,281,281);
					GridPane covergrid = new GridPane();
					Button back = new Button();
					back.setGraphic(new ImageView(backbtn));
					back.setBackground(null);
					covergrid.getChildren().addAll(ExceptionInfo,back);
					covergrid.setTranslateX(40);
					covergrid.setTranslateY(-100);
					covergrid.setVgap(0);
					GridPane.setConstraints(ExceptionInfo, 0, 0);
					GridPane.setConstraints(back, 0, 1);
					ExceptionInfo.setText("Champion cannot attack \nwhile disarmed.");
					rootAttack.getChildren().addAll(new ImageView(coversHover),covergrid);
					temp1.setAlwaysOnTop(true);
					temp1.setMaxHeight(281);
					temp1.setMaxWidth(281);
					temp1.setMaximized(true);
			    	temp1.setScene(attackScene);
			    	temp1.show();
			    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent arg0) {
							temp1.close();
						}
					});
				} catch (NotEnoughResourcesException e1) {
					BorderPane rootAttack = new BorderPane();
					Scene attackScene = new Scene(rootAttack,281,281);
					GridPane covergrid = new GridPane();
					Button back = new Button();
					back.setGraphic(new ImageView(backbtn));
					back.setBackground(null);
					covergrid.getChildren().addAll(ExceptionInfo,back);
					covergrid.setTranslateX(40);
					covergrid.setTranslateY(-100);
					covergrid.setVgap(0);
					GridPane.setConstraints(ExceptionInfo, 0, 0);
					GridPane.setConstraints(back, 0, 1);
					ExceptionInfo.setText("You don't have \nenough resources.");
					rootAttack.getChildren().addAll(new ImageView(coversHover),covergrid);
					temp1.setAlwaysOnTop(true);
					temp1.setMaxHeight(281);
					temp1.setMaxWidth(281);
					temp1.setMaximized(true);
			    	temp1.setScene(attackScene);
			    	temp1.show();
			    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent arg0) {
							temp1.close();
						}
					});
				} catch (InvalidTargetException e1) {
					BorderPane rootAttack = new BorderPane();
					Scene attackScene = new Scene(rootAttack,281,281);
					GridPane covergrid = new GridPane();
					Button back = new Button();
					back.setGraphic(new ImageView(backbtn));
					back.setBackground(null);
					covergrid.getChildren().addAll(ExceptionInfo,back);
					covergrid.setTranslateX(40);
					covergrid.setTranslateY(-100);
					covergrid.setVgap(0);
					GridPane.setConstraints(ExceptionInfo, 0, 0);
					GridPane.setConstraints(back, 0, 1);
					ExceptionInfo.setText("Invalid target.");
					rootAttack.getChildren().addAll(new ImageView(coversHover),covergrid);
					temp1.setAlwaysOnTop(true);
					temp1.setMaxHeight(281);
					temp1.setMaxWidth(281);
					temp1.setMaximized(true);
			    	temp1.setScene(attackScene);
			    	temp1.show();
			    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent arg0) {
							temp1.close();
						}
					});
				}
		    }
		    if(event.getCode()==KeyCode.A) {
		    	try {
					game.attack(Direction.LEFT);
					currChamp.setText(game.getCurrentChampion().stringgame());
					s =getTurns();
					trnOrder.setText("Turn Order: "+s);
					updateBoard();
					winner = game.checkGameOver();
					if(winner==game.getFirstPlayer()) {
						Winner.setText(n1);
						primaryStage.setScene(winScene);
						battlescenePlayer.setAutoPlay(false);
						battlescenePlayer.setMute(true);
						winningPlayer.setAutoPlay(true);
					}
					else if (winner==game.getSecondPlayer()) {
						Winner.setText(n2);
						primaryStage.setScene(winScene);
						battlescenePlayer.setAutoPlay(false);
						battlescenePlayer.setMute(true);
						winningPlayer.setAutoPlay(true);
					}
				} catch (ChampionDisarmedException e1) {
					BorderPane rootAttack = new BorderPane();
					Scene attackScene = new Scene(rootAttack,281,281);
					GridPane covergrid = new GridPane();
					Button back = new Button();
					back.setGraphic(new ImageView(backbtn));
					back.setBackground(null);
					covergrid.getChildren().addAll(ExceptionInfo,back);
					covergrid.setTranslateX(40);
					covergrid.setTranslateY(-100);
					covergrid.setVgap(0);
					GridPane.setConstraints(ExceptionInfo, 0, 0);
					GridPane.setConstraints(back, 0, 1);
					ExceptionInfo.setText("Champion cannot attack \nwhile disarmed.");
					rootAttack.getChildren().addAll(new ImageView(coversHover),covergrid);
					temp1.setAlwaysOnTop(true);
					temp1.setMaxHeight(281);
					temp1.setMaxWidth(281);
					temp1.setMaximized(true);
			    	temp1.setScene(attackScene);
			    	temp1.show();
			    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent arg0) {
							temp1.close();
						}
					});
				} catch (NotEnoughResourcesException e1) {
					BorderPane rootAttack = new BorderPane();
					Scene attackScene = new Scene(rootAttack,281,281);
					GridPane covergrid = new GridPane();
					Button back = new Button();
					back.setGraphic(new ImageView(backbtn));
					back.setBackground(null);
					covergrid.getChildren().addAll(ExceptionInfo,back);
					covergrid.setTranslateX(40);
					covergrid.setTranslateY(-100);
					covergrid.setVgap(0);
					GridPane.setConstraints(ExceptionInfo, 0, 0);
					GridPane.setConstraints(back, 0, 1);
					ExceptionInfo.setText("You don't have \nenough resources.");
					rootAttack.getChildren().addAll(new ImageView(coversHover),covergrid);
					temp1.setAlwaysOnTop(true);
					temp1.setMaxHeight(281);
					temp1.setMaxWidth(281);
					temp1.setMaximized(true);
			    	temp1.setScene(attackScene);
			    	temp1.show();
			    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent arg0) {
							temp1.close();
						}
					});
				} catch (InvalidTargetException e1) {
					BorderPane rootAttack = new BorderPane();
					Scene attackScene = new Scene(rootAttack,281,281);
					GridPane covergrid = new GridPane();
					Button back = new Button();
					back.setGraphic(new ImageView(backbtn));
					back.setBackground(null);
					covergrid.getChildren().addAll(ExceptionInfo,back);
					covergrid.setTranslateX(40);
					covergrid.setTranslateY(-100);
					covergrid.setVgap(0);
					GridPane.setConstraints(ExceptionInfo, 0, 0);
					GridPane.setConstraints(back, 0, 1);
					ExceptionInfo.setText("Invalid target.");
					rootAttack.getChildren().addAll(new ImageView(coversHover),covergrid);
					temp1.setAlwaysOnTop(true);
					temp1.setMaxHeight(281);
					temp1.setMaxWidth(281);
					temp1.setMaximized(true);
			    	temp1.setScene(attackScene);
			    	temp1.show();
			    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent arg0) {
							temp1.close();
						}
						
					});
				}
		    }})
	    
	    );
	    //end turn of champion
	    endturn.setOnMouseClicked(e->{
	    	endTurn();
	    	if(winner==game.getFirstPlayer()) {
				Winner.setText(n1);
				primaryStage.setScene(winScene);
				battlescenePlayer.setAutoPlay(false);
				battlescenePlayer.setMute(true);
				winningPlayer.setAutoPlay(true);
			}
			else if (winner==game.getSecondPlayer()) {
				Winner.setText(n2);
				primaryStage.setScene(winScene);
				battlescenePlayer.setAutoPlay(false);
				battlescenePlayer.setMute(true);
				winningPlayer.setAutoPlay(true);
			}
	    });
	    //cast abilities
	    cast1.setOnMouseClicked(e->{
	    	castAbility(cast1,0);
			if(winner==game.getFirstPlayer()) {
				Winner.setText(n1);
				primaryStage.setScene(winScene);
				battlescenePlayer.setAutoPlay(false);
				battlescenePlayer.setMute(true);
				winningPlayer.setAutoPlay(true);
			}
			else if (winner==game.getSecondPlayer()) {
				Winner.setText(n2);
				primaryStage.setScene(winScene);
				battlescenePlayer.setAutoPlay(false);
				battlescenePlayer.setMute(true);
				winningPlayer.setAutoPlay(true);
			}
	    });
	    cast2.setOnMouseClicked(e->{
	    	castAbility(cast2,1);
	    	if(winner==game.getFirstPlayer()) {
				Winner.setText(n1);
				primaryStage.setScene(winScene);
				battlescenePlayer.setAutoPlay(false);
				battlescenePlayer.setMute(true);
				winningPlayer.setAutoPlay(true);
			}
			else if (winner==game.getSecondPlayer()) {
				Winner.setText(n2);
				primaryStage.setScene(winScene);
				battlescenePlayer.setAutoPlay(false);
				battlescenePlayer.setMute(true);
				winningPlayer.setAutoPlay(true);
			}
	    });
	    cast3.setOnMouseClicked(e->{
	    	castAbility(cast3,2);
	    	if(winner==game.getFirstPlayer()) {
				Winner.setText(n1);
				primaryStage.setScene(winScene);
				battlescenePlayer.setAutoPlay(false);
				battlescenePlayer.setMute(true);
				winningPlayer.setAutoPlay(true);
			}
			else if (winner==game.getSecondPlayer()) {
				Winner.setText(n2);
				primaryStage.setScene(winScene);
				battlescenePlayer.setAutoPlay(false);
				battlescenePlayer.setMute(true);
				winningPlayer.setAutoPlay(true);
			}
	    });
	    cast4.setOnMouseClicked(e->{
	    	if(game.getCurrentChampion().getAbilities().size()==4) {
	   			castAbility(cast4,3);
	   			if(winner==game.getFirstPlayer()) {
					Winner.setText(n1);
					primaryStage.setScene(winScene);
					battlescenePlayer.setAutoPlay(false);
					battlescenePlayer.setMute(true);
					winningPlayer.setAutoPlay(true);
				}
				else if (winner==game.getSecondPlayer()) {
					Winner.setText(n2);
					primaryStage.setScene(winScene);
					battlescenePlayer.setAutoPlay(false);
					battlescenePlayer.setMute(true);
					winningPlayer.setAutoPlay(true);
				}
	    	} else {
	    		BorderPane rootLeader = new BorderPane();
				Scene leaderkScene = new Scene(rootLeader,281,281);
				GridPane covergrid = new GridPane();
				Button back = new Button();
				back.setGraphic(new ImageView(backbtn));
				back.setBackground(null);
				covergrid.getChildren().addAll(ExceptionInfo,back);
				covergrid.setTranslateX(40);
				covergrid.setTranslateY(-100);
				covergrid.setVgap(0);
				GridPane.setConstraints(ExceptionInfo, 0, 0);
				GridPane.setConstraints(back, 0, 1);
				ExceptionInfo.setText("This champion does not have a \nfourth ability.");
				rootLeader.getChildren().addAll(new ImageView(coversHover),covergrid);
				temp1.setAlwaysOnTop(true);
				temp1.setMaxHeight(281);
				temp1.setMaxWidth(281);
				temp1.setMaximized(true);
		    	temp1.setScene(leaderkScene);
		    	temp1.show();
		    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent arg0) {
						temp1.close();
					}
				});
	    	}
	    });
	    //use leader ability
	    leader.setOnMouseClicked(e->{
	    	try {
				game.useLeaderAbility();
				updateBoard();
				winner = game.checkGameOver();
				if(winner==game.getFirstPlayer()) {
					Winner.setText(n1);
					primaryStage.setScene(winScene);
					battlescenePlayer.setAutoPlay(false);
					battlescenePlayer.setMute(true);
					winningPlayer.setAutoPlay(true);
				}
				else if (winner==game.getSecondPlayer()) {
					Winner.setText(n2);
					primaryStage.setScene(winScene);
					battlescenePlayer.setAutoPlay(false);
					battlescenePlayer.setMute(true);
					winningPlayer.setAutoPlay(true);
				}
				currChamp.setText(game.getCurrentChampion().stringgame());
			} catch (LeaderNotCurrentException e1) {
				BorderPane rootLeader = new BorderPane();
				Scene leaderkScene = new Scene(rootLeader,281,281);
				GridPane covergrid = new GridPane();
				Button back = new Button();
				back.setGraphic(new ImageView(backbtn));
				back.setBackground(null);
				covergrid.getChildren().addAll(ExceptionInfo,back);
				covergrid.setTranslateX(40);
				covergrid.setTranslateY(-100);
				covergrid.setVgap(0);
				GridPane.setConstraints(ExceptionInfo, 0, 0);
				GridPane.setConstraints(back, 0, 1);
				ExceptionInfo.setText("This champion is not \nyour leader.");
				rootLeader.getChildren().addAll(new ImageView(coversHover),covergrid);
				temp1.setAlwaysOnTop(true);
				temp1.setMaxHeight(281);
				temp1.setMaxWidth(281);
				temp1.setMaximized(true);
		    	temp1.setScene(leaderkScene);
		    	temp1.show();
		    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent arg0) {
						temp1.close();
					}
				});
			} catch (LeaderAbilityAlreadyUsedException e1) {
				BorderPane rootLeader = new BorderPane();
				Scene leaderkScene = new Scene(rootLeader,281,281);
				GridPane covergrid = new GridPane();
				Button back = new Button();
				back.setGraphic(new ImageView(backbtn));
				back.setBackground(null);
				covergrid.getChildren().addAll(ExceptionInfo,back);
				covergrid.setTranslateX(40);
				covergrid.setTranslateY(-100);
				covergrid.setVgap(0);
				GridPane.setConstraints(ExceptionInfo, 0, 0);
				GridPane.setConstraints(back, 0, 1);
				ExceptionInfo.setText("Leader ability has \nalready been used.");
				rootLeader.getChildren().addAll(new ImageView(coversHover),covergrid);
				temp1.setAlwaysOnTop(true);
				temp1.setMaxHeight(281);
				temp1.setMaxWidth(281);
				temp1.setMaximized(true);
		    	temp1.setScene(leaderkScene);
		    	temp1.show();
		    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent arg0) {
						temp1.close();
					}
				});
			}
	    });
	    
	    strtgame.setDisable(false);
	    btn2.setOnAction(e-> {
	    	if(game.getFirstPlayer().getTeam().size()==3 && game.getSecondPlayer().getTeam().size()==3) {
	    		primaryStage.setScene(scene3);
	    	} else {
	    		BorderPane rootLeader = new BorderPane();
				Scene leaderkScene = new Scene(rootLeader,281,281);
				GridPane covergrid = new GridPane();
				Button back = new Button();
				back.setGraphic(new ImageView(backbtn));
				back.setBackground(null);
				covergrid.getChildren().addAll(ExceptionInfo,back);
				covergrid.setTranslateX(40);
				covergrid.setTranslateY(-100);
				covergrid.setVgap(0);
				GridPane.setConstraints(ExceptionInfo, 0, 0);
				GridPane.setConstraints(back, 0, 1);
				ExceptionInfo.setText("Choose both players' teams.");
				rootLeader.getChildren().addAll(new ImageView(coversHover),covergrid);
				temp1.setAlwaysOnTop(true);
				temp1.setMaxHeight(281);
				temp1.setMaxWidth(281);
				temp1.setMaximized(true);
		    	temp1.setScene(leaderkScene);
		    	temp1.show();
		    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent arg0) {
						temp1.close();
					}
				});
	    	}
	    });
	    strtgame.setOnAction(e-> {
	    	if(game.getFirstPlayer().getLeader()!=null && game.getSecondPlayer().getLeader()!=null) {
	    		primaryStage.setScene(scene4);
	    		elsePlayer.setAutoPlay(false);
	    		elsePlayer.setMute(true);
	    		battlescenePlayer.setAutoPlay(true);
	    	} else {
	    		BorderPane rootLeader = new BorderPane();
				Scene leaderkScene = new Scene(rootLeader,281,281);
				GridPane covergrid = new GridPane();
				Button back = new Button();
				back.setGraphic(new ImageView(backbtn));
				back.setBackground(null);
				covergrid.getChildren().addAll(ExceptionInfo,back);
				covergrid.setTranslateX(40);
				covergrid.setTranslateY(-100);
				covergrid.setVgap(0);
				GridPane.setConstraints(ExceptionInfo, 0, 0);
				GridPane.setConstraints(back, 0, 1);
				ExceptionInfo.setText("Choose both players' leaders.");
				rootLeader.getChildren().addAll(new ImageView(coversHover),covergrid);
				temp1.setAlwaysOnTop(true);
				temp1.setMaxHeight(281);
				temp1.setMaxWidth(281);
				temp1.setMaximized(true);
		    	temp1.setScene(leaderkScene);
		    	temp1.show();
		    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent arg0) {
						temp1.close();
					}
				});
	    	}
	    });
	    //grid 2 location
	    Grid2.getChildren().addAll(captainAmerica,deadpool,electro,ghostRider,hela,hulk,iceMan,ironMan,loki,quickSilver,spiderMan,drStrange,thor,venom,yellowJacket);
	    Grid2.setTranslateX(0);
	    Grid2.setTranslateY(0);
	    //grid 3 location
	    Grid3.add(champion, 0, 0);
	    Grid3.setTranslateX(0);
	    Grid3.setTranslateY(120);
	    //grid 4 location
	    Grid4.getChildren().addAll(p1,p2);
	    Grid4.setTranslateX(575);
	    Grid4.setTranslateY(0);
	    //scenes roots
		root.getChildren().addAll(new ImageView(img),btn,Grid,elseView);
		root1.getChildren().addAll(new ImageView(img2),btn2,btn3,btn4,Grid3,Grid2,Grid4,capt,deadp,elec,ghostr,hel,hul,icem,ironm,lok,quicks,spiderm,drs,thorr,ven,yellowj,elseView);
		root2.getChildren().addAll(new ImageView(img3),strtgame,Gridp1,Gridp2,elseView);
		root3.getChildren().addAll(new ImageView(img4),button1,button2,button3,button4,button5,button6,cast1,cast2,cast3,cast4,down,up,right,left,endturn,attack,Grid6,cover1,cover2,cover3,cover4,cover5,Grid7,leader,battlesceneView);
		rootWin.getChildren().addAll(new ImageView(WinBg),Grid8,winningView);
		//set stage
		primaryStage.setScene(scene1);
		primaryStage.show();
	}
	//battle scene update board
	public void updateBoard() {
		boolean[] co = new boolean[5];
		for(int i=0;i<co.length;i++) {
			co[i]=true;
		}
		for(int i=0;i<covers.size();i++) {
			Cover c = covers.get(i);
			if(c.getCurrentHP()<=0) {
				co[i] = false;
			}
		}
		if(co[0]==false) {
			root3.getChildren().remove(cover1);
		}
		if(co[1]==false) {
			root3.getChildren().remove(cover2);
		}
		if(co[2]==false) {
			root3.getChildren().remove(cover3);
		}
		if(co[3]==false) {
			root3.getChildren().remove(cover4);
		}
		if(co[4]==false) {
			root3.getChildren().remove(cover5);
		}
		boolean found = false;
		for(int i=0;i<buttons.size();i++) {
			found = false;
			for(int j=0;j<teams.size();j++) {
				if(teams.get(j).getName().equals(buttons.get(i).getAccessibleText())) {
					int x = (int)teams.get(j).getLocation().getX();
					int y = (int)teams.get(j).getLocation().getY();
					buttons.get(i).setTranslateX(330 + (85*y));
					buttons.get(i).setTranslateY(451 - (85*x));
					found = true;
				}
			}
			if(!found) {
				if(buttons.get(i)==(button1)) {
					button1.setVisible(false);
					root3.getChildren().remove(button1);
					buttons.remove(i);
					i--;
					break;
				}
				if(buttons.get(i)==(button2)) {
					button2.setVisible(false);
					root3.getChildren().remove(button2);
					buttons.remove(i);
					i--;
					break;
				}
				if(buttons.get(i)==(button3)) {
					button3.setVisible(false);
					root3.getChildren().remove(button3);
					buttons.remove(i);
					i--;
					break;
				}
				if(buttons.get(i)==(button4)) {
					button4.setVisible(false);
					root3.getChildren().remove(button4);
					buttons.remove(i);
					i--;
					break;
				}
				if(buttons.get(i)==(button5)) {
					button5.setVisible(false);
					root3.getChildren().remove(button5);
					buttons.remove(i);
					i--;
					break;
				}
				if(buttons.get(i)==(button6)) {
					button6.setVisible(false);
					root3.getChildren().remove(button6);
					buttons.remove(i);
					i--;
					break;
				}
			}
		}
	}
	
	public String getTurns() {
		String names = "";
		turns.clear();
		teams.clear();
		for(int i=0;i<game.getFirstPlayer().getTeam().size();i++) {
			teams.add(game.getFirstPlayer().getTeam().get(i));
		}
		for(int i=0;i<game.getSecondPlayer().getTeam().size();i++) {
			teams.add(game.getSecondPlayer().getTeam().get(i));
		}
		while(!game.getTurnOrder().isEmpty()) {
			turns.add((Champion)game.getTurnOrder().remove());
		}
		for(int i=0;i<turns.size();i++) {
			names += (turns.get(i)).getName();
			if(i<turns.size()-1) {
				names += ", ";
			} 
			else {
				names += ".";
			}
			game.getTurnOrder().insert(turns.get(i));
		}
		return names;
	}
	public void castAbility(Button cast, int index) {
		if(game.getCurrentChampion().getAbilities().get(index).getCastArea()==AreaOfEffect.SELFTARGET || game.getCurrentChampion().getAbilities().get(index).getCastArea()==AreaOfEffect.TEAMTARGET || game.getCurrentChampion().getAbilities().get(index).getCastArea()==AreaOfEffect.SURROUND) {
    		try {
				game.castAbility(game.getCurrentChampion().getAbilities().get(index));
				currChamp.setText(game.getCurrentChampion().stringgame());
				s = getTurns();
				trnOrder.setText("Turn Order: "+s);
				updateBoard();
				winner = game.checkGameOver();
			} catch (NotEnoughResourcesException e1) {
				BorderPane rootcast = new BorderPane();
				Scene castScene = new Scene(rootcast,281,281);
				GridPane covergrid = new GridPane();
				Button back = new Button();
				back.setGraphic(new ImageView(backbtn));
				back.setBackground(null);
				covergrid.getChildren().addAll(ExceptionInfo,back);
				covergrid.setTranslateX(40);
				covergrid.setTranslateY(-100);
				covergrid.setVgap(0);
				GridPane.setConstraints(ExceptionInfo, 0, 0);
				GridPane.setConstraints(back, 0, 1);
				ExceptionInfo.setText("Not enough resources.");
				rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
				temp1.setAlwaysOnTop(true);
				temp1.setMaxHeight(281);
				temp1.setMaxWidth(281);
				temp1.setMaximized(true);
		    	temp1.setScene(castScene);
		    	temp1.show();
		    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent arg0) {
						temp1.close();
					}
				});
			} catch (AbilityUseException e1) {
				BorderPane rootcast = new BorderPane();
				Scene castScene = new Scene(rootcast,281,281);
				GridPane covergrid = new GridPane();
				Button back = new Button();
				back.setGraphic(new ImageView(backbtn));
				back.setBackground(null);
				covergrid.getChildren().addAll(ExceptionInfo,back);
				covergrid.setTranslateX(40);
				covergrid.setTranslateY(-100);
				covergrid.setVgap(0);
				GridPane.setConstraints(ExceptionInfo, 0, 0);
				GridPane.setConstraints(back, 0, 1);
				ExceptionInfo.setText("Ability cannot be used.");
				rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
				temp1.setAlwaysOnTop(true);
				temp1.setMaxHeight(281);
				temp1.setMaxWidth(281);
				temp1.setMaximized(true);
		    	temp1.setScene(castScene);
		    	temp1.show();
		    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent arg0) {
						temp1.close();
					}
				});
			} catch (CloneNotSupportedException e1) {
				BorderPane rootcast = new BorderPane();
				Scene castScene = new Scene(rootcast,281,281);
				GridPane covergrid = new GridPane();
				Button back = new Button();
				back.setGraphic(new ImageView(backbtn));
				back.setBackground(null);
				covergrid.getChildren().addAll(ExceptionInfo,back);
				covergrid.setTranslateX(40);
				covergrid.setTranslateY(-100);
				covergrid.setVgap(0);
				GridPane.setConstraints(ExceptionInfo, 0, 0);
				GridPane.setConstraints(back, 0, 1);
				ExceptionInfo.setText("Clone not supported.");
				rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
				temp1.setAlwaysOnTop(true);
				temp1.setMaxHeight(281);
				temp1.setMaxWidth(281);
				temp1.setMaximized(true);
		    	temp1.setScene(castScene);
		    	temp1.show();
		    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent arg0) {
						temp1.close();
					}
				});
			}
    	}
    	if(game.getCurrentChampion().getAbilities().get(0).getCastArea()==AreaOfEffect.DIRECTIONAL) {
    		cast.setOnKeyPressed(event->{
		    	if(event.getCode()==KeyCode.W) {
			    	try {
						game.castAbility(game.getCurrentChampion().getAbilities().get(index), Direction.UP);
						currChamp.setText(game.getCurrentChampion().stringgame());
						s = getTurns();
						trnOrder.setText("Turn Order: "+s);
						updateBoard();
						winner = game.checkGameOver();
					} catch (NotEnoughResourcesException e1) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Not enough resources.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (AbilityUseException e1) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Ability cannot be used.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (CloneNotSupportedException e1) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Clone not supported.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					}
			    }
			    if(event.getCode()==KeyCode.S) {
			    	try {
						game.castAbility(game.getCurrentChampion().getAbilities().get(index), Direction.DOWN);
						currChamp.setText(game.getCurrentChampion().stringgame());
						s = getTurns();
						trnOrder.setText("Turn Order: "+s);
						updateBoard();
						winner = game.checkGameOver();
					} catch (NotEnoughResourcesException e1) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Not enough resources.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (AbilityUseException e1) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Ability cannot be used.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (CloneNotSupportedException e1) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Clone not supported.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					}
			    }
			    if(event.getCode()==KeyCode.D) {
			    	try {
						game.castAbility(game.getCurrentChampion().getAbilities().get(index), Direction.RIGHT);
						currChamp.setText(game.getCurrentChampion().stringgame());
						s = getTurns();
						trnOrder.setText("Turn Order: "+s);
						updateBoard();
						winner = game.checkGameOver();
					} catch (NotEnoughResourcesException e1) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Not enough resources.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (AbilityUseException e1) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Ability cannot be used.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (CloneNotSupportedException e1) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Clone not supported.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					}
			    }
			    if(event.getCode()==KeyCode.A) {
			    	try {
						game.castAbility(game.getCurrentChampion().getAbilities().get(index), Direction.LEFT);
						currChamp.setText(game.getCurrentChampion().stringgame());
						s = getTurns();
						trnOrder.setText("Turn Order: "+s);
						updateBoard();
						winner = game.checkGameOver();
					} catch (NotEnoughResourcesException e1) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Not enough resources.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (AbilityUseException e1) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Ability cannot be used.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (CloneNotSupportedException e1) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Clone not supported.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					}
			    }});
    	}
    	if(game.getCurrentChampion().getAbilities().get(index).getCastArea()==AreaOfEffect.SINGLETARGET) {
    		button1.setOnMouseClicked(new EventHandler<MouseEvent>() {
    			@Override
    			public void handle(MouseEvent arg0) {
    				try {
						game.castAbility(game.getCurrentChampion().getAbilities().get(index),(int)teams.get(buttons.indexOf(button1)).getLocation().getX(),(int)teams.get(buttons.indexOf(button1)).getLocation().getY());
						currChamp.setText(game.getCurrentChampion().stringgame());
						s =getTurns();
						trnOrder.setText("Turn Order: "+s);
						updateBoard();
						winner = game.checkGameOver();
					} catch (NotEnoughResourcesException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Not enough resources.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (AbilityUseException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Ability cannot be used.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (InvalidTargetException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Cannot cast ability on \nthis target.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (CloneNotSupportedException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Clone not supported.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					}
    		}});
    		button2.setOnMouseClicked(new EventHandler<MouseEvent>() {
    			@Override
    			public void handle(MouseEvent arg0) {
    				try {
						game.castAbility(game.getCurrentChampion().getAbilities().get(index),(int)teams.get(buttons.indexOf(button2)).getLocation().getX(),(int)teams.get(buttons.indexOf(button2)).getLocation().getY());
						currChamp.setText(game.getCurrentChampion().stringgame());
						s =getTurns();
						trnOrder.setText("Turn Order: "+s);
						updateBoard();
						winner = game.checkGameOver();
					} catch (NotEnoughResourcesException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Not enough resources.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (AbilityUseException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Ability cannot be used.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (InvalidTargetException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Cannot cast ability \non this target.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (CloneNotSupportedException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Clone not supported.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					}
    		}});
    		button3.setOnMouseClicked(new EventHandler<MouseEvent>() {
    			@Override
    			public void handle(MouseEvent arg0) {
    				try {
						game.castAbility(game.getCurrentChampion().getAbilities().get(index),(int)teams.get(buttons.indexOf(button3)).getLocation().getX(),(int)teams.get(buttons.indexOf(button3)).getLocation().getY());
						currChamp.setText(game.getCurrentChampion().stringgame());
						s =getTurns();
						trnOrder.setText("Turn Order: "+s);
						updateBoard();
						winner = game.checkGameOver();
					} catch (NotEnoughResourcesException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Not enough resources.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (AbilityUseException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Ability cannot be used.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (InvalidTargetException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Cannot cast ability \non this target.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (CloneNotSupportedException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Clone not supported.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					}
    		}});
    		button4.setOnMouseClicked(new EventHandler<MouseEvent>() {
    			@Override
    			public void handle(MouseEvent arg0) {
    				try {
						game.castAbility(game.getCurrentChampion().getAbilities().get(index),(int)teams.get(buttons.indexOf(button4)).getLocation().getX(),(int)teams.get(buttons.indexOf(button4)).getLocation().getY());
						currChamp.setText(game.getCurrentChampion().stringgame());
						s =getTurns();
						trnOrder.setText("Turn Order: "+s);
						updateBoard();
						winner = game.checkGameOver();
					} catch (NotEnoughResourcesException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Not enough resources.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (AbilityUseException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Ability cannot be used.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (InvalidTargetException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Cannot cast ability \non this target.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (CloneNotSupportedException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Clone not supported.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					}
    		}});
    		button5.setOnMouseClicked(new EventHandler<MouseEvent>() {
    			@Override
    			public void handle(MouseEvent arg0) {
    				try {
						game.castAbility(game.getCurrentChampion().getAbilities().get(index),(int)teams.get(buttons.indexOf(button5)).getLocation().getX(),(int)teams.get(buttons.indexOf(button5)).getLocation().getY());
						currChamp.setText(game.getCurrentChampion().stringgame());
						s =getTurns();
						trnOrder.setText("Turn Order: "+s);
						updateBoard();
						winner = game.checkGameOver();
					} catch (NotEnoughResourcesException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Not enough resources.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (AbilityUseException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Ability cannot be used.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (InvalidTargetException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Cannot cast ability \non this target.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (CloneNotSupportedException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Clone not supported.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					}
    		}});
    		button6.setOnMouseClicked(new EventHandler<MouseEvent>() {
    			@Override
    			public void handle(MouseEvent arg0) {
    				try {
						game.castAbility(game.getCurrentChampion().getAbilities().get(index),(int)teams.get(buttons.indexOf(button6)).getLocation().getX(),(int)teams.get(buttons.indexOf(button6)).getLocation().getY());
						currChamp.setText(game.getCurrentChampion().stringgame());
						s =getTurns();
						trnOrder.setText("Turn Order: "+s);
						updateBoard();
						winner = game.checkGameOver();
					} catch (NotEnoughResourcesException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Not enough resources.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (AbilityUseException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Ability cannot be used.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (InvalidTargetException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Cannot cast ability \non this target.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (CloneNotSupportedException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Clone not supported.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					}
    		}});
    		cover1.setOnMouseClicked(new EventHandler<MouseEvent>() {
    			@Override
    			public void handle(MouseEvent arg0) {
    				try {
						game.castAbility(game.getCurrentChampion().getAbilities().get(index),(int)covers.get(0).getLocation().getX(),(int)covers.get(0).getLocation().getY());
						currChamp.setText(game.getCurrentChampion().stringgame());
						s =getTurns();
						trnOrder.setText("Turn Order: "+s);
						updateBoard();
					} catch (NotEnoughResourcesException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Not enough resources.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (AbilityUseException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Out of range target.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (InvalidTargetException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Cannot cast ability \non this target.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (CloneNotSupportedException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Clone not supported.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					}
    		}});
    		cover2.setOnMouseClicked(new EventHandler<MouseEvent>() {
    			@Override
    			public void handle(MouseEvent arg0) {
    				try {
						game.castAbility(game.getCurrentChampion().getAbilities().get(index),(int)covers.get(1).getLocation().getX(),(int)covers.get(1).getLocation().getY());
						currChamp.setText(game.getCurrentChampion().stringgame());
						s =getTurns();
						trnOrder.setText("Turn Order: "+s);
						updateBoard();
					} catch (NotEnoughResourcesException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Not enough resources.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (AbilityUseException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Ability cannot be used.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (InvalidTargetException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Cannot cast ability \non this target.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (CloneNotSupportedException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Clone not supported.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					}
    		}});
    		cover3.setOnMouseClicked(new EventHandler<MouseEvent>() {
    			@Override
    			public void handle(MouseEvent arg0) {
    				try {
						game.castAbility(game.getCurrentChampion().getAbilities().get(index),(int)covers.get(2).getLocation().getX(),(int)covers.get(2).getLocation().getY());
						currChamp.setText(game.getCurrentChampion().stringgame());
						s =getTurns();
						trnOrder.setText("Turn Order: "+s);
						updateBoard();
					} catch (NotEnoughResourcesException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Not enough resources.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (AbilityUseException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Ability cannot be used.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (InvalidTargetException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Cannot cast ability \non this target.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (CloneNotSupportedException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Clone not supported.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					}
    		}});
    		cover4.setOnMouseClicked(new EventHandler<MouseEvent>() {
    			@Override
    			public void handle(MouseEvent arg0) {
    				try {
						game.castAbility(game.getCurrentChampion().getAbilities().get(index),(int)covers.get(3).getLocation().getX(),(int)covers.get(3).getLocation().getY());
						currChamp.setText(game.getCurrentChampion().stringgame());
						s =getTurns();
						trnOrder.setText("Turn Order: "+s);
						updateBoard();
					} catch (NotEnoughResourcesException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Not enough resources.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (AbilityUseException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Ability cannot be used.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (InvalidTargetException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Cannot cast ability \non this target.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (CloneNotSupportedException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Clone not supported.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					}
    		}});
    		cover5.setOnMouseClicked(new EventHandler<MouseEvent>() {
    			@Override
    			public void handle(MouseEvent arg0) {
    				try {
						game.castAbility(game.getCurrentChampion().getAbilities().get(index),(int)covers.get(4).getLocation().getX(),(int)covers.get(4).getLocation().getY());
						currChamp.setText(game.getCurrentChampion().stringgame());
						s =getTurns();
						trnOrder.setText("Turn Order: "+s);
						updateBoard();
					} catch (NotEnoughResourcesException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Not enough resources.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (AbilityUseException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Ability cannot be used.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (InvalidTargetException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Cannot cast ability \non this target.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					} catch (CloneNotSupportedException e) {
						BorderPane rootcast = new BorderPane();
						Scene castScene = new Scene(rootcast,281,281);
						GridPane covergrid = new GridPane();
						Button back = new Button();
						back.setGraphic(new ImageView(backbtn));
						back.setBackground(null);
						covergrid.getChildren().addAll(ExceptionInfo,back);
						covergrid.setTranslateX(40);
						covergrid.setTranslateY(-100);
						covergrid.setVgap(0);
						GridPane.setConstraints(ExceptionInfo, 0, 0);
						GridPane.setConstraints(back, 0, 1);
						ExceptionInfo.setText("Clone not supported.");
						rootcast.getChildren().addAll(new ImageView(coversHover),covergrid);
						temp1.setAlwaysOnTop(true);
						temp1.setMaxHeight(281);
						temp1.setMaxWidth(281);
						temp1.setMaximized(true);
				    	temp1.setScene(castScene);
				    	temp1.show();
				    	back.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								temp1.close();
							}
						});
					}
    		}});
    	}
    }
	public void endTurn() {
		game.endTurn();
    	currChamp.setText(game.getCurrentChampion().stringgame());
    	s = getTurns();
    	trnOrder.setText("Turn order: " +s);
    	updateBoard();
    	winner = game.checkGameOver();
	}
}
