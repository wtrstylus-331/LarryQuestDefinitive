/*
Program name: CollisionDetection.java
Date: Jan 24, 2025
Purpose: Detect if collisions are happening
 */

package org.group.larryquestdefinitive.control;

import org.group.larryquestdefinitive.entities.Direction;
import org.group.larryquestdefinitive.entities.Entity;
import javafx.scene.layout.Pane;
import javafx.geometry.Bounds;

public class CollisionDetection {
    // method to check if colliding or not
    public void checkCollision(Entity entity, Pane holderPane) {
        // Reset movement flags
        entity.setPossibleMove(Direction.ALL, true);

        Bounds entityBounds = entity.getCollider().localToScene(entity.getCollider().getBoundsInLocal());

        // Loop through children
        for (var node : holderPane.getChildren()) {
            if (node instanceof Collider collider) {
                Bounds colliderBounds = collider.localToScene(collider.getBoundsInLocal());

                // Check if entity bounds intersect with collider bounds
                if (entityBounds.intersects(colliderBounds)) {
                    double overlapX = 0;
                    double overlapY = 0;

                    // Check vertical collision (downward movement)
                    if (entityBounds.getMaxY() > colliderBounds.getMinY() && entityBounds.getMinY() < colliderBounds.getMinY()) {
                        if (entityBounds.getMaxY() > colliderBounds.getMinY()) {
                            entity.setPossibleMove(Direction.DOWN, false);
                            overlapY = entityBounds.getMaxY() - colliderBounds.getMinY();
                            entity.setPositionY(entity.getPositionY() - overlapY); // Adjust position above collider
                        }
                    }
                    // Check vertical collision (upward movement)
                    if (entityBounds.getMinY() < colliderBounds.getMaxY() && entityBounds.getMaxY() > colliderBounds.getMaxY()) {
                        if (entityBounds.getMinY() < colliderBounds.getMaxY()) {
                            entity.setPossibleMove(Direction.UP, false);
                            overlapY = colliderBounds.getMaxY() - entityBounds.getMinY();
                            entity.setPositionY(entity.getPositionY() + overlapY); // Adjust position below collider
                        }
                    }

                    // Check horizontal collision (rightward movement)
                    if (entityBounds.getMaxX() > colliderBounds.getMinX() && entityBounds.getMinX() < colliderBounds.getMinX()) {
                        if (entityBounds.getMaxX() > colliderBounds.getMinX()) {
                            entity.setPossibleMove(Direction.RIGHT, false);
                            overlapX = entityBounds.getMaxX() - colliderBounds.getMinX();
                            entity.setPositionX(entity.getPositionX() - overlapX); // Adjust position left of collider
                        }
                    }
                    // Check horizontal collision (leftward movement)
                    if (entityBounds.getMinX() < colliderBounds.getMaxX() && entityBounds.getMaxX() > colliderBounds.getMaxX()) {
                        if (entityBounds.getMinX() < colliderBounds.getMaxX()) {
                            entity.setPossibleMove(Direction.LEFT, false);
                            overlapX = colliderBounds.getMaxX() - entityBounds.getMinX();
                            entity.setPositionX(entity.getPositionX() + overlapX); // Adjust position right of collider
                        }
                    }

                    // Check adjacency for specific direction
                    if (!isAdjacent(entityBounds, colliderBounds, Direction.UP)) {
                        entity.setPossibleMove(Direction.UP, true);
                    } else {
                        entity.setPossibleMove(Direction.UP, false);
                    }

                    if (!isAdjacent(entityBounds, colliderBounds, Direction.DOWN)) {
                        entity.setPossibleMove(Direction.DOWN, true);
                    } else {
                        entity.setPossibleMove(Direction.DOWN, false);
                    }

                    if (!isAdjacent(entityBounds, colliderBounds, Direction.LEFT)) {
                        entity.setPossibleMove(Direction.LEFT, true);
                    } else {
                        entity.setPossibleMove(Direction.LEFT, false);
                    }

                    if (!isAdjacent(entityBounds, colliderBounds, Direction.RIGHT)) {
                        entity.setPossibleMove(Direction.RIGHT, true);
                    } else {
                        entity.setPossibleMove(Direction.RIGHT, false);
                    }
                }
            }
        }
    } // end of checkCollision method

    // Helper method to check if the entity is adjacent to the collider in a specific direction
    private boolean isAdjacent(Bounds entityBounds, Bounds colliderBounds, Direction direction) {
        switch (direction) {
            case UP:
                return entityBounds.getMinY() == colliderBounds.getMaxY(); // touching above
            case DOWN:
                return entityBounds.getMaxY() == colliderBounds.getMinY(); // touching below
            case LEFT:
                return entityBounds.getMinX() == colliderBounds.getMaxX(); // touching left
            case RIGHT:
                return entityBounds.getMaxX() == colliderBounds.getMinX(); // touching right
            default:
                return false;
        }
    }
} // end of CollisionDetection class
