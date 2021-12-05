package managers;

import javafx.application.Application;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.AudioPlayer;

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
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    /**
     * Create the GUI for main menu.
     *
     * @return the pane
     */
    private Pane buildMainMenu() {
        // Create top-level panel that will hold all GUI
    	BorderPane root = new BorderPane();
        Player player;
        // Create canvas
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        root.setCenter(canvas);
        // Create the main buttons for navigating the main menu
        Button chooseLevel = new Button("(START GAME (testing)");   
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
        //sidebar.getChildren().addAll(createPlayer, choosePlayer, newPlayer, deletePlayer,load ,exitMainMenu);
        sidebar.getChildren().addAll(chooseLevel, choosePlayer, newPlayer, deletePlayer, exitMainMenu);

        chooseLevel.setOnAction(e -> {
            Level level = null;
            try {
            	Pane chooseLevelPane = buildChooseLevel();
                Scene chooseLevelScene = new Scene(chooseLevelPane, 300, 200);
                Stage chooseLevelStage = new Stage();
                chooseLevelStage.setScene(chooseLevelScene);
                chooseLevelStage.setTitle("Choose Level");
                GameManager.chooseLevelMenu = chooseLevelStage;
                GameManager.chooseLevelMenu.show();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            mainMenu.close();
        });
        
        // Delete a player profile 
        deletePlayer.setOnAction(e -> {
        	Pane deletePlayerPane = buildDeletePlayer();
            Scene deletePlayerScene = new Scene(deletePlayerPane, 300, 200);
            Stage deletePlayerStage = new Stage();
            deletePlayerStage.setScene(deletePlayerScene);
            deletePlayerStage.setTitle("Delete player");
            deletePlayerStage.show();
        });
        
        // Create a new profile to play the game as
        newPlayer.setOnAction(e -> {
        	Pane newPlayerPane = buildNewPlayer();
            Scene newPlayerScene = new Scene(newPlayerPane, 300, 200);
            Stage newPlayerStage = new Stage();
            this.newPlayerMenu = newPlayerStage;
            this.newPlayerMenu.setScene(newPlayerScene);
            this.newPlayerMenu.setTitle("New player");
            this.newPlayerMenu.show();
        });


        // Load game
        load.setOnAction(e -> {
            if(this.currentPlayer == null){ // check if a player is selected, opens choose player if none are selected
                Pane choosePlayerPane = buildChoosePlayer();
                Scene choosePlayerScene = new Scene(choosePlayerPane, 300, 200);
                Stage choosePlayerStage = new Stage();
                this.choosePlayerMenu = choosePlayerStage;
                this.choosePlayerMenu.setScene(choosePlayerScene);
                this.choosePlayerMenu.setTitle("Choose player");
                this.choosePlayerMenu.show();
            } else { // If a player is selected open load menu
               //
            }
        });


        // Close the main menu
        exitMainMenu.setOnAction(e -> {
            GameManager.mainMenu.hide();
        });
        
        // Select a player profile to play the game as
        choosePlayer.setOnAction(e -> {
        	Pane choosePlayerPane = buildChoosePlayer();
            Scene choosePlayerScene = new Scene(choosePlayerPane, 300, 200);
            Stage choosePlayerStage = new Stage();
            this.choosePlayerMenu = choosePlayerStage;
            this.choosePlayerMenu.setScene(choosePlayerScene);
            this.choosePlayerMenu.setTitle("Choose player");
            this.choosePlayerMenu.show();
        });
        
        return root;
    }
    
    /**
     * Builds the new player UI.
     *
     * @return the pane
     */
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
    	        	alert.setHeaderText("Player deleted");
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
