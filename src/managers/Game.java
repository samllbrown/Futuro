package managers;

import java.io.IOException;
import java.util.ArrayList;

import board.Level;
import gameObject.*;
import inventory.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import services.MessageOfTheDay;
import javafx.util.Duration;

/**
 * The Class Game.
 */
public class Game {

    /** The tick timeline. */
    private Timeline tickTimeline;

    /** The Constant TILE_SIZE. */
    public static final int TILE_SIZE = 50;
    
    /** The current width. */
    public int CURRENT_WIDTH;
    
    /** The current height. */
    public int CURRENT_HEIGHT;
    
    /** The tile group. */
    private Group tileGroup = new Group();
    
    /** The mech group. */
    private Group mechGroup = new Group();
    
    /** The Constant WINDOW_WIDTH of the UI. */
    private static final int WINDOW_WIDTH = 750;
    
    /** The Constant WINDOW_HEIGHT of the UI. */
    private static final int WINDOW_HEIGHT = 725;
    
    /** The Constant CANVAS_WIDTH of the canvas */
    private static final int CANVAS_WIDTH = 700;
    
    /** The Constant CANVAS_HEIGHT of the canvas */
    private static final int CANVAS_HEIGHT = 650;
    
    /** The canvas. */
    private Canvas canvas;

    /** The level. */
    private Level level;
    
    /** The message of the day. */
    private String messageOfTheDay;
    
    /**
     * Instantiates a new game.
     *
     * @param level the level
     */
    public Game(Level level) {
        this.level = level;
        this.CURRENT_WIDTH = level.getGrid().getWidth();
        this.CURRENT_HEIGHT = level.getGrid().getHeight();
    }

    /**
     * Instantiates a new game.
     *
     * @param levelFile the level file
     * @throws Exception the exception
     */
    public Game(String levelFile) throws Exception {
        this.level = FileManager.readLevel(levelFile);
        this.CURRENT_WIDTH = level.getGrid().getWidth();
        this.CURRENT_HEIGHT = level.getGrid().getHeight();
    }

    //    private void updateMechs() throws Exception {
    //        int points;
    //        for(Mech m : this.level.getMechs()) {
    //            if(this.level.getGrid().getTileAt(m.getGridX(), m.getGridY()).getCurrentItem() != null) {
    //                this.level.getGrid().getTileAt(m.getGridX(), m.getGridY()).getCurrentItem().act(m);
    //            }
    //            if(m.getHealth() <= 0) {
    //                points = this.level.getCurrentScore() + (m.isPregnant() ? (SCORE_PER_KILL * (Mech.NUM_OF_BABIES_IF_BIRTHING + 1)) : SCORE_PER_KILL);
    //                this.level.setCurrentScore(points);
    //                // concurrent modification exception happening here probably.
    //                this.level.removeMech(m);
    //                System.err.println("A MECH HAS DIED");
    //            } else {
    //                for(Mech mechIShareMyTileWith : this.level.getGrid().getTileAt(m.getGridX(), m.getGridY()).getMechs()) {
    //                    if(!(mechIShareMyTileWith.isPregnant() || mechIShareMyTileWith.getType() == m.getType() || mechIShareMyTileWith.isSterile() || m.isBreeding() || mechIShareMyTileWith.isBreeding())) {
    //                        if (mechIShareMyTileWith.getType() == MechType.PRODUCTION) {
    //                            // need to add the isBreeding and other validation before doing this
    //                            for(int i = 0; i < 5; i++) {
    //                                this.level.addMech(m.birthMech());
    //                            }
    //                        }
    //                    }
    //                }
    //                // this is being accessed when it's being removed or something
    //                m.move(this.level.getGrid());
    //            }
    //        }
    //    }

    //    private void update() throws Exception {
    //        // this for loop should probs just go into an init method
    //        for(Item i : this.level.getItems()) {
    //            this.level.getGrid().getTileAt(i.getGridX(), i.getGridY()).setCurrentItem(i);
    //        }
    //        this.updateMechs();
    //    }

