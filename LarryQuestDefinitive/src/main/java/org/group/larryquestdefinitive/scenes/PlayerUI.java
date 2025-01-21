package org.group.larryquestdefinitive.scenes;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;

public class PlayerUI {

    private Pane holderPane;  // This will hold the UI elements
    private Rectangle hpBarBackground;  // Background of the HP bar
    private Rectangle hpBar;  // The actual HP bar (this will change width based on HP)

    public PlayerUI(Pane parent) {
        // Create a new Pane for UI elements
        this.holderPane = new Pane();

        // Create the background of the HP bar
        hpBarBackground = new Rectangle(150, 20); // Size of the background bar
        hpBarBackground.setFill(Color.GRAY); // Background color of HP bar

        // Create the actual HP bar (starts with full HP)
        hpBar = new Rectangle(150, 20); // Same size as background
        hpBar.setFill(Color.GREEN);  // The bar's color (represents HP)

        // Position the HP bar at the top-left corner
        hpBarBackground.setX(10);
        hpBarBackground.setY(10);  // Adjust the Y position if needed
        hpBar.setX(10);
        hpBar.setY(10);  // Adjust the Y position if needed

        // Add the elements to the holderPane
        this.holderPane.getChildren().addAll(hpBarBackground, hpBar);

        // Add the holderPane to the parent (the main game scene)
        parent.getChildren().add(this.holderPane);
    }

    // Update the health bar based on current HP (pass in the current HP)
    public void UpdateHP(int playerHP, int maxHP) {
        // Calculate the width of the HP bar based on the percentage of HP
        double healthPercentage = (double) playerHP / maxHP;
        double newWidth = 150 * healthPercentage;  // 150 is the max width of the bar
        hpBar.setWidth(newWidth);

        // Update the color based on the HP percentage
        if (healthPercentage > 0.5) {
            hpBar.setFill(Color.GREEN);  // Full health
        } else if (healthPercentage > 0.2) {
            hpBar.setFill(Color.YELLOW);  // Low health
        } else {
            hpBar.setFill(Color.RED);  // Critical health
        }

        // Optionally update the text to show current HP (e.g., "HP: 50/100")
        Text hpText = new Text(10, 40, "HP: " + playerHP + "/" + maxHP);
        hpText.setFont(Font.font("Arial", 16));
        this.holderPane.getChildren().add(hpText);
    }
}
