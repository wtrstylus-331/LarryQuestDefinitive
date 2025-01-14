package org.group.larryquestdefinitive;

import javafx.application.Application;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.group.larryquestdefinitive.scenes.TitlePage;

public class Main extends Application {
    // Static reference to global root for scene creation
    public static AnchorPane root = new AnchorPane();

    // Initialize the stage
    @Override
    public void start(Stage stage) {
        TitlePage title = new TitlePage(Main.root, Constants.WIDTH, Constants.HEIGHT);

        stage.setTitle("Larry Quest: Definitive Edition");
        stage.setScene(title);
        stage.setResizable(false);
        stage.show();
    }

    // Run the program
    public static void main(String[] args) {
        launch();
    }
} // end of Main class