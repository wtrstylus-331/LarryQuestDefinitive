package org.group.larryquestdefinitive;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.group.larryquestdefinitive.entities.Direction;
import org.group.larryquestdefinitive.scenes.TitlePage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    // public static stage reference
    public static Stage stage;

    // player sprite timelines
    public static Timeline plrUp = initPlayerTimeline(Direction.UP);
    public static Timeline plrDown = initPlayerTimeline(Direction.DOWN);
    public static Timeline plrRight = initPlayerTimeline(Direction.RIGHT);
    public static Timeline plrLeft = initPlayerTimeline(Direction.LEFT);

    public static ImageView playerIdle = new ImageView(
            new Image(
                    Main.class.getResourceAsStream("sprites/plr_idle.png")
            )
    );

    // Initialize the stage
    @Override
    public void start(Stage stage) {
        TitlePage title = new TitlePage(new AnchorPane(), Constants.WIDTH, Constants.HEIGHT);

        debugTimelines();

        stage.setTitle("Larry Quest: Definitive Edition");
        stage.setScene(title);
        stage.setResizable(false);
        stage.show();
        Main.stage = stage;
    }

    // Run the program
    public static void main(String[] args) {
        launch();
    }

    // Returns timeline for specified direction for player
    private static Timeline initPlayerTimeline(Direction dir) {
        String dirSuffix = switch (dir) {
            case DOWN -> "down";
            case RIGHT -> "right";
            case LEFT -> "left";
            case UP -> "up";
        };

        List<Image> images = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            images.add(new Image(Main.class.getResourceAsStream(
                    "sprites/plr_" + dirSuffix + "/walk_" + dirSuffix + i + ".png")));
        }

        Timeline timeline = new Timeline();
        for (int i = 0; i < images.size(); i++) {
            int index = i;
            timeline.getKeyFrames().add(new KeyFrame(
                    Duration.seconds(0.1 * i),
                    event -> {
                        playerIdle.setImage(images.get(index));
                    }
            ));
        }

        timeline.setCycleCount(Timeline.INDEFINITE);
        return timeline;
    }

    // Print if timelines initialized properly or not
    private void debugTimelines() {
        if (plrUp != null) {
            System.out.println("Player up timeline initialized");
        }

        if (plrDown != null) {
            System.out.println("Player down timeline initialized");
        }

        if (plrLeft != null) {
            System.out.println("Player left timeline initialized");
        }

        if (plrRight != null) {
            System.out.println("Player right timeline initialized");
        }
    }
} // end of Main class