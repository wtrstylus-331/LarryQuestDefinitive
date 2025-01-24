package org.group.larryquestdefinitive.control;

import java.util.ArrayList;

import org.group.larryquestdefinitive.Game;
import org.group.larryquestdefinitive.entities.Entity;
import org.group.larryquestdefinitive.scenes.GameOver;

import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {



    private static final long NANOS_PER_FRAME = 1_000_000_000 / 60; // Nanoseconds per frame (60 FPS)

    private Game game;
    private ArrayList<Entity> entities;
    private long lastUpdate = 0; // Tracks the time of the last frame update

    public GameLoop(Game game) {
        this.game = game;
        entities = game.getEntities();
    }

    @Override
    public void handle(long now) {
        // Limit the frame rate to 60 FPS
        if (now - lastUpdate >= NANOS_PER_FRAME) {
            lastUpdate = now;

            game.checkCollision();
            // Game update logic
            game.getCurrScene().Update(game.getPlayer());
            game.getPlayer().Update();

            for (int i = 0; i < entities.size(); i++) {
                entities.get(i).Update();
            }

            if(game.getPlayer().currHealth <= 0){
                 end();
            }



    //        System.out.println(game.getPlayer().getPositionX() + ", " + game.getPlayer().getPositionY());
       //     System.out.println("Entity: "+ entities.get(0).getPositionX() + ", " + entities.get(0).getPositionY());
        }
    }

    public void end(){
        this.stop();
        game.GameOver();
    }
}
