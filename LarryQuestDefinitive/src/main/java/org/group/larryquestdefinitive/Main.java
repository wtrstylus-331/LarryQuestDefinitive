/*
Program name: Main.java
Date: Jan 24, 2025
Purpose: Start and run the main application
 */

package org.group.larryquestdefinitive;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.group.larryquestdefinitive.control.MoveController;
import org.group.larryquestdefinitive.entities.Player;
import org.group.larryquestdefinitive.scenes.Maze;
import org.group.larryquestdefinitive.scenes.TitlePage;

public class Main extends Application {
    // public static stage reference
    public static Stage stage;

    // player idle
    public static Image playerVis = new Image(Main.class.getResourceAsStream("sprites/plr_idle.png"));

    // debug mode for colliders
    public static boolean debugMode = false;

    // title page
    public static TitlePage title;

    // Initialize the stage
    @Override
    public void start(Stage stage) {
        // create player and controller
        Player player = new Player(playerVis, 200, 200, "player");
        title = new TitlePage(new AnchorPane(), Constants.WIDTH, Constants.HEIGHT, player, stage);
        MoveController mc = new MoveController(player);
        mc.setupInput(title.parent);

        // set stage
        stage.setTitle("Larry Quest: Definitive Edition");
        stage.setScene(title);
        stage.setResizable(false);
        stage.show();
        Main.stage = stage;
    }

    // Run the program
    public static void main(String[] args) {
        for (int difficulty = 1; difficulty <= 5; difficulty++) {
            System.out.println("Maze Difficulty: " + difficulty);
            Maze generator = new Maze(difficulty);
            generator.printMaze();
            System.out.println();
        }
        launch();
    }
} // end of Main class