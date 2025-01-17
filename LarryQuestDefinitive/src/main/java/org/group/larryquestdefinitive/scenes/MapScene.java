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
import org.group.larryquestdefinitive.control.Collider;
import org.group.larryquestdefinitive.entities.Direction;

import java.util.ArrayList;

public class MapScene extends Scene implements Constants {
    private AnchorPane parent;
    private Pane holderPane;
    private ImageView map;
    private AnimationTimer timer;

    private boolean movingRight = false;
    private boolean movingLeft = false;
    private boolean movingUp = false;
    private boolean movingDown = false;

    public boolean playerColliding = false;

    public MapScene(Parent root, double w, double h) {
        super(root, w, h);
        super.setFill(Color.GRAY);
        this.parent = (AnchorPane) root;

        this.holderPane = new Pane();
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
        Rectangle collider = createCollider(collisionW,collisionH);
        collider.setX(x);
        collider.setY(y);

        this.holderPane.getChildren().add(collider);
    }

    private Rectangle createCollider(int w, int h) {
        Collider col = new Collider(w,h);

        if (Main.debugMode) {
            col.fill(Color.rgb(200,0,200, 0.7));
        } else {
            col.fill(Color.rgb(0,0,0,0));
        }

        //col.collide();
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
            if (!playerColliding) {
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
                boolean canMoveRight = true;
                boolean canMoveLeft = true;
                boolean canMoveUp = true;
                boolean canMoveDown = true;

                // Check for collisions in each direction
                for (var node : holderPane.getChildren()) {
                    if (node instanceof Collider collider && collider.isColliding(Main.mainPlayer)) {
                        if (movingRight && holderPane.getLayoutX() - 2 < collider.getTranslateX()) {
                            canMoveRight = false;
                        }
                        if (movingLeft && holderPane.getLayoutX() + 2 > collider.getTranslateX()) {
                            canMoveLeft = false;
                        }
                        if (movingUp && holderPane.getLayoutY() + 2 > collider.getTranslateY()) {
                            canMoveUp = false;
                        }
                        if (movingDown && holderPane.getLayoutY() - 2 < collider.getTranslateY()) {
                            canMoveDown = false;
                        }
                    }
                }

                // Move the map only if there's no collision in the current direction
                if (movingRight && canMoveRight) {
                    holderPane.setLayoutX(holderPane.getLayoutX() - 2);
                }
                if (movingLeft && canMoveLeft) {
                    holderPane.setLayoutX(holderPane.getLayoutX() + 2);
                }
                if (movingUp && canMoveUp) {
                    holderPane.setLayoutY(holderPane.getLayoutY() + 2);
                }
                if (movingDown && canMoveDown) {
                    holderPane.setLayoutY(holderPane.getLayoutY() - 2);
                }
            }
        };

        this.timer.start();
    }
}
