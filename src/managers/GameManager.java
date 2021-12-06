package managers;

import javafx.application.Application;

import java.io.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import java.util.Random;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import board.Level;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import javafx.stage.FileChooser;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;

import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import services.AudioPlayer;
import services.Globals;
//import javax.swing.text.html.ImageView;

/**
 * DeathMechInventoryItem.java
 * @author Sam B, ....
 * @version 1
 * Last Mod Date: 05/12/2021
 * Description: handles the menus before the running of a game
 */

public class GameManager extends Application {

    /** The Constant WINDOW_WIDTH. */
    private static final int WINDOW_WIDTH = 600;

    /** The Constant WINDOW_HEIGHT. */
    private static final int WINDOW_HEIGHT = 400;

    /** The Constant CANVAS_WIDTH. */
    private static final int CANVAS_WIDTH = 400;

    /** The Constant CANVAS_HEIGHT. */
    private static final int CANVAS_HEIGHT = 400;

    public static final String BUTTON_STYLE = "fx-background-color: \n"
	    + "        linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),\n" + "        #9d4024,\n"
	    + "        #d86e3a,\n" + "        radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c); "
	    + "-fx-color: black; " + "-fx-font-family: Impact; " + "-fx-font-size: 20;" + "-fx-font-weight: bold;";
    public static final String HOVERED_BUTTON_STYLE = "fx-background-color: \n"
	    + "        linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),\n" + "        #9d4024,\n"
	    + "        #d86e3a,\n" + "        radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c); "
	    + "-fx-color: black; " + "-fx-font-family: Impact; " + "-fx-font-size: 20;" + "-fx-font-weight: bold;"
	    + "-fx-opacity: 0.5";

    /** The Constant SHAPE_SIZE. */
    private static final int SHAPE_SIZE = 30;

    /** The main menu. */
    public static Stage mainMenu;

    public static Stage choosePlayerMenu;

    public static Stage newPlayerMenu;

    public static Stage deletePlayerMenu;
    public static Stage chooseLevelMenu;

    /** The current player. */
    public Player currentPlayer;

    /** The canvas. */
    private Canvas canvas;

    /** Leaderbord files */
    public static final File LEVEL_1LB = new File("res/LeaderboardFiles/LEVEL_1lb.txt");
    public static final File LEVEL_2LB = new File("res/LeaderboardFiles/LEVEL_2lb.txt");
    public static final File LEVEL_3LB = new File("res/LeaderboardFiles/LEVEL_3lb.txt");
    public static final File LEVEL_4LB = new File("res/LeaderboardFiles/LEVEL_4lb.txt");
    public static final File LEVEL_5LB = new File("res/LeaderboardFiles/LEVEL_5lb.txt");

    /**
     * Start function, starts the first main menu
     *
     * @param primaryStage the primary stage
     */
    public void start(Stage primaryStage) {
	// Build the GUI
	Pane root = buildMainMenu();
	root.setStyle("-fx-background-color: transparent;");
	// Create a scene from the GUI
	Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

	scene.setFill(new ImagePattern(new Image("file:res/PIXEL_ART.jpg", WINDOW_WIDTH, WINDOW_HEIGHT, false, false)));
	primaryStage.setTitle("Futuro");

	AudioPlayer.playMainMenu();

	// Display the scene on the stage
	primaryStage.setScene(scene);
	primaryStage.setResizable(false);
	mainMenu = primaryStage;
	mainMenu.show();
    }

