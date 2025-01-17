package org.group.larryquestdefinitive;

import org.group.larryquestdefinitive.control.CollisionDetection;
import org.group.larryquestdefinitive.control.GameLoop;
import org.group.larryquestdefinitive.control.MoveController;
import org.group.larryquestdefinitive.entities.Player;
import org.group.larryquestdefinitive.scenes.GameScene;

import javafx.scene.Scene;

public class Game {

    private Player player;
    private MoveController mc;
    private CollisionDetection cd;

    private Scene playScene;
    private GameScene currScene;

    public Game(Player player, Scene playScene){
        this.player = player;
        this.playScene = playScene;
        mc = new MoveController(this.player);
        cd = new CollisionDetection();
    }

    public Player getPlayer() {
        return player;
    }
    
    public GameScene getCurrScene(){
        return currScene;
    }

    public void setScene(GameScene scene){
        currScene = scene;
        playScene.setRoot(scene);
    }
}
