package managers;

import javafx.application.Application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import board.Level;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GameManager extends Application {

    // The dimensions of the window
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 400;

    // The dimensions of the canvas
    private static final int CANVAS_WIDTH = 400;
    private static final int CANVAS_HEIGHT = 400;

    // The size to draw the shapes
    private static final int SHAPE_SIZE = 30;
    // test branch
    public static Stage mainMenu;
    public Player currentPlayer;
    // The canvas in the GUI. This needs to be a global variable
    // (in this setup) as we need to access it in different methods.
    // We could use FXML to place code in the controller instead.
    private Canvas canvas;

    public void start(Stage primaryStage) {
        // Build the GUI
        Pane root = buildMainMenu();

        // Create a scene from the GUI
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setTitle("Futuro");
        
        // Display the scene on the stage
        primaryStage.setScene(scene);
        mainMenu = primaryStage;
     //   String bip = getCurrentWorkingDirectory() + "\\src\\music\\ratmusic.mp3";
     //   System.out.println(bip);
     //   Media hit = new Media(new File(bip).toURI().toString());
     //   MediaPlayer mediaPlayer = new MediaPlayer(hit);
        //mediaPlayer.play();
        mainMenu.show();
    }
    
    private static String getCurrentWorkingDirectory() {
        String userDirectory = System.getProperty("user.dir");
        return userDirectory;
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    /**
     * Create the GUI.
     */
    private Pane buildMainMenu() {
        // Create top-level panel that will hold all GUI
    	BorderPane root = new BorderPane();
        Player player;
        // Create canvas
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        root.setCenter(canvas);
        // Create the main buttons for navigating the main menu
        Button createPlayer = new Button("(START GAME (testing)");   
        Button choosePlayer = new Button("CHOOSE PLAYER");        
        Button newPlayer = new Button("CREATE PLAYER");
        Button deletePlayer = new Button("DELETE PLAYER");
        Button load = new Button("LOAD");
        
        
        Button exitMainMenu = new Button("EXIT GAME");       
        
        // Create a sidebar with some nice padding and spacing
        VBox sidebar = new VBox();
        sidebar.setSpacing(10);
        sidebar.setPadding(new Insets(10, 10, 10, 10));
        
        // Add the elements on the canvas onto the sidebar
        root.setLeft(sidebar);
        sidebar.getChildren().addAll(createPlayer, choosePlayer, newPlayer, deletePlayer,load ,exitMainMenu);


        createPlayer.setOnAction(e -> {
        	//Level level = new Level(10, 10, 10, null, 0, 10, 0, 0, null, null);
            Level level = null;
            try {
                level = FileManager.readLevel("LEVEL_1.txt");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            Game game = new Game(level);
            mainMenu.close();
            try {
                game.showGame();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
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


        // Load game
        load.setOnAction(e -> {
            if(this.currentPlayer == null){ // check if a player is selected, opens choose player if none are selected
                Pane choosePlayerPane = buildChoosePlayer();
                Scene choosePlayerScene = new Scene(choosePlayerPane, 300, 200);
                Stage choosePlayerStage = new Stage();
                choosePlayerStage.setScene(choosePlayerScene);
                choosePlayerStage.setTitle("Choose player");
                choosePlayerStage.show();
            } else { // If a player is selected open load menu
                Pane loadPane = loadNewGame();
                Scene loadScene = new Scene(loadPane, 300, 200);
                Stage loadStage = new Stage();
                loadStage.setScene(loadScene);
                loadStage.setTitle("Load game");
                loadStage.show();
            }
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
    
    private Pane buildNewPlayer() {
    	BorderPane root = new BorderPane();
    	VBox sidebar = new VBox();
        sidebar.setSpacing(10);
        sidebar.setPadding(new Insets(10, 10, 10, 10));
        
        Label playerID = new Label("ID of player: ");
        TextField playerIDInput = new TextField ();
        Label playerName = new Label("Name of player: ");
        TextField playerNameInput = new TextField ();
        Button newPlayerButton = new Button("CREATE PLAYER");
        root.setLeft(sidebar);
        sidebar.getChildren().addAll(playerID, playerIDInput, playerName, playerNameInput, newPlayerButton);
        
        FileManager playerCreator = new FileManager();
        newPlayerButton.setOnAction(e -> { 
    	try {
        	boolean created = (playerCreator.writeToPlayerFile(new Player(playerIDInput.getText() + "," + playerNameInput.getText())));
        	Alert alert  = new Alert(AlertType.CONFIRMATION);
        	if(created) {
	        	alert.setTitle("SUCCESS");
	        	alert.setHeaderText("Player created");
	        	alert.setContentText("Huzzah! You have created a Player.");
	            alert.showAndWait().ifPresent(rs -> {
	                if (rs == ButtonType.OK) {
	                    System.out.println("Pressed OK.");
	                }
	            });
        	}
        	else {
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
    
    private Pane buildDeletePlayer() {
    	BorderPane root = new BorderPane();
    	VBox sidebar = new VBox();
        sidebar.setSpacing(10);
        sidebar.setPadding(new Insets(10, 10, 10, 10));
        
        root.setLeft(sidebar);
        sidebar.getChildren().addAll();
    	return root;
    }

    // SetOnAction needs to load a file (open file manager? input level id like choose player does?)
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
        });

        return root;
    }
    
    private Pane buildChoosePlayer() {
    	BorderPane root = new BorderPane();
    	VBox sidebar = new VBox();
        sidebar.setSpacing(10);
        sidebar.setPadding(new Insets(10, 10, 10, 10));
        
        
        Label playerID = new Label("ID of player: ");
        TextField playerIDInput = new TextField ();
        Button choosePlayerButton = new Button("Choose player");
        
        
        root.setLeft(sidebar);
        sidebar.getChildren().addAll(playerID, playerIDInput, choosePlayerButton);
        choosePlayerButton.setOnAction(e -> {
        	if(playerIDInput.getText() != null) {
                try {
                    this.currentPlayer = FileManager.getPlayer(Integer.valueOf(playerIDInput.getText()));
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




/*
 * Button startGame = new Button("START NEW GAME");
 * 
 * 
 *         // Start the game
        startGame.setOnAction(e -> {
        	Level level = new Level(10, 10, 10, null, 0, 10, 0, 0, null, null);
            Game game = new Game(level);
            mainMenu.close();
        });
 * 
*/