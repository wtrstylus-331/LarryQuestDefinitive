/*
Program name: Player.java
Date: Jan 24, 2025
Purpose: Player extending from entity class with health
 */

package org.group.larryquestdefinitive.entities;

import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.group.larryquestdefinitive.Constants;

public class Player extends Entity {

    // health variables
    public static int currHealth;
    public int maxHealth;

    // constructor set to defaults
    public Player(Image sprite, double x, double y, String animType) {
        super(sprite, x, y, animType);
        moveSpeed = 10;
        this.maxHealth = 10;  // Set max health
        this.currHealth = 10; // Set initial current health
        this.layoutX = Constants.WIDTH / 2;
        this.layoutY = Constants.HEIGHT / 2;

        /* DEBUG: create a rectangle to visually represent player (red box for now)
        Rectangle rect = new Rectangle(0, 0, sprite.getWidth(), sprite.getHeight());
        rect.setFill(Color.RED); */
    }
} // end of Player class
