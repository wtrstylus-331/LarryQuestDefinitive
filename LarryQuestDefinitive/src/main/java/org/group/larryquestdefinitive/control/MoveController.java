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
        this.player = player;
    }

    public void setupInput(AnchorPane scene /*Image Up1, Image Left1, Image Down1, Image Right1, Enemy enemy*/) {
        scene.requestFocus();

        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case W:
                    if (!wPressed) {
                        wPressed = true;
                        player.Move(Direction.UP);
                    }
                    break;

                case A:
                    if (!aPressed) {
                        aPressed = true;
                        player.Move(Direction.LEFT);
                    }
                    break;

                case S:
                    if (!sPressed) {
                        sPressed = true;
                        player.Move(Direction.DOWN);
                    }
                    break;

                case D:
                    if (!dPressed) {
                        dPressed = true;
                        player.Move(Direction.RIGHT);
                    }
                    break;

                case SHIFT:
                    shiftPressed = true;
                    break;

                case G:
                    break;
            }
        });

        scene.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            switch (event.getCode()) {
                case W:
                    wPressed = false;
                    if (!aPressed && !sPressed && !dPressed) {
                        player.Stop(); // Stop only if no other key is pressed
                    }
                    break;
                case A:
                    aPressed = false;
                    if (!wPressed && !sPressed && !dPressed) {
                        player.Stop();
                    }
                    break;
                case S:
                    sPressed = false;
                    if (!wPressed && !aPressed && !dPressed) {
                        player.Stop();
                    }
                    break;
                case D:
                    dPressed = false;
                    if (!wPressed && !aPressed && !sPressed) {
                        player.Stop();
                    }
                    break;

                case SHIFT:
                    break;

                case G:
                    break;
            }
        });
    }
}
