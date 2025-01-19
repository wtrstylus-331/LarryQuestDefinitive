package org.group.larryquestdefinitive.entities;

import javafx.scene.image.*;

import org.group.larryquestdefinitive.Constants;

import javafx.animation.*;

public class Player extends Entity{
    public Player(Image sprite, double x, double y, String animType){
        super(sprite, x, y, animType);
        this.layoutX = Constants.WIDTH / 2;
        this.layoutY = Constants.HEIGHT / 2;
    }

    /*public void Move(Direction dir) {
        switch (dir) {
            case Direction.UP:
                // Start UpAnimation and update position
                UpAnimation.play();
                //layoutY -= 1; // Adjust this value based on your game logic
                break;

            case DOWN:
                // Start DownAnimation and update position
                DownAnimation.play();
                //layoutY += 1;
                break;

            case LEFT:
                // Start LeftAnimation and update position
                LeftAnimation.play();
                //layoutX -= 1;
                break;

            case RIGHT:
                // Start RightAnimation and update position
                RightAnimation.play();
                //layoutX += 1;
                break;
        }
    }*/
    
    /*public void Update(){
        
    }*/
}