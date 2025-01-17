package org.group.larryquestdefinitive.scenes;

import org.group.larryquestdefinitive.entities.Player;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class GameScene extends AnchorPane {
    private Pane holderPane;
    private ImageView map;

    public GameScene(){

    }

    public void Update(Player player){
        holderPane.setLayoutX(-player.getPositionX());
        holderPane.setLayoutY(-player.getPositionY());
    }
}
