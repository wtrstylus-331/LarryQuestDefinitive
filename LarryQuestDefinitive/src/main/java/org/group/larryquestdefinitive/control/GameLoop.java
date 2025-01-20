package org.group.larryquestdefinitive.control;

import java.util.ArrayList;

import org.group.larryquestdefinitive.Game;
import org.group.larryquestdefinitive.entities.Entity;

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
        }
    }
}
