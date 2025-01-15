package org.group.larryquestdefinitive.scenes;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.group.larryquestdefinitive.Main;
import org.group.larryquestdefinitive.entities.Direction;
import org.group.larryquestdefinitive.entities.PlayerPane;

public class TestingScene extends Scene {
    private AnchorPane root;
    private PlayerPane testPlayer;
    private Pane playerPane;

    public TestingScene(Parent parent, double w, double h) {
        super(parent, w, h);

        this.playerPane = new Pane();
        this.testPlayer = new PlayerPane(Main.playerIdle, 400, 250, Main.plrUp, Main.plrLeft, Main.plrDown, Main.plrRight);

        Text testing = new Text("placeholder text");
        testing.setFont(Font.font("arial", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        testing.setX(100);
        testing.setY(100);

        this.playerPane.getChildren().add(testPlayer);

        this.root = (AnchorPane) parent;
        this.root.getChildren().add(testing);
        this.root.getChildren().add(playerPane);
        this.addListener();
    }

    public void addListener() {
        this.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case W:
                    //this.map.setY(this.map.getY() + 1);
                    testPlayer.playTimeline(Direction.UP);
                case A:
                    //this.map.setX(this.map.getX() - 1);
                    testPlayer.playTimeline(Direction.LEFT);
                case S:
                    //this.map.setY(this.map.getY() - 1);
                    testPlayer.playTimeline(Direction.DOWN);
                case D:
                    //this.map.setX(this.map.getX() + 1);
                    testPlayer.playTimeline(Direction.RIGHT);
            }
        });

        this.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            switch (event.getCode()) {
                case W, A, S, D -> testPlayer.stopTimelines();
            }
        });
    }
}
