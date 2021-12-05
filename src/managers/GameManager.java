package managers;

import javafx.application.Application;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import java.util.Random;



import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import board.Level;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javax.swing.text.html.ImageView;

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
	
    private static final String BUTTON_STYLE = "fx-background-color: \n" +
            "        linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),\n" +
            "        #9d4024,\n" +
            "        #d86e3a,\n" +
            "        radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c); " +
            "-fx-color: black; " +
            "-fx-font-family: Impact; " +
            "-fx-font-size: 20;" +
            "-fx-font-weight: bold;";
    private static final String HOVERED_BUTTON_STYLE = "fx-background-color: \n" +
            "        linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),\n" +
            "        #9d4024,\n" +
            "        #d86e3a,\n" +
            "        radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c); " + 
            "-fx-color: black; " +
            "-fx-font-family: Impact; " +
            "-fx-font-size: 20;" +
            "-fx-font-weight: bold;" +
            "-fx-opacity: 0.5";
			
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

    /**
     * Start function, starts the first main menu
     *
     * @param primaryStage the primary stage
     */
    public void start(Stage primaryStage) {
        // Build the GUI
        Pane root = buildMainMenu();

        // Build the GUI 
        Pane root = buildGUI();
        
        // Create a scene from the GUI
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setTitle("Futuro");
        
        //audioPlayer.playMainMenu();
        
        // Display the scene on the stage
        primaryStage.setScene(scene);
        mainMenu = primaryStage;
        mainMenu.show();
    }

    /**
     * The main method.
     *
     * @param args the arguments
     * @throws Exception 
     */
    /*
    public static void main(String[] args) throws Exception {
        // Build the GUI
        Pane root = buildMainMenu();
        root.setStyle("-fx-background-color: transparent;");
        // Create a scene from the GUI
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

        scene.setFill(new ImagePattern(new Image("file:res/PIXEL_ART.jpg",  WINDOW_WIDTH, WINDOW_HEIGHT, false, false)));
        primaryStage.setTitle("Futuro");


        //audioPlayer.playMainMenu();
        
        // Display the scene on the stage
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        mainMenu = primaryStage;
        mainMenu.show();
    }
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
        Button chooseLevel = new Button("START GAME");
        Button choosePlayer = new Button("CHOOSE PLAYER");        
        Button newPlayer = new Button("CREATE PLAYER");
        Button deletePlayer = new Button("DELETE PLAYER");
        Button exitMainMenu = new Button("EXIT GAME");
        chooseLevel.setStyle(BUTTON_STYLE);
        choosePlayer.setStyle(BUTTON_STYLE);
        newPlayer.setStyle(BUTTON_STYLE);
        deletePlayer.setStyle(BUTTON_STYLE);
        exitMainMenu.setStyle(BUTTON_STYLE);

        // Create a sidebar with some nice padding and spacing
        VBox sidebar = new VBox();
        sidebar.setSpacing(10);
        sidebar.setAlignment(Pos.CENTER);
        sidebar.setPadding(new Insets(10, 10, 10, 10));
        
        // Add the elements on the canvas onto the sidebar
        root.setCenter(sidebar);
        sidebar.getChildren().addAll(chooseLevel, choosePlayer, newPlayer, deletePlayer, exitMainMenu);
        chooseLevel.setOnMouseEntered(e ->{
            chooseLevel.setStyle(HOVERED_BUTTON_STYLE);
        });
        chooseLevel.setOnMouseExited(e ->{
            chooseLevel.setStyle(BUTTON_STYLE);
        });
        chooseLevel.setOnAction(e -> {
            Level level = null;
            try {
            	Pane chooseLevelPane = buildChooseLevel();
                Scene chooseLevelScene = new Scene(chooseLevelPane, 300, 200);
                Stage chooseLevelStage = new Stage();
                chooseLevelStage.setScene(chooseLevelScene);
                chooseLevelStage.setTitle("Choose Level");
                chooseLevelStage.show();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            Game game = new Game(level);
            mainMenu.close();
            try {
            	AudioPlayer.stopAllMusic();
                game.showGame();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        deletePlayer.setOnMouseEntered(e ->{
            deletePlayer.setStyle(HOVERED_BUTTON_STYLE);
        });
        deletePlayer.setOnMouseExited(e ->{
            deletePlayer.setStyle(BUTTON_STYLE);
        });
        deletePlayer.setOnAction(e -> {
        	Pane deletePlayerPane = buildDeletePlayer();
            Scene deletePlayerScene = new Scene(deletePlayerPane, 300, 200);
            Stage deletePlayerStage = new Stage();
            deletePlayerStage.setScene(deletePlayerScene);
            deletePlayerStage.setTitle("New player");
            deletePlayerStage.show();
        });
        newPlayer.setOnMouseEntered(e ->{
            newPlayer.setStyle(HOVERED_BUTTON_STYLE);
        });
        newPlayer.setOnMouseExited(e ->{
            newPlayer.setStyle(BUTTON_STYLE);
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
        exitMainMenu.setOnMouseEntered(e ->{
            exitMainMenu.setStyle(HOVERED_BUTTON_STYLE);
        });
        exitMainMenu.setOnMouseExited(e ->{
            exitMainMenu.setStyle(BUTTON_STYLE);
        });
        // Close the main menu
        exitMainMenu.setOnAction(e -> {
            GameManager.mainMenu.hide();
        });
        choosePlayer.setOnMouseEntered(e ->{
            choosePlayer.setStyle(HOVERED_BUTTON_STYLE);
        });
        choosePlayer.setOnMouseExited(e ->{
            choosePlayer.setStyle(BUTTON_STYLE);
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
		
        root.setStyle("-fx-background-color: Gray");

        Label playerID = new Label("ID of player: ");
        TextField playerIDInput = new TextField ();
        Label playerName = new Label("Name of player: ");
        TextField playerNameInput = new TextField ();
        Button newPlayerButton = new Button("CREATE PLAYER");

        newPlayerButton.setStyle(BUTTON_STYLE);
        newPlayerButton.setStyle(BUTTON_STYLE);
        root.setLeft(sidebar);
        sidebar.getChildren().addAll(playerID, playerIDInput, playerName, playerNameInput, newPlayerButton);

        newPlayerButton.setOnMouseEntered(e ->{
            newPlayerButton.setStyle(HOVERED_BUTTON_STYLE);
        });
        newPlayerButton.setOnMouseExited(e ->{
            newPlayerButton.setStyle(BUTTON_STYLE);
        });

        newPlayerButton.setOnAction(e -> { 
    	try {
        	boolean created = (FileManager.writeToPlayerFile(new Player(playerIDInput.getText() + "," + playerNameInput.getText())));
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
   
    /**
     * Builds the delete player UI.
     *
     * @return the pane
     */
    private Pane buildDeletePlayer() {
    	BorderPane root = new BorderPane();
    	VBox sidebar = new VBox();
        sidebar.setSpacing(10);
        sidebar.setPadding(new Insets(10, 10, 10, 10));
        
        
        Label playerID = new Label("ID of player: ");
        TextField playerIDInput = new TextField ();
        Button deletePlayerButton = new Button("Delete player");
        
        root.setLeft(sidebar);
        sidebar.getChildren().addAll(playerID, playerIDInput, deletePlayerButton);


        deletePlayerButton.setOnAction(e -> {
        	try {
            	boolean deleted = (FileManager.deleteFromPlayerFile(new Player(Integer.valueOf(playerIDInput.getText()), "ass", 0)));
            	Alert alert  = new Alert(AlertType.CONFIRMATION);
            	if(deleted) {
            		alert.setTitle("SUCCESS");
    	        	alert.setContentText("Huzzah! You have deleted a Player.");
    	            alert.showAndWait().ifPresent(rs -> {
    	                if (rs == ButtonType.OK) {
    	                    System.out.println("Pressed OK.");
    	                    this.deletePlayerMenu.hide();
    	                }
    	            });
            	}
            	else {
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


    // SetOnAction needs to load a file (open file manager? input level id like choose player does?)
//     private Pane loadNewGame() {
//         BorderPane root = new BorderPane();
//         VBox sidebar = new VBox();
//         sidebar.setSpacing(10);
//         sidebar.setPadding(new Insets(10, 10, 10, 10));

//         FileChooser selectLoadFile = new FileChooser();
//         selectLoadFile.setTitle("Select game file");

//         Button loadButton = new Button("Select game file");

//         root.setLeft(sidebar);
//         sidebar.getChildren().add(loadButton);
//         loadButton.setOnAction(e -> {
//             File selectedFile = selectLoadFile.showOpenDialog(mainMenu);
//             String selectedFilePath = selectedFile.getAbsolutePath();
//             Level level = null;
//             try {
//                 level = FileManager.readLevel(selectedFilePath);
//             } catch (Exception exception) {
//                 exception.printStackTrace();
//             }
//             Game game = new Game(level);

//             mainMenu.close();
//             try {
//                 game.showGame();
//             } catch (Exception ex) {
//                 ex.printStackTrace();
//             }
//         });

//         return root;

//     }

	/**
     * Builds the choose level UI.
     *
     * @return the pane
     */
    private Pane buildChooseLevel() {
    	Button levelOne = new Button("Level One");
     	Button levelTwo = new Button("Level Two");
     	Button levelThree = new Button("Level Three");
     	Button levelFour = new Button("Level Four");
     	Button levelFive  = new Button("Level Five");
     	
    	BorderPane root = new BorderPane();
    	VBox sidebar = new VBox();
        sidebar.setSpacing(10);
        sidebar.setPadding(new Insets(10, 10, 10, 10));
        
        root.setLeft(sidebar);
        sidebar.getChildren().addAll(levelOne, levelTwo, levelThree, levelFour, levelFive);
        
        levelOne.setOnAction(e -> {
        Level level = null;
		try {
			level = FileManager.readLevel("res\\Levels\\LEVEL_1.txt"); 
        } catch (Exception exception) {
			exception.printStackTrace();
        }
        Game game = new Game(level);
        mainMenu.close();
        try {
        	AudioPlayer.stopAllMusic();
            game.showGame();
            chooseLevelMenu.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        	}
        });
        levelTwo.setOnAction(e -> {
            Level level = null;
            	try {
            		level = FileManager.readLevel("res\\Levels\\LEVEL_2.txt"); 
            } catch (Exception exception) {
                    exception.printStackTrace();
            }
            Game game = new Game(level);
            mainMenu.close();
            chooseLevelMenu.close();
            try {
            	AudioPlayer.stopAllMusic();
                game.showGame();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        levelThree.setOnAction(e -> {
            Level level = null;
			try {
				level = FileManager.readLevel("res\\Levels\\LEVEL_3.txt"); 		
			} catch (Exception exception) {
				exception.printStackTrace();
			}
            Game game = new Game(level);
            mainMenu.close();
            chooseLevelMenu.close();
            try {
            	AudioPlayer.stopAllMusic();
                game.showGame();
            } catch (Exception exception) {
                exception.printStackTrace();
        	}
        });
        levelFour.setOnAction(e -> {
            Level level = null;
            	try {
            		level = FileManager.readLevel("res\\Levels\\LEVEL_4.txt"); 
            } catch (Exception exception) {
                    exception.printStackTrace();
            }
            Game game = new Game(level);
            mainMenu.close();
            chooseLevelMenu.close();
            try {
            	AudioPlayer.stopAllMusic();
                game.showGame();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        levelFive.setOnAction(e -> {
            Level level = null;
            	try {
            		level = FileManager.readLevel("res\\Levels\\LEVEL_5.txt"); 
            } catch (Exception exception) {
                    exception.printStackTrace();
            }
            Game game = new Game(level);
            mainMenu.close();
            chooseLevelMenu.close();
            try {
            	AudioPlayer.stopAllMusic();
                game.showGame();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        return root; 
    }
        

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
        TextField playerIDInput = new TextField ();
        Button choosePlayerButton = new Button("Choose player");
        root.setCenter(sidebar);
        sidebar.getChildren().addAll(playerID, playerIDInput, choosePlayerButton);
        choosePlayerButton.setOnAction(e -> {
        	if(playerIDInput.getText() != null) {
                try {
                    this.currentPlayer = FileManager.getPlayer(Integer.valueOf(playerIDInput.getText()));
                    Alert alert = new Alert(AlertType.INFORMATION);
    				alert.setTitle("INFORMATION");
    				alert.setHeaderText("Player found");
    				alert.setContentText("Player ID:" + Integer.valueOf(playerIDInput.getText()));
    				alert.showAndWait().ifPresent(rs -> {
    				    if (rs == ButtonType.OK) {
    				        System.out.println("Pressed OK.");
    				        this.choosePlayerMenu.hide();
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
