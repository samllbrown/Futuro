package managers;

import javafx.application.Application;
import java.util.concurrent.TimeUnit;
import board.Level;
import javafx.geometry.Insets;
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
import javafx.stage.Stage;
import services.audioPlayer;

/**
 * FileManager.java
 * @author Sam B, ..... 
 * @version 1.5
 * Last Mod Date: 30/11/2021
 */

/**
 * The Class GameManager is used to manage the UI which processes the player and
 * the start of a new game.
 */
public class GameManager extends Application {

	/**
	 * The Constant WINDOW_WIDTH. The width of the window
	 */
	private static final int WINDOW_WIDTH = 600;

	/**
	 * The Constant WINDOW_HEIGHT. The height of the window
	 */
	private static final int WINDOW_HEIGHT = 400;

	/** The Constant CANVAS_WIDTH. */
	private static final int CANVAS_WIDTH = 400;

	/** The Constant CANVAS_HEIGHT. */
	private static final int CANVAS_HEIGHT = 400;

	/** MainMenu stage used for showing/opening the menu */
	public static Stage mainMenu;

	/** The current player. */
	public Player currentPlayer;

	/**
	 * Start
	 *Y
	 * @param primaryStage the main stage
	 */
	public void start(Stage primaryStage) {
		// Build the Initial UI
		Pane root = buildMainMenu();

		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		primaryStage.setTitle("Futuro");

		audioPlayer.playMainMenu();

		// Display the scene on the stage
		primaryStage.setScene(scene);
		GameManager.mainMenu = primaryStage;
		splashScreen();
	}

