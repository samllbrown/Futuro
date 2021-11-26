package managers;

import java.io.IOException;

import board.Level;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
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
//      Pane root = buildGUI();
        Stage stage = new Stage();
        Button loadLevel = new Button("LOAD LEVEL");
        Button showLeaderboard = new Button("SHOW LEADERBOARD");
        Button exitGame = new Button("EXIT GAME");
        
        ////ridpane.addRow(5, button);
        VBox sidebar = new VBox();
        sidebar.setSpacing(10);
        sidebar.setPadding(new Insets(10, 10, 10, 10));
        sidebar.getChildren().addAll(loadLevel, showLeaderboard, exitGame);
        
      
        exitGame.setOnAction(e -> {
            stage.hide();
            GameManager.mainMenu.show();
        });	
        
        // Scene scene = new Scene(gridpane, WINDOW_WIDTH, WINDOW_HEIGHT);  
        Scene scene = new Scene(sidebar, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setTitle("Futuro");
        
        // Display the scene on the stage
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
