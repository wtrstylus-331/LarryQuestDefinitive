module org.group.larryquestdefinitive {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.group.larryquestdefinitive to javafx.fxml;
    exports org.group.larryquestdefinitive;
}