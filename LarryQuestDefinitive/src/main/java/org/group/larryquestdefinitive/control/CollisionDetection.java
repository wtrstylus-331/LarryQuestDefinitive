package org.group.larryquestdefinitive.control;

import org.group.larryquestdefinitive.Main;
import org.group.larryquestdefinitive.entities.Direction;
import org.group.larryquestdefinitive.entities.Entity;

import javafx.scene.layout.Pane;

public class CollisionDetection {
    public void checkCollision(Entity entity, Pane holderPane){
        entity.setPossibleMove(Direction.ALL, true);

        for (var node : holderPane.getChildren()) {
            if (node instanceof Collider collider && collider.isColliding(entity)) {
                if (entity.getPositionX() - 2 < collider.getTranslateX()) {
                    entity.setPossibleMove(Direction.RIGHT, false);
                }
                if (entity.getPositionX() + 2 > collider.getTranslateX()) {
                    entity.setPossibleMove(Direction.LEFT, false);
                }
                if (entity.getPositionY() + 2 > collider.getTranslateY()) {
                    entity.setPossibleMove(Direction.UP, false);
                }
                if (entity.getPositionY() - 2 < collider.getTranslateY()) {
                    entity.setPossibleMove(Direction.DOWN, false);
                }
            }
        }
    }
}
