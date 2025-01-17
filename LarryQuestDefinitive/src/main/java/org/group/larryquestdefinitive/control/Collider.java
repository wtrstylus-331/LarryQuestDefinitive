package org.group.larryquestdefinitive.control;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import org.group.larryquestdefinitive.entities.*;

public class Collider extends Rectangle {
    public Collider(int width, int height) {
        super(width, height);
    }

    public void fill(Color color) {
        super.setFill(color);
    }

    public boolean isColliding(PlayerPane player) {
        // Get bounds since both nodes are within scene
        var playerBounds = player.localToScene(player.getBoundsInLocal());
        var colliderBounds = this.localToScene(this.getBoundsInLocal());

        // Check if the bounds intersect
        return playerBounds.intersects(colliderBounds);
    }

    public boolean isColliding(Entity player) {
        // Get bounds since both nodes are within scene
        var playerBounds = player.localToScene(player.getBoundsInLocal());
        var colliderBounds = this.localToScene(this.getBoundsInLocal());

        // Check if the bounds intersect
        return playerBounds.intersects(colliderBounds);
    }
}
