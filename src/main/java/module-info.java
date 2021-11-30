module SfStrategyGame {

    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires static lombok;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;


    exports application to javafx.graphics;
    exports controller to javafx.fxml;
    exports model to com.fasterxml.jackson.databind;
    exports model.technologies to com.fasterxml.jackson.databind;
    exports model.buildings to com.fasterxml.jackson.databind;
    exports model.spaceShips;

    opens controller to javafx.fxml;
    exports service.gameSaving to com.fasterxml.jackson.databind;


}

