package org.group.larryquestdefinitive.entities;

import javafx.scene.image.Image;
import javafx.animation.*;

public class Enemy extends Entity {

    private Player player;

    public Enemy(Image sprite, double x, double y, String animType, Player player) {
        super(sprite, x, y, animType);
        this.player = player;
    }

    public void Update() {
        // Calculate the angle between the enemy and the player
        double angle = Math.atan2(player.getPositionY() - posY, player.getPositionX() - posX) * 180 / Math.PI;

        // Based on the angle, choose a direction
        if (angle > -45 && angle < 45) {
            // Move right
            Move(Direction.RIGHT);
        } else if (angle >= 45 && angle < 135) {
            // Move up
            Move(Direction.UP);
        } else if (angle >= -135 && angle < -45) {
            // Move down
            Move(Direction.DOWN);
        } else {
            // Move left
            Move(Direction.LEFT);
        }

        // Update the enemy's position based on the movement
        super.Update();
    }
}
