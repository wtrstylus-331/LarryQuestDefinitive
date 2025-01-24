/*
Program name: Entity.java
Date: Jan 24, 2025
Purpose: Superclass to control animations and some collision
 */

package org.group.larryquestdefinitive.entities;

import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.group.larryquestdefinitive.Main;

import javafx.animation.*;

public class Entity extends Pane{

    // sprite
  protected ImageView sprite;

  // positions
  protected double posX;
  protected double posY;
  protected double layoutX;
  protected double layoutY;

  // timelines for animations
  protected Timeline UpAnimation; 
  protected Timeline LeftAnimation; 
  protected Timeline DownAnimation;
  protected Timeline RightAnimation;

  // idle sprites
  protected Image idleUp;
  protected Image idleDown;
  protected Image idleLeft;
  protected Image idleRight;

  // booleans
  protected Direction direction = Direction.UP;
  protected boolean canMoveRight = true;
  protected boolean canMoveLeft = true;
  protected boolean canMoveUp = true;
  protected boolean canMoveDown = true;

  protected boolean movingUp = false;
  protected boolean movingDown = false;
  protected boolean movingLeft = false;
  protected boolean movingRight = false;

  protected int moveSpeed;

  protected Rectangle collider;

  // constructor to set default values
  public Entity(Image sprite, double x, double y, String animType) {

    this.sprite = new ImageView(sprite);
    this.collider = new Rectangle(16, 8, 32, 50);
    this.collider.setFill(Color.TRANSPARENT);
    super.getChildren().addAll(this.collider, this.sprite);
    //super.getChildren().add(this.sprite);
    this.posX = x;
    this.posY = y;
    this.UpAnimation = initTimelines(animType, "up");
    this.LeftAnimation = initTimelines(animType, "left");
    this.DownAnimation = initTimelines(animType, "down");
    this.RightAnimation = initTimelines(animType, "right");

    idleUp = getIdle(animType, "up");
    idleDown = getIdle(animType, "down");
    idleLeft = getIdle(animType, "left");
    idleRight = getIdle(animType, "right");
  }

  // method to play animations based on direction
  public void Move(Direction dir) {
    switch (dir) {
        case Direction.UP:
            // Start UpAnimation and update position
            if(!movingUp){
              DownAnimation.stop();
              LeftAnimation.stop();
              RightAnimation.stop();

              setImage(idleUp);
              UpAnimation.play();
            }
            movingUp = true;

            movingDown = false;
            movingLeft = false;
            movingRight = false;
            break;

        case DOWN:
            // Start DownAnimation and update position
            if(!movingDown){
              UpAnimation.stop();
              LeftAnimation.stop();
              RightAnimation.stop();

              setImage(idleDown);
              DownAnimation.play();
            }
            movingDown = true;

            movingUp = false;
            movingLeft = false;
            movingRight = false;
            break;

        case LEFT:
            // Start LeftAnimation and update position
            if(!movingLeft){
              UpAnimation.stop();
              DownAnimation.stop();
              RightAnimation.stop();
              
              setImage(idleLeft);
              LeftAnimation.play();
            }
            movingLeft = true;

            movingUp = false;
            movingDown = false;
            movingRight = false;
            break;

        case RIGHT:
            // Start RightAnimation and update position
            if(!movingRight){
              UpAnimation.stop();
              DownAnimation.stop();
              LeftAnimation.stop();
              
              setImage(idleRight);
              RightAnimation.play();
            }
            movingRight = true;

            movingUp = false;
            movingDown = false;
            movingLeft = false;
            break;
    }
  } // end of Move method

    // method to stop animations
  public void Stop(){
    UpAnimation.stop();
    DownAnimation.stop();
    LeftAnimation.stop();
    RightAnimation.stop();

    if(movingUp) setImage(idleUp);
    if(movingDown) setImage(idleDown);
    if(movingLeft) setImage(idleLeft);
    if(movingRight) setImage(idleRight);

    movingUp = false;
    movingDown = false;
    movingLeft = false;
    movingRight = false;
  } // end of Stop method

    // method to update position
  public void Update() {
    setLayoutX(layoutX);
    setLayoutY(layoutY);

    if(movingUp && canMoveUp) posY -= 1 * moveSpeed; // Adjust this value based on your game logic
    if(movingDown && canMoveDown) posY += 1 * moveSpeed; // Adjust this value based on your game logic
    if(movingLeft && canMoveLeft) posX -= 1 * moveSpeed; // Adjust this value based on your game logic
    if(movingRight && canMoveRight) posX += 1 * moveSpeed; // Adjust this value based on your game logic
  }

  // method to return sprite
  public ImageView getSprite() {
    return sprite;
  }

  // method to return x position
  public double getPositionX() {
    return posX;
  }

   // method to set x position
  public void setPositionX(double x) {
    this.posX = x;
  }

  // method to return y position
  public double getPositionY() {
    return posY;
  }

  // method to set y position
  public void setPositionY(double y) {
    this.posY = y;
  }

  // method to conditionally set boolean flags for movement
  public void setPossibleMove(Direction dir, boolean possible){
    switch (dir) {
      case UP:
        canMoveUp = possible;
        break;
      case DOWN:
        canMoveDown = possible;
        break;
      case LEFT:
        canMoveLeft = possible;
        break;
      case RIGHT:
        canMoveRight = possible;
        break;
      case ALL:
        canMoveUp = possible;
        canMoveDown = possible;
        canMoveLeft = possible;
        canMoveRight = possible;
        break;
    }
  } // end of setPossibleMove method

    //  method to set sprite image
  public void setImage(Image image){
    this.sprite.setImage(image);
  }

  // method to create timelines for movement animations
  private Timeline initTimelines(String type, String dir) {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        // loop through resource and add to specific timeline param
        for (int i = 1; i <= 9; i++) {
            i = i == 1 ? 2 : i;
            int index = i;
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.1 * (i - 1)), e -> {
                setImage(new Image(
                        Main.class.getResourceAsStream("sprites/" + type + "/" + dir + "/walk_" + dir + index + ".png"))
                );
            }));
        }

        return timeline;
    } // end of initTimelines method

    // method to get idle sprite
  private Image getIdle(String type, String dir){
    return new Image(Main.class.getResourceAsStream("sprites/" + type + "/" + dir + "/walk_" + dir + 1 + ".png"));
  }

  // method to return collider rectangle
  public Rectangle getCollider(){
    return collider;
  }
} // end of Entity class