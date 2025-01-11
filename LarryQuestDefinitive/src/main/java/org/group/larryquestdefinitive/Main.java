package org.group.larryquestdefinitive;

import javafx.application.Application;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.group.larryquestdefinitive.scenes.TitlePage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        AnchorPane root = new AnchorPane();
        TitlePage title = new TitlePage(root, Constants.WIDTH, Constants.HEIGHT);

        stage.setTitle("Larry Quest: Definitive Edition");
        stage.setScene(title);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}