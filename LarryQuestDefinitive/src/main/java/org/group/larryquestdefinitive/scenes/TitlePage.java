package org.group.larryquestdefinitive.scenes;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TitlePage extends Scene {
    private AnchorPane parent;
    private Text title;

    public TitlePage(Parent root, double w, double h) {
        super(root, w, h);
        this.parent = (AnchorPane) root;

        this.test();
    }

    private void test() {
        this.title = new Text("Larry Quest: Definitive Edition");
        this.title.setFont(Font.font("arial", FontWeight.NORMAL, FontPosture.REGULAR, 60));
        this.title.setStroke(Color.BLACK);
        this.title.setStrokeWidth(2);
        this.title.setFill(Color.WHITE);
        this.title.setX(50);
        this.title.setY(100);

        this.parent.getChildren().add(this.title);
    }
}
