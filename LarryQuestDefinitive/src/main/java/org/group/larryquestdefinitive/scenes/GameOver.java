package org.group.larryquestdefinitive.scenes;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import org.group.larryquestdefinitive.Main;

public class GameOver {
    private Stage stage;

    public GameOver(Stage stage) {
        this.stage = stage;
    }

    public void display() {
        // Create a VBox for organizing the elements vertically
        VBox layout = new VBox(20);
        layout.setStyle("-fx-background-color: rgba(0, 0, 0, 0.8); -fx-alignment: center; -fx-padding: 50px;");

        // Create a Text element for displaying the Game Over message
        Text gameOverText = new Text("Game Over!");
        gameOverText.setStyle("-fx-font-size: 40px; -fx-fill: white;");

        // Create a Button to restart the game
        Button restartButton = new Button("Restart");
        restartButton.setStyle("-fx-font-size: 20px; -fx-padding: 10px;");
        restartButton.setOnAction(e -> restartGame());

        // Create a Button to exit the game
        Button exitButton = new Button("Exit");
        exitButton.setStyle("-fx-font-size: 20px; -fx-padding: 10px;");
        exitButton.setOnAction(e -> exitGame());

        // Add the elements to the layout
        layout.getChildren().addAll(gameOverText, restartButton, exitButton);

        // Create a scene and set it on the stage
        Scene gameOverScene = new Scene(layout, 600, 400);
        stage.setScene(gameOverScene);
        stage.show();
    }

    // Method to restart the game
    private void restartGame() {
    }

    // Method to exit the game
    private void exitGame() {
        // Close the game window
        System.exit(0);
    }
}
