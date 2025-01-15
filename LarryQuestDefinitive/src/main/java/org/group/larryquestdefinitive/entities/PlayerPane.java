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

    public PlayerPane(ImageView sprite, double x, double y,
                      Timeline UpAnimation, Timeline LeftAnimation,
                      Timeline DownAnimation, Timeline RightAnimation) {
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
        super.getChildren().add(sprite);
    }

    public void playTimeline(Direction direction) {
        this.stopTimelines();

        switch (direction) {
            case LEFT -> this.LeftAnimation.play();
            case RIGHT -> this.RightAnimation.play();
            case DOWN -> this.DownAnimation.play();
            case UP -> this.UpAnimation.play();
        }
    }

    public void stopTimelines() {
        this.UpAnimation.stop();
        this.LeftAnimation.stop();
        this.RightAnimation.stop();
        this.DownAnimation.stop();

        this.sprite.setImage(Main.playerIdle.getImage());
    }
}
