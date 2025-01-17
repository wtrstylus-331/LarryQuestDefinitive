package org.group.larryquestdefinitive.entities;

import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.animation.*;

public class Entity extends Pane{

  protected ImageView sprite;

  protected double posX;
  protected double posY;
  protected double layoutX;
  protected double layoutY;

  protected Timeline UpAnimation; 
  protected Timeline LeftAnimation; 
  protected Timeline DownAnimation;
  protected Timeline RightAnimation;

  public Entity(ImageView sprite, double x, double y,
                Timeline UpAnimation, Timeline LeftAnimation, 
                Timeline DownAnimation, Timeline RightAnimation) {

    this.sprite = sprite;
    this.posX = x;
    this.posY = y;
    this.UpAnimation = UpAnimation;
    this.LeftAnimation = LeftAnimation;
    this.DownAnimation = DownAnimation;
    this.RightAnimation = RightAnimation;
  }

  public void Move(Direction dir) {
    switch (dir) {
        case Direction.UP:
            // Start UpAnimation and update position
            UpAnimation.play();
            layoutY -= 1; // Adjust this value based on your game logic
            break;

        case DOWN:
            // Start DownAnimation and update position
            DownAnimation.play();
            layoutY += 1;
            break;

        case LEFT:
            // Start LeftAnimation and update position
            LeftAnimation.play();
            layoutX -= 1;
            break;

        case RIGHT:
            // Start RightAnimation and update position
            RightAnimation.play();
            layoutX += 1;
            break;
    }
}

  public void Update() {
    setLayoutX(layoutX);
    setLayoutY(layoutY);
  }

  public ImageView getSprite() {
    return sprite;
  }

  public double getPositionX() {
    return posX;
  }

  public void setPositionX(double x) {
    this.posX = x;
  }

  public double getPositionY() {
    return posY;
  }

  public void setPositionY(double y) {
    this.posY = y;
  }
}