    /**
     * The main method.
     *
     * @param args the arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
	System.out.println(javafx.scene.text.Font.getFamilies());
	launch(args);
    }

    /**
     * Create the GUI for main menu.
     *
     * @return the pane
     */
    private Pane buildMainMenu() {
	BorderPane root = new BorderPane();
	Player player;
	// Create canvas
	canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
	root.setCenter(canvas);

	// Create the main buttons for navigating the main menu
	Button startGame = new Button("START GAME");
	Button choosePlayer = new Button("CHOOSE PLAYER");
	Button newPlayer = new Button("CREATE PLAYER");
	Button deletePlayer = new Button("DELETE PLAYER");
	Button leaderboards = new Button("DISPLAY LEADERBOARD");
	Button exitMainMenu = new Button("EXIT GAME");
	startGame.setStyle(BUTTON_STYLE);
	choosePlayer.setStyle(BUTTON_STYLE);
	newPlayer.setStyle(BUTTON_STYLE);
	deletePlayer.setStyle(BUTTON_STYLE);
	leaderboards.setStyle(BUTTON_STYLE);
	exitMainMenu.setStyle(BUTTON_STYLE);

	// Create a sidebar with some nice padding and spacing
	VBox sidebar = new VBox();
	sidebar.setSpacing(10);
	sidebar.setAlignment(Pos.CENTER);
	sidebar.setPadding(new Insets(10, 10, 10, 10));

	// Add the elements on the canvas onto the sidebar
	root.setCenter(sidebar);
	sidebar.getChildren().addAll(startGame, choosePlayer, newPlayer, deletePlayer, exitMainMenu, leaderboards);

	startGame.setOnMouseEntered(e -> {
	    startGame.setStyle(HOVERED_BUTTON_STYLE);
	});

	startGame.setOnMouseExited(e -> {
	    startGame.setStyle(BUTTON_STYLE);
	});

	startGame.setOnAction(e -> {
	    if (this.currentPlayer != null) {
		Pane chooseLevelPane = buildChooseLevel();
		Scene chooseLevelScene = new Scene(chooseLevelPane, 300, 400);
		Stage chooseLevelStage = new Stage();
		chooseLevelStage.setScene(chooseLevelScene);
		chooseLevelStage.setTitle("Choose Level");
		GameManager.chooseLevelMenu = chooseLevelStage;
		GameManager.chooseLevelMenu.show();
	    } else
		showAlert("INFORMATION", "No player has been selected",
			"Please select a player before starting the game");
	});
	deletePlayer.setOnMouseEntered(e -> {
	    deletePlayer.setStyle(HOVERED_BUTTON_STYLE);
	});
	deletePlayer.setOnMouseExited(e -> {
	    deletePlayer.setStyle(BUTTON_STYLE);
	});
	deletePlayer.setOnAction(e -> {
	    Pane deletePlayerPane = buildDeletePlayer();
	    Scene deletePlayerScene = new Scene(deletePlayerPane, 300, 200);
	    Stage deletePlayerStage = new Stage();
	    deletePlayerStage.setScene(deletePlayerScene);
	    deletePlayerStage.setTitle("New player");
	    this.deletePlayerMenu = deletePlayerStage;
	    this.deletePlayerMenu.show();
	});
	newPlayer.setOnMouseEntered(e -> {
	    newPlayer.setStyle(HOVERED_BUTTON_STYLE);
	});
	newPlayer.setOnMouseExited(e -> {
	    newPlayer.setStyle(BUTTON_STYLE);
	});
	// Create a new profile to play the game as
	newPlayer.setOnAction(e -> {
	    Pane newPlayerPane = buildNewPlayer();
	    Scene newPlayerScene = new Scene(newPlayerPane, 300, 200);
	    Stage newPlayerStage = new Stage();
	    newPlayerStage.setScene(newPlayerScene);
	    newPlayerStage.setTitle("New player");
	    this.newPlayerMenu = newPlayerStage;
	    this.newPlayerMenu.show();
	});
	exitMainMenu.setOnMouseEntered(e -> {
	    exitMainMenu.setStyle(HOVERED_BUTTON_STYLE);
	});
	exitMainMenu.setOnMouseExited(e -> {
	    exitMainMenu.setStyle(BUTTON_STYLE);
	});
	// Close the main menu
	exitMainMenu.setOnAction(e -> {
	    GameManager.mainMenu.hide();
	});
	choosePlayer.setOnMouseEntered(e -> {
	    choosePlayer.setStyle(HOVERED_BUTTON_STYLE);
	});
	choosePlayer.setOnMouseExited(e -> {
	    choosePlayer.setStyle(BUTTON_STYLE);
	});
	choosePlayer.setOnAction(e -> {
	    Pane choosePlayerPane = buildChoosePlayer();
	    Scene choosePlayerScene = new Scene(choosePlayerPane, 300, 200);
	    Stage choosePlayerStage = new Stage();
	    choosePlayerStage.setScene(choosePlayerScene);
	    choosePlayerStage.setTitle("Choose player");
	    this.choosePlayerMenu = choosePlayerStage;
	    this.choosePlayerMenu.show();
	});
	leaderboards.setOnMouseEntered(e -> {
	    leaderboards.setStyle(HOVERED_BUTTON_STYLE);
	});
	leaderboards.setOnMouseExited(e -> {
	    leaderboards.setStyle(BUTTON_STYLE);
	});
	leaderboards.setOnAction(e -> {
	    Pane displayleaderboardPane = builddisplayLeaderboard();
	});

	return root;
    }

