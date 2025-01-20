package org.group.larryquestdefinitive.scenes;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.group.larryquestdefinitive.Constants;
import org.group.larryquestdefinitive.Game;
import org.group.larryquestdefinitive.Main;
import org.group.larryquestdefinitive.control.GameLoop;

public class TitlePage extends Scene implements Constants {
    public AnchorPane parent;
    private Button button;
    private Text title;
    private Game game;
    private GameLoop loop;

    public TitlePage(Parent root, double w, double h, Game game, GameLoop loop) {
        super(root, w, h);
        this.parent = (AnchorPane) root;
        this.game = game;
        this.loop = loop;

        this.addElements();
        this.addListener();
    }

    // Method to add UI elements to the scene
    private void addElements() {
        this.title = new Text("Larry Quest: Definitive Edition");
        this.title.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 60));
        this.title.setStroke(Color.BLACK);
        this.title.setStrokeWidth(2);
        this.title.setFill(Color.WHITE);
        this.title.setX(70);
        this.title.setY(100);

        this.button = new Button("Play");
        this.button.setFont(Font.font("arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));
        this.button.setMinSize(150,75);
        this.button.setBackground(Background.fill(Color.WHITE));
        this.button.setStyle("-fx-border-color: #000000; -fx-border-width: 2px;");
        this.button.setLayoutX(425);
        this.button.setLayoutY(250);
        this.button.setFocusTraversable(false);

        this.parent.getChildren().add(this.title);
        this.parent.getChildren().add(this.button);
    } // end of addElements method

    // Method to add button event handlers to the buttons
    private void addListener() {
        this.button.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            this.button.setFont(Font.font("arial", FontWeight.NORMAL, FontPosture.REGULAR, 50));
            this.button.setMinSize(160,80);
            this.button.setBackground(Background.fill(Color.rgb(200,200,200)));
            this.button.setLayoutX(415);
            this.button.setLayoutY(240);
        });

        this.button.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            this.button.setFont(Font.font("arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));
            this.button.setMinSize(150,75);
            this.button.setBackground(Background.fill(Color.WHITE));
            this.button.setLayoutX(425);
            this.button.setLayoutY(250);
        });

        this.button.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            // set to intro scene or whatever
            // temporary testing
            if (Main.debugMode) {
                GameScene test = new GameScene();
                test.setMap(new ImageView(new Image(Main.class.getResourceAsStream("scenes/Level2.png"))), 900, 500);

                /*test.addCollider(20,50,50,500);
                test.addCollider(925,50,50,500);
                test.addCollider(35,20,900,50);
                test.addCollider(35,530,900,50);
                   */
                
                Main.stage.setScene(game.getScene());
                game.setScene(test);
                loop.start();
            }
        });
    } // end of addListener method
} // end of TitlePage class
