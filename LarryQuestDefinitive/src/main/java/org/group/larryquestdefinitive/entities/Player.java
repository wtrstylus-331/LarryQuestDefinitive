package org.group.larryquestdefinitive.entities;

import javafx.scene.image.*;
import javafx.animation.*;

public class Player extends Entity{
    public Player(ImageView sprite, double x, double y,
                Timeline UpAnimation, Timeline LeftAnimation, 
                Timeline DownAnimation, Timeline RightAnimation){
        super(sprite, x, y, UpAnimation, LeftAnimation, DownAnimation, RightAnimation);
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
    
    public void Update(){
        
    }
}