	/**
	 * Splash screen.
	 */
	public void splashScreen() {
		Pane root = buildSplashScreen();
		Stage splashScreen = new Stage();
		splashScreen.setTitle("Futuro");
		ImageView img = new ImageView();
		img.setImage(new Image("file:res/Sprites/acidPuddle.png", 50, 50, false, false));
		root.getChildren().add(img);
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		splashScreen.setScene(scene);
		splashScreen.show();
		try {
			TimeUnit.SECONDS.sleep(3);
			splashScreen.hide();
			GameManager.mainMenu.show();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Builds the splash screen.
	 *
	 * @return the pane
	 */
	private Pane buildSplashScreen() {
		BorderPane root = new BorderPane();
		Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
		root.setCenter(canvas);
		return root;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		launch(args);
	}

	/**
	 * Create the Main menu GUI.
	 *
	 * @return the pane
	 */
	private Pane buildMainMenu() {
		// Create top-level panel that will hold all GUI
		BorderPane root = new BorderPane();
		// Create canvas
		Canvas canvas = new Canvas(500, 500);
		root.setCenter(canvas);
		// Create the main buttons for navigating the main menu
		Button createPlayer = new Button("(START GAME (testing)");
		Button choosePlayer = new Button("CHOOSE PLAYER");
		Button newPlayer = new Button("CREATE PLAYER");
		Button deletePlayer = new Button("DELETE PLAYER");

		Button exitMainMenu = new Button("EXIT GAME");

		// Create a sidebar with some nice padding and spacing
		VBox sidebar = new VBox();
		sidebar.setSpacing(10);
		sidebar.setPadding(new Insets(10, 10, 10, 10));

		// Add the elements on the canvas onto the sidebar
		root.setLeft(sidebar);
		sidebar.getChildren().addAll(createPlayer, choosePlayer, newPlayer, deletePlayer, exitMainMenu);

		createPlayer.setOnAction(e -> {
			Level level = null;
			try {
				level = FileManager.readLevel("res\\Levels\\LEVEL_1.txt");
			} catch (Exception exception) {
				exception.printStackTrace();
			}
			Game game = new Game(level);
			mainMenu.close();
			try {
				audioPlayer.stopAllMusic();
				game.showGame();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		});

		deletePlayer.setOnAction(e -> {
			Pane deletePlayerPane = buildDeletePlayer();
			Scene deletePlayerScene = new Scene(deletePlayerPane, 300, 200);
			Stage deletePlayerStage = new Stage();
			deletePlayerStage.setScene(deletePlayerScene);
			deletePlayerStage.setTitle("New player");
			deletePlayerStage.show();
		});

		// Create a new profile to play the game as
		newPlayer.setOnAction(e -> {
			Pane newPlayerPane = buildNewPlayer();
			Scene newPlayerScene = new Scene(newPlayerPane, 300, 200);
			Stage newPlayerStage = new Stage();
			newPlayerStage.setScene(newPlayerScene);
			newPlayerStage.setTitle("New player");
			newPlayerStage.show();
		});

		// Close the main menu
		exitMainMenu.setOnAction(e -> {
			GameManager.mainMenu.hide();
		});

		choosePlayer.setOnAction(e -> {
			Pane choosePlayerPane = buildChoosePlayer();
			Scene choosePlayerScene = new Scene(choosePlayerPane, 300, 200);
			Stage choosePlayerStage = new Stage();
			choosePlayerStage.setScene(choosePlayerScene);
			choosePlayerStage.setTitle("Choose player");
			choosePlayerStage.show();
		});

		return root;
	}

	/**
	 * Builds the new player GUI.
	 *
	 * @return the pane
	 */
	private Pane buildNewPlayer() {
		BorderPane root = new BorderPane();
		VBox sidebar = new VBox();
		sidebar.setSpacing(10);
		sidebar.setPadding(new Insets(10, 10, 10, 10));

		Label playerID = new Label("ID of player: ");
		TextField playerIDInput = new TextField();
		Label playerName = new Label("Name of player: ");
		TextField playerNameInput = new TextField();
		Button newPlayerButton = new Button("CREATE PLAYER");
		root.setLeft(sidebar);
		sidebar.getChildren().addAll(playerID, playerIDInput, playerName, playerNameInput, newPlayerButton);

		newPlayerButton.setOnAction(e -> {
			try {
				boolean created = (FileManager
						.writeToPlayerFile(new Player(playerIDInput.getText() + "," + playerNameInput.getText())));
				Alert alert = new Alert(AlertType.CONFIRMATION);
				if (created) {
					alert.setTitle("SUCCESS");
					alert.setHeaderText("Player created");
					alert.setContentText("Huzzah! You have created a Player.");
					alert.showAndWait().ifPresent(rs -> {
						if (rs == ButtonType.OK) {
							System.out.println("Pressed OK.");
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
	 * Builds the delete player GUI.
	 *
	 * @return the pane
	 */
	private Pane buildDeletePlayer() {
		BorderPane root = new BorderPane();
		VBox sidebar = new VBox();
		sidebar.setSpacing(10);
		sidebar.setPadding(new Insets(10, 10, 10, 10));

		Label playerID = new Label("ID of player: ");
		TextField playerIDInput = new TextField();
		Button deletePlayerButton = new Button("Delete player");

		root.setLeft(sidebar);
		sidebar.getChildren().addAll(playerID, playerIDInput, deletePlayerButton);

		deletePlayerButton.setOnAction(e -> {
			try {
				boolean deleted = (FileManager
						.deleteFromPlayerFile(new Player(Integer.valueOf(playerIDInput.getText()), "ass", 0)));
				Alert alert = new Alert(AlertType.CONFIRMATION);
				if (deleted) {
					alert.setTitle("SUCCESS");
					alert.setHeaderText("Player deleted");
					alert.setContentText("Huzzah! You have created a Player.");
					alert.showAndWait().ifPresent(rs -> {
						if (rs == ButtonType.OK) {
							System.out.println("Pressed OK.");
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

	/**
	 * Builds the choose level GUI.
	 *
	 * @return the pane
	 */
	private Pane buildChooseLevel() {
		// BorderPane root = new BorderPane();

		return null;
	}

	/**
	 * Builds the choose player GUI.
	 *
	 * @return the pane
	 */
	private Pane buildChoosePlayer() {
		BorderPane root = new BorderPane();
		VBox sidebar = new VBox();
		sidebar.setSpacing(10);
		sidebar.setPadding(new Insets(10, 10, 10, 10));

		Label playerID = new Label("ID of player: ");
		TextField playerIDInput = new TextField();
		Button choosePlayerButton = new Button("Choose player");

		root.setLeft(sidebar);
		sidebar.getChildren().addAll(playerID, playerIDInput, choosePlayerButton);
		choosePlayerButton.setOnAction(e -> {
			if (playerIDInput.getText() != null) {
				try {
					this.currentPlayer = FileManager.getPlayer(Integer.valueOf(playerIDInput.getText()));
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("INFORMATION");
					alert.setHeaderText("Player found");
					alert.setContentText("Player ID:" + Integer.valueOf(playerIDInput.getText()));
					alert.showAndWait().ifPresent(rs -> {
						if (rs == ButtonType.OK) {
							System.out.println("Pressed OK.");
						}
					});
				} catch (Exception exception) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("INFORMATION");
					alert.setHeaderText("No player found");
					alert.setContentText("Please try again");
					alert.showAndWait().ifPresent(rs -> {
						if (rs == ButtonType.OK) {
							System.out.println("Pressed OK.");
						}
					});
				}
			}
		});
		return root;
	}
}