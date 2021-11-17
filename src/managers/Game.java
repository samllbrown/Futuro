package managers;

import java.io.IOException;

import board.Level;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.MessageOfTheDay;

public class Game {
	
	private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 400;

    // The dimensions of the canvas
    private static final int CANVAS_WIDTH = 400;
    private static final int CANVAS_HEIGHT = 400;

    // The size to draw the shapes
    private static final int SHAPE_SIZE = 30;
    
    private Canvas canvas;
	    
	private Level level;
	private Player currentPlayer;
	private String messageOfTheDay;
	private Boolean isPaused;
	
	
	public Game(Level level) {	
		setLevel(level);
		showGame();
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	
	//BAD IMPLEMENTATION
	public String getMessageOfTheDay() {
		String message;
		try {
			message = MessageOfTheDay.getMessageOfTheDay();
		} catch (IOException e) {
			message = "Error" + e;
		}
		setMessageOfTheDay(message);
		return messageOfTheDay;
	}

	public void setMessageOfTheDay(String messageOfTheDay) {
		this.messageOfTheDay = messageOfTheDay;
	}


	public void showGame() {
		Pane root = buildGUI();
	    Stage stage = new Stage();
        // Create a scene from the GUI
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);        
        stage.setTitle("Futuro");
        
        // Display the scene on the stage
        stage.setScene(scene);
        stage.show();
		
	}
	
	 private Pane buildGUI() {
        // Create top-level panel that will hold all GUI
        BorderPane root = new BorderPane();
                
        // Create canvas
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        root.setCenter(canvas);
        
        Button startGame = new Button("THIS IS THE NEW GAME");
        
        VBox sidebar = new VBox();
        sidebar.setSpacing(10);
        sidebar.setPadding(new Insets(10, 10, 10, 10)); 
        root.setLeft(sidebar);  
        sidebar.getChildren().addAll(startGame);
        
        return root;
    }   
}
