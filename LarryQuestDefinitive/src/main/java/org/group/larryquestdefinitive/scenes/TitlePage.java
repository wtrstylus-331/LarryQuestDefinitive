/*
Program name: TitlePage.java
Date: Jan 24, 2025
Purpose: Title page to show game name and be able to load map
 */

package org.group.larryquestdefinitive.scenes;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import org.group.larryquestdefinitive.Constants;
import org.group.larryquestdefinitive.Game;
import org.group.larryquestdefinitive.control.GameLoop;
import org.group.larryquestdefinitive.entities.Player;

public class TitlePage extends Scene implements Constants {
    // variables
    public AnchorPane parent;
    private Button button;
    private Text title;
    private Player player;
    private Stage stage;

    // constructor set to defaults
    public TitlePage(Parent root, double w, double h, Player player, Stage stage) {
        super(root, w, h);
        this.parent = (AnchorPane) root;
        this.player = player;
        this.stage = stage;

        this.addElements();
        this.addListener();
    }

    // Method to add UI elements to the scene
    private void addElements() {
        this.title = new Text("Larry Quest: Definitive Edition");
        this.title.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 60));
        this.title.setStroke(Color.BLACK);
        this.title.setStrokeWidth(2);
        this.title.setFill(Color.WHITE);
        this.title.setX(70);
        this.title.setY(100);

        this.button = new Button("Play");
        this.button.setFont(Font.font("arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));
        this.button.setMinSize(150,75);
        this.button.setBackground(Background.fill(Color.WHITE));
        this.button.setStyle("-fx-border-color: #000000; -fx-border-width: 2px;");
        this.button.setLayoutX(425);
        this.button.setLayoutY(250);
        this.button.setFocusTraversable(false);

        this.parent.getChildren().add(this.title);
        this.parent.getChildren().add(this.button);
    } // end of addElements method

    // Method to add button event handlers to the buttons
    private void addListener() {
        this.button.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            this.button.setFont(Font.font("arial", FontWeight.NORMAL, FontPosture.REGULAR, 50));
            this.button.setMinSize(160,80);
            this.button.setBackground(Background.fill(Color.rgb(200,200,200)));
            this.button.setLayoutX(415);
            this.button.setLayoutY(240);
        });

        this.button.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            this.button.setFont(Font.font("arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));
            this.button.setMinSize(150,75);
            this.button.setBackground(Background.fill(Color.WHITE));
            this.button.setLayoutX(425);
            this.button.setLayoutY(250);
        });

        this.button.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            // set to map
            GameScene startScene = new GameScene();
            Scene playScene = new Scene(startScene, Constants.WIDTH, Constants.HEIGHT);

            Game game = new Game(player, playScene, stage);
            GameLoop loop = new GameLoop(game);
            Map map = new Map(player);
            map.centerStage(stage);
            map.setGame(game);
            map.setLoop(loop);
            Scene scene = new Scene(map, 600, 400);
            stage.setScene(scene);
        });
    } // end of addListener method
} // end of TitlePage class
