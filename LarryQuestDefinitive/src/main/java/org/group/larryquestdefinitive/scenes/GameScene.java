package org.group.larryquestdefinitive.scenes;

import org.group.larryquestdefinitive.entities.Player;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import org.group.larryquestdefinitive.Constants;

public class GameScene extends AnchorPane {
    private Pane holderPane;
    private ImageView map;

    public GameScene(){
        holderPane = new Pane();
        Rectangle rect = new Rectangle(500, 500);
        holderPane.getChildren().add(rect);

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

        this.holderPane.getChildren().add(this.map);
    }
}
