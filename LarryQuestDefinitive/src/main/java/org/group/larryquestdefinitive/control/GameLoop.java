/*
Program name: GameLoop.java
Date: Jan 24, 2025
Purpose: Main loop to control the game via updates
 */

package org.group.larryquestdefinitive.control;

import java.util.ArrayList;

import org.group.larryquestdefinitive.Game;
import org.group.larryquestdefinitive.entities.Entity;
import org.group.larryquestdefinitive.scenes.GameOver;

import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {
    // loop variables
    private static final long NANOS_PER_FRAME = 1_000_000_000 / 60; // Nanoseconds per frame (60 FPS)

    private Game game;
    private ArrayList<Entity> entities;
    private long lastUpdate = 0; // Tracks the time of the last frame update

    // constructor
    public GameLoop(Game game) {
        this.game = game;
        entities = game.getEntities();
    }

    // overriding handle method
    @Override
    public void handle(long now) {
        // Limit the frame rate to 60 FPS
        if (now - lastUpdate >= NANOS_PER_FRAME) {
            lastUpdate = now;

            game.updateEntities();
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
        }
    } // end of handle method

    // method to end loop
    public void end(){
        this.stop();
        game.getPlayer().currHealth = 10;
        game.GameOver();
    }
} // end of GameLoop class
