package managers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import board.Grid;
import board.Level;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
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
    //private Leaderboard leaderboard;
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

    // BAD IMPLEMENTATION
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
    	int gridHeight = level.getHeight();
    	int gridWidth = level.getWidth();
    	
    	
    	
    	GridPane grid = new GridPane();
    	int i = 0;
    	while (i < 10) {
    		grid.getColumnConstraints().add(new ColumnConstraints(10)); 
    		i++;
    	}
    	
    	FileInputStream imageStream = null;
		try {
			imageStream = new FileInputStream("C:\\Users\\Sam\\Pictures\\FUTURO\\res\\Sprites\\tileW.png");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Image image = new Image(imageStream);
		grid.add(new ImageView(image), 0, 0);
    	
        Stage stage = new Stage();
        Button showLeaderboard = new Button("SHOW LEADERBOARD");
        Button exitGame = new Button("EXIT GAME");
           
        Label messageOfDay = new Label(getMessageOfTheDay());
        VBox sidebar = new VBox();
        sidebar.setSpacing(10);
        sidebar.setPadding(new Insets(10, 10, 10, 10));
        sidebar.getChildren().addAll(showLeaderboard, exitGame, messageOfDay, grid);
        
        HBox gridBox = new HBox();
        gridBox.setSpacing(10);
        gridBox.getChildren().addAll(grid);
      
        exitGame.setOnAction(e -> {
            stage.hide();
            GameManager.mainMenu.show();
        });	
        
        Scene scene = new Scene(sidebar, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setTitle("Futuro");
        
        stage.setScene(scene);
        stage.show();	
    }
}

















/*
loadLevel.setOnAction(e -> {
    FileManager readLevel = new FileManager();            
    Level level = new Level(10, 10, 10, null, 0, 10, 0, 0, null, null);
    Game game = new Game(level);
});
*/
/*
showLeaderboard.setOnAction(e -> {
FileManager readLeaderboard = new FileManager();            
Leaderboard leaderboard = new Level(10, 10, 10, null, 0, 10, 0, 0, null, null);
Game game = new Game(level);
});
*/


//// Create a scene from the GUI
//GridPane gridpane = new GridPane();
//for (int i = 0; i < 40; i++) {
//  ColumnConstraints column = new ColumnConstraints(40);
//  gridpane.getColumnConstraints().add(column);
//  RowConstraints row = new RowConstraints(40);
//  gridpane.getRowConstraints().add(row);
//}
