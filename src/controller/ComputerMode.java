package controller;
import java.io.File;
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
import javafx.stage.Stage;
import model.abilities.Ability;
import model.abilities.AreaOfEffect;
import model.world.Champion;
import model.world.Cover;
import model.world.Direction;

public class ComputerMode extends Application {
	Stage primary;
	Stage temp1;
	static Scene scene1;
	Scene winScene;
	Scene loseScene;
	int count;
	String p1text = null;
	String n1 = null;
	String n2=null;
	String p2text = null;
	String[] images = new String[6];
	BorderPane root3;
	MediaPlayer winningPlayer;
	MediaPlayer battlescenePlayer;
	MediaPlayer losingPlayer;
	Game game;
	Button b1;
	Button b2;
	Button b3;
	Button b4;
	Button b5;
	Button b6;
	Button button1;
	Button button2;
	Button button3;
	Button button4;
	Button button5;
	Button button6;
	Button captainAmerica;
	Button deadpool;
	Button electro;
	Button ghostRider;
	Button hela;
	Button hulk;
	Button iceMan;
	Button ironMan;
	Button loki;
	Button quickSilver;
	Button spiderMan;
	Button drStrange;
	Button thor;
	Button venom;
	Button yellowJacket;
	ArrayList<Cover> covers = new ArrayList<Cover>();
	ArrayList<Button> buttons = new ArrayList<Button>();
	ArrayList<Champion> players = new ArrayList<Champion>();
	ArrayList<Image> allimages = new ArrayList<Image>();
	ArrayList<Image> battleimages = new ArrayList<Image>();
	ArrayList<ImageView> allimageviews = new ArrayList<ImageView>();
	Button cover1;
	Button cover2;
	Button cover3;
	Button cover4;
	Button cover5;
	Image coversHover;
	Image backbtn;
	ArrayList<Champion> turns = new ArrayList<Champion>();
	ArrayList<Champion> teams = new ArrayList<Champion>();
	ArrayList<Champion> availableChampions;
	String s = "";
	Label champion;
	Label currChamp;
	Label trnOrder;
	Label ExceptionInfo;
	Label Winner;
	Player winner;
	int randomchamps;
	int randomactions;
	//random leader
	int rand;
	//random direction for directional ability cast
	int direction;
	public static void main(String[]args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception{
		Stage temp = new Stage();
		primary = primaryStage;
		temp1 = new Stage();
		BorderPane root = new BorderPane();
		BorderPane root1 = new BorderPane();
		BorderPane root2 = new BorderPane();
		BorderPane rootWin = new BorderPane();
		BorderPane rootLose = new BorderPane();
		root3 = new BorderPane();
		BorderPane rootChamps = new BorderPane();
		scene1 = new Scene(root,1000,562);
		Scene scene2 = new Scene(root1,1000,562);
		Scene scene3 = new Scene(root2,1000,562);
		Scene scene4 = new Scene(root3,1000,562);
		winScene = new Scene(rootWin,1000,562);
		loseScene = new Scene(rootLose,1000,562);
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
		primary.setTitle("Marvel: Ultimate War");
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
		Image LoseBg = new Image(Files.newInputStream(Paths.get("images/bg and btn/lose scene.jpg")));
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
	    champion = new Label();
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
	    Winner = new Label();
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
	    File losingFile = new File("videos/losingmusic.mp4");
	    Media battlescenemusic = new Media(battleFile.toURI().toString());
	    Media elsemusic = new Media(elseFile.toURI().toString());
	    Media winningmusic = new Media(winningFile.toURI().toString());
	    Media losingmusic = new Media(losingFile.toURI().toString());
	    battlescenePlayer = new MediaPlayer(battlescenemusic);
	    MediaPlayer elsePlayer = new MediaPlayer(elsemusic);
	    elsePlayer.setAutoPlay(true);
	    losingPlayer = new MediaPlayer(losingmusic);
	    winningPlayer = new MediaPlayer(winningmusic);
	    MediaView battlesceneView = new MediaView(battlescenePlayer);
	    MediaView elseView = new MediaView(elsePlayer);
	    MediaView losingView = new MediaView(losingPlayer);
	    MediaView winningView = new MediaView(winningPlayer);
	    battlesceneView.setVisible(false);
	    elseView.setVisible(false);
	    losingView.setVisible(false);
	    winningView.setVisible(false);
	    //player 1 name
	    TextField player1= new TextField();
	    player1.setMinSize(500, 50);
	    player1.setPrefSize(500, 50);
	    player1.setPromptText("Player 1, please enter your name");
	    GridPane.setConstraints(player1, 0, 0);
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
	    p2.setText("Computer");
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
	    Grid.getChildren().addAll(player1,p2);
	    Grid.setTranslateX(250);
	    Grid.setTranslateY(300);
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
	    btn3.setOnAction(e-> primary.setScene(scene1));
	    btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				if(player1.getText()!="" && p2.getText()!="") {
					primary.setScene(scene2);
					p1text= player1.getText();
					p2text= p2.getText();
					p1.setText(p1text);
					p2.setText(p2text);
					n1=player1.getText();
					n2=p2.getText();
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
	    //add images to arraylist
	    allimages.add(c1);
	    allimages.add(c2);
	    allimages.add(c12);
	    allimages.add(c3);
	    allimages.add(c4);
	    allimages.add(c5);
	    allimages.add(c6);
	    allimages.add(c7);
	    allimages.add(c8);
	    allimages.add(c9);
	    allimages.add(c10);
	    allimages.add(c11);
	    allimages.add(c13);
	    allimages.add(c14);
	    allimages.add(c15);
	    battleimages.add(one);
	    battleimages.add(two);
	    battleimages.add(three);
	    battleimages.add(four);
	    battleimages.add(five);
	    battleimages.add(six);
	    battleimages.add(seven);
	    battleimages.add(eight);
	    battleimages.add(nine);
	    battleimages.add(ten);
	    battleimages.add(eleven);
	    battleimages.add(twelve);
	    battleimages.add(thirteen);
	    battleimages.add(fourteen);
	    battleimages.add(fifteen);
	    allimageviews.add(capt);
	    allimageviews.add(deadp);
	    allimageviews.add(drs);
	    allimageviews.add(elec);
	    allimageviews.add(ghostr);
	    allimageviews.add(hel);
	    allimageviews.add(hul);
	    allimageviews.add(icem);
	    allimageviews.add(ironm);
	    allimageviews.add(lok);
	    allimageviews.add(quicks);
	    allimageviews.add(spiderm);
	    allimageviews.add(thorr);
	    allimageviews.add(ven);
	    allimageviews.add(yellowj);
	    //scene 2 buttons "heheee"
	    	//cap
	    captainAmerica = new Button();
	    captainAmerica.setBackground(null);
	    captainAmerica.setGraphic(new ImageView(c1));
	    GridPane.setConstraints(captainAmerica, 0, 0);
	    	//deadpool
	    deadpool = new Button();
	    deadpool.setBackground(null);
	    deadpool.setGraphic(new ImageView(c2));
	    GridPane.setConstraints(deadpool, 0, 1);
	    	//electro
	    electro = new Button();
	    electro.setBackground(null);
	    electro.setGraphic(new ImageView(c3));
	    GridPane.setConstraints(electro, 0, 2);
	    	//ghostrider
	    ghostRider = new Button();
	    ghostRider.setBackground(null);
	    ghostRider.setGraphic(new ImageView(c4));
	    GridPane.setConstraints(ghostRider, 1, 0);
	    	//hela
	    hela = new Button();
	    hela.setBackground(null);
	    hela.setGraphic(new ImageView(c5));
	    GridPane.setConstraints(hela, 1, 1);
	    	//hulk
	    hulk = new Button();
	    hulk.setBackground(null);
	    hulk.setGraphic(new ImageView(c6));
	    GridPane.setConstraints(hulk, 1, 2);
	    	//iceman
	    iceMan = new Button();
	    iceMan.setBackground(null);
	    iceMan.setGraphic(new ImageView(c7));
	    GridPane.setConstraints(iceMan, 2, 0);
	    	//ironMan
	    ironMan = new Button();
	    ironMan.setBackground(null);
	    ironMan.setGraphic(new ImageView(c8));
	    GridPane.setConstraints(ironMan, 2, 1);
	    	//loki
	    loki = new Button();
	    loki.setBackground(null);
	    loki.setGraphic(new ImageView(c9));
	    GridPane.setConstraints(loki, 2, 2);
    		//quickSilver
	    quickSilver = new Button();
	    quickSilver.setBackground(null);
	    quickSilver.setGraphic(new ImageView(c10));
	    GridPane.setConstraints(quickSilver, 3, 0);
	    	//spiderMan
	    spiderMan = new Button();
	    spiderMan.setBackground(null);
	    spiderMan.setGraphic(new ImageView(c11));
	    GridPane.setConstraints(spiderMan, 3, 1);
			//drStrange
	    drStrange = new Button();
	    drStrange.setBackground(null);
	    drStrange.setGraphic(new ImageView(c12));
	    GridPane.setConstraints(drStrange, 3, 2);
	    	//thor
	    thor = new Button();
	    thor.setBackground(null);
	    thor.setGraphic(new ImageView(c13));
	    GridPane.setConstraints(thor, 4, 0);
	    	//venom
	    venom = new Button();
	    venom.setBackground(null);
	    venom.setGraphic(new ImageView(c14));
	    GridPane.setConstraints(venom, 4, 1);
	    	//yellowJacket
	    yellowJacket = new Button();
	    yellowJacket.setBackground(null);
	    yellowJacket.setGraphic(new ImageView(c15));
	    GridPane.setConstraints(yellowJacket, 4, 2);
	    //players' teams' buttons    
	    b1 = new Button();
	    b1.setBackground(null);
	    GridPane.setConstraints(b1, 0, 0);
	    b2 = new Button();
	    b2.setBackground(null);
	    GridPane.setConstraints(b2, 1, 0);
	    b3 = new Button();
	    b3.setBackground(null);
	    GridPane.setConstraints(b3, 2, 0);
	    b4 = new Button();
	    b4.setBackground(null);
	    GridPane.setConstraints(b4, 0, 0);
	    b5 = new Button();
	    b5.setBackground(null);
	    GridPane.setConstraints(b5, 1, 0);
	    b6 = new Button();
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
	  Player secondPlayer = new Player(p2.getText());
	  game = new Game(firstPlayer, secondPlayer);
	  Game.loadAbilities("Abilities.csv");
	  Game.loadChampions("Champions.csv");
	  availableChampions = Game.getAvailableChampions();
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
	   buttons.add(button1);
	   buttons.add(button2);
	   buttons.add(button3);
	   buttons.add(button4);
	   buttons.add(button5);
	   buttons.add(button6);
	   //adding to teams and viewing info
	    captainAmerica.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    	@Override
	        public void handle(MouseEvent click) {
	    		chooseTeam(captainAmerica,0,click);
	    	}
	    });
	    deadpool.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent click) {
	        	chooseTeam(deadpool,1,click);
	        }
	    });
	    electro.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent click) {
	        	chooseTeam(electro,3,click);
	        }
	    });
	    ghostRider.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent click) {
	        	chooseTeam(ghostRider,4,click);
	        }
	    });
	    hela.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent click) {
	        	chooseTeam(hela,5,click);
	        }
	    });
	    hulk.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent click) {
	        	chooseTeam(hulk,6,click);
	        }
	    });
	    iceMan.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent click) {
	        	chooseTeam(iceMan,7,click);
	        }
	    });
	    ironMan.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent click) {
	        	chooseTeam(ironMan,8,click);
	        }
	    });
	    loki.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent click) {
	        	chooseTeam(loki,9,click);
	        }
	    });
	    quickSilver.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent click) {
	        	chooseTeam(quickSilver,10,click);
	        }
	    });
	    spiderMan.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent click) {
	        	chooseTeam(spiderMan,11,click);
	        }
	    });
	    drStrange.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent click) {
	        	chooseTeam(drStrange,2,click);
	        }
	    });
	    thor.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent click) {
	        	chooseTeam(thor,12,click);
	        }
	    });
	    venom.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent click) {
	        	chooseTeam(venom,13,click);
	        }
	    });
	    yellowJacket.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent click) {
	        	chooseTeam(yellowJacket,14,click);
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
	    rand=(int)(Math.random()*3);
	    b1.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				game.getFirstPlayer().setLeader(game.getFirstPlayer().getTeam().get(0));
				b1.setDisable(true);
				b2.setDisable(true);
				b3.setDisable(true);
				game.getSecondPlayer().setLeader(game.getSecondPlayer().getTeam().get(rand));
				b4.setDisable(true);
				b5.setDisable(true);
				b6.setDisable(true);
				game.placeChampions();
				trnOrder.setText(getTurns());
				currChamp.setText(game.getCurrentChampion().stringgame());
			}
	    });
	    b2.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				game.getFirstPlayer().setLeader(game.getFirstPlayer().getTeam().get(1));
				b1.setDisable(true);
				b2.setDisable(true);
				b3.setDisable(true);
				game.getSecondPlayer().setLeader(game.getSecondPlayer().getTeam().get(rand));
				b4.setDisable(true);
				b5.setDisable(true);
				b6.setDisable(true);
				game.placeChampions();
				trnOrder.setText(getTurns());
				currChamp.setText(game.getCurrentChampion().stringgame());
			}
	    });
	    b3.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				game.getFirstPlayer().setLeader(game.getFirstPlayer().getTeam().get(2));
				b1.setDisable(true);
				b2.setDisable(true);
				b3.setDisable(true);
				game.getSecondPlayer().setLeader(game.getSecondPlayer().getTeam().get(rand));
				b4.setDisable(true);
				b5.setDisable(true);
				b6.setDisable(true);
				game.placeChampions();
				trnOrder.setText(getTurns());
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
					trnOrder.setText(getTurns());
					updateBoard();
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
					trnOrder.setText(getTurns());
					updateBoard();
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
					trnOrder.setText(getTurns());
					updateBoard();
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
					trnOrder.setText(getTurns());
					updateBoard();
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
	    });
	    //cast abilities
	    cast1.setOnMouseClicked(e->{
	    	castAbility(cast1,0);
	    });
	    cast2.setOnMouseClicked(e->{
	    	castAbility(cast2,1);
	    });
	    cast3.setOnMouseClicked(e->{
	    	castAbility(cast3,2);
	    });
	    cast4.setOnMouseClicked(e->{
	    	if(game.getCurrentChampion().getAbilities().size()==4) {
	   			castAbility(cast4,3);
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
	    		primary.setScene(scene3);
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
				ExceptionInfo.setText("Choose your team.");
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
	    		primary.setScene(scene4);
		    	elsePlayer.setAutoPlay(false);
	    		elsePlayer.setMute(true);
	    		battlescenePlayer.setAutoPlay(true);
	    		if(game.getSecondPlayer().getTeam().contains(game.getCurrentChampion())) {
	        		generateActions();
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
				ExceptionInfo.setText("Choose your leader.");
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
		rootLose.getChildren().addAll(new ImageView(LoseBg),losingView);
		//set stage
		primary.setScene(scene1);
		primary.show();
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
		winner = game.checkGameOver();
		if(winner==game.getFirstPlayer()) {
		
			battlescenePlayer.setAutoPlay(false);
			battlescenePlayer.setMute(true);
			winningPlayer.setAutoPlay(true);
			Winner.setText(n1);
			primary.setScene(winScene);
		}
		else if (winner==game.getSecondPlayer()) {
//			Winner.setText(n2);
			battlescenePlayer.setAutoPlay(false);
			battlescenePlayer.setMute(true);
			losingPlayer.setAutoPlay(true);
			primary.setScene(loseScene);
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
		names = "Turn order: ";
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
				trnOrder.setText(getTurns());
				updateBoard();
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
						trnOrder.setText(getTurns());
						updateBoard();
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
						trnOrder.setText(getTurns());
						updateBoard();
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
						trnOrder.setText(getTurns());
						updateBoard();
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
						trnOrder.setText(getTurns());
						updateBoard();
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
						trnOrder.setText(getTurns());
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
						trnOrder.setText(getTurns());
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
    		button3.setOnMouseClicked(new EventHandler<MouseEvent>() {
    			@Override
    			public void handle(MouseEvent arg0) {
    				try {
						game.castAbility(game.getCurrentChampion().getAbilities().get(index),(int)teams.get(buttons.indexOf(button3)).getLocation().getX(),(int)teams.get(buttons.indexOf(button3)).getLocation().getY());
						currChamp.setText(game.getCurrentChampion().stringgame());
						trnOrder.setText(getTurns());
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
    		button4.setOnMouseClicked(new EventHandler<MouseEvent>() {
    			@Override
    			public void handle(MouseEvent arg0) {
    				try {
						game.castAbility(game.getCurrentChampion().getAbilities().get(index),(int)teams.get(buttons.indexOf(button4)).getLocation().getX(),(int)teams.get(buttons.indexOf(button4)).getLocation().getY());
						currChamp.setText(game.getCurrentChampion().stringgame());
						trnOrder.setText(getTurns());
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
    		button5.setOnMouseClicked(new EventHandler<MouseEvent>() {
    			@Override
    			public void handle(MouseEvent arg0) {
    				try {
						game.castAbility(game.getCurrentChampion().getAbilities().get(index),(int)teams.get(buttons.indexOf(button5)).getLocation().getX(),(int)teams.get(buttons.indexOf(button5)).getLocation().getY());
						currChamp.setText(game.getCurrentChampion().stringgame());
						trnOrder.setText(getTurns());
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
    		button6.setOnMouseClicked(new EventHandler<MouseEvent>() {
    			@Override
    			public void handle(MouseEvent arg0) {
    				try {
						game.castAbility(game.getCurrentChampion().getAbilities().get(index),(int)teams.get(buttons.indexOf(button6)).getLocation().getX(),(int)teams.get(buttons.indexOf(button6)).getLocation().getY());
						currChamp.setText(game.getCurrentChampion().stringgame());
						trnOrder.setText(getTurns());
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
    		cover1.setOnMouseClicked(new EventHandler<MouseEvent>() {
    			@Override
    			public void handle(MouseEvent arg0) {
    				try {
						game.castAbility(game.getCurrentChampion().getAbilities().get(index),(int)covers.get(0).getLocation().getX(),(int)covers.get(0).getLocation().getY());
						currChamp.setText(game.getCurrentChampion().stringgame());
						trnOrder.setText(getTurns());
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
						trnOrder.setText(getTurns());
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
						trnOrder.setText(getTurns());
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
						trnOrder.setText(getTurns());
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
						trnOrder.setText(getTurns());
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
	//choose team of player
	public void chooseTeam(Button b, int index, MouseEvent click) {
		if(click.getClickCount() == 2) {
        	if(game.getFirstPlayer().getTeam().size()<3) {
        		game.getTurnOrder().insert(availableChampions.get(index));
        		game.getFirstPlayer().getTeam().add(availableChampions.get(index));
        	}
        	b.setDisable(true);
        	champion.setVisible(false);
        	if(count==0) {
        		allimageviews.get(index).setVisible(true);
        		allimageviews.get(index).setTranslateX(650);
        		allimageviews.get(index).setTranslateY(80);
        		b1.setGraphic(new ImageView(allimages.get(index)));
        		button1.setGraphic(new ImageView(battleimages.get(index)));
        		button1.setAccessibleText(availableChampions.get(index).getName());
        	}
        	else if(count==1) {
        		allimageviews.get(index).setVisible(true);
        		allimageviews.get(index).setTranslateX(735);
        		allimageviews.get(index).setTranslateY(80);
        		b2.setGraphic(new ImageView(allimages.get(index)));
        		button2.setGraphic(new ImageView(battleimages.get(index)));
        		button2.setAccessibleText(availableChampions.get(index).getName());
        	}
        	else if(count==2) {
        		allimageviews.get(index).setVisible(true);
        		allimageviews.get(index).setTranslateX(820);
        		allimageviews.get(index).setTranslateY(80);
        		b3.setGraphic(new ImageView(allimages.get(index)));
        		button3.setGraphic(new ImageView(battleimages.get(index)));
        		button3.setAccessibleText(availableChampions.get(index).getName());
        		generateTeam();
        	}
        	count++;
        }
        else if(click.getClickCount() == 1) {
        	Champion ch = availableChampions.get(index);
        	champion.setText(ch.toString());
        	champion.setVisible(true);
        }
	}
	//generate computer's team
	public void generateTeam() {
		boolean team1 = false;
		boolean team2 = false;
		int i=0;
		while(i<3) {
			team1=false;
			team2=false;
			randomchamps = (int)(Math.random()*15);
			for(int j=0;j<game.getFirstPlayer().getTeam().size();j++) {
				if(game.getFirstPlayer().getTeam().get(j)==availableChampions.get(randomchamps)) {
					team1=true;
				}
			}
			for(int j=0;j<game.getSecondPlayer().getTeam().size();j++) {
				if(game.getSecondPlayer().getTeam().get(j)==availableChampions.get(randomchamps)) {
					team2=true;
				}
			}
			if(!team1 && !team2) {
				if(game.getSecondPlayer().getTeam().size()<3) {
	        		game.getTurnOrder().insert(availableChampions.get(randomchamps));
	        		game.getSecondPlayer().getTeam().add(availableChampions.get(randomchamps));
				}
				if(i==0) {
					allimageviews.get(randomchamps).setVisible(true);
	    			allimageviews.get(randomchamps).setTranslateX(650);
	    			allimageviews.get(randomchamps).setTranslateY(210);
	        		b4.setGraphic(new ImageView(allimages.get(randomchamps)));
	        		button4.setGraphic(new ImageView(battleimages.get(randomchamps)));
	        		button4.setAccessibleText(availableChampions.get(randomchamps).getName());
				}
				else if(i==1) {
					allimageviews.get(randomchamps).setVisible(true);
	    			allimageviews.get(randomchamps).setTranslateX(735);
	    			allimageviews.get(randomchamps).setTranslateY(210);
	        		b5.setGraphic(new ImageView(allimages.get(randomchamps)));
	        		button5.setGraphic(new ImageView(battleimages.get(randomchamps)));
	        		button5.setAccessibleText(availableChampions.get(randomchamps).getName());
				}
				else if(i==2) {
					allimageviews.get(randomchamps).setVisible(true);
	    			allimageviews.get(randomchamps).setTranslateX(820);
	    			allimageviews.get(randomchamps).setTranslateY(210);
	        		b6.setGraphic(new ImageView(allimages.get(randomchamps)));
	        		button6.setGraphic(new ImageView(battleimages.get(randomchamps)));
	        		button6.setAccessibleText(availableChampions.get(randomchamps).getName());
				}
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
        		yellowJacket.setDisable(true);
				i++;
			}
		}
	}
	//generate actions for computer
	public void generateActions() {
		while(game.getSecondPlayer().getTeam().contains(game.getCurrentChampion()) && game.checkGameOver()==null) {
			randomactions = (int)(Math.random()*13);
			if(randomactions==0) {
				try {
					game.move(Direction.UP);
					updateBoard();
					currChamp.setText(game.getCurrentChampion().stringgame());
				} catch (UnallowedMovementException e1) {
					generateActions();
				} catch (NotEnoughResourcesException e1) {
					endTurn();
				}
			}
			if(randomactions==1) {
				try {
					game.move(Direction.DOWN);
					updateBoard();
					currChamp.setText(game.getCurrentChampion().stringgame());
				} catch (UnallowedMovementException e1) {
					generateActions();
				} catch (NotEnoughResourcesException e1) {
					endTurn();
				}
			}
			if(randomactions==2) {
				try {
					game.move(Direction.RIGHT);
					updateBoard();
					currChamp.setText(game.getCurrentChampion().stringgame());
				} catch (UnallowedMovementException e1) {
					generateActions();
				} catch (NotEnoughResourcesException e1) {
					endTurn();
				}
			}
			if(randomactions==3) {
				try {
					game.move(Direction.LEFT);
					updateBoard();
					currChamp.setText(game.getCurrentChampion().stringgame());
				} catch (UnallowedMovementException e1) {
					generateActions();
				} catch (NotEnoughResourcesException e1) {
					endTurn();
				}
			}
			if(randomactions==4) {
				try {
					game.attack(Direction.UP);
					currChamp.setText(game.getCurrentChampion().stringgame());
					trnOrder.setText(getTurns());
					updateBoard();
				} catch (ChampionDisarmedException e1) {
					generateActions();
				} catch (NotEnoughResourcesException e1) {
					endTurn();
				} catch (InvalidTargetException e1) {
					generateActions();
				}
			}
			if(randomactions==5) {
				try {
					game.attack(Direction.DOWN);
					currChamp.setText(game.getCurrentChampion().stringgame());
					trnOrder.setText(getTurns());
					updateBoard();
				} catch (ChampionDisarmedException e1) {
					generateActions();
				} catch (NotEnoughResourcesException e1) {
					endTurn();
				} catch (InvalidTargetException e1) {
					generateActions();
				}
			}
			if(randomactions==6) {
				try {
					game.attack(Direction.LEFT);
					currChamp.setText(game.getCurrentChampion().stringgame());
					trnOrder.setText(getTurns());
					updateBoard();
				} catch (ChampionDisarmedException e1) {
					generateActions();
				} catch (NotEnoughResourcesException e1) {
					endTurn();
				} catch (InvalidTargetException e1) {
					generateActions();
				}
			}
			if(randomactions==7) {
				try {
					game.attack(Direction.RIGHT);
					currChamp.setText(game.getCurrentChampion().stringgame());
					trnOrder.setText(getTurns());
					updateBoard();
				} catch (ChampionDisarmedException e1) {
					generateActions();
				} catch (NotEnoughResourcesException e1) {
					endTurn();
				} catch (InvalidTargetException e1) {
					generateActions();
				}
			}
			if(randomactions==8) {
				direction = (int)(Math.random()*4);
				if(game.getCurrentChampion().getAbilities().get(0).getCastArea()==AreaOfEffect.SELFTARGET || game.getCurrentChampion().getAbilities().get(0).getCastArea()==AreaOfEffect.TEAMTARGET || game.getCurrentChampion().getAbilities().get(0).getCastArea()==AreaOfEffect.SURROUND) {
		    		try {
						game.castAbility(game.getCurrentChampion().getAbilities().get(0));
						currChamp.setText(game.getCurrentChampion().stringgame());
						trnOrder.setText(getTurns());
						updateBoard();
					} catch (NotEnoughResourcesException e1) {
						endTurn();
					} catch (AbilityUseException e1) {
						generateActions();
					} catch (CloneNotSupportedException e1) {
						generateActions();
					}
		    	}
				if(game.getCurrentChampion().getAbilities().get(0).getCastArea()==AreaOfEffect.DIRECTIONAL) {
					if(direction==0) {
				    	try {
							game.castAbility(game.getCurrentChampion().getAbilities().get(0), Direction.UP);
							currChamp.setText(game.getCurrentChampion().stringgame());
							trnOrder.setText(getTurns());
							updateBoard();
						} catch (NotEnoughResourcesException e1) {
							endTurn();
						} catch (AbilityUseException e1) {
							generateActions();
						} catch (CloneNotSupportedException e1) {
							generateActions();
						}
				    }
				    if(direction==1) {
				    	try {
							game.castAbility(game.getCurrentChampion().getAbilities().get(0), Direction.DOWN);
							currChamp.setText(game.getCurrentChampion().stringgame());
							trnOrder.setText(getTurns());
							updateBoard();
						} catch (NotEnoughResourcesException e1) {
							endTurn();
						} catch (AbilityUseException e1) {
							generateActions();
						} catch (CloneNotSupportedException e1) {
							generateActions();
						}
				    }
				    if(direction==2) {
				    	try {
							game.castAbility(game.getCurrentChampion().getAbilities().get(0), Direction.RIGHT);
							currChamp.setText(game.getCurrentChampion().stringgame());
							trnOrder.setText(getTurns());
							updateBoard();
						} catch (NotEnoughResourcesException e1) {
							endTurn();
						} catch (AbilityUseException e1) {
							generateActions();
						} catch (CloneNotSupportedException e1) {
							generateActions();
						}
				    }
				    if(direction==3) {
				    	try {
							game.castAbility(game.getCurrentChampion().getAbilities().get(0), Direction.LEFT);
							currChamp.setText(game.getCurrentChampion().stringgame());
							trnOrder.setText(getTurns());
							updateBoard();
						} catch (NotEnoughResourcesException e1) {
							endTurn();
						} catch (AbilityUseException e1) {
							generateActions();
						} catch (CloneNotSupportedException e1) {
							generateActions();
						}
				    }
		    	}
			}
			if(randomactions==9) {
				direction = (int)(Math.random()*4);
				if(game.getCurrentChampion().getAbilities().get(1).getCastArea()==AreaOfEffect.SELFTARGET || game.getCurrentChampion().getAbilities().get(1).getCastArea()==AreaOfEffect.TEAMTARGET || game.getCurrentChampion().getAbilities().get(1).getCastArea()==AreaOfEffect.SURROUND) {
		    		try {
						game.castAbility(game.getCurrentChampion().getAbilities().get(1));
						currChamp.setText(game.getCurrentChampion().stringgame());
						trnOrder.setText(getTurns());
						updateBoard();
					} catch (NotEnoughResourcesException e1) {
						endTurn();
					} catch (AbilityUseException e1) {
						generateActions();
					} catch (CloneNotSupportedException e1) {
						generateActions();
					}
		    	}
				if(game.getCurrentChampion().getAbilities().get(0).getCastArea()==AreaOfEffect.DIRECTIONAL) {
					if(direction==0) {
				    	try {
							game.castAbility(game.getCurrentChampion().getAbilities().get(1), Direction.UP);
							currChamp.setText(game.getCurrentChampion().stringgame());
							trnOrder.setText(getTurns());
							updateBoard();
						} catch (NotEnoughResourcesException e1) {
							endTurn();
						} catch (AbilityUseException e1) {
							generateActions();
						} catch (CloneNotSupportedException e1) {
							generateActions();
						}
				    }
				    if(direction==1) {
				    	try {
							game.castAbility(game.getCurrentChampion().getAbilities().get(1), Direction.DOWN);
							currChamp.setText(game.getCurrentChampion().stringgame());
							trnOrder.setText(getTurns());
							updateBoard();
						} catch (NotEnoughResourcesException e1) {
							endTurn();
						} catch (AbilityUseException e1) {
							generateActions();
						} catch (CloneNotSupportedException e1) {
							generateActions();
						}
				    }
				    if(direction==2) {
				    	try {
							game.castAbility(game.getCurrentChampion().getAbilities().get(1), Direction.RIGHT);
							currChamp.setText(game.getCurrentChampion().stringgame());
							trnOrder.setText(getTurns());
							updateBoard();
						} catch (NotEnoughResourcesException e1) {
							endTurn();
						} catch (AbilityUseException e1) {
							generateActions();
						} catch (CloneNotSupportedException e1) {
							generateActions();
						}
				    }
				    if(direction==3) {
				    	try {
							game.castAbility(game.getCurrentChampion().getAbilities().get(1), Direction.LEFT);
							currChamp.setText(game.getCurrentChampion().stringgame());
							trnOrder.setText(getTurns());
							updateBoard();
						} catch (NotEnoughResourcesException e1) {
							endTurn();
						} catch (AbilityUseException e1) {
							generateActions();
						} catch (CloneNotSupportedException e1) {
							generateActions();
						}
				    }
		    	}
			}
			if(randomactions==10) {
				direction = (int)(Math.random()*4);
				if(game.getCurrentChampion().getAbilities().get(2).getCastArea()==AreaOfEffect.SELFTARGET || game.getCurrentChampion().getAbilities().get(2).getCastArea()==AreaOfEffect.TEAMTARGET || game.getCurrentChampion().getAbilities().get(2).getCastArea()==AreaOfEffect.SURROUND) {
		    		try {
						game.castAbility(game.getCurrentChampion().getAbilities().get(2));
						currChamp.setText(game.getCurrentChampion().stringgame());
						trnOrder.setText(getTurns());
						updateBoard();
					} catch (NotEnoughResourcesException e1) {
						endTurn();
					} catch (AbilityUseException e1) {
						generateActions();
					} catch (CloneNotSupportedException e1) {
						generateActions();
					}
		    	}
				if(game.getCurrentChampion().getAbilities().get(0).getCastArea()==AreaOfEffect.DIRECTIONAL) {
					if(direction==0) {
				    	try {
							game.castAbility(game.getCurrentChampion().getAbilities().get(2), Direction.UP);
							currChamp.setText(game.getCurrentChampion().stringgame());
							trnOrder.setText(getTurns());
							updateBoard();
						} catch (NotEnoughResourcesException e1) {
							endTurn();
						} catch (AbilityUseException e1) {
							generateActions();
						} catch (CloneNotSupportedException e1) {
							generateActions();
						}
				    }
				    if(direction==1) {
				    	try {
							game.castAbility(game.getCurrentChampion().getAbilities().get(2), Direction.DOWN);
							currChamp.setText(game.getCurrentChampion().stringgame());
							trnOrder.setText(getTurns());
							updateBoard();
						} catch (NotEnoughResourcesException e1) {
							endTurn();
						} catch (AbilityUseException e1) {
							generateActions();
						} catch (CloneNotSupportedException e1) {
							generateActions();
						}
				    }
				    if(direction==2) {
				    	try {
							game.castAbility(game.getCurrentChampion().getAbilities().get(2), Direction.RIGHT);
							currChamp.setText(game.getCurrentChampion().stringgame());
							trnOrder.setText(getTurns());
							updateBoard();
						} catch (NotEnoughResourcesException e1) {
							endTurn();
						} catch (AbilityUseException e1) {
							generateActions();
						} catch (CloneNotSupportedException e1) {
							generateActions();
						}
				    }
				    if(direction==3) {
				    	try {
							game.castAbility(game.getCurrentChampion().getAbilities().get(2), Direction.LEFT);
							currChamp.setText(game.getCurrentChampion().stringgame());
							trnOrder.setText(getTurns());
							updateBoard();
						} catch (NotEnoughResourcesException e1) {
							endTurn();
						} catch (AbilityUseException e1) {
							generateActions();
						} catch (CloneNotSupportedException e1) {
							generateActions();
						}
				    }
		    	}
			}
			if(randomactions==11) {
				try {
					game.useLeaderAbility();
					updateBoard();
					currChamp.setText(game.getCurrentChampion().stringgame());
				} catch (LeaderNotCurrentException e1) {
					generateActions();
				} catch (LeaderAbilityAlreadyUsedException e1) {
					generateActions();
				}
			}
			if(randomactions==12) {
				endTurn();
			}
		}
	}
	//end turn
	public void endTurn() {
		game.endTurn();
		currChamp.setText(game.getCurrentChampion().stringgame());
    	trnOrder.setText(getTurns());
    	updateBoard();
    	if(game.getSecondPlayer().getTeam().contains(game.getCurrentChampion()) && game.checkGameOver()==null) {
    		generateActions();
    	}
	}
}
