package org.group.larryquestdefinitive.entities;

import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.group.larryquestdefinitive.Main;

public class PlayerPane extends Pane {
    protected ImageView sprite;

    protected double posX;
    protected double posY;

    protected Timeline UpAnimation;
    protected Timeline LeftAnimation;
    protected Timeline DownAnimation;
    protected Timeline RightAnimation;

    private boolean isUpActive = false;
    private boolean isDownActive = false;
    private boolean isLeftActive = false;
    private boolean isRightActive = false;

    public PlayerPane(ImageView sprite, double x, double y,
                      Timeline UpAnimation, Timeline DownAnimation,
                      Timeline LeftAnimation, Timeline RightAnimation) {
        super();

        this.sprite = sprite;
        this.posX = x;
        this.posY = y;
        this.UpAnimation = UpAnimation;
        this.LeftAnimation = LeftAnimation;
        this.DownAnimation = DownAnimation;
        this.RightAnimation = RightAnimation;

        super.setWidth(64);
        super.setHeight(64);
        super.setLayoutX(posX);
        super.setLayoutY(posY);
        super.getChildren().add(sprite);
    }

    public void setImage(ImageView image) {
        this.sprite.setImage(image.getImage());
    }

    public void playAnimation(Direction direction) {
        stopTimelinesExcept(direction);

        switch (direction) {
            case UP -> {
                if (!isUpActive) {
                    UpAnimation.play();
                    isUpActive = true;
                }
            }
            case DOWN -> {
                if (!isDownActive) {
                    DownAnimation.play();
                    isDownActive = true;
                }
            }
            case LEFT -> {
                if (!isLeftActive) {
                    LeftAnimation.play();
                    isLeftActive = true;
                }
            }
            case RIGHT -> {
                if (!isRightActive) {
                    RightAnimation.play();
                    isRightActive = true;
                }
            }
        }
    }

    public void stopTimelines() {
        UpAnimation.stop();
        DownAnimation.stop();
        LeftAnimation.stop();
        RightAnimation.stop();

        isUpActive = false;
        isDownActive = false;
        isLeftActive = false;
        isRightActive = false;

        this.sprite.setImage(Main.playerIdle.getImage());
    }

    private void stopTimelinesExcept(Direction direction) {
        switch (direction) {
            case UP -> {
                if (isDownActive || isLeftActive || isRightActive) stopTimelines();
            }
            case DOWN -> {
                if (isUpActive || isLeftActive || isRightActive) stopTimelines();
            }
            case LEFT -> {
                if (isUpActive || isDownActive || isRightActive) stopTimelines();
            }
            case RIGHT -> {
                if (isUpActive || isDownActive || isLeftActive) stopTimelines();
            }
        }
    }
}
