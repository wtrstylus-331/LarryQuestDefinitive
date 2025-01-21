package org.group.larryquestdefinitive.entities;

import javafx.scene.image.Image;
import javafx.animation.*;

public class Enemy extends Entity {

    private Player player;
    public static int hp;  // Public and static HP for the enemy (shared by all instances)
    private boolean isPaused;
    private long pauseTime;
    private final long PAUSE_DURATION = 1000; // Pause for 1 second (1000 milliseconds)

    public Enemy(Image sprite, double x, double y, String animType, Player player) {
        super(sprite, x, y, animType);
        moveSpeed = 1;
        this.player = player;
        hp = 10; // Set the enemy's initial health (can adjust as needed)
        this.isPaused = false;
        this.pauseTime = 0;
    }

    public void Update() {
        // Calculate the difference in X and Y positions between the player and the enemy
        double deltaX = player.getPositionX() - this.getPositionX();
        double deltaY = player.getPositionY() - this.getPositionY();

        // If the player is within 32 pixels of the enemy
        if (Math.abs(deltaX) <= 32 && Math.abs(deltaY) <= 32) {
            if (!isPaused) {
                // Start the pause timer if not already paused
                isPaused = true;
                pauseTime = System.currentTimeMillis();
            }

            // Check if the pause duration has passed (1 second)
            if (System.currentTimeMillis() - pauseTime >= PAUSE_DURATION) {
                // If still within 32 pixels in front of the enemy, damage the player
                if (Math.abs(deltaX) <= 32 && Math.abs(deltaY) <= 32) {
                    Player.currHealth -= 1; // Subtract 1 HP from the player
                }

                // Reset pause state
                isPaused = false;
            }
        } else {
            // If the player is not within 32 pixels, move towards the player
            moveTowardsPlayer(deltaX, deltaY);
        }

        // Update the enemy's position based on the movement
        layoutX = posX;
        layoutY = posY;
        super.Update();

        // Check if the enemy's HP is 0 or less and delete it
        if (hp <= 0) {
            this.die();  // Remove the enemy from the game
        }
    }

    // Helper method to move the enemy towards the player
    private void moveTowardsPlayer(double deltaX, double deltaY) {
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
    }

    // Getter for enemy's health
    public static int getHp() {
        return hp;
    }

    // Method to reduce enemy health (can be used for enemy damage)
    public static void takeDamage(int damage) {
        hp -= damage;
        if (hp <= 0) {
            // Handle enemy death (for example, remove from game)
            // Call die() method to handle removal or death logic
        }
    }

    // Handle enemy death (remove from game or other logic)
    private void die() {

        if (this.getParent() != null) {
            this.getParent().getChildren().remove(this); // Remove the enemy from its parent container
        }
    }
}
