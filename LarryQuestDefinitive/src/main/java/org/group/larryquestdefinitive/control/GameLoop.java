package org.group.larryquestdefinitive.control;

import java.util.ArrayList;

import org.group.larryquestdefinitive.Game;
import org.group.larryquestdefinitive.entities.Entity;

import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer{
    private Game game;
    private ArrayList<Entity> entities;

    public GameLoop(Game game){
        this.game = game;
    }

    public void handle(long now){
        
        game.getCurrScene().Update(game.getPlayer());
        //game.getPlayer().Update();

        for(int i = 0; i < entities.size(); i++){
            entities.get(i).Update();
        }
    }
}
