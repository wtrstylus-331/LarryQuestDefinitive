package org.group.larryquestdefinitive.scenes;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import org.group.larryquestdefinitive.Main;
import org.group.larryquestdefinitive.entities.Direction;

public class TestingScene extends Scene {
    private AnchorPane root;

    public TestingScene(Parent parent, double w, double h) {
        super(parent, w, h);

        this.root = (AnchorPane) parent;
        this.root.getChildren().add(Main.mainPlayer);
        this.addListener();
    }

    public void addListener() {
        this.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            Direction direction = null;

            switch (event.getCode()) {
                case W -> direction = Direction.UP;
                case A -> direction = Direction.LEFT;
                case S -> direction = Direction.DOWN;
                case D -> direction = Direction.RIGHT;
            }

            if (direction != null) {
                Main.mainPlayer.playAnimation(direction);
            }
        });

        this.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            // Stop all animations when any key is released
            Main.mainPlayer.stopTimelines();
            Main.mainPlayer.setImage(Main.playerIdle);
        });
    }
}