    //    private void updateScore(int currentScore) {
    //        /*
    //         * while game is running so every tick, get mechs health, if mechs health is 0
    //         * add 10 points to current score and set that value as the currentscore of the
    //         * level assuming all mechs start with full health...
    //         */
    //        // if this.level.getNumberofmechsleftingame <= this.level.getlosingmechs then
    //        // game finished so
    //        // don't do for loop i guess else...
    //        for (Mech m : this.level.getMechs()) {
    //            if (m.getHealth() == 0 && m.getType() == MechType.PRODUCTION && m.isPregnant()) { // assuming they have 5
    //                                                                                              // babies idk how we're
    //                                                                                              // checking that - David
    //                currentScore = currentScore + 10 * (m.getNumOfBabies() + 1); // score is 10 times number of babies plus
    //                                                                             // the female mech
    //            } else if (m.getHealth() == 0) {
    //                currentScore = currentScore + 10;
    //            }
    //        }
    //        this.level.setCurrentScore(currentScore);
    /**
     * Tick.
     */
    //    }
    private void tick() {


        try {
            this.level.update();
            drawGame();
            // updateMechs();
            // moveMechs();
            // updateScore(this.level.getCurrentScore());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Gets the level.
     *
     * @return the level
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Sets the level.
     *
     * @param level the new level
     */
    public void setLevel(Level level) {
        this.level = level;
    }

    /**
     * Gets the message of the day.
     *
     * @return the message of the day
     */
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

    /**
     * Sets the message of the day.
     *
     * @param messageOfTheDay the new message of the day
     */
    public void setMessageOfTheDay(String messageOfTheDay) {
        this.messageOfTheDay = messageOfTheDay;
    }

    /**
     * Move all mechs.
     *
     * @throws Exception 
     */
    private void moveMechs() throws Exception {
        for (Mech m: this.level.getMechs()) {
            m.move(this.level.getGrid());
        }
    }

    /**
     * Builds the GUI of the game.
     *
     * @return the pane
     */
    private Pane buildGUI() {
        BorderPane root = new BorderPane();
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        root.setCenter(canvas);
        HBox topbar = new HBox();
        topbar.setSpacing(10);
        topbar.setPadding(new Insets(10, 10, 10, 10));
        root.setTop(topbar);

        VBox sidebar = new VBox();
        root.setRight(sidebar);
        Button exitGameButton = new Button("Exit Game");

        Button startTickTimelineButton = new Button("Start Ticks");
        Button stopTickTimelineButton = new Button("Stop Ticks");

        // Stop button is disabled by default
        stopTickTimelineButton.setDisable(true);

        // Setup the behaviour of the buttons.
        startTickTimelineButton.setOnAction(e -> {
            // Start the tick timeline and enable/disable buttons as appropriate.
            startTickTimelineButton.setDisable(true);
            tickTimeline = new Timeline(new KeyFrame(Duration.millis(500), event -> tick()));
            tickTimeline.setCycleCount(Animation.INDEFINITE);
            this.tickTimeline.play();
            stopTickTimelineButton.setDisable(false);
        });

        stopTickTimelineButton.setOnAction(e -> {
            // Stop the tick timeline and enable/disable buttons as appropriate.
            stopTickTimelineButton.setDisable(true);
            this.tickTimeline.pause();
            startTickTimelineButton.setDisable(false);
        });

        exitGameButton.setOnAction(e -> {
            //Needs to be closed
            GameManager.mainMenu.show();
        });


        topbar.getChildren().addAll(startTickTimelineButton, stopTickTimelineButton, exitGameButton);

        // This code setup what happens when the dragging starts on the image.
        // You probably don't need to change this (unless you wish to do more advanced
        // things).
       for(var i : this.level.getInventory().getHashMap().entrySet()) {
    	   InventoryItem iconItem = i.getValue();
    	   iconItem.setImage(iconItem.getSprite());
    	   sidebar.getChildren().addAll(iconItem, new Button(Integer.toString(iconItem.getRemainingUses())));
    	   
    	   iconItem.setOnDragDetected(new EventHandler<MouseEvent>() {
	            public void handle(MouseEvent event) {
	                Dragboard db = iconItem.startDragAndDrop(TransferMode.ANY);
	
	
	                ClipboardContent content = new ClipboardContent();
	                content.putString(iconItem.itemName);
	                db.setContent(content);
	
	
	                event.consume();
	            }
	        });
       }
        canvas.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                // Mark the drag as acceptable if the source was the draggable image.
                // (for example, we don't want to allow the user to drag things or files into
                // our application)
                event.acceptTransferModes(TransferMode.ANY);
                // Consume the event. This means we mark it as dealt with.
                event.consume();
            }
        });

        canvas.setOnDragDropped(new EventHandler < DragEvent > () {
            public void handle(DragEvent event) {
                // We call this method which is where the bulk of the behaviour takes place.
                canvasDragDroppedOccured(event);
                // Consume the event. This means we mark it as dealt with.
                event.consume();
            }
        });