    private Pane builddisplayLeaderboard() {
	BorderPane root = new BorderPane();
	Pane chooseLeaderboardPane = buildChooseLeaderboard();
	Scene chooseLeaderboardScene = new Scene(chooseLeaderboardPane, 500, 500);
	Stage chooseLeaderboardStage = new Stage();
	chooseLeaderboardStage.setScene(chooseLeaderboardScene);
	chooseLeaderboardStage.setTitle("Choose leaderboard to display");
	chooseLeaderboardStage.show();
	return root;
    }

    private Pane buildNewPlayer() {
	BorderPane root = new BorderPane();
	VBox sidebar = new VBox();
	sidebar.setSpacing(10);
	sidebar.setPadding(new Insets(10, 10, 10, 10));

	root.setStyle("-fx-background-color: Gray");

	Label playerID = new Label("ID of player: ");
	TextField playerIDInput = new TextField();
	Label playerName = new Label("Name of player: ");
	TextField playerNameInput = new TextField();
	Button newPlayerButton = new Button("CREATE PLAYER");

	newPlayerButton.setStyle(BUTTON_STYLE);
	newPlayerButton.setStyle(BUTTON_STYLE);
	root.setLeft(sidebar);
	sidebar.getChildren().addAll(playerID, playerIDInput, playerName, playerNameInput, newPlayerButton);

	newPlayerButton.setOnMouseEntered(e -> {
	    newPlayerButton.setStyle(HOVERED_BUTTON_STYLE);
	});
	newPlayerButton.setOnMouseExited(e -> {
	    newPlayerButton.setStyle(BUTTON_STYLE);
	});

	newPlayerButton.setOnAction(e -> {
	    try {
		boolean created = (FileManager.writeToPlayerFile(
			new Player(playerIDInput.getText() + "," + playerNameInput.getText() + "," + "1")));
		Alert alert = new Alert(AlertType.CONFIRMATION);
		if (created) {
		    alert.setTitle("SUCCESS");
		    alert.setHeaderText("Player created");
		    alert.setContentText("Huzzah! You have created a Player.");
		    alert.showAndWait().ifPresent(rs -> {
			if (rs == ButtonType.OK) {
			    System.out.println("Pressed OK.");
			    this.newPlayerMenu.hide();
			}
		    });
		} else {
		    alert.setTitle("FAILURE");
		    alert.setHeaderText("Player not created");
		    alert.setContentText("A player with that ID already exists");
		    alert.showAndWait().ifPresent(rs -> {
			if (rs == ButtonType.OK) {
			    System.out.println("Pressed OK.");
			}
		    });
		}
	    } catch (Exception exception) {
		exception.printStackTrace();
	    }
	});
	return root;
    }

    /**
     * Builds the delete player UI.
     *
     * @return the pane
     */
    private Pane buildDeletePlayer() {
	BorderPane root = new BorderPane();
	root.setStyle("-fx-background-color: Gray");
	VBox sidebar = new VBox();
	sidebar.setSpacing(10);
	sidebar.setPadding(new Insets(10, 10, 10, 10));

	Label playerID = new Label("ID of player: ");
	TextField playerIDInput = new TextField();
	Button deletePlayerButton = new Button("Delete player");
	playerID.setStyle("-fx-font-family: Impact;" + "-fx-font-size: 20");

	root.setCenter(sidebar);
	sidebar.getChildren().addAll(playerID, playerIDInput, deletePlayerButton);
	deletePlayerButton.setStyle(BUTTON_STYLE);

	deletePlayerButton.setOnMouseEntered(e -> {
	    deletePlayerButton.setStyle(HOVERED_BUTTON_STYLE);
	});
	deletePlayerButton.setOnMouseExited(e -> {
	    deletePlayerButton.setStyle(BUTTON_STYLE);
	});
	deletePlayerButton.setOnAction(e -> {
	    try {
		boolean deleted = (FileManager
			.deleteFromPlayerFile(new Player(Integer.valueOf(playerIDInput.getText()), "ass", 0)));
		Alert alert = new Alert(AlertType.CONFIRMATION);
		if (deleted) {
		    alert.setTitle("SUCCESS");
		    alert.setContentText("Huzzah! You have deleted a Player.");
		    alert.showAndWait().ifPresent(rs -> {
			if (rs == ButtonType.OK) {
			    System.out.println("Pressed OK.");
			    this.deletePlayerMenu.hide();
			}
		    });
		} else {
		    alert.setTitle("FAILURE");
		    alert.setHeaderText("Player not deleted");
		    alert.setContentText("A player with that ID can't be found");
		    alert.showAndWait().ifPresent(rs -> {
			if (rs == ButtonType.OK) {
			    System.out.println("Pressed OK.");
			}
		    });
		}
	    } catch (Exception exception) {
		exception.printStackTrace();
	    }
	});
	return root;
    }

