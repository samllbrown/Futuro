package managers;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import board.Grid;
import board.Level;
import board.Tile;
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
import javafx.scene.control.Label;
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
import services.AudioPlayer;
import services.MessageOfTheDay;
import javafx.util.Duration;

/**
 * Game.java
 * 
 * @author Debbie, Illia, Sam B
 * @version 3
 * last mod date: 4/12/2021
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

    /** The Constant WINDOW_WIDTH of the UI. */
    private static final int WINDOW_WIDTH = 1050;

    /** The Constant WINDOW_HEIGHT of the UI. */
    private static final int WINDOW_HEIGHT = 900;

    /** The Constant CANVAS_WIDTH of the canvas */
    private static final int CANVAS_WIDTH = 700;

    /** The Constant CANVAS_HEIGHT of the canvas */
    private static final int CANVAS_HEIGHT = 650;

    /** The canvas. */
    private Canvas canvas;

    private Stage gameStage;

    /** The level. */
    private Level level;

    /** The message of the day. */
    private String messageOfTheDay;

    private Player currentPlayer;

    /**
     * Instantiates a new game.
     *
     * @param level the level
     */
    public Game(Player player, Level level) {
	this.level = level;
	this.currentPlayer = player;
	System.out.println(this.CURRENT_WIDTH);
	System.out.println(this.CURRENT_HEIGHT);
	this.CURRENT_WIDTH = level.getGrid().getWidth();
	this.CURRENT_HEIGHT = level.getGrid().getHeight();
	System.out.println(this.CURRENT_WIDTH);
	System.out.println(this.CURRENT_HEIGHT);
	this.messageOfTheDay = getMessageOfTheDay();
    }

    /**
     * Instantiates a new empty game.
     */
    public Game() {
	this.level = null;
	this.currentPlayer = null;
	this.CURRENT_WIDTH = 0;
	this.CURRENT_HEIGHT = 0;
	this.messageOfTheDay = getMessageOfTheDay();
    }

    /**
     * Instantiates a new game.
     *
     * @param levelFile the level file
     * @throws Exception the exception
     */
    public Game(String levelFile, Player player) throws Exception {
	this.level = FileManager.readLevel(levelFile);
	this.currentPlayer = player;
	this.CURRENT_WIDTH = level.getGrid().getWidth();
	this.CURRENT_HEIGHT = level.getGrid().getHeight();
    }

    /**
     * Gets the current player.
     *
     * @return the current player
     */
    public Player getCurrentPlayer() {
	return this.currentPlayer;
    }

    /**
     * Sets the current player.
     *
     * @param currentPlayer the new current player
     */
    public void setCurrentPlayer(Player currentPlayer) {
	this.currentPlayer = currentPlayer;
    }

    /**
     * Tick.
     */
    private void tick() {
	try {
	    this.level.update();
	    drawGame();
	} catch (Exception e) {
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
	this.CURRENT_WIDTH = level.getGrid().getWidth();
	this.CURRENT_HEIGHT = level.getGrid().getHeight();
	this.messageOfTheDay = getMessageOfTheDay();
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
	HBox motdbar = new HBox();
	motdbar.setSpacing(10);
	motdbar.setPadding(new Insets(10));
	root.setBottom(motdbar);
	root.setStyle(" -fx-background-image:" + "url(" + "file:res/sprites/TileT.png" + ")" + ";"
		+ "-fx-background-size: 50 50;" + "-fx-background-position: center 105");
	AudioPlayer.playInGameMusic();
	VBox sidebar = new VBox();

	root.setRight(sidebar);

	Button startTickTimelineButton = new Button("Play");
	startTickTimelineButton.setStyle(GameManager.BUTTON_STYLE);
	Button stopTickTimelineButton = new Button("Pause");
	stopTickTimelineButton.setStyle(GameManager.BUTTON_STYLE);
	Button saveLevelButton = new Button("Save Level");
	saveLevelButton.setStyle(GameManager.BUTTON_STYLE);
	Button exitGameButton = new Button("Exit Game");
	exitGameButton.setStyle(GameManager.BUTTON_STYLE);

	Label messageOfDayLabel = new Label(this.messageOfTheDay);
	messageOfDayLabel.setStyle("-fx-text-fill: White;" + "-fx-font-family: Impact;" + "-fx-font-size: 20");

	stopTickTimelineButton.setDisable(true);

	startTickTimelineButton.setOnAction(e -> {

	    startTickTimelineButton.setDisable(true);
	    tickTimeline = new Timeline(new KeyFrame(Duration.millis(500), event -> tick()));
	    tickTimeline.setCycleCount(Animation.INDEFINITE);
	    this.tickTimeline.play();
	    stopTickTimelineButton.setDisable(false);
	});

	saveLevelButton.setOnAction(e -> {
	    saveLevel(this.level, this.currentPlayer);
	});

	stopTickTimelineButton.setOnAction(e -> {

	    stopTickTimelineButton.setDisable(true);
	    this.tickTimeline.pause();
	    startTickTimelineButton.setDisable(false);
	});

	exitGameButton.setOnAction(e -> {
	    this.gameStage.hide();

	    AudioPlayer.stopAllMusic();

	    this.tickTimeline.pause();

	    GameManager.mainMenu.show();

	});

	motdbar.getChildren().addAll(messageOfDayLabel);
	topbar.getChildren().addAll(startTickTimelineButton, stopTickTimelineButton, saveLevelButton, exitGameButton);

	for (var i : this.level.getInventory().getItems().entrySet()) {
	    InventoryItem iconItem = i.getValue();
	    iconItem.setImage(iconItem.getSprite());
	    sidebar.getChildren().addAll(iconItem, i.getValue().getLabel());

	    iconItem.setOnDragDetected(new EventHandler<MouseEvent>() {
		public void handle(MouseEvent event) {
		    if (iconItem.getRemainingUses() != 0) {
			Dragboard db = iconItem.startDragAndDrop(TransferMode.ANY);
			System.out.println(iconItem.getRemainingUses());

			ClipboardContent itemName = new ClipboardContent();
			itemName.putString(iconItem.itemName);
			db.setContent(itemName);

			event.consume();
		    }
		}
	    });
	}

	canvas.setOnDragDropped(new EventHandler<DragEvent>() {
	    public void handle(DragEvent event) {


		Dragboard db = event.getDragboard();

		canvasDragDroppedOccured(event);

		level.getInventory().getLabel(db.getString())
			.setText(Integer.toString(level.getInventory().getItemUses(db.getString())));

		event.consume();
	    }
	});

	canvas.setOnDragOver(new EventHandler<DragEvent>() {
	    public void handle(DragEvent event) {

		event.acceptTransferModes(TransferMode.ANY);

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

	if (db.hasString()) {
	    Item i = InventoryItem.getItemForName(db.getString(), xCoord, yCoord);
	    level.getInventory().useItem(db.getString());

		Mech newMech = new DeathMech(xCoord, yCoord);
		this.level.addMech(newMech);
	    } else {
		this.level.addItem(i);
	    }

	    GraphicsContext gc = canvas.getGraphicsContext2D();

	    gc.drawImage(i.getImage(), i.getGridX() * TILE_SIZE, i.getGridY() * TILE_SIZE);

	} else {
	    System.out.println("This error should not exist (Game.java)");
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
		HBox motdbar = new HBox();
		motdbar.setSpacing(10);
		motdbar.setPadding(new Insets(10));
		root.setBottom(motdbar);
		root.setStyle(" -fx-background-image:" + "url("+"file:res/sprites/TileT.png"+ ")" + ";"
				+ "-fx-background-size: 50 50;"
				+ "-fx-background-position: center 105");
		AudioPlayer.playInGameMusic();
		VBox sidebar = new VBox();

		root.setRight(sidebar);


		Button startTickTimelineButton = new Button("Play");
		startTickTimelineButton.setStyle(GameManager.BUTTON_STYLE);
		Button stopTickTimelineButton = new Button("Pause");
		stopTickTimelineButton.setStyle(GameManager.BUTTON_STYLE);
		Button saveLevelButton = new Button("Save Level");
		saveLevelButton.setStyle(GameManager.BUTTON_STYLE);
		Button exitGameButton = new Button("Exit Game");
		exitGameButton.setStyle(GameManager.BUTTON_STYLE);

		Label messageOfDayLabel = new Label(this.messageOfTheDay);
		messageOfDayLabel.setStyle("-fx-text-fill: White;"
				+ "-fx-font-family: Impact;"
				+ "-fx-font-size: 20");
		Button score = this.level.getButton();
		
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

		saveLevelButton.setOnAction(e ->{
			saveLevel(this.level, this.currentPlayer);
		});

		stopTickTimelineButton.setOnAction(e -> {
			// Stop the tick timeline and enable/disable buttons as appropriate.
			stopTickTimelineButton.setDisable(true);
			this.tickTimeline.pause();
			startTickTimelineButton.setDisable(false);
		});

		exitGameButton.setOnAction(e -> {
		    this.gameStage.hide();
		    AudioPlayer.stopAllMusic();
		    if (this.tickTimeline != null) {
		        this.tickTimeline.pause(); 
		    }
		    GameManager.mainMenu.show();

		});

		motdbar.getChildren().addAll(messageOfDayLabel);
		topbar.getChildren().addAll(startTickTimelineButton, stopTickTimelineButton,saveLevelButton, exitGameButton, score);

		// This code setup what happens when the dragging starts on the image.
		for (var i : this.level.getInventory().getItems().entrySet()) {
			InventoryItem iconItem = i.getValue();
			iconItem.setImage(iconItem.getSprite());
			sidebar.getChildren().addAll(iconItem, i.getValue().getLabel());
			
			iconItem.setOnDragDetected(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event) {
					if (iconItem.getRemainingUses() != 0) {
						Dragboard db = iconItem.startDragAndDrop(TransferMode.ANY);
						System.out.println(iconItem.getRemainingUses());

						ClipboardContent itemName = new ClipboardContent();
						itemName.putString(iconItem.itemName);
						db.setContent(itemName);

						event.consume();
					}
				}
			});
		}

		canvas.setOnDragDropped(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				// We call this method which is where the bulk of the behaviour takes place.
				Dragboard db = event.getDragboard();

				canvasDragDroppedOccured(event);

				level.getInventory().getLabel(db.getString())
						.setText(Integer.toString(level.getInventory().getItemUses(db.getString())));

				// Consume the event. This means we mark it as dealt with.
				event.consume();
			}
		});

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
		return root;
    }

    /**
     * Draw game (Ran every tick).
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
	for (Mech m : this.level.getMechs()) {
	    if (this.level.getGrid().getTileAt(m.getGridX(), m.getGridY()).isVisibleTile()) {
		gc.drawImage(m.getImage(), m.getGridX() * TILE_SIZE, m.getGridY() * TILE_SIZE);
	    }
	}

	for (Item i : this.level.getItems()) {
	    if (i.getXRange() > 0) {
		int q = 0;
		while (q < i.getXRange()) {
		    if (this.level.getGrid().getTileAt((i.getGridX() + q), i.getGridY())
			    .getTileType() != TileType.WALL) {
			gc.drawImage(i.getImage(), (i.getGridX() + q) * TILE_SIZE, i.getGridY() * TILE_SIZE);
		    } else {
			q = 1000;
		    }
		    q++;
		}
		q = 0;
		while (q < i.getXRange()) {
		    if (this.level.getGrid().getTileAt((i.getGridX() - q), i.getGridY())
			    .getTileType() != TileType.WALL) {
			gc.drawImage(i.getImage(), (i.getGridX() - q) * TILE_SIZE, i.getGridY() * TILE_SIZE);
		    } else {
			q = 1000;
		    }
		    q++;
		}
		q = 0;
		while (q < i.getYRange()) {
		    if (this.level.getGrid().getTileAt((i.getGridX()), i.getGridY() + q)
			    .getTileType() != TileType.WALL) {
			gc.drawImage(i.getImage(), i.getGridX() * TILE_SIZE, (i.getGridY() + q) * TILE_SIZE);
		    } else {
			q = 1000;
		    }
		    q++;
		}
		q = 0;
		while (q < i.getYRange()) {
		    if (this.level.getGrid().getTileAt((i.getGridX()), i.getGridY() - q)
			    .getTileType() != TileType.WALL) {
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
	stage.setResizable(false);
	gameStage = stage;
	gameStage.show();
    }

    /**
     * Save level.
     *
     * @param level the level
     * @param player the player
     */
    public void saveLevel(Level level, Player player) {
	FileManager.writeLevel(level, player);
    }
}