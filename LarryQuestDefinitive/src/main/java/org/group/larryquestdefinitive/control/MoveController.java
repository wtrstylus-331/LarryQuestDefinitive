package org.group.larryquestdefinitive.control;

import org.group.larryquestdefinitive.entities.*;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class MoveController {
    private Player player;

    private boolean wPressed = false;
    private boolean aPressed = false;
    private boolean sPressed = false;
    private boolean dPressed = false;
    private boolean shiftPressed = false;

    public MoveController(Player player){

    }

    public void setupInput(AnchorPane scene, Image Up1, Image Left1, Image Down1, Image Right1, Enemy enemy) {
    scene.requestFocus();
    
    scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
        switch (event.getCode()) {
          case W:
              
              wPressed = true;
              aPressed = false;
              sPressed = false;
              dPressed = false;
              break;
            
          case A:
              
              wPressed = false;
              aPressed = true;
              sPressed = false;
              dPressed = false;
              break;
            
          case S:
              
              wPressed = false;
              aPressed = false;
              sPressed = true;
              dPressed = false;
              break;  
            
          case D:
              
              wPressed = false;
              aPressed = false;
              sPressed = false;
              dPressed = true;
              break;

          case SHIFT:

              shiftPressed = true;
              //last = System.nanoTime();
              break;

          case G:
              /*now5 = System.nanoTime();
              if(now5 - last5 >= 200_000_000 && mana >= 20){
                attack(enemy);
                last5 = System.nanoTime();
              }*/
              break;
        }
    });
     
    scene.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
        switch (event.getCode()) {
            case W:
                super.playerUpAnimation.stop();
                if (lastAnimationWasUp) {
                    super.sprite.setImage(Up1);
                    lastAnimationWasUp = false;
                }
                wPressed = false;
                break;
            case A:
                super.playerLeftAnimation.stop();
                if (lastAnimationWasLeft) {
                    super.sprite.setImage(Left1);
                    lastAnimationWasLeft = false;
                }
                aPressed = false;
                break;
            case S:
                super.playerDownAnimation.stop();
                if (lastAnimationWasDown) {
                    super.sprite.setImage(Down1);
                    lastAnimationWasDown = false;
                }
                sPressed = false;
                break;
            case D:
                super.playerRightAnimation.stop();
                if (lastAnimationWasRight) {
                    super.sprite.setImage(Right1);
                    lastAnimationWasRight = false;
                }
                dPressed = false;
                break;

            case SHIFT:

                break;

            case G:
                break;
            }
        });
    }
}