        return root;
    }

    /**
     * Canvas drag dropped occurred.
     *
     * @param event the event
     */
    public void canvasDragDroppedOccured(DragEvent event) {
        Dragboard db = event.getDragboard();

        double x = event.getX();
        double y = event.getY();
        int xCoord = (int) Math.round(x) / TILE_SIZE;
        int yCoord = (int) Math.round(y) / TILE_SIZE;
        
        if(db.hasString()) {
	        Item i = InventoryItem.getItemForName(db.getString(), xCoord, yCoord);
	        level.getInventory().useItem(db.getString());
	        
	        if(db.getString() == "DEATH_MECH") {
	        	
	        	Mech newMech = new DeathMech(xCoord, yCoord);
	        	this.level.addMech(newMech);
	        } else {  this.level.addItem(i);}
	        // Draw an icon at the dropped location.
	        GraphicsContext gc = canvas.getGraphicsContext2D();
	        // Draw the the image so the top-left corner is where we dropped.
	        gc.drawImage(i.getImage(), i.getGridX() * TILE_SIZE, i.getGridY() * TILE_SIZE);
	        // Draw the the image so the center is where we dropped.
	        // gc.drawImage(iconImage, x - iconImage.getWidth() / 2.0, y -
	        // iconImage.getHeight() / 2.0);
        } else {
        	System.out.println("This error should not exist (Game.java)");
        }
    }

    /**
     * Draw game.
     */
    public void drawGame() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.GRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int i = 0; i < this.CURRENT_WIDTH; i++) {
            for (int j = 0; j < this.CURRENT_HEIGHT; j++) {
                gc.drawImage(this.level.getGrid().getTileAt(i, j).getImage(), i * TILE_SIZE, j * TILE_SIZE);
            }
        }
        for (Mech m: this.level.getMechs()) {
            if (this.level.getGrid().getTileAt(m.getGridX(), m.getGridY()).isVisibleTile()) {
                gc.drawImage(m.getImage(), m.getGridX() * TILE_SIZE, m.getGridY() * TILE_SIZE);
            }
        }

        for (Item i: this.level.getItems()) {
            if (i.getXRange() > 0) {
                int q = 0;
                while (q < i.getXRange()) {
                    if (this.level.getGrid().getTileAt((i.getGridX() + q), i.getGridY())
                        .getTileType() == TileType.PATH) {

                        gc.drawImage(i.getImage(), (i.getGridX() + q) * TILE_SIZE, i.getGridY() * TILE_SIZE);
                    } else {
                        q = 1000;
                    }
                    q++;
                }
                q = 0;
                while (q < i.getXRange()) {
                    if (this.level.getGrid().getTileAt((i.getGridX() - q), i.getGridY())
                        .getTileType() == TileType.PATH) {
                        gc.drawImage(i.getImage(), (i.getGridX() - q) * TILE_SIZE, i.getGridY() * TILE_SIZE);
                    } else {
                        q = 1000;
                    }
                    q++;
                }
                q = 0;
                while (q < i.getYRange()) {
                    if (this.level.getGrid().getTileAt((i.getGridX()), i.getGridY() + q)
                        .getTileType() == TileType.PATH) {
                        gc.drawImage(i.getImage(), i.getGridX() * TILE_SIZE, (i.getGridY() + q) * TILE_SIZE);
                    } else {
                        q = 1000;
                    }
                    q++;
                }
                q = 0;
                while (q < i.getYRange()) {
                    if (this.level.getGrid().getTileAt((i.getGridX()), i.getGridY() - q)
                        .getTileType() == TileType.PATH) {
                        gc.drawImage(i.getImage(), i.getGridX() * TILE_SIZE, (i.getGridY() - q) * TILE_SIZE);
                    } else {
                        q = 1000;
                    }
                    q++;
                }
            } else {
                gc.drawImage(i.getImage(), i.getGridX() * TILE_SIZE, i.getGridY() * TILE_SIZE);
            }
        }
    }

    /**
     * Make content. This populates the grid with mechs and tiles. 
     *
     * @return the parent
     * @throws Exception 
     */
    private Parent makeContent() throws Exception {
        Pane root = new Pane();
        root.setPrefSize(this.CURRENT_WIDTH * TILE_SIZE, this.CURRENT_HEIGHT * TILE_SIZE);
        root.getChildren().addAll(tileGroup, mechGroup);
        for (Mech m: this.level.getMechs()) {
            this.level.getGrid().getTileAt(m.getGridX(), m.getGridY()).addMech(m);
            mechGroup.setVisible(this.level.getGrid().getTileAt(m.getGridX(), m.getGridY()).isVisibleTile());
            mechGroup.getChildren().add(m);
        }
        for (int i = 0; i < this.CURRENT_WIDTH; i++) {
            for (int j = 0; j < this.CURRENT_HEIGHT; j++) {
                tileGroup.getChildren().add(this.level.getGrid().getTileAt(i, j));
            }
        }
        return root;
    }
    
    /**
     * Show the UI of the game.
     *
     * @throws Exception 
     */
    public void showGame() throws Exception {

        Pane root = buildGUI();
        Stage stage = new Stage();
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

        drawGame();
        stage.setScene(scene);
        stage.show();
    }
}