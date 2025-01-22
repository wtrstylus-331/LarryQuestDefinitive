package org.group.larryquestdefinitive.scenes;

import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import org.group.larryquestdefinitive.entities.Enemy;
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
import java.util.Collection;
import java.util.List;

public class GameScene extends AnchorPane {
    private Pane holderPane;
    private ImageView map;
    private Rectangle hpBarBackground;
    private Rectangle hpBar;

    public GameScene() {
        holderPane = new Pane();
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

        this.getChildren().add(holderPane);
        this.getChildren().add(hpBarBackground);    // Add the background to the scene
        this.getChildren().add(hpBar);              // Add the HP bar to the scene
        this.listener();

        hpBarBackground.toFront();
        hpBar.toFront();
    }

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
    }

    public void setMap(ImageView currentMap, int w, int h) {
        this.map = currentMap;
        this.map.setFitWidth(w);
        this.map.setFitHeight(h);

        double centerX = (Constants.WIDTH - this.map.getFitWidth()) / 2;
        double centerY = (Constants.HEIGHT - this.map.getFitHeight()) / 2;

        this.map.setX(centerX);
        this.map.setY(centerY);

        this.holderPane.getChildren().add(this.map);
    }

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
    }

    public void addCollider(int x, int y, int collisionW, int collisionH) {
        Rectangle collider = createCollider(collisionW,collisionH);
        collider.setX(x);
        collider.setY(y);

        this.holderPane.getChildren().add(collider);
    }

    private Rectangle createCollider(int w, int h) {
        Collider col = new Collider(w,h);

        if (Main.debugMode) {
            col.fill(Color.rgb(200,0,200, 0.7));
        } else {
            col.fill(Color.rgb(0,0,0,0));
        }

        return col;
    }

    public Pane getHolder() {
        return holderPane;
    }

    private void listener() {
        super.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                Player currPlr = null;
                Enemy enemy = null;

                // Traverse all nodes recursively to find Player and Enemy
                for (Node node : getAllNodes(super.getScene().getRoot())) {
                    if (node instanceof Player) {
                        currPlr = (Player) node;
                    } else if (node instanceof Enemy) {
                        enemy = (Enemy) node;
                    }

                    // Exit early if both are found
                    if (currPlr != null && enemy != null) {
                        break;
                    }
                }

                // Check if both currPlr and enemy are set
                if (currPlr != null && enemy != null) {
                    double pX = currPlr.getPositionX();
                    double pY = currPlr.getPositionY();

                    double eX = enemy.getPositionX();
                    double eY = enemy.getPositionY();

                    double magnitude = Math.sqrt(Math.pow(eX - pX, 2) + Math.pow(eY - pY, 2));

                    if (magnitude < 45) {
                        System.out.println("within bounds");
                    } else {
                        System.out.println("outside bounds");
                    }
                } else {
                    System.out.println("Player or Enemy is null!");
                }
            }
        });
    }

    // Helper method to traverse the entire node tree
    private Collection<? extends Node> getAllNodes(Node root) {
        List<Node> nodes = new ArrayList<>();
        if (root instanceof Pane) {
            nodes.add(root);
            for (Node child : ((Pane) root).getChildren()) {
                nodes.addAll(getAllNodes(child));
            }
        } else {
            nodes.add(root);
        }
        return nodes;
    }
}
