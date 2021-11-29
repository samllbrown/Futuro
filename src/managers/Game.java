package managers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import board.Grid;
import board.Level;
import gameObject.Mech;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
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
    // probably needs to be bigger than 50
    public static final int TILE_SIZE = 50;
    public int CURRENT_WIDTH;
    public int CURRENT_HEIGHT;
    private Group tileGroup = new Group();
    private Group mechGroup = new Group();

<<<<<<< Updated upstream
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 400;
=======
>>>>>>> Stashed changes

    // The dimensions of the canvas
    private static final int CANVAS_WIDTH = 400;
    private static final int CANVAS_HEIGHT = 400;

    // The size to draw the shapes
    private static final int SHAPE_SIZE = 30;

    private Canvas canvas;

    private Level level;
    private Player currentPlayer;
    // private Leaderboard leaderboard;
    private String messageOfTheDay;
    private Boolean isPaused;

    public Game(Level level) {
        this.level = level;
        this.CURRENT_WIDTH = level.getGrid().getWidth();
        this.CURRENT_HEIGHT = level.getGrid().getHeight();
    }

    public Game(String levelFile) throws Exception {
        this.level = FileManager.readLevel(levelFile);
        this.CURRENT_WIDTH = level.getGrid().getWidth();
        this.CURRENT_HEIGHT = level.getGrid().getHeight();
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

<<<<<<< Updated upstream
=======

    private void moveMechs() {
        for(Mech m : this.level.getMechs()) {
            m.move(1, 0);
            if(m.getGridX() > this.level.getGrid().getWidth()) {
                m.move(-2, 0);
            }
        }
    }



    private Pane buildGUI() {
        BorderPane root = new BorderPane();
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        root.setCenter(canvas);
        HBox toolbar = new HBox();
        toolbar.setSpacing(10);
        toolbar.setPadding(new Insets(10, 10, 10, 10));
        root.setTop(toolbar);

        Button mechMoveBtn = new Button("Move mechs");
        Button addItemBtn = new Button("Add item");
        toolbar.getChildren().addAll(mechMoveBtn, addItemBtn);

        mechMoveBtn.setOnAction(e -> {
            moveMechs();
            drawGame();
        });
        
        addItemBtn.setOnAction(e -> {
            drawGame();
        });


        return root;
    }

	public void drawGame() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.GRAY);
        gc.fillRect(0,0,canvas.getWidth(), canvas.getHeight());
        for(int i = 0; i < this.CURRENT_WIDTH; i++) {
            for(int j = 0; j < this.CURRENT_HEIGHT; j++) {
                gc.drawImage(this.level.getGrid().getTileAt(i, j).getImage(), i*TILE_SIZE, j*TILE_SIZE);
            }
        }
        for(Mech m : this.level.getMechs()) {
            gc.drawImage(m.getImage(), m.getGridX() * TILE_SIZE, m.getGridY() * TILE_SIZE);
        }
      //  for(Item i : this.level.getItems()) {
       // 	gc.drawImage(i.getImage(), i.getGridX() * TILE_SIZE, i.getGridY() * TILE_SIZE);
       // }
    }

>>>>>>> Stashed changes
    private Parent makeContent() throws Exception {
        Pane root = new Pane();
        root.setPrefSize(this.CURRENT_WIDTH * TILE_SIZE, this.CURRENT_HEIGHT * TILE_SIZE);
        root.getChildren().addAll(tileGroup, mechGroup);
        for(Mech m : this.level.getMechs()) {
            this.level.getGrid().getTileAt(m.getGridX(), m.getGridY()).addMech(m);
            mechGroup.setVisible(this.level.getGrid().getTileAt(m.getGridX(), m.getGridY()).isVisibleTile());
            mechGroup.getChildren().add(m);
        }
        for(int i = 0; i < this.CURRENT_WIDTH; i++) {
            for(int j = 0; j < this.CURRENT_HEIGHT; j++) {
                tileGroup.getChildren().add(this.level.getGrid().getTileAt(i, j));
            }
        }
        return root;
    }

//    @Override
//    public void start(Stage stage) throws Exception {
//        Scene scene = new Scene(makeContent());
//        stage.setTitle("Futuro Testing");
//        stage.setScene(scene);
//        stage.show();
//    }

    public void showGame() throws Exception {

//        int gridHeight = level.getHeight();
//        int gridWidth = level.getWidth();
//
//        GridPane grid = new GridPane();
//        int i = 0;
//        while (i < 10) {
//            grid.getColumnConstraints().add(new ColumnConstraints(10));
//            i++;
//        }
//
//        FileInputStream imageStream = null;
//        try {
//            // no Sam
//            imageStream = new FileInputStream("C:\\Users\\Sam\\Pictures\\FUTURO\\res\\Sprites\\tileW.png");
//        } catch (FileNotFoundException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        }
//        Image image = new Image(imageStream);
//        grid.add(new ImageView(image), 0, 0);

        Stage stage = new Stage();
        Scene scene = new Scene(makeContent());
        stage.setTitle("Futuro Testing");
        stage.setScene(scene);
        stage.show();
        //Label playerName = new Label();
//        Button showLeaderboard = new Button("SHOW LEADERBOARD");
//        Button exitGame = new Button("EXIT GAME");
//
//        Label messageOfDay = new Label(getMessageOfTheDay());
        //VBox sidebar = new VBox();
        //sidebar.setSpacing(10);
        //sidebar.setPadding(new Insets(10, 10, 10, 10));
        //sidebar.getChildren().addAll(showLeaderboard, exitGame, messageOfDay, grid);
//
//        HBox gridBox = new HBox();
//        gridBox.setSpacing(10);
        //gridBox.getChildren().addAll(grid);

//        exitGame.setOnAction(e -> {
//            stage.hide();
//            GameManager.mainMenu.show();
//        });

//        Scene scene = new Scene(sidebar, WINDOW_WIDTH, WINDOW_HEIGHT);
//        stage.setTitle("Futuro");
//        stage.setScene(scene);
//        stage.show();
        //start(stage);
    }

}

/*
 * loadLevel.setOnAction(e -> { FileManager readLevel = new FileManager(); Level
 * level = new Level(10, 10, 10, null, 0, 10, 0, 0, null, null); Game game = new
 * Game(level); });
 */
/*
 * showLeaderboard.setOnAction(e -> { FileManager readLeaderboard = new
 * FileManager(); Leaderboard leaderboard = new Level(10, 10, 10, null, 0, 10,
 * 0, 0, null, null); Game game = new Game(level); });
 */

//// Create a scene from the GUI
//GridPane gridpane = new GridPane();
//for (int i = 0; i < 40; i++) {
//  ColumnConstraints column = new ColumnConstraints(40);
//  gridpane.getColumnConstraints().add(column);
//  RowConstraints row = new RowConstraints(40);
//  gridpane.getRowConstraints().add(row);
//}
