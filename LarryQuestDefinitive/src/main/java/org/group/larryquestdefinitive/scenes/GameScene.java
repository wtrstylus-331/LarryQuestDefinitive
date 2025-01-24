/*
Program name: GameScene.java
Date: Jan 24, 2025
Purpose: Main scene where player and enemy logic are handled, with UI elements for health and colliders
 */

package org.group.larryquestdefinitive.scenes;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.group.larryquestdefinitive.Game;
import org.group.larryquestdefinitive.entities.Enemy;
import org.group.larryquestdefinitive.entities.Entity;
import org.group.larryquestdefinitive.entities.Player;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import org.group.larryquestdefinitive.Constants;
import org.group.larryquestdefinitive.Main;
import org.group.larryquestdefinitive.control.Collider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameScene extends AnchorPane {
    // private variables including UI elements
    private Pane holderPane;
    private ImageView map;
    private Rectangle hpBarBackground;
    private Rectangle hpBar;
    private Text scoreText;
    private Game game;

    // for wave system
    private int waveCounter, score;
    private Random rand = new Random();

    // constructor
    public GameScene() {
        holderPane = new Pane();
        waveCounter = score = 0;
        super.setBackground(Background.fill(Color.rgb(150,150,150)));

        // Initialize the background and foreground for the HP bar
        hpBarBackground = new Rectangle(200, 20);  // Set background size of the HP bar
        hpBarBackground.setFill(Color.GRAY);        // Gray color for the background
        hpBarBackground.setStroke(Color.BLACK);    // Border for the background
        hpBarBackground.setX(10);                  // Position at the top-left corner (X)
        hpBarBackground.setY(10);                  // Position at the top-left corner (Y)

        hpBar = new Rectangle(200, 20);             // HP bar is the same size as background initially
        hpBar.setFill(Color.GREEN);                 // Green color for the HP bar
        hpBar.setX(10);
        hpBar.setY(10);

        // set score text
        scoreText = new Text("Score: 0");
        scoreText.setX(10);
        scoreText.setY(70);
        scoreText.setFont(Font.font("arial", 30));

        this.getChildren().addAll(holderPane, hpBarBackground, hpBar, scoreText);
        this.listener();

        hpBarBackground.toFront();
        hpBar.toFront();
    } // end of GameScene constructor

    // method to set game variable
    public void setGame(Game g) {
        this.game = g;
    }

    // method to update score label
    private void updateScore(int score) {
        scoreText.setText("Score: " + score);
    }

    // Method to update pane position and player hp
    public void Update(Player player){
        // Update the holder pane position
        holderPane.setLayoutX(-player.getPositionX() + 500);
        holderPane.setLayoutY(-player.getPositionY() + 300);

        // Update the HP bar size based on player's current health
        updateHPBar(player);
    }

    // Update the HP bar based on the player's current health
    public void updateHPBar(Player player) {
        // Accessing the player's current health and max health
        double healthPercentage = (double) player.currHealth / player.maxHealth;
        double newWidth = 200 * healthPercentage;  // Calculate the new width of the HP bar

        hpBar.setWidth(newWidth);  // Set the width of the HP bar to match the health percentage

        // Change the HP bar color based on the health percentage
        if (healthPercentage > 0.5) {
            hpBar.setFill(Color.GREEN); // Green when health is more than 50%
        } else if (healthPercentage > 0.2) {
            hpBar.setFill(Color.YELLOW); // Yellow when health is between 20% and 50%
        } else {
            hpBar.setFill(Color.RED);    // Red when health is below 20%
        }
    } // end of updateHPBar method

    // method to set map from image
    public void setMap(ImageView currentMap, int w, int h) {
        this.map = currentMap;
        this.map.setFitWidth(w);
        this.map.setFitHeight(h);

        // center position
        double centerX = (Constants.WIDTH - this.map.getFitWidth()) / 2;
        double centerY = (Constants.HEIGHT - this.map.getFitHeight()) / 2;

        this.map.setX(centerX);
        this.map.setY(centerY);

        // add to pane
        this.holderPane.getChildren().add(this.map);
    } // end of setMap method

    // method to add image object with collider
    public void addObject(ImageView object, int x, int y, int w, int h, int collisionW, int collisionH, int xOffset, int yOffset) {
        object.setFitWidth(w);
        object.setFitHeight(h);
        object.setX(x);
        object.setY(y);

        Rectangle collider = createCollider(collisionW, collisionH);
        collider.setX(x + xOffset);
        collider.setY(y + yOffset);

        this.holderPane.getChildren().add(object);
        this.holderPane.getChildren().add(collider);
    } // end of addObject method

    // method to add collider object only
    public void addCollider(int x, int y, int collisionW, int collisionH) {
        Rectangle collider = createCollider(collisionW,collisionH);
        collider.setX(x);
        collider.setY(y);

        this.holderPane.getChildren().add(collider);
    }

    // method to create collider object
    private Rectangle createCollider(int w, int h) {
        Collider col = new Collider(w,h);

        // fill color if in debug mode
        if (Main.debugMode) {
            col.fill(Color.rgb(200,0,200, 0.7));
        } else {
            col.fill(Color.rgb(0,0,0,0));
        }

        return col;
    }

    // method to return pane holding all elements
    public Pane getHolder() {
        return holderPane;
    }

    // method to handle mouse clicked event, enemy wave system and score
    private void listener() {
        super.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                Player currPlr = game.getPlayer(); // get the current player from the game object

                if (currPlr == null) {
                    System.out.println("Player is null!");
                    return;
                }

                // generalized enemy handling in list
                List<Enemy> enemies = new ArrayList<>();
                for (Entity entity : game.getEntities()) {
                    if (entity instanceof Enemy) {
                        enemies.add((Enemy) entity);
                    }
                }

                // check if there are any enemies at all
                if (enemies.isEmpty()) {
                    // no enemies exist, increment wave and spawn new enemies
                    //System.out.println("Wave: " + waveCounter);
                    score++;
                    game.incrementScore();
                    updateScore(score);
                    spawnNewEnemy(currPlr);
                    return; // exit
                }

                // find the first enemy within attack range
                Enemy targetEnemy = null;
                for (Enemy enemy : enemies) {
                    if (enemy.GetEnemyHP() > 0) {
                        double pX = currPlr.getPositionX();
                        double pY = currPlr.getPositionY();

                        double eX = enemy.getPositionX();
                        double eY = enemy.getPositionY();

                        // calculate distance between player and enemy
                        double magnitude = Math.sqrt(Math.pow(eX - pX, 2) + Math.pow(eY - pY, 2));
                        if (magnitude < 45) {
                            targetEnemy = enemy;
                            break;
                        }
                    }
                }

                // check if enemy is null
                if (targetEnemy != null) {
                    // target enemy found within range and damage
                    //System.out.println("Within bounds");
                    targetEnemy.EnemyDamage();

                    // Check if the enemy is dead after damage
                    if (targetEnemy.GetEnemyHP() <= 0) {
                        game.removeEntity(targetEnemy); // Remove the enemy from the game
                        holderPane.getChildren().remove(targetEnemy); // Remove from the scene
                    }
                } else {
                    System.out.println("No enemies within range.");
                }
            } // end of conditional if block
        }); // end of eventHandler
    } // end of listener method

    // method to spawn a new enemy
    private void spawnNewEnemy(Player currPlr) {
        // increment wave counter and spawn a new enemy
        waveCounter++;
        System.out.println("Wave: " + waveCounter); // debug

        // spawn enemies based on wave number
        if (waveCounter >= 5) {
            for (int i = 0; i < 3; i++) {
                Enemy e = createEnemy(currPlr, rand.nextInt(100, 600), rand.nextInt(100, 400));
                game.addEntity(e);

                // add the enemy to the holderPane
                holderPane.getChildren().add(e);
            }
        } else if (waveCounter >= 10) {
            for (int i = 0; i < 5; i++) {
                Enemy e = createEnemy(currPlr, rand.nextInt(100, 600), rand.nextInt(100, 400));
                game.addEntity(e);

                // add the enemy to the holderPane
                holderPane.getChildren().add(e);
            }
        } else {
            Enemy e = createEnemy(currPlr, rand.nextInt(100, 600), rand.nextInt(100, 400));
            game.addEntity(e);
            // add the enemy to the holderPane
            holderPane.getChildren().add(e);
        }
    } // end of spawnNewEnemy method

    // method to return Enemy object
    private Enemy createEnemy(Player currPlr, int x, int y) {
        Enemy newEnemy = new Enemy(Main.playerVis, x, y, "player", currPlr, 5);
        return newEnemy;
    }
} // end of GameScene class
