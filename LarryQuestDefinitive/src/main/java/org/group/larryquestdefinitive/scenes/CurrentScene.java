package org.group.larryquestdefinitive.scenes;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class CurrentScene extends Scene {
    private AnchorPane parent;
    private Pane holderPane;
    private ImageView map;

    public CurrentScene(Parent root, ImageView currentMap) {
        super(root);

        this.holderPane = new Pane();

        this.parent = (AnchorPane) root;
        this.setMap(currentMap);
        this.addListener();
        this.parent.getChildren().add(this.holderPane);
    }

    public void setMap(ImageView currentMap) {
        this.map = currentMap;

        this.map.setX(currentMap.getFitWidth() / 2);
        this.map.setY(currentMap.getFitHeight() / 2);

        this.holderPane.getChildren().add(this.map);
    }

    public void addListener() {
        this.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case W:
                    this.map.setY(this.map.getY() + 1);

                    break;
                case A:
                    this.map.setX(this.map.getX() - 1);

                    break;
                case S:
                    this.map.setY(this.map.getY() - 1);

                    break;
                case D:
                    this.map.setX(this.map.getX() + 1);

                    break;
            }
        });
    }
}
