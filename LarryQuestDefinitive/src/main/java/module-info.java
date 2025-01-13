module org.group.larryquestdefinitive {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens org.group.larryquestdefinitive to javafx.fxml;
    exports org.group.larryquestdefinitive;
    exports org.group.larryquestdefinitive.scenes;
    opens org.group.larryquestdefinitive.scenes to javafx.fxml;
}