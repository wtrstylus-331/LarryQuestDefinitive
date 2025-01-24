/*
Program name: Map.java
Date: Jan 24, 2025
Purpose: Scene to show buttons to select and load certain levels (scene)
 */

package org.group.larryquestdefinitive.scenes;

import javafx.geometry.Rectangle2D;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.group.larryquestdefinitive.Game;
import org.group.larryquestdefinitive.Main;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import org.group.larryquestdefinitive.control.GameLoop;
import org.group.larryquestdefinitive.entities.Enemy;
import org.group.larryquestdefinitive.entities.Player;

public class Map extends Pane {

    // private variables
    private Game game;
    private GameLoop loop;
    private Player player;

    // constructor
    public Map(Player player) {
        this.player = player;
        // Set background image
        ImageView bg = new ImageView(new Image(Main.class.getResourceAsStream("scenes/map_bg.jpg")));
        bg.setFitWidth(600);
        bg.setFitHeight(550);
        this.getChildren().add(bg);

        // Add levels to the map
        createLevel(50, 150, "level1_thumbnail.png", "Level 1", () -> goToScene("Level3Scene2.png"));
        createLevel(240, 30, "level2_thumbnail.png", "Level 2", () -> goToScene("Level5.png"));
        createLevel(370, 250, "level3_thumbnail.png", "Level 3", () -> goToScene("Level6VersionA.png"));
    }

    // method to set game variable
    public void setGame(Game game) {
        this.game = game;
    }

    // method to set loop variable
    public void setLoop(GameLoop loop) {
        this.loop = loop;
    }

    // method to create image buttons for level
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

        // Add to the pane
        this.getChildren().addAll(imageView/*, label*/);
    } // end of createLevel

    // method to apply UI highlight effect
    private void applyHighlight(ImageView imageView, DropShadow highlightEffect) {
        imageView.setEffect(highlightEffect);
    }

    // method to remove UI highlight effect
    private void removeHighlight(ImageView imageView) {
        imageView.setEffect(null);
    }

    // method to load game scene based on input
    private void goToScene(String path) {
        GameScene gameScene = new GameScene();

        // set background map
        ImageView mapImage = new ImageView(new Image(Main.class.getResourceAsStream("scenes/" + path)));
        gameScene.setMap(mapImage, 900, 500);

        // add colliders
        gameScene.addCollider(20,50,50,500);
        gameScene.addCollider(925,50,50,500);
        gameScene.addCollider(35,20,900,50);
        gameScene.addCollider(35,530,900,50);

        // add initial enemy
        gameScene.setGame(game);

        Enemy enemy1 = new Enemy(Main.playerVis, 400, 200, "player", player, 5);
        game.addEntity(enemy1);

        // set scene and start loop
        game.setScene(gameScene);
        game.getScene().setRoot(gameScene);

        Main.stage.setScene(game.getScene());
        centerStage(Main.stage);
        loop.start();
    } // end of goToScene method

    // method to center the stage upon scene loading
    public void centerStage(Stage stage) {
        // Get the screen bounds
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        // Calculate the center position
        double centerX = (screenBounds.getWidth() - stage.getWidth()) / 2;
        double centerY = (screenBounds.getHeight() - stage.getHeight()) / 2;

        // Set the stage position
        stage.setX(centerX);
        stage.setY(centerY);
    }
} // end of Map class
