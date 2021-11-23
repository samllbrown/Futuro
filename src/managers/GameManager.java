package managers;


import javafx.application.Application;

import java.util.ArrayList;
import java.util.Random;

import board.Level;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
    
    // The canvas in the GUI. This needs to be a global variable
    // (in this setup) as we need to access it in different methods.
    // We could use FXML to place code in the controller instead.
    private Canvas canvas;
    
    public void start(Stage primaryStage) {
        // Build the GUI 
        Pane root = buildGUI();
        
        // Create a scene from the GUI
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);        
        primaryStage.setTitle("Futuro");
        
        // Display the scene on the stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {


        launch(args);
    }
    
    /**
     * Create the GUI.
     * @return The panel that contains the created GUI.
     */
    private Pane buildGUI() {
        // Create top-level panel that will hold all GUI
        BorderPane root = new BorderPane();
                
        // Create canvas
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        root.setCenter(canvas);
        
        Button startGame = new Button("START NEW GAME");
        Button selectPlayer = new Button("SELECT PLAYER");
        Button createPlayer = new Button("CREATE PLAYER");
        Button deletePlayer = new Button("DELETE PLAYER");
        
        // Create a sidebar with some nice padding and spacing
        VBox sidebar = new VBox();
        sidebar.setSpacing(10);
        sidebar.setPadding(new Insets(10, 10, 10, 10)); 
        root.setLeft(sidebar);  
        sidebar.getChildren().addAll(startGame,selectPlayer,createPlayer,deletePlayer);
        
        startGame.setOnAction(e -> {
			FileManager levelReader = new FileManager();
			//Level level = levelReader.readLevel("level1.txt");
			Level level = new Level(10, 10, 10, null, 0, 10, 0, 0, null, null);
			Game game = new Game(level);
		});

//      selectPlayer.setOnAction(e -> {
//          FileManager playerReader = new FileManager();
//          //Level level = levelReader.readLevel("Players.txt");
//          Player player = new Player(1,"Player1",2);
//          Game game = new Game(player);
//        });
       
//      createPlayer.setOnAction(e -> {
//            FileManager playerReader = new FileManager();
//            //Level level = levelReader.readLevel("Players.txt");
//            Player player = new Player(1,"Player1",2);
//            Game game = new Game(player);
//        });
        
//      deletePlayer.setOnAction(e -> {
//      FileManager playerReader = new FileManager();
//      //Level level = levelReader.readLevel("Players.txt");
//      Player player = new Player(1,"Player1",2);
//      Game game = new Game(player);
//  });
        
        return root;
    }   
}
