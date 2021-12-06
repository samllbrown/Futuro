package managers;

<<<<<<< Updated upstream
import java.io.IOException;
=======
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
>>>>>>> Stashed changes

import board.Level;
<<<<<<< Updated upstream
import javafx.geometry.Insets;
=======
import board.Tile;
import gameObject.*;
import inventory.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
>>>>>>> Stashed changes
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
<<<<<<< Updated upstream
=======
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
>>>>>>> Stashed changes
import javafx.stage.Stage;
import services.MessageOfTheDay;

public class Game {
<<<<<<< Updated upstream
=======

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
	private static final int WINDOW_WIDTH = 1000;

	/** The Constant WINDOW_HEIGHT of the UI. */
	private static final int WINDOW_HEIGHT = 825;

	/** The Constant CANVAS_WIDTH of the canvas */
	private static final int CANVAS_WIDTH = 700;

	/** The Constant CANVAS_HEIGHT of the canvas */
	private static final int CANVAS_HEIGHT = 650;

	/** The canvas. */
	private Canvas canvas;
>>>>>>> Stashed changes
	
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

<<<<<<< Updated upstream
=======
	/**
	 * Builds the GUI of the game.
	 *
	 * @return the pane
	 */
	private Pane buildGUI() {
		BorderPane root = new BorderPane();
		canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
		root.setCenter(canvas);
		root.setStyle(" -fx-background-image:" + "url("+"file:res/sprites/TileT.png"+ ")" + ";"
				+ "-fx-background-size: 50 50;"
				+ "-fx-background-position: center 116");
		HBox topbar = new HBox();
		topbar.setSpacing(10);
		topbar.setPadding(new Insets(10, 10, 10, 10));
		root.setTop(topbar);

		VBox sidebar = new VBox();
		root.setRight(sidebar);
		Button exitGameButton = new Button("Exit Game");
		exitGameButton.setStyle(GameManager.BUTTON_STYLE);
		Button startTickTimelineButton = new Button("Play");
		startTickTimelineButton.setStyle(GameManager.BUTTON_STYLE);
		Button stopTickTimelineButton = new Button("Pause");
		stopTickTimelineButton.setStyle(GameManager.BUTTON_STYLE);

		Label messageOfDayLabel = new Label(this.messageOfTheDay);
		messageOfDayLabel.setStyle("-fx-text-fill: White;"
				+ "-fx-font-family: Impact;"
				+ "-fx-font-size: 13");
		messageOfDayLabel.setAlignment(Pos.TOP_LEFT);
		Button score = this.level.getButton();

		exitGameButton.setOnMouseEntered(e ->{
			exitGameButton.setStyle(GameManager.HOVERED_BUTTON_STYLE);
		});
		exitGameButton.setOnMouseExited(e ->{
			exitGameButton.setStyle(GameManager.BUTTON_STYLE);
		});
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
			this.gameStage.hide();
			GameManager.mainMenu.show();
		});

		topbar.getChildren().addAll(startTickTimelineButton, stopTickTimelineButton, exitGameButton, messageOfDayLabel, score);

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

			if (db.getString() == "DEATH_MECH") {

				Mech newMech = new DeathMech(xCoord, yCoord);
				this.level.addMech(newMech);
			} else {
				this.level.addItem(i);
			}
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
	 * Make content. This populates the grid with mechs and tiles.
	 *
	 * @return the parent
	 * @throws Exception
	 */
	private Parent makeContent() throws Exception {
		Pane root = new Pane();
		root.setPrefSize(this.CURRENT_WIDTH * TILE_SIZE, this.CURRENT_HEIGHT * TILE_SIZE);
		root.getChildren().addAll(tileGroup, mechGroup);
		for (Mech m : this.level.getMechs()) {
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
>>>>>>> Stashed changes

	public void showGame() {
		Pane root = buildGUI();
	    Stage stage = new Stage();
        // Create a scene from the GUI
        GridPane gridpane = new GridPane();
        for (int i = 0; i < 40; i++) {
            ColumnConstraints column = new ColumnConstraints(40);
            gridpane.getColumnConstraints().add(column);
            RowConstraints row = new RowConstraints(40);
            gridpane.getRowConstraints().add(row);
        }
        
        Button button = new Button("Hello");
        gridpane.addRow(5, button);

        
        
        
        
        Scene scene = new Scene(gridpane, WINDOW_WIDTH, WINDOW_HEIGHT);        
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