    // SetOnAction needs to load a file (open file manager? input level id like
    // choose player does?)
    private Pane loadNewGame() {
	BorderPane root = new BorderPane();
	VBox sidebar = new VBox();
	sidebar.setSpacing(10);
	sidebar.setPadding(new Insets(10, 10, 10, 10));

	FileChooser selectLoadFile = new FileChooser();
	selectLoadFile.setTitle("Select game file");

	Button loadButton = new Button("Select game file");

	root.setLeft(sidebar);
	sidebar.getChildren().add(loadButton);
	loadButton.setOnAction(e -> {
	    File selectedFile = selectLoadFile.showOpenDialog(mainMenu);
	    String selectedFilePath = selectedFile.getAbsolutePath();
	    Level level = null;
	    try {
		level = FileManager.readLevel(selectedFilePath);
	    } catch (Exception exception) {
		exception.printStackTrace();
	    }
	    Game game = new Game(this.currentPlayer, level);

	    mainMenu.close();
	    try {
		game.showGame();
	    } catch (Exception ex) {
		ex.printStackTrace();
	    }
	});
	return root;
    }

    /**
    * Builds the choose level UI.
    *
    * @return the pane
    */
    private Pane buildChooseLeaderboard() {
	BorderPane root = new BorderPane();
	Button levelOne = new Button("Level One");
	Button levelTwo = new Button("Level Two");
	Button levelThree = new Button("Level Three");
	Button levelFour = new Button("Level Four");
	Button levelFive = new Button("Level Five");

	levelOne.setStyle(BUTTON_STYLE);
	levelTwo.setStyle(BUTTON_STYLE);
	levelThree.setStyle(BUTTON_STYLE);
	levelFour.setStyle(BUTTON_STYLE);
	levelFive.setStyle(BUTTON_STYLE);

	levelOne.setOnMouseEntered(e -> {
	    levelOne.setStyle(HOVERED_BUTTON_STYLE);
	});
	levelOne.setOnMouseExited(e -> {
	    levelOne.setStyle(BUTTON_STYLE);
	});
	levelTwo.setOnMouseEntered(e -> {
	    levelTwo.setStyle(HOVERED_BUTTON_STYLE);
	});
	levelTwo.setOnMouseExited(e -> {
	    levelTwo.setStyle(BUTTON_STYLE);
	});
	levelThree.setOnMouseEntered(e -> {
	    levelThree.setStyle(HOVERED_BUTTON_STYLE);
	});
	levelThree.setOnMouseExited(e -> {
	    levelThree.setStyle(BUTTON_STYLE);
	});
	levelFour.setOnMouseEntered(e -> {
	    levelFour.setStyle(HOVERED_BUTTON_STYLE);
	});
	levelFour.setOnMouseExited(e -> {
	    levelFour.setStyle(BUTTON_STYLE);
	});
	levelFive.setOnMouseEntered(e -> {
	    levelFive.setStyle(HOVERED_BUTTON_STYLE);
	});
	levelFive.setOnMouseExited(e -> {
	    levelFive.setStyle(BUTTON_STYLE);
	});

	root.setStyle("-fx-background-color: Gray");

	VBox sidebar = new VBox();
	sidebar.setSpacing(10);
	sidebar.setPadding(new Insets(10, 10, 10, 10));

	root.setCenter(sidebar);
	sidebar.getChildren().addAll(levelOne, levelTwo, levelThree, levelFour, levelFive);
	sidebar.setAlignment(Pos.CENTER);

	levelOne.setOnAction(e -> {
	    Stage lvl1Stage = new Stage();
	    lvl1Stage.setTitle("Level 1 Leaderboard");

	    HashMap<Integer, Integer> playerIdAndScore = new HashMap<>();
	    BufferedReader br = null;
	    try {
		br = new BufferedReader(new FileReader(LEVEL_1LB));
		String currentLine;
		String[] currentLineSplit;
		while ((currentLine = br.readLine()) != null) {
		    currentLineSplit = currentLine.split(",");
		    playerIdAndScore.put(Integer.valueOf(currentLineSplit[0]), Integer.valueOf(currentLineSplit[1]));
		}
		Leaderboard lvl1 = new Leaderboard(FileManager.readLevel("res\\Levels\\LEVEL_1.txt").getLevelID(),
			playerIdAndScore);
		// add each playerID and score to lvl1 leaderboard
		playerIdAndScore.forEach(lvl1::addToLeaderBoard);
		ArrayList<Label> labels = new ArrayList<Label>();
		lvl1.getIdsToScores().forEach((k, v) -> {
		    labels.add(new Label("PLAYER ID:" + k + "¦PLAYER SCORE:" + v));
		});

		GridPane gridPane = new GridPane();
		for (int i = 0; i < labels.size(); i++) {
		    gridPane.add(labels.get(i), 0, i, 1, 1);
		}
		Scene lvl1Scene = new Scene(gridPane, 300, 300);
		lvl1Stage.setScene(lvl1Scene);
		lvl1Stage.show();

	    } catch (IOException exception) {
		System.err.println("");
	    } catch (Exception exception) {
		exception.printStackTrace();
	    } finally {
		try {
		    br.close();
		} catch (IOException exception) {
		    System.err.println("There was an error closing the BufferedReader");
		}
	    }
	});

	levelTwo.setOnAction(e -> {
	    Stage lvl2Stage = new Stage();
	    lvl2Stage.setTitle("Level 2 Leaderboard");

	    HashMap<Integer, Integer> playerIdAndScore = new HashMap<>();
	    BufferedReader br = null;
	    try {
		br = new BufferedReader(new FileReader(LEVEL_2LB));
		String currentLine;
		String[] currentLineSplit;
		while ((currentLine = br.readLine()) != null) {
		    currentLineSplit = currentLine.split(",");
		    playerIdAndScore.put(Integer.valueOf(currentLineSplit[0]), Integer.valueOf(currentLineSplit[1]));
		}
		Leaderboard lvl2 = new Leaderboard(FileManager.readLevel("res\\Levels\\LEVEL_2.txt").getLevelID(),
			playerIdAndScore);
		// add each playerID and score to lvl2 leaderboard
		playerIdAndScore.forEach(lvl2::addToLeaderBoard);
		ArrayList<Label> labels = new ArrayList<Label>();
		lvl2.getIdsToScores().forEach((k, v) -> {
		    labels.add(new Label("PLAYER ID:" + k + "¦PLAYER SCORE:" + v));
		});

		GridPane gridPane = new GridPane();
		for (int i = 0; i < labels.size(); i++) {
		    gridPane.add(labels.get(i), 0, i, 1, 1);
		}
		Scene lvl2Scene = new Scene(gridPane, 300, 300);
		lvl2Stage.setScene(lvl2Scene);
		lvl2Stage.show();

	    } catch (IOException exception) {
		System.err.println("");
	    } catch (Exception exception) {
		exception.printStackTrace();
	    } finally {
		try {
		    br.close();
		} catch (IOException exception) {
		    System.err.println("There was an error closing the BufferedReader");
		}
	    }
	});

	levelThree.setOnAction(e -> {
	    Stage lvl3Stage = new Stage();
	    lvl3Stage.setTitle("Level 3 Leaderboard");

	    HashMap<Integer, Integer> playerIdAndScore = new HashMap<>();
	    BufferedReader br = null;
	    try {
		br = new BufferedReader(new FileReader(LEVEL_3LB));
		String currentLine;
		String[] currentLineSplit;
		while ((currentLine = br.readLine()) != null) {
		    currentLineSplit = currentLine.split(",");
		    playerIdAndScore.put(Integer.valueOf(currentLineSplit[0]), Integer.valueOf(currentLineSplit[1]));
		}
		Leaderboard lvl3 = new Leaderboard(FileManager.readLevel("res\\Levels\\LEVEL_3.txt").getLevelID(),
			playerIdAndScore);
		// add each playerID and score to lvl3 leaderboard
		playerIdAndScore.forEach(lvl3::addToLeaderBoard);
		ArrayList<Label> labels = new ArrayList<Label>();
		lvl3.getIdsToScores().forEach((k, v) -> {
		    labels.add(new Label("PLAYER ID:" + k + "¦PLAYER SCORE:" + v));
		});

		GridPane gridPane = new GridPane();
		for (int i = 0; i < labels.size(); i++) {
		    gridPane.add(labels.get(i), 0, i, 1, 1);
		}
		Scene lvl3Scene = new Scene(gridPane, 300, 300);
		lvl3Stage.setScene(lvl3Scene);
		lvl3Stage.show();

	    } catch (IOException exception) {
		System.err.println("");
	    } catch (Exception exception) {
		exception.printStackTrace();
	    } finally {
		try {
		    br.close();
		} catch (IOException exception) {
		    System.err.println("There was an error closing the BufferedReader");
		}
	    }
	});

	levelFour.setOnAction(e -> {
	    Stage lvl4Stage = new Stage();
	    lvl4Stage.setTitle("Level 4 Leaderboard");

	    HashMap<Integer, Integer> playerIdAndScore = new HashMap<>();
	    BufferedReader br = null;
	    try {
		br = new BufferedReader(new FileReader(LEVEL_4LB));
		String currentLine;
		String[] currentLineSplit;
		while ((currentLine = br.readLine()) != null) {
		    currentLineSplit = currentLine.split(",");
		    playerIdAndScore.put(Integer.valueOf(currentLineSplit[0]), Integer.valueOf(currentLineSplit[1]));
		}
		Leaderboard lvl4 = new Leaderboard(FileManager.readLevel("res\\Levels\\LEVEL_4.txt").getLevelID(),
			playerIdAndScore);
		// add each playerID and score to lvl4 leaderboard
		playerIdAndScore.forEach(lvl4::addToLeaderBoard);
		ArrayList<Label> labels = new ArrayList<Label>();
		lvl4.getIdsToScores().forEach((k, v) -> {
		    labels.add(new Label("PLAYER ID:" + k + "¦PLAYER SCORE:" + v));
		});

		GridPane gridPane = new GridPane();
		for (int i = 0; i < labels.size(); i++) {
		    gridPane.add(labels.get(i), 0, i, 1, 1);
		}
		Scene lvl4Scene = new Scene(gridPane, 300, 300);
		lvl4Stage.setScene(lvl4Scene);
		lvl4Stage.show();

	    } catch (IOException exception) {
		System.err.println("");
	    } catch (Exception exception) {
		exception.printStackTrace();
	    } finally {
		try {
		    br.close();
		} catch (IOException exception) {
		    System.err.println("There was an error closing the BufferedReader");
		}
	    }
	});

	levelFive.setOnAction(e -> {
	    Stage lvl5Stage = new Stage();
	    lvl5Stage.setTitle("Level 5 Leaderboard");

	    HashMap<Integer, Integer> playerIdAndScore = new HashMap<>();
	    BufferedReader br = null;
	    try {
		br = new BufferedReader(new FileReader(LEVEL_5LB));
		String currentLine;
		String[] currentLineSplit;
		while ((currentLine = br.readLine()) != null) {
		    currentLineSplit = currentLine.split(",");
		    playerIdAndScore.put(Integer.valueOf(currentLineSplit[0]), Integer.valueOf(currentLineSplit[1]));
		}
		Leaderboard lvl5 = new Leaderboard(FileManager.readLevel("res\\Levels\\LEVEL_5.txt").getLevelID(),
			playerIdAndScore);
		// add each playerID and score to lvl5 leaderboard
		playerIdAndScore.forEach(lvl5::addToLeaderBoard);
		ArrayList<Label> labels = new ArrayList<Label>();
		lvl5.getIdsToScores().forEach((k, v) -> {
		    labels.add(new Label("PLAYER ID:" + k + "¦PLAYER SCORE:" + v));
		});

		GridPane gridPane = new GridPane();
		for (int i = 0; i < labels.size(); i++) {
		    gridPane.add(labels.get(i), 0, i, 1, 1);
		}
		Scene lvl5Scene = new Scene(gridPane, 300, 300);
		lvl5Stage.setScene(lvl5Scene);
		lvl5Stage.show();

	    } catch (IOException exception) {
		System.err.println("");
	    } catch (Exception exception) {
		exception.printStackTrace();
	    } finally {
		try {
		    br.close();
		} catch (IOException exception) {
		    System.err.println("There was an error closing the BufferedReader");
		}
	    }
	});

	return root;
    }

