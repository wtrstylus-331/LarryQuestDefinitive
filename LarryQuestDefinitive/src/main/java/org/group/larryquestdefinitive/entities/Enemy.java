package org.group.larryquestdefinitive.entities;

import javafx.scene.image.Image;
import javafx.animation.*;

public class Enemy extends Entity {

    private Player player;

    public Enemy(Image sprite, double x, double y, String animType, Player player) {
        super(sprite, x, y, animType);
        moveSpeed = 1;
        this.player = player;
    }

    public void Update() {
        // Calculate the difference in X and Y positions between the player and the enemy
        double deltaX = player.getPositionX() - this.getPositionX();
        double deltaY = player.getPositionY() - this.getPositionY();

        // Check which axis (X or Y) has a greater difference to prioritize movement
        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            // If deltaX is greater, move left or right
            if (deltaX > 0) {
                this.Move(Direction.RIGHT); // Move right if player is to the right
            } else {
                this.Move(Direction.LEFT); // Move left if player is to the left
            }
        } else {
            // If deltaY is greater, move up or down
            if (deltaY > 0) {
                this.Move(Direction.DOWN); // Move down if player is below
            } else {
                this.Move(Direction.UP); // Move up if player is above
            }
        }

        // Update the enemy's position based on the movement
        layoutX = posX;
        layoutY = posY;
        super.Update();

        System.out.println(deltaX);
    }
}
