/*
Program name: Game.java
Date: Jan 24, 2025
Purpose: Main class for handling scenes and entities
 */

package org.group.larryquestdefinitive;

import java.util.ArrayList;

import org.group.larryquestdefinitive.control.CollisionDetection;
import org.group.larryquestdefinitive.control.MoveController;
import org.group.larryquestdefinitive.entities.Entity;
import org.group.larryquestdefinitive.entities.Player;
import org.group.larryquestdefinitive.scenes.GameOver;
import org.group.larryquestdefinitive.scenes.GameScene;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class Game {

    // private variables for players, controllers and scenes
    private Player player;
    private MoveController mc;
    private CollisionDetection cd;

    private Scene playScene;
    private GameScene currScene;

    private ArrayList<Entity> entities;

    private Stage stage;
    private int score;

    // constructor set to defaults
    public Game(Player player, Scene playScene, Stage stage){
        this.player = player;
        this.playScene = playScene;
        this.stage = stage;
        mc = new MoveController(this.player);
        cd = new CollisionDetection();
        entities = new ArrayList<>();
    }

    // method to return player
    public Player getPlayer() {
        return player;
    }

    // method to return current scene loaded
    public GameScene getCurrScene(){
        return currScene;
    }

    // method to return generalized scene
    public Scene getScene(){
        return playScene;
    }

    // method to increment score
    public void incrementScore() {
        score += 1;
    }

    // method to reset score
    public void resetScore() {
        score = 0;
    }

    // method to update logic for all current entities
    public void updateEntities() {
        for (Entity entity : entities) {
            entity.Update();
        }
    }

    // method to set scene specified
    public void setScene(GameScene scene){
        currScene = scene;
        currScene.getChildren().add(player);

        // add to pane
        for(int i = 0; i < entities.size(); i++){
            currScene.getHolder().getChildren().add(entities.get(i));
        }

        playScene.setRoot(scene);
        mc.setupInput(currScene);
    }

    // method to check collision based on controller
    public void checkCollision(){
        cd.checkCollision(player, currScene.getHolder());
    }

    // method to add entity
    public void addEntity(Entity add){
        entities.add(add);
    }

    // method to return arraylist of entities
    public ArrayList<Entity> getEntities(){
        return entities;
    }

    // method to remove entity from arraylist
    public void removeEntity(Entity remove){
        entities.remove(remove);
    }

    // method to display game over with score
    public void GameOver(){
        GameOver go = new GameOver(stage, score);
        go.display();
        resetScore();
    }
} // end of Game class
