package org.group.larryquestdefinitive;

import java.util.ArrayList;

import org.group.larryquestdefinitive.control.CollisionDetection;
import org.group.larryquestdefinitive.control.GameLoop;
import org.group.larryquestdefinitive.control.MoveController;
import org.group.larryquestdefinitive.entities.Entity;
import org.group.larryquestdefinitive.entities.Player;
import org.group.larryquestdefinitive.scenes.GameScene;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class Game {

    private Player player;
    private MoveController mc;
    private CollisionDetection cd;

    private Scene playScene;
    private GameScene currScene;

    private ArrayList<Entity> entities;

    private Stage stage;

    public Game(Player player, Scene playScene, Stage stage){
        this.player = player;
        this.playScene = playScene;
        this.stage = stage;
        mc = new MoveController(this.player);
        cd = new CollisionDetection();
        entities = new ArrayList<>();
    }

    public Player getPlayer() {
        return player;
    }
    
    public GameScene getCurrScene(){
        return currScene;
    }

    public Scene getScene(){
        return playScene;
    }

    public void setScene(GameScene scene){
        currScene = scene;
        currScene.getChildren().add(player);
        for(int i = 0; i < entities.size(); i++){
            currScene.getHolder().getChildren().add(entities.get(i));
        }
        playScene.setRoot(scene);
        mc.setupInput(currScene);
    }

    public void checkCollision(){
        cd.checkCollision(player, currScene.getHolder());
    }

    public void addEntity(Entity add){
        entities.add(add);
    }
    
    public ArrayList<Entity> getEntities(){
        return entities;
    }

    public void removeEntity(Entity remove){
        entities.remove(remove);
    }

    public void GameOver(){
        org.group.larryquestdefinitive.scenes.GameOver go = new org.group.larryquestdefinitive.scenes.GameOver(stage);
        go.display();
    }
}
