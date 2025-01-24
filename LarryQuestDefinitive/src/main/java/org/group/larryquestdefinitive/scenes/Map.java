package org.group.larryquestdefinitive.scenes;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.group.larryquestdefinitive.Constants;
import org.group.larryquestdefinitive.Game;
import org.group.larryquestdefinitive.Main;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.group.larryquestdefinitive.control.GameLoop;

public class Map extends Pane {

    private Game game;
    private GameLoop loop;

    public Map() {
        // Set background color or image
        //this.setStyle("-fx-background-color: #2a2a2a;");
        ImageView bg = new ImageView(new Image(Main.class.getResourceAsStream("scenes/map_bg.jpg")));
        bg.setFitWidth(600);
        bg.setFitHeight(550);
        this.getChildren().add(bg);

        // Add levels to the map
        createLevel(50, 150, "level1_thumbnail.png", "Level 1", () -> goToScene("Level3Scene2.png"));
        createLevel(240, 30, "level2_thumbnail.png", "Level 2", () -> goToScene("Level5.png"));
        createLevel(370, 250, "level3_thumbnail.png", "Level 3", () -> goToScene("Level6VersionA.png"));
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setLoop(GameLoop loop) {
        this.loop = loop;
    }

    private void createLevel(double x, double y, String imagePath, String levelName, Runnable action) {
        // Load the level image
        Image image = new Image(Main.class.getResourceAsStream("scenes/" + imagePath), 100, 100, true, true);
        ImageView imageView = new ImageView(image);
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);

        // Configure the highlight effect
        DropShadow highlightEffect = new DropShadow();
        highlightEffect.setColor(Color.YELLOW);
        highlightEffect.setSpread(0.5);
        highlightEffect.setRadius(10);
        highlightEffect.setOffsetX(0);
        highlightEffect.setOffsetY(0);

        // Add hover effect to the image
        imageView.setOnMouseEntered(event -> applyHighlight(imageView, highlightEffect));
        imageView.setOnMouseExited(event -> removeHighlight(imageView));
        imageView.setOnMouseClicked(event -> action.run());

        /*Add a label for the level
        Text label = new Text(levelName);
        label.setFont(Font.font("Arial", 16));
        label.setFill(Color.WHITE);
        label.setLayoutX(x + 10); // Adjust based on image size
        label.setLayoutY(y + 120);

         */

        // Add to the pane
        this.getChildren().addAll(imageView/*, label*/);
    }

    private void applyHighlight(ImageView imageView, DropShadow highlightEffect) {
        imageView.setEffect(highlightEffect);
    }

    private void removeHighlight(ImageView imageView) {
        imageView.setEffect(null);
    }

    private void goToScene(String path) {
        GameScene gameScene = new GameScene();

        ImageView mapImage = new ImageView(new Image(Main.class.getResourceAsStream("scenes/" + path)));
        gameScene.setMap(mapImage, 900, 500);

        gameScene.addCollider(20,50,50,500);
        gameScene.addCollider(925,50,50,500);
        gameScene.addCollider(35,20,900,50);
        gameScene.addCollider(35,530,900,50);

        Maze maze = new Maze(10);
        maze.addToGameScene(gameScene, 70);

        /*
        switch (path) {
            case "Level3Scene2.png", "Level5.png", "Level6VersionA.png" -> {
                gameScene.addCollider(20,50,50,500);
                gameScene.addCollider(925,50,50,500);
                gameScene.addCollider(35,20,900,50);
                gameScene.addCollider(35,530,900,50);
            }
        }
         */

        game.setScene(gameScene);
        game.getScene().setRoot(gameScene);

        Main.stage.setScene(game.getScene());
        centerStage(Main.stage);
        loop.start();
    }

    private static void centerStage(Stage stage) {
        // Get the screen bounds
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        // Calculate the center position
        double centerX = (screenBounds.getWidth() - stage.getWidth()) / 2;
        double centerY = (screenBounds.getHeight() - stage.getHeight()) / 2;

        // Set the stage position
        stage.setX(centerX);
        stage.setY(centerY);
    }
}
