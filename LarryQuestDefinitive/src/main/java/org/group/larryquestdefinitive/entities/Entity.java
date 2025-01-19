package org.group.larryquestdefinitive.entities;

import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import org.group.larryquestdefinitive.Constants;
import org.group.larryquestdefinitive.Main;

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

  protected Image idleUp;
  protected Image idleDown;
  protected Image idleLeft;
  protected Image idleRight;

  protected Direction direction = Direction.UP;
  protected boolean canMoveRight = true;
  protected boolean canMoveLeft = true;
  protected boolean canMoveUp = true;
  protected boolean canMoveDown = true;

  protected boolean movingUp = false;
  protected boolean movingDown = false;
  protected boolean movingLeft = false;
  protected boolean movingRight = false;

  public Entity(Image sprite, double x, double y, String animType) {

    this.sprite = new ImageView(sprite);
    super.getChildren().add(this.sprite);
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
  }

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
  }

  public void Update() {
    setLayoutX(layoutX);
    setLayoutY(layoutY);

    if(movingUp && canMoveUp) posY -= 10; // Adjust this value based on your game logic
    if(movingDown && canMoveDown) posY += 10; // Adjust this value based on your game logic
    if(movingLeft && canMoveLeft) posX -= 10; // Adjust this value based on your game logic
    if(movingRight && canMoveRight) posX += 10; // Adjust this value based on your game logic
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

  public Direction getDirection(){
    return direction;
  }

  public void setDirection(Direction dir){
    direction = dir;
  }

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
  }

  public void setImage(Image image){
    this.sprite.setImage(image);
  }

  private Timeline initTimelines(String type, String dir) {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
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

  private Image getIdle(String type, String dir){
    return new Image(Main.class.getResourceAsStream("sprites/" + type + "/" + dir + "/walk_" + dir + 1 + ".png"));
  }
}