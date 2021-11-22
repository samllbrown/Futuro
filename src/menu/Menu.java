package menu;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

public class Menu extends Application {
    public void start(Stage primaryStage) {
        // Create a new pane to hold our GUI
        FlowPane root = new FlowPane();
        root.setAlignment(javafx.geometry.Pos.CENTER);

        // Create a new label
        Label intro = new Label("Hello, welcome to the game!\n");
        
        // Create a new button
        Button button = new Button("Play game!");
        
        root.getChildren().add(intro);
        root.getChildren().add(button);

        // Create a scene based on the pane.
        Scene scene = new Scene(root, 400, 400);

        // Show the scene
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}