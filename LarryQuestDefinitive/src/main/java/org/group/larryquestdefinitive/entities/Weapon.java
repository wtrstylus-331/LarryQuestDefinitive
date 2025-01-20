package org.group.larryquestdefinitive.entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Weapon extends Pane {
    private ImageView sprite;

    public Weapon(Image image) {
        super();

        this.sprite = new ImageView(image);
    }
}
