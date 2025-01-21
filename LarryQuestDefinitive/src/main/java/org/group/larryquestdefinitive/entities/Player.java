package org.group.larryquestdefinitive.entities;

import javafx.scene.image.*;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.group.larryquestdefinitive.Constants;

public class Player extends Entity {

    public static int currHealth;
    public int maxHealth;

    public Player(Image sprite, double x, double y, String animType) {
        super(sprite, x, y, animType);
        moveSpeed = 10;
        this.maxHealth = 10;  // Set max health
        this.currHealth = 10; // Set initial current health
        this.layoutX = Constants.WIDTH / 2;
        this.layoutY = Constants.HEIGHT / 2;

        // Optional: create a rectangle to visually represent player (red box for now)
        Rectangle rect = new Rectangle(0, 0, sprite.getWidth(), sprite.getHeight());
        rect.setFill(Color.RED);
        // super.getChildren().add(0, rect); // If you want to add it visually
    }

    // Update method or other functionality could go here if needed
}
