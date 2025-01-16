package org.group.larryquestdefinitive;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.group.larryquestdefinitive.entities.PlayerPane;
import org.group.larryquestdefinitive.scenes.TitlePage;

public class Main extends Application {
    // public static stage reference
    public static Stage stage;

    // player sprite timelines and sprite resources
    public static Timeline plrUp = initTimelines("up");
    public static Timeline plrDown = initTimelines("down");
    public static Timeline plrRight = initTimelines("right");
    public static Timeline plrLeft = initTimelines("left");

    public static ImageView playerIdle = new ImageView(
            new Image(
                    Main.class.getResourceAsStream("sprites/plr_idle.png")
            )
    );

    // Static reference to player
    public static PlayerPane mainPlayer;

    public static boolean debugMode = true;

    // Initialize the stage
    @Override
    public void start(Stage stage) {
        TitlePage title = new TitlePage(new AnchorPane(), Constants.WIDTH, Constants.HEIGHT);
        debugTimelines();

        mainPlayer = new PlayerPane(playerIdle, 450, 300, plrUp, plrDown, plrLeft, plrRight);

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

    // Method to return initialized timeline for specific Player direction
    private static Timeline initTimelines(String type) {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        for (int i = 1; i <= 9; i++) {
            int index = i;
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.1 * (i - 1)), e -> {
                mainPlayer.setImage(new ImageView(new Image(
                        Main.class.getResourceAsStream("sprites/plr_" + type + "/walk_" + type + index + ".png"))
                ));
            }));
        }

        return timeline;
    } // end of initTimelines method

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
    } // end of debugTimelines method
} // end of Main class