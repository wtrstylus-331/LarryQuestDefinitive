package org.group.larryquestdefinitive.scenes;

import org.group.larryquestdefinitive.Main;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Map extends Pane {

    public Map() {
        // Set background color or image
        this.setStyle("-fx-background-color: #2a2a2a;");

        // Add levels to the map
        createLevel(50, 50, "level1.png", "Level 1");
        createLevel(200, 50, "level2.png", "Level 2");
        createLevel(350, 50, "level3.png", "Level 3");
    }

    private void createLevel(double x, double y, String imagePath, String levelName) {
        // Load the level image
        Image image = new Image(Main.class.getResourceAsStream("scenes/" + imagePath), 100, 100, true, true);
        ImageView imageView = new ImageView(image);
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);

        // Configure the highlight effect
        DropShadow highlightEffect = new DropShadow();
        highlightEffect.setColor(Color.YELLOW);
        highlightEffect.setSpread(0.5);
        highlightEffect.setRadius(10);
        highlightEffect.setOffsetX(0);
        highlightEffect.setOffsetY(0);

        // Add hover effect to the image
        imageView.setOnMouseEntered(event -> applyHighlight(imageView, highlightEffect));
        imageView.setOnMouseExited(event -> removeHighlight(imageView));

        // Add a label for the level
        Text label = new Text(levelName);
        label.setFont(Font.font("Arial", 16));
        label.setFill(Color.WHITE);
        label.setLayoutX(x + 10); // Adjust based on image size
        label.setLayoutY(y + 120);

        // Add to the pane
        this.getChildren().addAll(imageView, label);
    }

    private void applyHighlight(ImageView imageView, DropShadow highlightEffect) {
        imageView.setEffect(highlightEffect);
    }

    private void removeHighlight(ImageView imageView) {
        imageView.setEffect(null);
    }
}
