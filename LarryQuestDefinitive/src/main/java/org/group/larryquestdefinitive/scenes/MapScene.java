package org.group.larryquestdefinitive.scenes;

import javafx.animation.AnimationTimer;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.group.larryquestdefinitive.Constants;
import org.group.larryquestdefinitive.Main;
import org.group.larryquestdefinitive.entities.Direction;

import java.util.ArrayList;

public class MapScene extends Scene implements Constants {
    private AnchorPane parent;
    private Pane holderPane;
    private ImageView map;
    private AnimationTimer timer;
    private ArrayList<Rectangle> collisionBoxes;

    private boolean movingRight = false;
    private boolean movingLeft = false;
    private boolean movingUp = false;
    private boolean movingDown = false;

    public MapScene(Parent root, double w, double h) {
        super(root, w, h);
        super.setFill(Color.GRAY);
        this.parent = (AnchorPane) root;

        this.holderPane = new Pane();
        this.collisionBoxes = new ArrayList<>();
        this.parent.getChildren().add(this.holderPane);
        this.parent.getChildren().add(Main.mainPlayer);

        this.addMapListener();
        this.addPlayerListener();
        this.draw();
    }

    public void setMap(ImageView currentMap, int w, int h) {
        this.map = currentMap;
        this.map.setFitWidth(w);
        this.map.setFitHeight(h);

        double centerX = (WIDTH - this.map.getFitWidth()) / 2;
        double centerY = (HEIGHT - this.map.getFitHeight()) / 2;

        this.map.setX(centerX);
        this.map.setY(centerY);

        this.holderPane.getChildren().add(this.map);
    }

    public void addObject(ImageView object, int x, int y, int w, int h, int collisionW, int collisionH, int xOffset, int yOffset) {
        object.setFitWidth(w);
        object.setFitHeight(h);
        object.setX(x);
        object.setY(y);

        Rectangle collider = createCollider(collisionW, collisionH);
        collider.setX(x + xOffset);
        collider.setY(y + yOffset);

        this.holderPane.getChildren().add(object);
        this.holderPane.getChildren().add(collider);
    }

    public void addCollider(int x, int y, int collisionW, int collisionH) {
        Rectangle collider = createCollider(collisionW, collisionH);
        collider.setX(x);
        collider.setY(y);

        this.holderPane.getChildren().add(collider);
    }

    private Rectangle createCollider(int w, int h) {
        Rectangle col = new Rectangle();
        col.setWidth(w);
        col.setHeight(h);

        if (Main.debugMode) {
            col.setFill(Color.rgb(200,0,200, 0.7));
        } else {
            col.setFill(Color.rgb(0,0,0,0));
        }

        return col;
    }

    private void addMapListener() {
        this.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case W -> this.movingUp = true;
                case A -> this.movingLeft = true;
                case S -> this.movingDown = true;
                case D -> this.movingRight = true;
            }
        });

        this.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            switch (event.getCode()) {
                case W -> this.movingUp = false;
                case A -> this.movingLeft = false;
                case S -> this.movingDown = false;
                case D -> this.movingRight = false;
            }
        });
    }

    private void addPlayerListener() {
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

    public void draw() {
        this.timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (movingRight) {
                    holderPane.setLayoutX(holderPane.getLayoutX() - 2);
                }
                if (movingLeft) {
                    holderPane.setLayoutX(holderPane.getLayoutX() + 2);
                }
                if (movingUp) {
                    holderPane.setLayoutY(holderPane.getLayoutY() + 2);
                }
                if (movingDown) {
                    holderPane.setLayoutY(holderPane.getLayoutY() - 2);
                }
            }
        };

        this.timer.start();
    }
}
