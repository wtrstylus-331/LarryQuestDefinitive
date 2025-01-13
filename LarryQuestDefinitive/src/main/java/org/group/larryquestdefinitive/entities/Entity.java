package org.group.larryquestdefinitive.entities;

import javafx.scene.image.*;
import javafx.animation.*;

public class Entity {

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