    /**
    * Builds the choose level UI.
    *
    * @return the pane
    */
    private Pane buildChooseLevel() {
	HashMap<Button, String> buttonsToLevelFiles = new HashMap<>();
	Button levelOne = new Button("Level One");
	levelOne.setStyle(BUTTON_STYLE);
	buttonsToLevelFiles.put(levelOne, Globals.LEVEL1);
	Button levelTwo = new Button("Level Two");
	levelTwo.setStyle(BUTTON_STYLE);
	buttonsToLevelFiles.put(levelTwo, Globals.LEVEL2);
	Button levelThree = new Button("Level Three");
	levelThree.setStyle(BUTTON_STYLE);
	buttonsToLevelFiles.put(levelThree, Globals.LEVEL3);
	Button levelFour = new Button("Level Four");
	levelFour.setStyle(BUTTON_STYLE);
	buttonsToLevelFiles.put(levelFour, Globals.LEVEL4);
	Button levelFive = new Button("Level Five");
	levelFive.setStyle(BUTTON_STYLE);
	buttonsToLevelFiles.put(levelFive, Globals.LEVEL5);

	BorderPane root = new BorderPane();
	root.setStyle("-fx-background-color: Gray");
	VBox sidebar = new VBox();
	sidebar.setSpacing(10);
	sidebar.setPadding(new Insets(10, 10, 10, 10));
	sidebar.setAlignment(Pos.CENTER);
	root.setCenter(sidebar);
	sidebar.getChildren().addAll(levelOne, levelTwo, levelThree, levelFour, levelFive);
	levelOne.setOnMouseEntered(e -> {
	    levelOne.setStyle(HOVERED_BUTTON_STYLE);
	});
	levelOne.setOnMouseExited(e -> {
	    levelOne.setStyle(BUTTON_STYLE);
	});
	levelTwo.setOnMouseExited(e -> {
	    levelTwo.setStyle(BUTTON_STYLE);
	});
	levelTwo.setOnMouseEntered(e -> {
	    levelTwo.setStyle(HOVERED_BUTTON_STYLE);
	});
	levelThree.setOnMouseExited(e -> {
	    levelThree.setStyle(BUTTON_STYLE);
	});
	levelThree.setOnMouseEntered(e -> {
	    levelThree.setStyle(HOVERED_BUTTON_STYLE);
	});
	levelFour.setOnMouseExited(e -> {
	    levelFour.setStyle(BUTTON_STYLE);
	});
	levelFour.setOnMouseEntered(e -> {
	    levelFour.setStyle(HOVERED_BUTTON_STYLE);
	});
	levelFive.setOnMouseEntered(e -> {
	    levelFive.setStyle(HOVERED_BUTTON_STYLE);
	});
	levelFive.setOnMouseExited(e -> {
	    levelFive.setStyle(BUTTON_STYLE);
	});
	Game game = new Game();

	for (Button btn : buttonsToLevelFiles.keySet()) {
	    btn.setOnAction(e -> {
		try {
		    game.setLevel(FileManager.readLevel(buttonsToLevelFiles.get(btn)));
		    game.setCurrentPlayer(this.currentPlayer);
		    if (this.currentPlayer.getMaxLevelID() < game.getLevel().getLevelID()) {
			showAlert("Information", "Level too high", "You can't play that level yet");
		    } else {
			mainMenu.close();
			AudioPlayer.stopAllMusic();
			game.showGame();
			chooseLevelMenu.close();
		    }
		} catch (Exception exception) {
		    exception.printStackTrace();
		}
	    });
	}
	return root;
    }
//        levelOne.setOnAction(e -> {
//        Level level = null;
//		try {
//			level = FileManager.readLevel("res\\Levels\\LEVEL_1.txt");
//        } catch (Exception exception) {
//			exception.printStackTrace();
//        }
//        Game game = new Game(level);
//        mainMenu.close();
//        try {
//        	AudioPlayer.stopAllMusic();
//            game.showGame();
//            chooseLevelMenu.close();
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        	}
//        });
//        levelTwo.setOnAction(e -> {
//            Level level = null;
//            	try {
//            		level = FileManager.readLevel("res\\Levels\\LEVEL_2.txt");
//            } catch (Exception exception) {
//                    exception.printStackTrace();
//            }
//            Game game = new Game(level);
//            mainMenu.close();
//            chooseLevelMenu.close();
//            try {
//            	AudioPlayer.stopAllMusic();
//                game.showGame();
//            } catch (Exception exception) {
//                exception.printStackTrace();
//            }
//        });
//        levelThree.setOnAction(e -> {
//            Level level = null;
//			try {
//				level = FileManager.readLevel("res\\Levels\\LEVEL_3.txt");
//			} catch (Exception exception) {
//				exception.printStackTrace();
//			}
//            Game game = new Game(level);
//            mainMenu.close();
//            chooseLevelMenu.close();
//            try {
//            	AudioPlayer.stopAllMusic();
//                game.showGame();
//            } catch (Exception exception) {
//                exception.printStackTrace();
//        	}
//        });
//        levelFour.setOnAction(e -> {
//            Level level = null;
//            	try {
//            		level = FileManager.readLevel("res\\Levels\\LEVEL_4.txt");
//            } catch (Exception exception) {
//                    exception.printStackTrace();
//            }
//            Game game = new Game(level);
//            mainMenu.close();
//            chooseLevelMenu.close();
//            try {
//            	AudioPlayer.stopAllMusic();
//                game.showGame();
//            } catch (Exception exception) {
//                exception.printStackTrace();
//            }
//        });
//        levelFive.setOnAction(e -> {
//            Level level = null;
//            	try {
//            		level = FileManager.readLevel("res\\Levels\\LEVEL_5.txt");
//            } catch (Exception exception) {
//                    exception.printStackTrace();
//            }
//            Game game = new Game(level);
//            mainMenu.close();
//            chooseLevelMenu.close();
//            try {
//            	AudioPlayer.stopAllMusic();
//                game.showGame();
//            } catch (Exception exception) {
//                exception.printStackTrace();
//            }
//        });

    /**
     * Builds the choose player UI.
     *
     * @return the pane
     */
    private Pane buildChoosePlayer() {
	BorderPane root = new BorderPane();
	VBox sidebar = new VBox();
	sidebar.setSpacing(10);
	sidebar.setAlignment(Pos.CENTER);
	sidebar.setPadding(new Insets(10, 10, 10, 10));
	Label playerID = new Label("ID of player: ");
	playerID.setStyle("-fx-font-family: Impact;" + "-fx-font-size: 20");
	TextField playerIDInput = new TextField();
	Button choosePlayerButton = new Button("Choose player");
	choosePlayerButton.setStyle(BUTTON_STYLE);
	root.setCenter(sidebar);
	root.setStyle("-fx-background-color: Gray");
	sidebar.getChildren().addAll(playerID, playerIDInput, choosePlayerButton);
	choosePlayerButton.setOnMouseEntered(e -> {
	    choosePlayerButton.setStyle(HOVERED_BUTTON_STYLE);
	});

	choosePlayerButton.setOnMouseExited(e -> {
	    choosePlayerButton.setStyle(BUTTON_STYLE);
	});
	choosePlayerButton.setOnAction(e -> {
	    if (playerIDInput.getText() != null) {
		this.currentPlayer = FileManager.getPlayer(Integer.valueOf(playerIDInput.getText()));
		String playerIDGiven = String.valueOf(currentPlayer.getPlayerID());
		if (this.currentPlayer != null) {
		    showAlert("INFORMATION", "Player found", "Player ID: " + playerIDGiven);
		    GameManager.choosePlayerMenu.close();
		} else {
		    showAlert("INFORMATION", "Could not find player", "Player ID: " + playerIDGiven);
		}
	    } else {
		showAlert("INFORMATION", "Invalid player ID given", "Please try again.");
	    }
	});

	return root;
    }

    private void showAlert(String title, String header, String content) {
	Alert alert = new Alert(AlertType.INFORMATION);
	alert.setTitle(title);
	alert.setHeaderText(header);
	alert.setContentText(content);
	alert.showAndWait().ifPresent(rs -> {
	    if (rs == ButtonType.OK) {
		System.out.println("Pressed OK.");
	    }
	});
    }

}
