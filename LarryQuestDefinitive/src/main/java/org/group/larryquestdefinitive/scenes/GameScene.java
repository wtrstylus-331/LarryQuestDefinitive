package org.group.larryquestdefinitive.scenes;

import org.group.larryquestdefinitive.Game;
import org.group.larryquestdefinitive.control.GameLoop;
import org.group.larryquestdefinitive.entities.Player;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import org.group.larryquestdefinitive.Constants;
import org.group.larryquestdefinitive.Main;
import org.group.larryquestdefinitive.control.Collider;

public class GameScene extends AnchorPane {
    private Pane holderPane;
    private ImageView map;

    public GameScene() {
        holderPane = new Pane();
        Rectangle rect = new Rectangle(500, 500);
        //holderPane.getChildren().add(rect);

        this.getChildren().add(holderPane);
    }

    public void Update(Player player){
        holderPane.setLayoutX(-player.getPositionX());
        holderPane.setLayoutY(-player.getPositionY());
    }

    public void setMap(ImageView currentMap, int w, int h) {
        this.map = currentMap;
        this.map.setFitWidth(w);
        this.map.setFitHeight(h);

        double centerX = (Constants.WIDTH - this.map.getFitWidth()) / 2;
        double centerY = (Constants.HEIGHT - this.map.getFitHeight()) / 2;

        this.map.setX(centerX);
        this.map.setY(centerY);
        this.map.setScaleX(6);
        this.map.setScaleY(6);

        this.holderPane.getChildren().add(this.map);
    }

    public void addObject(ImageView object, int x, int y, int w, int h, int collisionW, int collisionH, int xOffset, int yOffset) {
        object.setFitWidth(w);
        object.setFitHeight(h);
        object.setX(x);
        object.setY(y);

        Rectangle collider = createCollider(collisionW, collisionH);
        collider.setX(x + xOffset);
        collider.setY(y + yOffset);

        this.holderPane.getChildren().add(object);
        this.holderPane.getChildren().add(collider);
    }

    public void addCollider(int x, int y, int collisionW, int collisionH) {
        Rectangle collider = createCollider(collisionW,collisionH);
        collider.setX(x);
        collider.setY(y);

        this.holderPane.getChildren().add(collider);
    }

    private Rectangle createCollider(int w, int h) {
        Collider col = new Collider(w,h);

        if (Main.debugMode) {
            col.fill(Color.rgb(200,0,200, 0.7));
        } else {
            col.fill(Color.rgb(0,0,0,0));
        }

        //col.collide();
        return col;
    }

    public Pane getHolder(){
        return holderPane;
    }
}
